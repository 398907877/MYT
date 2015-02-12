package com.yihu.main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.vo.ReturnValue;
import com.yihu.api.api.AskDoctorControlApi;
import com.yihu.api.impl.AskDoctorControlApiImpl;
import com.yihu.api.impl.AskDoctorNewInterfaceImpV1;
import com.yihu.api.impl.AskDoctorQuestionApiImpl;
import com.yihu.api.impl.AskDoctorQuestionApiTest;
import com.yihu.api.impl.MytDoctorOperconfidApiImpl;
import com.yihu.api.impl.NetworkConsultingMedicalApiImpl;
import com.yihu.event.api.EventMessage;
import com.yihu.myt.enums.DoctorDefaultAuthSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.BookenrolService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.impl.AskDoctorNewInterfaceV1ServiceImp;
import com.yihu.myt.service.service.impl.QuesMainService;
import com.yihu.myt.util.DBCache;
import com.yihu.myt.util.LoggerUtil;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.wsgw.api.InterfaceMessage;

public class Test4 {
	
	
	private static  Logger log = Logger.getLogger(Test4.class);
	

	@Test
	public void wujiajuntest1(){
		
		AskDoctorQuestionApiTest aqa= new AskDoctorQuestionApiTest();
		
		EventMessage msg = new EventMessage();
		//492--1
		//496--0
		msg.add("queID", "492");
		msg.add("docID", "710003065");
		
		aqa.closeQueFollow(msg, null);
		
	}

	
	@Test
	public void wujiajuntest2(){
		
		AskDoctorQuestionApiTest aqa= new AskDoctorQuestionApiTest();
		
		EventMessage msg = new EventMessage();
		//492--1
		//496--0
		msg.add("queID", "492");
		msg.add("docID", "710003065");
		
		aqa.rebackaddlove(msg, null);
		
	}
	
	
	@Test
	public void wujiajuntest3(){
		
		
		System.out.println();
		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("quID", 119988);
		json.put("userType", 0);
		json.put("pageIndex", 0);
		json.put("pageSize", 100);

		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getQueReplyByQuesID(msg);
		System.out.println(back);
		
	}
	
	
	
	@Test
	public void wujiajuntest4getDoctorConsultingListV5() throws Exception{
		DBCache.initCacheByDB();
		System.out.println( DBCache.smallDepartmentBigDepartmentTranslation.toString());
		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorUid", 710108801);
		json.put("pageSize", 10);
		json.put("quesType", 1);
		json.put("pageIndex", 0);
	//	json.put("twoDeptID", 7102);



		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getDoctorConsultingListV5(msg);
		System.out.println(back);
		
	}
	
	
	@Test
	public void wujiajuntest5(){
		
		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorUid", 710040076);
		json.put("pageSize", 10);
		json.put("pageIndex", 0);

		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getDoctorOpenListForUser(msg);
		System.out.println(back);
		
	}
	
	
	@Test
	public void wujiajuntest6(){
		
		AskDoctorControlApi nma= new AskDoctorControlApiImpl();
		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorUid", 710097518);


		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getDoctorFreeCount(msg);
		System.out.println(back);
		
	}
	
	
	
	@Test
	public void wujiajuntest7(){
		
		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("userId", 10817943);


		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.userIdgetDoctorUid(msg);
		System.out.println(back);
		
	}
	
	@Test
	public void wujiajuntest8(){
		
		AskDoctorQuestionApiTest nma= new AskDoctorQuestionApiTest();
		EventMessage msg = new EventMessage();
		msg.add("docID", "111111111111111");
		msg.add("queID", "475");
		
		ReturnValue back = nma.closeQueFollow(msg,null);
		System.out.println(back.getMessage());	
	}
	

	
	@Test
	public void wujiajuntest9(){
		
		MytDoctorOperconfidApiImpl nma= new MytDoctorOperconfidApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("cardNumber", "8300001486");
		json.put("pageSize", 10);
		json.put("pageIndex", 0);
	//	json.put("revertresult", "3");
		json.put("begintime", "2001-01-09 21:36:38.000");
		json.put("endtime", "2014-07-09 21:36:38.000");

		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getMytBookEnrols(msg);
		System.out.println(back);
	}
	
	
	
	@Test
	public void wujiajuntest10(){
		
		try {
			log.error("2");
			Test4 t  = null;
			t.wujiajuntest10();
		} catch (Exception e) {
			log.error(LoggerUtil.getTrace(e));
			// TODO: handle exception
		}

		
		

	}
	
	
	
	
	@Test
	public void wujiajuntest11(){
		

		AskDoctorQuestionApiImpl nma= new AskDoctorQuestionApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("userID", "12582284");
		json.put("listType", 1);
		json.put("start", 0);

		json.put("pageSize", 3);

		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getQuesListByUser(msg);
		System.out.println(back);
		

	}
	
	
	

	
	@Test
	public void testforjson(){
	//	String json="{"Result":[],"Message":"分页参数有误,需要传入pageIndex(从1开始),pageSize(小于1000) ","Code":-14444}"
		

	}
	
	
	
	

	@Test
	public void wujiajuntest121(){
		

		AskDoctorQuestionApiImpl nma= new AskDoctorQuestionApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("quesID", "267259");
		json.put("cardID", "671194735");
		json.put("userID", "12582284");
		json.put("doctorID", "710088481");
		json.put("pleasedLevel", "1");
		json.put("OperatorId", "1000008");
		json.put("OperatorName", "医院微信");
		

		
		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.overQuertionV2(msg);
		System.out.println(back);
		

	}
	
	private static IPostService postService = Ioc.get(IPostService.class);
	
	@Test
	public   void   test12_for_push(){
		
		
		
		
		String  describe="您咨询的@doc医生   @dept科室  已回复";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("doctorUid", 710003065);
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
				System.out.println("未找到医生信息。");
			}
			 doctorName = rts.getJSONObject(0).getString("doctorName");
			 deptName = rts.getJSONObject(0).getString("deptName");
			 
		}else{
			System.out.println("未找到医生信息。");
		}
		
		System.out.println(doctorName+"-"+deptName);
		describe=describe.replace("@doc",doctorName );
		describe=describe.replace("@dept", deptName);
		System.out.println("real---"+describe);
		
	}
	
	
	
	
	


	@Test
	public void wujiajuntest122s1(){
		

		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorID", "710096894");

		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.queryDoctorQueFree(msg);
		System.out.println(back);
		

	}
	
	
	@Test
	public  void  testetsteTIME(){
		
		// 获取到当前的月份和年
		 Calendar a = Calendar.getInstance();  
	      int yearrrrrrrr= a.get(Calendar.YEAR);
	      int monthrrrr= a.get(Calendar.MONTH)+1;
	      int todate = a.get(Calendar.DATE);
	      
	      
     	 System.out.println("年份：：："+yearrrrrrrr);
	      
    	 System.out.println("月份：：："+monthrrrr);
    	 
    	 System.out.println("日期：：："+todate);
	      
	    a.set(Calendar.YEAR, yearrrrrrrr);  
	    a.set(Calendar.MONTH, monthrrrr-1);  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);
	    
	    System.out.println("这个月   有       "+maxDate+"   天");
	    
	    //拼成搜索的 时间区间    '2014-10-27 00:00:00'   '2014-10-27 23:59:59'
	    
	    String queryStart=""+yearrrrrrrr+"-"+monthrrrr+"-"+"1"+"   "+"00:00:00";
	    String queryEnd=""+yearrrrrrrr+"-"+monthrrrr+"-"+maxDate+" "+" 23:59:59";
	    
	    System.out.println("开始时间：：："+queryStart);
	    System.out.println("结束时间：：："+queryEnd);

		
		
	}
	@Test
	public  void  testapi(){
		
		
		JSONObject rtjon = new JSONObject();
		JSONArray result1 = new JSONArray();
		
		
		JSONObject json1 = new JSONObject();
		json1.put("mtofreecount", 0);
		json1.put("mfreecount", 0);
		json1.put("dayfreecount", 0);
		json1.put("daytofreecount", 0);
		json1.put("docid", 0);
		
		
		
		
		result1.add(json1);
		
		rtjon.put("Code", "10000");
		rtjon.put("Message", "成功");
		rtjon.put("Result", result1);
		rtjon.put("finNum",0);
		
		
		
		
		System.out.println(rtjon.toString());
	}
	
	

	@Test
	public  void  testAskDoctorNewInterfaceV1ServiceImp() throws Exception{
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("doctorUid", 710107588);
		// jsonObj.put("displayStatus", 1);
		jsonObj.put("main", 1);
		jsonObj.put("pageIndex", 1);
		jsonObj.put("pageSize", 100);
		jsonObj.put("columns", "serviceStatus,provinceId");
		InterfaceMessage interfacemessage = new InterfaceMessage();
		interfacemessage.setParam(jsonObj.toString());
		int searchType = 1;
		/*JSONObject dcRt = JSONObject.fromObject(doctorInfoApi
				.queryComplexDoctorList(interfacemessage));*/
		JSONObject dcRt  =JSONObject.fromObject(postService.queryComplexDoctorList_v2(jsonObj.toString())) ;
		
		System.out.println(dcRt.toString());
	}
	
	

	@Test
	public void testquesFreeze(){
		

		AskDoctorQuestionApiImpl nma= new AskDoctorQuestionApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("userID", "17113834");
		json.put("quID", "344802");
		json.put("price", "1500");
		json.put("OperatorId", "1000000");
		json.put("OperatorName", "网站");

		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.quesFreeze(msg);
		System.out.println(back);
		

	}
	
	
	
	@Test
	public void testqueryHotQueByDept(){
		

		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("deptid", "0302");
		json.put("pageIndex", "1");
		json.put("pageSize", "10");
		//json.put("startdate", "2015-01-17 00:00:00");
	//	json.put("enddate", "2015-12-12 23:59:59");

		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.queryHotQueByDept(msg);
		System.out.println(back);
		

	}
	
	
	
	
	
	@Test
	public void testString(){
		
		String  demo="我222们";
		
		StringBuffer sb = new StringBuffer(demo);
		
		if(sb.length()<=10){
			
			for (int i = sb.length(); i <10; i++) {
				sb.append(" ");
			}
			
		}
		
		
		System.out.println(sb.toString());
		
		String last =sb.substring(0,10);
		System.out.println(last);
		
		
	}
	
	@Test
	public  void   test1() throws Exception{
		QuesMainService  ser = new QuesMainService();
		
		QuesMainVo vo = new QuesMainVo();
	//	vo.setASK_DoctorID(55555);
		
		String  back =ser.quesListForDoctorWaitAnswer(vo, 0, 0, 0, "");
		
		System.out.println("11"+back);
		
		
	}
	
	
	
	

	@Test
	public  void  testapi22(){
		
		
		String  tag ="myt.NetworkConsultingMedicalApi.queryWaitAndNewReplyCount";
		String  req="doctorUid";
		
		
		JSONObject rtjon = new JSONObject();
		JSONArray result1 = new JSONArray();
		
		
		JSONObject json1 = new JSONObject();
		json1.put("WaitReplyCount", 5);
		json1.put("NewReplyCount", 9);

		json1.put("docid", "77889911");
		
		
		
		
		result1.add(json1);
		
		rtjon.put("Code", "10000");
		rtjon.put("Message", "成功");
		rtjon.put("Result", result1);
		
		
		
		
		System.out.println(rtjon.toString());
	}
	
	
	

	@Test
	public void testQueryWaitAndNewReplyCount(){
		

		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorUid", "710021745");


		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.queryWaitAndNewReplyCount(msg);
		System.out.println(back);
	}
	
	@Test
	public  void  testSave() throws SQLException{
		
		
		
		Sql sqlquery= DB.me().createSql(DoctorDefaultAuthSqlNameEnum.testquery);
		//QuesMainVo
		System.out.println(sqlquery.getSqlString());
		
		 List<QuesMainVo>   vos= DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008,sqlquery, QuesMainVo.class);
		

	   
		 
		 
		for (QuesMainVo quesMainVo : vos) {
			  Sql sql = DB.me().createSql(DoctorDefaultAuthSqlNameEnum.testtosave);
			System.out.println("mainid++++++"+quesMainVo.getQUESMAIN_ID());
			sql.addParamValue(quesMainVo.getQUESMAIN_ID());
			


			System.out.println(sql.getSqlString());
			Integer back= DB.me().insert(MyDatabaseEnum.YiHuNet2008,sql);
			System.out.println(back);
					
					
					
		}
	
	}

	

	@Test
	public void testgetMytDocList(){
		

		MytDoctorOperconfidApiImpl nma= new MytDoctorOperconfidApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("provinceId", "26");
		json.put("bigDeptSn", "");
		json.put("diseaseId", "");
		json.put("cityId", "293");
		json.put("startRow", "0");
		json.put("displayStatus", "1");
		
		json.put("columns", "doctorUid,yiHuDoctorName,hospitalId,yiHuHosName,deptName,doctorSex,lineStatus,skill,intro,lczcName,photoPrefix,photoUri,phonePrice,feeTemplateId,feeInfo");
		json.put("pageSize", "40");
		json.put("hospitalId", "1023577");
		
		json.put("serviceStatusSearch", "2");
		json.put("doctorNameLike", "");
		json.put("interfaceVersion", "4");
		


		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getMytDocList(msg);
		System.out.println(back);
	}
	
	
	

	@Test
	public void testgetDoctorConsultingListV4(){
		

		NetworkConsultingMedicalApiImpl nma= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("quesType", "4");
		json.put("doctorUid", "710124203");
		json.put("pageSize", "5");
		json.put("pageIndex", "0");
		
		


		msg.setParam(json.toString());
		System.out.println(json);
		String back = nma.getDoctorConsultingListV4(msg);
		System.out.println(back);
	}
	
		
}
