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
		 
		String  param = "{'toAppType':1,'toUsers':'710040067','iosParam':'{'content':'�Է���','ti':'����ոչ����� �¸¸º�......','questionId':267252,'sourceName':'','msgType':0,'operCode':'1200 0','date':'2015-01-07 16:37:38','uri':'','contentId':450268,'sourceId':14460103}','androidParam':'{'questionId':267252,'sourceName':'','operCode':12000,'ti':'����ո� �������¸¸º�......','contentId':450268,'sourceId':14460103}'}";
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

			    //��ȡ  feeTemplateId  
			   
			   
			   JSONObject postpara = new JSONObject();
			   
				
			   postpara.put("price",30000 );
			   postpara.put("doctorPrice",30000);
			   
			   String  queryback= postService.queryFeeTemplateId(postpara.toString());
			   
			   net.sf.json.JSONObject  json1 = net.sf.json.JSONObject.fromObject(queryback);
			   int  feeTemplateId =   (Integer) json1.get("feeTemplateId");
			   
			   System.out.println("��ȡ����"+feeTemplateId);
			   
			   
			   //��ʼִ��ҵ��   ---------------------  
			   
			   for (CloseQueVo CloseQueVo : docList) {
				   //����post����  ��Ǯ
				  
				   // Ψһ��ˮ�Ż��
				   long  orderno =Long.valueOf(getOrderNo());
				   
				   System.out.println("��ε���ˮ��"+orderno);
				   JSONObject dcBillJson = new JSONObject();
				   dcBillJson.put("doctorUid", CloseQueVo.getDoctorId());
				   dcBillJson.put("serviceRecordId", orderno);
				   dcBillJson.put("price", 30000);
				   dcBillJson.put("serviceId", 6);
				   dcBillJson.put("feeTemplateId", feeTemplateId);
				   
				   System.out.println(dcBillJson.toString());
				   String reback = postService.insertBill(dcBillJson.toString());
				   //�ۿ�ɹ�  ��¼һ�����ݿ�  
				   
				   System.out.println("��Ǯ������Ϣ����"+reback);
				   
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
					//״̬����ΪT
					    flag="T";
					  
					  System.out.println("��ʼ�¼����");
					  
					  JobpayVo voo = new JobpayVo();
					  voo.setDOCID(docid);
					  voo.setNUM(num);
					  voo.setSEQNUM(seqnum);
					  voo.setFLAG(flag);
					  voo.setROUTEMESSAGE(routemessage);					  
					  JobpayService.insertJobpay(voo);
				  }else{
					//״̬����ΪF
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
			
			//1.�������ݵ�  ��  ZIXUN_rebackaddlove
			//2.��ѯ  �Ӱ���ֵ  
			
			Integer back1 = quesMainService.saveAddlove(queID, docID);
				System.out.println("+++���� �����ɹ�������" +back1);
				Calendar a = Calendar.getInstance();
				int yearrrrrrrr = a.get(Calendar.YEAR);
				int monthrrrr = a.get(Calendar.MONTH) + 1;
				int todate = a.get(Calendar.DATE);
				System.out.println("��ݣ�����" + yearrrrrrrr);

				System.out.println("�·ݣ�����" + monthrrrr);

				System.out.println("�գ�����" + todate);

				String heartstart = yearrrrrrrr + "-" + monthrrrr + "-" + todate
						+ "  00:00:00" + "";
				String heartend = yearrrrrrrr + "-" + monthrrrr + "-" + todate
						+ " 23:59:59" + "";
				System.out.println(heartstart);
				System.out.println(heartend);				
				int count =  quesMainService.queryaddlovecount(heartstart, heartend, docID);
				System.out.println("�Ӱ���ֵ  �ظ���  ������"+count);
				JSONObject AuthInfo = new JSONObject();
				AuthInfo.put("ClientId", "6000005");
				String resID= yearrrrrrrr + "" + monthrrrr + "" + todate;
				JSONObject axJson = new JSONObject();
				axJson.put("resId", resID);
				axJson.put("doctorUid", docID);
				
				if (count == 3) {
					System.out.println("����ֵ��   1");
					
					// ����ֵ +1
					axJson.put("typeId", 9);
					
				   System.out.println(axJson.toString());
				   String back =postservice.addDoctorLove(axJson.toString(),AuthInfo.toString());
				   System.out.println(back);
				   log.error(back);
					
				} else if (count == 10) {
					System.out.println("����ֵ��   2");
					// ����ֵ+2
					axJson.put("typeId", 10);
					System.out.println(axJson.toString());
					   String back =postservice.addDoctorLove(axJson.toString(),AuthInfo.toString());
						System.out.println(back);
 
					    log.error(back);
				}

			
			
			
			
			
			
			
			
			
 
			return new ReturnValue(1, "++++�ظ��Ӱ���ֵ +++. ");
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
	 * ���� �ر������ ��Ҫ ����һ����¼�� ���� �� ��queid docid zixuntype����ѯ����queid�� time��
	 *
	 * 
	 * ���£�����ȡϵͳʱ�� Ȼ�� ȡ�� ���� + ҽ����Ǯ
	 * 
	 * 
	 * 
	 * @param msg
	 * @param sub
	 * @return
	 */

	public ReturnValue closeQueFollow(EventMessage msg, Subscriber sub) {

		// 1.�رճɹ��ˣ� �������ݵ� �رձ���
		// 2.��ѯ���� ��ѯ �Ƿ�����150 ����Ļ� ��Ǯ
		// 3.��ѯ�����Ƿ񹫹���ѯ 3 +1 and 10 +2
		try {
			String docID = (String) msg.getValue("docID");
			String queID = (String) msg.getValue("queID");

			// queid ��ѯ ���� ָ��

			QuesMainVo vo = new QuesMainVo();
			vo.setQUESMAIN_ID(Integer.valueOf(queID));
			List<QuesMainVo> quevos = quesMainService
					.queryQuesMainListByCondition(vo);

			QuesMainVo que = quevos.get(0);
			String queflag = "";
			Integer source = que.getQD_SourceType();
			if (source == 0) {
				// ָ����ѯ--T(to)
				queflag = "T";

			} else if (source == 1) {
				// ������ѯ--P(public)
				queflag = "P";
			} else {
				// �������--O(other)
				queflag = "O";
			}

			// ���浽--�رձ�--ZIXUN_CloseQue

			Integer back1 = quesMainService.saveCloseQue(queID, docID, queflag);

			System.out.println("�ر�����+++�󱣴� �����ɹ�������");


				
				
				// ��ȡ����ǰ���·ݺ���
				 Calendar a = Calendar.getInstance();  
			      int yearrrrrrrr= a.get(Calendar.YEAR);
			      int monthrrrr= a.get(Calendar.MONTH)+1;

			      
		     	 System.out.println("��ݣ�����"+yearrrrrrrr);
			      
		    	 System.out.println("�·ݣ�����"+monthrrrr);
			      
			    a.set(Calendar.YEAR, yearrrrrrrr);  
			    a.set(Calendar.MONTH, monthrrrr-1);  
			    a.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
			    a.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
			    int maxDate = a.get(Calendar.DATE);
			    
			    System.out.println("�����   ��       "+maxDate+"   ��");
			    
			    //ƴ�������� ʱ������    '2014-10-27 00:00:00'   '2014-10-27 23:59:59'
			    
			    String queryStart=""+yearrrrrrrr+"-"+monthrrrr+"-"+"1"+"   "+"00:00:00";
			    String queryEnd=""+yearrrrrrrr+"-"+monthrrrr+"-"+maxDate+" "+" 23:59:59";
			    
			    System.out.println("��ʼʱ�䣺����"+queryStart);
			    System.out.println("����ʱ�䣺����"+queryEnd);

			    
			    //��ʼ��ѯzixun��Ĺ�����ѯ���� 1.���ڵ���150 �� �浽�����У�  2. Ȼ���Ǯ  3. Ȼ������ quartz
			    
				
			    
		 	    
			       List<CloseQueVo>       docList =quesMainService.queryCloseQueDoctorAndCount(queryStart, queryEnd);
				   if(docList.size()==0){
						return new ReturnValue(-10000, "++++û������������ ��Ǯҽ��   +++ " );
				   }else{
					   
					    //��ȡ  feeTemplateId  
					   
					   
					   JSONObject postpara = new JSONObject();
					   
						
					   postpara.put("price",30000 );
					   postpara.put("doctorPrice",30000);
					   
					   String  queryback= postService.queryFeeTemplateId(postpara.toString());
					   
					   net.sf.json.JSONObject  json1 = net.sf.json.JSONObject.fromObject(queryback);
					   int  feeTemplateId =   (Integer) json1.get("feeTemplateId");
					   
					   System.out.println("��ȡ����"+feeTemplateId);
					   
					   
					   //��ʼִ��ҵ��   ---------------------  
					   
					   for (CloseQueVo CloseQueVo : docList) {
						   //����post����  ��Ǯ
						  
						   // Ψһ��ˮ�Ż��
						   long  orderno =Long.valueOf(getOrderNo());
						   
						   System.out.println("��ε���ˮ��"+orderno);
						   JSONObject dcBillJson = new JSONObject();
						   dcBillJson.put("doctorUid", CloseQueVo.getDoctorId());
						   dcBillJson.put("serviceRecordId", orderno);
						   dcBillJson.put("price", 30000);
						   dcBillJson.put("serviceId", 6);
						   dcBillJson.put("feeTemplateId", feeTemplateId);
						   
						   System.out.println(dcBillJson.toString());
						   String reback = postService.insertBill(dcBillJson.toString());
						   //�ۿ�ɹ�  ��¼һ�����ݿ�  
						   
						   System.out.println("��Ǯ������Ϣ����"+reback);
						   
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
							//״̬����ΪT
							    flag="T";
							  
							  System.out.println("��ʼ�¼����");
							  
							  JobpayVo voo = new JobpayVo();
							  voo.setDOCID(docid);
							  voo.setNUM(num);
							  voo.setSEQNUM(seqnum);
							  voo.setFLAG(flag);
							  voo.setROUTEMESSAGE(routemessage);
							  
							  JobpayService.insertJobpay(voo);
							  
							  
							  
							 
						  }else{
							//״̬����ΪF
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
				

			
			
			
			return new ReturnValue(1, "++++�ر�����     ���� ҽ���Ƿ��Ǯ  �¼� +++.            " + docID
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
        No = Long.parseLong(nowdate)*1000;//�������һ�충����Ļ�������һ������
 
        String backNO=No+"0"+getNo();
        return backNO;
    }
	public static  int ADDNO = 0;
	public static long getNo(){
		ADDNO=ADDNO+5;
 
        return ADDNO;
    }
	
}
