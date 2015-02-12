package com.yihu.myt.util;

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.coreframework.remoting.reflect.RpcException;

public class StringUtil {

	// 星期数组
	public static String[] weekArr = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六" };
	public static String ClobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		if(clob!=null){
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		}
		return reString;
	} 
	/**
	 * 判断对象是否为空或者空串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		
		if (obj instanceof String) {
			return (obj == null || obj.toString().trim().length() == 0);
		}
		if (obj instanceof Long) {
			return (obj == null || (Long) obj == 0);
		}
		if (obj instanceof Integer) {
			return (obj == null || (Integer) obj == 0);
		}
		return obj == null;
	}
	
	/**
	 * 判断对象是否为空或者空串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	

	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();

	}

	/**
	 * 异常字符串处理方法
	 * 
	 * @param e
	 * @return
	 */
	public static String getException(Exception e) {
		String str = RpcException.getExceptionInfo(e);
		if (str.indexOf("Caused by") != -1) {
			str = str.substring(str.indexOf("Caused by:") + 10);
			str = str.substring(0, str.indexOf("\r\n\t"));
		} else {
			str = e.getMessage();
		}

		return str;
	}

	/**
	 * 如果为null则返回""
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJSONValue(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 如果为null则返回""
	 * 
	 * @param obj
	 * @return
	 */
	public static String getDecodeValue(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		} else {
			try {
				return URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	public static int parseInt(Integer i) {
		if (i == null)
			return 0;
		return i;
	}

	/**
	 * 去掉数组第一个值
	 * 
	 * @param arr
	 * @return
	 */
	public static String deleteFirstNode(String[] arr) {
		if (arr.length < 2)
			return null;
		String[] arrNew = new String[arr.length - 1];
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				continue;
			}
			arrNew[i - 1] = arr[i];
		}
		String ts = "";
		for (String s : arrNew) {
			ts += s + ",";
		}
		return ts.substring(0, ts.length() - 1);
	}
	
}
