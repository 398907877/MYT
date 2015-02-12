package com.yihu.myt.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.common.json.JSONArray;
import com.common.unique.AppUnique;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.util.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.AccCardtypeBean;
import com.yihu.account.api.ChargeReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.enums.AccFeeConst;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.enums.QuesMainSqlNameEnum;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.BaseDoctorViewBean;
import com.yihu.myt.vo.DoctorAccPriceView;
import com.yihu.myt.vo.DoctorAccountBean;
import com.yihu.myt.vo.DoctorBillBean;
import com.yihu.myt.vo.DoctorServiceFeeConfigBean;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytConswaterSatisfactionBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.Page;
import com.yihu.myt.vo.QuesMainVo;

/**
 * 咨询计费流水服务接口实现类
 * @author wangfeng
 * @company yihu.com 2012-8-6上午11:59:54
 */
public class ConswaterService implements IConswaterService {

	private IAccountService getAccountService() {
		try {
			return Rpc.get(IAccountService.class, ConfigUtil.getInstance().getUrl("url.account"), 8000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ServiceResult<List<MytConswaterBean>> getMytConswaterResult(MytConswaterBean parMcBean, Page<MytConswaterBean> pg, int cardorgid) {

		ServiceResult<List<MytConswaterBean>> sr = new ServiceResult<List<MytConswaterBean>>(-1, null);
		try {
			// sql语句拼接并执行查询
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			
			if (parMcBean.getOrgid() != null) {
				sbSql.append(" AND a.ORGID = " + parMcBean.getOrgid());
			}
			
			//phone
			if (parMcBean.getCustphone() != null) {
				sbSql.append(" AND a.CUSTPHONE LIKE '%" + parMcBean.getCustphone()+ "%'" );
				System.out.println("come   phone");
			}
			//zuoxi
			if (parMcBean.getOperatorname()!= null) {
				sbSql.append(" AND a.OPERATORNAME LIKE '%" + parMcBean.getOperatorname()+ "%'" );
				System.out.println("come   opera");
			}
			
			if (StringUtils.isNotEmpty(parMcBean.getHospofficeid())) {
				sbSql.append(" AND a.HOSPOFFICEID = '" + parMcBean.getHospofficeid()+"'");
			}
			if(StringUtils.isNotEmpty(parMcBean.getDoctorlevel())){
				sbSql.append(" and a.DOCTORLEVEL='" + parMcBean.getDoctorlevel() + "'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMcBean.getDoctorname() + "%'");
			}
			if (parMcBean.getMindatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME >= '" + DateUtil.dateFormat(parMcBean.getMindatetime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMcBean.getMaxdatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME <= '" + DateUtil.dateFormat(parMcBean.getMaxdatetime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getCardid())) {
				sbSql.append(" AND a.CARDID LIKE '%" + parMcBean.getCardid() + "%'");
			}
			if (parMcBean.getScore() != null) {
				sbSql.append(" AND a.SCORE = " + parMcBean.getScore());
			}
			if (cardorgid > 0) {
				sbSql.append(" and a.cardtypesn in (select cardtypesn from acc_cardtype where orgid=" + cardorgid + ")");
			}
			sbSql.append(" AND a.STATE = " + parMcBean.getState());
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytConswaterBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConswaterBean.class, pg.getOffset(), pg.getPageSize());
			
			// 判断是否有记录并返回结果
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	
	public ServiceResult<List<MytConswaterBean>> getMytConswaterResultList(String pkconswaterids) {

		ServiceResult<List<MytConswaterBean>> sr = new ServiceResult<List<MytConswaterBean>>(-1, null);
		try {
			// sql语句拼接并执行查询
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (StringUtil.isNotEmpty(pkconswaterids)) {
				sbSql.append(" AND a.PKCONSWATERID in (" + pkconswaterids + ") ");
			}
			sql.addVar("@p", sbSql.toString());
			List<MytConswaterBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConswaterBean.class);
			// 判断是否有记录并返回结果
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	public ServiceResult<List<MytConswaterBean>> queryMytConsListForReport(MytConswaterBean vo,int start,int pageSize) {

		ServiceResult<List<MytConswaterBean>> sr = new ServiceResult<List<MytConswaterBean>>(-1, null);
		try {
			// sql语句拼接并执行查询
			//String yesterday = DateOper.addDate(DateOper.formatDate(new Date(), "yyyy-MM-dd"), -1, "yyyy-MM-dd");
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterReportList);
			//sql.addParamValue(yesterday);
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("    and a.opertime > '2013-07-29 00:00:00'"  );
			if (vo.getAccountsn()!=null) {
				sbSql.append(" AND accountSn = ?  "  );
				sql.addParamValue(vo.getAccountsn());
			}
			if(vo.getOperconfid()!=null){
				sbSql.append(" AND Operconfid = ?  "  );
				sql.addParamValue(vo.getOperconfid());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytConswaterBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConswaterBean.class,start,pageSize);
			// 判断是否有记录并返回结果
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<List<MytConswaterBean>> queryMytConsList(MytConswaterBean vo,int start,int pageSize) {

		ServiceResult<List<MytConswaterBean>> sr = new ServiceResult<List<MytConswaterBean>>(-1, null);
		try {
			// sql语句拼接并执行查询
			//String yesterday = DateOper.addDate(DateOper.formatDate(new Date(), "yyyy-MM-dd"), -1, "yyyy-MM-dd");
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterReportList);
			//sql.addParamValue(yesterday);
			StringBuffer sbSql = new StringBuffer();
			if (vo.getAccountsn()!=null) {
				sbSql.append(" AND accountSn = ?  "  );
				sql.addParamValue(vo.getAccountsn());
			}
			if(vo.getOperconfid()!=null){
				sbSql.append(" AND Operconfid = ?  "  );
				sql.addParamValue(vo.getOperconfid());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytConswaterBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConswaterBean.class,start,pageSize);
			// 判断是否有记录并返回结果
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	public int queryMytConsListCount(MytConswaterBean vo) {
		try {
			// sql语句拼接并执行查询
			//String yesterday = DateOper.addDate(DateOper.formatDate(new Date(), "yyyy-MM-dd"), -1, "yyyy-MM-dd");
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterReportListCount);
			//sql.addParamValue(yesterday);
			
			
			StringBuffer sbSql = new StringBuffer();
			if (vo.getAccountsn()!=null) {
				sbSql.append(" AND accountSn = ?  "  );
				sql.addParamValue(vo.getAccountsn());
			}
			if(vo.getOperconfid()!=null){
				sbSql.append(" AND Operconfid = ?  "  );
				sql.addParamValue(vo.getOperconfid());
			}
			sql.addVar("@p", sbSql.toString());
			int rt = DB.me().queryForInteger(MyDatabaseEnum.boss, 
					sql);
			return rt;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public int queryMytConsListCountForReport(MytConswaterBean vo) {
		try {
			// sql语句拼接并执行查询
			//String yesterday = DateOper.addDate(DateOper.formatDate(new Date(), "yyyy-MM-dd"), -1, "yyyy-MM-dd");
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterReportListCount);
			//sql.addParamValue(yesterday);
			
			
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("    and a.opertime > '2013-07-29 00:00:00'"  );
			if (vo.getAccountsn()!=null) {
				sbSql.append(" AND accountSn = ?  "  );
				sql.addParamValue(vo.getAccountsn());
			}
			if(vo.getOperconfid()!=null){
				sbSql.append(" AND Operconfid = ?  "  );
				sql.addParamValue(vo.getOperconfid());
			}
			sql.addVar("@p", sbSql.toString());
			int rt = DB.me().queryForInteger(MyDatabaseEnum.boss, 
					sql);
			return rt;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	public ServiceResult<List<MytConswaterBean>> getMytConswaterWrongResult(MytConswaterBean parMcBean, Page<MytConswaterBean> pg) {

		ServiceResult<List<MytConswaterBean>> sr = new ServiceResult<List<MytConswaterBean>>(-1, null);
		try {
			// sql语句拼接并执行查询
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMcBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID IN (" + parMcBean.getOperconfid() + ")");
			}
			if (StringUtils.isNotEmpty(parMcBean.getOperconfids())) {
				sbSql.append(" AND a.OPERCONFID IN (" + parMcBean.getOperconfids() + ")");
			}
			sbSql.append(" AND (a.CHARGEID = '-2' OR a.FLAG != 2) AND DATEDIFF(MINUTE, a.ENDDATETIME, GETDATE()) >= 15");
			if (parMcBean.getOrgid() != null) {
				sbSql.append(" AND a.ORGID = " + parMcBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMcBean.getHospofficeid())) {
				sbSql.append(" AND a.HOSPOFFICEID = '" + parMcBean.getHospofficeid()+"'");
			}
			if(StringUtils.isNotEmpty(parMcBean.getDoctorlevel())){
				sbSql.append(" and a.DOCTORLEVEL='" + parMcBean.getDoctorlevel() + "'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMcBean.getDoctorname() + "%'");
			}
			if (parMcBean.getMindatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME >= '" + DateUtil.dateFormat(parMcBean.getMindatetime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMcBean.getMaxdatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME <= '" + DateUtil.dateFormat(parMcBean.getMaxdatetime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getCardid())) {
				sbSql.append(" AND a.CARDID LIKE '%" + parMcBean.getCardid() + "%'");
			}
			if (parMcBean.getScore() != null) {
				sbSql.append(" AND a.SCORE = " + parMcBean.getScore());
			}
			
			sbSql.append(" AND a.STATE = " + parMcBean.getState());
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytConswaterBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConswaterBean.class, pg.getPageNo(), pg.getPageSize());
			
			// 判断是否有记录并返回结果
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytConswaterCount(MytConswaterBean parMcBean, int cardorgid) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			// sql语句拼接并执行查询
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterCount);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMcBean.getOrgid() != null) {
				sbSql.append(" AND a.ORGID = " + parMcBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMcBean.getHospofficeid())) {
				sbSql.append(" AND a.HOSPOFFICEID = '" + parMcBean.getHospofficeid()+"'");
			}
			if(StringUtils.isNotEmpty(parMcBean.getDoctorlevel())){
				sbSql.append(" and a.DOCTORLEVEL='" + parMcBean.getDoctorlevel() + "'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMcBean.getDoctorname() + "%'");
			}
			if (parMcBean.getMindatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME >= '" + DateUtil.dateFormat(parMcBean.getMindatetime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMcBean.getMaxdatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME <= '" + DateUtil.dateFormat(parMcBean.getMaxdatetime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getCardid())) {
				sbSql.append(" AND a.CARDID LIKE '%" + parMcBean.getCardid() + "%'");
			}
			if (parMcBean.getScore() != null) {
				sbSql.append(" AND a.SCORE = " + parMcBean.getScore());
			}
			if (cardorgid > 0) {
				sbSql.append(" and a.cardtypesn in (select cardtypesn from acc_cardtype where orgid=" + cardorgid + ")");
			}
			sbSql.append(" AND a.STATE = " + parMcBean.getState());
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			
			// 判断是否有记录并返回结果
			if (count == null || count == 0) {
				sr.setResult(0);
				return sr;	
			}
			sr.setCode(1);
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytConswaterWrongCount(MytConswaterBean parMcBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			// sql语句拼接并执行查询
			Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterCount);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMcBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID IN (" + parMcBean.getOperconfid() + ")");
			}
			sbSql.append(" AND (a.CHARGEID = '-2' OR a.FLAG != 2) AND DATEDIFF(MINUTE, a.ENDDATETIME, GETDATE()) >= 15");
			if (parMcBean.getOrgid() != null) {
				sbSql.append(" AND a.ORGID = " + parMcBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMcBean.getHospofficeid())) {
				sbSql.append(" AND a.HOSPOFFICEID = '" + parMcBean.getHospofficeid()+"'");
			}
			if(StringUtils.isNotEmpty(parMcBean.getDoctorlevel())){
				sbSql.append(" and a.DOCTORLEVEL='" + parMcBean.getDoctorlevel() + "'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMcBean.getDoctorname() + "%'");
			}
			if (parMcBean.getMindatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME >= '" + DateUtil.dateFormat(parMcBean.getMindatetime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMcBean.getMaxdatetime() != null) {
				sbSql.append(" AND a.STARTDATETIME <= '" + DateUtil.dateFormat(parMcBean.getMaxdatetime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getCardid())) {
				sbSql.append(" AND a.CARDID LIKE '%" + parMcBean.getCardid() + "%'");
			}
			if (parMcBean.getScore() != null) {
				sbSql.append(" AND a.SCORE = " + parMcBean.getScore());
			}
			
			sbSql.append(" AND a.STATE = " + parMcBean.getState());
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			
			// 判断是否有记录并返回结果
			if (count == null || count <= 0) {
				sr.setMessage("无咨询计费流水记录显示。");
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询计费流水成功。");
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(0);
			return sr;
		}
	}

	public ServiceResult<MytConswaterBean> getMytConswaterEntity(MytConswaterBean parMcBean) {
		
		ServiceResult<MytConswaterBean> sr = new ServiceResult<MytConswaterBean>(-1, null);
		try {
			Sql sql = DB.me().createSelect(parMcBean, MyTableNameEnum.MYT_CONSWATER, "BOSS");
			MytConswaterBean o = DB.me().queryForBean(MyDatabaseEnum.boss, sql, MytConswaterBean.class);
			if (o == null) {
				sr.setMessage("咨询流水不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(o);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	
	
	
	public ReturnValue addMytConswater(MytConswaterBean water) {

		JdbcConnection connBoss = null;
		try {
			// MytConswaterBean proMcBean = new MytConswaterBean();
			
			String startTimeStr = DateUtil.dateFormat(water
					.getStartdatetime(), DateUtil.YMDHMS_FORMAT);

			// 借用sevendoctorid传递正确的起始时间，格式为HH:mm:ss
			String beg = water.getSevendoctorid() == null ? " 00:00:00" : " " + water.getSevendoctorid();
			Date begTime = DateUtil.parse(startTimeStr.substring(0, 10) + beg, DateUtil.YMDHMS_FORMAT);
			// 借用tendoctorid传递正确的结束时间，格式为HH:mm:ss
			String end = water.getTendoctorid() == null ? " 00:00:00" : " " + water.getTendoctorid();
			Date endTime = DateUtil.parse(startTimeStr.substring(0, 10) + end, DateUtil.YMDHMS_FORMAT);

			// 咨询秒数,结束时间
			int consSec = (int) (endTime.getTime() - begTime.getTime()) / 1000;

			// 请求计费总次数，分钟数
			int orders = 1;
			if ((consSec % 60) == 0)
				orders = consSec / 60 + 1;
			else
				orders = consSec / 60 + 2;
			int consMin = orders - 1;
			water.setStartdatetime(DateUtil.parse(begTime.getTime()));
			water.setEnddatetime(DateUtil.parse(endTime.getTime()));
			water.setOrders(orders);
			water.setConsmin(consMin);

			// 医生业务基本信息
			MytDoctorViewBean doctorView = new MytDoctorViewBean();
			doctorView.setOperconfid(water.getOperconfid());
			doctorView = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(doctorView, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
					MytDoctorViewBean.class);
			if (doctorView != null) {
				water.setSevendoctorid(doctorView.getSevendoctorid());
				water.setTendoctorid(doctorView.getTendoctorid());
				water.setDoctorname(doctorView.getDoctorname());
				water.setDoctorid(doctorView.getDoctorid());
				water.setOrgid(doctorView.getOrgid());
				water.setCityid(doctorView.getCityid());
				water.setHospofficename(doctorView.getHospofficename());
				water.setHospofficeid(doctorView.getHospofficeid());
				water.setDoctorlevel(doctorView.getDoctorlevel() + "");
			}

			// 获取医生基础信息
			BaseDoctorViewBean baseDoctorView = new BaseDoctorViewBean();
			baseDoctorView.setBasedoctorid(doctorView.getDoctorid());
			Sql sqlBdv = DB.me().createSelect(baseDoctorView, MyTableNameEnum.BASE_DOCTOR_VIEW, "BOSS");
			baseDoctorView= DB.me().queryForBean(MyDatabaseEnum.boss, 
					sqlBdv, BaseDoctorViewBean.class);
			if (baseDoctorView != null) {
				water.setHospitalid(baseDoctorView.getHospitalid());
				water.setHosdeptid(baseDoctorView.getHosdeptid());
				water.setHospitalname(baseDoctorView.getHospitalname());
				water.setHosdeptname(baseDoctorView.getDeptname());
			}

			com.yihu.account.api.AccMembershipcardBean card = getAccountService().getMembershipcardObject(water.getCardid());
			if (card == null) {
				return new ReturnValue(-1, "会员卡不存在，请核对后再试");
			}
			if (!"3".equals(card.getState().replaceAll(" ", ""))) {
				return new ReturnValue(-1, "会员卡状态不正常。");
			}
			water.setCardtypesn(card.getCardtypesn());

			// 获取会员卡类型信息
			AccCardtypeBean cardtype = getAccountService().getCardTypeObj(card.getCardtypesn());
			if (cardtype != null) {
				water.setCardtypename(cardtype.getCardtypename());
			}

			// 获取资费标准
			int[] feeArr = { 0, 0, 0 };
			int feeType = 3;
			String feeNo = "01";//计费代码
			if (doctorView != null && doctorView.getDoctorlevel() == 7) { // 全科医生
				water.setIsqk(1);
				feeType = 50;
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(),
						doctorView.getOrgid());
				feeNo = "03";
			} else {
				water.setIsqk(0);
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(),
						doctorView.getOrgid());
				feeNo = "01";
			}

			// 计算需扣费用
			int charge = 0;
			if (feeArr != null) {
				charge = feeArr[1];
				if (consMin >= feeArr[0])
					charge += (consMin - feeArr[0]) * feeArr[2];
			}
			// 获取资源			
			Integer res =getAccountService().getResourceCount(water.getCardid(), "02", feeNo);
			int unit = 0;
			if (res!=null && res > 0) {
				unit = 1;
			}

			// 更新流水
			water.setFlag(2);
			if (feeArr == null) {
				water.setMytfeename("未能获取资费！");
			} else {
				water.setMytfeename("前" + feeArr[0] + "分钟" + feeArr[1]
						/ 100 + "元，后每分钟" + feeArr[2] / 100 + "元");
			}
			
			water.setCharge(charge * (-1));
			water.setChargeid("-2");
			water.setOpertime(DateOper.getNowDateTime());
			water.setAbleshort(String.valueOf(MytConst.YES.getValue())); // 欠费咨询
			water.setState(MytConst.EFFECTIVE.getValue()); // 有效
			int waterId = DB.me().insert(MyDatabaseEnum.boss, DB.me().createInsertSql(
					water, MyTableNameEnum.MYT_CONSWATER, "BOSS"));
			
			// 获取及开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			ChargeReturnBean ret = getAccountService().charge(water.getCardid(), card.getAccountsn(), card.getCardtypesn(), feeType, 
				waterId, water.getOperatorid(), water.getOperatorname(), water.getRemark(),
				charge*(-1)+"", unit+"", 0, true, null, null);
			//System.out.println(ret.getCode()+":"+ret.getMessage());
			// 扣费
			water.setChargeid(ret.getChargewatersn()+"");
			DB.me().update(connBoss, 
					DB.me().createUpdateSql(water, "BOSS", MyTableNameEnum.MYT_CONSWATER, 
							" pkconswaterid = " +waterId));
			
			// 提交事务
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "添加咨询计费流水成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ServiceResult<List<MytConswaterSatisfactionBean>> getMytConswaterSatisfactionResult(
			MytConswaterSatisfactionBean parMcsBean,
			Page<MytConswaterSatisfactionBean> pg) {

		ServiceResult<List<MytConswaterSatisfactionBean>> sr = new ServiceResult<List<MytConswaterSatisfactionBean>>(-1, null);
		try {
			// 查询记录集
//			JSONObject json = DB.me().queryForJson(MyDatabaseEnum.boss, 
//					DB.me().createSelect(parMcsBean, MyTableNameEnum.MYT_CONSWATER_SATISFACTION, "BOSS"), 
//					pg.getOffset(), pg.getPageSize());
			
			List<MytConswaterSatisfactionBean> lstMcsBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(parMcsBean, MyTableNameEnum.MYT_CONSWATER_SATISFACTION, "BOSS"), 
					MytConswaterSatisfactionBean.class, 
					pg.getOffset(), pg.getPageSize());
			
			if (lstMcsBean == null || lstMcsBean.isEmpty()) {
				sr.setMessage("无咨询流水满意度回访显示……");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询流水满意度回访记录集成功。");
			sr.setResult(lstMcsBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytConswaterSatisfactionCount(MytConswaterSatisfactionBean parMcsBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			// 查询记录数
			List<MytConswaterSatisfactionBean> lstMcsBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(parMcsBean, MyTableNameEnum.MYT_CONSWATER_SATISFACTION, "BOSS"), 
					MytConswaterSatisfactionBean.class);
			if (lstMcsBean == null || lstMcsBean.isEmpty()) {
				sr.setMessage("无咨询流水满意度回访记录数。");
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询咨询流水满意度回访记录数成功。");
			sr.setResult(lstMcsBean.size());
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(0);
			return sr;
		}
	}

	public ReturnValue addMytConsWaterSatisfaction(
			MytConswaterSatisfactionBean parMcsBean) {

		try {
			Sql sql = DB.me().createInsertSql(
					parMcsBean, MyTableNameEnum.MYT_CONSWATER_SATISFACTION, "BOSS");
			int r = DB.me().insert(MyDatabaseEnum.boss, sql);
			if (r <= 0)
				return new ReturnValue(1, "添加咨询计费流水满意度回访失败。");
			return new ReturnValue(1, "添加咨询计费流水满意度回访成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "添加咨询计费流水满意度回访异常。");
		}
	}
	
	public ServiceResult<Integer> getBillCount(MytConswaterBean bean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getBillCount);
			StringBuffer sbSql = new StringBuffer("");
			if(bean.getOrgid()>0) {
				sbSql.append(" and a.orgid=");
				sbSql.append(bean.getOrgid());
			}
			if(bean.getHospitalid()>0) {
				sbSql.append(" and a.hospitalid=");
				sbSql.append(bean.getHospitalid());
			}
			if(StringUtils.isNotEmpty(bean.getHospofficename())) {
				sbSql.append(" and a.hospofficename='");
				sbSql.append(bean.getHospofficename());
				sbSql.append("'");
			}
			if(StringUtils.isNotEmpty(bean.getDoctorname())) {
				sbSql.append(" and a.doctorName='");
				sbSql.append(bean.getDoctorname());
				sbSql.append("'");
			}
			if(bean.getStartdatetime()!=null) {
				sbSql.append(" and a.startDateTime>'");
				sbSql.append(DateOper.formatDate(bean.getStartdatetime(), "yyyy-MM-dd"));
				sbSql.append("'");
			}
			if(bean.getEnddatetime()!=null) {
				sbSql.append(" and a.enddatetime<'");
				sbSql.append(DateOper.formatDate(bean.getEnddatetime(), "yyyy-MM-dd"));
				sbSql.append(" 23:59:59'");
			}
			sql.addVar("@p", sbSql.toString());
			//System.out.println(sql.getSqlString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			
			if (count == null || count <= 0) {
				sr.setMessage("没有帐单记录");
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询成功。");
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(0);
			return sr;
		}
	}
	
	public ServiceResult<JSONObject> getBillResult(MytConswaterBean bean, Page page) {

		ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getBill);
			StringBuffer sbSql = new StringBuffer();
			if(bean.getOrgid()>0) {
				sbSql.append(" and a.orgid=");
				sbSql.append(bean.getOrgid());
			}
			if(bean.getHospitalid()>0) {
				sbSql.append(" and a.hospitalid=");
				sbSql.append(bean.getHospitalid());
			}
			if(StringUtils.isNotEmpty(bean.getHospofficename())) {
				sbSql.append(" and a.hospofficename='");
				sbSql.append(bean.getHospofficename());
				sbSql.append("'");
			}
			if(StringUtils.isNotEmpty(bean.getDoctorname())) {
				sbSql.append(" and a.doctorName='");
				sbSql.append(bean.getDoctorname());
				sbSql.append("'");
			}
			if(bean.getStartdatetime()!=null) {
				sbSql.append(" and a.startDateTime>'");
				sbSql.append(DateOper.formatDate(bean.getStartdatetime(), "yyyy-MM-dd"));
				sbSql.append("'");
			}
			if(bean.getEnddatetime()!=null) {
				sbSql.append(" and a.enddatetime<'");
				sbSql.append(DateOper.formatDate(bean.getEnddatetime(), "yyyy-MM-dd"));
				sbSql.append(" 23:59:59'");
			}
			sql.addVar("@p", sbSql.toString());
			JSONObject obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql, page.getPageNo(), page.getPageSize());
			
			// 判断是否有记录并返回结果
			if (obj == null) {
				sr.setMessage("无账单记录");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("查询成功");
			sr.setResult(obj);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}

	public JSONObject changeBilling(Integer resUid, Integer serviceId,
			Integer resId, Integer time) {
		JSONObject rt = new JSONObject();
		try {
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject dcJson = new JSONObject();
			dcJson.put("doctorUid", resUid);
			dcJson.put("serviceId", serviceId);
			ServiceResult<String> priceJson = doctorServiceApi
					.getDoctorPrice(dcJson.toString());
			if (priceJson.getCode() > 0) {
				JSONObject docPrice = new JSONObject(priceJson.getResult());
				if (docPrice.getInt("size") > 0) {
					JSONArray pris = docPrice.getJSONArray("serviceList");
					JSONObject price = pris.getJSONObject(0);
					if (price.getInt("typeId") == 1) {
						int beTime = price.getInt("minuteBefore");
						int bePrice = price.getInt("minuteBeforePrice");
						int afPrice = price.getInt("perMinutePrice");
						if (time > beTime) {
							int rtPrice = (time - beTime) * afPrice + bePrice;
							rt.put("price", rtPrice);
							rt.put("message", "前" + beTime + "分钟" + bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元" );
							rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						} else {
							rt.put("price", bePrice);
							rt.put("message", "前" + beTime + "分钟" + bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元" );
							rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						}
						return rt;
					} else if (price.getInt("typeId") == 2) {
						int total = price.getInt("minuteNum");
						int tolalPrice = price.getInt("price");
						int rtprice = (time % total > 0 ? (time / total + 1) : (time / total)) * tolalPrice;
						rt.put("price", rtprice);
						rt.put("message", "每" + total + "分钟" + tolalPrice / 100 + "元");
						rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						return rt;
					} else {
						rt.put("price", price.getInt("price"));
						rt.put("message", "医生本次咨询费用" + price.getInt("price") / 100 + "元");
						rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						return rt;
					}
				}
			}
			rt.put("price", 0);
			rt.put("message", "未找到相关付费数据。");
			return rt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
	
	public int saveDoctorPrice(Integer resUid,Integer serviceId,Integer resId,Integer serviceRecordId,Integer price ,int feeTemplateId){
		try {
			
			com.yihu.baseinfo.api.DoctorServiceApi
			doctorServiceApi=Rpc.get(DoctorServiceApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject dcBillJson = new JSONObject();
			dcBillJson.put("doctorUid", resUid);
			dcBillJson.put("serviceRecordId", serviceRecordId);
			dcBillJson.put("serviceId", serviceId);
			dcBillJson.put("feeTemplateId", feeTemplateId);
			dcBillJson.put("price", price);
			ServiceResult<String> rt =  doctorServiceApi.insertBill(dcBillJson.toString());
			return rt.getCode();
			/*
			
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorAccPrice);
			//根据医生ID获取配置
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1  ");
			if ( resUid != null && serviceId != null) {
				sbSql.append(" AND doctorUid  = '" + resUid + "' ");
				sbSql.append(" AND ServiceId  = '" + serviceId + "' ");
				sbSql.append(" AND resId  = '" + resId + "' ");
			}
			sql.addVar("@p", sbSql.toString());
			DoctorAccPriceView doctorB = DB.me().queryForBean(
					MyDatabaseEnum.BaseInfo, sql,
					DoctorAccPriceView.class);
			if(doctorB == null)
				return 0;
			DoctorBillBean dcBill=new DoctorBillBean();
			dcBill.setDoctorUid(doctorB.getDoctorUid());
			dcBill.setBillWaterId(AppUnique.nextInt("BillWaterId", MyDatabaseEnum.BaseInfo));
			dcBill.setDa_id(doctorB.getDa_id());
			dcBill.setPrice(price);
			dcBill.setBeforeBalance(doctorB.getBalance());
			dcBill.setServiceId(serviceId);
			dcBill.setServiceRecordId(serviceRecordId);
			dcBill.setFeeTemplateId(doctorB.getFeeTemplateId());
			dcBill.setInsertime(DateOper.getNowDateTime());
			int overPrice = doctorB.getBalance() + price;
			dcBill.setCurrentBalance(doctorB.getBalance() + price);
			dcBill.setState(1);			
			Sql ssSql = DB.me().createInsertSql(dcBill, MyTableNameEnum.DC_Bill, "dbo");
			DB.me().insert(MyDatabaseEnum.BaseInfo,ssSql);
			//doctorB.setBalance(overPrice);
			DB.me().createUpdateSql(water, "BOSS", MyTableNameEnum.MYT_CONSWATER, 
					" pkconswaterid = " +waterId)
			DoctorAccountBean dab = new DoctorAccountBean();
			dab.setBalance(overPrice);
			DB.me().update(MyDatabaseEnum.BaseInfo,
				DB.me().createUpdateSql(dab,"dbo", MyTableNameEnum.DC_DoctorAccount, " Da_id = " + doctorB.getDa_id()));*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public ReturnValue payMytConswater(MytConswaterBean water) {

		JdbcConnection connBoss = null;
		try {
			// MytConswaterBean proMcBean = new MytConswaterBean();
			
			String startTimeStr = DateUtil.dateFormat(water
					.getStartdatetime(), DateUtil.YMDHMS_FORMAT);

			// 借用sevendoctorid传递正确的起始时间，格式为HH:mm:ss
			String beg = water.getSevendoctorid() == null ? " 00:00:00" : " " + water.getSevendoctorid();
			Date begTime = DateUtil.parse(startTimeStr.substring(0, 10) + beg, DateUtil.YMDHMS_FORMAT);
			// 借用tendoctorid传递正确的结束时间，格式为HH:mm:ss
			String end = water.getTendoctorid() == null ? " 00:00:00" : " " + water.getTendoctorid();
			Date endTime = DateUtil.parse(startTimeStr.substring(0, 10) + end, DateUtil.YMDHMS_FORMAT);
			if (beg.compareTo(end) >= 0) {
				return new ReturnValue(-1, "起始时间不能大于或等于结束时间");
			}

			// 咨询秒数,结束时间
			int consSec = (int) (endTime.getTime() - begTime.getTime()) / 1000;

			// 请求计费总次数，分钟数
			int orders = 1;
			if ((consSec % 60) == 0)
				orders = consSec / 60 + 1;
			else
				orders = consSec / 60 + 2;
			int consMin = orders - 1;
			water.setStartdatetime(DateUtil.parse(begTime.getTime()));
			water.setEnddatetime(DateUtil.parse(endTime.getTime()));
			water.setOrders(orders);
			water.setConsmin(consMin);

			// 医生业务基本信息
			MytDoctorViewBean doctorView = new MytDoctorViewBean();
			doctorView.setOperconfid(water.getOperconfid());
			doctorView = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(doctorView, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
					MytDoctorViewBean.class);
			if (doctorView != null) {
				water.setSevendoctorid(doctorView.getSevendoctorid());
				water.setTendoctorid(doctorView.getTendoctorid());
				water.setDoctorname(doctorView.getDoctorname());
				water.setDoctorid(doctorView.getDoctorid());
				water.setOrgid(doctorView.getOrgid());
				water.setCityid(doctorView.getCityid());
				water.setHospofficename(doctorView.getHospofficename());
				water.setHospofficeid(doctorView.getHospofficeid());
				water.setDoctorlevel(doctorView.getDoctorlevel() + "");
			}
		
			// 获取医生基础信息
			BaseDoctorViewBean baseDoctorView = new BaseDoctorViewBean();
			baseDoctorView.setBasedoctorid(doctorView.getDoctorid());
			Sql sqlBdv = DB.me().createSelect(baseDoctorView, MyTableNameEnum.BASE_DOCTOR_VIEW, "BOSS");
			baseDoctorView= DB.me().queryForBean(MyDatabaseEnum.boss, 
					sqlBdv, BaseDoctorViewBean.class);
			if (baseDoctorView != null) {
				water.setHospitalid(baseDoctorView.getHospitalid());
				water.setHosdeptid(baseDoctorView.getHosdeptid());
				water.setHospitalname(baseDoctorView.getHospitalname());
				water.setHosdeptname(baseDoctorView.getDeptname());
			}

			com.yihu.account.api.AccMembershipcardBean card = getAccountService().getMembershipcardObject(water.getCardid());
			if (card == null) {
				return new ReturnValue(-1, "会员卡不存在，请核对后再试");
			}
			if (!"3".equals(card.getState().replaceAll(" ", ""))) {
				return new ReturnValue(-1, "会员卡状态不正常。");
			}
			water.setCardtypesn(card.getCardtypesn());

			// 获取会员卡类型信息
			AccCardtypeBean cardtype = getAccountService().getCardTypeObj(card.getCardtypesn());
			if (cardtype != null) {
				water.setCardtypename(cardtype.getCardtypename());
			}

			
			
			/*// 获取资费标准
			int[] feeArr = { 0, 0, 0 };
			int feeType = 3;
			String feeNo = "01";//计费代码
			if (doctorView != null && doctorView.getDoctorlevel() == 7) { // 全科医生
				water.setIsqk(1);
				feeType = 50;
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(),
						doctorView.getOrgid());
				feeNo = "03";
			} else {
				water.setIsqk(0);
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(),
						doctorView.getOrgid());
				feeNo = "01";
			}

			// 计算需扣费用
			int charge = 0;
			if (feeArr != null) {
				charge = feeArr[1];
				if (consMin >= feeArr[0])
					charge += (consMin - feeArr[0]) * feeArr[2];
			}*/
			int feeType = 3;
			String feeNo = "01";//计费代码
			if (doctorView != null && doctorView.getDoctorlevel() == 7) { // 全科医生
				water.setIsqk(1);
				feeType = 50;
				feeNo = "03";
			} else {
				water.setIsqk(0);
				feeNo = "01";
			}
			// 获取资源			
			Integer res =getAccountService().getResourceCount(water.getCardid(), "02", feeNo);
			int unit = 0;
			if (res!=null && res > 0) {
				unit = 1;
			}

			
			//新接口获取医生资费
			JSONObject feeArr = this.changeBilling(doctorView.getOperconfid(), 1,1, consMin);

			// 更新流水
			water.setFlag(2);
			if (feeArr == null) {
				water.setMytfeename("未能获取资费！");
			} else {
				
				/*water.setMytfeename("前" + feeArr[0] + "分钟" + feeArr[1]
						/ 100 + "元，后每分钟" + feeArr[2] / 100 + "元");*/
				water.setMytfeename((String) feeArr.get("message"));
			}
			int charge =(Integer) feeArr.get("price");
			water.setCharge(charge* (-1));
			water.setChargeid("-2");
			water.setOpertime(DateOper.getNowDateTime());
			water.setAbleshort(String.valueOf(MytConst.YES.getValue())); // 欠费咨询
			water.setState(MytConst.EFFECTIVE.getValue()); // 有效
			int waterId = DB.me().insert(MyDatabaseEnum.boss, DB.me().createInsertSql(
					water, MyTableNameEnum.MYT_CONSWATER, "BOSS"));
			
			// 获取及开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			ChargeReturnBean ret = getAccountService().charge(water.getCardid(), card.getAccountsn(), card.getCardtypesn(), feeType, 
				waterId, water.getOperatorid(), water.getOperatorname(), water.getRemark(),
				charge*(-1)+"", unit+"", 0, true, null, null);
			//System.out.println(ret.getCode()+":"+ret.getMessage());
			// 扣费
			water.setChargeid(ret.getChargewatersn()+"");
			water.setFeeTemplateId(feeArr.getInt("feeTemplateId"));
			if (ret.getCode() == 1) {
				DB.me().update(
					connBoss,
					DB.me()
						.createUpdateSql(water, "BOSS", MyTableNameEnum.MYT_CONSWATER, " pkconswaterid = " + waterId));

				int sdprt = this.saveDoctorPrice(doctorView.getOperconfid(),1,1, waterId, charge*6/10,feeArr.getInt("feeTemplateId"));
				/*if(sdprt!=1){
					connBoss.rollback();
					return new ReturnValue(-1, "医生付费不成功。");
				}*/
				// 提交事务
				connBoss.commitTransaction(true);
				return new ReturnValue(1, "添加咨询计费流水成功。");
			} else {
				return new ReturnValue(-1, "流水已生成，但计费失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (connBoss != null)
					connBoss.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ReturnValue(-1, e.getMessage());
		} finally {
			try {
				if (connBoss != null)
					connBoss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public JSONObject getMytWaters(MytConswaterBean bean) throws Exception {
		Sql sql = DB.me().createSql(MySqlNameEnum.getConswaterResult);
		StringBuilder condition = new StringBuilder();
		condition.append(" where 1=1");
		if (StringUtil.isNotEmpty(bean.getPkwaterIDs())) { 
			condition.append(" and pkconswaterid in (");
			String[] ids = bean.getPkwaterIDs().split(",");
			for(int i = 0 ; i <ids.length ;i++){
				condition.append(" ? ,");
				sql.addParamValue(ids[i]);
			}
			condition.deleteCharAt(condition.length()-1);
			condition.append("   )");
		}
		sql.addVar("@p", condition.toString());
		sql.addVar("@page", "");
		JSONObject list = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		return list;

	}
	
	
	public JSONObject getMytWaterByCheck(MytConswaterBean bean,Integer type,Integer chagneType,Integer quesNo ,Integer pageSize, Integer pageIndex) throws Exception {
		Sql sql = DB.me().createSql(MySqlNameEnum.getCheckWaters);
		StringBuilder condition = new StringBuilder();
		
		if(type!= null && bean.getConsmin() != null){
			if(type==1 ){
				condition.append("  and  CONSMIN  >   " + bean.getConsmin());
			}else if(type == 2){
				condition.append("  and  CONSMIN <   " + bean.getConsmin());
			}
		}
		if( bean.getScore() != null){
			condition.append("   and  SCORE =   " + bean.getScore() );
		}
		if(quesNo!=null){
			condition.append("  and  EXISTS ( SELECT OPERCONFID ");
			condition.append("           FROM   ( SELECT    COUNT(OPERCONFID) opCont , ");
			condition.append("                              OPERCONFID ");
			condition.append("                   FROM      BOSS.MYT_CONSWATER ");
			condition.append("                   GROUP BY  OPERCONFID ");
			condition.append("                 ) AS a1 ");
			condition.append("         WHERE  a1.opCont <   " + quesNo);
			condition.append("                AND a1.OPERCONFID = c1.OPERCONFID ) ");
		}
		if(chagneType!=null){
			condition.append("  and constype =   " + chagneType);
		}
		if(StringUtil.isNotEmpty(bean.getDoctorname())){
			condition.append(" and DOCTORNAME = ?");
			sql.addParamValue(bean.getDoctorname());
		}
		if(bean.getHospitalid()!=null){
			condition.append(" and HosID = " + bean.getHospitalid());
		}
		if(bean.getProvinceId()!=null){
			condition.append(" and ProvinceId = " + bean.getProvinceId());
		} 	
		sql.addVar("@condition", condition.toString());
		if (pageSize != 0 || pageIndex != 0) {
			sql.addVar("@page", " and rowId >" + pageSize * pageIndex
					+ " and rowId <=" + (pageIndex + 1) * pageSize);
		} else {
			sql.addVar("@page", "");
		}
		//System.out.print(sql.toString());
		JSONObject list = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		return list;

	}
	public int getMytWaterCountByCheck(MytConswaterBean bean,Integer type,Integer chagneType,Integer quesNo ) throws Exception {
		Sql sql = DB.me().createSql(MySqlNameEnum.getCheckWatersCount);
		StringBuilder condition = new StringBuilder();
		if(type!=null&& bean.getConsmin() != null){
			if(type==1 ){
				condition.append("  and  CONSMIN  >   " + bean.getConsmin());
			}else if(type == 2){
				condition.append("  and  CONSMIN <   " + bean.getConsmin());
			}
		}
		if( bean.getScore() != null){
			condition.append("   and  SCORE =   " + bean.getScore() );
		}
		if(quesNo!=null){
			condition.append("  and  EXISTS ( SELECT OPERCONFID ");
			condition.append("           FROM   ( SELECT    COUNT(OPERCONFID) opCont , ");
			condition.append("                              OPERCONFID ");
			condition.append("                   FROM      BOSS.MYT_CONSWATER ");
			condition.append("                   GROUP BY  OPERCONFID ");
			condition.append("                 ) AS a1 ");
			condition.append("         WHERE  a1.opCont <  " + quesNo);
			condition.append("                AND a1.OPERCONFID = c1.OPERCONFID ) ");
		}
		if(chagneType!=null){
				condition.append("  and constype =   " + chagneType);
		}
		
		if(StringUtil.isNotEmpty(bean.getDoctorname())){
			condition.append(" and DOCTORNAME = ?");
			sql.addParamValue(bean.getDoctorname());
		}
		if(bean.getHospitalid()!=null){
			condition.append(" and HosID = " + bean.getHospitalid());
		}
		if(bean.getProvinceId()!=null){
			condition.append(" and ProvinceId = " + bean.getProvinceId());
		}
		sql.addVar("@condition", condition.toString());
		int list = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
		return list;

	}
}
