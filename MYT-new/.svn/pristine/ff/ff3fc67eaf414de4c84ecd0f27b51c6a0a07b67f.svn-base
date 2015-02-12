package com.yihu.api.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;

 
import com.boss.sdk.OperatorInfo;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import java.sql.Timestamp;

import com.yihu.account.api.AccMembershipcardBean;
import com.yihu.account.api.IAccountService;
import com.yihu.api.api.MytDoctorOperconfidApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.mgr.BusinessManager;
import com.yihu.myt.service.OperconfidService;
import com.yihu.myt.service.service.IBookEnrolService;
import com.yihu.myt.service.service.IConsEnrolService;
import com.yihu.myt.service.service.IConsphoneService;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.IDoctorInfoService;
import com.yihu.myt.service.service.IOperconfigService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.ISelfHelpService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.BookEnrolVo;
import com.yihu.myt.vo.BossAccountBean;
import com.yihu.myt.vo.ConsEnrolVo;
import com.yihu.myt.vo.DoctorInfoVo;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.OperconfigVo;
import com.yihu.myt.vo.ConsphoneVo;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.myt.vo.SelfHelpVo;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

public class MytDoctorOperconfidApiImpl implements MytDoctorOperconfidApi {

	private static IOperconfigService operconfigService = Ioc
			.get(IOperconfigService.class); // 医生配置信息服务接口
	
	private static IOperconfidService operService = Ioc
			.get(IOperconfidService.class); // 医生配置信息服务接口(旧)
	
	private static ISelfHelpService selfHelpService = Ioc
			.get(ISelfHelpService.class); // 医生配置信息服务接口

	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class); // 医生排班服务接口
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // 医生停诊服务接口

	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class); // 咨询流水服务接口
	private static IBookEnrolService bookEnrol = Ioc
			.get(IBookEnrolService.class); // 咨询流水服务接口
	
	private static IConsphoneService consphoneService = Ioc
			.get(IConsphoneService.class); // 医生电话接口
	private static IConsEnrolService consEnrolService = Ioc
			.get(IConsEnrolService.class); // 医生电话接口
	private static IConswaterService conswaterService = Ioc
			.get(IConswaterService.class);
	private static IConsumerOrdersService consumerOrdersService = Ioc
			.get(IConsumerOrdersService.class);
	private static IDoctorInfoService doctorInfoService = Ioc
			.get(IDoctorInfoService.class);
	
	private static IQuesMainService quesMainService = Ioc
			.get(IQuesMainService.class);
	private static IBookEnrolService bookEnrolService = Ioc
			.get(IBookEnrolService.class);
	private static IPostService postService = Ioc.get(IPostService.class);
	public static void main(String[] args) throws Exception {

		JSONObject obj = new JSONObject();
		/*
		 * obj.put("doctorUid", 711136036); obj.put("basedoctorid", 711136036);
		 * obj.put("consPhones", "13960841923,13960841923,18906910086");
		 * obj.put("operatorId", 1111111); obj.put("operatorName", 1111111);
		 * obj.put("doctorlevel", 0); obj.put("balanceType", 1);
		 * obj.put("isPayDoctor", 1); obj.put("payType", 1); obj.put("sendType",
		 * 1); obj.put("remark", "测试一下");
		 */
		// obj.put("doctorUid", 710003065);
		/*
		 * obj.put("userID", "10854906"); obj.put("userName","");
		 * obj.put("start", "0"); obj.put("pageSize", "10");
		 */
		/*
		 * obj.put("serviceId", 1); obj.put("typeId", 3); obj.put("price", 0);
		 */

		// {"":"","":"","":"118","":"","":"1","":"0","":"20","":"","":"0","":"2"}
		 /*obj.put("provinceId", "13");
		obj.put("bigDeptSn", "");
		obj.put("cityId", "118");
		obj.put("diseaseId", "");
		obj.put("displayStatus", "1");
		obj.put("startRow", "0");
		obj.put("pageSize", "20");
		obj.put("columns",
				"doctorUid,yiHuDoctorName,hospitalId,yiHuHosName,deptName,doctorSex,skill,intro,lczcName,photoPrefix,photoUri,phonePirce,feeTemplateId,feeInfo");
		obj.put("hospitalId", "0");
		obj.put("serviceStatusSearch", "2");*/
		/*obj.put("doctorUids", "4792,4811,4812");
		obj.put("startRow", "0");
		obj.put("pageSize", "20");
		obj.put("columns",
				"photoPrefix,doctorUid,");*/
		/*obj.put("doctorUid", "710027014"); 
		obj.put("mobile", "18059183600"); */
		
		//obj.put("doctorUid", "710002586"); 
		/*obj.put("userID", "12620852"); 
		obj.put("start", "0"); 
		obj.put("pageSize", "10"); */
		/*obj.put("beginTime", "08:00"); 
		obj.put("endTime", "08:01"); 
		obj.put("operatorName", "肖炉芳"); 
		obj.put("operatorID", "710003065"); 
		
		obj.put("weekids", "1"); 
		obj.put("arraworkid", "27613");*/
		
	/*	obj.put("start", "0"); 
		obj.put("pageSize", "10"); */
	//	String str ="{'doctorUid':'710028071','beginTime':'08:00','weekids':'5','operatorID':'710028071','endTime':'09:00','operatorName':'刘文强'}";
		String addMYTArrange = "{'beginTime':'08:00','doctorUid':'710047856','operatorID':'710047856','weekids':'0','operatorName':'李四','endTime':'08:30'}";
		//String str = "{'doctorUid':18676,'basedoctorid':'25313','consPhones':'15812500311','isPayDoctor':'1','payType':'1','sendType':'1','balanceType':'1','operatorId':5351,'operatorName':'翁斯波','doctorlevel':6,'remark':''}";
		String getMytBookEnrols = "{'cardNumber':'671536646','pageIndex':'0','pageSize':'5'}";
	  //cardNumber  custName custPhone dateWeek startTime endTime remark doctorUid operatorID operatorName
		String saveMytBookEnrol = "{'operatorID':900019,'cardNumber':'671044440','endTime':'9:00','doctorUid':'710040076','custName':'18142883902','remark':'','startTime':'08:00','custPhone':'18142883902','operatorName':'39健康网','dateWeek':'2014-08-13'}";
		String getMytWorkList = "{'doctorUid':'710040076'}";
		String getDocUserListMyt = "{'doctorUid':'710040076'}";
		String upBookEnrol ="{'bookEnrolID':'69894','state':'1','revert':'10'}";
		MytDoctorOperconfidApiImpl api = new MytDoctorOperconfidApiImpl();
		InterfaceMessage msg = new InterfaceMessage();
		msg.setParam(upBookEnrol);
	/*	String rt = api.saveDoctorMytPhones(msg);
		System.out.println(rt);*/
		
		
		 //ServiceResult<String> rt = api.saveDoctorMytPhones(obj.toString());
		 String rt = api.upBookEnrol(msg);
		 //System.out.println(rt); System.out.print(rt.getCode());
		 System.out.print(rt); 
		 
	}

	public ServiceResult<String> getDoctorMytPhones(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "入参有误");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			ConsphoneVo cpv = new ConsphoneVo();
			OperconfigVo op = new OperconfigVo();
			op.setOPERCONFID(doctorUid);
			int cont = operconfigService.queryOperconfigCountByCondition(op);
			if (cont <= 0) {
				result.setResult("");
				result.setCode(1);
				result.setMessage("无医生数据");
				return result;
			}
			op = operconfigService.queryOperconfid(op);
			cpv.setDoctorid(op.getDOCTORID());
			List<ConsphoneVo> cPhoness = consphoneService
					.queryConsphoneListByCondition(cpv);
			if (cPhoness.size() <= 0) {
				result.setResult("");
				result.setCode(1);
				result.setMessage("无医生电话数据");
				return result;
			}
			JSONObject cpJs = new JSONObject();
			StringBuffer rePhone = new StringBuffer();
			for (ConsphoneVo cp : cPhoness) {
				rePhone.append(cp.getConsphone() + ",");
			}
			rePhone.deleteCharAt(rePhone.length() - 1);
			cpJs.put("doctorUid", op.getOPERCONFID());
			cpJs.put("basedoctorid", op.getDOCTORID());
			cpJs.put("consPhones", rePhone.toString());
			cpJs.put("doctorlevel", op.getDOCTORLEVEL());
			cpJs.put("balanceType", op.getBALANCETYPE());
			cpJs.put("isPayDoctor", op.getISPAYDOCTOR());
			cpJs.put("payType", op.getPAYTYPE());
			cpJs.put("sendType", op.getSENDTYPE());
			cpJs.put("remark", op.getREMARK());
			result.setCode(1);
			result.setMessage("数据读取成功");
			result.setResult(cpJs.toString());
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
			return result;
		}
	}

	public ServiceResult<String> saveDoctorMytPhones(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "入参有误");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int basedoctorid = json.get("basedoctorid") == null ? null : json
					.getInt("basedoctorid");
			String consPhones = json.get("consPhones") == null ? null : json
					.getString("consPhones");
			String operatorId = json.get("operatorId") == null ? null : json
					.getString("operatorId");
			String operatorName = json.get("operatorName") == null ? null
					: json.getString("operatorName");
			int doctorlevel = json.get("doctorlevel") == null ? null : json
					.getInt("doctorlevel");
			int isPayDoctor = json.get("isPayDoctor") == null ? null : json
					.getInt("isPayDoctor");
			String payType = json.get("payType") == null ? null : json
					.getString("payType");
			String balanceType = json.get("balanceType") == null ? null : json
					.getString("balanceType");
			String sendType = json.get("sendType") == null ? null : json
					.getString("sendType");
			String remark = json.get("remark") == null ? null : json
					.getString("remark");

			OperconfigVo op = new OperconfigVo();
			op.setOPERCONFID(doctorUid);
			int cont = operconfigService.queryOperconfigCountByCondition(op);
			if (cont > 0) {// 有电话号码在库情况
				op.setOPERCONFID(doctorUid);
				op.setOPERATORID(operatorId);
				op.setDOCTORID(basedoctorid + "");
				op.setOPERATORNAME(operatorName);
				op.setOPERTIME(DateUtil.dateFormat(new Date()));
				op.setDOCTORLEVEL(doctorlevel);
				op.setISPAYDOCTOR(isPayDoctor);
				op.setPAYTYPE(payType);
				op.setBALANCETYPE(balanceType);
				op.setSENDTYPE(sendType);
				op.setREMARK(remark);
				op.setYiHustatus(1);
				op.setYiHuCommend(1);

				int rt = operconfigService.updatequeryOperconfid(op);
				if (rt < 0) {
					result.setResult("");
					result.setCode(-14444);
					result.setMessage("更新失败");
					return result;
				}
				String[] phones = consPhones.split(",");
				ConsphoneVo cpv = new ConsphoneVo();
				cpv.setDoctorid(basedoctorid + "");
				List<ConsphoneVo> cPhoness = consphoneService
						.queryConsphoneListByCondition(cpv);
				int tmp = 0;
				for (ConsphoneVo cpVo : cPhoness) {
					tmp = 0;
					for (String phone : phones) {
						if (cpVo.getConsphone().equals(phone)) {
							tmp = 1;
						}
					}
					if (tmp != 1) {
						// 如果没有在数组里头做逻辑删除该电话号码
						cpVo.setState(0);
						int rtcon = consphoneService.updateConsphone(cpVo);
						if (rtcon < 0) {
							result.setResult("");
							result.setCode(-14444);
							result.setMessage("删除医电话号码失败");
							return result;
						}
					}
				}
				ConsphoneVo inCVo;
				cpv.setDoctorid(basedoctorid + "");
				for (String phone : phones) {
					tmp = 0;
					for (ConsphoneVo cpVo : cPhoness) {
						if (phone.equals(cpVo.getConsphone())) {
							tmp = 1;
						}
					}
					if (tmp != 1) {
						inCVo = new ConsphoneVo();
						inCVo.setDoctorid(basedoctorid + "");
						inCVo.setState(1);
						inCVo.setOpertime(DateUtil.dateFormat(new Date()));
						inCVo.setConsphone(phone);
						int rtco = consphoneService.insertConsphone(inCVo);
						if (rtco < 0) {
							result.setResult("");
							result.setCode(-14444);
							result.setMessage("新增医电话号码失败");
							return result;
						}
					}
				}
			} else if (cont == 0) {// 没有账号，没有电话号码在库情况
				op.setOPERCONFID(doctorUid);
				op.setDOCTORID(basedoctorid + "");
				op.setOPERATORID(operatorId);
				op.setOPERATORNAME(operatorName);
				op.setOPERTIME(DateUtil.dateFormat(new Date()));
				op.setDOCTORLEVEL(doctorlevel);
				op.setISPAYDOCTOR(isPayDoctor);
				op.setPAYTYPE(payType);
				op.setBALANCETYPE(balanceType);
				op.setSENDTYPE(sendType);
				op.setREMARK(remark);
				op.setYiHustatus(1);
				op.setYiHuCommend(1);
				op.setSTATE("1");

				int oprt = operconfigService.insertOperconfig(op);
				if (oprt < 0) {
					result.setResult("");
					result.setCode(-14444);
					result.setMessage("新增医生账号失败");
					return result;
				}

				String[] phones = consPhones.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < phones.length; i++) {
					if (!list.contains(phones[i])) {
						list.add(phones[i]);
					}
				}
				String[] newStrPhones = list.toArray(new String[1]);
				ConsphoneVo inCVo;
				for (String phone : newStrPhones) {
					inCVo = new ConsphoneVo();
					inCVo.setDoctorid(doctorUid + "");
					inCVo.setState(1);
					inCVo.setOpertime(DateUtil.dateFormat(new Date()));
					inCVo.setConsphone(phone);
					int rtco = consphoneService.insertConsphone(inCVo);
					if (rtco < 0) {
						result.setResult("");
						result.setCode(-14444);
						result.setMessage("获取数据失败");
						return result;
					}
				}
			} else {
				result.setResult("");
				result.setCode(-14444);
				result.setMessage("新增医电话号码失败");
				return result;
			}
			result.setResult("");
			result.setCode(1);
			result.setMessage("操作成功");
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
			return result;
		}
	}
	
	public String saveDcPhones(InterfaceMessage msg) {
		String tag = "saveDcPhones";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String consPhones = json.get("consPhones") == null ? null : json
					.getString("consPhones");
			int basedoctorid = json.get("basedoctorid") == null ? null : json
					.getInt("basedoctorid");
			String[] phones = consPhones.split(",");
			ConsphoneVo cpv = new ConsphoneVo();
			cpv.setDoctorid(basedoctorid + "");
			List<ConsphoneVo> cPhoness = consphoneService
					.queryConsphoneListByCondition(cpv);
			int tmp = 0;
			for (ConsphoneVo cpVo : cPhoness) {
				tmp = 0;
				for (String phone : phones) {
					if (cpVo.getConsphone().equals(phone)) {
						tmp = 1;
					}
				}
				if (tmp != 1) {
					// 如果没有在数组里头做逻辑删除该电话号码
					cpVo.setState(0);
					int rtcon = consphoneService.updateConsphone(cpVo);
					if (rtcon < 0) {
						return ApiUtil.getJsonRt(-14444, "删除医电话号码失败。"); 
					}
				}
			}
			ConsphoneVo inCVo;
			cpv.setDoctorid(basedoctorid + "");
			for (String phone : phones) {
				tmp = 0;
				for (ConsphoneVo cpVo : cPhoness) {
					if (phone.equals(cpVo.getConsphone())) {
						tmp = 1;
					}
				}
				if (tmp != 1) {
					inCVo = new ConsphoneVo();
					inCVo.setDoctorid(basedoctorid + "");
					inCVo.setState(1);
					inCVo.setOpertime(DateUtil.dateFormat(new Date()));
					inCVo.setConsphone(phone);
					int rtco = consphoneService.insertConsphone(inCVo);
					if (rtco < 0) {
						return ApiUtil.getJsonRt(-14444, "新增医电话号码失败。"); 
					}
				}
			}
			return ApiUtil.getJsonRt(10000, "操作成功。");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
			// TODO Auto-generated catch block

		}
	}
	
	public String saveDoctorMytPhonesV2(InterfaceMessage msg) {
		String tag = "saveDoctorMytPhonesV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int basedoctorid = json.get("basedoctorid") == null ? null : json
					.getInt("basedoctorid");
			String consPhones = json.get("consPhones") == null ? null : json
					.getString("consPhones");
			String operatorId = json.get("operatorId") == null ? null : json
					.getString("operatorId");
			String operatorName = json.get("operatorName") == null ? null
					: json.getString("operatorName");
			int doctorlevel = json.get("doctorlevel") == null ? null : json
					.getInt("doctorlevel");
			int isPayDoctor = json.get("isPayDoctor") == null ? null : json
					.getInt("isPayDoctor");
			String payType = json.get("payType") == null ? null : json
					.getString("payType");
			String balanceType = json.get("balanceType") == null ? null : json
					.getString("balanceType");
			String sendType = json.get("sendType") == null ? null : json
					.getString("sendType");
			String remark = json.get("remark") == null ? null : json
					.getString("remark");
			OperconfigVo op = new OperconfigVo();
			op.setOPERCONFID(doctorUid);
			int cont = operconfigService.queryOperconfigCountByCondition(op);
			if (cont > 0) {// 有电话号码在库情况
				op.setOPERCONFID(doctorUid);
				op.setOPERATORID(operatorId);
				op.setDOCTORID(basedoctorid + "");
				op.setOPERATORNAME(operatorName);
				op.setOPERTIME(DateUtil.dateFormat(new Date()));
				op.setDOCTORLEVEL(doctorlevel);
				op.setISPAYDOCTOR(isPayDoctor);
				op.setPAYTYPE(payType);
				op.setBALANCETYPE(balanceType);
				op.setSENDTYPE(sendType);
				op.setREMARK(remark);
				op.setYiHustatus(1);
				op.setYiHuCommend(1);
				int rt = operconfigService.updatequeryOperconfid(op);
				if (rt < 0) {
					return ApiUtil.getJsonRt(-14444, "更新失败。"); 
				}
				String[] phones = consPhones.split(",");
				ConsphoneVo cpv = new ConsphoneVo();
				cpv.setDoctorid(basedoctorid + "");
				List<ConsphoneVo> cPhoness = consphoneService
						.queryConsphoneListByCondition(cpv);
				int tmp = 0;
				for (ConsphoneVo cpVo : cPhoness) {
					tmp = 0;
					for (String phone : phones) {
						if (cpVo.getConsphone().equals(phone)) {
							tmp = 1;
						}
					}
					if (tmp != 1) {
						// 如果没有在数组里头做逻辑删除该电话号码
						cpVo.setState(0);
						int rtcon = consphoneService.updateConsphone(cpVo);
						if (rtcon < 0) {
							return ApiUtil.getJsonRt(-14444, "删除医电话号码失败。"); 
						}
					}
				}
				ConsphoneVo inCVo;
				cpv.setDoctorid(basedoctorid + "");
				for (String phone : phones) {
					tmp = 0;
					for (ConsphoneVo cpVo : cPhoness) {
						if (phone.equals(cpVo.getConsphone())) {
							tmp = 1;
						}
					}
					if (tmp != 1) {
						inCVo = new ConsphoneVo();
						inCVo.setDoctorid(basedoctorid + "");
						inCVo.setState(1);
						inCVo.setOpertime(DateUtil.dateFormat(new Date()));
						inCVo.setConsphone(phone);
						int rtco = consphoneService.insertConsphone(inCVo);
						if (rtco < 0) {
							return ApiUtil.getJsonRt(-14444, "新增医电话号码失败。"); 
						}
					}
				}
			} else if (cont == 0) {// 没有账号，没有电话号码在库情况
				op.setOPERCONFID(doctorUid);
				op.setDOCTORID(basedoctorid + "");
				op.setOPERATORID(operatorId);
				op.setOPERATORNAME(operatorName);
				op.setOPERTIME(DateUtil.dateFormat(new Date()));
				op.setDOCTORLEVEL(doctorlevel);
				op.setISPAYDOCTOR(isPayDoctor);
				op.setPAYTYPE(payType);
				op.setBALANCETYPE(balanceType);
				op.setSENDTYPE(sendType);
				op.setREMARK(remark);
				op.setYiHustatus(1);
				op.setYiHuCommend(1);
				op.setSTATE("1");
				int oprt = operconfigService.insertOperconfig(op);
				if (oprt < 0) {
					return ApiUtil.getJsonRt(-14444, "新增医生账号失败。"); 
				}
				String[] phones = consPhones.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < phones.length; i++) {
					if (!list.contains(phones[i])) {
						list.add(phones[i]);
					}
				}
				String[] newStrPhones = list.toArray(new String[1]);
				ConsphoneVo inCVo;
				for (String phone : newStrPhones) {
					inCVo = new ConsphoneVo();
					inCVo.setDoctorid(doctorUid + "");
					inCVo.setState(1);
					inCVo.setOpertime(DateUtil.dateFormat(new Date()));
					inCVo.setConsphone(phone);
					int rtco = consphoneService.insertConsphone(inCVo);
					if (rtco < 0) {
						return ApiUtil.getJsonRt(-14444, "新增医电话号码失败。"); 
					}
				}
			} else {
				return ApiUtil.getJsonRt(-14444, "新增医电话号码失败。"); 
			}
			return ApiUtil.getJsonRt(10000, "操作成功。");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
			// TODO Auto-generated catch block
			
		}
	}
//
	public String addMYTArrangeForApp(InterfaceMessage msg) {
		String tag = "addMYTArrange";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			String weekids = json.get("weekids") == null ? null : json
					.getString("weekids");
			String beginTime = json.get("beginTime") == null ? null : json
					.getString("beginTime");
			String endTime = json.get("endTime") == null ? null : json
					.getString("endTime");
			String operatorID = json.get("operatorID") == null ? null : json
					.getString("operatorID");
			String operatorName = json.get("operatorName") == null ? null : json
					.getString("operatorName");			
			MytArraworkBean arrawork = new MytArraworkBean();
			com.common.json.JSONObject obj = operService.getConsphoneByDoctor(doctorUid);
			com.common.json.JSONArray phones = obj.getJSONArray("result");
			if(phones.length()<=0 ){
				return ApiUtil.getJsonRt(-2000, "该医生无电话数据。"); 
			}
			arrawork.setOperconfid(doctorUid);
			arrawork.setWeekid(weekids);
			arrawork.setStarttime(beginTime);
			arrawork.setEndtime(endTime);
			arrawork.setOperatorid(operatorID);
			arrawork.setOperatorname(operatorName);
			arrawork.setConsphone(phones.getJSONObject(0).getString("consphone"));
			arrawork.setState(1);
			Date date = new Date();
	        Timestamp ts = new Timestamp(date.getTime());
			arrawork.setOpertime( ts);
			String rt =  arraworkService.addMytArraworkForApp(arrawork);
			JSONObject rtjs = JSONObject.fromObject(rt);
			return rtjs.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String addMYTArrange(InterfaceMessage msg) {
		String tag = "addMYTArrange";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			String weekids = json.get("weekids") == null ? null : json
					.getString("weekids");
			String beginTime = json.get("beginTime") == null ? null : json
					.getString("beginTime");
			String endTime = json.get("endTime") == null ? null : json
					.getString("endTime");
			String operatorID = json.get("operatorID") == null ? null : json
					.getString("operatorID");
			String operatorName = json.get("operatorName") == null ? null : json
					.getString("operatorName");			
			MytArraworkBean arrawork = new MytArraworkBean();
			com.common.json.JSONObject obj = operService.getConsphoneByDoctor(doctorUid);
			com.common.json.JSONArray phones = obj.getJSONArray("result");
			if(phones.length()<=0 ){
				return ApiUtil.getJsonRt(-2000, "该医生无电话数据。"); 
			}
			arrawork.setOperconfid(doctorUid);
			arrawork.setWeekid(weekids);
			arrawork.setStarttime(beginTime);
			arrawork.setEndtime(endTime);
			arrawork.setOperatorid(operatorID);
			arrawork.setOperatorname(operatorName);
			arrawork.setConsphone(phones.getJSONObject(0).getString("consphone"));
			arrawork.setState(1);
			Date date = new Date();
	        Timestamp ts = new Timestamp(date.getTime());
			arrawork.setOpertime( ts);
			
			ReturnValue rt =  arraworkService.addMytArrawork(arrawork);
			if(rt.getCode() <= 0){
				return ApiUtil.getJsonRt(-2000, "新增排班失败。");
			}
			JSONObject rtJs =  JSONObject.fromObject(ApiUtil.getJsonRt(10000, "新增成功。"));
			return rtJs.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	
	public String modifyMYTArrange(InterfaceMessage msg) {
		String tag = "modifyMYTArrange";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int arraworkid = json.get("arraworkid") == null ? null : json
					.getInt("arraworkid");
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			String weekids = json.get("weekids") == null ? null : json
					.getString("weekids");
			String beginTime = json.get("beginTime") == null ? null : json
					.getString("beginTime");
			String endTime = json.get("endTime") == null ? null : json
					.getString("endTime");
			String operatorID = json.get("operatorID") == null ? null : json
					.getString("operatorID");
			String operatorName = json.get("operatorName") == null ? null : json
					.getString("operatorName");			
			MytArraworkBean arrawork = new MytArraworkBean();
			com.common.json.JSONObject obj = operService.getConsphoneByDoctor(doctorUid);
			com.common.json.JSONArray phones = obj.getJSONArray("result");
			if(phones.length()<=0 ){
				return ApiUtil.getJsonRt(-2000, "该医生无电话数据。"); 
			}
			arrawork.setArraworkid(arraworkid);
			arrawork.setOperconfid(doctorUid);
			arrawork.setWeekid(weekids);
			arrawork.setStarttime(beginTime);
			arrawork.setEndtime(endTime);
			arrawork.setOperatorid(operatorID);
			arrawork.setOperatorname(operatorName);
			arrawork.setConsphone(phones.getJSONObject(0).getString("consphone"));
			arrawork.setState(1);
			Date date = new Date();
	        Timestamp ts = new Timestamp(date.getTime());
			arrawork.setOpertime( ts);
			OperconfigVo opvo = new OperconfigVo();
			opvo.setOPERCONFID(doctorUid);
			opvo = operconfigService.queryOperconfid(opvo);
			if(opvo==null){
				return ApiUtil.getJsonRt(-2000, "获取医生数据失败。");
			}
			arrawork.setDoctorid(Integer.valueOf(opvo.getDOCTORID()));
			ReturnValue rt =  arraworkService.updateMytArrawork(arrawork);
			if(rt.getCode() <= 0){
				return ApiUtil.getJsonRt(-2000, "修改排班失败。");
			}
			JSONObject rtJs =  JSONObject.fromObject(ApiUtil.getJsonRt(10000, "修改成功。"));
			return rtJs.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	public String deleteMYTArrange(InterfaceMessage msg) {
		String tag = "deleteMYTArrange";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int arraworkid = json.get("arraworkid") == null ? null : json
					.getInt("arraworkid");
			String operatorID = json.get("operatorID") == null ? null : json
					.getString("operatorID");
			String operatorName = json.get("operatorName") == null ? null : json
					.getString("operatorName");
			OperatorInfo  operInfo = new OperatorInfo();
			operInfo.setOperatorID(Integer.valueOf(operatorID));
			operInfo.setOperatorName(operatorName);
			ReturnValue rt =  arraworkService.deleteMytArrawork(arraworkid, operInfo);
			if(rt.getCode() <= 0){
				return ApiUtil.getJsonRt(-2000, "排班删除失败。");
			}
			JSONObject rtJs =  JSONObject.fromObject(ApiUtil.getJsonRt(10000, "删除成功。"));
			return rtJs.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	/**
	 * 获取医生用户电话咨询列表
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getDocUserListMyt(InterfaceMessage msg) {
		String tag = "getDocUserListMyt";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			String userID = json.get("userID") == null ? null : json
					.getString("userID");
			int start = json.get("start") == null ? null : json.getInt("start");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			MytConswaterBean cons = new MytConswaterBean();
			cons.setOperconfid(doctorUid);
			cons.setAccountsn(userID);
			ServiceResult<List<MytConswaterBean>> rt = conswaterService
					.queryMytConsListForReport(cons, start, pageSize);
			int count = conswaterService.queryMytConsListCountForReport(cons);
			JSONArray rtjs = new JSONArray();
			JSONObject retJson = new JSONObject();
			if (rt.getCode() > 0) {
				JSONObject rtJson;
				for (MytConswaterBean con : rt.getResult()) {
					rtJson = new JSONObject();
					rtJson.put("startTime",
							DateUtil.dateFormat(con.getStartdatetime()));
					rtJson.put("endTime",
							DateUtil.dateFormat(con.getEnddatetime()));
					rtJson.put("consMin", con.getConsmin());
					rtjs.add(rtJson);
				}
			} else {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "未获取数据。");
			}
			retJson.put("list", rtjs);
			retJson.put("count", count);
			retJson.put("start", start);
			retJson.put("pageSize", pageSize);
			JSONObject rtjon = new JSONObject();
			rtjon.put("Param", retJson.toString());
			rtjon.put("Code", "10000");
			rtjon.put("Message", "成功");
			return rtjon.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, rtjs.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 获取医生用户电话咨询列表
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getDocUserListMytForUser(InterfaceMessage msg) {
		String tag = "getDocUserListMytForUser";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String userID = json.get("userID") == null ? null : json
					.getString("userID");
			int start = json.get("start") == null ? null : json.getInt("start");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			MytConswaterBean cons = new MytConswaterBean();
			cons.setAccountsn(userID);
			ServiceResult<List<MytConswaterBean>> rt = conswaterService
					.queryMytConsListForReport(cons, start, pageSize);
			int count = conswaterService.queryMytConsListCountForReport(cons);
			JSONArray rtjs = new JSONArray();
			JSONObject retJson = new JSONObject();
			if (rt.getCode() > 0) {
				JSONObject rtJson;
				for (MytConswaterBean con : rt.getResult()) {
					rtJson = new JSONObject();
					rtJson.put("startTime",
							DateUtil.dateFormat(con.getStartdatetime()));
					rtJson.put("endTime",
							DateUtil.dateFormat(con.getEnddatetime()));
					rtJson.put("doctorName", con.getDoctorname());
					rtJson.put("hosDeptName", con.getHosdeptname());
					rtJson.put("flag", con.getFlag());
					rtJson.put("cardID", con.getCardid());
					rtjs.add(rtJson);
				}
			} else {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "未获取数据。");
			}
			retJson.put("list", rtjs);
			retJson.put("count", count);
			retJson.put("start", start);
			retJson.put("pageSize", pageSize);
			JSONObject rtjon = new JSONObject();
			rtjon.put("Param", retJson.toString());
			rtjon.put("Code", "1");
			rtjon.put("Message", "成功");
			return rtjon.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, rtjs.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 获取医生用户网络咨询列表
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getDocUserListWys(InterfaceMessage msg) {
		String tag = "getDocUserListWys";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			String userName = json.get("userName") == null ? null : json
					.getString("userName");
			int start = json.get("start") == null ? null : json.getInt("start");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");

			QuesMainVo qvo = new QuesMainVo();
			qvo.setASK_DoctorID(doctorUid);
			qvo.setASK_UserID(userID);
			qvo.setASK_UserName(userName);
			List<QuesMainVo> quesVos = quesMainService.getReportList(qvo,
					start, pageSize);
			int rt = quesMainService.getReportListCount(qvo);
			if (rt < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "获取数据失败。");
			} else if (rt == 0) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "未获取数据。");
			}
			JSONArray rtjs = new JSONArray();
			JSONObject rtJson;
			JSONObject retJson = new JSONObject();
			if (quesVos.equals(null) || quesVos == null) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "未获取数据。");
			} else {
				for (QuesMainVo qv : quesVos) {
					rtJson = new JSONObject();
					rtJson.put("createTime", qv.getQUESMAIN_CreateTime());
					rtJson.put("content", qv.getQUESMAIN_Content());
					rtjs.add(rtJson);
				}
			}
			retJson.put("list", rtjs);
			retJson.put("count", rt);
			retJson.put("start", start);
			retJson.put("pageSize", pageSize);
			JSONObject rtjon = new JSONObject();
			rtjon.put("Param", retJson.toString());
			rtjon.put("Code", "1");
			rtjon.put("Message", "成功");
			return rtjon.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, retJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}

	/**
	 * 返回MYT医生列表（带是否在线）
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getMytDcList(InterfaceMessage msg) {
		String tag = "getMytDcList";
		try {
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			InterfaceMessage inMsg = new InterfaceMessage();
			JSONObject json = JSONObject.fromObject(msg.getParam());

			String nowTime = DateUtil.dateFormat(DateOper.getNowDateTime(),
					DateUtil.HM_FORMAT);
			String nowDate = DateUtil.dateFormat(DateOper.getNowDateTime(),
					DateUtil.YMD_FORMAT);
			int start = json.getInt("startRow");
			int pageSize = json.getInt("pageSize");
			// json.remove("startRow");
			// json.remove("pageSize");
			ServiceResult<com.common.json.JSONObject> arrawJson = arraworkService
					.getMytArraworks(nowTime, nowTime, nowDate, start, pageSize);
			if (arrawJson.getCode() < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444,
						arrawJson.getMessage());
			}
			ServiceResult<Integer> counts = arraworkService
					.getMytArraworksCount(nowTime, nowDate, nowTime);
			if (counts.getCode() < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444,
						counts.getMessage());
			}
			String ids = "";
			com.common.json.JSONObject jsres = arrawJson.getResult();
			com.common.json.JSONArray arjson = jsres.getJSONArray("result");
			for (int i = 0; i < arjson.length(); i++) {
				ids = ids + arjson.getJSONObject(i).getInt("operconfid") + ",";
			}
			ids = StringUtils.substringBeforeLast(ids, ",");
			json.put("doctorUids", ids);
			inMsg.setParam(json.toString());
			Integer uid;
			Integer opid;
			int tmp = 0;
			JSONArray doctors = JSONArray.fromObject(doctorInfoApi
					.queryComplexDoctorList(inMsg));
			for (int i = 0; i < doctors.size(); i++) {
				uid = doctors.getJSONObject(i).getInt("doctorUid");
				tmp = 0;
				for (int m = 0; m < arjson.length(); m++) {
					opid = arjson.getJSONObject(m).getInt("OPERCONFID");
					if (uid.equals(opid)) {
						if (arjson.getJSONObject(m).getInt("pa") == 1) {
							doctors.getJSONObject(i).put("arrawork", 2);
						} else if (arjson.getJSONObject(m).getInt("aw") == 1) {
							doctors.getJSONObject(i).put("arrawork", 1);
						} else {
							doctors.getJSONObject(i).put("arrawork", 0);
						}
						tmp = 1;
					}
				}
				if (tmp == 0) {
					doctors.getJSONObject(i).put("arrawork", 0);
				}
			}
			JSONObject rtjon = new JSONObject();
			rtjon.put("Code", 10000);
			rtjon.put("Message", "成功");
			rtjon.put("Total", counts.getResult());
			rtjon.put("Result", doctors.toString());
			return rtjon.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, retJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}

	public String getMytDocList(InterfaceMessage msg) {
		String tag = "getMytDocList";
		try {
			
			String  para =msg.getParam();
			System.out.println(para);
			
			
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			// InterfaceMessage inMsg = new InterfaceMessage();
			JSONObject json = JSONObject.fromObject(postService.queryComplexDoctorList_v2(para));
			
			System.out.println(json.toString());
			
			
		
			String nowTime = DateUtil.dateFormat(DateOper.getNowDateTime(),
					DateUtil.HM_FORMAT);
			String nowDate = DateUtil.dateFormat(DateOper.getNowDateTime(),
					DateUtil.YMD_FORMAT);

			int docid;
			for (int i = 0; i < json.getJSONArray("Result").size(); i++) {
				docid = json.getJSONArray("Result").getJSONObject(i)
						.getInt("doctorUid");
				MytArraworkBean rltMaBean = arraworkService.getMytArrawork(
						docid, nowTime).getResult();
				if (rltMaBean != null) {
					MytPauseworkBean rltMpBean = pauseService.getEntity(docid,
							nowTime, nowDate).getResult();
					if (rltMpBean != null) {
						json.getJSONArray("Result").getJSONObject(i)
								.put("arrawork", 2);
					} else {
						json.getJSONArray("Result").getJSONObject(i)
								.put("arrawork", 1);
					}
				} else {
					json.getJSONArray("Result").getJSONObject(i)
							.put("arrawork", 0);
				}
			}

			JSONArray jrt = json.getJSONArray("Result");
			JSONArray descJson = new JSONArray();
			for (int i = 0; i < jrt.size(); i++) {
				Integer arrawork = jrt.getJSONObject(i).getInt("arrawork");
				if (arrawork == 1) {
					descJson.add(jrt.getJSONObject(i));
				}
			}
			for (int i = 0; i < jrt.size(); i++) {
				Integer arrawork = jrt.getJSONObject(i).getInt("arrawork");
				if (arrawork == 2) {
					descJson.add(jrt.getJSONObject(i));
				}
			}
			for (int i = 0; i < jrt.size(); i++) {
				Integer arrawork = jrt.getJSONObject(i).getInt("arrawork");
				if (arrawork == 0) {
					descJson.add(jrt.getJSONObject(i));
				}
			}
			json.put("Result", descJson);
			return json.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, retJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}

	/**
	 * 返回MYT医生排班
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getMytDoctorArraworkList(InterfaceMessage msg) {
		String tag = "getMytDoctorArraworkList";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			ServiceResult<com.common.json.JSONObject> rt = arraworkService
					.getArraworkListAndOnline(doctorUid);
			if (rt.getCode() < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444,
						rt.getMessage());
			}
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.DAY_OF_YEAR, 6);
			Date mf = mfNow.getTime();
			List dateList = DateUtil.getDatesBetweenTwoDate(new Date(), mf);
			int tmp;
			com.common.json.JSONArray rtjsonArray = new com.common.json.JSONArray();
			for (int m = 0; m < dateList.size(); m++) {
				tmp = ((Date) dateList.get(m)).getDay();
				//System.out.println(tmp);
				for (int i = 0; i < rt.getResult().getJSONArray("result")
						.length(); i++) {
					if (rt.getResult().getJSONArray("result").getJSONObject(i)
							.getInt("week") == tmp) {
						com.common.json.JSONObject temJson = rt.getResult()
								.getJSONArray("result").getJSONObject(i);
						temJson.put("dayTime", DateUtil.dateFormat(
								(Date) dateList.get(m), DateUtil.YMDHMS_FORMAT));
						if (tmp == 0) {
							temJson.put("theday", "星期天");
						} else if (tmp == 1) {
							temJson.put("theday", "星期一");
						} else if (tmp == 2) {
							temJson.put("theday", "星期二");
						} else if (tmp == 3) {
							temJson.put("theday", "星期三");
						} else if (tmp == 4) {
							temJson.put("theday", "星期四");
						} else if (tmp == 5) {
							temJson.put("theday", "星期五");
						} else if (tmp == 6) {
							temJson.put("theday", "星期六");
						} else {
							temJson.put("theday", "星期" + tmp);
						}
						rtjsonArray.put(temJson);
						continue;
					}
				}
			}
			JSONObject rtjon = new JSONObject();
			rtjon.put("Code", 10000);
			rtjon.put("Message", "成功");
			rtjon.put("Result", rtjsonArray.toString());
			return rtjon.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, retJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	/**
	 * 拨打电话
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String sendMytSelfHelp(InterfaceMessage msg) {
		String tag = "sendMytSelfHelp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String mobile = json.get("mobile") == null ? null : json
					.getString("mobile");
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.account.api.AccLoginBean logic = accountService
					.getAccLoginObj(mobile);
			com.yihu.account.api.AccMembershipcardBean card = accountService
					.getMembershipcardObject(logic.getCardid());

			BossAccountBean bossAccount = new BossAccountBean();
			bossAccount.setAccountSN(card.getAccountsn());
			bossAccount.setCardID(card.getCardnumber());
			bossAccount.setCardState(Integer.parseInt(card.getState().trim()));
			bossAccount.setCardtypesn(card.getCardtypesn());
			MytDoctorViewBean rltMdvBean = null;
			ServiceResult<MytDoctorViewBean> sr = arraworkService
					.getMytDoctorView(Integer.valueOf(doctorUid));
			rltMdvBean = sr.getCode() == 1 ? sr.getResult() : null;
			List<MytArraworkBean> list = BusinessManager.getArraWorks(
					rltMdvBean.getBalancetype(), 0, "one", rltMdvBean
							.getOperconfid().toString(),
					rltMdvBean.getRemark(), rltMdvBean.getDoctorlevel()
							.toString(), rltMdvBean.getHospofficeid(),
					bossAccount, rltMdvBean.getOrgid());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					MytArraworkBean arra = (MytArraworkBean) list.get(i);
					if ("1".equals(arra.getOperatorname())) {
						// 在线
						break;
					}
					if ((i + 1) == list.size()) {
						if ("0".equals(arra.getOperatorname())) {
							/*
							 * return new RestResult( new OutPut(10001,
							 * "该医生当前不在线"), null, restParam);
							 */
							return WsUtil.getRetVal(msg.getOutType(), 10001,
									"该医生当前不在线");
						} else if ("2".equals(arra.getOperatorname())) {
							/*
							 * return new RestResult(new OutPut(10004,
							 * "医生停诊，无法受理咨询"), null, restParam);
							 */
							return WsUtil.getRetVal(msg.getOutType(), 10004,
									"医生停诊，无法受理咨询");
						} else if ("4".equals(arra.getOperatorname())) {
							/*
							 * return new RestResult(new OutPut(10005,
							 * "医生通话中，无法受理咨询"), null, restParam);
							 */
							return WsUtil.getRetVal(msg.getOutType(), 10005,
									"医生通话中，无法受理咨询");
						}
					}
				}
			}
			String[] retmsg = BusinessManager.checkCards(rltMdvBean
					.getBalancetype(), rltMdvBean.getDoctorlevel().toString(),
					rltMdvBean.getHospofficeid(), bossAccount, rltMdvBean
							.getOrgid(), rltMdvBean.getOperconfid());
			if ("0".equals(retmsg[0])) {
				/*
				 * return new RestResult(new OutPut(10006, res[1]), null,
				 * restParam);
				 */
				return WsUtil.getRetVal(msg.getOutType(), 10006, retmsg[1]);
			}

			SelfHelpVo selfHelp = new SelfHelpVo();
			selfHelp.setShMobile(mobile);
			selfHelp.setOperTime(DateUtil.dateFormat(DateOper.getNowDateTime()));
			selfHelp.setState(1);
			selfHelp.setOperConfId(doctorUid);
			selfHelp.setShType(1);
			selfHelp.setBussId(0);
			selfHelp.setUserPhone(mobile);
			selfHelp.setCardId(card.getCardnumber());
			int rt = selfHelpService.insertSelfHelp(selfHelp);
			if (rt < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "数据插入失败。");
			}
			JSONObject rtjon = new JSONObject();
			JSONArray rtAr = new JSONArray();
			rtjon.put("Code", 10000);
			rtjon.put("Message", "成功");
			rtjon.put("Result", rtAr);
			return rtjon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}

	/**
	 * 获得MYT网关电话
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getMytPhone(InterfaceMessage msg) {
		String tag = "getMytPhone";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			JSONObject rtjon = new JSONObject();
			JSONObject phone = new JSONObject();
			phone.put("telePhone", "059183111506");
			JSONArray rt = new JSONArray();
			rt.add(phone);
			rtjon.put("Code", 10000);
			rtjon.put("Message", "成功");
			rtjon.put("Result", rt);
			return rtjon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	/**
	 * 更新MYT电话回拨状态
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String upBookEnrol(InterfaceMessage msg) {
		String tag = "upBookEnrol";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int bookID = json.get("bookEnrolID") == null ? null : json
					.getInt("bookEnrolID");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			String state = json.get("state") == null ? null : json
					.getString("state");
			String revert =  json.get("revert") == null ? null : json
					.getString("revert");
			BookEnrolVo vo = new BookEnrolVo(); 	
			vo.setBOOKENROLID(bookID);
			/*vo = bookEnrolService.queryBookEnrol(vo);
			if(vo.getComeFrom()!= null){
				if(vo.getComeFrom().equals("900019")){
					postService.telecounSelingUpdate(revert,
							String.valueOf(bookID));
				}
			}*/
			vo.setREVERTRESULT(revert);
			vo.setSTATE(state);
			int uprt = bookEnrolService.updateBE(vo);
			if(uprt <= 0 ){
				 return ApiUtil.getJsonRt(-14444, "更新失败!");
			}else{
				return  ApiUtil.getJsonRt(10000, "更新成功!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 更新MYT电话回拨状态
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String upBookEnrolV2(InterfaceMessage msg) {
		String tag = "upBookEnrol";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int bookID = json.get("bookEnrolID") == null ? null : json
					.getInt("bookEnrolID");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			String state = json.get("state") == null ? null : json
					.getString("state");
			String revert =  json.get("revert") == null ? null : json
					.getString("revert");
			String operatorid =  json.get("operatorid") == null ? null : json
					.getString("operatorid"); 
			BookEnrolVo vo = new BookEnrolVo();
			vo.setBOOKENROLID(bookID);
			vo.setREVERTRESULT(revert);
			vo.setSTATE(state);
			if(operatorid.equals("900019")){
				if (revert.equals("12") || revert.equals("3")) {
					postService.telecounSelingUpdate("1",
							String.valueOf(bookID));
				} else if (revert.equals("1") || revert.equals("2")) {

				} else {
					postService.telecounSelingUpdate("2",
							String.valueOf(bookID));
				}
			}
			
			int uprt = bookEnrolService.updateBE(vo);
			if(uprt <= 0 ){
				 return ApiUtil.getJsonRt(-14444, "更新失败!");
			}else{
				return  ApiUtil.getJsonRt(10000, "更新成功!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 更新号百次数
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String upHBPhoneCount(InterfaceMessage msg) {
		String tag = "upHBPhoneCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String phone =  json.get("phone") == null ? null : json
					.getString("phone");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			String rt = postService.changeMytUserCount(phone);
			JSONObject rtJson = JSONObject.fromObject(rt);
			if(rtJson.getInt("Status") == 0 ){
				return  ApiUtil.getJsonRt(10000, rtJson.getString("description"));
			}else{
				 return ApiUtil.getJsonRt(-14444, rtJson.getString("description"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	/**
	 * 获取号百次数
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getHBPhoneCount(InterfaceMessage msg) {
		String tag = "upHBPhoneCount";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String phone =  json.get("phone") == null ? null : json
					.getString("phone");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			String rt = postService.getMytUserCount(phone);
			JSONObject rtJson = JSONObject.fromObject(rt);
			if(rtJson.getInt("Status") == 0 ){
				return  ApiUtil.getJsonRt(10000, rtJson.getString("description"));
			}else{
				 return ApiUtil.getJsonRt(-14444, rtJson.getString("description"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	public String getMytConsenrols(InterfaceMessage msg) {
		String tag = "getMytConsenrols";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String cardid =  json.get("cardNumber") == null ? null : json
					.getString("cardNumber");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			int pageIndex = json.get("pageIndex") == null ? null : json.getInt("pageIndex");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			ConsEnrolVo cVo =new ConsEnrolVo();
			cVo.setCARDID(cardid);
			String cons = consEnrolService.getMytConsenrols(cVo, pageSize, pageIndex);
			String rt =ApiUtil.getJsonRt(10000, "成功。") ;
			JSONObject rtJson = JSONObject.fromObject(rt);
			JSONObject consJson = JSONObject.fromObject(cons);
			rtJson.put("Result", consJson.getJSONArray("result"));
			return  rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String getMytBookEnrols(InterfaceMessage msg) {
		String tag = "getMytBookEnrols";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String cardid =  json.get("cardNumber") == null ? null : json
					.getString("cardNumber");
			
			String revertresult =  json.get("revertresult") == null ? null : json
					.getString("revertresult");
			String begintime =  json.get("begintime") == null ? null : json
					.getString("begintime");
			String endtime =  json.get("endtime") == null ? null : json
					.getString("endtime");
			
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			int pageIndex = json.get("pageIndex") == null ? null : json.getInt("pageIndex");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			String cons =bookenrolService.getUserReturnList(cardid,begintime,endtime,revertresult, pageSize, pageIndex);
			Integer count = bookenrolService.getUserReturnListCount(cardid,begintime,endtime,revertresult);
			String rt =ApiUtil.getJsonRt(10000, "成功。") ;
			JSONObject rtJson = JSONObject.fromObject(rt);
			JSONObject consJson = JSONObject.fromObject(cons);
			rtJson.put("Total",count);
			rtJson.put("Result", consJson.getJSONArray("result"));
			return  rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	public String saveMytBookEnrol(InterfaceMessage msg) {
		String tag = "saveMytBookEnrol";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String cardid =  json.get("cardNumber") == null ? null : json
					.getString("cardNumber");
			String custName =  json.get("custName") == null ? null : json
					.getString("custName");
			String custPhone =  json.get("custPhone") == null ? null : json
					.getString("custPhone");
			String dateWeek =  json.get("dateWeek") == null ? null : json
					.getString("dateWeek");
			String startTime =  json.get("startTime") == null ? null : json
					.getString("startTime");
			String endTime =  json.get("endTime") == null ? null : json
					.getString("endTime");
			String remark =  json.get("remark") == null ? null : json
					.getString("remark");
			Integer doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			String operatorid =  json.get("operatorID") == null ? null : json
					.getString("operatorID");
			String operatorName =  json.get("operatorName") == null ? null : json
					.getString("operatorName");
			//REVERTRESULT  STATE REVERTPHONE CUSTPHONE
			BookEnrolVo bvo = new BookEnrolVo();
			char[] dwArr = dateWeek.toCharArray();
			String dealed = "";
			for (int i = 0; i < dwArr.length; i++) {
				if ((dwArr[i] >= 48 && dwArr[i] <= 57) || dwArr[i] == 45) {
					dealed += dwArr[i];
				}
			}
			bvo.setCARDID(cardid);
			bvo.setCUSTNAME(custName);
			bvo.setSTARTTIME(startTime);
			bvo.setENDTIME(endTime);
			bvo.setREMARK(remark);
			bvo.setOPERCONFID(String.valueOf(doctorUid));
			bvo.setDATEWEEK(dealed);
			bvo.setCUSTPHONE(custPhone);
			bvo.setREVERTRESULT("1");
			bvo.setREVERTPHONE(custPhone);
			bvo.setComeFrom(operatorid);
			DoctorInfoVo dvo = new DoctorInfoVo();
			dvo.setDoctorUid(doctorUid);
			dvo = 	doctorInfoService.queryDoctorInfo(dvo);
			if(dvo==null){
				return ApiUtil.getJsonRt(-100001, "医生信息不存在。");
			}
			bvo.setCONSTYPE("1");
			bvo.setSTATE("1");
			bvo.setDOCTORID(dvo.getBaseDoctorID()+"");
			bvo.setDOCTORNAME(dvo.getDoctorName());
			bvo.setOPERATORID(operatorid);
			bvo.setOPERATORNAME(operatorName);
			bvo.setOPERTIME(DateUtil.dateFormat(new Date()));
			AccMembershipcardBean card = Rpc.get(IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"), 8000).getMembershipcardObject(
					cardid);
			if (card != null) {
				bvo.setCardtypesn(card.getCardtypesn());
				bvo.setCardorgid(card.getOrgid());
			} else {
				return ApiUtil.getJsonRt(-100002, "账户无效，登记失败。");
			}
			int rt = bookEnrol.insertBookEnrolRt(bvo);
			String rtString =ApiUtil.getJsonRt(10000, "成功。") ;
			JSONObject rtid = new JSONObject();
			rtid.put("bookEnrolID", rt);
			JSONArray idArray = new JSONArray();
			idArray.add(rtid); 
			JSONObject rtJson = JSONObject.fromObject(rtString);
			rtJson.put("Result", idArray);
			return  rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	
	public String getMytWorkList(InterfaceMessage msg) {
		String tag = "getMytWorkList";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
				// 判断今天是周几
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateOper.getNowDateTime());
			int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

			// 查询排班
			ServiceResult<List<Object[]>> sr = arraworkService
					.getMytArraworkForDate(doctorUid+"");
			if(sr.getCode() <= 0 ){
				return ApiUtil.getJsonRt(-2000, sr.getMessage());
			}
			List<Object[]> lstObj = sr.getCode() > 0 ? sr.getResult() : null;
			
			List<String> al = new ArrayList<String>();
			List<String> temp = new ArrayList<String>();

			for (Object[] objs : lstObj) {
				int wd = Integer.parseInt(objs[0].toString());
				// 计算实际日期差距
				int realWeek = wd;
				if (week > wd) {
					realWeek = wd + 7;
				}
				cal.setTime(DateOper.getNowDateTime());
				cal.add(Calendar.DAY_OF_YEAR, realWeek - week);
				String dateWeek = DateUtil.dateFormat(cal.getTime(),
						DateUtil.YMD_FORMAT)
						+ "("
						+ StringUtil.weekArr[wd]
						+ ")" + objs[1].toString() + "--" + objs[2].toString();

				if (week > wd) {
					al.add(0, dateWeek);
				} else {
					temp.add(dateWeek);
				}
			}
			for (String strTemp : temp) {
				al.add(0, strTemp);
			}

			// 将查询结果放入jsonArray
			JSONArray jsonArray = new JSONArray();
			if (al != null && !al.isEmpty()) {
				JSONObject obs = new JSONObject();
				obs.put("dateid", "99999");
				obs.put("datename", "--请选择--");
				obs.put("selected", true);
				jsonArray.add(obs);
				for (int i = 0; i < al.size(); i++) {
					JSONObject ob = new JSONObject();
					ob.put("dateid", i);
					ob.put("datename", al.get(i));
					jsonArray.add(ob);
				}
			}
			String rt =ApiUtil.getJsonRt(10000, "成功。") ;
			JSONObject rtJson = JSONObject.fromObject(rt);
			rtJson.put("Result", jsonArray);
			return  rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return  ApiUtil.getJsonRt(-14444, "加载异常!"
					+ StringUtil.getException(e));
		}
	}
	

}
