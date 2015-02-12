package com.yihu.myt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONArray;
import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.db.SqlNameEnum;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.DebugLogHelper;
import com.yihu.account.api.AccMembershipcardBean;
import com.yihu.account.api.IAccountService;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.enums.BookEnrolSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.service.service.ICommonService;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.Consstatistic;
import com.yihu.myt.vo.DicEntity;
import com.yihu.myt.vo.MytBookenrolBean;
import com.yihu.myt.vo.MytBookenrolView;
import com.yihu.myt.vo.MytConsenrolBean;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytRevertBean;
import com.yihu.myt.vo.Page;

/**
 * 预约回复服务接口实现类
 * @author wangfeng
 * @company yihu.com 2012-8-6上午09:17:20
 */
public class BookenrolService implements IBookenrolService {

	
	public ServiceResult<List<MytBookenrolView>> getMytBookenrolResult(MytBookenrolView parMbBean, int regway, Page<MytBookenrolView> pg, int jsType) {
		
		ServiceResult<List<MytBookenrolView>> sr = new ServiceResult<List<MytBookenrolView>>(-1, null);
		try {
			int flag = 0;
			Sql sql = DB.me().createSql(MySqlNameEnum.getBookenrolResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMbBean.getOrgid()!=null && parMbBean.getOrgid()>0) {
				sbSql.append(" AND a.ORGID = " + parMbBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMbBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMbBean.getDoctorname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCardid())) {
				sbSql.append(" AND a.CARDID = '" + parMbBean.getCardid() + "' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCustname())) {
				sbSql.append(" AND a.CUSTNAME LIKE '%" + parMbBean.getCustname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getRevertresult())) {
				sbSql.append(" AND a.REVERTRESULT = " + parMbBean.getRevertresult());
			}
			if (parMbBean.getBegOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateOper.formatDate(parMbBean.getBegOpertime(),"yyyy-MM-dd") + "' ");
				flag = 1;
			}
			if (parMbBean.getEndOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateOper.formatDate(parMbBean.getEndOpertime(),"yyyy-MM-dd") + " 23:59:59' ");
				flag = 1;
			}
			if (flag == 0) {
				sbSql.append(" and a.opertime>getdate()-31");
			}
			if (parMbBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMbBean.getState() + " ");
			}
			if(regway==1) {
				sbSql.append(" and a.operatorid!=1000000");
			} else if(regway==2) {
				sbSql.append(" and a.operatorid=1000000");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytBookenrolView> lst = null;
			if (pg == null) {
				lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, MytBookenrolView.class);
			} else {
				lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, MytBookenrolView.class, jsType == MytConst.QUERY_JS.getValue() ? pg.getOffset() : pg.getPageNo(), pg.getPageSize());
			}
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无预约回复显示。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("查询预约回复异常。");
			return sr;
		}
	}
public ServiceResult<List<MytBookenrolView>> getMytBookenrolResultS(MytBookenrolView parMbBean, int regway, Page<MytBookenrolView> pg, int jsType) {
		
		ServiceResult<List<MytBookenrolView>> sr = new ServiceResult<List<MytBookenrolView>>(-1, null);
		try {
			int flag = 0;
			Sql sql = DB.me().createSql(MySqlNameEnum.getBookenrolResultS);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
		/*	if (parMbBean.getOrgid()>0) {
				sbSql.append(" AND a.ORGID = " + parMbBean.getOrgid());
			}*/
			/*if (StringUtils.isNotEmpty(parMbBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMbBean.getDoctorname() + "%' ");
			}*/
			
			if (StringUtils.isNotEmpty(parMbBean.getOperconfids())) {
				sbSql.append(" AND a.OPERCONFID in( " + parMbBean.getOperconfids() + ")");
			}
			
			if (StringUtils.isNotEmpty(parMbBean.getCardid())) {
				sbSql.append(" AND a.CARDID = '" + parMbBean.getCardid() + "' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCustname())) {
				sbSql.append(" AND a.CUSTNAME LIKE '%" + parMbBean.getCustname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getRevertresult())) {
				sbSql.append(" AND a.REVERTRESULT = " + parMbBean.getRevertresult());
			}
			if (parMbBean.getBegOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateOper.formatDate(parMbBean.getBegOpertime(),"yyyy-MM-dd") + "' ");
				flag = 1;
			}
			if (parMbBean.getEndOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateOper.formatDate(parMbBean.getEndOpertime(),"yyyy-MM-dd") + " 23:59:59' ");
				flag = 1;
			}
			if (flag == 0) {
				sbSql.append(" and a.opertime>getdate()-31");
			}
			if (parMbBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMbBean.getState() + " ");
			}
			if(regway==1) {
				sbSql.append(" and a.operatorid!=1000000");
			} else if(regway==2) {
				sbSql.append(" and a.operatorid=1000000");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			List<MytBookenrolView> lst = null;
			if (pg == null) {
				lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, MytBookenrolView.class);
			} else {
				lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, MytBookenrolView.class, jsType == MytConst.QUERY_JS.getValue() ? pg.getOffset() : pg.getPageNo(), pg.getPageSize());
			}
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("无预约回复显示。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("查询预约回复异常。");
			return sr;
		}
	}
	public ServiceResult<List<MytBookenrolBean>> getMytBookenrolResult(MytBookenrolBean parMbBean) {
		
		ServiceResult<List<MytBookenrolBean>> sr = new ServiceResult<List<MytBookenrolBean>>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getBookenrolResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMbBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID = " + parMbBean.getOperconfid());
			}
			if (parMbBean.getOrgid()!=null) {
				sbSql.append(" AND a.ORGID = " + parMbBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMbBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMbBean.getDoctorname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCardid())) {
				sbSql.append(" AND a.CARDID = '" + parMbBean.getCardid() + "' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCustname())) {
				sbSql.append(" AND a.CUSTNAME LIKE '%" + parMbBean.getCustname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getRevertresult())) {
				sbSql.append(" AND a.REVERTRESULT = " + parMbBean.getRevertresult());
			}
//			if (parMbBean.getBegOpertime() != null) {
//				sbSql.append(" AND a.OPERTIME >= '" + parMbBean.getBegOpertime() + "' ");
//			}
//			if (parMbBean.getEndOpertime() != null) {
//				sbSql.append(" AND a.OPERTIME <= '" + parMbBean.getEndOpertime() + "' ");
//			}
			if (parMbBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMbBean.getState() + " ");
			}
			sql.addVar("@p", sbSql.toString());
			List<MytBookenrolBean> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, MytBookenrolBean.class);
			if (lst == null || lst.isEmpty()) {
				sr.setMessage("预约回复不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(lst);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("查询预约回复异常。");
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytBookenrolCount(MytBookenrolView parMbBean, int regway) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			int flag = 0;
			Sql sql = DB.me().createSql(MySqlNameEnum.getBookenrolCount);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMbBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID = " + parMbBean.getOperconfid());
			}
			if (parMbBean.getOrgid()!=null) {
				sbSql.append(" AND a.ORGID = " + parMbBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMbBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMbBean.getDoctorname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCardid())) {
				sbSql.append(" AND a.CARDID = '" + parMbBean.getCardid() + "' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCustname())) {
				sbSql.append(" AND a.CUSTNAME LIKE '%" + parMbBean.getCustname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getRevertresult())) {
				sbSql.append(" AND a.REVERTRESULT = " + parMbBean.getRevertresult());
			}
			if (parMbBean.getBegOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateOper.formatDate(parMbBean.getBegOpertime(), "yyyy-MM-dd") + "' ");
				flag=1;
			}
			if (parMbBean.getEndOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateOper.formatDate(parMbBean.getEndOpertime(), "yyyy-MM-dd") + " 23:59:59' ");
				flag=1;
			}
			if (flag == 0) {
				sbSql.append(" and a.opertime>getdate()-31");
			}
			if (parMbBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMbBean.getState() + " ");
			}
			if(regway==1) {
				sbSql.append(" and a.operatorid!=1000000");
			} else if(regway==2) {
				sbSql.append(" and a.operatorid=1000000");
			}
			
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			
			if (count == null || count == 0) {
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setResult(0);
			return sr;
		}
	}
	public ServiceResult<Integer> getMytBookenrolCountS(MytBookenrolView parMbBean, int regway) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			int flag = 0;
			Sql sql = DB.me().createSql(MySqlNameEnum.getBookenrolCountS);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if (parMbBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID = " + parMbBean.getOperconfid());
			}
			/*if (parMbBean.getOrgid()!=null) {
				sbSql.append(" AND a.ORGID = " + parMbBean.getOrgid());
			}
			if (StringUtils.isNotEmpty(parMbBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMbBean.getDoctorname() + "%' ");
			}*/
			if (StringUtils.isNotEmpty(parMbBean.getOperconfids())) {
				sbSql.append(" AND a.OPERCONFID in( " + parMbBean.getOperconfids() + ")");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCardid())) {
				sbSql.append(" AND a.CARDID = '" + parMbBean.getCardid() + "' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getCustname())) {
				sbSql.append(" AND a.CUSTNAME LIKE '%" + parMbBean.getCustname() + "%' ");
			}
			if (StringUtils.isNotEmpty(parMbBean.getRevertresult())) {
				sbSql.append(" AND a.REVERTRESULT = " + parMbBean.getRevertresult());
			}
			if (parMbBean.getBegOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateOper.formatDate(parMbBean.getBegOpertime(), "yyyy-MM-dd") + "' ");
				flag=1;
			}
			if (parMbBean.getEndOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateOper.formatDate(parMbBean.getEndOpertime(), "yyyy-MM-dd") + " 23:59:59' ");
				flag=1;
			}
			if (flag == 0) {
				sbSql.append(" and a.opertime>getdate()-31");
			}
			if (parMbBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMbBean.getState() + " ");
			}
			if(regway==1) {
				sbSql.append(" and a.operatorid!=1000000");
			} else if(regway==2) {
				sbSql.append(" and a.operatorid=1000000");
			}
			
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			
			if (count == null || count == 0) {
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setResult(0);
			return sr;
		}
	}
	public ServiceResult<MytBookenrolBean> getMytBookenrolEntity(MytBookenrolBean parMbBean) {
		
		ServiceResult<MytBookenrolBean> sr = new ServiceResult<MytBookenrolBean>(-1, null);
		try {
			MytBookenrolBean rltMbBean = DB.me().queryForBean(
					MyDatabaseEnum.boss,
					DB.me().createSelect(parMbBean,
							MyTableNameEnum.MYT_BOOKENROL, "BOSS"),
					MytBookenrolBean.class);
			if (rltMbBean == null) {
				sr.setMessage("预约回复不存在。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(rltMbBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取预约回复异常。");
			return sr;
		}
	}
	
	public ServiceResult<JSONArray> getMytConsenrol(MytConsenrolBean parMcBean, Page<MytConsenrolBean> pg) {
		
		ServiceResult<JSONArray> sr = new ServiceResult<JSONArray>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getConsenrolResult);
			StringBuffer sbSql = new StringBuffer("");
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND b.doctorname like '%" + parMcBean.getDoctorname() + "%'");
			}
			//机构
			if(parMcBean.getOrgid()!=null){
				sbSql.append(" and b.orgid=").append(parMcBean.getOrgid());
			}
			
			//会员卡
			if(parMcBean.getCardid()!=null){
				sbSql.append(" AND a.cardid like '%" + parMcBean.getCardid() + "%'");
			}
			
			//custphone 会员电话
			
			if(parMcBean.getCustphone()!=null){
				sbSql.append(" AND a.custphone like '%" + parMcBean.getCustphone() + "%'");
			}
			//operatorname坐席
			
			if(parMcBean.getOperatorname()!=null){
				sbSql.append(" AND a.operatorname like '%" + parMcBean.getOperatorname() + "%'");
			}
			
			
			
			
			if (StringUtils.isNotEmpty(parMcBean.getTurnresult())) {
				sbSql.append(" AND a.TURNRESULT = " + parMcBean.getTurnresult());
			}
			if (StringUtils.isNotEmpty(parMcBean.getConstype())) {
				sbSql.append(" AND a.CONSTYPE = " + parMcBean.getConstype());
			}
			if (StringUtils.isNotEmpty(parMcBean.getMinDateTime())) {
				sbSql.append(" AND a.OPERTIME > '" + parMcBean.getMinDateTime() + " 00:00:00'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getMaxDateTime())) {
				sbSql.append(" AND a.OPERTIME < '" + parMcBean.getMaxDateTime() + " 23:59:59'");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			System.out.println(sql.getSqlString()+"2222222");
			
			List<MytConsenrolBean> lstMcBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytConsenrolBean.class, pg.getPageNo(), pg.getPageSize());
			
			
			
			
			JSONArray obj = DB.me().queryForJson(MyDatabaseEnum.boss,sql, pg.getPageNo(), pg.getPageSize()).getJSONArray("result");
			if (obj == null || obj.length()==0) {
				sr.setMessage("咨询登记记录不存在。");
				sr.setResult(new JSONArray());
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(obj);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(new JSONArray());
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytConsenrol(MytConsenrolBean parMcBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getConsenrolCount);
			StringBuffer sbSql = new StringBuffer("");
			if (StringUtils.isNotEmpty(parMcBean.getDoctorname())) {
				sbSql.append(" AND b.doctorname like '%" + parMcBean.getDoctorname() + "%'");
			}
			
			//机构
			if(parMcBean.getOrgid()!=null){
				sbSql.append(" and b.orgid=").append(parMcBean.getOrgid());
			}
			
			//会员卡
			if(parMcBean.getCardid()!=null){
				sbSql.append(" AND a.cardid like '%" + parMcBean.getCardid() + "%'");
			}
			
			//custphone 会员电话
			
			if(parMcBean.getCustphone()!=null){
				sbSql.append(" AND a.custphone like '%" + parMcBean.getCustphone() + "%'");
			}
			//operatorname坐席
			
			if(parMcBean.getOperatorname()!=null){
				sbSql.append(" AND a.operatorname like '%" + parMcBean.getOperatorname() + "%'");
			}
			
			
			if (StringUtils.isNotEmpty(parMcBean.getTurnresult())) {
				sbSql.append(" AND a.TURNRESULT = " + parMcBean.getTurnresult());
			}
			if (StringUtils.isNotEmpty(parMcBean.getConstype())) {
				sbSql.append(" AND a.CONSTYPE = " + parMcBean.getConstype());
			}
			if (StringUtils.isNotEmpty(parMcBean.getMinDateTime())) {
				sbSql.append(" AND a.OPERTIME > '" + parMcBean.getMinDateTime() + " 00:00:00'");
			}
			if (StringUtils.isNotEmpty(parMcBean.getMaxDateTime())) {
				sbSql.append(" AND a.OPERTIME < '" + parMcBean.getMaxDateTime() + " 23:59:59'");
			}
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			if (count == null || count == 0) {
				sr.setMessage("无咨询登记记录数显示。");
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(0);
			return sr;
		}
	}
	
	public ReturnValue addMytConsenrol(MytConsenrolBean bean) {
		JdbcConnection connBoss = null;
		try {
			// 先保留回复电话与回复时段
			MytBookenrolBean bookenrol = null;
			// 查询医生配置信息
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(bean.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconfig == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			AccMembershipcardBean card = Rpc.get(IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"), 8000).getMembershipcardObject(
					bean.getCardid());
			if (card != null) {
				bean.setCardtypesn(card.getCardtypesn());
				bean.setCardorgid(card.getOrgid());
			} else {
				return new ReturnValue(-1, "卡无效，登记失败");
			}

			bean.setDoctorid(operconfig.getDoctorid());
			bean.setOpertime(DateOper.getNowDateTime());
			bean.setState(MytConst.EFFECTIVE.getValue());

			// 设置指定医生
			if (!StringUtils.isEmpty(bean.getConstype()) && 
				String.valueOf(MytConst.EFFECTIVE.getValue()).equals(bean.getConstype())
				&& bean.getIsappoint() == null) {
				bean.setIsappoint(String.valueOf(MytConst.NO.getValue()));
			}

			// 转接项标志
			int isTurn = 0;
			if (!StringUtils.isEmpty(bean.getConstype()) && 
					bean.getConstype().equals(String.valueOf(MytConst.CONSTYPE_SPECIFIED.getValue()))) {
				// 指定咨询
				bean.setVouchdefeat(null);
				bean.setVouchresult(null);
				isTurn = 1;
			} else {
				// 推荐咨询
				if (!StringUtils.isEmpty(bean.getVouchresult()) && 
						bean.getVouchresult().equals(String.valueOf(MytConst.CONSTYPE_SPECIFIED.getValue()))) {
					// 推荐成功
					bean.setVouchdefeat(null);
					isTurn = 1;
				} else {
					// 推荐失败
					bean.setTurndefeat(null);
					bean.setTurnresult(null);
					bean.setIsrevert(null);
					bean.setNorevert(null);
				}
			}

			// 转接项
			if (isTurn == 1) {
				if (!StringUtils.isEmpty(bean.getTurnresult()) && 
						String.valueOf(MytConst.SUCCESS.getValue()).equals(bean.getTurnresult())) {
					// 转接成功
					bean.setTurndefeat(null);
					bean.setIsrevert(null);
					bean.setNorevert(null);
				} else {
					// 转接失败
					if (!StringUtils.isEmpty(bean.getIsrevert()) && 
							String.valueOf(MytConst.SUCCESS.getValue()).equals(bean.getIsrevert())) {
						String dateStr = bean.getDateweeks();
						// 回复
						bean.setNorevert(null);
						bookenrol = new MytBookenrolBean();
						bookenrol.setRevertphone(bean.getRevertphone());

						// 回复时段.2009-07-01(星期三)17:00--22:00
						String dateWeek = dateStr.substring(0, dateStr
								.indexOf("("));
						String times = dateStr
								.substring(dateStr.indexOf(")") + 1);
						String[] timeArr = times.split("--");

						bookenrol.setDateweek(dateWeek);
						bookenrol.setStarttime(timeArr[0]);
						bookenrol.setEndtime(timeArr[1]);

						// 预约的设置
						bookenrol.setDoctorid(operconfig.getDoctorid());
						bookenrol.setOpertime(DateOper.getNowDateTime());
						bookenrol.setState(MytConst.EFFECTIVE
								.getValue());
						bookenrol.setOperconfid(bean.getOperconfid());
						bookenrol.setCardid(bean.getCardid());
						bookenrol.setCustname(bean.getCustname());
						bookenrol.setCustphone(bean.getCustphone());
						bookenrol.setConstype(bean.getConstype());
						bookenrol.setRevertresult(String
								.valueOf(MytConst.UN_RESPONSE.getValue()));
						bookenrol.setOperatorid(bean.getOperatorid());
						bookenrol.setOperatorname(bean.getOperatorname());
						bookenrol.setRemark(bean.getRemark());
						bookenrol.setOrgid(bean.getOrgid());
						bookenrol.setCardtypesn(bean.getCardtypesn());
						bookenrol.setCardorgid(bean.getCardorgid());
						bookenrol.setProvinceId(bean.getProvinceId());
					}
				}
			}
			bean.setRevertphone(null);
			bean.setDateweeks(null);
			
			// 获取数据库事务及开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			DB.me().insert(connBoss, DB.me().createInsertSql(bean, MyTableNameEnum.MYT_CONSENROL, "BOSS"));
			if (bookenrol != null) {
				DB.me().insert(connBoss, DB.me().createInsertSql(bookenrol, MyTableNameEnum.MYT_BOOKENROL, "BOSS"));
			}

			connBoss.commitTransaction(true);
			return new ReturnValue(1, "添加咨询预约登记成功。");
		} catch (Exception e) {
			e.printStackTrace();
			if (connBoss != null)
				connBoss.rollbackAndclose();
			return new ReturnValue(-1, e.getMessage());
		} finally {
			try {
				if (connBoss != null)
					connBoss.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public ReturnValue updateMytConsenrol(String bookenrolid,
			String operconfid, String revertresult, String remark,
			int operatorid, String operatorname) {

		JdbcConnection connBoss = null;
		try {
			// 查询预约回复信息
			MytBookenrolBean proMbBean = new MytBookenrolBean();
			proMbBean.setBookenrolid(Integer.parseInt(bookenrolid));
			MytBookenrolBean rltMbBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createInsertSql(proMbBean, MyTableNameEnum.MYT_BOOKENROL, "BOSS"),
					MytBookenrolBean.class);
			if (rltMbBean == null) {
				return new ReturnValue(-1, "预约回复信息不存在。");
			}

			// 根据医生信息编号查询医生信息
			MytOperconfigBean proMoBean = new MytOperconfigBean();
			proMoBean.setOperconfid(Integer.parseInt(operconfid));
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(proMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}

			// 修改咨询预约回复（回访）
			MytRevertBean voMrBean = new MytRevertBean();
			voMrBean.setBookenrolid(bookenrolid);
			voMrBean.setCardid(rltMbBean.getCardid());
			voMrBean.setCustname(rltMbBean.getCustname());
			voMrBean.setDoctorid(rltMoBean.getDoctorid());
			voMrBean.setOperconfid(Integer.valueOf(operconfid));
			voMrBean.setRevertphone(rltMbBean.getCustphone());
			voMrBean.setRevertresult(revertresult);
			voMrBean.setRemark(remark);
			voMrBean.setState(String.valueOf(MytConst.EFFECTIVE.getValue()));
			voMrBean.setOperatorid(operatorid + "");
			voMrBean.setOperatorname(operatorname);
			voMrBean.setOpertime(DateOper.getNowDateTime());
			
			// 获取数据库事务及开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			DB.me().insert(connBoss, DB.me().createInsertSql(voMrBean, MyTableNameEnum.MYT_REVERT, "BOSS"));
			
			// 更新预约回复信息
			rltMbBean.setRevertresult(revertresult);
			rltMbBean.setBookenrolid(null);
			DB.me().update(connBoss, DB.me().createUpdateSql(rltMbBean, "BOSS", MyTableNameEnum.MYT_BOOKENROL, " bookenrolid = " + bookenrolid));
			
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "修改预约回复成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ServiceResult<MytDoctorViewBean> getMytDoctorView(int operconfId) {
		
		ServiceResult<MytDoctorViewBean> sr = new ServiceResult<MytDoctorViewBean>(-1, null);
		try {
			// 查询医生视图信息
			MytDoctorViewBean doctorView = new MytDoctorViewBean();
			doctorView.setOperconfid(operconfId);
			doctorView = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(doctorView, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
					MytDoctorViewBean.class);
			if (doctorView == null) {
				sr.setMessage("医生视图信息不存在。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(doctorView);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生视图信息异常。");
			return sr;
		}
	}
	
	public ServiceResult<List<MytRevertBean>> getMytRevert(String bookenrolId) {
		
		ServiceResult<List<MytRevertBean>> sr = new ServiceResult<List<MytRevertBean>>(-1, null);
		try {
			// 查询咨询回访
			MytRevertBean qryMrBean = new MytRevertBean();
			qryMrBean.setBookenrolid(bookenrolId);
			qryMrBean.setState(String.valueOf(MytConst.EFFECTIVE.getValue()));
			List<MytRevertBean> lstMrBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMrBean, MyTableNameEnum.MYT_REVERT, "BOSS"), 
					MytRevertBean.class);
			if (lstMrBean == null || lstMrBean.isEmpty()) {
				sr.setMessage("咨询回访信息不存在。");
			}
			sr.setCode(1);
			sr.setResult(lstMrBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生视图信息异常。");
			return sr;
		}
	}

	public ReturnValue updateMytConsenrol(int bookenrolid,
			int operconfid, String revertresult, String remark,
			OperatorInfo operator) {
		JdbcConnection connBoss = null;
		try {
			// 根据咨询流水编号查询咨询流水信息
			MytBookenrolView bookenrolView = new MytBookenrolView();
			bookenrolView.setBookenrolid(bookenrolid);
			bookenrolView = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(bookenrolView, MyTableNameEnum.MYT_BOOKENROL_VIEW, "BOSS"),
					MytBookenrolView.class);
			
			// 添加回复
			MytRevertBean proMrBean = new MytRevertBean();
			proMrBean.setBookenrolid(bookenrolid+"");
			proMrBean.setCardid(bookenrolView.getCardid());
			proMrBean.setCustname(bookenrolView.getCustname());
			proMrBean.setDoctorid(bookenrolView.getDoctorid());
			proMrBean.setOperconfid(bookenrolView.getOperconfid());
			proMrBean.setRevertphone(bookenrolView.getCustphone());
			proMrBean.setRevertresult(revertresult);
			proMrBean.setRemark(remark);
			proMrBean.setState(MytConst.EFFECTIVE.getValue() + "");
			proMrBean.setOperatorid(operator.getOperatorID() + "");
			proMrBean.setOperatorname(operator.getOperatorName());
			proMrBean.setOpertime(DateOper.getNowDateTime());
			
			// 获取连接并开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			DB.me().insert(connBoss, DB.me().createInsertSql(proMrBean, MyTableNameEnum.MYT_REVERT, "BOSS"));
			
			// 修改预约登记信息
			MytBookenrolBean bookenrol = new MytBookenrolBean();
			bookenrol.setRevertresult(revertresult);
			bookenrol.setBookenrolid(null);
			
			DB.me().update(connBoss, 
					DB.me().createUpdateSql(bookenrol, "BOSS", MyTableNameEnum.MYT_BOOKENROL, 
							" bookenrolid = " + bookenrolid));
			
			// 提交事务及返回信息
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "更新预约回复满意回复成功。");
		} catch (Exception e) {
			e.printStackTrace();
			if (connBoss != null)
				connBoss.rollbackAndclose();
			return new ReturnValue(-1, "更新预约回复满意回复失败。");
		} finally {
			try {
				if (connBoss != null)
					connBoss.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public String getGradReport(String operName,int prvid, int rows, int page,
			String sBTime, String sETime) {
		JSONObject result = new JSONObject();
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.gradeReport);
			StringBuffer sb = new StringBuffer("");
			if (StringUtils.isNotEmpty(operName) ){
				sb.append(" and operatorname =   '" +operName + "'");
			}
			if (prvid  >0){
				sb.append(" and mb.ProvinceId =  " +prvid );
			}
			if (StringUtils.isNotEmpty(sBTime) ){
				sb.append(" and opertime > '"+sBTime+"'");
			}
			if(  StringUtils.isNotEmpty(sETime)){
				sb.append(" and  opertime < '"+sETime+"'  ");
			}
			
			
			sql.addVar("@p", sb.toString());
			DebugLogHelper.info(sql.toString());
			//System.out.println(sql.toString());
			result = DB.me().queryForJson(
					MyDatabaseEnum.boss, sql, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				result.put("code", "-1");
				result.put("result", e.getLocalizedMessage());
			} catch (JSONException e1) {
				
				e1.printStackTrace();
			}
		}
		return result.toString();
	}
	public String getUserReturnList(String cardid, String start,String end,String type, Integer pageSize, Integer pageIndex) {
		JSONObject result = new JSONObject();
		try {
			Sql sql = DB.me().createSql(BookEnrolSqlNameEnum.getUserReturnList);
			StringBuffer sb = new StringBuffer("");
			if(!StringUtil.isEmpty(cardid)){
			
				
				
				sb.append(" and cardid in ( "   );
				String  [] types = cardid.split(",");
				for (int i = 0; i < types.length; i++) {
					if(i==types.length-1){
						sb.append("'"+types[i]+"'" +"   )" );
					}else{
						sb.append("'"+types[i]+"'" +" ," );
					}
				}
				
			}
			
			
			//new add
			
			if(!StringUtil.isEmpty(type)){
				
				
				sb.append(" and REVERTRESULT in ( "   );
				String  [] types = type.split(",");
				for (int i = 0; i < types.length; i++) {
					if(i==types.length-1){
						sb.append(types[i] +"   )" );
					}else{
						sb.append(types[i] +" ," );
					}
				}
				
			}
			
			
			
			
			if(!StringUtil.isEmpty(start)){
				
				sb.append(" and OPERTIME >  " +"'"+start+"'"   );
				
			}			
			
			if(!StringUtil.isEmpty(end)){
				
				sb.append(" and OPERTIME < "+"'"+end+"'"   );
				
			}
			
			
			
			
			if (pageSize != 0 || pageIndex != 0) {
				sql.addVar("@p", " and rowId >" + pageSize * pageIndex
						+ " and rowId <=" + (pageIndex + 1) * pageSize);
			} else {
				sql.addVar("@p", "");
			}
			sql.addVar("@c", sb.toString());
			DebugLogHelper.info(sql.toString());
			System.out.println(sql.toString());
			result = DB.me().queryForJson(
					MyDatabaseEnum.boss, sql);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				result.put("code", "-14444");
				result.put("result", e.getLocalizedMessage());
			} catch (JSONException e1) {
				
				e1.printStackTrace();
			}
		}
		return result.toString();
	}
	public Integer getUserReturnListCount(String cardid,String start,String end,String type) {
		Integer rt =0;
		try {
			Sql sql = DB.me().createSql(BookEnrolSqlNameEnum.getUserReturnListCount);
			StringBuffer sb = new StringBuffer("");
			if(!StringUtil.isEmpty(cardid)){

				
				

				sb.append(" and cardid in ( "   );
				String  [] types = cardid.split(",");
				for (int i = 0; i < types.length; i++) {
					if(i==types.length-1){
						sb.append("'"+types[i]+"'" +"   )" );
					}else{
						sb.append("'"+types[i]+"'" +" ," );
					}
				}
			}
			

			//new add
			
			if(!StringUtil.isEmpty(type)){
				
				
				sb.append(" and REVERTRESULT in ( "   );
				String  [] types = type.split(",");
				for (int i = 0; i < types.length; i++) {
					if(i==types.length-1){
						sb.append(types[i] +"   )" );
					}else{
						sb.append(types[i] +" ," );
					}
				}
				
			}
			
			
			
			
			if(!StringUtil.isEmpty(start)){
				
				sb.append(" and OPERTIME >  " +"'"+start+"'"   );
				
			}			
			
			if(!StringUtil.isEmpty(end)){
				
				sb.append(" and OPERTIME < "+"'"+end+"'"   );
				
			}
			
			
			sql.addVar("@c", sb.toString());
			//System.out.println(sql.toString());
			DebugLogHelper.info(sql.toString());
			rt  = DB.me().queryForInteger(
					MyDatabaseEnum.boss, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	
	/**
	 * @author WUJIAJUN
	 * @查询名医的信息！
	 */
	public static   ICommonService   commonService =  Ioc.get(ICommonService.class);
	@Override
	public ServiceResult<List<Consstatistic>> getAllConsstatistic(
		Consstatistic bean, Page<Consstatistic> pg) {		
		ServiceResult<List<Consstatistic>> sr = new ServiceResult<List<Consstatistic>>(-1, null);
		 try {
			 //查询结果集			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.finConsstatisticResult);			 
			 StringBuffer para = new StringBuffer("");			 			 
				if (StringUtils.isNotEmpty(bean.getStartdate())) {
					para.append(" AND b.REPLY_CreateTime > '" + bean.getStartdate()+ " 00:00:00'");
					}				
				if (StringUtils.isNotEmpty(bean.getEnddate())) {
					para.append(" AND b.REPLY_CreateTime < '" + bean.getEnddate() + " 23:59:59'");
					}
				
				
				
				StringBuffer para3 = new StringBuffer("");
					if (StringUtils.isNotEmpty(bean.getStartdate())) {
						para3.append(" AND STARDATE > '" + bean.getStartdate()+ " 00:00:00'");
						}
					
					if (StringUtils.isNotEmpty(bean.getEnddate())) {

						para3.append(" AND STARDATE < '" + bean.getEnddate() + " 23:59:59'");
						
						}
					
					
				//科室和医生的统计
			    StringBuffer para4 = new StringBuffer("");
			    if (StringUtils.isNotEmpty(bean.getStartdate())) {
			    	para4.append(" AND endDate > '" + bean.getStartdate()+ " 00:00:00'");
					}
				
				if (StringUtils.isNotEmpty(bean.getEnddate())) {

					para4.append(" AND endDate < '" + bean.getEnddate() + " 23:59:59'");
					
					}
			    
			    
					
				
				
			 sql.addVar("@pone", para.toString());//加入时间的限制
			 sql.addVar("@ptwo", para.toString());//加入时间的限制
			 sql.addVar("@pthree", para3.toString());//加入时间的限制
			 
			 sql.addVar("@pfour", para4.toString());
			 sql.addVar("@pfive", para4.toString());
			
			 /*
			 //加入字典
			 StringBuffer dicpara = new StringBuffer("");
			 //TODO   查找字典
				List<Map<String, Object>>   backlist= commonService.getBusinName("MYT_DOC");
				List<DicEntity>  docquery = new ArrayList<DicEntity>();		
				for (Map<String, Object> mapp : backlist) {
					DicEntity entity = new DicEntity();
					for (String key : mapp.keySet()) {
						if("businID".equals(key)){
							entity.setBusinID((String) mapp.get(key));
						}
		                if("businName".equals(key)){
		                	entity.setBusinName((String) mapp.get(key));
						}
					}
					docquery.add(entity);
				}
			//docquery<dicentity>  取值
				for (int i = 0; i < docquery.size(); i++) {
					if(i==0){
						//append
						dicpara.append("  AND  id IN ( "+docquery.get(i).getBusinID()+" ");
						
					}else if (i==docquery.size()-1){
						dicpara.append("   ,  "+docquery.get(i).getBusinID()+")");	
						
					}else{
						dicpara.append("   ,  "+docquery.get(i).getBusinID());	
						
					}
				}
			 sql.addVar("@dic", dicpara.toString());//加入 字典搜索出来的  医生
			 
			 */
			  System.out.println("查询的 sql   是：：：：：：：：：："+sql.getSqlString());
			 List<Consstatistic> lists = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, 
						sql, Consstatistic.class, pg.getPageNo(), pg.getPageSize());
			 sr.setResult(lists);
			 return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
		}
		return sr;
	}
	
	
	
	
	
	@Override
	public ServiceResult<Integer> getAllConsstatisticCount(Consstatistic bean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		
		
		 try {
			 //查询结果集
			 
			 Sql sql   = DB.me().createSql(MySqlNameEnum.finConsstatisticCount);
			 
			 
			 StringBuffer para = new StringBuffer("");
			 
			 

				if (StringUtils.isNotEmpty(bean.getStartdate())) {
					para.append(" AND b.REPLY_CreateTime > '" + bean.getStartdate()+ " 00:00:00'");
					}
				
				if (StringUtils.isNotEmpty(bean.getEnddate())) {

					para.append(" AND b.REPLY_CreateTime < '" + bean.getEnddate() + " 23:59:59'");
					
					}
				
				
				 StringBuffer para3 = new StringBuffer("");
					if (StringUtils.isNotEmpty(bean.getStartdate())) {
						para3.append(" AND STARDATE > '" + bean.getStartdate()+ " 00:00:00'");
						}
					
					if (StringUtils.isNotEmpty(bean.getEnddate())) {

						para3.append(" AND STARDATE < '" + bean.getEnddate() + " 23:59:59'");
						
						}
				
				 sql.addVar("@pone", para.toString());//加入时间的限制
				 sql.addVar("@ptwo", para.toString());//加入时间的限制
				 sql.addVar("@pthree", para3.toString());//加入时间的限制
				 
				 
				 /*
				 StringBuffer dicpara = new StringBuffer("");
				 //TODO   查找字典
					List<Map<String, Object>>   backlist= commonService.getBusinName("MYT_DOC");
					List<DicEntity>  docquery = new ArrayList<DicEntity>();		
					for (Map<String, Object> mapp : backlist) {
						DicEntity entity = new DicEntity();
						for (String key : mapp.keySet()) {
							if("businID".equals(key)){
								entity.setBusinID((String) mapp.get(key));
							}
			                if("businName".equals(key)){
			                	entity.setBusinName((String) mapp.get(key));
							}
						}
						docquery.add(entity);
					}
				//docquery<dicentity>  取值
					for (int i = 0; i < docquery.size(); i++) {
						if(i==0){
							//append
							dicpara.append("  AND  id IN ( "+docquery.get(i).getBusinID()+" ");
							
						}else if (i==docquery.size()-1){
							dicpara.append("   ,  "+docquery.get(i).getBusinID()+")");	
							
						}else{
							dicpara.append("   ,  "+docquery.get(i).getBusinID());	
							
						}
					}
				 sql.addVar("@dic", dicpara.toString());//加入 字典搜索出来的  医生
				 */
				 //System.out.println("COUNT IS：：：：：：：：：："+sql.getSqlString());
				 
		      	Integer count = DB.me().queryForInteger(MyDatabaseEnum.YiHuNet2008, sql);
				if (count == null ) {
					
					 System.out.println(" 查询page 数量 是：：：NULL");
					sr.setMessage("咨询登记记录不存在。");
					sr.setResult(0);
					return sr;
				}
			    
			 
			   sr.setCode(1);
			   sr.setMessage("成功");
			   sr.setResult(count);
			 
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
		}
		
		return sr;
	}

	
	
	
	
	
}
