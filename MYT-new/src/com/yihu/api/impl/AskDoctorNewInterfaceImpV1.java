package com.yihu.api.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.yihu.api.api.AskDoctorNewInterfaceV1;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.IAskDoctorNewInterfaceV1Service;
import com.yihu.myt.service.service.IDoctorFreeCountService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.util.LoggerUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.wsgw.api.InterfaceMessage;

public class AskDoctorNewInterfaceImpV1 implements   AskDoctorNewInterfaceV1
{
	
	
	private static  org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AskDoctorNewInterfaceImpV1.class);

	

	private static IPostService postService = Ioc
			.get(IPostService.class);
	
	private static IAskDoctorNewInterfaceV1Service askDoctorNewInterfaceService = Ioc
			.get(IAskDoctorNewInterfaceV1Service.class);
	
	
	
	/**
	 * @title
	 * demo
	 * 
	 * 
	 * @desc
	 * demo
	 * @param msg
	 * @return
	 */
	public String demo(InterfaceMessage msg) {
		
		String tag = "demo";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer intdemo = json.get("demo") == null ? null : json
					.getInt("demo");
			String Stringdemo = json.getString("demo") == null ? null : json
					.getString("demo");
			
			
			
			
			
			
			
			return  null;
		
			}catch (JSONException e) {
		     log.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			log.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	
	
	

	/**
	 * @title
	 * �ṩʵʱ��ѯ�������ظ�����ɵ���������
	 * 
	 * 
	 * @desc
	 * ����
	 * ҽ�������ѻظ�XX���������
	 * ҽ�������ѻظ�XX�����ָ������
	 * ҽ��������Ҫ��ɵ�����������ĿǰΪ150������ʹ��BOSS�ֵ����洢�����ֵ��
	 * @param msg
	 * @return
	 */
	public String queryDoctorQueFree(InterfaceMessage msg) {
		
		String tag = "queryDoctorQueFree";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");

			
			
			String    back= askDoctorNewInterfaceService.queryDoctorQueFree(doctorID);
			
			JSONObject backjson=JSONObject.fromObject(back);
			
			 JSONArray rs =   backjson.getJSONArray("result");

			
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			
			
			rt.put("Result",rs.toString());
			rt.put("finNum",150);
			
			
		
			return  rt.toString();
		
			}catch (JSONException e) {
		     log.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			log.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	
}
