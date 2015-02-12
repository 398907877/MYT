package com.yihu.api.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import com.common.json.JSONException;
//import com.common.json.JSONObject;
import com.yihu.account.api.AccCollectionReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.api.api.AskDoctorControlApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.IDoctorFreeCountEditService;
import com.yihu.myt.service.service.IDoctorFreeCountService;
import com.yihu.myt.service.service.IUserFreeCountService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.DoctorFreeCountEditVo;
import com.yihu.myt.vo.DoctorFreeCountVo;
import com.yihu.myt.vo.UserFreeCountVo;

import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

public class AskDoctorControlApiImpl implements AskDoctorControlApi {

	private static IDoctorFreeCountService doctorFreeCountService = Ioc
			.get(IDoctorFreeCountService.class);
	private static IDoctorFreeCountEditService doctorFreeCountEditService = Ioc
			.get(IDoctorFreeCountEditService.class);
	private static IUserFreeCountService userFreeCountService = Ioc
			.get(IUserFreeCountService.class);

	 
	public static void main(String[] args) throws Exception {
		JSONObject json = new JSONObject();
		/*json.put("doctorUid", "710003065");
		json.put("userID", "10132238");*/
		//json.put("userID", "10854906");
		
		//json.put("ufcid", "149");
		//json.put("start", "0");
		//json.put("pageSize", "10");
		//"55
		//json.put("pageSize", "10");
		/*json.put("userID", "10132238");
		json.put("start", "0");
		json.put("pageSize", "10");*/
		//json.put("userID", "12423803");
		json.put("doctorUid", "710046153");
		json.put("userID", "12361905");
		//{"userID":"12423803","doctorUid":"710036857"}
		String str = 	"{'userID':'12423803','start':'0','pageSize':'6'}";
		String addDoctorFreeCount = "{'doctorUid':'710003065','freeCount':'5'}";
		String getDoctorFreeCount = "{'doctorUid':'710075654'}";
		String updateDoctorFreeCount = "{'dfcId':'1101','freeCount':'100'}";
		
		InterfaceMessage msg = new InterfaceMessage();
		AskDoctorControlApiImpl dc = new AskDoctorControlApiImpl();
		msg.setParam(getDoctorFreeCount);
		String rt = dc.getDoctorFreeCount(msg);
		//String rt = dc.getDoctorFreeCount(msg);
		System.out.println(rt);
	}

	/**
	 * 医生免费咨询新增
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String addDoctorFreeCount(InterfaceMessage msg) {
		String tag = "addDoctorFreeCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int freeCount = json.get("freeCount") == null ? null : json
					.getInt("freeCount");
			/*int remainFreeCount = json.get("remainFreeCount") == null ? null
					: json.getInt("remainFreeCount");*/
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo.setFreeCount(freeCount);
			dcFreeVo.setRemainFreeCount(freeCount);
			dcFreeVo.setOccupyFreeCount(0);
			dcFreeVo.setBeginTime(DateUtil.dateFormat(new Date()));
			dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
			dcFreeVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			dcFreeVo.setEndTime(DateUtil.dateFormat(mf));
			int rt = doctorFreeCountService.insertDoctorFreeCount(dcFreeVo);
			if (rt < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "写入医生免费数据失败");			
			}
			return WsUtil.getRetVal(msg.getOutType(), 1, "医生数据写入成功");		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String addDoctorFreeCountForApp(InterfaceMessage msg) {
		String tag = "addDoctorFreeCountForApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int freeCount = json.get("freeCount") == null ? null : json
					.getInt("freeCount");
			/*int remainFreeCount = json.get("remainFreeCount") == null ? null
					: json.getInt("remainFreeCount");*/
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo.setFreeCount(freeCount);
			dcFreeVo.setRemainFreeCount(freeCount);
			dcFreeVo.setOccupyFreeCount(0);
			dcFreeVo.setBeginTime(DateUtil.dateFormat(new Date()));
			dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
			dcFreeVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			dcFreeVo.setEndTime(DateUtil.dateFormat(mf));
			int rt = doctorFreeCountService.insertDoctorFreeCount(dcFreeVo);
			if (rt < 0) {
				return ApiUtil.getJsonRt(-14444, "写入医生免费数据失败。"); 
			}
			return ApiUtil.getJsonRt(10000, "医生数据写入成功。"); 
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 医生免费咨询次数改变
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String  updateDoctorFreeCount(InterfaceMessage msg) {
		String tag = "updateDoctorFreeCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer freeCount = json.get("freeCount") == null ? null : json
					.getInt("freeCount");
			Integer dfcId = json.get("dfcId") == null ? null : json
					.getInt("dfcId");
			DoctorFreeCountEditVo docFCEVo = new DoctorFreeCountEditVo();
			docFCEVo.setDfcId(dfcId);
			docFCEVo.setFreeCount(freeCount);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			docFCEVo.setEndTime(DateUtil.dateFormat(mf));
			docFCEVo.setBeginTime(DateUtil.dateFormat(new Date()));
			int rt =  doctorFreeCountEditService.insertDoctorFreeCountEdit(docFCEVo);			
			if (rt < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "日志数据插入失败。");
			}
			return WsUtil.getRetVal(msg.getOutType(), 1, "插入成功。");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String  updateDoctorFreeCountForApp(InterfaceMessage msg) {
		String tag = "updateDoctorFreeCountForApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer freeCount = json.get("freeCount") == null ? null : json
					.getInt("freeCount");
			Integer dfcId = json.get("dfcId") == null ? null : json
					.getInt("dfcId");
			DoctorFreeCountEditVo docFCEVo = new DoctorFreeCountEditVo();
			docFCEVo.setDfcId(dfcId);
			docFCEVo.setFreeCount(freeCount);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			docFCEVo.setEndTime(DateUtil.dateFormat(mf));
			docFCEVo.setBeginTime(DateUtil.dateFormat(new Date()));
			int rt =  doctorFreeCountEditService.insertDoctorFreeCountEdit(docFCEVo);		
			if (rt < 0) {
				return ApiUtil.getJsonRt(-14444, "日志数据插入失败。"); 
			}
			return ApiUtil.getJsonRt(10000, "插入成功。"); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 医生免费咨询次数获取
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getDoctorFreeCount(InterfaceMessage msg) {
		String tag = "getDoctorFreeCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo = doctorFreeCountService.queryDoctorFreeCount(dcFreeVo);
			JSONObject rtJson = new JSONObject();
			if (dcFreeVo != null) {
				DoctorFreeCountEditVo docFCEVo = new DoctorFreeCountEditVo();
				docFCEVo.setDfcId(dcFreeVo.getDfcId());
				docFCEVo = doctorFreeCountEditService.queryDoctorFreeCountEdit(docFCEVo);	
				rtJson.put("dfcId", dcFreeVo.getDfcId());
				rtJson.put("freeCount", dcFreeVo.getFreeCount());
				rtJson.put("remainFreeCount", dcFreeVo.getRemainFreeCount());
				rtJson.put("occupyFreeCount",dcFreeVo.getOccupyFreeCount());
				rtJson.put("lastUpdateTime", dcFreeVo.getLastUpdateTime());
				rtJson.put("lastChangeTime", dcFreeVo.getLastChangeTime());
				if(docFCEVo==null || docFCEVo.equals(null)){
					rtJson.put("freeCountEdit", -1);
				}else{
					rtJson.put("freeCountEdit", docFCEVo.getFreeCount());
				}
				return WsUtil.getRetVal(msg.getOutType(), 1, rtJson.toString());
			} else {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "医生数据不存在。");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String getDoctorFreeCountForApp(InterfaceMessage msg) {
		String tag = "getDoctorFreeCountForApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo = doctorFreeCountService.queryDoctorFreeCount(dcFreeVo);
			JSONObject rtJson = new JSONObject();
			if (dcFreeVo != null) {
				DoctorFreeCountEditVo docFCEVo = new DoctorFreeCountEditVo();
				docFCEVo.setDfcId(dcFreeVo.getDfcId());
				docFCEVo = doctorFreeCountEditService.queryDoctorFreeCountEdit(docFCEVo);	
				rtJson.put("dfcId", dcFreeVo.getDfcId());
				rtJson.put("freeCount", dcFreeVo.getFreeCount());
				rtJson.put("remainFreeCount", dcFreeVo.getRemainFreeCount());
				rtJson.put("occupyFreeCount",dcFreeVo.getOccupyFreeCount());
				rtJson.put("lastUpdateTime", dcFreeVo.getLastUpdateTime());
				rtJson.put("lastChangeTime", dcFreeVo.getLastChangeTime());
				if(docFCEVo==null || docFCEVo.equals(null)){
					rtJson.put("freeCountEdit", -1);
				}else{
					rtJson.put("freeCountEdit", docFCEVo.getFreeCount());
				}
				JSONObject rt= JSONObject.fromObject(ApiUtil.getJsonRt(10000, "数据读取成功"));
				JSONArray res = new JSONArray();
				res.add(rtJson);
				rt.put("Result", res);
				return rt.toString();
			} else {
				return ApiUtil.getJsonRt(-2000, "医生数据不存在。");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 用户获取免费咨询名额
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public  String  getUserFreeCount(InterfaceMessage msg) {
		String tag = "getUserFreeCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			int count = userFreeCountService.userGetFreeCount(doctorUid,userID,1);
			if(count>0){
				return WsUtil.getRetVal(msg.getOutType(), -3000, "您今天已对该医生进行过免费咨询");
			}
			UserFreeCountVo uvo = new UserFreeCountVo();
			uvo = userFreeCountService.userGetFree(doctorUid,userID);
			if(uvo != null){
				return WsUtil.getRetVal(msg.getOutType(), 1,  String.valueOf(uvo.getUfcId()));
			}
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo = doctorFreeCountService.queryDoctorFreeCount(dcFreeVo);
			Date day = DateUtil.parse(dcFreeVo.getLastChangeTime(),DateUtil.YMD_FORMAT);
			String tday =  DateUtil.dateFormat(new Date(),DateUtil.YMD_FORMAT);
			if ( (DateUtil.parse(tday,DateUtil.YMD_FORMAT)).equals(day) ){
				if (dcFreeVo.getRemainFreeCount() > 0) {
					UserFreeCountVo uFreeCount = new UserFreeCountVo();
					uFreeCount.setDfcId(dcFreeVo.getDfcId());
					uFreeCount.setBeginTime(DateUtil.dateFormat(new Date()));
					Calendar mfNow = Calendar.getInstance();
					mfNow.setTime(new Date());
					mfNow.add(Calendar.MINUTE, 10);
					Date mf = mfNow.getTime();
					uFreeCount.setExpirationTime(DateUtil.dateFormat(mf));
					Calendar edNow = Calendar.getInstance();
					edNow.setTime(new Date());
					edNow.add(Calendar.YEAR, 100);
					Date ed = edNow.getTime();
					uFreeCount.setEndTime(DateUtil.dateFormat(ed));
					uFreeCount.setUserId(userID);
					uFreeCount.setIfOver(0);
					int rtuse = userFreeCountService.insertUserFreeCount(uFreeCount);
					if(rtuse<=0){
						return WsUtil.getRetVal(msg.getOutType(), -14444, "数据插入失败。");
					}
					dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					dcFreeVo.setRemainFreeCount(dcFreeVo.getRemainFreeCount() - 1);
					dcFreeVo.setOccupyFreeCount(dcFreeVo.getOccupyFreeCount() + 1);
					int rt = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dcFreeVo);
					if (rt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444, "更新免费数据失败。");
					}
					return WsUtil.getRetVal(msg.getOutType(), 1,  String.valueOf(rtuse));
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -2000, "此医生免费咨询名额已经用完");
				}
			} else {
				if (dcFreeVo.getFreeCount() > 0) {
					UserFreeCountVo uFreeCount = new UserFreeCountVo();
					uFreeCount.setDfcId(dcFreeVo.getDfcId());
					uFreeCount.setBeginTime(DateUtil.dateFormat(new Date()));
					Calendar mfNow = Calendar.getInstance();
					mfNow.setTime(new Date());
					mfNow.add(Calendar.MINUTE, 10);
					Date mf = mfNow.getTime();
					uFreeCount.setExpirationTime(DateUtil.dateFormat(mf));
					Calendar edNow = Calendar.getInstance();
					edNow.setTime(new Date());
					edNow.add(Calendar.YEAR, 100);
					Date ed = edNow.getTime();
					uFreeCount.setEndTime(DateUtil.dateFormat(ed));
					uFreeCount.setUserId(userID);
					uFreeCount.setIfOver(0);
					int rtuse =  userFreeCountService.insertUserFreeCount(uFreeCount);// 插入数据
					if (rtuse < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444, "更新免费数据失败。");
					}
					dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					dcFreeVo.setRemainFreeCount(dcFreeVo.getFreeCount() - 1);
					dcFreeVo.setOccupyFreeCount(dcFreeVo.getOccupyFreeCount() + 1);
					int rt = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dcFreeVo);
					if (rt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444, "更新免费数据失败。");
					}
					return WsUtil.getRetVal(msg.getOutType(), 1,  String.valueOf(rtuse));
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -2000, "此医生免费咨询名额已经用完");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		
		}
	}
	public  String  getUserFreeCountForApp(InterfaceMessage msg) {
		String tag = "getUserFreeCountForApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			DoctorFreeCountVo dcFreeVo = new DoctorFreeCountVo();
			int count = userFreeCountService.userGetFreeCount(doctorUid,userID,1);
			JSONObject rtUid =new  JSONObject();
			JSONObject rtjs =JSONObject.fromObject( ApiUtil.getJsonRt(10000,  "操作成功。"));
			JSONArray jay = new JSONArray();
			if(count>0){
				return ApiUtil.getJsonRt(-3000, "您今天已对该医生进行过免费咨询");
			}
			UserFreeCountVo uvo = new UserFreeCountVo();
			uvo = userFreeCountService.userGetFree(doctorUid,userID);
			if(uvo != null){
				rtUid.put("ufcID", uvo.getUfcId());
				jay.add(rtUid);
				rtjs.put("Result", jay);
				return rtjs.toString();
			}
			dcFreeVo.setDoctorUid(doctorUid);
			dcFreeVo = doctorFreeCountService.queryDoctorFreeCount(dcFreeVo);
			if(dcFreeVo ==null){
				return ApiUtil.getJsonRt(-14444, "医生没有免费咨询名额");
			}
			Date day = DateUtil.parse(dcFreeVo.getLastChangeTime(),DateUtil.YMD_FORMAT);
			String tday =  DateUtil.dateFormat(new Date(),DateUtil.YMD_FORMAT);
			if ( (DateUtil.parse(tday,DateUtil.YMD_FORMAT)).equals(day) ){
				if (dcFreeVo.getRemainFreeCount() > 0) {
					UserFreeCountVo uFreeCount = new UserFreeCountVo();
					uFreeCount.setDfcId(dcFreeVo.getDfcId());
					uFreeCount.setBeginTime(DateUtil.dateFormat(new Date()));
					Calendar mfNow = Calendar.getInstance();
					mfNow.setTime(new Date());
					mfNow.add(Calendar.MINUTE, 10);
					Date mf = mfNow.getTime();
					uFreeCount.setExpirationTime(DateUtil.dateFormat(mf));
					Calendar edNow = Calendar.getInstance();
					edNow.setTime(new Date());
					edNow.add(Calendar.YEAR, 100);
					Date ed = edNow.getTime();
					uFreeCount.setEndTime(DateUtil.dateFormat(ed));
					uFreeCount.setUserId(userID);
					uFreeCount.setIfOver(0);
					int rtuse = userFreeCountService.insertUserFreeCount(uFreeCount);
					if(rtuse<=0){
						return ApiUtil.getJsonRt(-14444, "数据插入失败。");
					}
					dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					dcFreeVo.setRemainFreeCount(dcFreeVo.getRemainFreeCount() - 1);
					dcFreeVo.setOccupyFreeCount(dcFreeVo.getOccupyFreeCount() + 1);
					int rt = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dcFreeVo);
					if (rt < 0) {
						return ApiUtil.getJsonRt(-14444, "更新免费数据失败。");
					}
					rtUid.put("ufcID", rtuse);
					jay.add(rtUid);
					rtjs.put("Result", jay);
					
					return rtjs.toString();
				} else {
					return ApiUtil.getJsonRt(-2000, "此医生免费咨询名额已经用完");
				}
			} else {
				if (dcFreeVo.getFreeCount() > 0) {
					UserFreeCountVo uFreeCount = new UserFreeCountVo();
					uFreeCount.setDfcId(dcFreeVo.getDfcId());
					uFreeCount.setBeginTime(DateUtil.dateFormat(new Date()));
					Calendar mfNow = Calendar.getInstance();
					mfNow.setTime(new Date());
					mfNow.add(Calendar.MINUTE, 10);
					Date mf = mfNow.getTime();
					uFreeCount.setExpirationTime(DateUtil.dateFormat(mf));
					Calendar edNow = Calendar.getInstance();
					edNow.setTime(new Date());
					edNow.add(Calendar.YEAR, 100);
					Date ed = edNow.getTime();
					uFreeCount.setEndTime(DateUtil.dateFormat(ed));
					uFreeCount.setUserId(userID);
					uFreeCount.setIfOver(0);
					int rtuse =  userFreeCountService.insertUserFreeCount(uFreeCount);// 插入数据
					if (rtuse < 0) {
						return ApiUtil.getJsonRt(-14444, "更新免费数据失败。");
					}
					dcFreeVo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					dcFreeVo.setRemainFreeCount(dcFreeVo.getFreeCount() - 1);
					dcFreeVo.setOccupyFreeCount(dcFreeVo.getOccupyFreeCount() + 1);
					int rt = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dcFreeVo);
					if (rt < 0) {
						return ApiUtil.getJsonRt(-14444, "更新免费数据失败。");
					}
					rtUid.put("ufcID", rtuse);
					jay.add(rtUid);
					rtjs.put("Result", jay);
					return rtjs.toString();
				} else {
					return ApiUtil.getJsonRt(-2000, "此医生免费咨询名额已经用完");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		
		}
	}
	/**
	 * 完成免费指定咨询操作
	 * 
	 * @param
	 * @param
	 * @return
	 */
	
	public String overUserFreeCount(InterfaceMessage msg){
		String tag="overUserFreeCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int ufcid = json.get("ufcid") == null ? null : json
					.getInt("ufcid");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			UserFreeCountVo uFreeCount = new UserFreeCountVo();
			uFreeCount.setUserId(userID);
			uFreeCount.setUfcId(ufcid);
			uFreeCount.setIfOver(0);
			uFreeCount =	userFreeCountService.queryUserFreeCount(uFreeCount);
			if(uFreeCount==null){
				return WsUtil.getRetVal(msg.getOutType(), -2000, "该抢答已经过期.");
			}else{
				uFreeCount.setIfOver(1);
				uFreeCount.setEndTime(DateUtil.dateFormat(new Date()));
				int rt = userFreeCountService.updateUserFree(uFreeCount);
				if(rt<0){
					return WsUtil.getRetVal(msg.getOutType(), -14444, "更新抢答数据失败.");
				}
				UserFreeCountVo ufc = new UserFreeCountVo();
				ufc.setUfcId(ufcid);
				ufc = userFreeCountService.queryUserFreeCount(ufc);
				DoctorFreeCountVo dvo = new DoctorFreeCountVo();
				dvo.setDfcId(ufc.getDfcId());
				dvo = doctorFreeCountService.queryDoctorFreeCount(dvo);
				dvo.setOccupyFreeCount(dvo.getOccupyFreeCount()-1);
				dvo.setLastChangeTime(DateUtil.dateFormat(new Date()));
				int rtd = doctorFreeCountService.updateDoctorFreeCountForUpFreeCount(dvo);
				if(rtd<0){
					return WsUtil.getRetVal(msg.getOutType(), -14444, "更新抢答数据失败.");
				}
			}
			return WsUtil.getRetVal(msg.getOutType(), 1, "更新抢答数据成功");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public  String  getFreeDoctorList(InterfaceMessage msg) {
		String tag = "getFreeDoctorList";
		try {
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			int start =  json.get("start") == null ? null : json
					.getInt("start");
			int pageSize =   json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			JSONObject rtJs = new JSONObject();
			List<AccCollectionReturnBean> accList=	 accountService.queryAccCollectionList(3, userID);
			if(accList.size()==0){
				return WsUtil.getRetVal(msg.getOutType(), -14444, "无医生数据。");
			}
			StringBuffer docIDs = new StringBuffer() ;
			for(AccCollectionReturnBean ac : accList){
				docIDs.append(ac.getCollectionId() +",") ;
			}
			
				docIDs.deleteCharAt(docIDs.length() - 1);
			
			List<DoctorFreeCountVo> list = doctorFreeCountService.queryDoctorFreeCountList(docIDs.toString(),userID);
			if(list.size()==0){
				return WsUtil.getRetVal(msg.getOutType(), -14444, "无医生数据。");
			}
			StringBuffer ids = new StringBuffer() ;
			for(DoctorFreeCountVo dv : list){
				ids.append(dv.getDoctorUid() + ",");
			}
			ids.deleteCharAt(ids.length() - 1);
			JSONObject dcUids =new JSONObject();
			dcUids.put("doctorUids", ids.toString());
			dcUids.put("displayStatus", 1);
			dcUids.put("main", 1);
			ServiceResult<String>  rtDcs =doctorInfoApi.queryComplexDoctorList(dcUids.toString(), start, pageSize);
			ServiceResult<Integer>  rtCount = doctorInfoApi.queryDoctorCount(dcUids.toString());
			if(rtCount.getCode()<0){
				return WsUtil.getRetVal(msg.getOutType(), -2000, "获取医生数据失败。");
			}
			if(rtDcs.getCode()>0){
				JSONArray docs=JSONArray.fromObject(rtDcs.getResult());
				rtJs.put("doctors", docs);
				rtJs.put("count", rtCount.getResult());
				
			}else{
				return WsUtil.getRetVal(msg.getOutType(), -2000, "获取医生数据失败。");
			}
			return WsUtil.getRetVal(msg.getOutType(), 1, rtJs.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(tag, new LogBody().set("方法", tag).set("参数", msg.getParam()).set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	
	
}
