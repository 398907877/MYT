package com.yihu.myt.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IMIDoctorService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.Page;

/**
 * 推荐医生信息服务接口实现类
 * @author wangfeng
 * @company yihu.com
 * 2012-8-7下午05:23:20
 */
public class MIDoctorService implements IMIDoctorService {

	public ServiceResult<JSONObject> queryResult(MytMainintrodoctorBean parMmBean, Page<MytMainintrodoctorBean> pg) {
		
		ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer("");
			if (StringUtils.isNotEmpty(parMmBean.getQryOrgid())) {
				sbSql.append(" AND b.ORGID = " + parMmBean.getQryOrgid());
			}
			if (StringUtils.isNotEmpty(parMmBean.getQryDoctorname())) {
				sbSql.append(" AND b.DOCTORNAME LIKE '%" + parMmBean.getQryDoctorname() + "%'");
			}
			if (StringUtils.isNotEmpty(parMmBean.getQryHospofficeid())) {
				sbSql.append(" AND b.HOSPOFFICEID = '" + parMmBean.getQryHospofficeid()+"'");
			}
			if (parMmBean.getQryMinOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateUtil.dateFormat(parMmBean.getQryMinOpertime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMmBean.getQryMaxOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateUtil.dateFormat(parMmBean.getQryMaxOpertime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytMainintrodoctorResult);
			sql.addVar("@p", sbSql.toString());
			JSONObject obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql, pg.getPageNo(), pg.getPageSize());
			
			if (obj == null) {
				sr.setMessage("未查询到推荐医生信息。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(obj);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<Integer> queryCount(MytMainintrodoctorBean parMmBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer("");
			if (StringUtils.isNotEmpty(parMmBean.getQryOrgid())) {
				sbSql.append(" AND b.ORGID = " + parMmBean.getQryOrgid());
			}
			if (StringUtils.isNotEmpty(parMmBean.getQryDoctorname())) {
				sbSql.append(" AND b.DOCTORNAME LIKE '%" + parMmBean.getQryDoctorname() + "%'");
			}
			if (StringUtils.isNotEmpty(parMmBean.getQryHospofficeid())) {
				sbSql.append(" AND b.HOSPOFFICEID = '" + parMmBean.getQryHospofficeid()+"'");
			}
			if (parMmBean.getQryMinOpertime() != null) {
				sbSql.append(" AND a.OPERTIME >= '" + DateUtil.dateFormat(parMmBean.getQryMinOpertime(), DateUtil.YMD_FORMAT) + " 00:00:00'");
			}
			if (parMmBean.getQryMaxOpertime() != null) {
				sbSql.append(" AND a.OPERTIME <= '" + DateUtil.dateFormat(parMmBean.getQryMaxOpertime(), DateUtil.YMD_FORMAT) + " 23:59:59'");
			}
			System.out.println(sbSql.toString());
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytMainintrodoctorCount);
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			if (count == null || count <= 0) {
				sr.setMessage("未查询到推荐医生信息。");
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

	public ServiceResult<MytMainintrodoctorBean> queryEntity(String mainintrodoctorid) {
		
		ServiceResult<MytMainintrodoctorBean> sr = new ServiceResult<MytMainintrodoctorBean>(-1, null);
		try {
			MytMainintrodoctorBean qryMmBean = new MytMainintrodoctorBean();
			qryMmBean.setMainintrodoctorid(Integer.parseInt(mainintrodoctorid));
			MytMainintrodoctorBean o = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(qryMmBean, MyTableNameEnum.MYT_MAININTRODOCTOR, "BOSS"),
					MytMainintrodoctorBean.class);
			if (o == null) {
				sr.setMessage("推荐医生信息不存在。");
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
			sr.setResult(null);
			return sr;
		}
	}

	public ReturnValue insert(MytMainintrodoctorBean parMmBean) {
		
		try {
			// 根据推荐医生操作ID获取医生配置信息
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(parMmBean.getOperconfid());
			Sql sql = DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS");
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, sql, MytOperconfigBean.class);
			if (rltMoBean != null) {
				parMmBean.setDoctorid(rltMoBean.getDoctorid());
			}
			
			// 保存推荐医生信息
			sql = DB.me().createInsertSql(parMmBean, MyTableNameEnum.MYT_MAININTRODOCTOR, "BOSS");
			int r = DB.me().insert(MyDatabaseEnum.boss, sql);
			if (r < 0) {
				return new ReturnValue(-1, "添加推荐医生信息错误。");
			}
			
			return new ReturnValue(1, "添加推荐医生信息成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue update(MytMainintrodoctorBean parMmBean) {
		
		try {
			// 根据推荐医生ID获取推荐医生信息
			MytMainintrodoctorBean qryMmBean = new MytMainintrodoctorBean();
			qryMmBean.setMainintrodoctorid(parMmBean.getMainintrodoctorid());
			MytMainintrodoctorBean rltMmBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMmBean, MyTableNameEnum.MYT_MAININTRODOCTOR, "BOSS"), 
					MytMainintrodoctorBean.class);
			
			// 更新字段
			rltMmBean.setOperatorid(parMmBean.getOperatorid());
			rltMmBean.setOperatorname(parMmBean.getOperatorname());
			rltMmBean.setStartdate(parMmBean.getStartdate());
			rltMmBean.setEnddate(parMmBean.getEnddate());
			rltMmBean.setStarttime(parMmBean.getStarttime());
			rltMmBean.setEndtime(parMmBean.getEndtime());
			rltMmBean.setRemark(parMmBean.getRemark());	
			rltMmBean.setState(parMmBean.getState());
			rltMmBean.setMainintrodoctorid(null);
			DB.me().update(MyDatabaseEnum.boss, DB.me().createUpdateSql(
					rltMmBean, "BOSS", MyTableNameEnum.MYT_MAININTRODOCTOR, 
					" mainintrodoctorid = " + parMmBean.getMainintrodoctorid()));
			
			return new ReturnValue(1, "修改推荐医生信息成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue delete(int mainintrodoctorid) {

		try {
			// 获取推荐医生信息并将状态置为0表示删除
			MytMainintrodoctorBean qryMmBean = new MytMainintrodoctorBean();
			qryMmBean.setMainintrodoctorid(mainintrodoctorid);
			MytMainintrodoctorBean rltMmBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMmBean, MyTableNameEnum.MYT_MAININTRODOCTOR, "BOSS"), 
					MytMainintrodoctorBean.class);
			
			// 更新字段
			rltMmBean.setState(MytConst.NO.getValue());
			rltMmBean.setMainintrodoctorid(null);
			DB.me().update(MyDatabaseEnum.boss, DB.me().createUpdateSql(
					rltMmBean, "BOSS", MyTableNameEnum.MYT_MAININTRODOCTOR, 
					" mainintrodoctorid = " + mainintrodoctorid));
			
			return new ReturnValue(1, "删除推荐医生信息成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

}
