package com.yihu.myt.service.service.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.service.service.INewDoctorService;
import com.yihu.myt.vo.Consstatistic;
import com.yihu.myt.vo.HotQuesMainVo;
import com.yihu.myt.vo.NewDocProblem;
import com.yihu.myt.vo.NewDocProblemParaVO;
import com.yihu.myt.vo.Page;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.myt.vo.SuchDownDocMSGVo;


public class NewDoctorService  implements INewDoctorService{
	
	
	@Override
	public List<NewDocProblem> queryNewDoctorList(NewDocProblemParaVO bean)
			throws Exception {
		
		// TODO wujiajun
		//进行查询  返回结果集
		
		
		 
			 //查询结果集			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.queryNewDoctorMessage);			 
			 StringBuffer datepara = new StringBuffer("");	
			 StringBuffer placepara = new StringBuffer("");	
			 

			 
			 
				if (StringUtils.isNotEmpty(bean.getStarttime())) {
					datepara.append(" AND QUESMAIN_CreateTime > '" + bean.getStarttime()+ " 00:00:00'");
					}				
				if (StringUtils.isNotEmpty(bean.getEndtime())) {
					datepara.append(" AND QUESMAIN_CreateTime < '" + bean.getEndtime() + " 23:59:59'");
					}
				
				sql.addVar("@countone", datepara.toString());
				sql.addVar("@counttwo", datepara.toString());
				sql.addVar("@countthree", datepara.toString());
				
				//
				 StringBuffer minquery = new StringBuffer("");	
					if (StringUtils.isNotEmpty(bean.getStarttime())) {
						minquery.append("  firstReplyTime > '" + bean.getStarttime()+ " 00:00:00'");
						}				
					if (StringUtils.isNotEmpty(bean.getEndtime())) {
						minquery.append(" AND firstReplyTime < '" + bean.getEndtime() + " 23:59:59'");
						}
				
					sql.addVar("@minqueryone", minquery.toString());
					sql.addVar("@minquerytwo", minquery.toString());
			
					//
				
				if (StringUtils.isNotEmpty(bean.getProvince())) {
					placepara.append(" AND ProvinceID =" + bean.getProvince());
					}				
				if (StringUtils.isNotEmpty(bean.getCity())) {
					placepara.append(" AND CityID =" + bean.getCity());
					}
				if (StringUtils.isNotEmpty(bean.getHospital())) {
					placepara.append(" AND hospitalid =" + bean.getHospital());
					}
				if (StringUtils.isNotEmpty(bean.getDepartment())) {
					placepara.append(" AND HosDeptID =" + bean.getDepartment());
					}
				
				sql.addVar("@place", placepara.toString());
				
				
				
					

			 //sql.addVar("@pone", para.toString());//加入时间的限制
			 //sql.addVar("@ptwo", para.toString());//加入时间的限制
		
			  System.out.println("查询的 sql   是：：：：：：：：：："+sql.getSqlString());
			 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
						sql, NewDocProblem.class);
		
		
		return lists;
	}
	
	
	

	@Override
	public List<NewDocProblem> queryDoctorList(NewDocProblemParaVO bean,Page pg) throws Exception {
		// TODO wujiajun
		//进行查询  返回结果集
		
		
		 
			 //查询结果集			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.getDoctorMessage);			 
			 StringBuffer para = new StringBuffer("");	
			 /*
				if (StringUtils.isNotEmpty(bean.getStarttime())) {
					para.append(" AND b.REPLY_CreateTime > '" + bean.getStarttime()+ " 00:00:00'");
					}				
				if (StringUtils.isNotEmpty(bean.getEndtime())) {
					para.append(" AND b.REPLY_CreateTime < '" + bean.getEndtime() + " 23:59:59'");
					}
					*/

			 //sql.addVar("@pone", para.toString());//加入时间的限制
			 //sql.addVar("@ptwo", para.toString());//加入时间的限制
		
			 // System.out.println("查询的 sql   是：：：：：：：：：："+sql.getSqlString());
			 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
						sql, NewDocProblem.class);
		
		
		return lists;
	}

	@Override
	public List<NewDocProblem> queryAllDoctorList(NewDocProblemParaVO vo,Page  page) throws Exception {
		// TODO wujiajun
		return null;
	}

	@Override
	public Integer queryDoctorListCount(NewDocProblemParaVO bean)
			throws Exception {
		
		

		 //查询结果集			 
		 Sql sql   = DB.me().createSql(MySqlNameEnum.getDoctorMessageCount);			 
		 StringBuffer para = new StringBuffer("");	
		 /*
			if (StringUtils.isNotEmpty(bean.getStarttime())) {
				para.append(" AND b.REPLY_CreateTime > '" + bean.getStarttime()+ " 00:00:00'");
				}				
			if (StringUtils.isNotEmpty(bean.getEndtime())) {
				para.append(" AND b.REPLY_CreateTime < '" + bean.getEndtime() + " 23:59:59'");
				}
				*/

		 //sql.addVar("@pone", para.toString());//加入时间的限制
		 //sql.addVar("@ptwo", para.toString());//加入时间的限制
	
		//  System.out.println("查询的 sql   是：：：：：：：：：："+sql.getSqlString());
		 	Integer count = DB.me().queryForInteger(MyDatabaseEnum.YiHuNet2008, sql);
		 	
		
	return count;
	}

	@Override
	public NewDocProblem queryDoctorById(String id) throws Exception {
		
		 Sql sql   = DB.me().createSql(MySqlNameEnum.getDoctorMessageById);			 
		 StringBuffer para = new StringBuffer("");	
		 
		 para.append(" and  x.DoctorUid=" + id);
		 
		 sql.addVar("@such", para.toString());
	
		
		  List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
					sql, NewDocProblem.class);
		 	
	
		
		 	
		 	
		 	
		 	
		 	
		 	if(lists!=null &&lists.size()!=0 ){
		 		
		 		
		 		return lists.get(0);
	
		 	}else{
		 		NewDocProblem  vovovo = new NewDocProblem();
		 		vovovo.setHosname("不存在");
		 		vovovo.setDeptname("不存在");
		 		vovovo.setDoctorname("不存在");
		 		
		 		return vovovo;	
		 		
		 		}
		 	
		
		
	}




	//TODO  wujiajun
	public List<NewDocProblem> queryNewALLDoctorList(NewDocProblemParaVO bean,List<String> twodeptids)
			throws Exception {
		
		
		
		// TODO wujiajun
		//进行查询  返回结果集
		
		
		 
			 //查询结果集			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.queryNewDoctorMessageDept);			 
			 StringBuffer datepara = new StringBuffer("");	
			 StringBuffer placepara = new StringBuffer("");	
			 
			 
				if (StringUtils.isNotEmpty(bean.getStarttime())) {
					datepara.append(" AND QUESMAIN_CreateTime > '" + bean.getStarttime()+ " 00:00:00'");
					}				
				if (StringUtils.isNotEmpty(bean.getEndtime())) {
					datepara.append(" AND QUESMAIN_CreateTime < '" + bean.getEndtime() + " 23:59:59'");
					}
				
				sql.addVar("@countone", datepara.toString());
				sql.addVar("@counttwo", datepara.toString());
				sql.addVar("@countthree", datepara.toString());
				
				
				//
				 StringBuffer minquery = new StringBuffer("");	
					if (StringUtils.isNotEmpty(bean.getStarttime())) {
						minquery.append(" and firstReplyTime > '" + bean.getStarttime()+ " 00:00:00'");
						}				
					if (StringUtils.isNotEmpty(bean.getEndtime())) {
						minquery.append(" AND firstReplyTime < '" + bean.getEndtime() + " 23:59:59'");
						}
				
					sql.addVar("@minqueryone", minquery.toString());
					sql.addVar("@minquerytwo", minquery.toString());
			
					//
				
				
				
				
				//如果一级科室有值 并且二级科室没值
				if(StringUtils.isNotEmpty(bean.getOnedept()) && !StringUtils.isNotEmpty(bean.getTwodept())){
					
					//把所有一级科室下面的二级科室找出来 id  加进去
					
					String  inpara="";
					for (int i = 0; i < twodeptids.size(); i++) {
						
						if(i==0){
							inpara=inpara+twodeptids.get(i);
							
						}else{
						
						inpara=inpara+","+twodeptids.get(i);
						}
						
					}
					placepara.append("  and xxxxx.standarddeptid IN   (  "+inpara+"  )  ");
				}else
				//二级科室查询
				
				if (StringUtils.isNotEmpty(bean.getTwodept())) {
					placepara.append(" AND  xxxxx.standarddeptid=" + bean.getTwodept());
					}				
			
				
				sql.addVar("@such", placepara.toString());
				

			 //sql.addVar("@pone", para.toString());//加入时间的限制
			 //sql.addVar("@ptwo", para.toString());//加入时间的限制
		   System.out.println(sql.getSqlString());
		
			 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
						sql, NewDocProblem.class);
		
		
		return lists;
		
		
	}




	@Override
	public List<NewDocProblem> queryReward(String startdate,
			String enddate) throws Exception {
		// TODO Auto-generated method stub
		

		// TODO wujiajun
		//进行查询  返回结果集
		
		
		 
			 //查询结果集			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.suchdoc300);			 
			 StringBuffer datepara = new StringBuffer("");	
			 
			 
				if (StringUtils.isNotEmpty(startdate)) {
					datepara.append(" AND firstReplyTime > '" + startdate+ "'");
					}				
				if (StringUtils.isNotEmpty(enddate)) {
					datepara.append(" AND firstReplyTime < '" +enddate+ "'");
					}
				
				sql.addVar("@such", datepara.toString());
				System.out.println(sql.getSqlString());
				
			 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
						sql, NewDocProblem.class);
		
		
		return lists;
		
	}




	@Override
	public List<NewDocProblem> queryOneDateChannel(String nowdate,int today)
			throws Exception {
		
		 Sql sql   = DB.me().createSql(MySqlNameEnum.suchOneDateChannel);			
		 StringBuffer timepara = new StringBuffer("");	
		 StringBuffer datepara = new StringBuffer("");	
		 
			
		    timepara.append("  '" + nowdate+"   日 '" );
		   sql.addVar("@onedateone", timepara.toString());
		   sql.addVar("@onedatetwo", timepara.toString());
		 
		 
			if (StringUtils.isNotEmpty(nowdate)) {
				datepara.append(" AND QUESMAIN_CreateTime > '" + nowdate+ " 00:00:00'");
				}				
			if (StringUtils.isNotEmpty(nowdate)) {
				datepara.append(" AND QUESMAIN_CreateTime < '" +nowdate+" 23:59:59'");
				}
			
			sql.addVar("@suchone", datepara.toString());
			sql.addVar("@suchtwo", datepara.toString());
			System.out.println(sql.getSqlString());
			
		 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
					sql, NewDocProblem.class);
	
	
		
		
		return lists;
	}




	@Override
	public List<NewDocProblem> queryListEveryDay(String start, String  end)
			throws Exception {
		

		 Sql sql   = DB.me().createSql(MySqlNameEnum.queryListEveryDay);			

		 
		   
		   StringBuffer usertime = new StringBuffer("");	
			if (StringUtils.isNotEmpty(start)) {
				usertime.append(" AND QUESMAIN_CreateTime > '" + start+ " 00:00:00'");
				}				
			if (StringUtils.isNotEmpty(start)) {
				usertime.append(" AND QUESMAIN_CreateTime < '" +end+" 23:59:59'");
				}
			
			sql.addVar("@usersuch1", usertime.toString());
			sql.addVar("@usersuch2", usertime.toString());
			sql.addVar("@usersuch3", usertime.toString());
			
			
			 StringBuffer doctime = new StringBuffer("");	
				if (StringUtils.isNotEmpty(start)) {
					doctime.append(" AND oo.firstReplyTime > '" + start+ " 00:00:00'");
					}				
				if (StringUtils.isNotEmpty(end)) {
					doctime.append(" AND oo.firstReplyTime < '" +end+" 23:59:59'");
					}
				
				sql.addVar("@docsuch1", doctime.toString());
				sql.addVar("@docsuch2", doctime.toString());
				sql.addVar("@docsuch3", doctime.toString());
				
			
			
			
			
			
			
			
			System.out.println(sql.getSqlString());
			
		 List<NewDocProblem> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
					sql, NewDocProblem.class);
	
	
		
		
		return lists;
	}



	
	
	/**
	 *根据医院id  查询到 咨询问题的数据 ！（按照点击量排序  并且 需要开放）
	 */
	@Override
	public List<HotQuesMainVo> queryQuesMainByHospitalID(String hos)
			throws Exception {
		// TODO Auto-generated method stub
		


		 Sql sql   = DB.me().createSql(MySqlNameEnum.queryQuesMainByHospitalID);			

		 
		   
		   StringBuffer suchString = new StringBuffer("");	
			if (StringUtils.isNotEmpty(hos)) {
				suchString.append(" AND hospitalid = " +hos  );
				}				

			
			sql.addVar("@such", suchString.toString());

			System.out.println(sql.getSqlString());
			
		     List<HotQuesMainVo> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, sql, HotQuesMainVo.class);
	
	
		
		
		return lists;
		
		
		
	}



	/**
	 *根据科室id  查询到 咨询问题的数据 ！（按照点击量排序  并且 需要开放）
	 */
	@Override
	public List<HotQuesMainVo> queryQuesMainByDepartmentID(String dept)
			throws Exception {
		// TODO Auto-generated method stub
		

		 Sql sql   = DB.me().createSql(MySqlNameEnum.queryQuesMainByDepartmentID);			

		 
		   
		   StringBuffer suchString = new StringBuffer("");	
			if (StringUtils.isNotEmpty(dept)) {
				suchString.append(" AND standarddeptid = " +dept  );
				}				

			
			sql.addVar("@such", suchString.toString());

			System.out.println(sql.getSqlString());
			
		     List<HotQuesMainVo> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, sql, HotQuesMainVo.class);
	
	
		
		
		return lists;
		
	}




	@Override
	public  List<QuesMainVo> queryPushDoctor(String start,String end) throws Exception {
		
		 Sql sql   = DB.me().createSql(MySqlNameEnum.queryPushDoctor);			

		 
		   
	   	 StringBuffer suchString = new StringBuffer("");	
			if (StringUtils.isNotEmpty(start)) {
				suchString.append(" AND OPERTIME > " +"'"+start+"'"  );
				}
			if (StringUtils.isNotEmpty(end)) {
				suchString.append(" AND OPERTIME < " +"'"+end +"'" );
				}
			

			
			sql.addVar("@such", suchString.toString());
			System.out.println(sql.getSqlString());
		    List<QuesMainVo>  vos= DB.me().queryForBeanList(MyDatabaseEnum.boss, sql,QuesMainVo.class);
			
	
	
		
		
		
		return vos;
	}




	@Override
	public String queryPushMsg(String start,String end) throws Exception {
		// TODO Auto-generated method stub
		
		 Sql sql   = DB.me().createSql(MySqlNameEnum.queryPushMsg);			

		 
		   
		   StringBuffer suchString = new StringBuffer("");	
			if (StringUtils.isNotEmpty(start)) {
				suchString.append(" AND OPERTIME > " +"'"+start+"'"  );
				}
			if (StringUtils.isNotEmpty(end)) {
				suchString.append(" AND OPERTIME < " +"'"+end +"'" );
				}
			

			
			sql.addVar("@time1", suchString.toString());
			sql.addVar("@time2", suchString.toString());
			sql.addVar("@time3", suchString.toString());

			System.out.println(sql.getSqlString());
			JSONObject json= DB.me().queryForJson(MyDatabaseEnum.boss, sql);
			
	
	
		
		
		
		return json.toString();
	}




	@Override
	public int suchDownDocMSGcount(String starttime, String endtime,
			String province, String city, String hos, String doctorname,int startrow,int endrow)
			throws Exception {

		Sql sql   = DB.me().createSql(MySqlNameEnum.suchDownDocMSGcount);			
		StringBuffer suchString = new StringBuffer("");	
		
		StringBuffer pageString = new StringBuffer("");	
		
		StringBuffer timeString = new StringBuffer("");	
		
		
		if (StringUtils.isNotEmpty(starttime)) {
			timeString.append("  AND QUESMAIN_CreateTime >="+"'"+starttime+"'");
			}
		if (StringUtils.isNotEmpty(endtime)) {
			timeString.append("  AND QUESMAIN_CreateTime <="+"'"+endtime+"'");
		}
		if (StringUtils.isNotEmpty(province)) {

		    suchString.append("  and provinceid="+province);
			
		}
		if (StringUtils.isNotEmpty(city)) {
			 suchString.append("  and cityid="+city+"   ");
			
		}
		if (StringUtils.isNotEmpty(hos)) {
			suchString.append("  and hospitalid="+hos);
			
		}
		
		if (StringUtils.isNotEmpty(doctorname)) {
			suchString.append("  and doctorname   like  "+"'%"+doctorname+"%'");
		}
		
		
		

		
		
		
		

		sql.addVar("@such", suchString.toString());
		sql.addVar("@page", pageString.toString());
		sql.addVar("@timesuch", timeString.toString());
		
		System.out.println(sql.getSqlString());
		int count= DB.me().queryForInteger(MyDatabaseEnum.YiHuNet2008, sql);
		
		return count;
	}




	@Override
	public List<SuchDownDocMSGVo> suchDownDocMSGlist(String starttime,
			String endtime, String province, String city, String hos,
			String doctorname,int startrow,int endrow) throws Exception {
	

		Sql sql   = DB.me().createSql(MySqlNameEnum.suchDownDocMSGlist);			
		StringBuffer suchString = new StringBuffer("");	
		
		StringBuffer pageString = new StringBuffer("");	
		
		StringBuffer timeString = new StringBuffer("");	
		
		
		if (StringUtils.isNotEmpty(starttime)) {
			timeString.append("  AND QUESMAIN_CreateTime >="+"'"+starttime+"'");
			}
		if (StringUtils.isNotEmpty(endtime)) {
			timeString.append("  AND QUESMAIN_CreateTime <="+"'"+endtime+"'");
		}
		if (StringUtils.isNotEmpty(province)) {

		    suchString.append("  and provinceid="+province);
			
		}
		if (StringUtils.isNotEmpty(city)) {
			 suchString.append("  and cityid="+city+"   ");
			
		}
		if (StringUtils.isNotEmpty(hos)) {
			suchString.append("  and hospitalid="+hos);
			
		}
		
		if (StringUtils.isNotEmpty(doctorname)) {
			suchString.append("  and doctorname   like  "+"'%"+doctorname+"%'");
		}
		
		
		
		
		if (startrow>=0&&endrow>=0) {
			pageString.append("  and rowid>="+startrow);
			pageString.append("    and rowid<="+endrow);
		}
		
		
		

		sql.addVar("@such", suchString.toString());
		sql.addVar("@page", pageString.toString());
		sql.addVar("@timesuch", timeString.toString());
		
		System.out.println(sql.getSqlString());
		List<SuchDownDocMSGVo> list= DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, sql, SuchDownDocMSGVo.class);
		
		return list;
	}

	




}
