package com.yihu.myt.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IPauseService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.MytPauseworkView;
import com.yihu.myt.vo.Page;

/**
 * 医生停诊操作服务实现类
 * @author wangfeng
 * @company yihu.com
 * 2012-8-10上午11:04:06
 */
public class PauseService implements IPauseService {

	public JSONObject getResults(MytPauseworkView pauseworkView, String startOpertime,
			String endOpertime, Page<MytPauseworkBean> pg) {

		JSONObject obj = new JSONObject();
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getPauseworkResult);
			StringBuffer sbSql = new StringBuffer(" WHERE state=1 ");
			if (pauseworkView.getOrgid()!=null && pauseworkView.getOrgid() > 0) {
				sbSql.append(" AND ORGID = " + pauseworkView.getOrgid());
			}
			if (StringUtils.isNotEmpty(pauseworkView.getHospofficeid())) {
				sbSql.append(" AND HOSPOFFICEID = " + pauseworkView.getHospofficeid());
			}
			if (StringUtils.isNotEmpty(pauseworkView.getDoctorname())) {
				sbSql.append(" AND DOCTORNAME LIKE '%" + pauseworkView.getDoctorname() + "%' ");
			}
			if (StringUtils.isNotEmpty(startOpertime)) {
				sbSql.append(" AND OPERTIME >= '" + startOpertime + "' ");
			}
			if (StringUtils.isNotEmpty(endOpertime)) {
				sbSql.append(" AND OPERTIME <= '" + endOpertime + " 23:59:59' ");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql, pg.getOffset(), pg.getPageSize());
			//System.out.println(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	public ServiceResult<List<MytPauseworkBean>>  getTimeResults(MytPauseworkBean pauseworkBean) {
		ServiceResult<List<MytPauseworkBean>> sr = new ServiceResult<List<MytPauseworkBean>>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getPauseworkStone);
			StringBuffer sbSql = new StringBuffer(" WHERE state=1  ");
			if (pauseworkBean.getOperconfid()!=null ) {
				sbSql.append(" AND OPERCONFID = " + pauseworkBean.getOperconfid());
			}
			sbSql.append(" AND ENDDATE >getdate() ");
			sql.addVar("@p", sbSql.toString());
			List<MytPauseworkBean> lstO = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytPauseworkBean.class);
				
			if (lstO == null || lstO.isEmpty()) {
				sr.setMessage("医生停诊不存在或已删除。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生停诊成功。");
			sr.setResult(lstO);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生停诊异常。");
			return sr;
		}
	}
	public ServiceResult<Integer> getCount(MytPauseworkView pauseworkView, String startOpertime,
			String endOpertime) {

		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getPauseworkCount);
			StringBuffer sbSql = new StringBuffer(" WHERE state=1 ");
			if (pauseworkView.getOrgid() != null && pauseworkView.getOrgid() > 0) {
				sbSql.append(" AND ORGID = " + pauseworkView.getOrgid());
			}
			if (StringUtils.isNotEmpty(pauseworkView.getHospofficeid())) {
				sbSql.append(" AND HOSPOFFICEID = " + pauseworkView.getHospofficeid());
			}
			if (StringUtils.isNotEmpty(pauseworkView.getDoctorname())) {
				sbSql.append(" AND DOCTORNAME LIKE '%" + pauseworkView.getDoctorname() + "%' ");
			}
			if (StringUtils.isNotEmpty(startOpertime)) {
				sbSql.append(" AND OPERTIME >= '" + startOpertime + "' ");
			}
			if (StringUtils.isNotEmpty(endOpertime)) {
				sbSql.append(" AND OPERTIME <= '" + endOpertime + " 23:59:59' ");
			}
			sql.addVar("@p", sbSql.toString());
			int count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);

			if (count == 0) {
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
	
	public ServiceResult<JSONObject> getEntity(MytPauseworkBean parMpBean) {
		
		ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			JSONObject json = DB.me().queryForJson(MyDatabaseEnum.boss, 
					DB.me().createSelect(parMpBean, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"));
			if (json == null) {
				sr.setMessage("医生停诊不存在或已删除。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(json);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生停诊异常。");
			return sr;
		}
	}
	
	public ServiceResult<MytPauseworkBean> getEntity(int operconfid, String nowTime, String nowDate) {
		
		ServiceResult<MytPauseworkBean> sr = new ServiceResult<MytPauseworkBean>(-1, null);
		try {
			Sql sqlMpBean = DB.me().createSql(MySqlNameEnum.getPausework);
			sqlMpBean.addParamValue(operconfid+"");
			sqlMpBean.addParamValue(MytConst.EFFECTIVE.getValue()+"");
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowTime);
			sqlMpBean.addParamValue(nowTime);
			MytPauseworkBean o = DB.me().queryForBean(MyDatabaseEnum.boss, 
					sqlMpBean, MytPauseworkBean.class);
			
			if (o == null) {
				sr.setMessage("医生停诊不存在或已删除。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生停诊成功。");
			sr.setResult(o);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生停诊异常。");
			return sr;
		}
	}
public  ServiceResult<List<MytPauseworkBean>> getEntitys(String nowTime, String nowDate) {
		
	 ServiceResult<List<MytPauseworkBean>> sr = new  ServiceResult<List<MytPauseworkBean>>(-1, null);
		try {
			Sql sqlMpBean = DB.me().createSql(MySqlNameEnum.getPauseworks);
			sqlMpBean.addParamValue(MytConst.EFFECTIVE.getValue()+"");
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowTime);
			sqlMpBean.addParamValue(nowTime);
			 List<MytPauseworkBean> lstO = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sqlMpBean, MytPauseworkBean.class);
			
			 if (lstO == null || lstO.isEmpty()) {
					sr.setMessage("医生停诊不存在或已删除。");
					sr.setResult(null);
					return sr;
				}
				sr.setCode(1);
				sr.setMessage("获取医生停诊成功。");
				sr.setResult(lstO);
				return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生停诊异常。");
			return sr;
		}
	}
	public ServiceResult<List<MytPauseworkBean>> getResult(int operconfid) {
		
		ServiceResult<List<MytPauseworkBean>> sr = new ServiceResult<List<MytPauseworkBean>>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getMaskPausework);
			sql.addParamValue(operconfid);
			List<MytPauseworkBean> lstO = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
				sql, MytPauseworkBean.class);
			
			if (lstO == null || lstO.isEmpty()) {
				sr.setMessage("医生停诊不存在或已删除。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生停诊成功。");
			sr.setResult(lstO);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生停诊异常。");
			return sr;
		}
	}
	
	public ReturnValue add(MytPauseworkBean pausework) {

		try {
			// 获取医生配置信息
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(pausework.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconfig == null) {
				return new ReturnValue(-1, "医生配置信息不存在，无法保存停诊信息。");
			}
			MytPauseworkBean pause = new MytPauseworkBean();
			pause.setOperconfid(pausework.getOperconfid());
			pause.setStartdate(pausework.getStartdate());
			pause.setEnddate(pausework.getEnddate());
			pause.setStarttime(pausework.getStarttime());
			pause.setEndtime(pausework.getEndtime());
			pause.setState(1);
			pause = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(pause, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"),
					MytPauseworkBean.class);
			if (pause != null)
				return new ReturnValue(-1, "停诊信息已存在，无需重复添加");

			// 保存停诊信息
			pausework.setDoctorid(operconfig.getDoctorid());
			int r = DB.me().insert(MyDatabaseEnum.boss,
					DB.me().createInsertSql(pausework, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"));
			if (r == 0) {
				return new ReturnValue(-1, "添加医生停诊操作错误。");
			}

			return new ReturnValue(1, "添加医生停诊成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "添加医生停诊异常。");
		}
	}
	public ReturnValue addPause(MytPauseworkBean pausework) {

		try {
			// 获取医生配置信息
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(pausework.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconfig == null) {
				return new ReturnValue(-1, "医生配置信息不存在，无法保存停诊信息。");
			}
			MytPauseworkBean pause = new MytPauseworkBean();
			pause.setOperconfid(pausework.getOperconfid());
			pause.setStartdate(pausework.getStartdate());
			pause.setEnddate(pausework.getEnddate());
			pause.setStarttime(pausework.getStarttime());
			pause.setEndtime(pausework.getEndtime());
			pause.setState(1);
			pause = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(pause, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"),
					MytPauseworkBean.class);
			if (pause != null)
				return new ReturnValue(-1, "停诊信息已存在，无需重复添加");

			// 保存停诊信息
			pausework.setDoctorid(operconfig.getDoctorid());
			int r = DB.me().insert(MyDatabaseEnum.boss,
					DB.me().createInsertSql(pausework, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"));
			if (r == 0) {
				return new ReturnValue(-1, "添加医生停诊操作错误。");
			}

			return new ReturnValue(r, "添加医生停诊成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "添加医生停诊异常。");
		}
	}
	public ReturnValue addPauseForWeb(MytPauseworkBean pausework) {

		try {
			// 获取医生配置信息
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(pausework.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconfig == null) {
				return new ReturnValue(-1, "医生配置信息不存在，无法保存停诊信息。");
			}
			MytPauseworkBean pause = new MytPauseworkBean();
			pause.setOperconfid(pausework.getOperconfid());
			pause.setStartdate(pausework.getStartdate());
			pause.setEnddate(pausework.getEnddate());
			pause.setStarttime(pausework.getStarttime());
			pause.setEndtime(pausework.getEndtime());
			pause.setState(1);
			pause = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(pause, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"),
					MytPauseworkBean.class);
			if (pause != null)
				return new ReturnValue(20001, "停诊信息已存在，无需重复添加");

			// 保存停诊信息
			pausework.setDoctorid(operconfig.getDoctorid());
			int r = DB.me().insert(MyDatabaseEnum.boss,
					DB.me().createInsertSql(pausework, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"));
			if (r == 0) {
				return new ReturnValue(-1, "添加医生停诊操作错误。");
			}

			return new ReturnValue(r, "添加医生停诊成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "添加医生停诊异常。");
		}
	}
	public ReturnValue delete(int pauseid, OperatorInfo operator) {

		try {
			// 获取医生停诊记录并更新状态为停诊
			MytPauseworkBean qryMpBean = new MytPauseworkBean();
			qryMpBean.setPauseid(pauseid);
			MytPauseworkBean rltMpBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMpBean, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"), 
					MytPauseworkBean.class);
			if (rltMpBean == null) {
				return new ReturnValue(-1, "医生停诊信息不存在。");
			}
			rltMpBean.setOperatorid(operator.getOperatorID() + "");
			rltMpBean.setOperatorname(operator.getOperatorName());
			rltMpBean.setOpertime(DateOper.getNowDateTime());
			rltMpBean.setState(MytConst.PAUSE.getValue());
			rltMpBean.setPauseid(null);
			int r = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(rltMpBean, "BOSS", MyTableNameEnum.MYT_PAUSEWORK, " pauseid = " + pauseid));
			if (r == 0) {
				return new ReturnValue(-1, "删除医生停诊操作错误。");
			}
			
			return new ReturnValue(1, "删除医生停诊成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "删除医生停诊异常。");
		}
	}

	public ReturnValue update(MytPauseworkBean pausework) {
		
		try {
			// 获取医生停诊记录并更新记录
			MytPauseworkBean bean = new MytPauseworkBean();
			bean.setPauseid(pausework.getPauseid());
			bean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(bean, MyTableNameEnum.MYT_PAUSEWORK, "BOSS"), 
					MytPauseworkBean.class);
			if (bean == null) {
				return new ReturnValue(-1, "医生停诊信息不存在。");
			}
			bean.setState(pausework.getState());
			bean.setStartdate(pausework.getStartdate());
			bean.setEnddate(pausework.getEnddate());
			bean.setStarttime(pausework.getStarttime());
			bean.setEndtime(pausework.getEndtime());
			bean.setRemark(pausework.getRemark());
			bean.setPauseid(null);
			int r = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(bean, "BOSS", MyTableNameEnum.MYT_PAUSEWORK, " pauseid = " + pausework.getPauseid()));
			if (r == 0) {
				return new ReturnValue(-1, "修改医生停诊操作错误。");
			}
			
			return new ReturnValue(1, "修改医生停诊成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "修改医生停诊异常。");
		}
	}
	public int updatePause(MytPauseworkBean vo) throws Exception{
		try {
			StringBuilder condition = new StringBuilder();
			if (vo.getPauseid() > 0) {
				condition.append("  Pauseid = " + vo.getPauseid());
				vo.setPauseid(null);
				int r = DB.me().update(
						MyDatabaseEnum.boss,
						DB.me().createUpdateSql(vo,
								MyTableNameEnum.MYT_PAUSEWORK,
								condition.toString()));
				return r;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	
		
	}
public String getEntityValue(int operconfid, String nowTime, String nowDate) throws Exception{
			Sql sqlMpBean = DB.me().createSql(MySqlNameEnum.getPauseworkValue);
			sqlMpBean.addParamValue(operconfid+"");
			sqlMpBean.addParamValue(MytConst.EFFECTIVE.getValue()+"");
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowDate);
			sqlMpBean.addParamValue(nowTime);
			sqlMpBean.addParamValue(nowTime);
			JSONObject rt  = DB.me().queryForJson(MyDatabaseEnum.boss, sqlMpBean);
			return rt.toString();
			}
}
