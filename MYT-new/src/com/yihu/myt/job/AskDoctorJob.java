package com.yihu.myt.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.boss.sdk.ExecutionContext;
import com.boss.sdk.ExecutionResult;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.AccMembershipcardBean;
import com.yihu.account.api.ChargeReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.account.api.ReturnValueBean;
import com.yihu.api.MsgApi;
import com.yihu.baseinfo.api.DoctorAccountApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.IBookEnrolService;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.ICreditsRecordService;
import com.yihu.myt.service.service.IDepartmentsService;
import com.yihu.myt.service.service.IDoctorFreeCountEditService;
import com.yihu.myt.service.service.IDoctorFreeCountService;
import com.yihu.myt.service.service.IFailOverQuesService;
import com.yihu.myt.service.service.IFilesService;
import com.yihu.myt.service.service.IJobpayService;
import com.yihu.myt.service.service.INewDoctorService;
import com.yihu.myt.service.service.IPatientService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.service.service.IQuesCloseWaterService;
import com.yihu.myt.service.service.IQuesEvaluateService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.service.service.IReplyService;
import com.yihu.myt.service.service.IUserFreeCountService;
import com.yihu.myt.service.service.impl.PostService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.ConsumerOrdersVo;
import com.yihu.myt.vo.CreditsRecordVo;
import com.yihu.myt.vo.DoctorFreeCountEditVo;
import com.yihu.myt.vo.DoctorFreeCountVo;
import com.yihu.myt.vo.FailOverQuesVo;
import com.yihu.myt.vo.JobpayVo;
import com.yihu.myt.vo.NewDocProblem;
import com.yihu.myt.vo.QuesCloseWaterVo;
import com.yihu.myt.vo.QuesEvaluateVo;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.myt.vo.UserFreeCountVo;
import com.yihu.smsgw.api.PublicForSmsService;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

public class AskDoctorJob {
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
	private static IFailOverQuesService failOverQuesService = Ioc
			.get(IFailOverQuesService.class);
	private static ICreditsRecordService creditsRecordService = Ioc
			.get(ICreditsRecordService.class);
	private static IQuesCloseWaterService quesCloseWaterService = Ioc
			.get(IQuesCloseWaterService.class);
	private static IDoctorFreeCountService doctorFreeCountService = Ioc
			.get(IDoctorFreeCountService.class);
	private static IDoctorFreeCountEditService doctorFreeCountEditService = Ioc
			.get(IDoctorFreeCountEditService.class);
	private static IUserFreeCountService userFreeCountService = Ioc
			.get(IUserFreeCountService.class);
	private static IBookEnrolService bookEnrolService = Ioc
			.get(IBookEnrolService.class);
	private static IReplyService replyService = Ioc
			.get(IReplyService.class); 
	
	private static IPostService postService = Ioc.get(IPostService.class);
	
	public static void main(String[] args) throws Exception {
		try {
			// 获取到当前的月份和年
			
			
			
			AskDoctorJob job = new AskDoctorJob();
 
			ExecutionResult rt = job.pushMonthlyreport(null);
 
			System.out.println(rt.getMessage());
			/*
			 * com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi =
			 * Rpc.get( DoctorAccountApi.class,
			 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
			 */
			/*
			 * com.yihu.baseinfo.api.CommonApi api=Rpc.get(CommonApi.class , new
			 * Url("10.0.100.124", 8912));
			 */
			/*
			 * InterfaceMessage msg = new InterfaceMessage(); JSONObject json =
			 * new JSONObject(); json.put("doctorUid", "710003065");
			 * msg.setParam(json.toString()); String rt =
			 * doctorAccountApi.addReplycount(msg); System.out.print(rt);
			 */
			/*
			 * AskDoctorJob adc = new AskDoctorJob();
			 * System.out.print(adc.overQues(null).getMessage());
			 */
			/*
			 * com.yihu.account.api.IAccountService accountService = Rpc.get(
			 * IAccountService.class,
			 * ConfigUtil.getInstance().getUrl("url.account"));
			 * 
			 * AccMembershipcardBean accBean = new AccMembershipcardBean();
			 * accBean.setAccountsn(11381020); accBean.setState("3"); accBean =
			 * accountService .getMembershipcardObject(accBean);
			 * 
			 * System.out.println(accBean.getAccounttypesn());
			 */
		/*	com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));*/

			/*
			 * ReturnValueBean clrt = accountService.clearFrozen( 11431874,
			 * 5462, "12", "01");
			 */

			/*
			 * ChargeReturnBean chrt = accountService.charge( "670187279",
			 * 11431874, 7, 84, 5462, 1000000, "网站", "问医生默认“网上问医费用”", "-3000",
			 * null, 0, false, null, null);
			 * 
			 * ReturnValueBean ret = accountService.frozenWswyFee(11431874,
			 * 3000, 710015580, "12", "01", 1000000, "网站");
			 */
		/*	AskDoctorJob job = new AskDoctorJob();
			ExecutionResult rt = job.overQues(null);

			System.out.println(rt.getCode());
			System.out.print(rt.getMessage());
			/* FailOverQuesVo failVo = new FailOverQuesVo(); */
			/*
			 * failVo.setFailOverDoctorID(1); failVo.setFailOverOrderID(0);
			 * failVo.setFailOverPrice(0); failVo.setFailOverUserID(1);
			 * failVo.setFailOverQuesID(1); failVo.setFailOverUserCardID("0");
			 * failVo.setFailOverStatus(1); failVo.setFeeTemplateId(0);
			 * failVo.setFailOverType(10);// failVo.setFailOver(1);
			 * failVo.setFailOverStatus(1);
			 * System.out.println(failOverQuesService
			 * .updateFailOverQuesVo(failVo));
			 * //System.out.println(failOverQuesService
			 * .insertFailOverQues(failVo));
			 */
			/*
			 * FailOverQuesVo fVo = new FailOverQuesVo(); FailOverQuesVo fail;
			 * 
			 * List<FailOverQuesVo> failList = failOverQuesService
			 * .queryFailOverQuesListByCondition(fVo);
			 */

			// overFreeQues
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	private static INewDoctorService newDoctorService = Ioc
			.get(INewDoctorService.class); 
	private static IJobpayService JobpayService = Ioc
			.get(IJobpayService.class); 
	
	public static  int ADDNO = 0;
	public static String getOrderNo(){
		
        long No = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowdate = sdf.format(new Date());
        No = Long.parseLong(nowdate)*1000;//这里如果一天订单多的话可以用一万或更大
 
        String backNO=No+"0"+getNo();
        return backNO;
    }
	
	public static long getNo(){
		ADDNO=ADDNO+5;
 
        return ADDNO;
    }

	/**
	 *   公共咨询回复量奖励
	 *    标准：一个内月回复公共咨询数 ≧ 150 条， 每月奖励 300 元
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult backReward(ExecutionContext c) {
		try {
			
			// 获取到当前的月份和年
			 Calendar a = Calendar.getInstance();  
		      int yearrrrrrrr= a.get(Calendar.YEAR);
		      int monthrrrr= a.get(Calendar.MONTH);
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
		    
		   List<NewDocProblem>      docList =  newDoctorService.queryReward(queryStart, queryEnd);
		   if(docList.size()==0){
			   return new ExecutionResult(" 没有 满足 条件的医生");
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
			   
			   for (NewDocProblem newDocProblem : docList) {
				   //发送post请求  加钱
				  
				   // 唯一流水号获得
				   long  orderno =Long.valueOf(getOrderNo());
				   
				   System.out.println("这次的流水号"+orderno);
				   JSONObject dcBillJson = new JSONObject();
				   dcBillJson.put("doctorUid", newDocProblem.getASK_DoctorID());
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
				  
				  
				  
				   String docid= newDocProblem.getASK_DoctorID();
				   String num=newDocProblem.getNum();
				   String seqnum=String.valueOf(orderno);
				   String flag;
				   String  routemessage=code+":"+message;
				  if("10000".equals(code)){
					//状态设置为T
					    flag="T";
					  
					  System.out.println("这笔记录下来");
					  
					  JobpayVo vo = new JobpayVo();
					  vo.setDOCID(docid);
					  vo.setNUM(num);
					  vo.setSEQNUM(seqnum);
					  vo.setFLAG(flag);
					  vo.setROUTEMESSAGE(routemessage);
					  
					  JobpayService.insertJobpay(vo);
					  
					  
					  
					 
				  }else{
					//状态设置为F
					   flag="F";
					   
						  JobpayVo vo = new JobpayVo();
						  vo.setDOCID(docid);
						  vo.setNUM(num);
						  vo.setSEQNUM(seqnum);
						  vo.setFLAG(flag);
						  vo.setROUTEMESSAGE(routemessage);
						  
						  JobpayService.insertJobpay(vo);
					 
				  }
				   //
  
				}
		   }

			
			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			
			
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}
	
	
	
	
	
	/**
	 * 推送月报  （每月一次）推送上个月的内容
	 * @throws Exception 
	 * */
	public  ExecutionResult  pushMonthlyreport(ExecutionContext c) throws Exception{
		//1.获取上个月的时间   2.根据时间查询  总咨询人数   成功咨询人数  分钟数  3.获取所有医生 推送
		
		
		// 获取到当前的月份和年
		 Calendar a = Calendar.getInstance();  
	      int yearrrrrrrr= a.get(Calendar.YEAR);
	      int monthrrrr= a.get(Calendar.MONTH);
    	 System.out.println("年份：：："+yearrrrrrrr);
	      
      	 System.out.println("月份：：："+monthrrrr);
	      
	    a.set(Calendar.YEAR, yearrrrrrrr);  
	    a.set(Calendar.MONTH, monthrrrr-1);  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);
	    
	    System.out.println("这个月   有       "+maxDate+"   天");
	    
	    //拼成搜索的 时间区间    '2014-10-27 00:00:00'   '2014-10-27 23:59:59'
	    
	   if(monthrrrr==0){
		   yearrrrrrrr=yearrrrrrrr-1;
		   monthrrrr=12;
		   
	   }
	    
	    String queryStart=""+yearrrrrrrr+"-"+monthrrrr+"-"+"1"+"   "+"00:00:00";
	    String queryEnd=""+yearrrrrrrr+"-"+monthrrrr+"-"+maxDate+" "+" 23:59:59";
	    
	    System.out.println("开始时间：：："+queryStart);
	    System.out.println("结束时间：：："+queryEnd);
	    
	    

		String back= newDoctorService.queryPushMsg(queryStart,queryEnd);
		net.sf.json.JSONObject    datamsg=  net.sf.json.JSONObject.fromObject(back);
	
		net.sf.json.JSONArray  pushmsg = datamsg.getJSONArray("result");
		System.out.println(pushmsg.toString());

		String allzixun =(String) pushmsg.getJSONObject(0).getString("allzixun");
		String finzixun =(String) pushmsg.getJSONObject(0).getString("finzixun");
		String allmin =(String) pushmsg.getJSONObject(0).getString("allmin");
		
		System.out.println(allzixun+finzixun+allmin);
		
		
		//开始拼装推送 信息
		
		net.sf.json.JSONObject iospara = new net.sf.json.JSONObject();
	    iospara.put("ti", "电话诊室");
	    //ioscontent
	    String content="@month月总共预约数量 @allzixun 成功咨询数量 @finzixun 总分钟数 @allmin";
	    content=content.replace("@month", String.valueOf(monthrrrr));
	    content=content.replace("@allzixun", allzixun);
	    content=content.replace("@finzixun",finzixun );
	    content=content.replace("@allmin",allmin);
	    System.out.println("content+++++"+content);
	    iospara.put("content",content );
	    iospara.put("operCode", "13003");
	    
	    net.sf.json.JSONObject androidpara = new net.sf.json.JSONObject();
	    //contentHtml
	    String contentHtml="<font size='4' color='#666666'>@month月份总共预约咨询<font size=4 color='#FF9900'>@allzixun</font>人次，总共完成咨询<font size=4 color='#FF9900'>@finzixun</font>人次，总时长：<font size=4 color='#FF9900'>@allmin</font>分钟</font>";
	    contentHtml=contentHtml.replace("@month", String.valueOf(monthrrrr));
	    contentHtml=contentHtml.replace("@allzixun", allzixun);
	    contentHtml=contentHtml.replace("@finzixun",finzixun );
	    contentHtml=contentHtml.replace("@allmin",allmin);
	    System.out.println("contentHtml+++++"+contentHtml);
	    androidpara.put("contentHtml",contentHtml);
	    //contentHtml
	    String ios="<font color='#5b5b5b'  size='16' ><b>@month月份总共预约咨询<font  color='#ff8100'> @allzixun </font>人次，总共完成咨询<font  color='#ff8100'> @finzixun </font>人次，总时长：<font  color='#ff8100'> @allmin </font>分钟</b></font>";
	    ios=ios.replace("@month", String.valueOf(monthrrrr));
	    ios=ios.replace("@allzixun", allzixun);
	    ios=ios.replace("@finzixun",finzixun );
	    ios=ios.replace("@allmin",allmin);
	    System.out.println("ios+++++"+ios);
	    androidpara.put("ios", ios);
	    
	    androidpara.put("title", "电话诊室");
	    androidpara.put("content", content);
	    androidpara.put("operCode", "13003");
	    //head
	    String head="<center><b><big>@month月份 - 月报</big></b></center>";
	    head=head.replace("@month", String.valueOf(monthrrrr));
	    System.out.println("head+++++"+head);
	    androidpara.put("head", head);
	    
		

	    net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		
		PostService post = new PostService();
		
		json.put("iosParam", iospara.toString());
		json.put("androidParam", androidpara.toString());
		json.put("toAppType", 1);
		//获取所有医生  循环发送
		
		
		
		
		List<QuesMainVo>    docs=  newDoctorService.queryPushDoctor(queryStart,queryEnd);
		
		for (QuesMainVo quesMainVo : docs) {
			json.put("toUsers", quesMainVo.getASK_DoctorID());
			System.out.println("FASONG"+json.toString());
			String  back11 =post.sendMsgJ(json.toString());
			System.out.println(back11);
		}
		System.out.println("发送完毕+++++++");
		return new ExecutionResult("操作成功！");
	}
	
	
	/**
	 * 累计每日获取爱心值增加
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult addDocLove(ExecutionContext c) {
		try {
			JSONObject replys = replyService.getYestodayReplyCount();
			JSONArray list = replys.getJSONArray("result");
			for (int i = 0; i < list.length(); i++) {
			/*com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));*/
				String resID = "20141230";
				if (list.getJSONObject(i).getInt("replycount") >= 3) {
					
					JSONObject axJson = new JSONObject();
					axJson.put("doctorUid",  list.getJSONObject(i).getInt("doctoruid"));
					axJson.put("typeId", 9);
					axJson.put("resId", resID);
					JSONObject AuthInfo = new JSONObject();
					AuthInfo.put("ClientId", "6000005");
					String back =postService.addDoctorLove(axJson.toString(),AuthInfo.toString());
					System.out.println(back);
					/*
					ServiceResult<String> addlrt = doctorAccountApi
							.addDoctorLove(axJson.toString());*/
					/*if (addlrt.getCode() < 0) {
						System.out.println( ApiUtil.getJsonRt(-14444, "爱心值写入失败。" + list.getJSONObject(i).getInt("doctoruid")));
					}*/
				}
				if (list.getJSONObject(i).getInt("replycount") >= 10) {
					JSONObject axJson = new JSONObject();
					axJson.put("doctorUid",  list.getJSONObject(i).getInt("doctoruid"));
					axJson.put("typeId", 10);
					axJson.put("resId", resID);
					JSONObject AuthInfo = new JSONObject();
					AuthInfo.put("ClientId", "6000005");
					String back =postService.addDoctorLove(axJson.toString(),AuthInfo.toString());
					System.out.println(back);
					/*ServiceResult<String> addlrt = doctorAccountApi
							.addDoctorLove(axJson.toString());
					if (addlrt.getCode() < 0) {
						System.out.println( ApiUtil.getJsonRt(-14444, "爱心值写入失败。" + list.getJSONObject(i).getInt("doctoruid")));
					}*/
				}
			}
			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}
	
	/**
	 * 推送提醒预约回拨
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult sendReturnPhone(ExecutionContext c) {
		try {
			JSONObject bookList =  bookEnrolService.getBookEnrolListForSendMsg();
		
			JSONArray list = bookList.getJSONArray("result");
			for(int i =0;i<list.length();i++){
			MsgApi msgApi = Rpc.get(MsgApi.class, ConfigUtil.getInstance().getUrl("url.jpushMsg"));
			JSONObject mesJson = new JSONObject();
			JSONObject androidJson = new JSONObject();
			JSONObject iosJson = new JSONObject();
			androidJson.put("uri", "");
			androidJson.put("date",DateUtil.dateFormat(new Date()));
			androidJson.put("title", "有您的预约回拨，请点击查看。");
			androidJson.put("content", "有您的预约回拨，请点击查看。");
			androidJson.put("operCode", "12001");
			iosJson.put("ti","有您的预约回拨，请点击查看。");
			iosJson.put("operCode", 12001);
			iosJson.put("content",  "有您的预约回拨，请点击查看。");
			mesJson.put("androidParam", androidJson);
			mesJson.put("toAppType", 1);
			mesJson.put("toUsers", list.getJSONObject(i).getInt("operconfid"));
			mesJson.put("iosParam", iosJson);
			InterfaceMessage pushMsg = new InterfaceMessage();
			pushMsg.setParam(mesJson.toString());
			JSONObject authinfo = new JSONObject();
			authinfo.put("ClientId", "client.myt");
			pushMsg.setAuthInfo(authinfo.toString());
			System.out.println(mesJson.toString());
 			String purt= msgApi.sendMsg(pushMsg);
			System.out.println(purt);
			//System.out.println(mesJson.toString());
			}
			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	
	/**
	 * 指定过期问题自动退费
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult qusReturnPremium(ExecutionContext c) {
		try {
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));

			JdbcConnection conn = null;
			Sql sql = DB.me().createSql(MySqlNameEnum.getListForReturnPremium);
			JSONArray array = DB.me()
					.queryForJson(MyDatabaseEnum.YiHuNet2008, sql)
					.getJSONArray("result");
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					// [{"ask_userid":11749683,"co_id":2100,"quesmain_id":16940}]
					/*
					 * System.out.println("co_id:" + obj.getInt("co_id"));
					 * System.out.println("quesmain_id:" +
					 * obj.getInt("quesmain_id"));
					 * System.out.println("ask_userid:" +
					 * obj.getInt("ask_userid"));
					 */
					ReturnValueBean clrt = accountService.clearFrozen(
							obj.getInt("ask_userid"), obj.getInt("co_id"),
							"12", "01");
					if (clrt.getCode() < 0) {
						continue;
					} else {
						// 开始事务
						conn = DB.me()
								.getConnection(MyDatabaseEnum.YiHuNet2008);
						conn.beginTransaction(50000);
						ConsumerOrdersVo cVo = new ConsumerOrdersVo();
						cVo.setCO_Status(-1);
						cVo.setCO_ID(obj.getInt("co_id"));
						int cort = consumerOrdersService
								.updateCOrdersByCondition(cVo, conn);
						if (cort < 0) {
							conn.rollback();
						}
						QuesMainVo qusVo = new QuesMainVo();
						qusVo.setQUESMAIN_ID(obj.getInt("quesmain_id"));
						qusVo.setASK_UserID(obj.getInt("ask_userid"));
						qusVo.setQD_Price(0);
						qusVo.setQD_OrdersStatus(5);
						//qusVo.setQD_DoctorGetPrice(0);
						int qmrt = quesMainService.updateQMainByCondition(
								qusVo, conn);
						if (qmrt < 0) {
							conn.rollback();
						}
						conn.commitTransaction(true);
						/*QuesCloseWaterVo vo = new QuesCloseWaterVo();
						vo.setOperType(2);// 未知渠道关闭
						vo.setCreateTime(DateUtil.dateFormat(new Date()));
						vo.setQuesID(obj.getInt("quesmain_id"));
						quesCloseWaterService.insertQuesCloseWater(vo);*/

					}
				}
			}
			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	/**
	 * 自动结束免费问题
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult overFreeQues(ExecutionContext c) {
		try {
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));

			JdbcConnection conn = null;
			Sql sql = DB.me().createSql(MySqlNameEnum.getOverFreeQues);
			JSONArray array = DB.me()
					.queryForJson(MyDatabaseEnum.YiHuNet2008, sql)
					.getJSONArray("result");
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					// [{"ask_userid":11749683,"co_id":2100,"quesmain_id":16940}]
					/*
					 * System.out.println("quesmain_id:" +
					 * obj.getInt("quesmain_id"));
					 * System.out.println("ask_doctorid:" +
					 * obj.getInt("ask_doctorid"));
					 * System.out.println("quesmain_content:" +
					 * obj.getInt("quesmain_content"));
					 * System.out.println("ask_userid:" +
					 * obj.getInt("ask_userid"));
					 */

					conn = DB.me().getConnection(MyDatabaseEnum.YiHuNet2008);
					conn.beginTransaction(3000);
					// 插入用户评价
					QuesEvaluateVo qevo = new QuesEvaluateVo();
					qevo.setASK_DoctorID(obj.getInt("ask_doctorid"));
					qevo.setASK_UserID(obj.getInt("ask_userid"));
					qevo.setELT_CRTime(DateUtil.dateFormat(new Date()));
					qevo.setELT_PleasedLevel(0);
					qevo.setELT_isSysEva(1);
					qevo.setASK_QuesID(obj.getInt("quesmain_id"));
					int elrt = quesEvaluateService.insertQuesEvaluate(qevo);
					if (elrt < 0) {
						conn.rollback();
					}
					// 更新用户状态
					QuesMainVo qusVo = new QuesMainVo();
					qusVo.setQUESMAIN_ID(obj.getInt("quesmain_id"));
					qusVo.setQD_Price(0);
					qusVo.setQD_Status(1);
					qusVo.setQD_OrdersStatus(0);
					int qmrt = quesMainService.updateQuesMain(qusVo);
					if (qmrt < 0) {
						conn.rollback();
					}
					// 操作医生记录
					InterfaceMessage msg = new InterfaceMessage();
					JSONObject json = new JSONObject();
					json.put("doctorUid", obj.getInt("ask_doctorid"));
					msg.setParam(json.toString());
					String rt = doctorAccountApi.addReplycount(msg);
					if (!rt.isEmpty()) {
						JSONObject rtJson = new JSONObject(rt);
						if (rtJson.getInt("Code") < 0) {
							conn.rollback();
						}
					}
					conn.commitTransaction(true);
					QuesCloseWaterVo vo = new QuesCloseWaterVo();
					vo.setOperType(2);// 未知渠道关闭
					vo.setCreateTime(DateUtil.dateFormat(new Date()));
					vo.setQuesID(obj.getInt("quesmain_id"));
					
					int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
					if(rCont ==0){
						int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
					}
					
				}
			}
			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	/**
	 * 自动结束收费问题
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult overQues(ExecutionContext c) {
		try {
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc.get(
					DoctorAccountApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			Sql sql = DB.me().createSql(MySqlNameEnum.getOverQues);
			JSONArray array = DB.me()
					.queryForJson(MyDatabaseEnum.YiHuNet2008, sql)
					.getJSONArray("result");
			AccMembershipcardBean accBean;
			ConsumerOrdersVo cVo;
			FailOverQuesVo failVo;
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					/*
					 * System.out.println("quesmain_id:" +
					 * obj.getInt("quesmain_id"));
					 * System.out.println("ask_doctorid:" +
					 * obj.getInt("ask_doctorid"));
					 * System.out.println("qd_price:" + obj.getInt("qd_price"));
					 * System.out.println("ask_userid:" +
					 * obj.getInt("ask_userid"));
					 * System.out.println("----------------");
					 */
					if  (obj.getInt("qd_price") == 0 && obj.getInt("qd_docfreeid") == 0) {//收费问题过期并被回答的自动关闭
						QuesMainVo qvo = new QuesMainVo();
						qvo.setQUESMAIN_ID(obj.getInt("quesmain_id"));
						qvo.setQD_Status(1);
						int qurt = quesMainService.updateQuesMain(qvo);
						if (qurt < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(0);
							failVo.setFailOverPrice(0);
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverUserCardID("0");
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(0);
							failVo.setFailOverType(8);// 8免费更新问题数据失败
							failVo.setFailMemo("收费问题过期并被回答的问题更新问题数据失败");
							failOverQuesService.insertFailOverQues(failVo);
						}
						JSONObject axJson = new JSONObject();
						axJson.put("doctorUid", obj
								.getInt("ask_doctorid"));
						axJson.put("resId", obj.getInt("quesmain_id"));
						axJson.put("typeId", 4);
						ServiceResult<String> addlrt = doctorAccountApi
								.addDoctorLove(axJson.toString());
						CreditsRecordVo crVo = new CreditsRecordVo();
						crVo.setASK_QuesID(obj.getInt("quesmain_id"));
						crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
						crVo.setASK_DoctorID(qvo.getASK_DoctorID());
						crVo.setCR_Credits(1);
						crVo.setCR_CreditsType(2);
						crVo.setCR_Type(3);
						crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
						int rtCr = creditsRecordService
								.insertCreditsRecord(crVo);
					 
						// 操作医生记录
						InterfaceMessage msg = new InterfaceMessage();
						JSONObject json = new JSONObject();
						json.put("doctorUid", obj.getInt("ask_doctorid"));
						msg.setParam(json.toString());
						String rt = doctorAccountApi.addReplycount(msg);

						if (!rt.isEmpty()) {
							JSONObject rtJson = new JSONObject(rt);
							if (rtJson.getInt("Code") < 0) {
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(0);
								failVo.setFailOverPrice(0);
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverUserCardID("0");
								failVo.setFailOverStatus(1);
								failVo.setFeeTemplateId(0);
								failVo.setFailOverType(10);//
								failVo.setFailMemo(rtJson.getString("Message"));
								failOverQuesService.insertFailOverQues(failVo);
							}
						}

					}
					else if (obj.getInt("qd_price") == 0 ) {// 指定免费问题处理
						// 用户评价
						QuesEvaluateVo qevo = new QuesEvaluateVo();
						qevo.setASK_DoctorID(obj.getInt("ask_doctorid"));
						qevo.setASK_UserID(obj.getInt("ask_userid"));
						qevo.setELT_CRTime(DateUtil.dateFormat(new Date()));
						qevo.setELT_PleasedLevel(0);
						qevo.setASK_QuesID(obj.getInt("quesmain_id"));
						qevo.setELT_isSysEva(1);
						int elrt = quesEvaluateService.insertQuesEvaluate(qevo);
						if (elrt < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(0);
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverPrice(0);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverUserCardID("0");
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(0);
							failVo.setFailOverType(7);// 免费用户评价失败
							failVo.setFailMemo("免费用户评价失败");

							failOverQuesService.insertFailOverQues(failVo);
						}
						// 更新问题表数据
						QuesMainVo qvo = new QuesMainVo();
						qvo.setQUESMAIN_ID(obj.getInt("quesmain_id"));
						qvo.setQD_Status(1);
						int qurt = quesMainService.updateQuesMain(qvo);
						if (qurt < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(0);
							failVo.setFailOverPrice(0);
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverUserCardID("0");
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(0);
							failVo.setFailOverType(8);// 8免费更新问题数据失败
							failVo.setFailMemo("免费更新问题数据失败");
							failOverQuesService.insertFailOverQues(failVo);
						}
						// 操作医生记录
						InterfaceMessage msg = new InterfaceMessage();
						JSONObject json = new JSONObject();
						json.put("doctorUid", obj.getInt("ask_doctorid"));
						msg.setParam(json.toString());
						String rt = doctorAccountApi.addReplycount(msg);

						if (!rt.isEmpty()) {
							JSONObject rtJson = new JSONObject(rt);
							if (rtJson.getInt("Code") < 0) {
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(0);
								failVo.setFailOverPrice(0);
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverUserCardID("0");
								failVo.setFailOverStatus(1);
								failVo.setFeeTemplateId(0);
								failVo.setFailOverType(10);//
								failVo.setFailMemo(rtJson.getString("Message"));
								failOverQuesService.insertFailOverQues(failVo);
							}
						}

					} else {// 价格非0的
						accBean = new AccMembershipcardBean();
						accBean.setAccountsn(obj.getInt("ask_userid"));
						accBean.setState("3");
						accBean = accountService
								.getMembershipcardObject(accBean);
						if (accBean == null) {
							continue;
						}
						cVo = new ConsumerOrdersVo();
						cVo.setASK_QuesID(obj.getInt("quesmain_id"));
						cVo = consumerOrdersService
								.queryConsumerOrdersByQuesID(cVo);
						int price = cVo.getCO_Price() * (-1);
						int feeTemplateId = cVo.getFeeTemplateId();
						try {
							// 解除冻结
							ReturnValueBean clrt = accountService.clearFrozen(
									obj.getInt("ask_userid"), cVo.getCO_ID(),
									"12", "01");
							if (clrt.getCode() < 0) {
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(cVo.getCO_ID());
								failVo.setFailOverPrice(price);
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverUserCardID(accBean
										.getCardnumber());
								failVo.setFailOverStatus(0);
								failVo.setFeeTemplateId(feeTemplateId);
								failVo.setFailOverType(1);// 1 表示解冻失败
								failVo.setFailMemo(clrt.getMessage());
								;
								failOverQuesService.insertFailOverQues(failVo);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(1);// 1 表示解冻失败
							failVo.setFailMemo(e.getMessage());
							;
							failOverQuesService.insertFailOverQues(failVo);
							// return new ExecutionResult("异常!");
						}

						// 计费扣除用户费用
						try {
							/*ChargeReturnBean chrt = accountService.charge(
									accBean.getCardnumber(),
									obj.getInt("ask_userid"),
									cVo.getASK_QuesType(), 84, cVo.getCO_ID(),
									1000000, "网站", "问医生默认“网上问医费用”",
									String.valueOf(price), null, 0, false,
									null, null);
*/							
							String chargeRt =	postService.charge(String.valueOf(accBean.getCardnumber()), "84",String.valueOf(cVo.getCO_ID()) ,"1000000", "网站","0"
									, "false", "问医生默认“网上问医费用”", String.valueOf(price), "1", String.valueOf(cVo.getASK_DoctorID())
									,"0","0","{\"ClientId\":1000000}");
							System.out.println(chargeRt);
							net.sf.json.JSONObject js =  net.sf.json.JSONObject.fromObject(chargeRt);
							if (js.getInt("Code")<0&& js.getInt("Code")!= -2000) {// 如果返回意扣款更新不操作
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(cVo.getCO_ID());
								failVo.setFailOverPrice(price);
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserCardID(accBean
										.getCardnumber());
								failVo.setFailOverStatus(1);
								failVo.setFeeTemplateId(feeTemplateId);
								failVo.setFailOverType(2);// 2 表示扣款失败
								failVo.setFailMemo(js.getString("Message"));
								failOverQuesService.insertFailOverQues(failVo);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(2);// 2 表示扣款失败
							failVo.setFailMemo(e.getMessage());
							failOverQuesService.insertFailOverQues(failVo);
							// return new ExecutionResult("异常!");
						}
						try {
							// 医生加钱
							JSONObject dcBillJson = new JSONObject();
							dcBillJson.put("doctorUid",
									obj.getInt("ask_doctorid"));
							dcBillJson.put("serviceRecordId", cVo.getCO_ID());
							dcBillJson.put("serviceId", 2);
							dcBillJson.put("feeTemplateId", feeTemplateId);
							dcBillJson.put("price", price * (-1) * 6 / 10);// 医生扣费
							ServiceResult<String> rt = doctorServiceApi
									.insertBill(dcBillJson.toString());
							if (rt.getCode() < 0) {
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(cVo.getCO_ID());
								failVo.setFailOverPrice(price);
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserCardID(accBean
										.getCardnumber());
								failVo.setFailOverStatus(1);
								failVo.setFeeTemplateId(feeTemplateId);
								failVo.setFeeTemplateId(feeTemplateId);
								failVo.setFailOverType(3);// 3 表示扣款失败
								failVo.setFailMemo(rt.getMessage());
								failOverQuesService.insertFailOverQues(failVo);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(3);// 3表示扣款失败
							failVo.setFailMemo(e.getMessage());
							failOverQuesService.insertFailOverQues(failVo);
							// return new ExecutionResult("异常!");
						}
						// 更新流水订单信息
						cVo.setCO_Status(1);
						int cort = consumerOrdersService
								.updateConsumerOrdersByCondition(cVo);
						if (cort < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(4);// 更新流水失败
							failVo.setFailMemo(" 更新流水失败");
							failOverQuesService.insertFailOverQues(failVo);
						}

						// 用户评价
						QuesEvaluateVo qevo = new QuesEvaluateVo();
						qevo.setASK_DoctorID(obj.getInt("ask_doctorid"));
						qevo.setASK_UserID(obj.getInt("ask_userid"));
						qevo.setELT_CRTime(DateUtil.dateFormat(new Date()));
						qevo.setELT_PleasedLevel(0);
						qevo.setELT_isSysEva(1);
						qevo.setASK_QuesID(obj.getInt("quesmain_id"));
						int elrt = quesEvaluateService.insertQuesEvaluate(qevo);
						if (elrt < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(5);// 5用户评价失败
							failVo.setFailMemo("用户评价失败");
							failOverQuesService.insertFailOverQues(failVo);
						}
						// 更新问题表数据
						QuesMainVo qvo = new QuesMainVo();
						qvo.setQUESMAIN_ID(obj.getInt("quesmain_id"));
						qvo.setQD_Status(1);
						int qurt = quesMainService.updateQuesMain(qvo);
						if (qurt < 0) {
							failVo = new FailOverQuesVo();
							failVo.setFailOverDoctorID(obj
									.getInt("ask_doctorid"));
							failVo.setFailOverOrderID(cVo.getCO_ID());
							failVo.setFailOverPrice(price);
							failVo.setFailOverUserID(obj.getInt("ask_userid"));
							failVo.setFailOverQuesID(obj.getInt("quesmain_id"));
							failVo.setFailOverUserCardID(accBean
									.getCardnumber());
							failVo.setFailOverStatus(1);
							failVo.setFeeTemplateId(feeTemplateId);
							failVo.setFailOverType(6);// 6更新问题数据失败
							failVo.setFailMemo("用户评价失败");
							failOverQuesService.insertFailOverQues(failVo);
						}

						// 操作医生记录
						InterfaceMessage msg = new InterfaceMessage();
						JSONObject json = new JSONObject();
						json.put("doctorUid", obj.getInt("ask_doctorid"));
						msg.setParam(json.toString());
						String rt = doctorAccountApi.addReplycount(msg);

						if (!rt.isEmpty()) {
							JSONObject rtJson = new JSONObject(rt);
							if (rtJson.getInt("Code") < 0) {
								failVo = new FailOverQuesVo();
								failVo.setFailOverDoctorID(obj
										.getInt("ask_doctorid"));
								failVo.setFailOverOrderID(cVo.getCO_ID());
								failVo.setFailOverPrice(price);
								failVo.setFailOverUserID(obj
										.getInt("ask_userid"));
								failVo.setFailOverQuesID(obj
										.getInt("quesmain_id"));
								failVo.setFailOverUserCardID(accBean
										.getCardnumber());
								failVo.setFailOverStatus(1);
								failVo.setFeeTemplateId(feeTemplateId);
								failVo.setFailOverType(10);// 6更新问题数据失败
								failVo.setFailMemo("更新问题数据失败");
								failOverQuesService.insertFailOverQues(failVo);
							}
						}
					}

					QuesCloseWaterVo vo = new QuesCloseWaterVo();
					vo.setOperType(2);// 未知渠道关闭
					vo.setCreateTime(DateUtil.dateFormat(new Date()));
					vo.setQuesID(obj.getInt("quesmain_id"));
					int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
					if(rCont ==0){
						int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
					}
				}
			}

			// 执行验证操作失败的重新检查
			FailOverQuesVo fVo = new FailOverQuesVo();
			FailOverQuesVo fail;

			List<FailOverQuesVo> failList = failOverQuesService
					.queryFailOverQuesListByCondition(fVo);
			int i = 0;
			do {
				for (FailOverQuesVo vo : failList) {
					if (vo.getFailOverType().equals(1)) {// 解冻失败
						try {
							// 解除冻结
							ReturnValueBean clrt = accountService.clearFrozen(
									vo.getFailOverUserID(),
									vo.getFailOverOrderID(), "12", "01");
							if (clrt.getCode() > 0) {
								fail = new FailOverQuesVo();
								fail.setFailOver(vo.getFailOver());
								fail.setFailOverStatus(0);
								failOverQuesService.updateFailOverQuesVo(fail);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
						}
					}
					if (vo.getFailOverType().equals(2)) {// 付费失败
						try {
							// 计费
							ChargeReturnBean chrt = accountService.charge(
									vo.getFailOverUserCardID(),
									vo.getFailOverUserID(), 7, 84,
									vo.getFailOverOrderID(), 1000000, "网站",
									"问医生默认“网上问医费用”",
									vo.getFailOverPrice() + "", null, 0, false,
									null, null);
							if (chrt.getCode() > 0 || chrt.getCode() == -2000) {
								fail = new FailOverQuesVo();
								fail.setFailOver(vo.getFailOver());
								fail.setFailOverStatus(0);
								failOverQuesService.updateFailOverQuesVo(fail);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
						}
					}
					if (vo.getFailOverType().equals(3)) {// 付费失败
						try {
							// 医生加钱
							JSONObject dcBillJson = new JSONObject();
							dcBillJson.put("doctorUid",
									vo.getFailOverDoctorID());
							dcBillJson.put("serviceRecordId",
									vo.getFailOverOrderID());
							dcBillJson.put("serviceId", 2);
							dcBillJson.put("feeTemplateId",
									vo.getFeeTemplateId());
							dcBillJson.put("price", vo.getFailOverPrice()
									* (-1) * 6 / 10);// 医生扣费
							ServiceResult<String> rt = doctorServiceApi
									.insertBill(dcBillJson.toString());
							if (rt.getCode() > 0) {
								fail = new FailOverQuesVo();
								fail.setFailOver(vo.getFailOver());
								fail.setFailOverStatus(0);
								failOverQuesService.updateFailOverQuesVo(fail);
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.logInfo(e.getMessage());
						}
					}
				}
				failList = failOverQuesService
						.queryFailOverQuesListByCondition(fVo);
				i++;
			} while (i == 5);

			return new ExecutionResult("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	/**
	 * 自动过期用户占用的免费咨询名额
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult overUserFreeAskCount(ExecutionContext c) {
		try {
			UserFreeCountVo uvo = new UserFreeCountVo();
			uvo.setIfOver(0);
			List<UserFreeCountVo> userFreeList = userFreeCountService
					.queryUserFreeOverList(uvo);
			for (UserFreeCountVo vo : userFreeList) {
				vo.setEndTime(DateUtil.dateFormat(new Date()));
				int rt = userFreeCountService.updateUserFree(vo);
				if (rt < 0) {
					Logger.get().info(
							"com.yihu.myt.job.AskDoctorJob",
							LogBody.me().set("msg",
									"更新失败,dfcid=" + uvo.getDfcId()));
				}
				DoctorFreeCountVo dvo = new DoctorFreeCountVo();
				dvo.setDfcId(vo.getDfcId());
				dvo = doctorFreeCountService.queryDoctorFreeCount(dvo);
				dvo.setOccupyFreeCount(dvo.getOccupyFreeCount() - 1);
				dvo.setRemainFreeCount(dvo.getRemainFreeCount() + 1);
				dvo.setLastChangeTime(DateUtil.dateFormat(new Date()));
				int rtd = doctorFreeCountService
						.updateDoctorFreeCountForUpFreeCount(dvo);
				if (rtd < 0) {
					Logger.get().info(
							"com.yihu.myt.job.AskDoctorJob",
							LogBody.me().set("msg",
									"更新失败,dfcid=" + uvo.getDfcId()));
				}
			}
			return new ExecutionResult(" 操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	/**
	 * 夜间更新医生的免费指定咨询数量
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult updateDoctorFreeCount(ExecutionContext c) {
		try {
			DoctorFreeCountVo vvv = new DoctorFreeCountVo();
			doctorFreeCountService.upDocFreeCountToInitialization(vvv);
			DoctorFreeCountEditVo docFCEVo = new DoctorFreeCountEditVo();
			List<DoctorFreeCountEditVo> dfcList = doctorFreeCountEditService
					.queryDocFreeEditListForUpdate(docFCEVo);
			DoctorFreeCountVo dfcVo;
			for (DoctorFreeCountEditVo vo : dfcList) {
				dfcVo = new DoctorFreeCountVo();
				dfcVo.setDfcId(vo.getDfcId());
				dfcVo.setFreeCount(vo.getFreeCount());
				dfcVo.setRemainFreeCount(vo.getFreeCount());
				int rt = doctorFreeCountService
						.updateDoctorFreeCountForUpFreeCount(dfcVo);
				if (rt < 0) {
					Logger.get().info(
							"com.yihu.myt.job.AskDoctorJob",
							LogBody.me().set("msg",
									"更新失败,dfcid=" + vo.getDfcId()));
				}
				vo.setEndTime(DateUtil.dateFormat(new Date()));
				int rtd = doctorFreeCountEditService
						.updateDoctorFreeCountEdit(vo);
				if (rtd < 0) {
					Logger.get().info(
							"com.yihu.myt.job.AskDoctorJob",
							LogBody.me().set("msg",
									"更新失败,dfcid=" + vo.getDfcId()));
				}
			}

			return new ExecutionResult(" 操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	/**
	 * 发送 昨日22:00- 今日 9:00 的问题提醒短信
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult sendDocQueSMSJob(ExecutionContext c) {
		try {
			QuesMainVo vo = new QuesMainVo();
			com.common.json.JSONObject quesJson = quesMainService
					.queryQuesCountForSendSMS(vo);
			JSONArray result = quesJson.getJSONArray("result");
			com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
					DoctorInfoApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			for (int i = 0; i < result.length(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("doctorUid",
						result.getJSONObject(i).getString("doctoruid"));
				// jsonObj.put("displayStatus", 1);
				jsonObj.put("main", 1);
				jsonObj.put("startRow", 0);
				jsonObj.put("pageSize", 0);
				jsonObj.put("columns", "doctorAccount,doctorName");
				InterfaceMessage interfacemessage = new InterfaceMessage();
				interfacemessage.setParam(jsonObj.toString());
				net.sf.json.JSONObject dcRt = net.sf.json.JSONObject
						.fromObject(doctorInfoApi
								.queryComplexDoctorList(interfacemessage));
				net.sf.json.JSONArray dcJson = net.sf.json.JSONArray
						.fromObject(dcRt.getJSONArray("Result"));
				PublicForSmsService sms = Rpc.get(PublicForSmsService.class,
						ConfigUtil.getInstance().getUrl("url.smsgw"), 16000);
				sms.send(
						dcJson.getJSONObject(0).getString("doctorAccount"),
						"尊敬的"
								+ dcJson.getJSONObject(0).getString(
										"doctorName")
								+ "医生，您好！昨天22点到今天9点，有"
								+ result.getJSONObject(i)
										.getString("quescount")
								+ "位信赖您的患者向您提交了咨询，正等待您的解答！马上登录健康之路手机APP ：http://m.yihu.cn/d32或医护网进行回复吧！",
						10111210);
			}
			return new ExecutionResult(" 操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("异常!");
		}
	}

	private void logInfo(String msg) {
		Logger.get().info("com.yihu.myt.job.AskDoctorJob",
				LogBody.me().set("msg", msg));
	}
	
	
	

	/**
	 * 推送月报  （每月一次）推送上个月的内容
	 * @throws Exception 
	 * */
	public  ExecutionResult  pushMonthlyreportTest(ExecutionContext c) throws Exception{
		//1.获取上个月的时间   2.根据时间查询  总咨询人数   成功咨询人数  分钟数  3.获取所有医生 推送
		
		
		// 获取到当前的月份和年
		 Calendar a = Calendar.getInstance();  
	      int yearrrrrrrr= a.get(Calendar.YEAR);
	      int monthrrrr= a.get(Calendar.MONTH);
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
	    
	    

		String back= newDoctorService.queryPushMsg(queryStart,queryEnd);
		net.sf.json.JSONObject    datamsg=  net.sf.json.JSONObject.fromObject(back);
	
		net.sf.json.JSONArray  pushmsg = datamsg.getJSONArray("result");
		System.out.println(pushmsg.toString());

		String allzixun =(String) pushmsg.getJSONObject(0).getString("allzixun");
		String finzixun =(String) pushmsg.getJSONObject(0).getString("finzixun");
		String allmin =(String) pushmsg.getJSONObject(0).getString("allmin");
		
		System.out.println(allzixun+finzixun+allmin);
		
		
		//开始拼装推送 信息
		
		net.sf.json.JSONObject iospara = new 	net.sf.json.JSONObject();
	    iospara.put("ti", "电话诊室");
	    //ioscontent
	    String content="@month月总共预约数量 @allzixun 成功咨询数量 @finzixun 总分钟数 @allmin";
	    content=content.replace("@month", String.valueOf(monthrrrr));
	    content=content.replace("@allzixun", allzixun);
	    content=content.replace("@finzixun",finzixun );
	    content=content.replace("@allmin",allmin);
	    System.out.println("content+++++"+content);
	    iospara.put("content",content);
	    iospara.put("operCode", "13003");
	    
	    net.sf.json.JSONObject androidpara = new net.sf.json.JSONObject();
	    //contentHtml
	    String contentHtml="<font size='4' color='#666666'>@month月份总共预约咨询<font size=4 color='#FF9900'>@allzixun</font>人次，总共完成咨询<font size=4 color='#FF9900'>@finzixun</font>人次，总时长：<font size=4 color='#FF9900'>@allmin</font>分钟</font>";
	    contentHtml=contentHtml.replace("@month", String.valueOf(monthrrrr));
	    contentHtml=contentHtml.replace("@allzixun", allzixun);
	    contentHtml=contentHtml.replace("@finzixun",finzixun );
	    contentHtml=contentHtml.replace("@allmin",allmin);
	    System.out.println("contentHtml+++++"+contentHtml);
	    androidpara.put("contentHtml",contentHtml);
	    //contentHtml
	    String ios="<font color='#5b5b5b'  size='16' ><b>@month月份总共预约咨询<font  color='#ff8100'> @allzixun </font>人次，总共完成咨询<font  color='#ff8100'> @finzixun </font>人次，总时长：<font  color='#ff8100'> @allmin </font>分钟</b></font>";
	    ios=ios.replace("@month", String.valueOf(monthrrrr));
	    ios=ios.replace("@allzixun", allzixun);
	    ios=ios.replace("@finzixun",finzixun );
	    ios=ios.replace("@allmin",allmin);
	    System.out.println("ios+++++"+ios);
	    androidpara.put("ios", ios);
	    
	    androidpara.put("title", "电话诊室");
	    androidpara.put("content", content);
	    androidpara.put("operCode", "13003");
	    //head
	    String head="<center><b><big>@month月份 - 月报</big></b></center>";
	    head=head.replace("@month", String.valueOf(monthrrrr));
	    System.out.println("head+++++"+head);
	    androidpara.put("head", head);
	    
		

	    net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		
		PostService post = new PostService();
		
		json.put("iosParam", iospara.toString());
		json.put("androidParam", androidpara.toString());
		json.put("toAppType", 1);
		//获取所有医生  循环发送
		
		
		
		/*
		List<QuesMainVo>    docs=  newDoctorService.queryPushDoctor(queryStart,queryEnd);
		
		for (QuesMainVo quesMainVo : docs) {
			json.put("toUsers", quesMainVo.getASK_DoctorID());
			System.out.println("FASONG"+json.toString());
			String  back11 =post.sendMsgJ(json.toString());
			System.out.println(back11);
		}
		*/
		
		
		json.put("toUsers","710066684");
		System.out.println("FASONG"+json.toString());
		String  back11 =post.sendMsgJ(json.toString());
		System.out.println(back11);
		
		
		json.put("toUsers","710066616");
		System.out.println("FASONG"+json.toString());
		String  back12 =post.sendMsgJ(json.toString());
		System.out.println(back12);
		
		
		json.put("toUsers","710108258");
		System.out.println("FASONG"+json.toString());
		String  back13=post.sendMsgJ(json.toString());
		System.out.println(back13);
		
		
		
		
		System.out.println("发送完毕+++++++");
		return new ExecutionResult("操作成功！");
	}
	
	
	

}
