package com.yihu.myt.http;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.util.StringUtil;

public class QueryAction {

	
	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class);
	public HttpResponseContext gradeReport(HttpRequestContext request) {
		try {
			String starttime =request.getParameter("starttimes");
			String endtime=request.getParameter("endtimes");
			int provinceid=request.getInt("provinceid"); 
			String operatorname=request.getParameter("operatorname");
			int  rows = request.getInt("start");
			int page = request.getInt("limit");
			String rt =bookenrolService.getGradReport(operatorname, provinceid, rows, page, starttime, endtime);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
public HttpResponseContext gradeReportNew(HttpRequestContext request) {
		
		try {
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String starttime =  jsonobj.get("sBTime") != null ? jsonobj.get("sBTime").toString(): null ;
			String endtime =  jsonobj.get("sETime") != null ? jsonobj.get("sETime").toString(): null ;
			String provinceid =  jsonobj.get("provinceid") != null ? jsonobj.get("provinceid").toString(): null ;
			int pvid  = 0;
			if(StringUtil.isNotEmpty(provinceid)){
				 pvid = Integer.valueOf(provinceid); 
			}
			String operatorname =  jsonobj.get("operatorname") != null ? jsonobj.get("operatorname").toString(): null ;
			int  rows = request.getInt("start");
			int page = request.getInt("limit");
			String rt =bookenrolService.getGradReport(operatorname, pvid, rows, page, starttime, endtime);
			JSONObject rtjs =new  JSONObject(rt);
			JSONArray res = rtjs.getJSONArray("result");
			int allcounts = 0;
			int alloursdc  =0;
			int prodc = 0;
			int cd1 = 0;
			int cd2 = 0;
			for(int i = 0 ; i < res.length() ;i++){
				 allcounts  = allcounts  + res.getJSONObject(i).getInt("allcounts");
				 alloursdc  = alloursdc  + res.getJSONObject(i).getInt("alloursdc");
				 prodc  = prodc  + res.getJSONObject(i).getInt("prodc");
				 cd1 = cd1 +  res.getJSONObject(i).getInt("cd1");
				 cd2 = cd2 +  res.getJSONObject(i).getInt("cd2");
			}
			JSONObject allin =new  JSONObject();
			allin.put("provname", "×Ü¼Æ");
			allin.put("opname", " ");
			allin.put("allcounts", allcounts);
			allin.put("alloursdc", alloursdc);
			allin.put("prodc", prodc);
			allin.put("cd1", cd1);
			allin.put("cd2", cd2);
			rtjs.getJSONArray("result").put(allin);
			return new HttpResponseContext(rtjs.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
}
