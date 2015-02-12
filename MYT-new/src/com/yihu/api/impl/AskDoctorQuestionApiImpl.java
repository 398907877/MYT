package com.yihu.api.impl;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;















/*
import com.common.cipher.BASE64Decoder;
import com.common.cipher.BASE64Encoder;*/
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.BalanceReturnBean;
import com.yihu.account.api.ChargeReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.account.api.ReturnValueBean;
import com.yihu.api.MsgApi;
import com.yihu.api.MsgManagerService;
import com.yihu.api.api.AskDoctorQuestionApi;
import com.yihu.baseinfo.api.CommonApi;
import com.yihu.baseinfo.api.DoctorAccountApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.baseinfo.api.HosDeptApi;
import com.yihu.baseinfo.api.HospitalApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.IAnswerRecordService;
import com.yihu.myt.service.service.IAskDoctorNewInterfaceV1Service;
import com.yihu.myt.service.service.IAssayResultMainService;
import com.yihu.myt.service.service.ICloseMainService;
import com.yihu.myt.service.service.IDiseaseService;
import com.yihu.myt.service.service.IDocSubCloseQuesService;
import com.yihu.myt.service.service.IDoctorDefaultAuthService;
import com.yihu.myt.service.service.IDoctorFreeCountEditService;
import com.yihu.myt.service.service.IDoctorFreeCountService;
import com.yihu.myt.service.service.IOpenAuthService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesCloseWaterService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.IFilesService;
import com.yihu.myt.service.service.IDepartmentsService;
import com.yihu.myt.service.service.IPatientService;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.IQuesEvaluateService;
import com.yihu.myt.service.service.ICreditsRecordService;
import com.yihu.myt.service.service.IQuesReplyMutualService;
import com.yihu.myt.service.service.IReplyService;
import com.yihu.myt.service.service.IUserFreeCountService;
import com.yihu.myt.service.service.impl.QuesMainService;
import com.yihu.myt.vo.AnswerRecordVo;
import com.yihu.myt.vo.AssayResultMainVo;
import com.yihu.myt.vo.CloseMainVo;
import com.yihu.myt.vo.DepartmentsVo;
import com.yihu.myt.vo.DiseaseVo;
import com.yihu.myt.vo.DocSubCloseQuesVo;
import com.yihu.myt.vo.DoctorDefaultAuthVo;
import com.yihu.myt.vo.DoctorFreeCountVo;
import com.yihu.myt.vo.DoctorVo;
import com.yihu.myt.vo.OpenAuthVo;
import com.yihu.myt.vo.PatientVo;
import com.yihu.myt.vo.QuesCloseWaterVo;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.myt.vo.FilesVo;
import com.yihu.myt.vo.ConsumerOrdersVo;
import com.yihu.myt.vo.QuesEvaluateVo;
import com.yihu.myt.vo.CreditsRecordVo;
import com.yihu.myt.vo.QuesReplyMutualVo;
import com.yihu.myt.vo.ReplyVo;
import com.yihu.myt.vo.UserFreeCountVo;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.LoggerUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.smsgw.api.PublicForSmsService;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

public class AskDoctorQuestionApiImpl implements AskDoctorQuestionApi {
	
	private static  org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AskDoctorQuestionApiImpl.class);

	private static IPostService postService = Ioc.get(IPostService.class);

	private static IQuesMainService quesMainService = Ioc
			.get(IQuesMainService.class);
	private static IPatientService patientService = Ioc
			.get(IPatientService.class);
	private static IFilesService filesService = Ioc.get(IFilesService.class);
	private static IConsumerOrdersService consumerOrdersService = Ioc
			.get(IConsumerOrdersService.class);
	private static IDepartmentsService departmentsService = Ioc
			.get(IDepartmentsService.class);
	private static IQuesEvaluateService quesEvaluateService = Ioc
			.get(IQuesEvaluateService.class);
	private static ICreditsRecordService creditsRecordService = Ioc
			.get(ICreditsRecordService.class);
	private static IReplyService replyService = Ioc.get(IReplyService.class);
	private static IQuesCloseWaterService quesCloseWaterService = Ioc
			.get(IQuesCloseWaterService.class);
	private static IQuesReplyMutualService quesReplyMutualService = Ioc
			.get(IQuesReplyMutualService.class);
	private static IAssayResultMainService assayResultMainService = Ioc
			.get(IAssayResultMainService.class);
	private static IDocSubCloseQuesService docSubCloseQuesService = Ioc
			.get(IDocSubCloseQuesService.class);
	private static IAnswerRecordService answerRecordService = Ioc
			.get(IAnswerRecordService.class);
	private static IDiseaseService diseaseService = Ioc
			.get(IDiseaseService.class);
	private static IDoctorFreeCountService doctorFreeCountService = Ioc
			.get(IDoctorFreeCountService.class);

	private static IUserFreeCountService userFreeCountService = Ioc
			.get(IUserFreeCountService.class);
	private static IAskDoctorNewInterfaceV1Service askDoctorNewInterfaceService = Ioc
			.get(IAskDoctorNewInterfaceV1Service.class);
	
	
	public static String smbPrefix = "smb://UpImg:www.yihu.com@10.0.0.75";
	// public static String httpPrefix = "http://3g.yihu.com";
	public static String httpPrefix = "http://61.131.3.201";
	public static String doctorDir = "/upfile/doctor/";
	public static String hospitalDir = "/upfile/hospital/";
	public static String tjtDir = "/upfile/tjt/";
	public static String userUpFile = "/upfilea_ccessory/ZiXun";
	public static String upfilea_ccessory = "/upfile/ZiXun";

	public static void main(String[] args) throws Exception {
		JSONObject json = new JSONObject();
		String str = "{'deptID':'','queID':'143799','doctorID':'710040074'}";
		String que = "{'doctorID':'710028096','userID':'12423803','deptID':'0','content':'test����1','tipPhone':'15659180353','sourceType':'0','handid':'1','platform':'0','diseaseStr':'','filesCount':'0','userName':'671044440','price':'2500','deptList':'6002','quesType':'0','docFreeID':'0','feeTemplateId':'393','fileMess':[],'patientMess':{'patname':'','patsex':'1','patprvid':'19','patprvname':'�㶫ʡ','patcityid':'201','patcityname':'�ع���','patbirth':'2003-07-17'},'pathogenesisTime':'2014-07-15','illness':'test����','treatmentExperience':'test����001'}";
		String quesFreeze = "{'userID':'11386912','quID':'192488','OperatorId':'1000000','OperatorName':'��վ'}";
		String overQuertion = "{'quesID':'230660','cardID':'671475420','userID':'12886178','doctorID':'710096895','pleasedLevel':'0' ,'OperatorId':'1000000','OperatorName':'��վ'}";
		String addDoctorFreeCount = "{'doctorUid':'710093264','freeCount':'4'}";
		String getQuesListByUser="{\"userID\":12582284,\"listType\":1,\"start\":0,\"pageSize\":3}";
		//      OperatorId OperatorName
		String  getDoctorConsultingListV4 = "{'pageSize':'20','doctorUid':'710090646','twoDeptID':'7103','pageIndex':'0','quesType':'1'}";
		String saveQuestionNoFreezeV2 = "{'doctorID':'710028096','userID':'12423803','deptID':'0','content':'��������������������������123','tipPhone':'15659180353','sourceType':'0','handid':'1','platform':'0','diseaseStr':'','filesCount':'0','userName':'671044440','price':'0','deptList':'6002','quesType':'1','docFreeID':'6879','feeTemplateId':'393','fileMess':[],'patientMess':{'patname':'','patsex':'1','patprvid':'13','patprvname':'����ʡ','patcityid':'118','patcityname':'������','patbirth':'2003-08-20'},'pathogenesisTime':'1231','illness':'1312','treatmentExperience':'1231'}";
		String saveQV2 = "{'quesType':'0','doctorID':710003065,'tipPhone':'15005902791','userID':12423803,'deptID':7000474,'userName':'671044440','content':'����ʹ��˿��������','sourceType':0,'handid':1,'filesCount':2,'platform':0,'diseaseStr':'gxy','price':15,'feeTemplateId':5397,'deptList':'','docFreeID':0,'patientMess':{'patname':'�û�','patsex':1,'patprvid':1,'patprvname':'������','patcityid':1,'patcityname':'1','patbirth':0},'pathogenesisTime':'���24Сʱ','illness':'��','treatmentExperience':'123123','fileMess':[{'premark':'premark','status':'1','createTime':'2014-12-2','oldName':'QQͼƬ20141128151109.jpg','newName':'QQͼƬ20141128151109.jpg','typeID':'4','objTypeID':'1','path':'http://f1.yihuimg.com/TFS/upfile/ZIXUN/201412/02/002592_1417490389957_fullsize.jpg','operatorID':'1000000','size':'31'},{'premark':'premark','status':'1','createTime':'2014-12-2','oldName':'QQͼƬ20141128151109.jpg','newName':'QQͼƬ20141128151109.jpg','typeID':'4','objTypeID':'1','path':'http://f1.yihuimg.com/TFS/upfile/ZIXUN/201412/02/002593_1417490393545_fullsize.jpg','operatorID':'1000000','size':'31'}]}";
		String overQuertionV2 = " {'quesID':'267259','cardID':'671194735','userID':'12582284','doctorID':'710088481','pleasedLevel':'1','OperatorId':'1000008','OperatorName':'ҽԺ΢��'}";
 		String getQuesListByUserV2 = "{\"userIDs\":'12582284,13333',\"listType\":1,\"start\":0,\"pageSize\":3}";
		String getQueReplyByQuesID = "{'quID':313871,'pageSize':100,'pageIndex':0,'userType':0}";
  		AskDoctorQuestionApiImpl api = new AskDoctorQuestionApiImpl();
		InterfaceMessage msg = new InterfaceMessage();
		msg.setParam(overQuertionV2);
		
		msg.setAuthInfo("{'ClientId':1000000}");
		String  rt = api.overQuertionV2(msg);
		//String rt = api.saveQuestionNoFreezeV2(msg);
		System.out.println(rt);
		

	}
	
	/**
	 * 
	 * @param �������� ��  ͬ�����ű�����ݣ�   ���Ҳ鿴 ���ñ�  set��Ӧ����
	 * @return
	 */
	
	
	private static IDoctorDefaultAuthService doctorDefaultAuthService = Ioc
			.get(IDoctorDefaultAuthService.class);
	
	private static IOpenAuthService openAuthService = Ioc
			.get(IOpenAuthService.class);
	
	
	
	public String saveQuestionNoFreeze(InterfaceMessage msg) {

		String tag = "saveQuestionNoFreezeV3";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			// System.out.print(msg.getParam());
			Integer doctorID = json.get("doctorID") == null ? 0 : json
					.getInt("doctorID");
			String tipPhone = json.get("tipPhone") == null ? null : json
					.getString("tipPhone");
			Integer userID = json.get("userID") == null ? 0 : json
					.getInt("userID");
			Integer deptID = json.get("deptID") == null ? 0 : json
					.getInt("deptID");
			String userName = json.get("userName") == null ? null : json
					.getString("userName");
			String content = json.get("content") == null ? null : json
					.getString("content");
			Integer sourceType = json.get("sourceType") == null ? 0 : json
					.getInt("sourceType");
			Integer filesCount = json.get("filesCount") == null ? 0 : json
					.getInt("filesCount");
			Integer handid = json.get("handid") == null ? 0 : json
					.getInt("handid");
			Integer Platform = json.get("platform") == null ? 0 : json
					.getInt("platform");
			String diseaseStr = json.get("diseaseStr") == null ? null : json
					.getString("diseaseStr");
			Integer price = json.get("price") == null ? 0 : json.getInt("price");
			Integer feeTemplateId = json.get("feeTemplateId") == null ? 0 : json
					.getInt("feeTemplateId");
			String DeptList = json.get("deptList") == null ? null : json
					.getString("deptList");
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer docFreeID = json.get("docFreeID") == null ? 0 : json
					.getInt("docFreeID");
			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");
			
			String pathogenesisTime = json.get("pathogenesisTime") == null ? null : json
					.getString("pathogenesisTime");
			String illness = json.get("illness") == null ? null : json
					.getString("illness");
			String treatmentExperience = json.get("treatmentExperience") == null ? null : json
					.getString("treatmentExperience");
		
			Integer coVoID = 0;
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorService= Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
					CommonApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.HosDeptApi hosDeptApi = Rpc.get(
					HosDeptApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String deptIds = "";
			

			QuesMainVo qvo = new QuesMainVo();
			PatientVo pat = new PatientVo();
			qvo.setQUESMAIN_Content(content);
			qvo.setASK_UserID(userID);
			int qsCon = quesMainService.querySameQueCont(qvo);
			if (qsCon > 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10003, "������ѯ�������Ѵ��ڡ�");
			}
			if (docFreeID > 0) {
				UserFreeCountVo uFreeCount = new UserFreeCountVo();
				uFreeCount.setUserId(userID);
				uFreeCount.setUfcId(docFreeID);
				uFreeCount.setIfOver(0);
				uFreeCount = userFreeCountService
						.queryUserFreeCount(uFreeCount);
				if (uFreeCount == null) {
					return WsUtil
							.getRetVal(msg.getOutType(), -12000, "�������Ѿ�����.");
				} else {
					uFreeCount.setIfOver(1);
					uFreeCount.setEndTime(DateUtil.dateFormat(new Date()));
					int rt = userFreeCountService.updateUserFree(uFreeCount);
					if (rt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10005,
								"������������ʧ��.");
					}
					UserFreeCountVo ufc = new UserFreeCountVo();
					ufc.setUfcId(docFreeID);
					ufc = userFreeCountService.queryUserFree(ufc);
					DoctorFreeCountVo dvo = new DoctorFreeCountVo();
					dvo.setDfcId(ufc.getDfcId());
					dvo = doctorFreeCountService.queryDoctorFreeCount(dvo);
					dvo.setOccupyFreeCount(dvo.getOccupyFreeCount() - 1);
					dvo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					int rtd = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dvo);
					if (rtd < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10006,
								"������������ʧ��.");
					}
				}
			}
			String authInfoStr = msg.getAuthInfo();
			String clientId = "99999";
			if (StringUtil.isEmpty(authInfoStr)) {
				clientId  = Platform.toString();
			}
			JSONObject authInfo = JSONObject.fromObject(authInfoStr); // ���뷽��Ϣ
			//clientId = StringUtil.isEmpty(authInfo.get("ClientId")) ? null : authInfo.getString("ClientId");
			qvo.setQD_PathogenesisTime(pathogenesisTime); //����ʱ��
			qvo.setQD_Illness(illness);//��Ҫ��֢
			qvo.setQD_TreatmentExperience(treatmentExperience); //���ﾭ��
			if(feeTemplateId>0 && quesType !=1){
				InterfaceMessage iMsg = new InterfaceMessage();
				JSONObject msgParam = new JSONObject();
				msgParam.put("feeTemplateId", feeTemplateId);
				iMsg.setParam(msgParam.toString());
				JSONObject dcPrice = JSONObject.fromObject(doctorService.queryFeeTemplateById(iMsg));
				qvo.setQD_DoctorGetPrice(dcPrice.getInt("doctorPrice"));
				qvo.setQD_Price(dcPrice.getInt("price"));
			}else{
				qvo.setQD_DoctorGetPrice(0);
	 		}
			qvo.setQD_ClientId(clientId);
			qvo.setQD_TipPhone(tipPhone);
			qvo.setASK_SourceAskID(0);
			qvo.setQD_DiseaseStr(diseaseStr);
			qvo.setQD_AskedCount(0);
			qvo.setQD_Ating(0);
			qvo.setQD_CheckManID(0);
			qvo.setQD_ClickCount(0);
			qvo.setQD_Credits(0);
			qvo.setQD_DocReplayCount(0);
			qvo.setQD_HandleID(1);
			qvo.setQD_IsAppAttend(0);
			qvo.setQD_IsReplay(0);
			qvo.setQD_IsUserReplay(1);
			qvo.setQD_Reason("");
			qvo.setQD_Status(0);
			qvo.setQD_SupplyContent("");
			qvo.setQUESMAIN_IsOpen(1);
			qvo.setQUESMAIN_Platform(Platform); // ����
			qvo.setQUESMAIN_Status(1);
			qvo.setQD_ShowStatus(",0,");
			qvo.setQD_CheckTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_DiseaseIDS("");
			qvo.setASK_UserID(userID);
			qvo.setASK_UserName(userName);
			qvo.setQUESMAIN_Content(content);
			qvo.setQUESMAIN_CreateTime(DateUtil.dateFormat(new Date()));
			qvo.setQUESMAIN_Title("");
			qvo.setQD_FilesCount(filesCount);
			qvo.setQD_QuesType(quesType);
			qvo.setQD_DocFreeID(docFreeID);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(mf)); // �������2����aʧЧʱ��+100
			qvo.setQD_OrdersId("");
			qvo.setQD_OrdersStatus(0); // δ���ɶ���.set����֧��s
			qvo.setQD_Price(0);
			qvo.setQD_SourceType(sourceType); // 1����2����a
			qvo.setASK_DoctorID(0);
			qvo.setQD_AskDeptID(0);
			qvo.setQD_DeptIDS("");
			qvo.setQD_DeptTwoIDS("");
			// �������ݱ���
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);

			if (patID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10001, "���뻼��ʧ�ܡ�");
			}
			qvo.setASK_PatientID(patID);
			qvo.setQD_FilesCount(filesCount);
			// ��������
			FilesVo file;
			String filsIds = "";
			if (filesCount > 0) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("createTime"));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10009,
								"��������ʧ�ܡ�");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}

			// qvo.setASK_DepartID(String.valueOf(rt));
			ConsumerOrdersVo coVo = new ConsumerOrdersVo();
			if (sourceType == 0 && doctorID > 0)// ָ��ҽ��
			{
				ServiceResult<String> rtdc = doctorInfoApi
						.getComplexDoctorByUid(doctorID);
				if (rtdc.getCode() > 0) {
					JSONObject dc = JSONObject.fromObject(rtdc.getResult());
					qvo.setASK_SourceAskID(dc.getInt("doctorUid"));
					qvo.setASK_DoctorID(dc.getInt("doctorUid"));
					qvo.setQD_CheckStatus(1);
					qvo.setQD_DeptIDS(dc.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(dc.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(dc.getInt("hosDeptId"));// ҽ�����ڿ���
					qvo.setQD_Price(price);
					qvo.setQD_OrdersStatus(1);
					if (price > 0 || quesType == 1) {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.DAY_OF_YEAR, 2);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// �շ��������24Сʱ
					} else {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.YEAR, 100);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// 72Сʱ��ȥ
						// ��������⣩
						// ������ˮ
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -11111,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else if ((sourceType == 2 || sourceType == 3) && deptID > 0) {
				// ָ��������ѯ
				qvo.setQD_CheckStatus(1);
				qvo.setQD_Price(price);
				ServiceResult<String> hos = hosDeptApi
						.queryComplexHosDeptById(deptID);
				if (hos.getCode() > 0) {
					JSONObject hospt = JSONObject.fromObject(hos.getResult());
					qvo.setASK_SourceAskID(deptID);
					qvo.setQD_DeptIDS(hospt.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(hospt.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(deptID);// ָ���Ŀ�����ѯ���ڿ���
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());
					rightNow.add(Calendar.DAY_OF_YEAR, 2);
					Date dt1 = rightNow.getTime();
					qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dt1));// �շ�����24Сʱ����
					if (price > 0) {// ��������շ�������������ˮ����
						qvo.setQD_OrdersStatus(1); // δ֧��
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -11111,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else {
				String bigDeptIds = ",";
				String ltDeptIds = ",";
				JSONObject dpjson = new JSONObject();
				dpjson.put("deptIds", DeptList);
				if (DeptList.equals("") || DeptList == null
						|| DeptList.equals("0")) {
					qvo.setQD_CheckStatus(0);
				} else {
					ServiceResult<String> rtdepts = commonApi
							.getSmallDepartmentList(dpjson.toString());
					if (rtdepts.getCode() > 0) {
						JSONArray depts = JSONArray.fromObject(rtdepts
								.getResult());
						DepartmentsVo deptVo;
						for (int i = 0; i < depts.size(); i++) {
							deptVo = new DepartmentsVo();
							deptVo.setASK_DepartIDOne(depts.getJSONObject(i)
									.getInt("deptSn"));
							deptVo.setASK_DepartNameOne(depts.getJSONObject(i)
									.getString("bigName"));
							deptVo.setASK_DepartIDTwo(depts.getJSONObject(i)
									.getString("deptId"));
							deptVo.setASK_DepartNameTwo(depts.getJSONObject(i)
									.getString("name"));
							deptVo.setDEPART_OperatorType(0);
							deptVo.setDEPART_CreateTime(DateUtil
									.dateFormat(new Date()));
							int rtdp = departmentsService
									.insertDepartments(deptVo);
							deptIds = deptIds + rtdp + ",";
							bigDeptIds = bigDeptIds
									+ depts.getJSONObject(i).getInt("deptSn")
									+ ",";
							ltDeptIds = ltDeptIds
									+ depts.getJSONObject(i)
											.getString("deptId") + ",";
						}
						qvo.setQD_CheckStatus(1);
					}
				}
				qvo.setQD_DeptIDS(bigDeptIds);
				qvo.setQD_DeptTwoIDS(ltDeptIds);
			}

			qvo.setQD_HandleID(handid);
			qvo.setQD_SourceType(sourceType);

			// ��������
			int quID = quesMainService.insertQuesMain(qvo);
			if (quID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10002, "��������ʧ�ܡ�");
			}
			
			System.out.println("88888888888888888888888888888888888888888888888888888888888");
			
			//TODO  WUJIAJUN
			//��������ɹ���  ��   ��߲�������
			
			//1. ����ҽ��id ȥ��ѯ ���ñ�  --������ ȡ��O C  ��������� set  --û������ set O 
			//2.������ ���� ���ű��� 
			
			try {
				
				DoctorDefaultAuthVo   confvo = new  DoctorDefaultAuthVo();
				confvo.setDoctorId(String.valueOf(doctorID));
				List<DoctorDefaultAuthVo>    list=     doctorDefaultAuthService.queryDoctorDefaultAuthListByDoctorId(confvo);
				
				
				
				OpenAuthVo  openvo = new OpenAuthVo();
				openvo.setQuesMainId(String.valueOf(quID));
				openvo.setDoctorId(String.valueOf(doctorID));
				
				if(list.size()==0){
					//Ĭ��ֵ  O
					openvo.setOpenAuthFlag("O");
					
				}else{
					//�����ֵ ȡ����
					openvo.setOpenAuthFlag(list.get(0).getOpenFlag());
					
				}
				
				//���浽open��
				openAuthService.insertOpenAuth(openvo);
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return WsUtil.getRetVal(msg.getOutType(), -10002, "���� ���� Ȩ�� ʧ�ܣ���");
			}
			
		
			System.out.println("9999999999999999999999999999999999999999999999999999999");
			
			
						
			if (price > 0) {
				// ������ˮ
				coVo.setASK_QuesID(quID);
				coVo.setASK_Content(content);
				coVo.setASK_QuesType(7);
				coVo.setASK_UserID(userID);
				coVo.setCO_CreateTime(DateUtil.dateFormat(new Date()));
				coVo.setCO_Price(price);
				coVo.setCO_Status(-2);
				coVo.setASK_DoctorID(doctorID);
				coVo.setASK_DeptID(deptID);
				coVo.setFeeTemplateId(feeTemplateId);
				if (sourceType == 2) {
					coVo.setCO_AskType(1);
				} else {
					coVo.setCO_AskType(0);
				}
				coVoID = consumerOrdersService.insertConsumerOrders(coVo);
				if (coVoID == 0) {
					return WsUtil
							.getRetVal(msg.getOutType(), -10003, "���ɶ���ʧ�ܡ�");
				}
				qvo.setQD_OrdersId(String.valueOf(coVoID));
				/*
				 * // �����û��˻���� ReturnValueBean ret =
				 * accountService.frozenWswyFee(userID, price, coVoID, "12",
				 * "01", operatorID, operatorName); if (ret.getCode() < 0) {
				 * return WsUtil.getRetVal(msg.getOutType(), -14444,
				 * ret.getMessage()); }
				 */
			} else {
				QuesMainVo qusVo = new QuesMainVo();
				qusVo.setQUESMAIN_ID(quID);
				qusVo.setQD_OrdersStatus(0);
				quesMainService.updateQuesMain(qusVo);
			}
			/*
			 * QuesMainVo qusVo = new QuesMainVo(); qusVo.setQUESMAIN_ID(quID);
			 * qusVo.setQD_OrdersStatus(2);
			 * quesMainService.updateQuesMain(qusVo);
			 */
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(quID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���¸�������ʧ�ܡ�");
				}
			}

			// ���������ѯ���ű�
			if (StringUtil.isNotEmpty(deptIds)) {
				deptIds = StringUtils.substringBeforeLast(deptIds, ",");
				DepartmentsVo dVo = new DepartmentsVo();
				dVo.setASK_QuesID(quID);
				int dert = departmentsService.updateDepartmentsForQuesID(dVo,
						deptIds);
				if (dert < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���������ѯ�����б�ʧ�ܡ�");
				}
			}
			// ���¶�����ID����
			/*
			 * ConsumerOrdersVo cVo = new ConsumerOrdersVo(); if (coVoID != 0) {
			 * cVo.setASK_QuesID(quID); cVo.setCO_ID(coVoID);
			 * cVo.setCO_Status(0); int rtcon = consumerOrdersService
			 * .updateConsumerOrdersByCondition(cVo); if (rtcon < 0) { return
			 * WsUtil.getRetVal(msg.getOutType(), -14444, "���¶�������ʧ�ܡ�");
			 * 
			 * } }
			 */
			if (price == 0) {
				QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(quID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(0);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}

			JSONObject qus = new JSONObject();
			qus.put("quertionID", quID);
			JSONObject rtJs = new JSONObject();
			rtJs.put("Code", 10000);
			rtJs.put("Message", "�ɹ�");
			JSONArray rtJr = new JSONArray();
			rtJr.add(qus);
			rtJs.put("Result", rtJr.toString());
			return rtJs.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, qus.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	
	
	
	
	
	

	/**
	 * ���Ᵽ��
	 * 
	 * @param quesContent
	 * @param userId
	 * @return
	 */
	public ServiceResult<String> saveQuertion(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "�������޷��رա�");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			Integer doctorID = json.get("doctorID") == null ? 0 : json
					.getInt("doctorID");
			Integer userID = json.get("userID") == null ? 0 : json
					.getInt("userID");
			Integer deptID = json.get("deptID") == null ? 0 : json
					.getInt("deptID");
			Integer operatorID = json.get("OperatorId") == null ? 0 : json
					.getInt("OperatorId");
			String operatorName = json.get("OperatorName") == null ? null
					: json.getString("OperatorName");
			String userName = json.get("userName") == null ? null : json
					.getString("userName");
			String content = json.get("content") == null ? null : json
					.getString("content");
			Integer sourceType = json.get("sourceType") == null ? 0 : json
					.getInt("sourceType");
			Integer filesCount = json.get("filesCount") == null ? 0 : json
					.getInt("filesCount");
			Integer handid = json.get("handid") == null ? 0 : json
					.getInt("handid");
			Integer Platform = json.get("platform") == null ? 0 : json
					.getInt("platform");
			String diseaseStr = json.get("diseaseStr") == null ? null : json
					.getString("diseaseStr");
			Integer price = json.get("price") == null ? null : json.getInt("price");
			Integer feeTemplateId = json.get("feeTemplateId") == null ? null : json
					.getInt("feeTemplateId");
			String DeptList = json.get("DeptList") == null ? null : json
					.getString("DeptList");

			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");
			int coVoID = 0;
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
					CommonApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.HosDeptApi hosDeptApi = Rpc.get(
					HosDeptApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			String deptIds = "";
			QuesMainVo qvo = new QuesMainVo();
			PatientVo pat = new PatientVo();
			qvo.setQUESMAIN_Content(content);
			qvo.setASK_UserID(userID);
			int qsCon = quesMainService.querySameQueCont(qvo);
			if (qsCon > 0) {
				result.setCode(-14444);
				result.setMessage("������ѯ�������Ѵ��ڡ�");
				result.setResult("");
				return result;
			}
			qvo.setASK_SourceAskID(0);
			qvo.setQD_DiseaseStr(diseaseStr);
			qvo.setQD_AskedCount(0);
			qvo.setQD_Ating(0);
			qvo.setQD_CheckManID(0);
			qvo.setQD_ClickCount(0);
			qvo.setQD_Credits(0);
			qvo.setQD_DocReplayCount(0);
			qvo.setQD_HandleID(1);
			qvo.setQD_IsAppAttend(0);
			qvo.setQD_IsReplay(0);
			qvo.setQD_IsUserReplay(1);
			qvo.setQD_Reason("");
			qvo.setQD_Status(0);
			qvo.setQD_SupplyContent("");
			qvo.setQUESMAIN_IsOpen(1);
			qvo.setQUESMAIN_Platform(Platform); // ����
			qvo.setQUESMAIN_Status(1);
			qvo.setQD_ShowStatus(",0,");
			qvo.setQD_CheckTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_DiseaseIDS("");
			qvo.setASK_UserID(userID);
			qvo.setASK_UserName(userName);
			qvo.setQUESMAIN_Content(content);
			qvo.setQUESMAIN_CreateTime(DateUtil.dateFormat(new Date()));
			qvo.setQUESMAIN_Title("");
			qvo.setQD_FilesCount(filesCount);
			qvo.setASK_UserID(userID);

			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(mf)); // �������2����aʧЧʱ��+100
			qvo.setQD_OrdersId("");
			qvo.setQD_OrdersStatus(0); // δ���ɶ���.set����֧��s
			qvo.setQD_Price(0);
			qvo.setQD_SourceType(sourceType); // 1����2����a
			qvo.setASK_DoctorID(0);
			qvo.setQD_AskDeptID(0);
			qvo.setQD_DeptIDS("");
			qvo.setQD_DeptTwoIDS("");
			// �������ݱ���
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);

			if (patID < 0) {
				result.setCode(-14444);
				result.setMessage("���뻼��ʧ��");
				result.setResult("");
				return result;
			}
			qvo.setASK_PatientID(patID);
			qvo.setQD_FilesCount(filesCount);
			// ��������
			FilesVo file;
			String filsIds = "";

			if (filesCount > 0) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"Status"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("CreateTime"));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"OldName"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("NewName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"TypeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"ObjTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"Path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("Size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"OperatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						result.setCode(-14444);
						result.setMessage("��������ʧ��");
						result.setResult("");
						return result;
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}

			// qvo.setASK_DepartID(String.valueOf(rt));
			ConsumerOrdersVo coVo = new ConsumerOrdersVo();
			if (sourceType == 0 && doctorID > 0)// ָ��ҽ��
			{
				ServiceResult<String> rtdc = doctorInfoApi
						.getComplexDoctorByUid(doctorID);
				if (rtdc.getCode() > 0) {
					JSONObject dc = JSONObject.fromObject(rtdc.getResult());
					qvo.setASK_SourceAskID(dc.getInt("doctorUid"));
					qvo.setASK_DoctorID(dc.getInt("doctorUid"));
					qvo.setQD_CheckStatus(1);
					qvo.setQD_DeptIDS(dc.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(dc.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(dc.getInt("hosDeptId"));// ҽ�����ڿ���
					qvo.setQD_Price(price);
					qvo.setQD_OrdersStatus(1);
					if (price > 0) {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.DAY_OF_YEAR, 2);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// �շ��������24Сʱ
					} else {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.YEAR, 100);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// 72Сʱ��ȥ
						// ��������⣩
						// ������ˮ
					}
				} else {
					result.setCode(-14444);
					result.setMessage("ָ��ҽ����Ϣ������");
					result.setResult("");
					return result;
				}
			} else if (sourceType == 2 && deptID > 0) {
				// ָ��������ѯ
				qvo.setQD_CheckStatus(1);
				qvo.setQD_Price(price);
				ServiceResult<String> hos = hosDeptApi
						.queryComplexHosDeptById(deptID);
				if (hos.getCode() > 0) {
					JSONObject hospt = JSONObject.fromObject(hos.getResult());
					qvo.setASK_SourceAskID(deptID);
					qvo.setQD_DeptIDS(hospt.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(hospt.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(deptID);// ָ���Ŀ�����ѯ���ڿ���
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());
					rightNow.add(Calendar.DAY_OF_YEAR, 2);
					Date dt1 = rightNow.getTime();
					qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dt1));// �շ�����24Сʱ����
					if (price > 0) {// ��������շ�������������ˮ����
						qvo.setQD_OrdersStatus(1); // δ֧��
					}
				} else {
					result.setCode(-14444);
					result.setMessage("ָ��������Ϣ������");
					result.setResult("");
					return result;
				}
			} else {
				String bigDeptIds = ",";
				String ltDeptIds = ",";
				JSONObject dpjson = new JSONObject();
				dpjson.put("deptIds", DeptList);
				if (DeptList.equals("") || DeptList == null) {
					qvo.setQD_CheckStatus(0);
				} else {
					ServiceResult<String> rtdepts = commonApi
							.getSmallDepartmentList(dpjson.toString());
					if (rtdepts.getCode() > 0) {
						JSONArray depts = JSONArray.fromObject(rtdepts
								.getResult());
						DepartmentsVo deptVo;
						for (int i = 0; i < depts.size(); i++) {
							deptVo = new DepartmentsVo();
							deptVo.setASK_DepartIDOne(depts.getJSONObject(i)
									.getInt("deptSn"));
							deptVo.setASK_DepartNameOne(depts.getJSONObject(i)
									.getString("bigName"));
							deptVo.setASK_DepartIDTwo(depts.getJSONObject(i)
									.getString("deptId"));
							deptVo.setASK_DepartNameTwo(depts.getJSONObject(i)
									.getString("name"));
							deptVo.setDEPART_OperatorType(0);
							deptVo.setDEPART_CreateTime(DateUtil
									.dateFormat(new Date()));
							int rtdp = departmentsService
									.insertDepartments(deptVo);
							deptIds = deptIds + rtdp + ",";
							bigDeptIds = bigDeptIds
									+ depts.getJSONObject(i).getInt("deptSn")
									+ ",";
							ltDeptIds = ltDeptIds
									+ depts.getJSONObject(i)
											.getString("deptId") + ",";
						}
						qvo.setQD_CheckStatus(1);
					}
				}
				qvo.setQD_DeptIDS(bigDeptIds);
				qvo.setQD_DeptTwoIDS(ltDeptIds);
			}

			/* qvo.setQD_HandleID(handid); */
			qvo.setQD_SourceType(sourceType);

			// ��������
			int quID = quesMainService.insertQuesMain(qvo);
			if (quID < 0) {
				result.setCode(-14444);
				result.setMessage("��������ʧ��");
				result.setResult("");
				return result;
			}
			if (price > 0) {
				// ������ˮ
				coVo.setASK_Content(content);
				coVo.setASK_QuesType(7);
				coVo.setASK_UserID(userID);
				coVo.setCO_CreateTime(DateUtil.dateFormat(new Date()));
				coVo.setCO_Price(price);
				coVo.setCO_Status(-2);
				coVo.setASK_DoctorID(doctorID);
				coVo.setASK_DeptID(deptID);
				coVo.setFeeTemplateId(feeTemplateId);
				if (sourceType == 2) {
					coVo.setCO_AskType(1);
				} else {
					coVo.setCO_AskType(0);
				}
				coVoID = consumerOrdersService.insertConsumerOrders(coVo);
				if (coVoID == 0) {
					result.setCode(-14444);
					result.setMessage("���ɶ���ʧ��");
					result.setResult("");
					return result;
				}

				qvo.setQD_OrdersId(String.valueOf(coVoID));
				// �����û��˻����
				ReturnValueBean ret = accountService.frozenWswyFee(userID,
						price, coVoID, "12", "01", operatorID, operatorName);
				if (ret.getCode() < 0) {
					result.setCode(-14444);
					result.setMessage(ret.getMessage());
					result.setResult("");
					return result;
				}
				QuesMainVo qusVo = new QuesMainVo();
				qusVo.setQUESMAIN_ID(quID);
				qusVo.setQD_OrdersStatus(2);
				int rt = quesMainService.updateQuesMain(qusVo);
				if (rt < 0) {
					result.setCode(-14444);
					result.setMessage("��������ʧ��");
					result.setResult("");
					return result;
				}
			} else {
				QuesMainVo qusVo = new QuesMainVo();
				qusVo.setQUESMAIN_ID(quID);
				qusVo.setQD_OrdersStatus(0);
				quesMainService.updateQuesMain(qusVo);
			}
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(quID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					result.setCode(-14444);
					result.setMessage("���¸�������ʧ��");
					result.setResult("");
					return result;
				}
			}
			// ���������ѯ���ű�
			if (StringUtil.isNotEmpty(deptIds)) {
				deptIds = StringUtils.substringBeforeLast(deptIds, ",");
				DepartmentsVo dVo = new DepartmentsVo();
				dVo.setASK_QuesID(quID);
				int dert = departmentsService.updateDepartmentsForQuesID(dVo,
						deptIds);
				if (dert < 0) {
					result.setCode(-14444);
					result.setMessage("���������ѯ�����б�ʧ��");
					result.setResult("");
					return result;
				}
			}
			// ���¶�����ID����
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			if (coVoID != 0) {
				cVo.setASK_QuesID(quID);
				cVo.setCO_ID(coVoID);
				cVo.setCO_Status(0);
				int rtcon = consumerOrdersService
						.updateConsumerOrdersByCondition(cVo);
				if (rtcon < 0) {
					result.setCode(-14444);
					result.setMessage("���¶�������ʧ��");
					result.setResult("");
					return result;
				}
			}
			result.setCode(1);
			result.setMessage("�����ɹ�");
			JSONObject qus = new JSONObject();
			qus.put("quertionID", quID);
			result.setResult(qus.toString());
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * �������ⲻ����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String saveQuestionNoFreezebak(InterfaceMessage msg) {
		String tag = "saveQuestionNoFreeze";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			// System.out.print(msg.getParam());
			Integer doctorID = json.get("doctorID") == null ? 0 : json
					.getInt("doctorID");
			String tipPhone = json.get("tipPhone") == null ? null : json
					.getString("tipPhone");
			Integer userID = json.get("userID") == null ? 0 : json
					.getInt("userID");
			Integer deptID = json.get("deptID") == null ? 0 : json
					.getInt("deptID");
			String userName = json.get("userName") == null ? null : json
					.getString("userName");
			String content = json.get("content") == null ? null : json
					.getString("content");
			Integer sourceType = json.get("sourceType") == null ? 0 : json
					.getInt("sourceType");
			Integer filesCount = json.get("filesCount") == null ? 0 : json
					.getInt("filesCount");
			Integer handid = json.get("handid") == null ? 0 : json
					.getInt("handid");
			Integer Platform = json.get("platform") == null ? 0 : json
					.getInt("platform");
			String diseaseStr = json.get("diseaseStr") == null ? null : json
					.getString("diseaseStr");
			Integer price = json.get("price") == null ? 0 : json.getInt("price");
			Integer feeTemplateId = json.get("feeTemplateId") == null ? 0 : json
					.getInt("feeTemplateId");
			String DeptList = json.get("deptList") == null ? null : json
					.getString("deptList");
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer docFreeID = json.get("docFreeID") == null ? 0 : json
					.getInt("docFreeID");
			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");
			

			Integer coVoID = 0;
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
					CommonApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.HosDeptApi hosDeptApi = Rpc.get(
					HosDeptApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorService= Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String deptIds = "";
			

			QuesMainVo qvo = new QuesMainVo();
			PatientVo pat = new PatientVo();
			qvo.setQUESMAIN_Content(content);
			qvo.setASK_UserID(userID);
			int qsCon = quesMainService.querySameQueCont(qvo);
			if (qsCon > 0) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "������ѯ�������Ѵ��ڡ�");
			}
			if (docFreeID > 0) {
				UserFreeCountVo uFreeCount = new UserFreeCountVo();
				uFreeCount.setUserId(userID);
				uFreeCount.setUfcId(docFreeID);
				uFreeCount.setIfOver(0);
				uFreeCount = userFreeCountService
						.queryUserFreeCount(uFreeCount);
				if (uFreeCount == null) {
					return WsUtil
							.getRetVal(msg.getOutType(), -2000, "�������Ѿ�����.");
				} else {
					uFreeCount.setIfOver(1);
					uFreeCount.setEndTime(DateUtil.dateFormat(new Date()));
					int rt = userFreeCountService.updateUserFree(uFreeCount);
					if (rt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"������������ʧ��.");
					}
					UserFreeCountVo ufc = new UserFreeCountVo();
					ufc.setUfcId(docFreeID);
					ufc = userFreeCountService.queryUserFree(ufc);
					DoctorFreeCountVo dvo = new DoctorFreeCountVo();
					dvo.setDfcId(ufc.getDfcId());
					dvo = doctorFreeCountService.queryDoctorFreeCount(dvo);
					dvo.setOccupyFreeCount(dvo.getOccupyFreeCount() - 1);
					dvo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					int rtd = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dvo);
					if (rtd < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"������������ʧ��.");
					}
				}
			}
			String authInfoStr = msg.getAuthInfo();
			String clientId = "99999";
			if (StringUtil.isEmpty(authInfoStr)) {
				clientId  = Platform.toString();
			}
			JSONObject authInfo = JSONObject.fromObject(authInfoStr); // ���뷽��Ϣ
			clientId = StringUtil.isEmpty(authInfo.get("ClientId")) ? null : authInfo.getString("ClientId");
			
			
			if(feeTemplateId>0 && quesType !=1){
				InterfaceMessage iMsg = new InterfaceMessage();
				JSONObject msgParam = new JSONObject();
				msgParam.put("feeTemplateId", feeTemplateId);
				iMsg.setParam(msgParam.toString());
				JSONObject dcPrice = JSONObject.fromObject(doctorService.queryFeeTemplateById(iMsg));
				qvo.setQD_DoctorGetPrice(dcPrice.getInt("doctorPrice"));
				qvo.setQD_Price(dcPrice.getInt("price"));
				price = dcPrice.getInt("price");
			}else{
				qvo.setQD_DoctorGetPrice(0);
			}
			
			qvo.setQD_ClientId(clientId);
			qvo.setQD_TipPhone(tipPhone);
			qvo.setASK_SourceAskID(0);
			qvo.setQD_DiseaseStr(diseaseStr);
			qvo.setQD_AskedCount(0);
			qvo.setQD_Ating(0);
			qvo.setQD_CheckManID(0);
			qvo.setQD_ClickCount(0);
			qvo.setQD_Credits(0);
			qvo.setQD_DocReplayCount(0);
			qvo.setQD_HandleID(1);
			qvo.setQD_IsAppAttend(0);
			qvo.setQD_IsReplay(0);
			qvo.setQD_IsUserReplay(1);
			qvo.setQD_Reason("");
			qvo.setQD_Status(0);
			qvo.setQD_SupplyContent("");
			qvo.setQUESMAIN_IsOpen(1);
			qvo.setQUESMAIN_Platform(Platform); // ����
			qvo.setQUESMAIN_Status(1);
			qvo.setQD_ShowStatus(",0,");
			qvo.setQD_CheckTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_DiseaseIDS("");
			qvo.setASK_UserID(userID);
			qvo.setASK_UserName(userName);
			qvo.setQUESMAIN_Content(content);
			qvo.setQUESMAIN_CreateTime(DateUtil.dateFormat(new Date()));
			qvo.setQUESMAIN_Title("");
			qvo.setQD_FilesCount(filesCount);
			qvo.setQD_QuesType(quesType);
			qvo.setQD_DocFreeID(docFreeID);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(mf)); // �������2����aʧЧʱ��+100
			qvo.setQD_OrdersId("");
			qvo.setQD_OrdersStatus(0); // δ���ɶ���.set����֧��s
			qvo.setQD_Price(0);
			qvo.setQD_SourceType(sourceType); // 1����2����a
			qvo.setASK_DoctorID(0);
			qvo.setQD_AskDeptID(0);
			qvo.setQD_DeptIDS("");
			qvo.setQD_DeptTwoIDS("");
			// �������ݱ���
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);

			if (patID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "���뻼��ʧ�ܡ�");
			}
			qvo.setASK_PatientID(patID);
			qvo.setQD_FilesCount(filesCount);
			// ��������
			FilesVo file;
			String filsIds = "";
			if (filesCount > 0) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("createTime"));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"��������ʧ�ܡ�");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}

			// qvo.setASK_DepartID(String.valueOf(rt));
			ConsumerOrdersVo coVo = new ConsumerOrdersVo();
			if (sourceType == 0 && doctorID > 0)// ָ��ҽ��
			{
				ServiceResult<String> rtdc = doctorInfoApi
						.getComplexDoctorByUid(doctorID);
				if (rtdc.getCode() > 0) {
					JSONObject dc = JSONObject.fromObject(rtdc.getResult());
					qvo.setASK_SourceAskID(dc.getInt("doctorUid"));
					qvo.setASK_DoctorID(dc.getInt("doctorUid"));
					qvo.setQD_CheckStatus(1);
					qvo.setQD_DeptIDS(dc.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(dc.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(dc.getInt("hosDeptId"));// ҽ�����ڿ���
					qvo.setQD_Price(price);
					qvo.setQD_OrdersStatus(1);
					if (price > 0 || quesType == 1) {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.DAY_OF_YEAR, 2);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// �շ��������24Сʱ
					} else {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.YEAR, 100);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// 72Сʱ��ȥ
						// ��������⣩
						// ������ˮ
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else if ((sourceType == 2 || sourceType == 3) && deptID > 0) {
				// ָ��������ѯ
				qvo.setQD_CheckStatus(1);
				qvo.setQD_Price(price);
				ServiceResult<String> hos = hosDeptApi
						.queryComplexHosDeptById(deptID);
				if (hos.getCode() > 0) {
					JSONObject hospt = JSONObject.fromObject(hos.getResult());
					qvo.setASK_SourceAskID(deptID);
					qvo.setQD_DeptIDS(hospt.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(hospt.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(deptID);// ָ���Ŀ�����ѯ���ڿ���
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());
					rightNow.add(Calendar.DAY_OF_YEAR, 2);
					Date dt1 = rightNow.getTime();
					qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dt1));// �շ�����24Сʱ����
					if (price > 0) {// ��������շ�������������ˮ����
						qvo.setQD_OrdersStatus(1); // δ֧��
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else {
				String bigDeptIds = ",";
				String ltDeptIds = ",";
				JSONObject dpjson = new JSONObject();
				dpjson.put("deptIds", DeptList);
				if (DeptList.equals("") || DeptList == null
						|| DeptList.equals("0")) {
					qvo.setQD_CheckStatus(0);
				} else {
					ServiceResult<String> rtdepts = commonApi
							.getSmallDepartmentList(dpjson.toString());
					if (rtdepts.getCode() > 0) {
						JSONArray depts = JSONArray.fromObject(rtdepts
								.getResult());
						DepartmentsVo deptVo;
						for (int i = 0; i < depts.size(); i++) {
							deptVo = new DepartmentsVo();
							deptVo.setASK_DepartIDOne(depts.getJSONObject(i)
									.getInt("deptSn"));
							deptVo.setASK_DepartNameOne(depts.getJSONObject(i)
									.getString("bigName"));
							deptVo.setASK_DepartIDTwo(depts.getJSONObject(i)
									.getString("deptId"));
							deptVo.setASK_DepartNameTwo(depts.getJSONObject(i)
									.getString("name"));
							deptVo.setDEPART_OperatorType(0);
							deptVo.setDEPART_CreateTime(DateUtil
									.dateFormat(new Date()));
							int rtdp = departmentsService
									.insertDepartments(deptVo);
							deptIds = deptIds + rtdp + ",";
							bigDeptIds = bigDeptIds
									+ depts.getJSONObject(i).getInt("deptSn")
									+ ",";
							ltDeptIds = ltDeptIds
									+ depts.getJSONObject(i)
											.getString("deptId") + ",";
						}
						qvo.setQD_CheckStatus(1);
					}
				}
				qvo.setQD_DeptIDS(bigDeptIds);
				qvo.setQD_DeptTwoIDS(ltDeptIds);
			}

			qvo.setQD_HandleID(handid);
			qvo.setQD_SourceType(sourceType);

			// ��������
			int quID = quesMainService.insertQuesMain(qvo);
			if (quID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "��������ʧ�ܡ�");
			}
			if (price > 0) {
				// ������ˮ
				coVo.setASK_QuesID(quID);
				coVo.setASK_Content(content);
				coVo.setASK_QuesType(7);
				coVo.setASK_UserID(userID);
				coVo.setCO_CreateTime(DateUtil.dateFormat(new Date()));
				coVo.setCO_Price(price);
				coVo.setCO_Status(-2);
				coVo.setASK_DoctorID(doctorID);
				coVo.setASK_DeptID(deptID);
				coVo.setFeeTemplateId(feeTemplateId);
				if (sourceType == 2) {
					coVo.setCO_AskType(1);
				} else {
					coVo.setCO_AskType(0);
				}
				coVoID = consumerOrdersService.insertConsumerOrders(coVo);
				if (coVoID == 0) {
					return WsUtil
							.getRetVal(msg.getOutType(), -14444, "���ɶ���ʧ�ܡ�");
				}
				qvo.setQD_OrdersId(String.valueOf(coVoID));
				/*
				 * // �����û��˻���� ReturnValueBean ret =
				 * accountService.frozenWswyFee(userID, price, coVoID, "12",
				 * "01", operatorID, operatorName); if (ret.getCode() < 0) {
				 * return WsUtil.getRetVal(msg.getOutType(), -14444,
				 * ret.getMessage()); }
				 */
			} else {
				QuesMainVo qusVo = new QuesMainVo();
				qusVo.setQUESMAIN_ID(quID);
				qusVo.setQD_OrdersStatus(0);
				quesMainService.updateQuesMain(qusVo);
			}
			/*
			 * QuesMainVo qusVo = new QuesMainVo(); qusVo.setQUESMAIN_ID(quID);
			 * qusVo.setQD_OrdersStatus(2);
			 * quesMainService.updateQuesMain(qusVo);
			 */
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(quID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���¸�������ʧ�ܡ�");
				}
			}

			// ���������ѯ���ű�
			if (StringUtil.isNotEmpty(deptIds)) {
				deptIds = StringUtils.substringBeforeLast(deptIds, ",");
				DepartmentsVo dVo = new DepartmentsVo();
				dVo.setASK_QuesID(quID);
				int dert = departmentsService.updateDepartmentsForQuesID(dVo,
						deptIds);
				if (dert < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���������ѯ�����б�ʧ�ܡ�");
				}
			}
			// ���¶�����ID����
			/*
			 * ConsumerOrdersVo cVo = new ConsumerOrdersVo(); if (coVoID != 0) {
			 * cVo.setASK_QuesID(quID); cVo.setCO_ID(coVoID);
			 * cVo.setCO_Status(0); int rtcon = consumerOrdersService
			 * .updateConsumerOrdersByCondition(cVo); if (rtcon < 0) { return
			 * WsUtil.getRetVal(msg.getOutType(), -14444, "���¶�������ʧ�ܡ�");
			 * 
			 * } }
			 */
			if (price == 0) {
				QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(quID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(0);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}

			JSONObject qus = new JSONObject();
			qus.put("quertionID", quID);
			JSONObject rtJs = new JSONObject();
			rtJs.put("Code", 10000);
			rtJs.put("Message", "�ɹ�");
			JSONArray rtJr = new JSONArray();
			rtJr.add(qus);
			rtJs.put("Result", rtJr.toString());
			return rtJs.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, qus.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	/**
	 * �������ⲻ����
	 * 
	 * @param
	 * @param  ��ע�������v2�汾  ��Ҫ�滻�� ���� ���ŵ�
	 * @return
	 */
	public String saveQuestionNoFreezeV2(InterfaceMessage msg) {

		String tag = "saveQuestionNoFreezeV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			// System.out.print(msg.getParam());
			Integer doctorID = json.get("doctorID") == null ? 0 : json
					.getInt("doctorID");
			String tipPhone = json.get("tipPhone") == null ? null : json
					.getString("tipPhone");
			Integer userID = json.get("userID") == null ? 0 : json
					.getInt("userID");
			Integer deptID = json.get("deptID") == null ? 0 : json
					.getInt("deptID");
			String userName = json.get("userName") == null ? null : json
					.getString("userName");
			String content = json.get("content") == null ? null : json
					.getString("content");
			Integer sourceType = json.get("sourceType") == null ? 0 : json
					.getInt("sourceType");
			Integer filesCount = json.get("filesCount") == null ? 0 : json
					.getInt("filesCount");
			Integer handid = json.get("handid") == null ? 0 : json
					.getInt("handid");
			Integer Platform = json.get("platform") == null ? 0 : json
					.getInt("platform");
			String diseaseStr = json.get("diseaseStr") == null ? null : json
					.getString("diseaseStr");
			Integer price = json.get("price") == null ? 0 : json.getInt("price");
			Integer feeTemplateId = json.get("feeTemplateId") == null ? 0 : json
					.getInt("feeTemplateId");
			String DeptList = json.get("deptList") == null ? null : json
					.getString("deptList");
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer docFreeID = json.get("docFreeID") == null ? 0 : json
					.getInt("docFreeID");
			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");
			
			String pathogenesisTime = json.get("pathogenesisTime") == null ? null : json
					.getString("pathogenesisTime");
			String illness = json.get("illness") == null ? null : json
					.getString("illness");
			String treatmentExperience = json.get("treatmentExperience") == null ? null : json
					.getString("treatmentExperience");
		
			Integer coVoID = 0;
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorService= Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
					CommonApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.HosDeptApi hosDeptApi = Rpc.get(
					HosDeptApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String deptIds = "";
			

			QuesMainVo qvo = new QuesMainVo();
			PatientVo pat = new PatientVo();
			qvo.setQUESMAIN_Content(content);
			qvo.setASK_UserID(userID);
			int qsCon = quesMainService.querySameQueCont(qvo);
			if (qsCon > 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10003, "������ѯ�������Ѵ��ڡ�");
			}
			if (docFreeID > 0) {
				UserFreeCountVo uFreeCount = new UserFreeCountVo();
				uFreeCount.setUserId(userID);
				uFreeCount.setUfcId(docFreeID);
				uFreeCount.setIfOver(0);
				uFreeCount = userFreeCountService
						.queryUserFreeCount(uFreeCount);
				if (uFreeCount == null) {
					return WsUtil
							.getRetVal(msg.getOutType(), -12000, "�������Ѿ�����.");
				} else {
					uFreeCount.setIfOver(1);
					uFreeCount.setEndTime(DateUtil.dateFormat(new Date()));
					int rt = userFreeCountService.updateUserFree(uFreeCount);
					if (rt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10005,
								"������������ʧ��.");
					}
					UserFreeCountVo ufc = new UserFreeCountVo();
					ufc.setUfcId(docFreeID);
					ufc = userFreeCountService.queryUserFree(ufc);
					DoctorFreeCountVo dvo = new DoctorFreeCountVo();
					dvo.setDfcId(ufc.getDfcId());
					dvo = doctorFreeCountService.queryDoctorFreeCount(dvo);
					dvo.setOccupyFreeCount(dvo.getOccupyFreeCount() - 1);
					dvo.setLastChangeTime(DateUtil.dateFormat(new Date()));
					int rtd = doctorFreeCountService
							.updateDoctorFreeCountForUpFreeCount(dvo);
					if (rtd < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10006,
								"������������ʧ��.");
					}
				}
			}
			String authInfoStr = msg.getAuthInfo();
			String clientId = "99999";
			if (StringUtil.isEmpty(authInfoStr)) {
				clientId  = Platform.toString();
			}
			JSONObject authInfo = JSONObject.fromObject(authInfoStr); // ���뷽��Ϣ
			clientId = StringUtil.isEmpty(authInfo.get("ClientId")) ? null : authInfo.getString("ClientId");
			qvo.setQD_PathogenesisTime(pathogenesisTime); //����ʱ��
			qvo.setQD_Illness(illness);//��Ҫ��֢
			qvo.setQD_TreatmentExperience(treatmentExperience); //���ﾭ��
			if(feeTemplateId>0 && quesType !=1){
				InterfaceMessage iMsg = new InterfaceMessage();
				JSONObject msgParam = new JSONObject();
				msgParam.put("feeTemplateId", feeTemplateId);
				iMsg.setParam(msgParam.toString());
				JSONObject dcPrice = JSONObject.fromObject(doctorService.queryFeeTemplateById(iMsg));
				qvo.setQD_DoctorGetPrice(dcPrice.getInt("doctorPrice"));
				qvo.setQD_Price(dcPrice.getInt("price"));
				price = dcPrice.getInt("price");
			}else{
				qvo.setQD_DoctorGetPrice(0);
	 		}
			qvo.setQD_ClientId(clientId);
			qvo.setQD_TipPhone(tipPhone);
			qvo.setASK_SourceAskID(0);
			qvo.setQD_DiseaseStr(diseaseStr);
			qvo.setQD_AskedCount(0);
			qvo.setQD_Ating(0);
			qvo.setQD_CheckManID(0);
			qvo.setQD_ClickCount(0);
			qvo.setQD_Credits(0);
			qvo.setQD_DocReplayCount(0);
			qvo.setQD_HandleID(1);
			qvo.setQD_IsAppAttend(0);
			qvo.setQD_IsReplay(0);
			qvo.setQD_IsUserReplay(1);
			qvo.setQD_Reason("");
			qvo.setQD_Status(0);
			qvo.setQD_SupplyContent("");
			qvo.setQUESMAIN_IsOpen(1);
			qvo.setQUESMAIN_Platform(Platform); // ����
			qvo.setQUESMAIN_Status(1);
			qvo.setQD_ShowStatus(",0,");
			qvo.setQD_CheckTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
			qvo.setQD_DiseaseIDS("");
			qvo.setASK_UserID(userID);
			qvo.setASK_UserName(userName);
			qvo.setQUESMAIN_Content(content);
			qvo.setQUESMAIN_CreateTime(DateUtil.dateFormat(new Date()));
			qvo.setQUESMAIN_Title("");
			qvo.setQD_FilesCount(filesCount);
			qvo.setQD_QuesType(quesType);
			qvo.setQD_DocFreeID(docFreeID);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			mfNow.add(Calendar.YEAR, 100);
			Date mf = mfNow.getTime();
			qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(mf)); // �������2����aʧЧʱ��+100
			qvo.setQD_OrdersId("");
			qvo.setQD_OrdersStatus(0); // δ���ɶ���.set����֧��s
			qvo.setQD_Price(0);
			qvo.setQD_SourceType(sourceType); // 1����2����a
			qvo.setASK_DoctorID(0);
			qvo.setQD_AskDeptID(0);
			qvo.setQD_DeptIDS("");
			qvo.setQD_DeptTwoIDS("");
			// �������ݱ���
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);

			if (patID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10001, "���뻼��ʧ�ܡ�");
			}
			qvo.setASK_PatientID(patID);
			qvo.setQD_FilesCount(filesCount);
			// ��������
			FilesVo file;
			String filsIds = "";
			if (filesCount > 0) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("createTime"));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10009,
								"��������ʧ�ܡ�");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}

			// qvo.setASK_DepartID(String.valueOf(rt));
			ConsumerOrdersVo coVo = new ConsumerOrdersVo();
			if (sourceType == 0 && doctorID > 0)// ָ��ҽ��
			{
				ServiceResult<String> rtdc = doctorInfoApi
						.getComplexDoctorByUid(doctorID);
				if (rtdc.getCode() > 0) {
					JSONObject dc = JSONObject.fromObject(rtdc.getResult());
					qvo.setASK_SourceAskID(dc.getInt("doctorUid"));
					qvo.setASK_DoctorID(dc.getInt("doctorUid"));
					qvo.setQD_CheckStatus(1);
					qvo.setQD_DeptIDS(dc.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(dc.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(dc.getInt("hosDeptId"));// ҽ�����ڿ���
					qvo.setQD_Price(price);
					qvo.setQD_OrdersStatus(1);
					if (price > 0 || quesType == 1) {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.DAY_OF_YEAR, 2);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// �շ��������24Сʱ
					} else {
						Calendar dcNow = Calendar.getInstance();
						dcNow.setTime(new Date());
						dcNow.add(Calendar.YEAR, 100);
						Date dc1 = dcNow.getTime();
						qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// 72Сʱ��ȥ
						// ��������⣩
						// ������ˮ
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -11111,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else if ((sourceType == 2 || sourceType == 3) && deptID > 0) {
				// ָ��������ѯ
				qvo.setQD_CheckStatus(1);
				qvo.setQD_Price(price);
				ServiceResult<String> hos = hosDeptApi
						.queryComplexHosDeptById(deptID);
				if (hos.getCode() > 0) {
					JSONObject hospt = JSONObject.fromObject(hos.getResult());
					qvo.setASK_SourceAskID(deptID);
					qvo.setQD_DeptIDS(hospt.getString("bigDepartment"));// ��׼����
					qvo.setQD_DeptTwoIDS(hospt.getString("standardDeptId"));// ��׼��������
					qvo.setQD_AskDeptID(deptID);// ָ���Ŀ�����ѯ���ڿ���
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());
					rightNow.add(Calendar.DAY_OF_YEAR, 2);
					Date dt1 = rightNow.getTime();
					qvo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dt1));// �շ�����24Сʱ����
					if (price > 0) {// ��������շ�������������ˮ����
						qvo.setQD_OrdersStatus(1); // δ֧��
					}
				} else {
					return WsUtil.getRetVal(msg.getOutType(), -11111,
							"ָ��ҽ����Ϣ�����ڡ�");
				}
			} else {
				String bigDeptIds = ",";
				String ltDeptIds = ",";
				JSONObject dpjson = new JSONObject();
				dpjson.put("deptIds", DeptList);
				if (DeptList.equals("") || DeptList == null
						|| DeptList.equals("0")) {
					qvo.setQD_CheckStatus(0);
				} else {
					ServiceResult<String> rtdepts = commonApi
							.getSmallDepartmentList(dpjson.toString());
					if (rtdepts.getCode() > 0) {
						JSONArray depts = JSONArray.fromObject(rtdepts
								.getResult());
						DepartmentsVo deptVo;
						for (int i = 0; i < depts.size(); i++) {
							deptVo = new DepartmentsVo();
							deptVo.setASK_DepartIDOne(depts.getJSONObject(i)
									.getInt("deptSn"));
							deptVo.setASK_DepartNameOne(depts.getJSONObject(i)
									.getString("bigName"));
							deptVo.setASK_DepartIDTwo(depts.getJSONObject(i)
									.getString("deptId"));
							deptVo.setASK_DepartNameTwo(depts.getJSONObject(i)
									.getString("name"));
							deptVo.setDEPART_OperatorType(0);
							deptVo.setDEPART_CreateTime(DateUtil
									.dateFormat(new Date()));
							int rtdp = departmentsService
									.insertDepartments(deptVo);
							deptIds = deptIds + rtdp + ",";
							bigDeptIds = bigDeptIds
									+ depts.getJSONObject(i).getInt("deptSn")
									+ ",";
							ltDeptIds = ltDeptIds
									+ depts.getJSONObject(i)
											.getString("deptId") + ",";
						}
						qvo.setQD_CheckStatus(1);
					}
				}
				qvo.setQD_DeptIDS(bigDeptIds);
				qvo.setQD_DeptTwoIDS(ltDeptIds);
			}

			qvo.setQD_HandleID(handid);
			qvo.setQD_SourceType(sourceType);

			// ��������
			int quID = quesMainService.insertQuesMain(qvo);
			if (quID < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -10002, "��������ʧ�ܡ�");
			}
			
			
			
			
			System.out.println("88888888888888888888888888888888888888888888888888888888888");
			
			//TODO  WUJIAJUN
			//��������ɹ���  ��   ��߲�������
			
			//1. ����ҽ��id ȥ��ѯ ���ñ�  --������ ȡ��O C  ��������� set  --û������ set O 
			//2.������ ���� ���ű��� 
			
			try {
				
				DoctorDefaultAuthVo   confvo = new  DoctorDefaultAuthVo();
				confvo.setDoctorId(String.valueOf(doctorID));
				List<DoctorDefaultAuthVo>    list=     doctorDefaultAuthService.queryDoctorDefaultAuthListByDoctorId(confvo);
				
				
				
				OpenAuthVo  openvo = new OpenAuthVo();
				openvo.setQuesMainId(String.valueOf(quID));
				openvo.setDoctorId(String.valueOf(doctorID));
				
				if(list.size()==0){
					//Ĭ��ֵ  O
					openvo.setOpenAuthFlag("O");
					
				}else{
					//�����ֵ ȡ����
					openvo.setOpenAuthFlag(list.get(0).getOpenFlag());
					
				}
				
				//���浽open��
				openAuthService.insertOpenAuth(openvo);
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return WsUtil.getRetVal(msg.getOutType(), -10002, "���� ���� Ȩ�� ʧ�ܣ���");
			}
			
		
			System.out.println("9999999999999999999999999999999999999999999999999999999");
			
			
			
			
			
			
			
			
			
			
			if (price > 0) {
				// ������ˮ
				coVo.setASK_QuesID(quID);
				coVo.setASK_Content(content);
				coVo.setASK_QuesType(7);
				coVo.setASK_UserID(userID);
				coVo.setCO_CreateTime(DateUtil.dateFormat(new Date()));
				coVo.setCO_Price(price);
				coVo.setCO_Status(-2);
				coVo.setASK_DoctorID(doctorID);
				coVo.setASK_DeptID(deptID);
				coVo.setFeeTemplateId(feeTemplateId);
				if (sourceType == 2) {
					coVo.setCO_AskType(1);
				} else {
					coVo.setCO_AskType(0);
				}
				coVoID = consumerOrdersService.insertConsumerOrders(coVo);
				if (coVoID == 0) {
					return WsUtil
							.getRetVal(msg.getOutType(), -10003, "���ɶ���ʧ�ܡ�");
				}
				qvo.setQD_OrdersId(String.valueOf(coVoID));
				/*
				 * // �����û��˻���� ReturnValueBean ret =
				 * accountService.frozenWswyFee(userID, price, coVoID, "12",
				 * "01", operatorID, operatorName); if (ret.getCode() < 0) {
				 * return WsUtil.getRetVal(msg.getOutType(), -14444,
				 * ret.getMessage()); }
				 */
			} else {
				QuesMainVo qusVo = new QuesMainVo();
				qusVo.setQUESMAIN_ID(quID);
				qusVo.setQD_OrdersStatus(0);
				quesMainService.updateQuesMain(qusVo);
			}
			/*
			 * QuesMainVo qusVo = new QuesMainVo(); qusVo.setQUESMAIN_ID(quID);
			 * qusVo.setQD_OrdersStatus(2);
			 * quesMainService.updateQuesMain(qusVo);
			 */
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(quID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���¸�������ʧ�ܡ�");
				}
			}

			// ���������ѯ���ű�
			if (StringUtil.isNotEmpty(deptIds)) {
				deptIds = StringUtils.substringBeforeLast(deptIds, ",");
				DepartmentsVo dVo = new DepartmentsVo();
				dVo.setASK_QuesID(quID);
				int dert = departmentsService.updateDepartmentsForQuesID(dVo,
						deptIds);
				if (dert < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"���������ѯ�����б�ʧ�ܡ�");
				}
			}
			// ���¶�����ID����
			/*
			 * ConsumerOrdersVo cVo = new ConsumerOrdersVo(); if (coVoID != 0) {
			 * cVo.setASK_QuesID(quID); cVo.setCO_ID(coVoID);
			 * cVo.setCO_Status(0); int rtcon = consumerOrdersService
			 * .updateConsumerOrdersByCondition(cVo); if (rtcon < 0) { return
			 * WsUtil.getRetVal(msg.getOutType(), -14444, "���¶�������ʧ�ܡ�");
			 * 
			 * } }
			 */
			if (price == 0) {
				QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(quID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(0);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}

			JSONObject qus = new JSONObject();
			qus.put("quertionID", quID);
			JSONObject rtJs = new JSONObject();
			rtJs.put("Code", 10000);
			rtJs.put("Message", "�ɹ�");
			JSONArray rtJr = new JSONArray();
			rtJr.add(qus);
			rtJs.put("Result", rtJr.toString());
			return rtJs.toString();
			// return WsUtil.getRetVal(msg.getOutType(), 1, qus.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	
	
	/**
	 * �����û�����
	 * 
	 * @param
	 * @param
	 * @return
	 */

	public String quesFreeze(InterfaceMessage msg) {
		String tag = "quesFreeze";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			String operatorName = json.get("OperatorName") == null ? null
					: json.getString("OperatorName");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			int quID = json.get("quID") == null ? null : json.getInt("quID");
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			/*
			 * int price = json.get("price") == null ? null :
			 * json.getInt("price");
			 */
			/*
			 * int feeTemplateId = json.get("feeTemplateId") == null ? null :
			 * json .getInt("feeTemplateId");
			 */
			com.yihu.baseinfo.api.DoctorServiceApi doctorService= Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			// �����û��˻����
			ConsumerOrdersVo conVo = new ConsumerOrdersVo();
			conVo.setASK_QuesID(quID);
			conVo = consumerOrdersService.queryConsumerOrdersByQuesID(conVo);
			if (conVo == null || conVo.equals(null)) {
				return ApiUtil.getJsonRt(-14444, "��ȡ��������ʧ�ܡ�");
			}
			
			/*ReturnValueBean ret = accountService.frozenWswyFee(userID,
					conVo.getCO_Price(), conVo.getCO_ID(), "12", "01",
					operatorID, operatorName);
			if (ret.getCode() < 0) {
				return ApiUtil.getJsonRt(-14444, ret.getMessage());
			}*/
			JSONObject AuthInfo = new JSONObject();
			AuthInfo.put("ClientId", "6000005");
			
			
			InterfaceMessage iMsg = new InterfaceMessage();
			JSONObject msgParam = new JSONObject();
			msgParam.put("feeTemplateId", conVo.getFeeTemplateId());
			iMsg.setParam(msgParam.toString());
			JSONObject dcPrice = JSONObject.fromObject(doctorService.queryFeeTemplateById(iMsg));
			dcPrice.getInt("doctorPrice");
			String wsrt = 	postService.frozenWswyFee(String.valueOf(userID),String.valueOf(conVo.getCO_ID()) ,String.valueOf( conVo.getCO_Price()), "12",
					"01", "������ҽ��ѯ��", "WSWY", String.valueOf(operatorID), operatorName,String.valueOf(conVo.getASK_DoctorID()), 
					String.valueOf(msg.getOutType() ),String.valueOf(msg.getParamType()) ,AuthInfo.toString());
			System.out.println(wsrt);
			net.sf.json.JSONObject js =  net.sf.json.JSONObject.fromObject(wsrt);
			if(js.getInt("Code")<0){
				
				System.out.println(wsrt);
				return ApiUtil.getJsonRt(-14444, js.getString("Message"));
			}
			
			
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(quID);
			qusVo.setQD_OrdersStatus(2);
			int rt = quesMainService.updateQuesMain(qusVo);
			if (rt < 0) {
				return ApiUtil.getJsonRt(-14444, "���¶�������ʧ�ܡ�");
			}
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			cVo.setASK_QuesID(quID);
			cVo.setCO_ID(conVo.getCO_ID());
			cVo.setCO_Status(0);
			int rtcon = consumerOrdersService
					.updateConsumerOrdersByCondition(cVo);
			if (rtcon < 0) {
				return ApiUtil.getJsonRt(-14444, "���¶�������ʧ�ܡ�");
			}
			String s1 = DateUtil.dateFormat(new Date(), DateUtil.YMD_FORMAT);
			String s2 = s1 + " 09:00:00";
			String s3 = s1 + " 22:00:00";
			if (DateUtil.getIfInTime(s2, s3) && conVo.getASK_DoctorID() > 0) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("doctorUid", conVo.getASK_DoctorID());
				jsonObj.put("erviceStatusSearch", 3);
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put("columns", "doctorAccount,doctorName");
				InterfaceMessage dcMsg = new InterfaceMessage();
				dcMsg.setParam(jsonObj.toString());
				JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
						.queryComplexDoctorList(dcMsg));
				JSONArray dcJson = JSONArray.fromObject(dcRt
						.getJSONArray("Result"));
				int countDoc = dcRt.getInt("Total");
				if (countDoc < 0) {
					return ApiUtil.getJsonRt(10000, "��ȡ������Ϣʧ�ܡ�");
				}
//�Ƿ����ɾ��??
//				PublicForSmsService sms = Rpc.get(PublicForSmsService.class,
//						ConfigUtil.getInstance().getUrl("url.smsgw"), 16000);
//				sms.send(
//						dcJson.getJSONObject(0).getString("doctorAccount"),
//						"�𾴵�"
//								+ dcJson.getJSONObject(0).getString(
//										"doctorName")
//										+ "ҽ�������ã��ո���һλ�������Ļ��������ύ����ѯ�����ȴ����Ľ�����ϵ�¼����֮·�ֻ�APP ��http://m.yihu.cn/d32��ҽ�������лظ��ɣ�",
//										10111210);
			}
			QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
			qrmVo.setQueID(quID);
			qrmVo.setReplyCount(1);
			qrmVo.setReplyType(0);
			qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
			qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(new Date());
			rightNow.add(Calendar.YEAR, 100);
			Date dt1 = rightNow.getTime();
			qrmVo.setEndTime(DateUtil.dateFormat(dt1));
			int qvoNo = quesReplyMutualService.queryQuesReplyMutualCountByCondition(qrmVo);
			if(qvoNo ==0){
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}
			BalanceReturnBean rtb = accountService.getAllBalance(userID, "0",
					"0");
			JSONObject balance = new JSONObject();
			balance.put("balance", rtb.getAvailableBalance());
			balance.put("frozenBalance", rtb.getFrozenBalance());
			balance.put("specialAmounts", rtb.getSpecialAmounts());
			balance.put("frozenSpecialBalance", rtb.getFrozenSpecialBalance());
			balance.put("giftAmounts", rtb.getGiftAmounts());
			balance.put("balanceNew", rtb.getBalance());
			balance.put("frozenGiftBalance", rtb.getFrozenGiftBalance());
			 
			JSONArray Result = new JSONArray();
			Result.add(balance);
			JSONObject rtJS = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"����ɹ�"));
			rtJS.put("Result", Result);
			JSONObject pushJson = new JSONObject();
			JSONObject mesJson = new JSONObject();
			mesJson.put("sourceid",conVo.getASK_UserID());
			mesJson.put("interactType", "100000");// �û��ظ�
			mesJson.put("targetid", quID);
			mesJson.put("content", quID);
			pushJson.put("Type", "100000");// �û��ظ�
			pushJson.put("SendCode", conVo.getASK_DoctorID());
			pushJson.put("SendKind", 10);
			pushJson.put("RecvKind", 11);
			pushJson.put("SendName", "APP");
			pushJson.put("SendWay", 1);
			pushJson.put("RecvWay", "12");
			pushJson.put("AppKey", "554d8e9ac52715ddca89311a");
			pushJson.put("masterSecret", "97c59461bce86655c94f8f6f");
			pushJson.put("RecvCode", conVo.getASK_DoctorID());
			pushJson.put("Content", "APP");
			pushJson.put("ContentExtend", mesJson);
		
			MsgManagerService sms = Rpc.get(MsgManagerService.class, ConfigUtil
					.getInstance().getUrl("url.MsgManager"), 3000);
			InterfaceMessage pushMsg = new InterfaceMessage();
			pushMsg.setParam(pushJson.toString());
			//�Ƿ����ɾ��??
			//String rtMsg = sms.pushMsg(pushMsg);
		   //System.out.println(rtMsg);
			MsgApi msgApi = Rpc.get(MsgApi.class, ConfigUtil.getInstance().getUrl("url.jpushMsg"));
			JSONObject nMesJson = new JSONObject();
			JSONObject androidJson = new JSONObject();
			JSONObject iosJson = new JSONObject();
			androidJson.put("uri", "");
			androidJson.put("date",DateUtil.dateFormat(new Date()));
			androidJson.put("sourceId", userID);
			androidJson.put("sourceName", "");
			androidJson.put("contentId", rt);
			androidJson.put("content", conVo.getASK_Content());
			androidJson.put("questionId", quID);
			//androidJson.put("title", "���յ�1������ѯ�������鿴��");
			QuesMainVo qu = new QuesMainVo();
			qu.setQUESMAIN_ID(quID);
			qu = quesMainService.queryQuesMainByCondition(qu);
			if( qu.getQUESMAIN_Content().length() > 15 ){
				androidJson.put("title", qu.getQUESMAIN_Content().subSequence(0, 15) +"......");
			}else{
				androidJson.put("title", qu.getQUESMAIN_Content().subSequence(0,  qu.getQUESMAIN_Content().length()-1) +"......");
			}
			androidJson.put("operCode", "12000");
			androidJson.put("msgType", "");
			iosJson.put("questionId", quID);
			if( qu.getQUESMAIN_Content().length() > 15 ){
				iosJson.put("ti", qu.getQUESMAIN_Content().subSequence(0, 15) +"......");
			}else{
				iosJson.put("ti", qu.getQUESMAIN_Content().subSequence(0,  qu.getQUESMAIN_Content().length()-1) +"......");
			}
			//iosJson.put("ti", "���յ�1������ѯ�������鿴��");
			iosJson.put("operCode", 12000);
			iosJson.put("contentId", rt);
			iosJson.put("sourceId", userID);
			iosJson.put("sourceName", "");
			nMesJson.put("androidParam", androidJson);
			nMesJson.put("toAppType", 1);
			nMesJson.put("toUsers", conVo.getASK_DoctorID());
			nMesJson.put("iosParam", iosJson);
			//InterfaceMessage pushMsgN = new InterfaceMessage();
			//pushMsgN.setParam(nMesJson.toString());
			JSONObject authinfo = new JSONObject();
			authinfo.put("ClientId", "client.myt");
			//pushMsgN.setAuthInfo(authinfo.toString());
			//String purt= msgApi.sendMsg(pushMsgN);
			//
			String purt = postService.sendMsgJ(nMesJson.toString());
			
			System.out.println(purt);
			return rtJS.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));

		}
	}

	/**
	 * �������ת��������
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String questionFreeToCharge(InterfaceMessage msg) {
		String tag = "questionFreeToCharge";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String tipPhone = json.get("tipPhone") == null ? null : json
					.getString("tipPhone");
			int queID = json.get("queID") == null ? null : json.getInt("queID");
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			String content = json.get("content") == null ? null : json
					.getString("content");
			int deptID = json.get("deptID") == null ? null : json
					.getInt("deptID");
			int filesCount = json.get("filesCount") == null ? null : json
					.getInt("filesCount");
			int price = json.get("price") == null ? null : json.getInt("price");
			int feeTemplateId = json.get("feeTemplateId") == null ? null : json
					.getInt("feeTemplateId");
			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");

			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo.equals(null) || qusVo == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			FilesVo file;
			String filsIds = "";
			if (filesCount > 0 && fileMess != null) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(fileMess.getJSONObject(i)
							.getString("createTime"));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return ApiUtil.getJsonRt(-14444, "��������ʧ��.");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}
			// �߼�ɾ��֮ǰ�ĸ���
			FilesVo delFIle = new FilesVo();
			delFIle.setFILES_Status(0);
			delFIle.setFILES_ObjTypeID(1);
			delFIle.setFILES_ObjID(queID);
			filesService.updateFilesByCondition(delFIle);
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(queID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return ApiUtil.getJsonRt(-14444, "���¸�����IDʧ�ܡ�");
				}
			}
			// qvo.setASK_DepartID(String.valueOf(rt));
			ConsumerOrdersVo coVo = new ConsumerOrdersVo();
			// ���±�������
			qusVo.setQUESMAIN_Content(content);
			// �����趨�۸�
			qusVo.setQD_Price(price);
			qusVo.setQD_TipPhone(tipPhone);
			Calendar dcNow = Calendar.getInstance();
			dcNow.setTime(new Date());
			dcNow.add(Calendar.DAY_OF_YEAR, 2);
			Date dc1 = dcNow.getTime();
			qusVo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));// �շ��������48Сʱ
			// �������ݱ���
			PatientVo pat = new PatientVo();
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);
			// ���±��滼������
			qusVo.setASK_PatientID(patID);
			// ������ˮ
			coVo.setASK_Content(content);
			coVo.setASK_QuesType(7);
			coVo.setASK_UserID(userID);
			coVo.setCO_CreateTime(DateUtil.dateFormat(new Date()));
			coVo.setCO_Price(price);
			coVo.setCO_Status(-2);
			coVo.setASK_DoctorID(doctorID);
			coVo.setASK_DeptID(deptID);
			coVo.setFeeTemplateId(feeTemplateId);
			if (qusVo.getQD_SourceType() == 2) {
				coVo.setCO_AskType(1);
			} else {
				coVo.setCO_AskType(0);
			}
			int coVoID = consumerOrdersService.insertConsumerOrders(coVo);
			if (coVoID == 0) {
				return ApiUtil.getJsonRt(-14444, "���ɶ���ʧ�ܡ�");
			}
			/*
			 * ReturnValueBean ret = accountService.frozenWswyFee(userID, price,
			 * coVoID, "12", "01", operatorID, operatorName); if (ret.getCode()
			 * < 0) { return ApiUtil.getJsonRt(-14444, ret.getMessage()); }
			 */
			// ���¶�����ID����
			qusVo.setQD_OrdersStatus(1);
			int rtqe = quesMainService.updateQuesMain(qusVo);
			if (rtqe < 0) {
				return ApiUtil.getJsonRt(-14444, "������������ʧ�ܡ�");
			}
			// ���¶�����ID����
			/*
			 * ConsumerOrdersVo cVo = new ConsumerOrdersVo(); if (coVoID != 0) {
			 * cVo.setASK_QuesID(queID); cVo.setCO_ID(coVoID);
			 * cVo.setCO_Status(0); int rtcon = consumerOrdersService
			 * .updateConsumerOrdersByCondition(cVo); if (rtcon < 0) { return
			 * ApiUtil.getJsonRt(-14444, "���¶�����ID����ʧ�ܡ�"); } }
			 */

			JSONObject rtjon = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"�ɹ�"));
			JSONArray rt = new JSONArray();
			rtjon.put("Result", rt);
			return rtjon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	public String editSaveQues(InterfaceMessage msg) {
		String tag = "editSaveQues";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String tipPhone = json.get("tipPhone") == null ? null : json
					.getString("tipPhone");
			int queID = json.get("queID") == null ? null : json.getInt("queID");
			String content = json.get("content") == null ? null : json
					.getString("content");
			int filesCount = json.get("filesCount") == null ? null : json
					.getInt("filesCount");
			int price = json.get("price") == null ? null : json.getInt("price");
			JSONObject patientMess = json.get("patientMess") == null ? null
					: json.getJSONObject("patientMess");
			JSONArray fileMess = json.get("fileMess") == null ? null : json
					.getJSONArray("fileMess");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo.equals(null) || qusVo == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			FilesVo file;
			String filsIds = "";
			if (filesCount > 0 && fileMess != null) {
				for (int i = 0; i < fileMess.size(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(DateUtil.dateFormat(new Date()));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return ApiUtil.getJsonRt(-14444, "��������ʧ��.");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}
			// �߼�ɾ��֮ǰ�ĸ���
			FilesVo delFIle = new FilesVo();
			delFIle.setFILES_Status(0);
			delFIle.setFILES_ObjTypeID(1);
			delFIle.setFILES_ObjID(queID);
			filesService.updateFilesByCondition(delFIle);

			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(queID);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return ApiUtil.getJsonRt(-14444, "���¸�����IDʧ�ܡ�");
				}
			}
			// ���±�������
			qusVo.setQUESMAIN_Content(content);
			// �����趨�۸�
			qusVo.setQD_Price(price);
			qusVo.setQD_TipPhone(tipPhone);
			// �������ݱ���
			PatientVo pat = new PatientVo();
			pat.setPATIENT_Name(patientMess.getString("patname"));
			pat.setPATIENT_Birth(patientMess.getString("patbirth"));
			pat.setPATIENT_Sex(patientMess.getInt("patsex"));
			pat.setASK_CityID(patientMess.getInt("patcityid"));
			pat.setASK_CityName(patientMess.getString("patcityname"));
			pat.setASK_ProvinceID(patientMess.getInt("patprvid"));
			pat.setASK_ProvinceName(patientMess.getString("patprvname"));
			int patID = patientService.insertPatient(pat);
			// ���±��滼������
			qusVo.setASK_PatientID(patID);
			int rtqe = quesMainService.updateQuesMain(qusVo);
			if (rtqe < 0) {
				return ApiUtil.getJsonRt(-14444, "������������ʧ�ܡ�");
			}
			JSONObject rtjon = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"�ɹ�"));
			JSONArray rt = new JSONArray();
			rtjon.put("Result", rt);
			return rtjon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * �ر�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public ServiceResult<String> overQuertion(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "�������");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			int quesID = json.get("quesID") == null ? null : json
					.getInt("quesID");
			String cardID = json.get("cardID") == null ? null : json
					.getString("cardID");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			int pleasedLevel = json.get("pleasedLevel") == null ? null : json
					.getInt("pleasedLevel");
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			String operatorName = json.get("OperatorName") == null ? null
					: json.getString("OperatorName");
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));

			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));

			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(quesID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			cVo.setASK_QuesID(quesID);
			cVo = consumerOrdersService.queryConsumerOrdersByQuesID(cVo);

			if (quesID > 0 && StringUtil.isNotEmpty(cardID) && userID > 0
					&& doctorID > 0) {
				// �û��ر�����
				if (qvo.getQD_DocReplayCount() > 0) {
					// �����ָ������,�������,�۳��û�����
					int pri = qvo.getQD_Price();
					if (pri > 0) {
						if (qvo.getQD_SourceType() == 0
								|| qvo.getQD_SourceType() == 2) {
							cVo.setASK_QuesType(7);
						} else {
							cVo.setASK_QuesType(8);
						}
						// �������
						ReturnValueBean clrt = accountService.clearFrozen(
								userID, cVo.getCO_ID(), "12", "01");
						/*
						 * if (clrt.getCode() < 0) { result.setCode(-14444);
						 * result.setMessage(clrt.getMessage());
						 * result.setResult(""); return result; }
						 */
						int price = cVo.getCO_Price() * (-1);
						int feeTemplateId = cVo.getFeeTemplateId();
						// �Ʒѿ۳��û�����
						ChargeReturnBean chrt = accountService.charge(cardID,
								userID, cVo.getASK_QuesType(), 84,
								cVo.getCO_ID(), operatorID, operatorName,
								"��ҽ��Ĭ�ϡ�������ҽ���á�", String.valueOf(price), null,
								0, false, null, null);
						System.out.println(chrt.getMessage());
						if (chrt.getCode() < 0) {
							result.setCode(-14444);
							result.setMessage(chrt.getMessage());
							result.setResult("");
							return result;
						}
						/*String chargeRt =	postService.charge(cardID, "84",String.valueOf(cVo.getCO_ID()) ,String.valueOf(operatorID) , operatorName,"0"
								, "false", "��ҽ��Ĭ�ϡ�������ҽ���á�", String.valueOf(price), "", String.valueOf(cVo.getASK_DoctorID()));
						System.out.println(chargeRt);
						net.sf.json.JSONObject js =  net.sf.json.JSONObject.fromObject(chargeRt);
						if(js.getInt("Code")<0){
							//return ApiUtil.getJsonRt(-14444, js.getString("Message"));
							result.setCode(-14444);
							result.setMessage(js.getString("Message"));
							result.setResult("");
							return result;
						}*/
						// ҽ����Ǯ
						JSONObject dcBillJson = new JSONObject();
						dcBillJson.put("doctorUid", doctorID);
						dcBillJson.put("serviceRecordId", cVo.getCO_ID());
						dcBillJson.put("serviceId", 2);
						dcBillJson.put("feeTemplateId", feeTemplateId);
						dcBillJson.put("price",(-1)* price);// ҽ���۷�
						ServiceResult<String> rt = doctorServiceApi
								.insertBill(dcBillJson.toString());
						if (rt.getCode() < 0) {
							result.setCode(-14444);
							result.setMessage(rt.getMessage());
							result.setResult("");
							return result;
						}
						// ������ˮ������Ϣ
						cVo.setCO_Status(1);
						int cort = consumerOrdersService
								.updateConsumerOrdersByCondition(cVo);
						if (cort < 0) {
							result.setCode(-14444);
							result.setMessage("��������ʧ��");
							result.setResult("");
							return result;
						}

					} else {// ���������߼۸�Ϊ0������
						qvo.setQUESMAIN_ID(quesID);
						qvo.setQD_Status(1);
						qvo.setQD_RecordExpiredTime(DateUtil
								.dateFormat(new Date()));

					}
					// �û�����
					QuesEvaluateVo qevo = new QuesEvaluateVo();
					qevo.setASK_DoctorID(qvo.getASK_DoctorID());
					qevo.setASK_UserID(qvo.getASK_UserID());
					qevo.setELT_CRTime(DateUtil.dateFormat(new Date()));
					qevo.setELT_PleasedLevel(pleasedLevel);
					qevo.setASK_QuesID(quesID);
					qevo.setELT_isSysEva(0);
					int elrt = quesEvaluateService.insertQuesEvaluate(qevo);
					if (elrt < 0) {
						result.setCode(-14444);
						result.setMessage("������ˮ����ʧ��");
						result.setResult("");
						return result;
					}
					// ������� �ͼӰ���ֵ
					if (pleasedLevel == 1) {
						qvo.setQD_Status(2);
						// ����ֵ����(������������ˮ)
						JSONObject axJson = new JSONObject();
						axJson.put("doctorUid", qvo.getASK_DoctorID());
						axJson.put("resId", quesID);
						axJson.put("typeId", 3);
						ServiceResult<String> addlrt = doctorAccountApi
								.addDoctorLove(axJson.toString());
						if (addlrt.getCode() < 0) {
							result.setCode(-14444);
							result.setMessage("����ֵ����ʧ��");
							result.setResult("");
							return result;
						}
						CreditsRecordVo crVo = new CreditsRecordVo();
						crVo.setASK_QuesID(quesID);
						crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
						crVo.setASK_DoctorID(qvo.getASK_DoctorID());
						crVo.setCR_Credits(1);
						crVo.setCR_CreditsType(2);
						crVo.setCR_Type(2);
						crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
						int rtCr = creditsRecordService
								.insertCreditsRecord(crVo);
						if (rtCr < 0) {
							result.setCode(-14444);
							result.setMessage("����ֵ��ˮ����ʧ��");
							result.setResult("");
							return result;
						}
					} else {
						qvo.setQD_Status(1);
					}
					int qurt = quesMainService.updateQuesMain(qvo);
					if (qurt < 0) {
						result.setCode(-14444);
						result.setMessage("��������״̬ʧ��");
						result.setResult("");
						return result;
					}
					result.setCode(1);
					result.setMessage("����رգ������ɹ�");
					result.setResult("");
					
					
					//�¼� 
					
					EventUtilForMYT.sendEvenForClose(String.valueOf(doctorID),String.valueOf(quesID));
					
					
					
					
					return result;
				}
			} else if (quesID > 0 && doctorID > 0 && userID == 0
					&& StringUtil.isEmpty(cardID)) {
				result.setCode(1);
				result.setMessage("ҽ����֧�ָò�������");
				result.setResult("");
				return result;
				// ҽ������ر�����
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �ر�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private static ICloseMainService closeMainService = Ioc
			.get(ICloseMainService.class);
	
	public String overQuertionV2(InterfaceMessage msg) {
		String tag = "overQuertionV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			int quesID = json.get("quesID") == null ? null : json
					.getInt("quesID");
			String cardID = json.get("cardID") == null ? null : json
					.getString("cardID");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			int pleasedLevel = json.get("pleasedLevel") == null ? null : json
					.getInt("pleasedLevel");
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			String operatorName = json.get("OperatorName") == null ? null
					: json.getString("OperatorName");
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));

			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));

			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(quesID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			qvo.setOpenAuthFlag(null);
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			cVo.setASK_QuesID(quesID);
			cVo = consumerOrdersService.queryConsumerOrdersByQuesID(cVo);

			if (quesID > 0 && StringUtil.isNotEmpty(cardID) && userID > 0
					&& doctorID > 0 ) {
				// �û��ر�����
				if (qvo.getQD_DocReplayCount() > 0) {
					// �����ָ������,�������,�۳��û�����
					int pri = qvo.getQD_Price();
					if (pri > 0) {
						if (qvo.getQD_SourceType() == 0
								|| qvo.getQD_SourceType() == 2) {
							cVo.setASK_QuesType(7);
						} else {
							cVo.setASK_QuesType(8);
						}
						// �������
						ReturnValueBean clrt = accountService.clearFrozen(
								userID, cVo.getCO_ID(), "12", "01");
						if (clrt.getCode() < 0 && clrt.getCode() != -2000) {
							// -2000�Ѿ��ⶳ��
							return WsUtil.getRetVal(msg.getOutType(), -2000,
									"�ƷѶ���ʧ�ܡ�");
						}
						int price = cVo.getCO_Price() * (-1);
						int feeTemplateId = cVo.getFeeTemplateId();
						// �Ʒѿ۳��û�����
						/*ChargeReturnBean chrt = accountService.charge(cardID,
								userID, cVo.getASK_QuesType(), 84,
								cVo.getCO_ID(), operatorID, operatorName,
								"��ҽ��Ĭ�ϡ�������ҽ���á�", String.valueOf(price), null,
								0, false, null, null);
						if (chrt.getCode() < 0 && chrt.getCode() != -2000) {// -2000�Ѿ��۷ѹ�
							return ApiUtil.getJsonRt(-2000, "�Ʒѿ۳�ʧ�ܡ�");
						}
						System.out.println(msg.getSeq());*/
						System.out.println("ZZZZZZ"+cardID);
						String chargeRt =	postService.charge(cardID, "84",String.valueOf(cVo.getCO_ID()) ,String.valueOf(operatorID) , operatorName,"0"
								, "false", "��ҽ��Ĭ�ϡ�������ҽ���á�", String.valueOf(price), "1", String.valueOf(cVo.getASK_DoctorID())
								,String.valueOf(msg.getOutType() ),String.valueOf(msg.getParamType()) ,msg.getAuthInfo());
						System.out.println(chargeRt);
						net.sf.json.JSONObject js =  net.sf.json.JSONObject.fromObject(chargeRt);
						if(js.getInt("Code")<0&& js.getInt("Code")!= -2000){
							//return ApiUtil.getJsonRt(-14444, js.getString("Message"));
							return ApiUtil.getJsonRt(-2000, "�Ʒѿ۳�ʧ�ܡ�");
						}
						
						 
						// ҽ����Ǯ
						JSONObject dcBillJson = new JSONObject();
						if (qvo.getQD_SourceType() == 2) {
							dcBillJson.put("outDoctorUid", doctorID);
							dcBillJson.put("serviceId", 3);
							dcBillJson.put("hosDeptId", qvo.getQD_AskDeptID());
							
						} else if (qvo.getQD_SourceType() == 3) {
							dcBillJson.put("outDoctorUid", doctorID);
							dcBillJson.put("serviceId", 4);
							dcBillJson.put("groupId", qvo.getQD_DocFreeID());
						} else {
							dcBillJson.put("doctorUid", doctorID);
							dcBillJson.put("serviceId", 2);
						}
						if( js.getInt("Packagecode")>0){
							dcBillJson.put("serviceId", 8);
						}
						dcBillJson.put("resId", js.getString("Packagecode"));
						dcBillJson.put("serviceRecordId", cVo.getCO_ID());
						dcBillJson.put("feeTemplateId", feeTemplateId);
						dcBillJson.put("price", (-1) *price );// ҽ���۷�
						/*ServiceResult<String> rt = doctorServiceApi
								.insertBill(dcBillJson.toString());
						if (rt.getCode() < 0) {
							return ApiUtil.getJsonRt(-14444, rt.getMessage());
						}*/
						String rtBiil = postService.insertBill(dcBillJson.toString());
						System.out.println(rtBiil+"888888888");
						JSONObject rtb = JSONObject.fromObject(rtBiil);
	
						if(rtb.getInt("Code")<0&& rtb.getInt("Code")!=-10000){
							return ApiUtil.getJsonRt(-14444, rtb.getString("Message"));
						}
						// ������ˮ������Ϣ
						cVo.setCO_Status(1);
						int cort = consumerOrdersService
								.updateConsumerOrdersByCondition(cVo);
						if (cort < 0) {
							return ApiUtil.getJsonRt(-14444, "��������ʧ�ܡ�");
						}

					} else {// ���������߼۸�Ϊ0������
						qvo.setQUESMAIN_ID(quesID);
						qvo.setQD_Status(1);
						qvo.setQD_RecordExpiredTime(DateUtil
								.dateFormat(new Date()));
					}
					// �û�����
					QuesEvaluateVo qevo = new QuesEvaluateVo();
					qevo.setASK_DoctorID(qvo.getASK_DoctorID());
					qevo.setASK_UserID(qvo.getASK_UserID());
					qevo.setELT_CRTime(DateUtil.dateFormat(new Date()));
					qevo.setELT_PleasedLevel(pleasedLevel);
					qevo.setASK_QuesID(quesID);
					qevo.setELT_isSysEva(0);
					int elrt = quesEvaluateService.insertQuesEvaluate(qevo);
					if (elrt < 0) {
						return ApiUtil.getJsonRt(-14444, "������ˮ����ʧ�ܡ�");
					}
					// ����ǻظ����Ĺ������� �ͼӰ���ֵ
					if( qvo.getQD_OrdersStatus() == 5){
						// ����ֵ����(������������ˮ)
						JSONObject axJson = new JSONObject();
						axJson.put("doctorUid", qvo.getASK_DoctorID());
						axJson.put("resId", quesID);
						axJson.put("typeId", 4);
						ServiceResult<String> addlrt = doctorAccountApi
								.addDoctorLove(axJson.toString());
						if (addlrt.getCode() < 0) {
							return ApiUtil.getJsonRt(-14444, "����ֵ��ˮ����ʧ�ܡ�");
						}
						CreditsRecordVo crVo = new CreditsRecordVo();
						crVo.setASK_QuesID(quesID);
						crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
						crVo.setASK_DoctorID(qvo.getASK_DoctorID());
						crVo.setCR_Credits(1);
						crVo.setCR_CreditsType(2);
						crVo.setCR_Type(3);
						crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
						int rtCr = creditsRecordService
								.insertCreditsRecord(crVo);
						if (rtCr < 0) {
							return ApiUtil.getJsonRt(-14444, "����ֵ��ˮ����ʧ�ܡ�");
						}
					}
					// ������� �ͼӰ���ֵ
					if (pleasedLevel == 1) {
						qvo.setQD_Status(2);
						// ����ֵ����(������������ˮ)
						JSONObject axJson = new JSONObject();
						axJson.put("doctorUid", qvo.getASK_DoctorID());
						axJson.put("resId", quesID);
						axJson.put("typeId", 3);
						ServiceResult<String> addlrt = doctorAccountApi
								.addDoctorLove(axJson.toString());
						if (addlrt.getCode() < 0) {
							return ApiUtil.getJsonRt(-14444, "����ֵ��ˮ����ʧ�ܡ�");
						}
						CreditsRecordVo crVo = new CreditsRecordVo();
						crVo.setASK_QuesID(quesID);
						crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
						crVo.setASK_DoctorID(qvo.getASK_DoctorID());
						crVo.setCR_Credits(1);
						crVo.setCR_CreditsType(2);
						crVo.setCR_Type(1);
						crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
						int rtCr = creditsRecordService
								.insertCreditsRecord(crVo);
						if (rtCr < 0) {
							return ApiUtil.getJsonRt(-14444, "����ֵ��ˮ����ʧ�ܡ�");
						}
					} else {
						qvo.setQD_Status(1);
					}
					qvo.setQD_CheckStatus(1);
					int qurt = quesMainService.updateQuesMain(qvo);
					if (qurt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"��������״̬ʧ��");
					}

					QuesCloseWaterVo vo = new QuesCloseWaterVo();
					if (operatorID == 1111111 || operatorID == 1000000
							|| operatorID == 9000029) {
						vo.setOperType(1);// �û��ر�
					} else if (operatorID == 9000023 || operatorID == 9000030) {
						vo.setOperType(3);// ҽ���ر�
					} else if (operatorID == 9000024 || operatorID == 9000031) {
						vo.setOperType(4);// ҽѧ��Ա�ر�
					} else {
						vo.setOperType(5);// δ֪�����ر�
					}
					vo.setCreateTime(DateUtil.dateFormat(new Date()));
					vo.setQuesID(quesID);
					int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
					if(rCont ==0){
						int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
						if (rtqu < 0) {
							return WsUtil.getRetVal(msg.getOutType(), -14444,
									"����ر�ʱ�����״̬ʧ��");
						}
					}
					
					//�¼�
					EventUtilForMYT.sendEvenForClose(String.valueOf(doctorID),String.valueOf(quesID));
				    /*//TODO   wujiajun
					//--------------
					//System.out.println("ҽ���� id��������" +doctorID);
					 DoctorVo docvo= closeMainService.getDocById(String.valueOf(doctorID) );
					//System.out.println(docvo.getDoctorName()+"ҽ�����֣�����");
					CloseMainVo clvo = new CloseMainVo();
					clvo.setDOCID(docvo.getDoctorUid());
					clvo.setDOCNAME(docvo.getDoctorName());
					closeMainService.insertCloseMain(clvo);
					//------------------ 
*/
					return WsUtil.getRetVal(msg.getOutType(), 10000,
							"����رգ������ɹ�");
				} else {
					return WsUtil.getRetVal(msg.getOutType(), 10000,
							"�ȴ�ҽ���ظ��У������޷��رա�");
				}
			} else if (quesID > 0 && doctorID > 0 && userID == 0
					&& StringUtil.isEmpty(cardID)) {
				return WsUtil.getRetVal(msg.getOutType(), 10000, "ҽ����֧�ָò�������");
				// ҽ������ر�����
			} else {
				return WsUtil.getRetVal(msg.getOutType(), 10000, "�����޷��رա�");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	
	
	
	
	/**
	 * �ظ�����(ҽ��)
	 * 
	 * @param quesContent
	 * @param userId
	 * @return
	 */
	public String replyQuertionDoctor(InterfaceMessage msg) {
		String tag = "replyQuertionDoctor";
		try {
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.common.json.JSONObject json = new com.common.json.JSONObject(
					msg.getParam());
			int replyType = json.get("replyType") == null ? null : json
					.getInt("replyType");
			int queID = json.get("queID") == null ? null : json.getInt("queID");
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			String messCont = json.get("messCont") == null ? null : json
					.getString("messCont");
			com.common.json.JSONArray fileMess = json.get("fileMess") == null ? null
					: json.getJSONArray("fileMess");
			int platform = json.get("platform") == null ? null : json
					.getInt("platform");
			int filesCount = json.get("filesCount") == null ? null : json
					.getInt("filesCount");
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo.equals(null) || qusVo == null) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "δ�����������.");
			}
			if (qusVo.getQD_SourceType() == 1
					&& qusVo.getQD_DocReplayCount() == 0) {
				QuesMainVo qm = new QuesMainVo();
				qm.setASK_DoctorID(doctorID);
				qm.setQUESMAIN_ID(queID);
				int ifyours = quesMainService.querCountDcAnswerQus(qm);
				if (ifyours <= 0) {
					return WsUtil.getRetVal(msg.getOutType(), -2000,
							"����������ѹ��ڣ������»�ȡ");
				}
			} else {
				if (!qusVo.getASK_DoctorID().equals(doctorID)
						|| qusVo.getQD_CheckStatus() == 0) {
					return WsUtil
							.getRetVal(msg.getOutType(), -2000, "�޷��ظ������⡣");
				}
			}
			String s1 = DateUtil.dateFormat(new Date());
			String s2 = qusVo.getQUESMAIN_ExpiredTime();
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.util.Calendar c1 = java.util.Calendar.getInstance();
			java.util.Calendar c2 = java.util.Calendar.getInstance();
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
			int result = c1.compareTo(c2);
			if (result >= 0 && qusVo.getQD_OrdersStatus() != 5
					&& qusVo.getQD_Price() != 0 && qusVo.getQD_DocReplayCount()==0) {// ���ʱ���Ѿ�����ִ���˿�
				ConsumerOrdersVo cVo = new ConsumerOrdersVo();
				cVo.setASK_QuesID(qusVo.getQUESMAIN_ID());
				cVo = consumerOrdersService.queryConsumerOrdersByQuesID(cVo);
				ReturnValueBean clrt = accountService.clearFrozen(userID,
						cVo.getCO_ID(), "12", "01");
				if (clrt.getCode() < 0) {
					return ApiUtil.getJsonRt(clrt.getCode(), clrt.getMessage());
				}
				// ConsumerOrdersVo cVo = new ConsumerOrdersVo();
				cVo.setCO_Status(-1);
				// cVo.setCO_ID(1);
				int cort = consumerOrdersService.updateCOrdersByCondition(cVo);
				if (cort < 0) {
					return ApiUtil.getJsonRt(-14444, "��������ʧ��");
				}
				qusVo.setQD_Price(0);
				qusVo.setQD_OrdersStatus(5);
			}
			if (qusVo.getQD_SourceType() == 1
					&& qusVo.getQD_DocReplayCount() == 0) {
				// ��һ�λش������а���ֵ
				JSONObject axJson = new JSONObject();
				axJson.put("doctorUid", qusVo.getASK_DoctorID());
				axJson.put("resId", queID);
				axJson.put("typeId", 2);
				ServiceResult<String> addlrt = doctorAccountApi
						.addDoctorLove(axJson.toString());
				if (addlrt.getCode() < 0) {
					return ApiUtil.getJsonRt(-14444, "����ֵд��ʧ�ܡ�");
				}
				CreditsRecordVo crVo = new CreditsRecordVo();
				crVo.setASK_QuesID(queID);
				crVo.setASK_DoctorAccountID(qusVo.getASK_DoctorID());
				crVo.setASK_DoctorID(qusVo.getASK_DoctorID());
				crVo.setCR_Credits(1);
				crVo.setCR_CreditsType(2);
				crVo.setCR_Type(2);
				crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
				int rtCr = creditsRecordService
						.insertCreditsRecord(crVo);
				if (rtCr < 0) {
					return ApiUtil.getJsonRt(-14444, "����ֵ��ˮ����ʧ�ܡ�");
				}
				
				//���� �¼��Ƿ�  �ظ���3�� 10��
				EventUtilForMYT.sendEvenForAddlove(String.valueOf(qusVo.getASK_DoctorID()), String.valueOf(queID));
				
				
			}
			ReplyVo reply = new ReplyVo();
			reply.setREPLY_CreateTime(DateUtil.dateFormat(new Date()));
			reply.setREPLY_Content(messCont);
			reply.setASK_UserID(userID);
			reply.setASK_QuesID(queID);
			reply.setASK_DoctorID(doctorID);
			reply.setREPLY_Price(Double.valueOf(qusVo.getQD_Price()));
			reply.setREPLY_Type(replyType);
			reply.setREPLY_Platform(platform);
			reply.setREPLY_ContentType(0);
			reply.setREPLY_Status(1);
			reply.setREPLY_CheckStatus(1);
			reply.setREPLY_CheckTime(DateUtil.dateFormat(new Date()));
			reply.setREPLY_CheckManID(0);
			reply.setREPLY_CheckManName("admin");
			reply.setREPLY_UserType(1);
			reply.setREPLY_IsLook(0);
			int rt = replyService.insertReply(reply);
			if (rt < 0) {
				return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�");
			}
			QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
			qrmVo.setQueID(queID);
			qrmVo = quesReplyMutualService.queryQuesReplyMutual(qrmVo);
			if (qrmVo == null) {
				qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(queID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(1);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			} else {
				if (qrmVo.getReplyType() == 1) {
					qrmVo.setReplyCount(qrmVo.getReplyCount() + 1);
				} else {
					qrmVo.setReplyType(1);
					qrmVo.setReplyCount(1);
				}
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				int rtQRM = quesReplyMutualService.updateQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}

			FilesVo file;
			String filsIds = "";
			if (filesCount > 0 && fileMess != null) {
				for (int i = 0; i < fileMess.length(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(DateUtil.dateFormat(new Date()));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return ApiUtil.getJsonRt(-14444, "��������ʧ�ܡ�");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}
			Calendar dcNow = Calendar.getInstance();
			dcNow.setTime(new Date());
			dcNow.add(Calendar.DAY_OF_YEAR, 2);
			Date dc1 = dcNow.getTime();
			qusVo.setQUESMAIN_ExpiredTime(DateUtil.dateFormat(dc1));
			qusVo.setQD_DocReplayCount(qusVo.getQD_DocReplayCount() + 1);
			qusVo.setQD_IsUserReplay(0);
			qusVo.setQD_IsReplay(1);
			int qurt = quesMainService.updateQuesMain(qusVo);
			if (qurt < 0) {
				return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�");
			}
			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(rt);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return ApiUtil.getJsonRt(-14444, "���¸�����IDʧ�ܡ�");
				}
			}
			JSONObject rtJson = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"�ɹ���"));
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			/*com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));*/
			/*JSONObject jsonObj = new JSONObject();
			jsonObj.put("doctorUid", doctorID);
			// jsonObj.put("displayStatus", 1);
			jsonObj.put("main", 1);
			jsonObj.put("startRow", 0);
			jsonObj.put("pageSize", 0);
			jsonObj.put("columns", "textZX");
			InterfaceMessage interfacemessage = new InterfaceMessage();
			interfacemessage.setParam(jsonObj.toString());*/
			/*JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
					.queryComplexDoctorList(interfacemessage));*/
			/*JSONArray dcJson = JSONArray
					.fromObject(dcRt.getJSONArray("Result"));*/
			JSONObject rtid = new JSONObject();
			/*rtid.put("firstOpen", 0);
			rtid.put("price", 0);*/
		//	JSONObject dcBillJson = new JSONObject();
			//int countDoc = dcRt.getInt("Total");
			QuesMainVo qv = new QuesMainVo();
			qv.setASK_DoctorID(doctorID);
		//	int ct = quesMainService.querCountOverQus(qv);
			/*if (countDoc > 0 && ct<1  && qusVo.getQD_SourceType() == 1) {
				String serviceStatus = dcJson.getJSONObject(0).getString(
						"textZX");
				if (serviceStatus.equals("0")) {
				dcBillJson.put("doctorUid", doctorID);
				dcBillJson.put("serviceId", 5);
				dcBillJson.put("serviceRecordId", queID);
				ServiceResult<String> rtBill = doctorServiceApi
						.insertBill(dcBillJson.toString());
				if (rtBill.getCode() < 0) {
					return ApiUtil.getJsonRt(-14444, rtBill.getMessage());
				}
				rtid.put("firstOpen", 1);
				rtid.put("price", rtBill.getResult());
				}
			}*/
			JSONArray rtJr = new JSONArray();
			rtid.put("replyid", rt);
			rtJr.add(rtid);
			rtJson.put("Result", rtJr);
			JSONObject pushJson = new JSONObject();
			JSONObject mesJson = new JSONObject();
			mesJson.put("sourceid", qusVo.getASK_DoctorID());
			mesJson.put("interactType", "110002");// ҽ���ظ�
			mesJson.put("targetid", queID);
			mesJson.put("content", rt);
			pushJson.put("Type", "110002");// ҽ���ظ�
			pushJson.put("SendCode", qusVo.getASK_DoctorID());
			pushJson.put("SendKind", 11);
			pushJson.put("RecvKind", 10);
			pushJson.put("SendName", "APP");
			pushJson.put("sendWay", 1);
			if (platform == 0) {
				pushJson.put("RecvWay", "11");
			} else {
				pushJson.put("RecvWay", "12");
			}
			pushJson.put("AppKey", "7a2d788ee03598a0613d1272");
			pushJson.put("masterSecret", "bb1571722129212ce2a9c4ef");
			pushJson.put("RecvCode", qusVo.getASK_UserID());
			pushJson.put("Content", "APP");
			pushJson.put("ContentExtend", mesJson);
			// System.out.println(pushJson);
			MsgManagerService sms = Rpc.get(MsgManagerService.class, ConfigUtil
					.getInstance().getUrl("url.MsgManager"), 3000);
			InterfaceMessage pushMsg = new InterfaceMessage();
			pushMsg.setParam(pushJson.toString());
			String rtMsg ="";
			try {
				 rtMsg = sms.pushMsg(pushMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(rtMsg);
			}
			String sb1 = DateUtil.dateFormat(new Date(), DateUtil.YMD_FORMAT);
			String sb2 = sb1 + " 09:00:00";
			String sb3 = sb1 + " 22:00:00";
			if (DateUtil.getIfInTime(sb2, sb3)
					&& StringUtil.isNotEmpty(qusVo.getQD_TipPhone())) {
				if (!qusVo.getQD_TipPhone().equals("0")) {
					JSONObject jsObj = new JSONObject();
					jsObj.put("doctorUid", qusVo.getASK_DoctorID());
					jsObj.put("serviceStatusSearch", 3);
					// jsonObj.put("displayStatus", 1);
					jsObj.put("main", 1);
					jsObj.put("startRow", 0);
					jsObj.put("pageSize", 0);
					jsObj.put("columns", "doctorAccount,doctorName");
					InterfaceMessage dcMsg = new InterfaceMessage();
					dcMsg.setParam(jsObj.toString());
					JSONObject dcsRt = JSONObject.fromObject(doctorInfoApi
							.queryComplexDoctorList(dcMsg));
					JSONArray dcsJson = JSONArray.fromObject(dcsRt
							.getJSONArray("Result"));
					int countsDoc = dcsRt.getInt("Total");
					if (countsDoc < 0) {
						return ApiUtil.getJsonRt(10000, "��ȡ������Ϣʧ�ܡ�");
					}
					PublicForSmsService smsService = Rpc.get(
							PublicForSmsService.class, ConfigUtil.getInstance()
									.getUrl("url.smsgw"), 16000);
					Integer str;
					try {
						str = smsService
								.send(qusVo.getQD_TipPhone(),
										"���ã�������ѯ����"
												+ DateUtil.dateFormat(new Date(),
														DateUtil.YMDHMS_FORMAT)
												+ "�õ�"
												+ dcsJson.getJSONObject(0)
														.getString("doctorName")
												+ "ҽ���Ļظ����뾡���¼����֮·APP��ҽ�����鿴�����������Ҫ׷�ʣ�����ѯ����24Сʱ��رա�",
										10101210);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("���ŵ���ʧ�ܡ�");
					}
					
					
				}
			}
			// System.out.print(rtMsg);
			
			
			
			
			
			//�������� describe  �ֶ�
			String  describe="";
			
			try{
		
		    describe="����ѯ��@docҽ��   @dept����  �ѻظ�";
		    
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("doctorUid", doctorID);
			jsonObj.put("main", 1);
			jsonObj.put("pageIndex", 1);
			jsonObj.put("pageSize", 100);
			jsonObj.put("columns", "deptName,doctorName");

			JSONObject dcRt  =JSONObject.fromObject(postService.queryComplexDoctorList_v2(jsonObj.toString())) ;

			int code = dcRt.getInt("Code");
			String doctorName=null;
			String deptName =null;
			if(code > 0 ){
				JSONArray rts= dcRt.getJSONArray("Result");
				if(rts.size() == 0){
					System.out.println("δ�ҵ�ҽ����Ϣ��");
				}
				 doctorName = rts.getJSONObject(0).getString("doctorName");
				 deptName = rts.getJSONObject(0).getString("deptName");
				 
			}else{
				System.out.println("δ�ҵ�ҽ����Ϣ��");
			}
			
			System.out.println(doctorName+"-"+deptName);
			describe=describe.replace("@doc",doctorName );
			describe=describe.replace("@dept", deptName);
			System.out.println("real---"+describe);
			
	        }catch(Exception e){
				
	        	LOG.error(LoggerUtil.getTrace(e));
	        			
	        	return ApiUtil.getJsonRt(-14444,
						"  �޷���ѯ����ҽ����Ϣ   �� ����Զ�̽ӿ�  baseinfo.DoctorInfoApi.queryComplexDoctorList_v2   ʧ��)");
	        	
			}
			
			
			
			
			
			
			//����NEW
			JSONObject nMesJson = new JSONObject();
			JSONObject androidJson = new JSONObject();
			JSONObject iosJson = new JSONObject();
			androidJson.put("uri", "");
			androidJson.put("date",DateUtil.dateFormat(new Date()));
			androidJson.put("sourceId", userID);
			androidJson.put("sourceName", "");
			androidJson.put("contentId", rt);
			androidJson.put("content", messCont);
			androidJson.put("questionId", queID);
			//describe
			androidJson.put("describe", describe);
			
			if( qusVo.getQUESMAIN_Content().length() > 15 ){
				androidJson.put("title", qusVo.getQUESMAIN_Content().subSequence(0, 15) +"......");
			}else{
				androidJson.put("title", qusVo.getQUESMAIN_Content().subSequence(0,  qusVo.getQUESMAIN_Content().length()-1) +"......");
			}
			

			/*if(qusVo.getQD_DocReplayCount()>0){
				androidJson.put("title", "��һ������׷�ʣ������鿴��");
			}else{
				androidJson.put("title", "���յ�1������ѯ�������鿴��");
			}*/
			androidJson.put("operCode", "12000");
			androidJson.put("msgType", replyType);
			iosJson.put("questionId", queID);
			if( qusVo.getQUESMAIN_Content().length() > 15 ){
				iosJson.put("ti", qusVo.getQUESMAIN_Content().subSequence(0, 15) +"......");
			}else{
				iosJson.put("ti", qusVo.getQUESMAIN_Content().subSequence(0, qusVo.getQUESMAIN_Content().length()-1) +"......");
			}
						
			/*if(qusVo.getQD_DocReplayCount()>0){
				iosJson.put("ti", "��һ������׷�ʣ������鿴��");
			}else{
				iosJson.put("ti", "���յ�1������ѯ�������鿴��");
			}*/
			iosJson.put("operCode", 12000);
			iosJson.put("contentId", rt);
			iosJson.put("sourceId", userID);
			iosJson.put("sourceName", "");
			//describe
			iosJson.put("describe", describe);
			
			nMesJson.put("androidParam", androidJson);
			nMesJson.put("toAppType", 2);
			nMesJson.put("toUsers", userID);
			nMesJson.put("iosParam", iosJson);
			InterfaceMessage pushMsgN = new InterfaceMessage();
			pushMsgN.setParam(nMesJson.toString());
			JSONObject authinfo = new JSONObject();
			authinfo.put("ClientId", "client.myt");
			pushMsgN.setAuthInfo(authinfo.toString());
			String purt="";
			try {
				
				purt=postService.sendMsgJ(nMesJson.toString());
				System.out.print(purt);
			//	purt = msgApi.sendMsg(pushMsgN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(purt);
			}
			
			
			
			return rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * �ظ�����(�û�)
	 * 
	 * @param quesContent
	 * @param userId
	 * @return
	 */
	public String replyQuertionUser(InterfaceMessage msg) {
		String tag = "replyQuertionUser";
		try {
			com.common.json.JSONObject json = new com.common.json.JSONObject(
					msg.getParam());
			int replyType = json.get("replyType") == null ? null : json
					.getInt("replyType");
			int queID = json.get("queID") == null ? null : json.getInt("queID");
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			int userID = json.get("userID") == null ? null : json
					.getInt("userID");
			String messCont = json.get("messCont") == null ? null : json
					.getString("messCont");
			com.common.json.JSONArray fileMess = json.get("fileMess") == null ? null
					: json.getJSONArray("fileMess");
			int platform = json.get("platform") == null ? null : json
					.getInt("platform");
			int filesCount = json.get("filesCount") == null ? null : json
					.getInt("filesCount");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo.equals(null) || qusVo == null ) {
				return WsUtil.getRetVal(msg.getOutType(), -2000, "δ�����������.");
			}
			if(qusVo.getQD_Status()==1 || qusVo.getQD_Status()==2){
				return WsUtil.getRetVal(msg.getOutType(), -2000, "�����ѹر�.");
			}
			
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JdbcConnection conn = null;
			conn = DB.me().getConnection(MyDatabaseEnum.YiHuNet2008);
			conn.beginTransaction(5000);
			// ִ������
			ReplyVo reply = new ReplyVo();
			reply.setREPLY_CreateTime(DateUtil.dateFormat(new Date()));
			reply.setREPLY_Content(messCont);
			reply.setASK_UserID(userID);
			reply.setASK_QuesID(queID);
			reply.setASK_DoctorID(doctorID);
			reply.setREPLY_Price(Double.valueOf(qusVo.getQD_Price()));
			reply.setREPLY_Type(replyType);
			reply.setREPLY_Platform(platform);
			reply.setREPLY_ContentType(1);// ��������
			reply.setREPLY_Status(1);
			reply.setREPLY_CheckStatus(1);
			reply.setREPLY_CheckTime(DateUtil.dateFormat(new Date()));
			reply.setREPLY_CheckManID(0);
			reply.setREPLY_CheckManName("admin");
			reply.setREPLY_UserType(2);
			reply.setREPLY_IsLook(0);
			reply.setREPLY_FilesCount(filesCount);
			if (qusVo.getASK_DoctorID() > 0 && qusVo.getQD_DocReplayCount() > 0) {// ҽ���Ѿ��ظ�����Ϊ׷��
				reply.setREPLY_Price(Double.valueOf(0));
				reply.setREPLY_ContentType(0);
			}

			QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
			qrmVo.setQueID(queID);
			qrmVo = quesReplyMutualService.queryQuesReplyMutual(qrmVo);
			if (qrmVo == null) {
				qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(queID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(0);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			} else {
				if (qrmVo.getReplyType() == 0) {
					qrmVo.setReplyCount(qrmVo.getReplyCount() + 1);
				} else {
					qrmVo.setReplyType(0);
					qrmVo.setReplyCount(1);
				}
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				int rtQRM = quesReplyMutualService.updateQuesReplyMutual(qrmVo,
						conn);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			}
			int rt = replyService.insertReplyByCondition(reply, conn);
			if (rt < 0) {
				conn.rollback();
				return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�");
			}
			qusVo.setQD_IsUserReplay(1);
			qusVo.setQD_IsReplay(0);
			qusVo.setQD_IsAppAttend(1);
			if (filesCount > 0) {
				qusVo.setQD_FilesCount(qusVo.getQD_FilesCount() + filesCount);
			}
			int qurt = quesMainService.updateQMainByCondition(qusVo, conn);
			if (qurt < 0) {
				conn.rollback();
				return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�");
			}
			conn.commitTransaction(true);
			// ��������
			FilesVo file;

			String filsIds = "";
			if (filesCount > 0 && fileMess != null) {
				for (int i = 0; i < fileMess.length(); i++) {
					file = new FilesVo();
					file.setFILES_Status(fileMess.getJSONObject(i).getInt(
							"status"));
					file.setFILES_CreateTime(DateUtil.dateFormat(new Date()));
					file.setFILES_OldName(fileMess.getJSONObject(i).getString(
							"oldName"));
					file.setFILES_NewName(fileMess.getJSONObject(i).getString(
							"newName"));
					file.setFILES_TypeID(fileMess.getJSONObject(i).getInt(
							"typeID"));
					file.setFILES_ObjTypeID(fileMess.getJSONObject(i).getInt(
							"objTypeID"));
					file.setFILES_Path(fileMess.getJSONObject(i).getString(
							"path"));
					file.setFILES_Size(fileMess.getJSONObject(i).getInt("size"));
					file.setFILES_OperatorID(fileMess.getJSONObject(i).getInt(
							"operatorID"));
					int rtFlid = filesService.insertFiles(file);
					if (rtFlid < 0) {
						return ApiUtil.getJsonRt(-14444, "��������ʧ��.");
					}
					filsIds = filsIds + rtFlid + ",";
				}
			}

			// ���¸�����ID
			FilesVo fl;
			fl = new FilesVo();
			fl.setFILES_ObjID(rt);
			if (StringUtil.isNotEmpty(filsIds)) {
				filsIds = StringUtils.substringBeforeLast(filsIds, ",");
				int rtfl = filesService.updateFilesForQuesID(fl, filsIds);
				if (rtfl < 0) {
					return ApiUtil.getJsonRt(-14444, "���¸�����IDʧ�ܡ�");
				}
			}

			DocSubCloseQuesVo docSubClose = new DocSubCloseQuesVo();
			docSubClose.setQueID(queID);
			docSubClose.setEndTime(DateUtil.dateFormat(new Date()));
			docSubCloseQuesService.updateDocSubCloseQuesByQueID(docSubClose);

			// System.out.println(theNewPath);
			// System.out.println(rt);
			/*
			 * if (replyType == 2) { ReplyVo replyly = new ReplyVo();
			 * replyly.setREPLY_Content(theNewPath); replyly.setREPLY_ID(rt);
			 * int uprt = replyService.updateReplyByCondition(replyly); if (uprt
			 * < 0) { return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�"); } }
			 */
			JSONObject rtJson = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"�ɹ���"));
			if(qusVo.getQD_OrdersStatus() == 2|| qusVo.getQD_OrdersStatus() ==0 ||qusVo.getQD_OrdersStatus() ==5){
				JSONArray rtJr = new JSONArray();
				JSONObject rtid = new JSONObject();
				rtid.put("replyid", rt);
				rtJr.add(rtid);
				rtJson.put("Result", rtJr);

				JSONObject pushJson = new JSONObject();
				JSONObject mesJson = new JSONObject();
				mesJson.put("sourceid", qusVo.getASK_UserID());
				mesJson.put("interactType", "100000");// �û��ظ�
				mesJson.put("targetid", queID);
				mesJson.put("content", rt);
				pushJson.put("Type", "100000");// �û��ظ�
				pushJson.put("SendCode", qusVo.getASK_UserID());
				pushJson.put("SendKind", 10);
				pushJson.put("RecvKind", 11);
				pushJson.put("SendName", "APP");
				pushJson.put("SendWay", 1);
				if (platform == 0) {
					pushJson.put("RecvWay", "11");
				} else {
					pushJson.put("RecvWay", "12");
				}
				pushJson.put("AppKey", "554d8e9ac52715ddca89311a");
				pushJson.put("masterSecret", "97c59461bce86655c94f8f6f");
				pushJson.put("RecvCode", qusVo.getASK_DoctorID());
				pushJson.put("Content", "APP");
				pushJson.put("ContentExtend", mesJson);
				// System.out.println(pushJson);
				MsgManagerService sms = Rpc.get(MsgManagerService.class, ConfigUtil
						.getInstance().getUrl("url.MsgManager"), 3000);
				InterfaceMessage pushMsg = new InterfaceMessage();
				pushMsg.setParam(pushJson.toString());
				String rtMsg= "";
				try {
					  rtMsg = sms.pushMsg(pushMsg);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(rtMsg);
				}
				
				MsgApi msgApi = Rpc.get(MsgApi.class, ConfigUtil.getInstance().getUrl("url.jpushMsg"));
				JSONObject nMesJson = new JSONObject();
				JSONObject androidJson = new JSONObject();
				JSONObject iosJson = new JSONObject();
				androidJson.put("uri", "");
				androidJson.put("date",DateUtil.dateFormat(new Date()));
				androidJson.put("sourceId", userID);
				androidJson.put("sourceName", "");
				androidJson.put("contentId", rt);
				//��ȡmessCont    30 ����   �����Ļ���� ���Ļ���ȡ
				String  demo=messCont;
				StringBuffer sb = new StringBuffer(demo);
				if(sb.length()<=30){
					
					for (int i = sb.length(); i <30; i++) {
						sb.append(" ");
					}
				}
				System.out.println(sb.toString());
				String last =sb.substring(0,30);
				

				androidJson.put("content", last);
				androidJson.put("questionId", queID);
				System.out.println("OOOOOOO"+qusVo.getQUESMAIN_Content());
				if( qusVo.getQUESMAIN_Content().length() > 15 ){
					androidJson.put("title", qusVo.getQUESMAIN_Content().subSequence(0, 15) +"......");
				}else{
					androidJson.put("title", qusVo.getQUESMAIN_Content().subSequence(0,  qusVo.getQUESMAIN_Content().length()-1) +"......");
				}
				

				/*if(qusVo.getQD_DocReplayCount()>0){
					androidJson.put("title", "��һ������׷�ʣ������鿴��");
				}else{
					androidJson.put("title", "���յ�1������ѯ�������鿴��");
				}*/
				androidJson.put("operCode", "12000");
				androidJson.put("msgType", replyType);
				iosJson.put("questionId", queID);
				System.out.println();
				if( qusVo.getQUESMAIN_Content().length() > 15 ){
					iosJson.put("ti", qusVo.getQUESMAIN_Content().subSequence(0, 15) +"......");
				}else{
					iosJson.put("ti", qusVo.getQUESMAIN_Content().subSequence(0, qusVo.getQUESMAIN_Content().length()-1) +"......");
				}
							
				/*if(qusVo.getQD_DocReplayCount()>0){
					iosJson.put("ti", "��һ������׷�ʣ������鿴��");
				}else{
					iosJson.put("ti", "���յ�1������ѯ�������鿴��");
				}*/
				iosJson.put("operCode", 12000);
				iosJson.put("contentId", rt);
				iosJson.put("sourceId", userID);
				iosJson.put("sourceName", "");
				nMesJson.put("androidParam", androidJson);
				nMesJson.put("toAppType", 1);
				nMesJson.put("toUsers", doctorID);
				nMesJson.put("iosParam", iosJson);
				InterfaceMessage pushMsgN = new InterfaceMessage();
				pushMsgN.setParam(nMesJson.toString());
				JSONObject authinfo = new JSONObject();
				authinfo.put("ClientId", "client.myt");
				pushMsgN.setAuthInfo(authinfo.toString());
				String purt="";
				try {
					
					purt=postService.sendMsgJ(nMesJson.toString());
					
					JSONObject relback = JSONObject.fromObject(purt);
					
					int code =relback.getInt("Code");
					System.out.println("CODE:::"+code);
					if(code!=10000){
						LOG.error("������Ϣʧ��  replyQuertionUser�ظ����⣨�û������������� "+purt);
					}
					
				//	purt = msgApi.sendMsg(pushMsgN);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(purt);
				}
				
			}
			// System.out.println(rtMsg);
			return rtJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ����ѽ������������
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getCountQues(InterfaceMessage msg) {
		String tag = "getCountQues";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			Integer dept = json.get("deptID") == null ? null : json
					.getInt("deptID");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setASK_DoctorID(doctorUid);
			qusVo.setQD_AskDeptID(dept);
			int count = quesMainService.querCountOverQus(qusVo);
			return WsUtil.getRetVal(msg.getOutType(), 1, count + "");
		} catch (JSONException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ͳ��ҽ���Ѿ��ظ�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getReplyQues(InterfaceMessage msg) {
		String tag = "getReplyQues";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			ReplyVo rpl = new ReplyVo();
			rpl.setASK_DoctorID(doctorUid);
			rpl.setREPLY_UserType(1);
			rpl.setREPLY_Status(1);
			int count = replyService.queryReplyCount(rpl);
			return WsUtil.getRetVal(msg.getOutType(), 1, count + "");
		} catch (JSONException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ���������������
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getTeamQues(InterfaceMessage msg) {
		String tag = "getTeamQues";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer teamID = json.get("teamID") == null ? null : json
					.getInt("teamID");
			String time = json.get("time") == null ? null : json
					.getString("time");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQD_QuesType(2);
			qusVo.setQD_DocFreeID(teamID);
			com.common.json.JSONObject countDocs = quesMainService.querTeamQus(
					qusVo, time);
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", countDocs.getJSONArray("result"));
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ��������ID��ȡ������ϸ��Ϣ
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesContentByQuesID(InterfaceMessage msg) {
		String tag = "getQuesContentByQuesID";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quID = json.get("quID") == null ? null : json
					.getInt("quID");
			Integer userID = json.get("userID") == null ? null : json
					.getInt("userID");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(quID);
			qusVo.setASK_UserID(userID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONObject queJson = new JSONObject();
			queJson.put("doctorUid", qusVo.getASK_DoctorID());
			queJson.put("price", qusVo.getQD_Price());
			queJson.put("content", qusVo.getQUESMAIN_Content());
			queJson.put("tipPhone", qusVo.getQD_TipPhone());
			queJson.put("createTime", qusVo.getQUESMAIN_CreateTime());
			queJson.put("handid", qusVo.getQD_HandleID());
			queJson.put("filesCount", qusVo.getQD_FilesCount());
			queJson.put("platform", qusVo.getQUESMAIN_Platform());
			queJson.put("diseaseStr", qusVo.getQD_DiseaseStr());
			queJson.put("sourceType", qusVo.getQD_SourceType());
			queJson.put("pathogenesisTime", qusVo.getQD_PathogenesisTime());//����ʱ��
			queJson.put("illness", qusVo.getQD_Illness());//��Ҫ��֢
			queJson.put("treatmentExperience", qusVo.getQD_TreatmentExperience()); //���ﾭ��
			queJson.put("doctorGetPrice", qusVo.getQD_DoctorGetPrice());//ҽ����ȡ������
			
			FilesVo vo = new FilesVo();
			vo.setFILES_ObjID(quID);
			vo.setFILES_ObjTypeID(1);
			List<FilesVo> fileList = filesService.queryFilesListByCondition(vo);
			JSONArray jary = new JSONArray();
			for (FilesVo file : fileList) {
				JSONObject filejs = new JSONObject();
				filejs.put("Status", file.getFILES_Status());
				filejs.put("NewName", file.getFILES_NewName());
				filejs.put("TypeID", file.getFILES_TypeID());
				filejs.put("ObjTypeID ", file.getFILES_ObjTypeID());
				filejs.put("Path", file.getFILES_Path());
				filejs.put("Size", file.getFILES_Size());
				filejs.put("CreateTime", file.getFILES_CreateTime());
				filejs.put("ObjID", file.getFILES_ObjID());
				filejs.put("OperatorID", file.getFILES_OperatorID());
				filejs.put("OldName", file.getFILES_OldName());
				filejs.put("ID", file.getFILES_ID());
				jary.add(filejs);
			}
			queJson.put("fileMess", jary);
			DepartmentsVo deptVo = new DepartmentsVo();
			deptVo.setASK_QuesID(quID);
			JSONArray jaryDept = new JSONArray();
			List<DepartmentsVo> depts = departmentsService
					.queryDepartmentsListByCondition(deptVo);
			if (depts == null) {
				return ApiUtil.getJsonRt(-14444, "��ȡ�����б�ʧ�ܡ�");
			}
			for (DepartmentsVo dVo : depts) {
				JSONObject deptJson = new JSONObject();
				deptJson.put("departIDOne", dVo.getASK_DepartIDOne());
				deptJson.put("departIDTwo", dVo.getASK_DepartIDTwo());
				deptJson.put("departNameOne", dVo.getASK_DepartNameOne());
				deptJson.put("departNameTwo", dVo.getASK_DepartNameTwo());
				jaryDept.add(deptJson);
			}
			queJson.put("deptList", jaryDept);
			PatientVo pVo = new PatientVo();
			pVo.setPATIENT_ID(qusVo.getASK_PatientID());
			pVo = patientService.queryPatient(pVo);
			if (pVo == null) {
				return ApiUtil.getJsonRt(-14444, "δ��û�������.");
			}
			JSONObject pjson = new JSONObject();
			pjson.put("patname ", pVo.getPATIENT_Name());
			pjson.put("patsex", pVo.getPATIENT_Sex());
			pjson.put("patprvid", pVo.getASK_ProvinceID());
			pjson.put("patcityid", pVo.getASK_CityID());
			pjson.put("patprvname", pVo.getASK_ProvinceName());
			pjson.put("patcityname", pVo.getASK_CityName());
			pjson.put("patbirth", pVo.getPATIENT_Birth());
			queJson.put("patientMess", pjson);
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject jsonObj = new JSONObject();
			JSONObject docJson = new JSONObject();
			// System.out.println(que.getASK_DoctorID());
			if (qusVo.getASK_DoctorID() > 0) {
				jsonObj.put("doctorUid", qusVo.getASK_DoctorID());
				jsonObj.put("serviceStatusSearch", 3);
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put(
						"columns",
						"feeTemplateId,provinceShort,skill,textPrice,lczcName,hosDeptGuid,hosGuid,lczc,serviceStatus,provinceName,hospitalId,states,serviceStatusSearch,hosName,doctorGuid,doctorUid,doctorName,doctorSex,hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid,photoPrefix,photoUri");
				InterfaceMessage interfacemessage = new InterfaceMessage();
				interfacemessage.setParam(jsonObj.toString());
				JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
						.queryComplexDoctorList(interfacemessage));
				JSONArray dcJson = JSONArray.fromObject(dcRt
						.getJSONArray("Result"));
				int countDoc = dcRt.getInt("Total");

				if (countDoc > 0) {
					docJson.put("textPrice",
							dcJson.getJSONObject(0).get("textPrice"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("lczcName",
							dcJson.getJSONObject(0).get("lczcName"));
					docJson.put("hosDeptGuid",
							dcJson.getJSONObject(0).get("hosDeptGuid"));
					docJson.put("hosGuid",
							dcJson.getJSONObject(0).get("hosGuid"));
					docJson.put("lczc", dcJson.getJSONObject(0).get("lczc"));
					docJson.put("serviceStatus",
							dcJson.getJSONObject(0).get("serviceStatus"));
					docJson.put("provinceShort",
							dcJson.getJSONObject(0).get("provinceShort"));
					docJson.put("provinceName",
							dcJson.getJSONObject(0).get("provinceName"));
					docJson.put("hospitalId",
							dcJson.getJSONObject(0).get("hospitalId"));
					docJson.put("states", dcJson.getJSONObject(0).get("states"));
					docJson.put("serviceStatusSearch", dcJson.getJSONObject(0)
							.get("serviceStatusSearch"));
					docJson.put("textPrice",
							dcJson.getJSONObject(0).get("textPrice"));
					docJson.put("hosName",
							dcJson.getJSONObject(0).get("hosName"));
					docJson.put("doctorGuid",
							dcJson.getJSONObject(0).get("doctorGuid"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("doctorName",
							dcJson.getJSONObject(0).get("doctorName"));
					docJson.put("doctorSex",
							dcJson.getJSONObject(0).get("doctorSex"));
					docJson.put("hosDeptId",
							dcJson.getJSONObject(0).get("hosDeptId"));
					docJson.put("deptName",
							dcJson.getJSONObject(0).get("deptName"));
					docJson.put("standardDeptId",
							dcJson.getJSONObject(0).get("standardDeptId"));
					docJson.put("bigDepartmentName", dcJson.getJSONObject(0)
							.get("bigDepartmentName"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("photoPrefix",
							dcJson.getJSONObject(0).get("photoPrefix"));
					docJson.put("photoUri",
							dcJson.getJSONObject(0).get("photoUri"));
					docJson.put("feeTemplateId",
							dcJson.getJSONObject(0).get("feeTemplateId"));
				}
			}
			queJson.put("doctor", docJson);
			AssayResultMainVo aVo = new AssayResultMainVo();
			aVo.setASK_QuesID(quID);
			List<AssayResultMainVo> aVos = assayResultMainService
					.queryAssayResultMainListByCondition(aVo);
			if (aVos == null) {
				return ApiUtil.getJsonRt(-14444, "��д���浥����.");
			}
			JSONArray jarArm = new JSONArray();
			for (AssayResultMainVo avo : aVos) {
				JSONObject armJson = new JSONObject();
				armJson.put("aliasName", avo.getARM_AliasName());
				armJson.put("censorTime", avo.getARM_CensorTime());
				armJson.put("createTime", avo.getARM_CreateTime());
				armJson.put("hospitalName", avo.getARM_HospitalName());
				armJson.put("armID", avo.getARM_ID());
				armJson.put("name", avo.getARM_Name());
				armJson.put("operatorID", avo.getARM_OperatorID());
				armJson.put("order", avo.getARM_Order());
				armJson.put("quesType", avo.getARM_QuesType());
				armJson.put("remark", avo.getARM_Remark());
				armJson.put("status", avo.getARM_Status());
				armJson.put("quesID", avo.getASK_QuesID());
				armJson.put("atID", avo.getAT_ID());
				jarArm.add(armJson);
			}
			queJson.put("assayResultMess", jarArm);
			
			QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
			qrmVo.setQueID(quID);
			qrmVo = quesReplyMutualService.queryQuesReplyMutual(qrmVo);
			if (qrmVo == null) {
				qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(quID);
				qrmVo.setReplyCount(1);
				qrmVo.setReplyType(0);
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			} else {
				if (qrmVo.getReplyType() != 0) {
					qrmVo.setReplyType(0);
					qrmVo.setReplyCount(1);
					qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
					int rtQRM = quesReplyMutualService
							.updateQuesReplyMutual(qrmVo);
					if (rtQRM < 0) {
						return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
					}
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", queJson);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ��������ID��ȡ����ͻظ���ϸ��Ϣ�б�ģ��
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQueReplyByQuesID(InterfaceMessage msg) {
		String tag = "getQueReplyByQuesID";
		try {
			
			LOG.error("getQueReplyByQuesID     +++  ���++++"+msg.getParam());
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quID = json.get("quID") == null ? null : json
					.getInt("quID");
			Integer replyID = json.get("replyID") == null ? null : json
					.getInt("replyID");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			Integer pageIndex = json.get("pageIndex") == null ? null : json
					.getInt("pageIndex");
			Integer userType = json.get("userType") == null ? null : json
					.getInt("userType");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(quID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo.equals(null) || qusVo == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONObject queJson = new JSONObject();
			queJson.put("quID", qusVo.getQUESMAIN_ID());
			queJson.put("doctorUid", qusVo.getASK_DoctorID());
			queJson.put("price", qusVo.getQD_Price());
			queJson.put("content", qusVo.getQUESMAIN_Content());
			queJson.put("tipPhone", qusVo.getQD_TipPhone());
			queJson.put("createTime", qusVo.getQUESMAIN_CreateTime());
			queJson.put("handid", qusVo.getQD_HandleID());
			queJson.put("filesCount", qusVo.getQD_FilesCount());
			queJson.put("platform", qusVo.getQUESMAIN_Platform());
			queJson.put("diseaseStr", qusVo.getQD_DiseaseStr());
			queJson.put("userid", qusVo.getASK_UserID());
			queJson.put("sourceType", qusVo.getQD_SourceType());
			queJson.put("docReplyCount", qusVo.getQD_DocReplayCount());
			//new add
			
			//  ����ʱ��2
			queJson.put("havettime", qusVo.getQD_PathogenesisTime());
			//��Ҫ��֢
			queJson.put("mainill", qusVo.getQD_Illness());
			// ���ﾭ�� 
			queJson.put("texperience", qusVo.getQD_TreatmentExperience());
			
			//����״̬ add
			queJson.put("orderStatus", qusVo.getQD_OrdersStatus());
			//����״̬ add
			queJson.put("status", qusVo.getQD_Status());
			//
			
			queJson.put("QUESMAIN_ExpiredTime", qusVo.getQUESMAIN_ExpiredTime());
			
			
			
			
			
			JSONObject jsonObj = new JSONObject();
			// System.out.println(que.getASK_DoctorID());
			JSONObject docJson = new JSONObject();
			if (qusVo.getASK_DoctorID() > 0) {
				jsonObj.put("doctorUid", qusVo.getASK_DoctorID());
				jsonObj.put("erviceStatusSearch", 3);
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put(
						"columns",
						"skill,textPrice,lczcName,hosDeptGuid,hosGuid,lczc,serviceStatus,provinceName,hospitalId,states,serviceStatusSearch,hosName,doctorGuid,doctorUid,doctorName,doctorSex,hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid,photoPrefix,photoUri");
				InterfaceMessage interfacemessage = new InterfaceMessage();
				interfacemessage.setParam(jsonObj.toString());
				JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
						.queryComplexDoctorList(interfacemessage));
				JSONArray dcJson = JSONArray.fromObject(dcRt
						.getJSONArray("Result"));
				int countDoc = dcRt.getInt("Total");
				if (countDoc > 0) {
					docJson.put("skill", dcJson.getJSONObject(0).get("skill"));
					docJson.put("textPrice",
							dcJson.getJSONObject(0).get("textPrice"));
					docJson.put("lczcName",
							dcJson.getJSONObject(0).get("lczcName"));
					docJson.put("hosDeptGuid",
							dcJson.getJSONObject(0).get("hosDeptGuid"));
					docJson.put("hosGuid",
							dcJson.getJSONObject(0).get("hosGuid"));
					docJson.put("lczc", dcJson.getJSONObject(0).get("lczc"));
					docJson.put("serviceStatus",
							dcJson.getJSONObject(0).get("serviceStatus"));
					docJson.put("provinceName",
							dcJson.getJSONObject(0).get("provinceName"));
					docJson.put("hospitalId",
							dcJson.getJSONObject(0).get("hospitalId"));
					docJson.put("states", dcJson.getJSONObject(0).get("states"));
					docJson.put("serviceStatusSearch", dcJson.getJSONObject(0)
							.get("serviceStatusSearch"));
					docJson.put("hosName",
							dcJson.getJSONObject(0).get("hosName"));
					docJson.put("doctorGuid",
							dcJson.getJSONObject(0).get("doctorGuid"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("doctorName",
							dcJson.getJSONObject(0).get("doctorName"));
					docJson.put("doctorSex",
							dcJson.getJSONObject(0).get("doctorSex"));
					docJson.put("hosDeptId",
							dcJson.getJSONObject(0).get("hosDeptId"));
					docJson.put("deptName",
							dcJson.getJSONObject(0).get("deptName"));
					docJson.put("standardDeptId",
							dcJson.getJSONObject(0).get("standardDeptId"));
					docJson.put("bigDepartmentName", dcJson.getJSONObject(0)
							.get("bigDepartmentName"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("photoPrefix",
							dcJson.getJSONObject(0).get("photoPrefix"));
					docJson.put("photoUri",
							dcJson.getJSONObject(0).get("photoUri"));
					QuesMainVo qus = new QuesMainVo();
					qus.setASK_DoctorID(qusVo.getASK_DoctorID());
					int count = quesMainService.querCountOverQus(qus);
					docJson.put("doctorQuesCount", count);
				}
			}
			String url = "http://www.yihu.com";
			JSONArray jary = new JSONArray();
			JSONArray replysJson = new JSONArray();
			if (replyID == null || replyID == 0) {
				FilesVo vo = new FilesVo();
				vo.setFILES_ObjID(quID);
				vo.setFILES_ObjTypeID(1);
				List<FilesVo> fileList = filesService
						.queryFilesListByCondition(vo);
				for (FilesVo file : fileList) {
					JSONObject rpl = new JSONObject();
					rpl.put("content", file.getFILES_Path());
					String rUrl = file.getFILES_Path();
					if (rUrl != null) {
						if (rUrl.substring(0, 4).equals("http")) {
							rpl.put("content", file.getFILES_Path());
						} else {
							rpl.put("content", url + file.getFILES_Path());
						}
					}
					rpl.put("rpType", 2);
					rpl.put("userType", 2);
					rpl.put("rpType", 2);
					rpl.put("platform", 1);
					rpl.put("filesCount", 0);
					rpl.put("checkTime", file.getFILES_CreateTime());
					replysJson.add(rpl);
				}
			}
			queJson.put("fileMess", jary);
			PatientVo pVo = new PatientVo();
			pVo.setPATIENT_ID(qusVo.getASK_PatientID());
			pVo = patientService.queryPatient(pVo);
			if (pVo == null) {
				return ApiUtil.getJsonRt(-14444, "δ��û�������.");
			}
			JSONObject pjson = new JSONObject();
			pjson.put("patname", pVo.getPATIENT_Name());
			pjson.put("patsex", pVo.getPATIENT_Sex());
			pjson.put("patprvid", pVo.getASK_ProvinceID());
			pjson.put("patcityid", pVo.getASK_CityID());
			pjson.put("patprvname", pVo.getASK_ProvinceName());
			pjson.put("patcityname", pVo.getASK_CityName());
			pjson.put("patbirth", pVo.getPATIENT_Birth());
			BalanceReturnBean userBalance = accountService.getAllBalance(
					qusVo.getASK_UserID(), "12", "01");
			queJson.put("userBalance", userBalance.getAvailableBalance());
			ReplyVo reply = new ReplyVo();
			reply.setASK_QuesID(qusVo.getQUESMAIN_ID());
			reply.setREPLY_ID(replyID);
			List<ReplyVo> replys = replyService.queryReplyListByCondition(
					reply, pageIndex, pageSize);
			int total = replyService.queryReplyCount(reply);

			for (ReplyVo rp : replys) {
				JSONObject rply = new JSONObject();
				if (rp.getREPLY_Type() == 2 || rp.getREPLY_Type() == 1) {
					String rtUrl = rp.getREPLY_Content();
					if (rtUrl != null) {
						if (rtUrl.substring(0, 4).equals("http")) {
							rply.put("content", rp.getREPLY_Content());
						} else {
							rply.put("content", url + rp.getREPLY_Content());
						}
					}
				} else {
					rply.put("content", rp.getREPLY_Content());
				}
				rply.put("replyid", rp.getREPLY_ID());
				rply.put("userType", rp.getREPLY_UserType());
				rply.put("rpType", rp.getREPLY_Type());
				rply.put("platform", rp.getREPLY_Platform());
				rply.put("filesCount", rp.getREPLY_FilesCount());
				rply.put("checkTime", rp.getREPLY_CheckTime());
				replysJson.add(rply);
				if (rp.getREPLY_FilesCount() > 0 && rp.getREPLY_Type() == 3) {
					FilesVo file = new FilesVo();
					file.setFILES_ObjID(rp.getREPLY_ID());
					file.setFILES_ObjTypeID(2);
					List<FilesVo> fls = filesService
							.queryFilesListByCondition(file);
					for (FilesVo flvo : fls) {
						rply = new JSONObject();
						rply.put("replyid", rp.getREPLY_ID());
						if (flvo.getFILES_Path() != null) {
							if (flvo.getFILES_Path().substring(0, 4).equals("http")) {
								rply.put("content", flvo.getFILES_Path());
							} else {
								rply.put("content", url + flvo.getFILES_Path());
							}
						}else{
							rply.put("content","");
						}
						rply.put("userType", rp.getREPLY_UserType());
						rply.put("rpType", 2);
						rply.put("platform", rp.getREPLY_Platform());
						rply.put("filesCount", 0);
						rply.put("checkTime", rp.getREPLY_CheckTime());
						replysJson.add(rply);
					}
				}
			}
			queJson.put("replys", replysJson);
			queJson.put("doctor", docJson);
			queJson.put("patientMess", pjson);
			QuesReplyMutualVo qrmVo = new QuesReplyMutualVo();
			qrmVo.setQueID(quID);
			qrmVo = quesReplyMutualService.queryQuesReplyMutual(qrmVo);
			if (qrmVo == null) {
				qrmVo = new QuesReplyMutualVo();
				qrmVo.setQueID(quID);
				qrmVo.setReplyCount(0);
				if (userType == 0) {
					qrmVo.setReplyType(0);
				} else {
					qrmVo.setReplyType(1);
				}
				qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				qrmVo.setBeginTime(DateUtil.dateFormat(new Date()));
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				qrmVo.setEndTime(DateUtil.dateFormat(dt1));
				int rtQRM = quesReplyMutualService.insertQuesReplyMutual(qrmVo);
				if (rtQRM < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
				}
			} else {
				if (qrmVo.getReplyType() == 1 && userType == 0) {
					qrmVo.setReplyType(0);
					qrmVo.setReplyCount(0);
					qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
					int rtQRM = quesReplyMutualService
							.updateQuesReplyMutual(qrmVo);
					if (rtQRM < 0) {
						return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
					}
				} else if (qrmVo.getReplyType() == 0 && userType == 1) {
					qrmVo.setReplyType(1);
					qrmVo.setReplyCount(0);
					qrmVo.setLastUpdateTime(DateUtil.dateFormat(new Date()));
					int rtQRM = quesReplyMutualService
							.updateQuesReplyMutual(qrmVo);
					if (rtQRM < 0) {
						return ApiUtil.getJsonRt(-14444, "���ɽ�������ʧ�ܡ�");
					}
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", queJson);
			rt.put("Total", total);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ��������ID��ȡ����ͻظ���ϸ��Ϣ�б�ģ�飨�û���
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListByUser(InterfaceMessage msg) {
		String tag = "getQuesListByUser";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer userID = json.get("userID") == null ? null : json
					.getInt("userID");
			Integer listType = json.get("listType") == null ? null : json
					.getInt("listType");
			Integer start = json.get("start") == null ? null : json
					.getInt("start");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			QuesMainVo qusVo = new QuesMainVo();
 			// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
			if (listType == 0) {// �ѽ��
				qusVo.setQD_Statuss("1,2");
			} else if (listType == 3) {
				qusVo.setQD_Statuss("5");
			} else {// δ���
				qusVo.setQD_Statuss("0,3");
			}
			qusVo.setASK_UserID(userID);
			List<QuesMainVo> queVos = quesMainService
					.queryQuesMainListByStatus(qusVo, start, pageSize);
			int count = quesMainService.queryQuesMainListByStatusCountForUser(qusVo);
			if (queVos.equals(null) || queVos == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONArray queArray = new JSONArray();
			for (int i = 0; i < queVos.size(); i++) {
				JSONObject queJson = new JSONObject();
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				String s1 = DateUtil.dateFormat(new Date());
				String s2 = queVos.get(i).getQUESMAIN_ExpiredTime();
				String s3 = queVos.get(i).getQD_RecordExpiredTime();
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				java.util.Calendar c1 = java.util.Calendar.getInstance();
				java.util.Calendar c2 = java.util.Calendar.getInstance();
				java.util.Calendar c3 = java.util.Calendar.getInstance();
				c1.setTime(df.parse(s1));
				c2.setTime(df.parse(s2));
				c3.setTime(df.parse(s3));
				int result = c1.compareTo(c2);
				int result2 = c1.compareTo(c3);
				if (queVos.get(i).getQUESMAIN_Status() == 0) {
					queJson.put("statusMemo", "��ѯ�ѱ�ɾ��");
				} else if (queVos.get(i).getQD_CheckStatus() == 3){
					queJson.put("statusMemo", "�ύ�ر������");
				}else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& (queVos.get(i).getQD_Status() == 1 || queVos.get(i)
								.getQD_Status() == 2)
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0
								|| queVos.get(i).getQD_OrdersStatus() == 2 || queVos
								.get(i).getQD_OrdersStatus() == 5)) {
					if(queVos.get(i).getQD_OrdersStatus() == 5){
						queJson.put("statusMemo", "����ɣ����˿�");
					}else{
						queJson.put("statusMemo", "�����");
					}
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_OrdersStatus() == 5
						&& queVos.get(i).getQD_DocReplayCount() == 0
						&& queVos.get(i).getQD_Status() == 0 && (result >= 0))// ��ʱ�����˿�
				{
					queJson.put("statusMemo", "��ʱ�����˿�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_OrdersStatus() == 5
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_IsReplay() == 1
						&& queVos.get(i).getQD_IsUserReplay() == 0)// ���˿�ѻش�
				{
					queJson.put("statusMemo", "���˿�ѻش�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_IsReplay() == 1
						&& queVos.get(i).getQD_IsUserReplay() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0 || queVos
								.get(i).getQD_OrdersStatus() == 2))// ҽ���ѻش�
				{
					queJson.put("statusMemo", "ҽ���ѻش�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0
								|| queVos.get(i).getQD_OrdersStatus() == 2 || queVos
								.get(i).getQD_OrdersStatus() == 5)
						&& queVos.get(i).getQD_IsReplay() == 0
						&& queVos.get(i).getQD_IsUserReplay() == 1
						&& (result < 0))// �ȴ�ҽ���ظ���
				{
					queJson.put("statusMemo", "�ȴ�ҽ���ظ���");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0 || queVos
								.get(i).getQD_OrdersStatus() == 2)
						&& queVos.get(i).getQD_IsReplay() == 0
						&& queVos.get(i).getQD_IsUserReplay() == 1
						&& queVos.get(i).getQD_DocReplayCount() == 0
						&& (result > 0))// ��ʱ
				{
					queJson.put("statusMemo", "��ʱ");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_Price() > 0
						&& queVos.get(i).getQD_OrdersStatus() == 1
						&& queVos.get(i).getQD_DocReplayCount() == 0 
						&& queVos.get(i).getQD_DocFreeID() ==0)// δ֧��
				{
					queJson.put("statusMemo", "δ֧��");
				} else {
					if (queVos.get(i).getQUESMAIN_Status() == 1
							&& queVos.get(i).getQD_CheckStatus() == 0
							&& queVos.get(i).getQD_Status() == 5
							&& queVos.get(i).getQD_SourceType() == 1)// ��ѯ�ѳ���)
					{
						queJson.put("statusMemo", "��ѯ�ѳ���");
					} else {
						//queJson.put("statusMemo", "�ȴ�ҽ���ظ�");
						queJson.put("statusMemo", "���˿�");
					}
				}

				queJson.put("queID", queVos.get(i).getQUESMAIN_ID());
				queJson.put("doctorUid", queVos.get(i).getASK_DoctorID());
				queJson.put("price", queVos.get(i).getQD_Price());
				queJson.put("content", queVos.get(i).getQUESMAIN_Content());
				queJson.put("tipPhone", queVos.get(i).getQD_TipPhone());
				queJson.put("createTime", queVos.get(i)
						.getQUESMAIN_CreateTime());
				queJson.put("handid", queVos.get(i).getQD_HandleID());
				queJson.put("status", queVos.get(i).getQD_Status());
				queJson.put("reason", queVos.get(i).getQD_Reason());
				// queJson.put("filesCount", que.getQD_FilesCount());
				queJson.put("platform", queVos.get(i).getQUESMAIN_Platform());
				queJson.put("diseaseStr", queVos.get(i).getQD_DiseaseStr());
				queJson.put("sourceType", queVos.get(i).getQD_SourceType());
				queJson.put("orderStatus", queVos.get(i).getQD_OrdersStatus());
				if (queVos.get(i).getQD_OrdersStatus() == 5) {
					queJson.put("queStatusName", " ");
				} else if (queVos.get(i).getQD_Status() == 1
						|| queVos.get(i).getQD_Status() == 2) {
					queJson.put("queStatusName", "�ѽ��");
				} else {
					queJson.put("queStatusName", "δ���");
				}
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				if (queVos.get(i).getASK_DoctorID() != 0) {
					com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc
							.get(DoctorInfoApi.class, ConfigUtil.getInstance()
									.getUrl("url.baseinfo"));
					JSONObject jsonObj = new JSONObject();
					// System.out.println(que.getASK_DoctorID());
					jsonObj.put("doctorUid", queVos.get(i).getASK_DoctorID());
					jsonObj.put("erviceStatusSearch", 3);
					// jsonObj.put("displayStatus", 1);
					jsonObj.put("main", 1);
					jsonObj.put("startRow", 0);
					jsonObj.put("pageSize", 0);
					jsonObj.put(
							"columns",
							"hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid,doctorName,photoPrefix,photoUri,doctorSex");
					InterfaceMessage interfacemessage = new InterfaceMessage();
					interfacemessage.setParam(jsonObj.toString());
					JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
							.queryComplexDoctorList(interfacemessage));
					JSONArray dcJson = JSONArray.fromObject(dcRt
							.getJSONArray("Result"));
					int countDoc = dcRt.getInt("Total");
					if (countDoc > 0) {
						queJson.put("doctorSex",
								dcJson.getJSONObject(0).get("doctorSex"));
						queJson.put("hosDeptId",
								dcJson.getJSONObject(0).get("hosDeptId"));
						queJson.put("deptName",
								dcJson.getJSONObject(0).get("deptName"));
						queJson.put("standardDeptId", dcJson.getJSONObject(0)
								.get("standardDeptId"));
						queJson.put("bigDepartmentName", dcJson
								.getJSONObject(0).get("bigDepartmentName"));
						queJson.put("doctorUid",
								dcJson.getJSONObject(0).get("doctorUid"));
						queJson.put("doctorName",
								dcJson.getJSONObject(0).get("doctorName"));
						queJson.put("photoPrefix",
								dcJson.getJSONObject(0).get("photoPrefix"));
						queJson.put("photoUri",
								dcJson.getJSONObject(0).get("photoUri"));
					} else {
						queJson.put("hosDeptId", 0);
						queJson.put("deptName", "");
						queJson.put("standardDeptId", 0);
						queJson.put("bigDepartmentName", "");
						queJson.put("doctorUid", 0);
						queJson.put("doctorName", "");
						queJson.put("photoPrefix", "");
						queJson.put("photoUri", "");
					}
				} else {
					queJson.put("hosDeptId", 0);
					queJson.put("deptName", "");
					queJson.put("standardDeptId", 0);
					queJson.put("bigDepartmentName", "");
					queJson.put("doctorUid", 0);
					queJson.put("doctorName", "");
					queJson.put("photoPrefix", "");
					queJson.put("photoUri", "");
				}
				QuesReplyMutualVo quesRMVo = new QuesReplyMutualVo();
				quesRMVo.setQueID(queVos.get(i).getQUESMAIN_ID());
				quesRMVo = quesReplyMutualService
						.queryQuesReplyMutual(quesRMVo);
				if (quesRMVo != null) {
					if (quesRMVo.getReplyType() == 1) {
						queJson.put("quesReplyMutual", quesRMVo.getReplyCount());
					} else {
						queJson.put("quesReplyMutual", 0);
					}
				} else {
					queJson.put("quesReplyMutual", 0);
				}
				ReplyVo repVo = new ReplyVo();
				repVo.setASK_QuesID(queVos.get(i).getQUESMAIN_ID());
				repVo = replyService.queryReplyLastOne(repVo);
				if (repVo != null) {
					queJson.put("lastReply", repVo.getREPLY_Content());
					queJson.put("lastReplyType", repVo.getREPLY_Type());
				} else {
					queJson.put("lastReply", "");
					queJson.put("lastReplyType", 0);
				}
				queArray.add(queJson);

			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Total", count);
			rt.put("Result", queArray);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	
	/**
	 * ��������ID��ȡ����ͻظ���ϸ��Ϣ�б�ģ����ݶ���˻����û���
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListByUserV2(InterfaceMessage msg) {
		String tag = "getQuesListByUserV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer userID = json.get("userID") == null ? null : json
					.getInt("userID");
			Integer listType = json.get("listType") == null ? null : json
					.getInt("listType");
			Integer start = json.get("start") == null ? null : json
					.getInt("start");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			String userIDs = json.get("userIDs") == null ? null : json
					.getString("userIDs");
			QuesMainVo qusVo = new QuesMainVo();
 			// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
			if (listType == 0) {// �ѽ��
				qusVo.setQD_Statuss("1,2");
			} else if (listType == 3) {
				qusVo.setQD_Statuss("5");
			} else {// δ���
				qusVo.setQD_Statuss("0,3");
			}
			if(StringUtil.isEmpty(userID)){
				qusVo.setUserIDs(userIDs);
			}else{
				qusVo.setASK_UserID(userID);
			}
			List<QuesMainVo> queVos = quesMainService
					.queryQuesMainListByStatus(qusVo, start, pageSize);
			int count = quesMainService.queryQuesMainListByStatusCountForUser(qusVo);
			if (queVos.equals(null) || queVos == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONArray queArray = new JSONArray();
			for (int i = 0; i < queVos.size(); i++) {
				JSONObject queJson = new JSONObject();
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				String s1 = DateUtil.dateFormat(new Date());
				String s2 = queVos.get(i).getQUESMAIN_ExpiredTime();
				String s3 = queVos.get(i).getQD_RecordExpiredTime();
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				java.util.Calendar c1 = java.util.Calendar.getInstance();
				java.util.Calendar c2 = java.util.Calendar.getInstance();
				java.util.Calendar c3 = java.util.Calendar.getInstance();
				c1.setTime(df.parse(s1));
				c2.setTime(df.parse(s2));
				c3.setTime(df.parse(s3));
				int result = c1.compareTo(c2);
				int result2 = c1.compareTo(c3);
				if (queVos.get(i).getQUESMAIN_Status() == 0) {
					queJson.put("statusMemo", "��ѯ�ѱ�ɾ��");
				} else if (queVos.get(i).getQD_CheckStatus() == 3){
					queJson.put("statusMemo", "�ύ�ر������");
				}else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& (queVos.get(i).getQD_Status() == 1 || queVos.get(i)
								.getQD_Status() == 2)
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0
								|| queVos.get(i).getQD_OrdersStatus() == 2 || queVos
								.get(i).getQD_OrdersStatus() == 5)) {
					if(queVos.get(i).getQD_OrdersStatus() == 5){
						queJson.put("statusMemo", "����ɣ����˿�");
					}else{
						queJson.put("statusMemo", "�����");
					}
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_OrdersStatus() == 5
						&& queVos.get(i).getQD_DocReplayCount() == 0
						&& queVos.get(i).getQD_Status() == 0 && (result >= 0))// ��ʱ�����˿�
				{
					queJson.put("statusMemo", "��ʱ�����˿�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_OrdersStatus() == 5
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_IsReplay() == 1
						&& queVos.get(i).getQD_IsUserReplay() == 0)// ���˿�ѻش�
				{
					queJson.put("statusMemo", "���˿�ѻش�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_DocReplayCount() > 0
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_IsReplay() == 1
						&& queVos.get(i).getQD_IsUserReplay() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0 || queVos
								.get(i).getQD_OrdersStatus() == 2))// ҽ���ѻش�
				{
					queJson.put("statusMemo", "ҽ���ѻش�");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0
								|| queVos.get(i).getQD_OrdersStatus() == 2 || queVos
								.get(i).getQD_OrdersStatus() == 5)
						&& queVos.get(i).getQD_IsReplay() == 0
						&& queVos.get(i).getQD_IsUserReplay() == 1
						&& (result < 0))// �ȴ�ҽ���ظ���
				{
					queJson.put("statusMemo", "�ȴ�ҽ���ظ���");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& (queVos.get(i).getQD_OrdersStatus() == 0 || queVos
								.get(i).getQD_OrdersStatus() == 2)
						&& queVos.get(i).getQD_IsReplay() == 0
						&& queVos.get(i).getQD_IsUserReplay() == 1
						&& queVos.get(i).getQD_DocReplayCount() == 0
						&& (result > 0))// ��ʱ
				{
					queJson.put("statusMemo", "��ʱ");
				} else if (queVos.get(i).getQUESMAIN_Status() == 1
						&& queVos.get(i).getQD_CheckStatus() == 1
						&& queVos.get(i).getQD_Status() == 0
						&& queVos.get(i).getQD_Price() > 0
						&& queVos.get(i).getQD_OrdersStatus() == 1
						&& queVos.get(i).getQD_DocReplayCount() == 0 
						&& queVos.get(i).getQD_DocFreeID() ==0)// δ֧��
				{
					queJson.put("statusMemo", "δ֧��");
				} else {
					if (queVos.get(i).getQUESMAIN_Status() == 1
							&& queVos.get(i).getQD_CheckStatus() == 0
							&& queVos.get(i).getQD_Status() == 5
							&& queVos.get(i).getQD_SourceType() == 1)// ��ѯ�ѳ���)
					{
						queJson.put("statusMemo", "��ѯ�ѳ���");
					} else {
						queJson.put("statusMemo", "�ȴ�ҽ���ظ�");
					}
				}

				queJson.put("queID", queVos.get(i).getQUESMAIN_ID());
				queJson.put("doctorUid", queVos.get(i).getASK_DoctorID());
				queJson.put("price", queVos.get(i).getQD_Price());
				queJson.put("content", queVos.get(i).getQUESMAIN_Content());
				queJson.put("tipPhone", queVos.get(i).getQD_TipPhone());
				queJson.put("createTime", queVos.get(i)
						.getQUESMAIN_CreateTime());
				queJson.put("handid", queVos.get(i).getQD_HandleID());
				queJson.put("status", queVos.get(i).getQD_Status());
				queJson.put("reason", queVos.get(i).getQD_Reason());
				// queJson.put("filesCount", que.getQD_FilesCount());
				queJson.put("platform", queVos.get(i).getQUESMAIN_Platform());
				queJson.put("diseaseStr", queVos.get(i).getQD_DiseaseStr());
				queJson.put("sourceType", queVos.get(i).getQD_SourceType());
				queJson.put("orderStatus", queVos.get(i).getQD_OrdersStatus());
				queJson.put("userID", queVos.get(i).getASK_UserID());
				if (queVos.get(i).getQD_OrdersStatus() == 5) {
					queJson.put("queStatusName", "���˿�");
				} else if (queVos.get(i).getQD_Status() == 1
						|| queVos.get(i).getQD_Status() == 2) {
					queJson.put("queStatusName", "�ѽ��");
				} else {
					queJson.put("queStatusName", "δ���");
				}
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				if (queVos.get(i).getASK_DoctorID() != 0) {
					com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc
							.get(DoctorInfoApi.class, ConfigUtil.getInstance()
									.getUrl("url.baseinfo"));
					JSONObject jsonObj = new JSONObject();
					// System.out.println(que.getASK_DoctorID());
					jsonObj.put("doctorUid", queVos.get(i).getASK_DoctorID());
					jsonObj.put("erviceStatusSearch", 3);
					// jsonObj.put("displayStatus", 1);
					jsonObj.put("main", 1);
					jsonObj.put("startRow", 0);
					jsonObj.put("pageSize", 0);
					jsonObj.put(
							"columns",
							"hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid,doctorName,photoPrefix,photoUri,doctorSex");
					InterfaceMessage interfacemessage = new InterfaceMessage();
					interfacemessage.setParam(jsonObj.toString());
					JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
							.queryComplexDoctorList(interfacemessage));
					JSONArray dcJson = JSONArray.fromObject(dcRt
							.getJSONArray("Result"));
					int countDoc = dcRt.getInt("Total");
					if (countDoc > 0) {
						queJson.put("doctorSex",
								dcJson.getJSONObject(0).get("doctorSex"));
						queJson.put("hosDeptId",
								dcJson.getJSONObject(0).get("hosDeptId"));
						queJson.put("deptName",
								dcJson.getJSONObject(0).get("deptName"));
						queJson.put("standardDeptId", dcJson.getJSONObject(0)
								.get("standardDeptId"));
						queJson.put("bigDepartmentName", dcJson
								.getJSONObject(0).get("bigDepartmentName"));
						queJson.put("doctorUid",
								dcJson.getJSONObject(0).get("doctorUid"));
						queJson.put("doctorName",
								dcJson.getJSONObject(0).get("doctorName"));
						queJson.put("photoPrefix",
								dcJson.getJSONObject(0).get("photoPrefix"));
						queJson.put("photoUri",
								dcJson.getJSONObject(0).get("photoUri"));
					} else {
						queJson.put("hosDeptId", 0);
						queJson.put("deptName", "");
						queJson.put("standardDeptId", 0);
						queJson.put("bigDepartmentName", "");
						queJson.put("doctorUid", 0);
						queJson.put("doctorName", "");
						queJson.put("photoPrefix", "");
						queJson.put("photoUri", "");
					}
				} else {
					queJson.put("hosDeptId", 0);
					queJson.put("deptName", "");
					queJson.put("standardDeptId", 0);
					queJson.put("bigDepartmentName", "");
					queJson.put("doctorUid", 0);
					queJson.put("doctorName", "");
					queJson.put("photoPrefix", "");
					queJson.put("photoUri", "");
				}
				QuesReplyMutualVo quesRMVo = new QuesReplyMutualVo();
				quesRMVo.setQueID(queVos.get(i).getQUESMAIN_ID());
				quesRMVo = quesReplyMutualService
						.queryQuesReplyMutual(quesRMVo);
				if (quesRMVo != null) {
					if (quesRMVo.getReplyType() == 1) {
						queJson.put("quesReplyMutual", quesRMVo.getReplyCount());
					} else {
						queJson.put("quesReplyMutual", 0);
					}
				} else {
					queJson.put("quesReplyMutual", 0);
				}
				ReplyVo repVo = new ReplyVo();
				repVo.setASK_QuesID(queVos.get(i).getQUESMAIN_ID());
				repVo = replyService.queryReplyLastOne(repVo);
				if (repVo != null) {
					queJson.put("lastReply", repVo.getREPLY_Content());
					queJson.put("lastReplyType", repVo.getREPLY_Type());
				} else {
					queJson.put("lastReply", "");
					queJson.put("lastReplyType", 0);
				}
				queArray.add(queJson);

			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Total", count);
			rt.put("Result", queArray);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			LOG.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error(LoggerUtil.getTrace(e));
			
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	/**
	 * ��������ID��ȡ����ͻظ���ϸ��Ϣ�б�ģ�飨ҽ����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListByDoctor(InterfaceMessage msg) {
		String tag = "getQuesListByDoctor";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			Integer listType = json.get("listType") == null ? null : json
					.getInt("listType");
			Integer start = json.get("start") == null ? null : json
					.getInt("start");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			QuesMainVo qusVo = new QuesMainVo();
			// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
			if (listType == 0) {// �ѽ��
				qusVo.setQD_Statuss("1,2");
			} else {// δ���
				qusVo.setQD_Statuss("0,3");
			}
			qusVo.setASK_DoctorID(doctorID);
			List<QuesMainVo> queVos = quesMainService
					.queryQuesMainListForDoctor(qusVo, start, pageSize);
			int count = quesMainService.queryQuesMainListByStatusCount(qusVo);
			if (queVos.equals(null) || queVos == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONArray queArray = new JSONArray();
			for (int i = 0; i < queVos.size(); i++) {
				JSONObject queJson = new JSONObject();
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				if (queVos.get(i).getQD_Status() == 0) {
					queJson.put("statusMemo", "δ���");
				} else if (queVos.get(i).getQD_Status() == 1) {
					queJson.put("statusMemo", "�ѽ��δ����");
				} else if (queVos.get(i).getQD_Status() == 2) {
					queJson.put("statusMemo", "�ѽ��δ����");
				} else if (queVos.get(i).getQD_Status() == 3) {
					queJson.put("statusMemo", "δ����");
				} else if (queVos.get(i).getQD_Status() == 4) {
					queJson.put("statusMemo", "ʧЧ");
				} else {
					queJson.put("statusMemo", "����");
				}
				queJson.put("queID", queVos.get(i).getQUESMAIN_ID());
				queJson.put("doctorUid", queVos.get(i).getASK_DoctorID());
				queJson.put("price", queVos.get(i).getQD_Price());
				queJson.put("content", queVos.get(i).getQUESMAIN_Content());
				queJson.put("tipPhone", queVos.get(i).getQD_TipPhone());
				queJson.put("createTime", queVos.get(i)
						.getQUESMAIN_CreateTime());
				queJson.put("handid", queVos.get(i).getQD_HandleID());
				queJson.put("status", queVos.get(i).getQD_Status());
				queJson.put("filesCount", queVos.get(i).getQD_FilesCount());
				queJson.put("platform", queVos.get(i).getQUESMAIN_Platform());
				queJson.put("diseaseStr", queVos.get(i).getQD_DiseaseStr());
				queJson.put("sourceType", queVos.get(i).getQD_SourceType());
				queJson.put("orderStatus", queVos.get(i).getQD_OrdersStatus());

				if (queVos.get(i).getQD_OrdersStatus() == 5) {
					queJson.put("queStatusName", "���˿�");
				} else if (queVos.get(i).getQD_Status() == 1
						|| queVos.get(i).getQD_Status() == 2) {
					queJson.put("queStatusName", "�ѽ��");
				} else {
					queJson.put("queStatusName", "δ���");
				}
				// 1�ѽ��δ���� 0δ��� 2�ѽ�������� 3δ���� 4ʧЧ 5����
				if (queVos.get(i).getASK_DoctorID() != 0) {
					com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc
							.get(DoctorInfoApi.class, ConfigUtil.getInstance()
									.getUrl("url.baseinfo"));
					JSONObject jsonObj = new JSONObject();
					// System.out.println(que.getASK_DoctorID());
					jsonObj.put("doctorUid", queVos.get(i).getASK_DoctorID());
					jsonObj.put("erviceStatusSearch", 3);
					// jsonObj.put("displayStatus", 1);
					jsonObj.put("main", 1);
					jsonObj.put("startRow", 0);
					jsonObj.put("pageSize", 0);
					jsonObj.put(
							"columns",
							"hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid ,doctorName,photoPrefix,photoUri");
					InterfaceMessage interfacemessage = new InterfaceMessage();
					interfacemessage.setParam(jsonObj.toString());
					JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
							.queryComplexDoctorList(interfacemessage));
					JSONArray dcJson = JSONArray.fromObject(dcRt
							.getJSONArray("Result"));
					int countDoc = dcRt.getInt("Total");
					if (countDoc > 0) {
						queJson.put("doctorSex",
								dcJson.getJSONObject(0).get("doctorSex"));
						queJson.put("hosDeptId",
								dcJson.getJSONObject(0).get("hosDeptId"));
						queJson.put("deptName",
								dcJson.getJSONObject(0).get("deptName"));
						queJson.put("standardDeptId", dcJson.getJSONObject(0)
								.get("standardDeptId"));
						queJson.put("bigDepartmentName", dcJson
								.getJSONObject(0).get("bigDepartmentName"));
						queJson.put("doctorUid",
								dcJson.getJSONObject(0).get("doctorUid"));
						queJson.put("doctorName",
								dcJson.getJSONObject(0).get("doctorName"));
						queJson.put("photoPrefix",
								dcJson.getJSONObject(0).get("photoPrefix"));
						queJson.put("photoUri",
								dcJson.getJSONObject(0).get("photoUri"));
					} else {
						queJson.put("hosDeptId", 0);
						queJson.put("deptName", "");
						queJson.put("standardDeptId", 0);
						queJson.put("bigDepartmentName", "");
						queJson.put("doctorUid", 0);
						queJson.put("doctorName", "");
						queJson.put("photoPrefix", "");
						queJson.put("photoUri", "");
					}
				} else {
					queJson.put("hosDeptId", 0);
					queJson.put("deptName", "");
					queJson.put("standardDeptId", 0);
					queJson.put("bigDepartmentName", "");
					queJson.put("doctorUid", 0);
					queJson.put("doctorName", "");
					queJson.put("photoPrefix", "");
					queJson.put("photoUri", "");
				}
				QuesReplyMutualVo quesRMVo = new QuesReplyMutualVo();
				quesRMVo.setQueID(queVos.get(i).getQUESMAIN_ID());
				quesRMVo = quesReplyMutualService
						.queryQuesReplyMutual(quesRMVo);
				if (quesRMVo != null) {
					if (quesRMVo.getReplyType() == 0) {
						queJson.put("quesReplyMutual", quesRMVo.getReplyCount());
					} else {
						queJson.put("quesReplyMutual", 0);
					}
				} else {
					queJson.put("quesReplyMutual", 0);
				}
				ReplyVo repVo = new ReplyVo();
				repVo.setASK_QuesID(queVos.get(i).getQUESMAIN_ID());
				repVo = replyService.queryReplyLastOne(repVo);
				if (repVo != null) {
					queJson.put("lastReply", repVo.getREPLY_Content());
					queJson.put("lastReplyType", repVo.getREPLY_Type());
				} else {
					queJson.put("lastReply", "");
					queJson.put("lastReplyType", 0);
				}
				queArray.add(queJson);
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Total", count);
			rt.put("Result", queArray);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ҽ���ύ12Сʱ��ر�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String subDocClosedQues(InterfaceMessage msg) {
		String tag = "subDocClosedQues";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			DocSubCloseQuesVo dsc = new DocSubCloseQuesVo();
			dsc.setQueID(queID);
			dsc = docSubCloseQuesService.queryDocSubCloseQues(dsc);
			if (dsc == null) {
				dsc = new DocSubCloseQuesVo();
				dsc.setQueID(queID);
			}
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(new Date());
			rightNow.add(Calendar.YEAR, 100);
			Date dt1 = rightNow.getTime();
			dsc.setBeginTime(DateUtil.dateFormat(new Date()));
			dsc.setEndTime(DateUtil.dateFormat(dt1));
			dsc.setLastUpdateTime(DateUtil.dateFormat(new Date()));
			if (dsc.getDocSubCloseQuesID() != null) {
				int rtdc = docSubCloseQuesService
						.updateDocSubCloseQuesByQueID(dsc);
				if (rtdc < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɹر�����ʧ��");
				}
			} else {
				int rtdc = docSubCloseQuesService.insertDocSubCloseQues(dsc);
				if (rtdc < 0) {
					return ApiUtil.getJsonRt(-14444, "���ɹر�����ʧ��");
				}
			}

			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queID);
			qvo.setQD_Status(1);// �ı�״̬Ϊ���ڴ���
			qvo.setQD_CheckManID(0);// �ı�״̬Ϊ���ڴ���
			qvo.setQD_CheckStatus(3);
			int qurt = quesMainService.updateQuesMain(qvo);
			if (qurt < 0) {
				return WsUtil.getRetVal(msg.getOutType(), -14444, "��������״̬ʧ��");
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ҽ���ύ12Сʱ��ر�����
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String subDocClosedQuesV2(InterfaceMessage msg) {
		String tag = "subDocClosedQuesV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			QuesMainVo qvo = new QuesMainVo();

			qvo.setQUESMAIN_ID(queID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			qvo.setOpenAuthFlag(null);
			ConsumerOrdersVo co = new ConsumerOrdersVo();
			co.setASK_QuesID(queID);
			co = consumerOrdersService.queryConsumerOrdersByQuesID(co);
			
			QuesCloseWaterVo vo = new QuesCloseWaterVo();
			
			vo.setCreateTime(DateUtil.dateFormat(new Date()));
			vo.setQuesID(queID);

			if( qvo.getQD_Status() == 3 ){
				return WsUtil.getRetVal(msg.getOutType(), -10001,
						"����������˽׶Σ����Եȣ�");
			}
			if( qvo.getQD_Status() != 0 ){
				return WsUtil.getRetVal(msg.getOutType(), -10001,
						"����������˽׶Σ����Եȣ�");
			}
			if ( qvo.getQD_Price() > 0 ) {
				vo.setOperType(3);// ҽ���ر�
				DocSubCloseQuesVo dsc = new DocSubCloseQuesVo();
				dsc.setQueID(queID);
				dsc = docSubCloseQuesService.queryDocSubCloseQues(dsc);
				if (dsc == null) {
					dsc = new DocSubCloseQuesVo();
					dsc.setQueID(queID);
				}
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.YEAR, 100);
				Date dt1 = rightNow.getTime();
				dsc.setBeginTime(DateUtil.dateFormat(new Date()));
				dsc.setEndTime(DateUtil.dateFormat(dt1));
				dsc.setLastUpdateTime(DateUtil.dateFormat(new Date()));
				if (dsc.getDocSubCloseQuesID() != null) {
					int rtdc = docSubCloseQuesService
							.updateDocSubCloseQuesByQueID(dsc);
					if (rtdc < 0) {
						return ApiUtil.getJsonRt(-10002, "���ɹر�����ʧ��");
					}
				} else {
					int rtdc = docSubCloseQuesService.insertDocSubCloseQues(dsc);
					if (rtdc < 0) {
						return ApiUtil.getJsonRt(-10002, "���ɹر�����ʧ��");
					}
				}

				QuesMainVo qv = new QuesMainVo();
				qv.setOpenAuthFlag(null);
				qv.setQUESMAIN_ID(queID);
				qv.setQD_Status(1);// �ı�״̬Ϊ���ڴ���
				qv.setQD_CheckManID(0);// �ı�״̬Ϊ���ڴ���
				qv.setQD_CheckStatus(3);
				int qurt = quesMainService.updateQuesMain(qv);
				if (qurt < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444, "��������״̬ʧ��");
				}
				int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
				if(rCont ==0){
					int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
					if (rtqu < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10003,
								"����ر�ʱ�����״̬ʧ��");
					}
				}
				return ApiUtil.getJsonRt(10000, "�ɹ�");
			}else{
				vo.setOperType(2);// ���˿�ر���Ϊϵͳ�ر�
				qvo.setQUESMAIN_ID(queID);
				qvo.setQD_Status(1);
				qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
				int qurt = quesMainService.updateQuesMain(qvo);
				if (qurt < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"��������״̬ʧ��");
				}
				com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc
						.get(DoctorAccountApi.class, ConfigUtil
								.getInstance().getUrl("url.baseinfo"));
				// ����ǻظ����Ĺ������� �ͼӰ���ֵ
				if (qvo.getQD_OrdersStatus() == 5) {
					// ����ֵ����(������������ˮ)
					JSONObject axJson = new JSONObject();
					axJson.put("doctorUid", qvo.getASK_DoctorID());
					axJson.put("resId", queID);
					axJson.put("typeId", 4);
					ServiceResult<String> addlrt = doctorAccountApi
							.addDoctorLove(axJson.toString());
					if (addlrt.getCode() < 0) {
						return ApiUtil.getJsonRt(-10010, "����ֵ��ˮ����ʧ�ܡ�");
					}
					CreditsRecordVo crVo = new CreditsRecordVo();
					crVo.setASK_QuesID(queID);
					crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
					crVo.setASK_DoctorID(qvo.getASK_DoctorID());
					crVo.setCR_Credits(1);
					crVo.setCR_CreditsType(2);
					crVo.setCR_Type(3);
					crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
					int rtCr = creditsRecordService
							.insertCreditsRecord(crVo);
					if (rtCr < 0) {
						return ApiUtil.getJsonRt(-10010, "����ֵ��ˮ����ʧ�ܡ�");
					}
				}
				int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
				if(rCont ==0){
					int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
					if (rtqu < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -10004,
								"����ر�ʱ�����״̬ʧ��");
					}
				}
				return ApiUtil.getJsonRt(10000, "�ɹ�");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	/**
	 * ҽ���б�
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListForDoctor(InterfaceMessage msg) {
		String tag = "getQuesListForDoctor";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer queStatus = json.get("queStatus") == null ? null : json
					.getInt("queStatus");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			Integer pageIndex = json.get("pageIndex") == null ? null : json
					.getInt("pageIndex");
			Integer doctorUid = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			String dept = json.get("deptTwoID") == null ? null : json
					.getString("deptTwoID");
			String groupDepts = json.get("groupDepts") == null ? null : json
					.getString("groupDepts");
			Integer hosDept = json.get("hosDept") == null ? null : json
					.getInt("hosDept");
			Integer cityID = json.get("cityID") == null ? null : json
					.getInt("cityID");
			Integer provinceID = json.get("provinceID") == null ? null : json
					.getInt("provinceID");
			/*com.yihu.baseinfo.api.IDoctorGroupApi doctorGroupApi = Rpc.get(
					IDoctorGroupApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));*/
			com.yihu.baseinfo.api.HosDeptApi hosDeptApi = Rpc.get(
					HosDeptApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			QuesMainVo qmv = new QuesMainVo();
			qmv.setASK_DoctorID(doctorUid);
			qmv.setQD_DeptTwoIDS(dept);
			qmv.setQD_AskDeptID(hosDept);
			qmv.setQD_DocFreeIDs(groupDepts);
			String rtStr = quesMainService.queryDesignationQuesList(qmv,
					quesType, queStatus, provinceID, cityID, pageSize,
					pageIndex);
			Integer rtCount = quesMainService.queryDesignationQuesList(qmv,
					quesType, queStatus, provinceID);
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			if (quesType == 3 && rtCount <= 0) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("doctorUid", doctorUid);
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put("columns", "serviceStatus");
				InterfaceMessage interfacemessage = new InterfaceMessage();
				interfacemessage.setParam(jsonObj.toString());
				JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
						.queryComplexDoctorList(interfacemessage));
				JSONArray dcJson = JSONArray.fromObject(dcRt
						.getJSONArray("Result"));
				int countDoc = dcRt.getInt("Total");
				if (countDoc > 0) {
					String serviceStatus = dcJson.getJSONObject(0).getString(
							"serviceStatus");
					if (serviceStatus.substring(1, 2).equals("0")) {
						ReplyVo rpl = new ReplyVo();
						rpl.setASK_DoctorID(doctorUid);
						rpl.setREPLY_UserType(1);
						rpl.setREPLY_Status(1);
						int count = replyService.queryReplyCount(rpl);
						if (count <= 0) {
							rtStr = quesMainService
									.queryDesignationQuestionLibraryList(qmv,
											quesType, queStatus, provinceID,
											cityID, pageSize, pageIndex);
							rtCount = quesMainService
									.queryDoctorQuestionLibraryListCount(qmv,
											quesType, queStatus, provinceID);
							if (StringUtil.isEmpty(rtStr)) {
								return ApiUtil.getJsonRt(-20000, "��ȡ����ʧ��");
							}
						}
					}
				}
			}
			JSONObject rtJson = JSONObject.fromObject(rtStr);
			JSONArray rtResult = rtJson.getJSONArray("result");
			for (int i = 0; i < rtResult.size(); i++) {
				int qusType = rtResult.getJSONObject(i).getInt("qd_sourcetype");
				if (qusType == 3) {
					InterfaceMessage imsg = new InterfaceMessage();
					JSONObject parm = new JSONObject();
					if (rtResult.getJSONObject(i).get("qd_docfreeid") != null) {
						parm.put("groupId",
								rtResult.getJSONObject(i)
										.getInt("qd_docfreeid"));
						imsg.setParam(parm.toString());
						/*String group = doctorGroupApi
								.queryDoctorGroupByGroupIdOrDoctorSn(imsg);
						JSONObject groupJson = JSONObject.fromObject(group);
						rtResult.getJSONObject(i).put("comeFromName",
								groupJson.getString("groupName"));*/
					} else {
						rtResult.getJSONObject(i).put("comeFromName", "");
					}
				} else if (qusType == 2) {
					InterfaceMessage imsg = new InterfaceMessage();
					JSONObject parm = new JSONObject();
					if (StringUtil.isNotEmpty(rtResult.getJSONObject(i).get("qd_askdeptid") )) {
						parm.put("hosDeptId",
								rtResult.getJSONObject(i)
										.getInt("qd_askdeptid"));
						parm.put("columns", "deptName");
						imsg.setParam(parm.toString());
						String group = hosDeptApi.queryComplexHosDeptById(imsg);
						JSONObject groupJson = JSONObject.fromObject(group);
						rtResult.getJSONObject(i).put("comeFromName",
								groupJson.getString("deptName"));
					} else {
						rtResult.getJSONObject(i).put("comeFromName", "");
					}
				} else {
					rtResult.getJSONObject(i).put("comeFromName", "");
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", rtJson.getJSONArray("result"));
			rt.put("Total", rtCount);
			rt.put("PageSize", pageSize);
			rt.put("PageIndex", pageIndex);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ҽ�������б�
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListForDoctorApp(InterfaceMessage msg) {
		String tag = "getQuesListForDoctorApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			Integer pageIndex = json.get("pageIndex") == null ? null : json
					.getInt("pageIndex");
			Integer doctorUid = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			Integer deptID = json.get("deptID") == null ? null : json
					.getInt("deptID");
			String twoDeptID = json.get("twoDeptID") == null ? null : json
					.getString("twoDeptID");
			QuesMainVo qmv = new QuesMainVo();
			qmv.setASK_DoctorID(doctorUid);
			qmv.setQD_AskDeptID(deptID);
			qmv.setQD_DeptTwoIDS(twoDeptID);
			Integer rtCount = quesMainService
					.queryDesignationCountQuesListForApp(qmv, quesType);
			String rtStr = "";
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("doctorUid", doctorUid);
			// jsonObj.put("displayStatus", 1);
			jsonObj.put("main", 1);
			jsonObj.put("startRow", 0);
			jsonObj.put("pageSize", 0);
			jsonObj.put("columns", "serviceStatus");
			InterfaceMessage interfacemessage = new InterfaceMessage();
			interfacemessage.setParam(jsonObj.toString());
			JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
					.queryComplexDoctorList(interfacemessage));
			JSONArray dcJson = JSONArray
					.fromObject(dcRt.getJSONArray("Result"));
			int countDoc = dcRt.getInt("Total");
			rtStr = quesMainService.queryDesignationQuesListForApp(qmv,
					quesType, pageSize, pageIndex);
			if (StringUtil.isEmpty(rtStr)) {
				return ApiUtil.getJsonRt(-20000, "��ȡ����ʧ��");
			}
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setASK_DoctorID(doctorUid);
			int ct = quesMainService.querCountOverQus(qusVo);
			if (countDoc > 0 && quesType == 3 && rtCount <= 0 &&ct<1) {
				/*String serviceStatus = dcJson.getJSONObject(0).getString(
						"serviceStatus");
				if (serviceStatus.substring(1, 2).equals("0")) {*/
				ReplyVo rpl = new ReplyVo();
				rpl.setASK_DoctorID(doctorUid);
				rpl.setREPLY_UserType(1);
				rpl.setREPLY_Status(1);
				int count = replyService.queryReplyCount(rpl);
				if (count <= 0) {
					rtCount = quesMainService
							.queryQuestionLibraryCountForApp(qmv, quesType);
					rtStr = quesMainService.queryQuestionLibraryListForApp(
							qmv, quesType, pageSize, pageIndex);
					if (StringUtil.isEmpty(rtStr)) {
						return ApiUtil.getJsonRt(-20000, "��ȡ����ʧ��");
					}
				}
				/*}*/
			}
			JSONObject rtJson = JSONObject.fromObject(rtStr);
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			JSONArray rtResult = rtJson.getJSONArray("result");
			for (int i = 0; i < rtResult.size(); i++) {
			}
			rt.put("Result", rtJson.getJSONArray("result"));
			rt.put("Total", rtCount);
			rt.put("PageSize", pageSize);
			rt.put("PageIndex", pageIndex);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ҽ�������б�(����)
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesListCountForDoctorApp(InterfaceMessage msg) {
		String tag = "getQuesListCountForDoctorApp";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer doctorUid = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			Integer deptID = json.get("deptID") == null ? null : json
					.getInt("deptID");
			String twoDeptID = json.get("twoDeptID") == null ? null : json
					.getString("twoDeptID");
			
			com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
					CommonApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			InterfaceMessage imsg = new InterfaceMessage();
			JSONObject dpjson = new JSONObject();
			dpjson.put("doctorUid", doctorUid);
			imsg.setParam(dpjson.toString());
			String rtJs = commonApi.queryDoctorFriendAndNotice(imsg);
			JSONObject resJson =JSONObject.fromObject(rtJs);
			int doctorFriendCount = 0;
			int noticeCount = 0;
			if(resJson.getInt("Code")>0){
				doctorFriendCount = resJson.getInt("doctorFriendCount");
				noticeCount =resJson.getInt("noticeCount");
			}
			
			QuesMainVo qmv = new QuesMainVo();
			qmv.setASK_DoctorID(doctorUid);
			qmv.setQD_AskDeptID(deptID);
			qmv.setQD_DeptTwoIDS(twoDeptID);
			Integer rtCount = quesMainService
					.queryDesignationCountQuesListForAppV2(qmv, 1);
			Integer freeCount = quesMainService
					.queryDesignationCountQuesListForApp(qmv, 3);
			Integer deptCount = quesMainService
					.queryDesignationCountQuesListForApp(qmv, 4);
			Integer deptUserCount = quesMainService
					.queryDesignationCountQuesListForApp(qmv, 5);
			JSONObject rtJson = new JSONObject();
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rtJson.put("userQueCount", rtCount);
			rtJson.put("freeQueCount", freeCount);
			rtJson.put("deptUserCount", deptUserCount);
			rtJson.put("deptCount", deptCount);
			rtJson.put("doctorFriendCount", doctorFriendCount);
			rtJson.put("noticeCount", noticeCount);
			JSONArray rtjar = new JSONArray();
			rtjar.add(rtJson);
			rt.put("Result", rtjar);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	/**
	 * ҽ�������б�(����)��վ
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesCountForDoctor(InterfaceMessage msg) {
		String tag = "getQuesCountForDoctor";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quesType = json.get("quesType") == null ? null : json
					.getInt("quesType");
			Integer queStatus = json.get("queStatus") == null ? null : json
					.getInt("queStatus");
			Integer doctorUid = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			String dept = json.get("deptTwoID") == null ? null : json
					.getString("deptTwoID");
			Integer hosDept = json.get("hosDept") == null ? null : json
					.getInt("hosDept");
			Integer provinceID = json.get("provinceID") == null ? null : json
					.getInt("provinceID");
			String groupDepts = json.get("groupDepts") == null ? null : json
					.getString("groupDepts");
			QuesMainVo qmv = new QuesMainVo();
			qmv.setASK_DoctorID(doctorUid);
			qmv.setQD_DeptTwoIDS(dept);
			qmv.setQD_AskDeptID(hosDept);
			qmv.setQD_DocFreeIDs(groupDepts);
			Integer rtCount = quesMainService.queryDesignationQuesList(qmv,
					quesType, queStatus, provinceID);
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			JSONObject cont = new JSONObject();
			cont.put("count", rtCount);
			JSONArray result = new JSONArray();
			result.add(cont);
			rt.put("Result", result);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ���⾫ѡ״̬�޸�
	 * 
	 * @param quesContent
	 * @param userId
	 * @return
	 */
	public String editQuesShowType(InterfaceMessage msg) {
		String tag = "editQuesShowType";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			String showType = json.get("showType") == null ? null : json
					.getString("showType");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo.setQD_ShowStatus(showType);
			int qert = quesMainService.updateQuesMain(qusVo);
			if (qert < 0) {
				return ApiUtil.getJsonRt(-14444, "����ʧ�ܡ�");
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/**
	 * ��������
	 * 
	 * @param quesContent
	 * @param userId
	 * @return
	 */
	public String doctorAnswer(InterfaceMessage msg) {
		String tag = "doctorAnswer";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());

			Integer queID=0;
			Integer doctorUid=0;
			Integer deptID=0;
			try {
				queID = json.get("queID") == null ? null : json
						.getInt("queID");
				doctorUid = json.get("doctorID") == null ? null : json
						.getInt("doctorID");
				deptID = json.get("deptID") == null ? null : json
						.getInt("deptID");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logger.get().error(
						tag,
						new LogBody().set("����", tag).set("����", msg.getParam())
								.set("�쳣", StringUtil.getException(e)));
				return ApiUtil.getJsonRt(-19999,
						"��γ���!" + StringUtil.getException(e));
				
			}
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("doctorUid", doctorUid);
			// jsonObj.put("displayStatus", 1);
			jsonObj.put("main", 1);
			jsonObj.put("startRow", 0);
			jsonObj.put("pageSize", 0);
			jsonObj.put("columns", "textZX");
			InterfaceMessage interfacemessage = new InterfaceMessage();
			interfacemessage.setParam(jsonObj.toString());
			JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
					.queryComplexDoctorList(interfacemessage));
			JSONArray dcJson = JSONArray
					.fromObject(dcRt.getJSONArray("Result"));
			JSONObject rtid = new JSONObject();
			rtid.put("firstOpen", 0);
			rtid.put("price", 0);
			int countDoc = dcRt.getInt("Total");
			if (countDoc > 0) {
				String textZX = dcJson.getJSONObject(0).getString("textZX");
				if (textZX.equals("0")) {
					QuesMainVo quHave = new QuesMainVo();
					quHave.setASK_DoctorID(doctorUid);
					int quHaRt = quesMainService.querCountHaveQus(quHave);
					if (quHaRt <= 0) {
						if (queID > 90000000) {
							QuesMainVo qusVo = new QuesMainVo();
							qusVo.setQUESMAIN_ID(queID);
							// ��ȡ���������
							qusVo = quesMainService
									.queryQuestionLibraryByCondition(qusVo);
							// ��ȡ����⻼������
							PatientVo pat = new PatientVo();
							pat.setPATIENT_ID(qusVo.getASK_PatientID());
							pat = patientService.queryPatient(pat);
							if (pat == null) {
								return ApiUtil.getJsonRt(-14444, "��ȡ����ʧ�ܡ�");
							}
							pat.setPATIENT_ID(null);
							// �����»��ߵ����߱�
							int patID = patientService.insertPatient(pat);
							if (patID < 0) {
								return ApiUtil.getJsonRt(-14444, "д�뻼����Ϣʧ�ܡ�");
							}
							// �������⵽�����
							qusVo.setASK_PatientID(patID);
							qusVo.setQUESMAIN_ID(null);
							queID = quesMainService.insertQuesMain(qusVo);
							if (queID < 0) {
								return ApiUtil.getJsonRt(-14444, "���������ת��ʧ��");
							}
						}else{
							QuesMainVo qus = new QuesMainVo();
							qus.setQUESMAIN_ID(queID);
							int qert = quesMainService.querCountAnswerQus(qus);
							if (qert <= 0) {
								return ApiUtil.getJsonRt(-2000, "�������ѱ�����");
							}
						}
					} else {
						return ApiUtil.getJsonRt(-2000, "��������δ��ͨ������ѯ���񣬵�ǰֻ����ȡһ�������ѯ��");
					}
				}else if(textZX.equals("2")){
					return ApiUtil.getJsonRt(-3000, "�������ѹر�������ѯ�����뿪ͨ������ȡ���⡣");
				} else {
					QuesMainVo qus = new QuesMainVo();
					qus.setQUESMAIN_ID(queID);
					int qert = quesMainService.querCountAnswerQus(qus);
					if (qert <= 0) {
						return ApiUtil.getJsonRt(-2000, "�������ѱ�����");
					}
				}
			} else {
				return ApiUtil.getJsonRt(-2000, "ҽ����Ϣ�����ڡ�");
			}
			QuesMainVo qu = new QuesMainVo();
			qu.setQUESMAIN_ID(queID);
			qu = quesMainService.queryQuesMainByCondition(qu);
			qu.setOpenAuthFlag(null);
			qu.setASK_DoctorID(doctorUid);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(new Date());
			rightNow.add(Calendar.MINUTE, 10);
			Date dt1 = rightNow.getTime();
			qu.setQD_RecordExpiredTime(DateUtil.dateFormat(dt1));
			int qrt = quesMainService.updateQuesMain(qu);
			if (qrt < 0) {
				ApiUtil.getJsonRt(-14444, "���������ʧ��");
			}
			AnswerRecordVo avo = new AnswerRecordVo();
			avo.setASK_DeptID(deptID);
			avo.setASK_DoctorID(doctorUid);
			avo.setASK_QuesID(queID);
			avo.setAR_peratorType(1);
			avo.setAR_Status(1);
			avo.setAR_OperatorTime(DateUtil.dateFormat(new Date()));
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(new Date());
			nowTime.add(Calendar.MINUTE, 10);
			Date dt = nowTime.getTime();
			avo.setAR_FailTime(DateUtil.dateFormat(dt));
			int rtan = answerRecordService.insertAnswerRecord(avo);
			if (rtan < 0) {
				ApiUtil.getJsonRt(-14444, "���������ʧ��");
			}
			JSONObject queIDjs = new JSONObject();
			queIDjs.put("queID", queID);
			JSONArray result = new JSONArray();
			result.add(queIDjs);
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", result);
			
			
			////TODO  WUJIAJUN
			//1.queid ��ѯ����ǰ�������¼  ������      2.����docid ���ҵ�  ����  3. update������
			
			try {
				
				DoctorDefaultAuthVo   confvo = new  DoctorDefaultAuthVo();
				confvo.setDoctorId(String.valueOf(doctorUid));
				List<DoctorDefaultAuthVo>    list=     doctorDefaultAuthService.queryDoctorDefaultAuthListByDoctorId(confvo);
				
				
				
				OpenAuthVo  openvo = new OpenAuthVo();
				openvo.setQuesMainId(String.valueOf(queID));
				openvo.setDoctorId(String.valueOf(doctorUid));
				
				if(list.size()==0){
					//Ĭ��ֵ  O
					openvo.setOpenAuthFlag("O");
					
				}else{
					//�����ֵ ȡ����
					openvo.setOpenAuthFlag(list.get(0).getOpenFlag());
					
				}
				
				
				
				
				
				//update  TODO
				
				System.out.println("����� ҽ��id   +++++++++"+openvo.getDoctorId());
				openAuthService.updateDocidandopenflagByqueID(openvo);
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return WsUtil.getRetVal(msg.getOutType(), -10002, " ���� ʧ��  OPENFLAG ERROR  FOR UPDATE��");
			}
			
			
			
			
			
			
			
			
			
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}

	/*
	 * �ļ��ϴ�����
	 
	public String uploadFile(String file, String smbPath, String oldName) {
		OutputStream out = null;
		SmbFile remoteFile = null;
		JSONObject json = new JSONObject();
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] by = decoder.decodeBuffer(file);
			// ���ֽ�����bytes�е����ݣ�д���ļ������fos��
			SmbFile smbDir = new SmbFile(smbPath);
			if (!smbDir.exists()) {
				smbDir.mkdirs();
				// log.info("������Ŀ¼��" + smbPath);
			}
			int size = by.length;
			String ext = this.GetFileExt(oldName);
			String dyas = DateUtil.dateFormat(new Date(),
					DateUtil.YMDHMS_FORMAT_ALL);
			// System.out.println(dyas);
			String smbFilename = "sy_" + dyas + by.length + "." + ext;
			remoteFile = new SmbFile(smbPath + smbFilename);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			// System.out.println(size);
			out.write(by);
			out.flush();
			json.put("size", size);
			json.put("url", smbPath);
			json.put("fileName", smbFilename);
			json.put("code", 1);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code", -14444);
			return json.toString();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/

	/*
	 * �ļ����ط���
	 
	public String downloadFile() {
		String filepath = "d:\\����\\�ϴ�ǰ.jpg";
		FileInputStream in = null;
		String rt = null;
		byte bytes[] = null;
		try {
			in = new FileInputStream(filepath);
			bytes = new byte[in.available()];

			// ��������in��,�� bytes.length ���ֽڵ����ݶ����ֽ�����bytes��
			in.read(bytes);
			BASE64Encoder encoder = new BASE64Encoder();
			rt = encoder.encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}*/

	/**
	 * ��������ID��ȡ������ϸ��Ϣ
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getQuesContentByQuesIDForCheck(InterfaceMessage msg) {
		String tag = "getQuesContentByQuesIDForCheck";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer quID = json.get("quID") == null ? null : json
					.getInt("quID");
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(quID);
			qusVo = quesMainService.queryQuesMainByCondition(qusVo);
			if (qusVo == null) {
				return ApiUtil.getJsonRt(-2000, "δ�����������.");
			}
			JSONObject queJson = new JSONObject();
			queJson.put("doctorUid", qusVo.getASK_DoctorID());
			queJson.put("price", qusVo.getQD_Price());
			queJson.put("content", qusVo.getQUESMAIN_Content());
			//��������3���ֶ�
			queJson.put("havettime", qusVo.getQD_PathogenesisTime());//����ʱ��
			queJson.put("mainill", qusVo.getQD_Illness());//��Ҫ��֢
			queJson.put("texperience", qusVo.getQD_TreatmentExperience());//���ﾭ�� 
			
			queJson.put("tipPhone", qusVo.getQD_TipPhone());
			queJson.put("createTime", qusVo.getQUESMAIN_CreateTime());
			queJson.put("handid", qusVo.getQD_HandleID());
			queJson.put("filesCount", qusVo.getQD_FilesCount());
			queJson.put("platform", qusVo.getQUESMAIN_Platform());
			queJson.put("diseaseStr", qusVo.getQD_DiseaseStr());
			queJson.put("sourceType", qusVo.getQD_SourceType());
			FilesVo vo = new FilesVo();
			vo.setFILES_ObjID(quID);
			vo.setFILES_ObjTypeID(1);
			List<FilesVo> fileList = filesService.queryFilesListByCondition(vo);
			JSONArray jary = new JSONArray();
			for (FilesVo file : fileList) {
				JSONObject filejs = new JSONObject();
				filejs.put("Status", file.getFILES_Status());
				filejs.put("NewName", file.getFILES_NewName());
				filejs.put("TypeID", file.getFILES_TypeID());
				filejs.put("ObjTypeID ", file.getFILES_ObjTypeID());
				filejs.put("Path", file.getFILES_Path());
				filejs.put("Size", file.getFILES_Size());
				filejs.put("CreateTime", file.getFILES_CreateTime());
				filejs.put("ObjID", file.getFILES_ObjID());
				filejs.put("OperatorID", file.getFILES_OperatorID());
				filejs.put("OldName", file.getFILES_OldName());
				filejs.put("ID", file.getFILES_ID());
				jary.add(filejs);
			}
			queJson.put("fileMess", jary);
			DepartmentsVo deptVo = new DepartmentsVo();
			deptVo.setASK_QuesID(quID);
			JSONArray jaryDept = new JSONArray();
			List<DepartmentsVo> depts = departmentsService
					.queryDepartmentsListByCondition(deptVo);
			if (depts == null) {
				return ApiUtil.getJsonRt(-14444, "��ȡ�����б�ʧ�ܡ�");
			}
			for (DepartmentsVo dVo : depts) {
				JSONObject deptJson = new JSONObject();
				deptJson.put("departIDOne", dVo.getASK_DepartIDOne());
				deptJson.put("departIDTwo", dVo.getASK_DepartIDTwo());
				deptJson.put("departNameOne", dVo.getASK_DepartNameOne());
				deptJson.put("departNameTwo", dVo.getASK_DepartNameTwo());
				jaryDept.add(deptJson);
			}
			queJson.put("deptList", jaryDept);
			PatientVo pVo = new PatientVo();
			pVo.setPATIENT_ID(qusVo.getASK_PatientID());
			pVo = patientService.queryPatient(pVo);
			if (pVo == null) {
				return ApiUtil.getJsonRt(-14444, "δ��û�������.");
			}
			JSONObject pjson = new JSONObject();
			pjson.put("patname ", pVo.getPATIENT_Name());
			pjson.put("patsex", pVo.getPATIENT_Sex());
			pjson.put("patprvid", pVo.getASK_ProvinceID());
			pjson.put("patcityid", pVo.getASK_CityID());
			pjson.put("patprvname", pVo.getASK_ProvinceName());
			pjson.put("patcityname", pVo.getASK_CityName());
			pjson.put("patbirth", pVo.getPATIENT_Birth());
			queJson.put("patientMess", pjson);
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject jsonObj = new JSONObject();
			JSONObject docJson = new JSONObject();
			// System.out.println(que.getASK_DoctorID());
			if (qusVo.getASK_DoctorID() > 0) {
				jsonObj.put("doctorUid", qusVo.getASK_DoctorID());
				jsonObj.put("serviceStatusSearch", 3);
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put(
						"columns",
						"feeTemplateId,provinceShort,skill,textPrice,lczcName,hosDeptGuid,hosGuid,lczc,serviceStatus,provinceName,hospitalId,states,serviceStatusSearch,hosName,doctorGuid,doctorUid,doctorName,doctorSex,hosDeptId,deptName,standardDeptId,bigDepartmentName,doctorUid,photoPrefix,photoUri");
				InterfaceMessage interfacemessage = new InterfaceMessage();
				interfacemessage.setParam(jsonObj.toString());
				JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
						.queryComplexDoctorList(interfacemessage));

				JSONArray dcJson = JSONArray.fromObject(dcRt
						.getJSONArray("Result"));
				int countDoc = dcRt.getInt("Total");

				if (countDoc > 0) {
					docJson.put("textPrice",
							dcJson.getJSONObject(0).get("textPrice"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("lczcName",
							dcJson.getJSONObject(0).get("lczcName"));
					docJson.put("hosDeptGuid",
							dcJson.getJSONObject(0).get("hosDeptGuid"));
					docJson.put("hosGuid",
							dcJson.getJSONObject(0).get("hosGuid"));
					docJson.put("lczc", dcJson.getJSONObject(0).get("lczc"));
					docJson.put("serviceStatus",
							dcJson.getJSONObject(0).get("serviceStatus"));
					docJson.put("provinceShort",
							dcJson.getJSONObject(0).get("provinceShort"));
					docJson.put("provinceName",
							dcJson.getJSONObject(0).get("provinceName"));
					docJson.put("hospitalId",
							dcJson.getJSONObject(0).get("hospitalId"));
					docJson.put("states", dcJson.getJSONObject(0).get("states"));
					docJson.put("serviceStatusSearch", dcJson.getJSONObject(0)
							.get("serviceStatusSearch"));
					docJson.put("textPrice",
							dcJson.getJSONObject(0).get("textPrice"));
					docJson.put("hosName",
							dcJson.getJSONObject(0).get("hosName"));
					docJson.put("doctorGuid",
							dcJson.getJSONObject(0).get("doctorGuid"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("doctorName",
							dcJson.getJSONObject(0).get("doctorName"));
					docJson.put("doctorSex",
							dcJson.getJSONObject(0).get("doctorSex"));
					docJson.put("hosDeptId",
							dcJson.getJSONObject(0).get("hosDeptId"));
					docJson.put("deptName",
							dcJson.getJSONObject(0).get("deptName"));
					docJson.put("standardDeptId",
							dcJson.getJSONObject(0).get("standardDeptId"));
					docJson.put("bigDepartmentName", dcJson.getJSONObject(0)
							.get("bigDepartmentName"));
					docJson.put("doctorUid",
							dcJson.getJSONObject(0).get("doctorUid"));
					docJson.put("photoPrefix",
							dcJson.getJSONObject(0).get("photoPrefix"));
					docJson.put("photoUri",
							dcJson.getJSONObject(0).get("photoUri"));
					docJson.put("feeTemplateId",
							dcJson.getJSONObject(0).get("feeTemplateId"));
				}
			}
			queJson.put("doctor", docJson);
			AssayResultMainVo aVo = new AssayResultMainVo();
			aVo.setASK_QuesID(quID);
			List<AssayResultMainVo> aVos = assayResultMainService
					.queryAssayResultMainListByCondition(aVo);
			if (aVos == null) {
				return ApiUtil.getJsonRt(-14444, "��д���浥����.");
			}
			JSONArray jarArm = new JSONArray();
			for (AssayResultMainVo avo : aVos) {
				JSONObject armJson = new JSONObject();
				armJson.put("aliasName", avo.getARM_AliasName());
				armJson.put("censorTime", avo.getARM_CensorTime());
				armJson.put("createTime", avo.getARM_CreateTime());
				armJson.put("hospitalName", avo.getARM_HospitalName());
				armJson.put("armID", avo.getARM_ID());
				armJson.put("name", avo.getARM_Name());
				armJson.put("operatorID", avo.getARM_OperatorID());
				armJson.put("order", avo.getARM_Order());
				armJson.put("quesType", avo.getARM_QuesType());
				armJson.put("remark", avo.getARM_Remark());
				armJson.put("status", avo.getARM_Status());
				armJson.put("quesID", avo.getASK_QuesID());
				armJson.put("atID", avo.getAT_ID());
				jarArm.add(armJson);
			}
			queJson.put("assayResultMess", jarArm);
			DiseaseVo dsVo = new DiseaseVo();
			dsVo.setASK_QuesID(quID);
			List<DiseaseVo> list = diseaseService.queryDiseaseListByCondition(dsVo);
			JSONArray	dis = JSONArray.fromObject(list);
			queJson.put("diseases", dis);
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "�ɹ�"));
			rt.put("Result", queJson);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("����", tag).set("����", msg.getParam())
							.set("�쳣", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	private String GetFileExt(String _filepath) {
		if (StringUtil.isEmpty(_filepath)) {
			return "";
		}
		if (_filepath.lastIndexOf(".") > 0) {
			return _filepath.substring(_filepath.lastIndexOf(".") + 1); // �ļ���չ����������.��
		}
		return "";
	}
	
	
	
	
	


	/**
	 * @title
	 *  �û�ɾ������ 
	 * @desc
	 * @param msg
	 * @return
	 */
	public String userDeleteQues(InterfaceMessage msg) {
		
		String tag = "userDeleteQues";
		try {
			
			
		
			JSONObject json = JSONObject.fromObject(msg.getParam());
			LOG.error("userDeleteQues��������������"+json.toString());
			Integer askuserid = json.get("askuserid") == null ? null : json
					.getInt("askuserid");

			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");

			
			
			
			QuesMainVo  vo = new QuesMainVo();
			vo.setASK_UserID(askuserid);
			vo.setQUESMAIN_ID(queID);
			
			
			Integer    deleCount= askDoctorNewInterfaceService.deleteQuesVoByUser(vo);
			
			System.out.println(deleCount);
			if(deleCount>=0){
			 	JSONObject rt = JSONObject.fromObject(ApiUtil
						.getJsonRt(10000, "�ɹ�"));
			 	
				return  		rt.toString();
				
			}else{
				JSONObject rt = JSONObject.fromObject(ApiUtil
						.getJsonRt(-14444, "ʧ��"));
				return  		rt.toString();
			}
			
			
			
			
			
			
		
			}catch (JSONException e) {
				LOG.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		} catch (Exception e) {
			LOG.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			
			return ApiUtil.getJsonRt(-14444,
					"�����쳣!" + StringUtil.getException(e));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
