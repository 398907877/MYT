package com.yihu.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.Severity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import sun.misc.BASE64Decoder;

import com.boss.sdk.HttpRequestContext;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.vo.ReturnValue;
import com.yihu.api.impl.AskDoctorQuestionApiImpl;
import com.yihu.api.impl.AskDoctorQuestionApiTest;
import com.yihu.api.impl.MytDoctorOperconfidApiImpl;
import com.yihu.api.impl.NetworkConsultingMedicalApiImpl;
import com.yihu.event.api.EventMessage;
import com.yihu.myt.enums.DoctorDefaultAuthSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.http.NewStatisticsAction;
import com.yihu.myt.job.AskDoctorJob;
import com.yihu.myt.service.service.IJobpayService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.impl.NewDoctorService;
import com.yihu.myt.service.service.impl.OpenAuthService;
import com.yihu.myt.service.service.impl.PostService;
import com.yihu.myt.vo.CloseQueVo;
import com.yihu.myt.vo.DoctorQueSeachStoneVo;
import com.yihu.myt.vo.JobpayVo;
import com.yihu.myt.vo.OpenAuthVo;
import com.yihu.myt.vo.SuchDownDocMSGVo;
import com.yihu.wsgw.api.InterfaceMessage;


public class Test5 {

	
	
	


	@Test
	public void wujiajuntest1(){
		
		AskDoctorQuestionApiImpl AskDoctorQuestionApiImpl= new AskDoctorQuestionApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("askuserid", "666");
		json.put("queID", "387404");



		
		
		msg.setParam(json.toString());
		System.out.println(json);
		
		
		String bbk= AskDoctorQuestionApiImpl.userDeleteQues(msg);
		
		System.out.println(bbk);
		
	}
	
	
	

	@Test
	public void wujiajuntest2(){
		
		AskDoctorQuestionApiImpl AskDoctorQuestionApiImpl= new AskDoctorQuestionApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("platform", "1");
		
		json.put("userID", "17113834");

		
		json.put("replyType", "0");

		
		json.put("queID", "345101");

		
		json.put("fileMess", "[]");

		json.put("filesCount", "0");
		json.put("messCont", "太垃圾了");
		json.put("doctorID", "710040067");
		


		
		
		msg.setParam(json.toString());
		System.out.println(json);
		
		
		String bbk= AskDoctorQuestionApiImpl.replyQuertionUser(msg);
		
		System.out.println(bbk);
		
	}
	
	
	
	
	
	


	@Test
	public void wujiajuntestPUsh() throws Exception{
		
		
		AskDoctorJob job = new AskDoctorJob();
		job.pushMonthlyreport(null);
	}
	
	
	/*
	*
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}


	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    return string.toString();
	}

	/*test*/
	@Test
	public  void test23232323() {
	    String test = "w.zuidaima.com";
	 
	    String unicode = string2Unicode(test);
	     
	    String string = unicode2String(unicode) ;
	     
	    System.out.println(unicode);
	     
	    System.out.println(string);
	 
	}
	
	

	@Test
	public void wujiajuntest21123(){
		
		NetworkConsultingMedicalApiImpl NetworkConsultingMedicalApiImpl= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("pageSize", "20");
		
		json.put("doctorUid", "710133974");

		
		json.put("pageIndex", "0");

		
		json.put("quesType", "1");

		
		
		
		msg.setParam(json.toString());
		System.out.println(json);
		
		
		String bbk= NetworkConsultingMedicalApiImpl.getDoctorConsultingListV4(msg);
		
		System.out.println(bbk);
		
	}
	
	
	
	

	@Test
	public void wujiajuntestsaveDoctorSeachStone(){
		
		NetworkConsultingMedicalApiImpl NetworkConsultingMedicalApiImpl= new NetworkConsultingMedicalApiImpl();
		

		InterfaceMessage  msg = new InterfaceMessage();
		JSONObject json = new JSONObject();
		
		json.put("doctorStone", "[{\"seachID\":\"888\",\"num\":0,\"ID\":0,\"state\":1,\"doctorUid\":1111111,\"operTime\":\"\",\"seachType\":0},{\"seachID\":\"0501\",\"num\":0,\"ID\":0,\"state\":1,\"doctorUid\":1111111,\"operTime\":\"\",\"seachType\":0},{\"seachID\":\"05200\",\"num\":0,\"ID\":0,\"state\":1,\"doctorUid\":1111111,\"operTime\":\"\",\"seachType\":0},{\"seachID\":\"05200\",\"num\":0,\"ID\":0,\"state\":1,\"doctorUid\":1111111,\"operTime\":\"\",\"seachType\":0}]");
		
		json.put("saveType", "2");

		
		
		
		msg.setParam(json.toString());
		System.out.println(json);
		
		
		String bbk= NetworkConsultingMedicalApiImpl.saveDoctorSeachStone(msg);
		
		System.out.println(bbk);
		
	}
	@Test
	public void  testJsonArray(){
		
		JSONArray arry = new JSONArray();
		DoctorQueSeachStoneVo  vo=new DoctorQueSeachStoneVo();

		
		vo.setSeachID("1111");
		arry.add(vo);
		
      DoctorQueSeachStoneVo  vo2=new DoctorQueSeachStoneVo();

		
		vo2.setSeachID("1111");
		arry.add(vo2);
		
		System.out.println(arry.toString());
		
		
		
		
	}
	
	@Test
	public  void  testBASE64(){
		String name ="2323232323wuiidusidhsj吴佳经济活动&%";
		String namecode= this.string2BASE64(name);
		System.err.println(namecode);
	}
	
	
	
	
	  // String  BASE64
	   public static String string2BASE64(String s) {   
		if (s == null) return null;   
		return (new sun.misc.BASE64Encoder()).encode( s.getBytes() );   
		}   
		  
		// 将 BASE64 编码的字符串 s 进行解码   
		public static String  base642String(String s) {   
		if (s == null) return null;   
		BASE64Decoder decoder = new BASE64Decoder();   
		try {   
		byte[] b = decoder.decodeBuffer(s);   
		return new String(b);   
		} catch (Exception e) {   
		return null;   
		}   
		}  
		
		//初始化 ZiXun_DoctorQueSeachStone  让感兴趣的科室 不能超过  5条！
		@Test
		public  void  init_ZiXun_DoctorQueSeachStone(){
			//查询所有的   doctorid   条件  state=1  ！			  count》=5    list   （docuid）  =====》 update
			
			
			
			
		}

		
		


		@Test
		public void wujiajuntest12344(){
			
			MytDoctorOperconfidApiImpl MytDoctorOperconfidApiImpl= new MytDoctorOperconfidApiImpl();
			

			InterfaceMessage  msg = new InterfaceMessage();
			JSONObject json = new JSONObject();
			
			json.put("pageIndex", "1");
			json.put("bigDeptSn", "0");
			json.put("doctorNameLike", "");
			json.put("displayStatus", "1");
			json.put("provinceId", "");
			json.put("diseaseId", "");
			json.put("serviceStatusSearch", "2");
			json.put("hospitalId", "0");
			json.put("pageSize", "10");
			json.put("columns", "doctorSn,doctorUid,yiHuDoctorName,hospitalId,yiHuHosName,deptName,doctorSex,skill,intro,lczcName,photoPrefix,photoUri,phonePirce,feeTemplateId,feeInfo,provinceName,cityName,loveValue,serviceStatus,textPrice,hosDeptId");

			



			
			
			msg.setParam(json.toString());
			System.out.println(json);
			
			
			String bbk= MytDoctorOperconfidApiImpl.getMytDocList(msg);
			
			System.out.println(bbk);
			
		}
		


		@Test
		public void wujiajuntest1234423() throws Exception{
			
			NewDoctorService NewDoctorService= new NewDoctorService();
			

			
			
			List<SuchDownDocMSGVo> list= NewDoctorService.suchDownDocMSGlist("2015-01-01 00:00:00","2015-01-031 23:59:59",null,null,null,null,0,10);
			
			System.out.println(list.size());
			System.out.println(list.get(0).getHosname());
			
		}
		
		@Test
		public void wujiajuntestEXCEL() throws Exception{
			
			NewStatisticsAction NewStatisticsAction= new NewStatisticsAction();
			
			HttpRequestContext req = new HttpRequestContext(null);
			req.addParam("jo1", null);
			req.addParam("jo2", null);
			req.addParam("jo3", null);
			
			req.addParam("starttimes", "2015-01-01 00:00:00");
			req.addParam("endtimes", "2015-01-31 23:59:59");
			
			req.addParam("pageIndex", "0");
			req.addParam("pageSize", "999999");
			
			
		    NewStatisticsAction.toDownDocMSG(req);

			
		}
		
		
		
		@Test
		public void wujiajuntestdemo() throws Exception{
			
			NetworkConsultingMedicalApiImpl NetworkConsultingMedicalApiImpl= new NetworkConsultingMedicalApiImpl();

			InterfaceMessage  msg = new InterfaceMessage();
			JSONObject json = new JSONObject();
			json.put("pageIndex", "1");
			json.put("pageSize", "20");
			json.put("doctorUid", "710003065");
			msg.setParam(json.toString());
			System.out.println(json);
			
			String back=NetworkConsultingMedicalApiImpl.getDoctorOpenListForUser(msg);

			System.out.println(back);
		}
	
		
		
		@Test
		public void testURL() throws Exception{
			
			System.out.println(System.getProperty("user.dir") );
			
		}
		
		
		@Test
		public void wujiajuntestdemo2() throws Exception{
			
			NetworkConsultingMedicalApiImpl NetworkConsultingMedicalApiImpl= new NetworkConsultingMedicalApiImpl();

			InterfaceMessage  msg = new InterfaceMessage();
			JSONObject json = new JSONObject();
			json.put("pageSize", "20");
			json.put("pageIndex", "1");
			json.put("doctorUid", "710003065");

			
			msg.setParam(json.toString());
			System.out.println(json);
			
			String back=NetworkConsultingMedicalApiImpl.getDoctorOpenListForUser(msg);

			System.out.println(back);
		}
	
		
		
		@Test
		public void sdsdsdsd() throws Exception{
			
			PostService PostService= new  PostService();


			
			String back=PostService.upfile("{hosid:1024727}", "D:\\myeclipseworkspacenewwordk\\MYT-new\\zookeeper-3.3.6.jar");

			System.out.println(back);
		}
		
		
		
		
		
		public static String getOrderNo(){
			
	        long No = 0;
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String nowdate = sdf.format(new Date());
	        No = Long.parseLong(nowdate)*1000;//这里如果一天订单多的话可以用一万或更大
	 
	        String backNO=No+"0"+getNo();
	        return backNO;
	    }
		public static  int ADDNO = 0;
		public static long getNo(){
			ADDNO=ADDNO+5;
	 
	        return ADDNO;
	    }
		
		
		
		private static IQuesMainService quesMainService = Ioc
				.get(IQuesMainService.class);
		
		private static IPostService  postservice= Ioc
				.get(IPostService.class);
		private static IPostService postService = Ioc.get(IPostService.class);
		private static IJobpayService JobpayService = Ioc
				.get(IJobpayService.class); 
		
		@Test
		public void  test150() throws Exception{
			
			
			
			
			

			Sql sql = DB.me().createSql(DoctorDefaultAuthSqlNameEnum.testadd150);
				
				StringBuffer such =new StringBuffer();
				
				such.append("and 	REPLY_CreateTime >  ? AND REPLY_CreateTime < ?");
				sql.addParamValue("2015-01-01 00:00:00");
				sql.addParamValue("2015-01-31 23:59:59");
				
				sql.addVar("@such", such.toString());
				
				System.out.println(sql.getSqlString());

				 List<CloseQueVo> docList  = DB.me().queryForBeanList(
					MyDatabaseEnum.YiHuNet2008, sql,CloseQueVo.class);
			
			
			System.out.println(docList.size());
			
			
			

			   if(docList.size()==0){
					System.out.println("no  doctor");
			   }else{
				   
				    //获取  feeTemplateId  
				   
				   
				   JSONObject postpara = new JSONObject();
				   
					
				   postpara.put("price",30000 );
				   postpara.put("doctorPrice",30000);
				   
				   String  queryback= postService.queryFeeTemplateId(postpara.toString());
				   
				   net.sf.json.JSONObject  json1 = net.sf.json.JSONObject.fromObject(queryback);
				   int  feeTemplateId =   (Integer) json1.get("feeTemplateId");
				   
				   System.out.println("获取到的"+feeTemplateId);
				   
				   
				   //开始执行业务   ---------------------  
				   
				   for (CloseQueVo CloseQueVo : docList) {
					   //发送post请求  加钱
					  
					   // 唯一流水号获得
					   long  orderno =Long.valueOf(getOrderNo());
					   
					   System.out.println("这次的流水号"+orderno);
					   JSONObject dcBillJson = new JSONObject();
					   dcBillJson.put("doctorUid", CloseQueVo.getDoctorId());
					   dcBillJson.put("serviceRecordId", orderno);
					   dcBillJson.put("price", 30000);
					   dcBillJson.put("serviceId", 6);
					   dcBillJson.put("feeTemplateId", feeTemplateId);
					   
					   System.out.println(dcBillJson.toString());
					   String reback = postService.insertBill(dcBillJson.toString());
					   //扣款成功  记录一笔数据库  
					   
					   System.out.println("加钱返回信息：："+reback);
					   
					   net.sf.json.JSONObject  rebackj = net.sf.json.JSONObject.fromObject(reback);
					  String code =rebackj.getString("Code");
					  String message =rebackj.getString("Message");
					  
					  System.out.println(code+message);
					  
					  
					  
					   String docid= CloseQueVo.getDoctorId();
					   String num=CloseQueVo.getCOUNT();
					   String seqnum=String.valueOf(orderno);
					   String flag;
					   String  routemessage=code+":"+message;
					  if("10000".equals(code)){
						//状态设置为T
						    flag="T";
						  
						  System.out.println("这笔记录下来");
						  
						  JobpayVo voo = new JobpayVo();
						  voo.setDOCID(docid);
						  voo.setNUM(num);
						  voo.setSEQNUM(seqnum);
						  voo.setFLAG(flag);
						  voo.setROUTEMESSAGE(routemessage);
						  
						  JobpayService.insertJobpay(voo);
						  
						  
						  
						 
					  }else{
						//状态设置为F
						   flag="F";
						   
							  JobpayVo voo = new JobpayVo();
							  voo.setDOCID(docid);
							  voo.setNUM(num);
							  voo.setSEQNUM(seqnum);
							  voo.setFLAG(flag);
							  voo.setROUTEMESSAGE(routemessage);
							  
							  JobpayService.insertJobpay(voo);
						 
					  }
					   //

					}
			   }
			

		}
		
		
		
		
		
		@Test
		public void updateClicksByID() throws Exception{
			OpenAuthService OpenAuthService= new OpenAuthService();
			
			OpenAuthVo vo  = new OpenAuthVo();
			vo.setQuesMainId("15770");
			
			List<OpenAuthVo> vos  =OpenAuthService.queryOpenAuthListByCondition(vo);

			System.out.println(vos.get(0).getClicks());

			
		}
		

		@Test
		public void getQueReplyByQuesIDV2(){
			
			NetworkConsultingMedicalApiImpl NetworkConsultingMedicalApiImpl= new NetworkConsultingMedicalApiImpl();
			

			InterfaceMessage  msg = new InterfaceMessage();
			JSONObject json = new JSONObject();
			
			json.put("quID", "345467");
			//json.put("replyID", "710133974");
			//json.put("replyIDs", "0");
			json.put("pageSize", "100");
			json.put("pageIndex", "0");
			json.put("userType", "1");

			
			
			
			msg.setParam(json.toString());
			System.out.println(json);
			
			
			String bbk= NetworkConsultingMedicalApiImpl.getQueReplyByQuesIDV2(msg);
			
			System.out.println(bbk);
			
		}
		

		
		
		
		
		
		
		
}
