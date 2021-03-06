package com.yihu.api.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.boss.sdk.ExecutionResult;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.QuesMainSqlNameEnum;
import com.yihu.myt.service.service.IJobpayService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.impl.PostService;
import com.yihu.myt.util.LoggerUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.CloseQueVo;
import com.yihu.myt.vo.JobpayVo;
import com.yihu.myt.vo.NewDocProblem;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.account.api.AccMemberBean;
import com.yihu.account.api.SysOperatorBean;
import com.yihu.api.impl.AskDoctorQuestionApiImpl;
import com.yihu.baseinfo.api.DoctorAccountApi;
import com.yihu.event.api.EventMessage;
import com.yihu.event.api.Subscriber;

public class AskDoctorQuestionApiTest {
	
	private static org.apache.log4j.Logger  log=  org.apache.log4j.Logger  .getLogger(AskDoctorQuestionApiTest.class);
	
	private static IPostService postService = Ioc.get(IPostService.class);
	private static IJobpayService JobpayService = Ioc
			.get(IJobpayService.class); 

	public static void getQuesListByUser() {
		AskDoctorQuestionApiImpl adq = new AskDoctorQuestionApiImpl();
		InterfaceMessage msg = new InterfaceMessage();
		String str = "{'userID':'12585592','listType':'0','start':'0','pageSize':'10'}";
		msg.setParam(str);
		String rt = adq.getQuesListByUser(msg);
		System.out.println(rt);
	}

	public static void Post() {
		PostService pst = new PostService();
		System.out
				.println(pst.telecounSelingUpdate("1", String.valueOf(61967)));
	}

	public static void main(String[] args) throws Exception {
		JSONObject AuthInfo = new JSONObject();
		AuthInfo.put("ClientId", "6000005");
		
		JSONObject axJson = new JSONObject();
		 
		String  param = "{'toAppType':1,'toUsers':'710040067','iosParam':'{'content':'吃饭饭','ti':'火锅刚刚哈哈哈 嘎嘎嘎呵......','questionId':267252,'sourceName':'','msgType':0,'operCode':'1200 0','date':'2015-01-07 16:37:38','uri':'','contentId':450268,'sourceId':14460103}','androidParam':'{'questionId':267252,'sourceName':'','operCode':12000,'ti':'火锅刚刚 哈哈哈嘎嘎嘎呵......','contentId':450268,'sourceId':14460103}'}";
		String back =postservice.sendMsgJ(param);
		System.out.println(back);
		
		/*Sql sql = DB.me().createSql(
				QuesMainSqlNameEnum.test150addmoney);
		
		StringBuilder condition = new StringBuilder();
		
		condition.append(" AND CreateTime > '2014-12-01 00:00:00.000'    AND CreateTime < '2014-12-31 23:59:59.000'");
		
		sql.addVar("@such", condition.toString());
		
		System.out.println(sql.toString());
		List<CloseQueVo> docList = DB.me().queryForBeanList(
				MyDatabaseEnum.YiHuNet2008, sql, CloseQueVo.class);

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
					 
				  }}*/
	}

	private static IQuesMainService quesMainService = Ioc
			.get(IQuesMainService.class);
	private static IPostService  postservice= Ioc
			.get(IPostService.class);
	
	

	
	
	
	public ReturnValue rebackaddlove(EventMessage msg, Subscriber sub){
		
		try{
			String docID = (String) msg.getValue("docID");
			String queID = (String) msg.getValue("queID");
			
			//1.保存数据到  表  ZIXUN_rebackaddlove
			//2.查询  加爱心值  
			
			Integer back1 = quesMainService.saveAddlove(queID, docID);
				System.out.println("+++保存 操作成功！！！" +back1);
				Calendar a = Calendar.getInstance();
				int yearrrrrrrr = a.get(Calendar.YEAR);
				int monthrrrr = a.get(Calendar.MONTH) + 1;
				int todate = a.get(Calendar.DATE);
				System.out.println("年份：：：" + yearrrrrrrr);

				System.out.println("月份：：：" + monthrrrr);

				System.out.println("日：：：" + todate);

				String heartstart = yearrrrrrrr + "-" + monthrrrr + "-" + todate
						+ "  00:00:00" + "";
				String heartend = yearrrrrrrr + "-" + monthrrrr + "-" + todate
						+ " 23:59:59" + "";
				System.out.println(heartstart);
				System.out.println(heartend);				
				int count =  quesMainService.queryaddlovecount(heartstart, heartend, docID);
				System.out.println("加爱心值  回复完  数量是"+count);
				JSONObject AuthInfo = new JSONObject();
				AuthInfo.put("ClientId", "6000005");
				String resID= yearrrrrrrr + "" + monthrrrr + "" + todate;
				JSONObject axJson = new JSONObject();
				axJson.put("resId", resID);
				axJson.put("doctorUid", docID);
				
				if (count == 3) {
					System.out.println("爱心值加   1");
					
					// 爱心值 +1
					axJson.put("typeId", 9);
					
				   System.out.println(axJson.toString());
				   String back =postservice.addDoctorLove(axJson.toString(),AuthInfo.toString());
				   System.out.println(back);
				   log.error(back);
					
				} else if (count == 10) {
					System.out.println("爱心值加   2");
					// 爱心值+2
					axJson.put("typeId", 10);
					System.out.println(axJson.toString());
					   String back =postservice.addDoctorLove(axJson.toString(),AuthInfo.toString());
						System.out.println(back);
 
					    log.error(back);
				}

			
			
			
			
			
			
			
			
			
 
			return new ReturnValue(1, "++++回复加爱心值 +++. ");
		} catch (Exception e) {
			    log.error(LoggerUtil.getTrace(e));
				e.printStackTrace();
				log.error(LoggerUtil.getTrace(e));
				return new ReturnValue(-14444,
						"rebackaddlove   ERROR !!!!     CHECK  IT :"
								+ e.getMessage());
			}
		
	}
	
	/**
	 * 
	 * 首先 关闭问题后 需要 保存一条记录到 表中 ！ （queid docid zixuntype（查询出来queid） time）
	 *
	 * 
	 * 当月！！获取系统时间 然后 取到 年月 + 医生加钱
	 * 
	 * 
	 * 
	 * @param msg
	 * @param sub
	 * @return
	 */

	public ReturnValue closeQueFollow(EventMessage msg, Subscriber sub) {

		// 1.关闭成功了！ 插入数据到 关闭表中
		// 2.查询本月 咨询 是否满足150 满足的话 加钱
		// 3.查询本天是否公共咨询 3 +1 and 10 +2
		try {
			String docID = (String) msg.getValue("docID");
			String queID = (String) msg.getValue("queID");

			// queid 查询 公共 指定

			QuesMainVo vo = new QuesMainVo();
			vo.setQUESMAIN_ID(Integer.valueOf(queID));
			List<QuesMainVo> quevos = quesMainService
					.queryQuesMainListByCondition(vo);

			QuesMainVo que = quevos.get(0);
			String queflag = "";
			Integer source = que.getQD_SourceType();
			if (source == 0) {
				// 指定咨询--T(to)
				queflag = "T";

			} else if (source == 1) {
				// 公共咨询--P(public)
				queflag = "P";
			} else {
				// 其他情况--O(other)
				queflag = "O";
			}

			// 保存到--关闭表--ZIXUN_CloseQue

			Integer back1 = quesMainService.saveCloseQue(queID, docID, queflag);

			System.out.println("关闭问题+++后保存 操作成功！！！");


				
				
				// 获取到当前的月份和年
				 Calendar a = Calendar.getInstance();  
			      int yearrrrrrrr= a.get(Calendar.YEAR);
			      int monthrrrr= a.get(Calendar.MONTH)+1;

			      
		     	 System.out.println("年份：：："+yearrrrrrrr);
			      
		    	 System.out.println("月份：：："+monthrrrr);
			      
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

			    
			    //开始查询zixun表的公共咨询数量 1.大于等于150 的 存到集合中！  2. 然后加钱  3. 然后配置 quartz
			    
				
			    
		 	    
			       List<CloseQueVo>       docList =quesMainService.queryCloseQueDoctorAndCount(queryStart, queryEnd);
				   if(docList.size()==0){
						return new ReturnValue(-10000, "++++没有满足条件的 加钱医生   +++ " );
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
				

			
			
			
			return new ReturnValue(1, "++++关闭问题     结算 医生是否加钱  事件 +++.            " + docID
					+ "              ." + queID);
		} catch (Exception e) {
		    log.error(LoggerUtil.getTrace(e));
			e.printStackTrace();
			log.error(LoggerUtil.getTrace(e));

			return new ReturnValue(-14444,
					"closeQueFollow   ERROR !!!!     CHECK  IT :"
							+ e.getMessage());
		}
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
	
}
