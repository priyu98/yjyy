package com.example.yjyy.util;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.security.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Tools {

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(Object s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}


	/**
	 * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
	 *
	 * @param obj 被计算长度的对象
	 * @return 长度
	 */
	public static int length(Object obj) {
		if (obj == null) {
			return 0;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length();
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).size();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).size();
		}

		int count;
		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			count = 0;
			while (iter.hasNext()) {
				count++;
				iter.next();
			}
			return count;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			count = 0;
			while (enumeration.hasMoreElements()) {
				count++;
				enumeration.nextElement();
			}
			return count;
		}
		if (obj.getClass().isArray() == true) {
			return Array.getLength(obj);
		}
		return -1;
	}

	public static boolean isListEqual(List l0, List l1){
		if (l0 == l1)
			return true;
		if (l0 == null && l1 == null)
			return true;
		if (l0 == null || l1 == null)
			return false;
		if (l0.size() != l1.size())
			return false;
		for (Object o : l0) {
			if (!l1.contains(o))
				return false;
		}
		for (Object o : l1) {
			if (!l0.contains(o))
				return false;
		}
		return true;
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyMMddHHmmssSSS，日期转字符串
	 *
	 * @param date
	 * @return yyMMddHHmmssSSS
	 */
	public static String dateToStr(Date date) {
		return date2Str(date, "yyMMddHHmmssSSS");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}

	/**
	 * 按照yyyy-MM-dd的格式，字符串转日期
	 *
	 * @param date
	 * @return
	 */
	public static Date str2Date1(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}



	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	public static String getLastMonthFirstDay(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}

	public static String getLastMonthLastDay(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sf.format(calendar.getTime());
	}

	/**
	 * 把时间根据时、分、秒转换为时间段
	 * 
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now;

		try {
			now = new Date();
			Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * 写txt里的单行内容
	 *
	 *            文件路径
	 * @param content
	 *            写入的内容
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 *
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 读取txt里的单行内容
	 *
	 *            文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	
	 public static String appMd5(File file) {
	        MessageDigest digest = null;
	        FileInputStream fis = null;
	        byte[] buffer = new byte[1024];

	        try {
	            if (!file.isFile()) {
	                return "";
	            }

	            digest = MessageDigest.getInstance("MD5");
	            fis = new FileInputStream(file);

	            while (true) {
	                int len;
	                if ((len = fis.read(buffer, 0, 1024)) == -1) {
	                    fis.close();
	                    break;
	                }

	                digest.update(buffer, 0, len);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }

	        BigInteger var5 = new BigInteger(1, digest.digest());
	        return String.format("%1$032x", new Object[]{var5});
	    }

	/**
	 * 获取所在 周 第一天
	 * @param date
	 * @return
	 */
	public static Date getTheFirstDayOfWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getADayOfWeek(cal).getTime();
	}

	private static Calendar getADayOfWeek(Calendar day) {
		final int currDayOfWeek = day.get(Calendar.DAY_OF_WEEK);
		final int week = 7;
		if (currDayOfWeek == Calendar.MONDAY) {
			return day;
		}
		int diffDay = Calendar.MONDAY - currDayOfWeek;
		if (currDayOfWeek == Calendar.SUNDAY) {
			diffDay -= week;
		}
		day.add(Calendar.DATE, diffDay);
		return day;
	}

	//获取参数所在周的周日日期
	public static String getSundayDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int currDay = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date(date.getTime()-(currDay-1)*24*60*60*1000));
	}

	//获取星期数
	public static int getWeekDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(int code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", code);
        return result;
    }

    //微信数据解密
	public static String decrypt(String session_key, String iv, String encryptData) {
		String decryptString = "";
		init();
		byte[] sessionKeyByte = Base64.decodeBase64(session_key);
		byte[] ivByte = Base64.decodeBase64(iv);
		byte[] encryptDataByte = Base64.decodeBase64(encryptData);

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key key = new SecretKeySpec(sessionKeyByte, "AES");
			AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
			algorithmParameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
			byte[] bytes = cipher.doFinal(encryptDataByte);
			decryptString = new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptString;
	}

	private static boolean hasInited = false;

	public static void init() {
		if (hasInited) {
			return;
		}
		Security.addProvider(new BouncyCastleProvider());
		hasInited = true;
	}

	public static String getWxXml(HttpServletRequest request) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request
							.getInputStream()));

			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
			// sb为微信返回的xml
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String map2string(Map<String,Object> map){
		StringBuilder str = new StringBuilder();
		String[] sortedKeys = map.keySet().toArray(new String[]{});
		Arrays.sort(sortedKeys);// 排序请求参数
		for(String key: sortedKeys){
			str.append(key).append("=").append(map.get(key)).append("&");
		}
		return str.toString().substring(0,str.lastIndexOf("&"));
	}

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 *
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) {
		try {
			if (null == strxml || "".equals(strxml)) {
				return null;
			}

			Map m = new HashMap();
			InputStream in = String2Inputstream(strxml);
			SAXBuilder builder = new SAXBuilder();
			Document doc;

			doc = builder.build(in);

			Element root = doc.getRootElement();
			List list = root.getChildren();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String k = e.getName();
				String v = "";
				List children = e.getChildren();
				if (children.isEmpty()) {

					v = e.getTextNormalize();
				} else {

					v = getChildrenText(children);

				}
				m.put(k, v);
			}

			// 关闭流
			in.close();

			return m;
		} catch (JDOMException | IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

	/**
	 * 获取子结点的xml
	 *
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		map.put("appid","wxd930ea5d5a258f4f");
		map.put("mch_id","10000100");
		map.put("device_info","1000");
		map.put("body","test");
		map.put("nonce_str","ibuaiVcKdpRxkhJA");
		String str = map2string(map)+"&key=192006250b4c09247ec02edce69f6a2d";
		System.out.println(MD5Util.getMD5Info(str));
	}
}
