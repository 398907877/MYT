package com.yihu.myt.mgr;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ApiUtil {
	public static String getJsonRt(Integer code ,String message){
		JSONObject rtjon = new JSONObject();
		JSONArray result = new JSONArray();
		rtjon.put("Code", code);
		rtjon.put("Message", message);
		rtjon.put("Result", result);
		return rtjon.toString();
	}
}
