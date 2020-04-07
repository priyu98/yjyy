package com.example.yjyy.util;

import com.example.yjyy.config.MyX509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xinke
 */
@Component
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class.getName());
    private static final String appid = "wxf275b0bcada2b33b";
    private static final String secret = "f9088de1385f71c020c8fc436a5ea072";
    private static final String mch_id = "1582805761";
    private static final String key = "1";


    private static PoolingHttpClientConnectionManager connManager = null;

    private static CloseableHttpClient client = null;

    @PostConstruct
    public void init() {
        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(1000);
        connManager.setDefaultMaxPerRoute(1000);

        client = HttpClients.custom().setConnectionManager(connManager)
                .setConnectionManagerShared(true).build();
    }


    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {

        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }


            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = new JSONObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    // public static String doPostJson(String url, String json,String tokenHeader) {
    //
    //     CloseableHttpResponse response = null;
    //     try {
    //         // 创建Http Post请求
    //         url = Normalizer.normalize(url, Normalizer.Form.NFKC);
    //         HttpPost httpPost = new HttpPost(url);
    //         // 创建请求内容
    //         httpPost.setHeader("HTTP Method","POST");
    //         httpPost.setHeader("Connection","Keep-Alive");
    //         httpPost.setHeader("Content-Type","application/json;charset=utf-8");
    //
    //         if (!url.contains("userauth")) {
    //             httpPost.setHeader("cmsgateway-token", tokenHeader);
    //         }
    //
    //         StringEntity stringEntity = new StringEntity(json);
    //         httpPost.setEntity(stringEntity);
    //
    //         // 执行http请求
    //         response = client.execute(httpPost);
    //         if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
    //
    //             HttpEntity entity = response.getEntity();
    //             if (null != entity) {
    //                 resultString = EntityUtils.toString(entity, "UTF-8");
    //
    //                 // if (url.contains("userauth")) {
    //                 //     dealToken(resultString);
    //                 // }
    //             } else {
    //                 resultString = StringUtils.ENTITY_BLANK;
    //             }
    //
    //             try {
    //                 EntityUtils.consume(entity);
    //             } catch (IOException e) {
    //                 LOGGER.error("release entity failed {}", e.getMessage());
    //             }
    //         }
    //     } catch (Exception e) {
    //         return StringUtils.CONNECT_ERROR;
    //     } finally {
    //         if (null != response) {
    //             try {
    //                 EntityUtils.consume(response.getEntity());
    //                 response.close();
    //             } catch (IOException e) {
    //                 LOGGER.error("release response failed {}", e.getMessage());
    //             }
    //         }
    //     }
    //     return resultString;
    // }

    //微信小程序code获取openid
    public static String code2Session(String code){
        Map<String,String> param = new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        return doGet("https://api.weixin.qq.com/sns/jscode2session?",param);
    }

    //微信程序发送订阅消息
    public static String wxSendMsg(String access_token,String touser,String template_id,Map<String,Object> data){
        Map<String,Object> param = new HashMap<>();
        param.put("touser",touser);
        param.put("template_id",template_id);
        param.put("data",data);
        return httpsRequest("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token,"POST",new JSONObject(param).toString()).toString();
    }

    //微信统一下单api
    public static String wxUnifiedOrder(String payid, String openid, BigDecimal price){
        String nonce_str = String.valueOf(Tools.getRandomNum());
        String body = "育瑜伽-会员卡购买";
        String out_trade_no = payid;
        int total_fee = (int)(price.floatValue()*100);
        String spbill_create_ip = "39.106.171.39";
        String notify_url = "https://yuyoga.club/yjyy/wxController/wxReturnOrder";
        String trade_type = "JSAPI";
        Map<String,Object> param = new HashMap<>();
        param.put("appid",appid);
        param.put("mch_id",mch_id);
        param.put("nonce_str",nonce_str);
        param.put("body",body);
        param.put("out_trade_no",out_trade_no);
        param.put("total_fee",total_fee);
        param.put("spbill_create_ip",spbill_create_ip);
        param.put("notify_url",notify_url);
        param.put("trade_type",trade_type);
        param.put("openid",openid);
        String str = Tools.map2string(param)+"&key="+key;
        String sign = MD5Util.getMD5Info(str);
        param.put("sign",sign);
        return doPost("https://api.mch.weixin.qq.com/pay/unifiedorder",param);
    }

    public static void main(String args[]){
        Map<String, Object> data = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();
        Map<String, String> map4 = new HashMap<>();
        map1.put("value", "1");
        map2.put("value", "1");
        map3.put("value", "2020-4-7 11:02");
        map4.put("value", "1");
        data.put("name1", map1);
        data.put("name2", map2);
        data.put("date3", map3);
        data.put("thing4", map4);
        System.out.println(HttpUtils.wxSendMsg("32_T0r8hfCSl6Z9diJNNc63EsliTowBQgUy0mZNA05L21kc2H3cePoGjPKFn3l7Xeq-GgvsHVqoOnQHrmqSEMq2NjZdbbw7rD3UU1R8YIvnX33ZscmU4W4GHhVv2WQu2cEy5PyJd4R2ijFPAIZ8BFFdABASWW","o9MUC5SRzbeUR9iiEAaozrFrS5u8","7RDWriJThlCrK9KtiWnLsex1GDCUJnn7DGHoiSdazUI",data));
    }
}
