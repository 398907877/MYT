package com.yihu.myt.http;

import java.util.Date;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.boss.sdk.OperatorInfo;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.AccMembershipcardBean;
import com.yihu.account.api.IAccountService;
import com.yihu.api.api.AskDoctorQuestionApi;
import com.yihu.api.api.DoctorBillDataApi;
import com.yihu.api.api.NetworkConsultingMedicalApi;
import com.yihu.baseinfo.api.DoctorAccountApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.ICloseMainService;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.IDepAndDisService;
import com.yihu.myt.service.service.IDepartmentsService;
import com.yihu.myt.service.service.IDiseaseService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.impl.DepAndDisService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.CloseMainVo;
import com.yihu.myt.vo.ConsumerOrdersVo;
import com.yihu.myt.vo.DepAndDisVo;
import com.yihu.myt.vo.DepartmentsVo;
import com.yihu.myt.vo.DiseaseVo;
import com.yihu.myt.vo.DoctorVo;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.oa.api.IBasisWS;
import com.yihu.wsgw.api.InterfaceMessage;
public class QuestionMainAction {
	private static IQuesMainService quesMainService = Ioc
			.get(IQuesMainService.class);
	private static IConsumerOrdersService consumerOrdersService = Ioc
			.get(IConsumerOrdersService.class);
	private static AskDoctorQuestionApi askDoctorQuestionApi = Ioc
			.get(AskDoctorQuestionApi.class);
	private static NetworkConsultingMedicalApi networkConsultingMedicalApi = Ioc
			.get(NetworkConsultingMedicalApi.class);
	
	private static IDepartmentsService departmentsService = Ioc
			.get(IDepartmentsService.class);
	private static IDiseaseService diseaseService = Ioc
	.get(IDiseaseService.class);
	private static DoctorBillDataApi doctorBillDataApi = Ioc
			.get(DoctorBillDataApi.class);
	
	
	public HttpResponseContext getCheckList(HttpRequestContext request) {
		try {	
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String starttime =  jsonobj.get("sBTime") != null ? jsonobj.get("sBTime").toString(): null ;
			String endtime =  jsonobj.get("sETime") != null ? jsonobj.get("sETime").toString(): null ;
			String cont =  jsonobj.get("cont") != null ? jsonobj.get("cont").toString(): null ;
			//String checkMainID =  jsonobj.get("checkMainID") != null ? jsonobj.get("checkMainID").toString(): null ;
			int  pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			Integer checkID =  jsonobj.get("checkID") != null ? jsonobj.getInt("checkID"): null ;
			/*
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID", "");
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			 */			
			QuesMainVo vo = new QuesMainVo();
			vo.setQD_CheckStatus(checkID);
			vo.setQUESMAIN_Content(cont);
			String rt = quesMainService.quesDoctorCheckList(vo, starttime, endtime, pageSize, pageIndex);
			int count = quesMainService.quesDoctorCheckListCount(vo, starttime, endtime );
			
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("totalProperty", count);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getReturnList(HttpRequestContext request) {
		try {	
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String starttime =  jsonobj.get("sBTime") != null ? jsonobj.get("sBTime").toString(): null ;
			String endtime =  jsonobj.get("sETime") != null ? jsonobj.get("sETime").toString(): null ;
			String cont =  jsonobj.get("cont") != null ? jsonobj.get("cont").toString(): null ;
			//String checkMainID =  jsonobj.get("checkMainID") != null ? jsonobj.get("checkMainID").toString(): null ;
			int  pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			//Integer checkID =  jsonobj.get("checkID") != null ? jsonobj.getInt("checkID"): null ;
			/*
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID", "");
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			 */			
			QuesMainVo vo = new QuesMainVo();
			//vo.setQD_CheckStatus(checkID);
			vo.setQUESMAIN_Content(cont);
			String rt = quesMainService.quesReturnList(vo, starttime, endtime, pageSize, pageIndex);
			int count = quesMainService.quesReturnListCount(vo, starttime, endtime );
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("totalProperty", count);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getCheckQueCount(HttpRequestContext request) {
		
		try {	
			/*String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);*/
 			Integer quesid =   request.getInt("queid");
			InterfaceMessage msg = new InterfaceMessage();
			JSONObject par = new JSONObject();
			par.put("quID", quesid);
			msg.setParam(par.toString());
			String rt = askDoctorQuestionApi.getQuesContentByQuesIDForCheck(msg);
			JSONObject rtJson = new JSONObject(rt);
			//System.out.print(rt);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	
	
	private static IDepAndDisService depAndDisService = Ioc.get(IDepAndDisService.class);
	
	
	public HttpResponseContext saveCheck(HttpRequestContext request) {
		try{
			String onedepids = request.getParameter("onedepids");
			String onedepnames = request.getParameter("onedepnames");
			String twodepids = request.getParameter("twodepids");
			String twodepnames = request.getParameter("twodepnames");
			String diseaseids = request.getParameter("diseaseids");
			String diseasenames = request.getParameter("diseasenames");
			Integer queid = request.getInt("queid");
			OperatorInfo oper = request.getOperator();
			//TODO  WUJIAJUN  获取到用户的信息！  要把分科和疾病 set到  表中去 然后在列表去取值
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo")); 
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID",oper.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject emp = new JSONObject(retString);
			String phone = emp.getString("mobiPhone");
			ServiceResult<String> dcrt = doctorAccountApi.getDoctorAccountInfoByLoginId(phone);
			if(dcrt.getCode() < 0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
			}
			JSONObject dcrtJs = new JSONObject(dcrt.getResult());
			int doctorUid = dcrtJs.getInt("doctorUid");
			String doctorName = dcrtJs.getString("doctorName");
		//把疾病和分科  分别插入表中
			if(!"".equals(onedepnames)){
				//科室
				String [] lista= onedepnames.split(",");
				
				for (String string : lista) {
					//插入到表中
					
					DepAndDisVo  vo = new DepAndDisVo ();
					vo.setDocId(String.valueOf(doctorUid));
					vo.setDocName(doctorName);
					vo.setDepartMent(string);
					depAndDisService.insertDepAndDis(vo);
				}
			}
			if(!"".equals(diseasenames)){
				//疾病
				String [] listb= diseasenames.split(",");
				for (String string : listb) {
					//插入到表中
					DepAndDisVo  vo = new DepAndDisVo ();
					vo.setDocId(String.valueOf(doctorUid));
					vo.setDocName(doctorName);
					vo.setDisease(string);
					depAndDisService.insertDepAndDis(vo);
				}
				
			}
			

			if(StringUtil.isNotEmpty(onedepids) && StringUtil.isNotEmpty(onedepnames)  && StringUtil.isNotEmpty(twodepids)  && StringUtil.isNotEmpty(twodepnames) ){
				String[] odept = onedepids.split(",");
				String[] odpName = onedepnames.split(",");
				String[] tdept = twodepids.split(",");
				String[] tdpName = twodepnames.split(",");
				DepartmentsVo dvp = new DepartmentsVo();
				dvp.setASK_QuesID(queid);
				dvp.setDEPART_Status(0);
				int dpdl = departmentsService.updateDepartmentsForQuesID(dvp);
				if(dpdl<0){
					return new  HttpResponseContext(ApiUtil.getJsonRt(-14444, "初始化关联科室库数据失败。"));
				}
				DepartmentsVo dep;
				for(int i=0;i<odept.length;i++){
					dep = new DepartmentsVo();
					dep.setASK_DepartIDOne(Integer.valueOf(odept[i]));
					dep.setASK_DepartIDTwo(tdept[i]);
					dep.setASK_DepartNameOne(odpName[i]);
					dep.setASK_DepartNameTwo(tdpName[i]);
					dep.setASK_QuesID(queid);
					dep.setDEPART_Status(1);
					dep.setDEPART_OperatorType(1);
					int dert = departmentsService.insertDepartments(dep);
					if(dert<=0){
						return new  HttpResponseContext(ApiUtil.getJsonRt(-14444, "关联科室库数据保存失败。"));
					}
				}
			}
			if(StringUtil.isNotEmpty(diseaseids) && StringUtil.isNotEmpty(diseasenames)){
				String[] disid = diseaseids.split(",");
				String[] disName = diseasenames.split(",");
				DiseaseVo disVo = new DiseaseVo();
				disVo.setASK_QuesID(queid);
				disVo.setDISEASE_Status(0);
				int disDl = diseaseService.updateDiseaseForAskQueID(disVo);
				if(disDl<0){
					return new  HttpResponseContext(ApiUtil.getJsonRt(-14444, "初始化疾病库数据失败。"));
				}
				DiseaseVo dvo;
				for(int i=0;i<disid.length;i++){
					dvo=new DiseaseVo();
					dvo.setASK_DiseaseID(Integer.valueOf(disid[i]));
					dvo.setASK_DiseaseName(disName[i]);
					dvo.setASK_QuesID(queid);
					dvo.setDISEASE_OperatorType(1);
					dvo.setDISEASE_Status(1);
					int rtdi = diseaseService.insertDisease(dvo);
					if(rtdi<=0){
						return new  HttpResponseContext(ApiUtil.getJsonRt(-14444, "疾病库数据保存失败。"));
					}
				}
			}
			QuesMainVo qmvo = new QuesMainVo();
			qmvo.setQUESMAIN_ID(queid);
			qmvo.setQD_DeptIDS(","+onedepids);
			qmvo.setQD_DeptTwoIDS("," + twodepids);
			qmvo.setQD_DiseaseIDS("," + diseaseids);
			qmvo.setQD_CheckStatus(1);
			qmvo.setQD_IsUserReplay(1);
			qmvo.setQD_CheckOperatorID(String.valueOf(oper.getOperatorID()));
			qmvo.setQD_CheckOperatorName(oper.getOperatorName());
			qmvo.setQD_CheckTime(DateUtil
					.dateFormat(new Date()));
			int qe = quesMainService.updateQuesMain(qmvo);
			if(qe<0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "问题数据保存失败。"));
			}
			return new HttpResponseContext(ApiUtil.getJsonRt(10000, "数据保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext saveReturn(HttpRequestContext request) {
		try{
			String remark = request.getParameter("remark");
			Integer queid = request.getInt("queid"); 
			OperatorInfo oper = request.getOperator();
			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queid);
			qvo.setQD_Reason(remark);
			qvo.setQD_CheckTime(DateUtil
					.dateFormat(new Date()));
			qvo.setQD_Status(5);
			qvo.setQD_CheckStatus(0);
			qvo.setQD_CheckOperatorID(String.valueOf(oper.getOperatorID()));
			qvo.setQD_CheckOperatorName(oper.getOperatorName());
			int qe = quesMainService.updateQuesMain(qvo);
			if(qe<0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "问题数据保存失败。"));
			}
			return new HttpResponseContext(ApiUtil.getJsonRt(10000, "数据保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	public HttpResponseContext deleteQue(HttpRequestContext request) {
		try {
			Integer queid = request.getInt("queid");
			OperatorInfo oper = request.getOperator();
			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queid);
			qvo.setQUESMAIN_Status(0);
			qvo.setQD_CheckStatus(0);
			qvo.setQD_CheckTime(DateUtil
					.dateFormat(new Date()));
			qvo.setQD_CheckOperatorID(String.valueOf(oper.getOperatorID()));
			qvo.setQD_CheckOperatorName(oper.getOperatorName());
			int qe = quesMainService.updateQuesMain(qvo);
			if (qe < 0) {
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444,
						"问题数据保存失败。"));
			}
			return new HttpResponseContext(ApiUtil.getJsonRt(10000, "数据保存成功"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getQuesList(HttpRequestContext request) {
		try {
			int  pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			
			
			OperatorInfo oper = request.getOperator();
			
			
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo")); 
			
			
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID",oper.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject emp = new JSONObject(retString);
			String phone = emp.getString("mobiPhone");
			ServiceResult<String> dcrt = doctorAccountApi.getDoctorAccountInfoByLoginId(phone);
			if(dcrt.getCode() < 0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
			}
			JSONObject dcrtJs = new JSONObject(dcrt.getResult());
			int doctorUid = dcrtJs.getInt("doctorUid");
			QuesMainVo qvo = new QuesMainVo();
			qvo.setASK_DoctorID(doctorUid);
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			Integer type =  jsonobj.get("type") != null ? jsonobj.getInt("type"): null ;
			Integer ifFree =  jsonobj.get("ifFree") != null ? jsonobj.getInt("ifFree"): null ;
			String rt = quesMainService.quesFreeOverTimeList(qvo, type,ifFree,pageSize,pageIndex);
			int count = quesMainService.quesFreeOverTimeListCount(qvo, type,ifFree);
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("totalProperty", count);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	public HttpResponseContext doctorAnswer(HttpRequestContext request) {
		try {
			Integer queid = request.getInt("queid");
			OperatorInfo oper = request.getOperator();
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo")); 
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID",oper.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject emp = new JSONObject(retString);
			String phone = emp.getString("mobiPhone");
			ServiceResult<String> dcrt = doctorAccountApi.getDoctorAccountInfoByLoginId(phone);
			if(dcrt.getCode() < 0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
			}
			JSONObject dcrtJs = new JSONObject(dcrt.getResult()); 
			int doctorUid = dcrtJs.getInt("doctorUid");
			int hosDeptId= dcrtJs.getInt("hosDeptId");
			JSONObject param = new  JSONObject();
			param.put("queID",queid);
			param.put("doctorID",doctorUid);
			param.put("deptID",hosDeptId);
			InterfaceMessage msg = new InterfaceMessage();
			msg.setParam(param.toString());
			String rt = askDoctorQuestionApi.doctorAnswer(msg);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getQuesReplys(HttpRequestContext request) {
		try {	
			JSONObject param = new  JSONObject();
			Integer queid = request.getInt("queid");
			param.put("quID",queid);
			param.put("replyID",0);
			param.put("pageSize",100);
			param.put("pageIndex",0);
			param.put("userType",2);
			InterfaceMessage msg = new InterfaceMessage();
			msg.setParam(param.toString());
			OperatorInfo oper = request.getOperator();
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo")); 
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID",oper.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject emp = new JSONObject(retString);
			String phone = emp.getString("mobiPhone");
			ServiceResult<String> dcrt = doctorAccountApi.getDoctorAccountInfoByLoginId(phone);
			if(dcrt.getCode() < 0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
			}
			JSONObject dcrtJs = new JSONObject(dcrt.getResult()); 
			int doctorUid = dcrtJs.getInt("doctorUid");
			String rt = askDoctorQuestionApi.getQueReplyByQuesID(msg);
			//System.out.println(rt);
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("userDocUid", doctorUid);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext replyQues(HttpRequestContext request) {
		try {	
			Integer queid = request.getInt("queid");
			Integer closed = request.getInt("closed");
			String remark = request.getParameter("remark");
			OperatorInfo oper = request.getOperator();
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo")); 
			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID",oper.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject emp = new JSONObject(retString);
			String phone = emp.getString("mobiPhone");
			ServiceResult<String> dcrt = doctorAccountApi.getDoctorAccountInfoByLoginId(phone);
			if(dcrt.getCode() < 0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
			}
			JSONObject dcrtJs = new JSONObject(dcrt.getResult()); 
			int doctorUid = dcrtJs.getInt("doctorUid");
			String userID = request.getParameter("userID");
			JSONObject param = new  JSONObject();
			JSONArray jar = new JSONArray();
			param.put("queID",queid);
			param.put("replyType",0);
			param.put("doctorID",doctorUid);
			param.put("userID",userID);
			param.put("messCont",remark);
			param.put("fileMess",jar);
			param.put("platform",0);
			param.put("filesCount",0);
			InterfaceMessage msg = new InterfaceMessage();
			msg.setParam(param.toString());
			String rt = networkConsultingMedicalApi.replyQuertionDoctor(msg);
			if(closed > 0 ){
				IAccountService accountService = Rpc.get(IAccountService.class, ConfigUtil
						.getInstance().getUrl("url.account"), 8000);
				AccMembershipcardBean  card = new AccMembershipcardBean ();
				card.setAccountsn(Integer.valueOf(userID));
				card.setState("3");//3表示状态正常的卡
				card = 	accountService.getMembershipcardObject(card);
				InterfaceMessage overMsg = new InterfaceMessage();
				JSONObject overPar = new  JSONObject();
				overPar.put("quesID", queid);
				overPar.put("cardID", card.getCardnumber());
				overPar.put("userID", userID);
				overPar.put("doctorID", doctorUid);
				overPar.put("pleasedLevel", "1");
				overPar.put("OperatorId", "900031");
				overPar.put("OperatorName", "网站管理员");
				overMsg.setParam(overPar.toString());
				String asrt = askDoctorQuestionApi.overQuertionV2(overMsg);
				JSONObject asrtJs = new JSONObject(asrt);
				if(asrtJs.getInt("Code")<=0){
					return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "医生数据获取失败。")); 
				}
			}
 			JSONObject rtJson = new JSONObject(rt);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getListForCheck(HttpRequestContext request) {
		try 
		{
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String btime =  jsonobj.get("btime")==null?null: jsonobj.get("btime").toString();
			String etime =  jsonobj.get("etime")==null?null: jsonobj.get("etime").toString();
			Integer sourceType =  StringUtil.isEmpty(jsonobj.get("sourceType"))? null : jsonobj.getInt("sourceType");
			Integer ordersStatus =  StringUtil.isEmpty(jsonobj.get("ordersStatus"))? null : jsonobj.getInt("ordersStatus");
			Integer quesType =  StringUtil.isEmpty(jsonobj.get("quesType"))? null : jsonobj.getInt("quesType");
			Integer platform =  StringUtil.isEmpty(jsonobj.get("platform"))? null : jsonobj.getInt("platform");
			Integer askUserID =  StringUtil.isEmpty(jsonobj.get("askUserID"))? null : jsonobj.getInt("askUserID");
			Integer pid =  StringUtil.isEmpty(jsonobj.get("pid"))? null : jsonobj.getInt("pid");
			Integer dcid =  StringUtil.isEmpty(jsonobj.get("dcid"))? null : jsonobj.getInt("dcid");
			Integer dcrid =  StringUtil.isEmpty(jsonobj.get("dcrid"))? null : jsonobj.getInt("dcrid");
			String quesContent =  StringUtil.isEmpty(jsonobj.get("quesContent")) ? null:jsonobj.get("quesContent").toString();
			String askDoctorName =StringUtil.isEmpty(jsonobj.get("askDoctorName")) ?null: jsonobj.get("askDoctorName").toString();  

			QuesMainVo vo = new QuesMainVo();
			vo.setQD_SourceType(sourceType);
			vo.setQD_OrdersStatus(ordersStatus);
			vo.setQUESMAIN_Platform(platform);
			vo.setASK_UserID(askUserID);
			vo.setQUESMAIN_Content(quesContent);
			vo.setProvinceID(pid);
			vo.setAskDocName(askDoctorName);
			if(quesType == null){
				quesType = 99999;
			}
			int count = quesMainService.quesListCountForCheckTeam(vo,dcid,dcrid, quesType, btime, etime);
			String rt = quesMainService.quesListForCheckTeam(vo,dcid,dcrid, quesType, btime, etime,pageSize, pageIndex);
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("totalProperty", count);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getListForOverDcSub(HttpRequestContext request) {
		try 
		{	
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			System.out.println(jsonobj.get("checkStatus"));
			Integer checkStatus =  jsonobj.get("checkStatus")==null? null : jsonobj.getInt("checkStatus");
			QuesMainVo vo = new QuesMainVo();
			vo.setQD_CheckStatus(checkStatus);
			int count = quesMainService.quesDcSubCheckCount(vo);
			String rt = quesMainService.quesDcSubCheck(vo,pageSize, pageIndex);
			JSONObject rtJson = new JSONObject(rt);
			rtJson.put("totalProperty", count);
			return new HttpResponseContext(rtJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	} 
	
	
	private static ICloseMainService closeMainService = Ioc
			.get(ICloseMainService.class);
	
	private static IPostService postService = Ioc
			.get(IPostService.class); 
	public HttpResponseContext overSubQues(HttpRequestContext request) {
		try 
		{	
			Integer closedType =  request.getInt("closedType");
			Integer queid = request.getInt("queid");
			QuesMainVo vo = new QuesMainVo();
			vo.setQUESMAIN_ID(queid);
			vo = quesMainService.queryQuesMainByCondition(vo);
			InterfaceMessage overMsg = new InterfaceMessage();
			JSONObject overPar = new  JSONObject();
			JSONObject comeFrom = new JSONObject();
			comeFrom.put("ClientId", 0);
			String asrt = "";
			
			
			
			String  closeDocName="";
			String  closeDocID="";
			try {
				//post   
				JSONObject jsObj = new JSONObject();
				jsObj.put("doctorUid", vo.getASK_DoctorID());
				jsObj.put("erviceStatusSearch", 3);
				// jsonObj.put("displayStatus", 1);
				jsObj.put("main", 1);
				jsObj.put("pageIndex", 1);
				jsObj.put("pageSize", 100);
				jsObj.put("columns", "doctorAccount,doctorName");
				net.sf.json.JSONObject dcsRt = 	net.sf.json.JSONObject.fromObject(postService.queryComplexDoctorList_v2(jsObj.toString()));
				net.sf.json.JSONArray dcsJson = net.sf.json.JSONArray.fromObject(dcsRt
						.getJSONArray("Result"));
				net.sf.json.JSONObject  doctornamemmmmmm = (net.sf.json.JSONObject) dcsJson.get(0);
				System.out.println("查询的 json：：："+doctornamemmmmmm.getString("doctorName"));
				
				doctornamemmmmmm.getString("doctorName");
				String.valueOf( vo.getASK_DoctorID());
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "查询 医生基础信息失败。")); 
			}
			
		
			
			
			
			//这两个用于set到  关闭的表中去
	
			
			
			
			if(closedType.equals(1)){
				IAccountService accountService = Rpc.get(IAccountService.class, ConfigUtil
						.getInstance().getUrl("url.account"), 8000);
				AccMembershipcardBean  card = new AccMembershipcardBean ();
				card.setAccountsn(Integer.valueOf(vo.getASK_UserID()));
				card.setState("3");//3表示状态正常的卡
				card = 	accountService.getMembershipcardObject(card);
				overPar.put("quesID", queid);
				overPar.put("cardID", card.getCardnumber());
				overPar.put("userID", vo.getASK_UserID());
				overPar.put("doctorID", vo.getASK_DoctorID());
				overPar.put("pleasedLevel", "0");
				overPar.put("OperatorId", "900031");
				overPar.put("OperatorName", "网站管理员");
				overMsg.setAuthInfo(comeFrom.toString());
				overMsg.setParam(overPar.toString());
				
				
				
			

				
				
				
				
				
				
		
				
				asrt=askDoctorQuestionApi.overQuertionV2(overMsg);
			}else if(closedType.equals(0)){
			//	userid coid queID OperatorId OperatorNameheme
				ConsumerOrdersVo cvo = new ConsumerOrdersVo();
				cvo.setASK_QuesID(queid);
				cvo = consumerOrdersService.queryConsumerOrdersByQuesID(cvo);
				if(StringUtil.isEmpty(cvo)){
					return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "订单数据获取失败。")); 
				}
				overPar.put("queID", queid);
				overPar.put("coid",cvo.getCO_ID());
				overPar.put("userid", vo.getASK_UserID());
				overPar.put("OperatorId", "900031");
				overPar.put("OperatorName", "网站管理员");
				overPar.put("doctorID", vo.getASK_DoctorID());
				
				overMsg.setParam(overPar.toString());
				
				System.out.println("医生的 id：：：：" +vo.getASK_DoctorID());
				asrt = doctorBillDataApi.overQuesRefund(overMsg);
			}else{
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "问题数据获取失败。")); 
			}
			JSONObject asrtJs = new JSONObject(asrt);
			if(asrtJs.getInt("Code")<=0){
				return new HttpResponseContext(ApiUtil.getJsonRt(-14444, "问题数据获取失败。")); 
			}
			return new HttpResponseContext(asrtJs.toString());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext queReportForDepts(HttpRequestContext request) {
		try 
		{
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String time =  StringUtil.isEmpty(jsonobj.get("time")) ? null:jsonobj.get("time").toString();
			String rt = quesMainService.queReportForDepts( time,pageSize, pageIndex);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext queReportForDays(HttpRequestContext request) {
		try 
		{
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			String json = request.getParameter("json");
			//String time = request.getParameter("time");
			JSONObject jsonobj = new JSONObject(json);
			String time =  StringUtil.isEmpty(jsonobj.get("time")) ? null:jsonobj.get("time").toString();
			String rt = quesMainService.queReportForDays( time,pageSize, pageIndex);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext queReportForPlatform(HttpRequestContext request) {
		try 
		{
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String time =  StringUtil.isEmpty(jsonobj.get("time")) ? null:jsonobj.get("time").toString();
			String rt = quesMainService.queReportForPlatform( time,pageSize, pageIndex);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext queReportForCity(HttpRequestContext request) {
		try 
		{
			int pageSize = request.getInt("rows");
			int pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			String json = request.getParameter("json");
			JSONObject jsonobj = new JSONObject(json);
			String time =  StringUtil.isEmpty(jsonobj.get("time")) ? null:jsonobj.get("time").toString();
			String rt = quesMainService.queReportForCity( time,pageSize, pageIndex);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
}
