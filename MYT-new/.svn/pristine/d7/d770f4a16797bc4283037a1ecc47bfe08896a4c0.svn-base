package com.yihu.myt.service.service.impl;


import java.util.Calendar;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.AskDoctorNewInterfaceImpV1Enum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.service.service.IAskDoctorNewInterfaceV1Service;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.QuesMainVo;

public class AskDoctorNewInterfaceV1ServiceImp  implements  IAskDoctorNewInterfaceV1Service{

	@Override
	public String queryDoctorQueFree(int doctorID) throws Exception {
		// TODO Auto-generated method stub

		Sql sql = DB.me().createSql(AskDoctorNewInterfaceImpV1Enum.queryDoctorQueFree);		
		StringBuilder docidsuch = new StringBuilder();
		StringBuilder monthsuch = new StringBuilder();
		StringBuilder daysuch = new StringBuilder();
		
		//获取 本月的 时间（开始   结束   时间）

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
	    
	    
	    
	    
		
		
		
		//获取 本日的 时间（开始   结束   时间）
		
		String heartstart = yearrrrrrrr + "-" + monthrrrr + "-" + todate
				+ "  00:00:00" + "";
		String heartend = yearrrrrrrr + "-" + monthrrrr + "-" + todate
				+ " 23:59:59" + "";
		
		
		
		
		if(StringUtil.isNotEmpty(doctorID)){
			
			docidsuch.append(" and ask_doctorid= " +doctorID+"");
			
		}
		
		
		monthsuch.append("AND REPLY_CreateTime > '"+queryStart+"'");
		monthsuch.append("AND REPLY_CreateTime < '"+queryEnd+"'");
		
		
		
		daysuch.append("AND REPLY_CreateTime > '"+heartstart+"'");
		daysuch.append("AND REPLY_CreateTime < '"+heartend+"'");
		
		
		
		
		sql.addVar("@docid", docidsuch.toString());
		sql.addVar("@suchmonth", monthsuch.toString());
		sql.addVar("@suchday", daysuch.toString());
		
		System.out.println(sql.getSqlString());
		
		com.common.json.JSONObject rt;
		rt = DB.me().queryForJson(
				MyDatabaseEnum.YiHuNet2008, sql);
		
		
		System.out.println(rt.toString());
		return rt.toString();
		

	}

	@Override
	public String queryHotQueByDept(String deptid, int star, int end,String startdate,String enddate)
			throws Exception {
		
		Sql sql = DB.me().createSql(AskDoctorNewInterfaceImpV1Enum.queryHotQueByDept);	
		
		StringBuilder dept = new StringBuilder();
		StringBuilder time = new StringBuilder();
		StringBuilder page = new StringBuilder();
		
		
		
		dept.append("	AND QD_DeptTwoIDS LIKE  '%"+deptid+"%'");
		
		
		
		if(StringUtil.isNotEmpty(startdate)){
			
			time.append("  AND QUESMAIN_CreateTime > ' " +startdate+" '");
		
		}
		
		if(StringUtil.isNotEmpty(enddate)){
			
		
			time.append("  AND QUESMAIN_CreateTime < ' " +enddate+" '");
		}
		
		page.append("  AND rowid >=" +star+"");
		page.append("  AND rowid <=" +end+"");
		
		
		
		sql.addVar("@dept", dept.toString());
		sql.addVar("@time", time.toString());
		sql.addVar("@page", page.toString());
		
		System.out.println(sql.getSqlString());
		
		
		com.common.json.JSONObject rt;
		rt = DB.me().queryForJson(
				MyDatabaseEnum.YiHuNet2008, sql);
		
		
		return rt.toString();
	}

	@Override
	public String queryWaitAndNewReplyCount(int doctorUid) throws Exception {
	
		Sql sql = DB.me().createSql(AskDoctorNewInterfaceImpV1Enum.queryWaitAndNewReplyCount);		
		StringBuilder docidsuch = new StringBuilder();
		StringBuilder idis = new StringBuilder(""+doctorUid+"");
		
		
		docidsuch.append("  AND ASK_DoctorID =  " +doctorUid+"");
		
		
		sql.addVar("@doctorUid", docidsuch.toString());
		sql.addVar("@idis", idis.toString());
		System.out.println(sql.getSqlString());
		com.common.json.JSONObject rt;
		rt = DB.me().queryForJson(
				MyDatabaseEnum.YiHuNet2008, sql);
		return rt.toString();
	}

	@Override
	public Integer deleteQuesVoByUser(QuesMainVo vo) throws Exception {
		// TODO Auto-generated method stub
		
		

		Sql sql = DB.me().createSql(AskDoctorNewInterfaceImpV1Enum.deleteQuesVoByUser);		
		StringBuilder such = new StringBuilder();
		
		if(vo.getASK_UserID()!=0){
			such.append(" AND ASK_USERID="+vo.getASK_UserID());
		}
		
		if(vo.getQUESMAIN_ID()!=0){
			such.append(" AND quesmain_id="+vo.getQUESMAIN_ID());
		
		}
		
		
		
		
		
		
		sql.addVar("@such", such.toString());
		
		System.out.println(sql.getSqlString());

		Integer count = DB.me().update(MyDatabaseEnum.YiHuNet2008, sql);
		return  count;
	}

}
