package com.example.yjyy.util;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
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

    public static void main(String args[]){
        Map<String,String> param = new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        param.put("grant_type","client_credential");
        String result = HttpUtils.doGet("https://api.weixin.qq.com/cgi-bin/token?",param);
        System.out.println(result);
    }
}
