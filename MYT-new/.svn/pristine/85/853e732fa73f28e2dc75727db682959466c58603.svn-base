package com.yihu.myt.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.DebugLogHelper;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.vo.MytArraphoneViewBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytArraworkPhoneBean;
import com.yihu.myt.vo.MytArraworkView;
import com.yihu.myt.vo.MytArraworkWBean;
import com.yihu.myt.vo.MytConsphoneBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.Page;

/**
 * 医生排班服务接口实现
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:27:55
 */
public class ArraworkService implements IArraworkService {

	public ReturnValue addMytArrawork(MytArraworkBean arrawork) {
		
		JdbcConnection connBoss = null;
		try {
			// 获取Boss数据库事务并开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			// 根据Operconfid查询医生配置信息
			MytOperconfigBean operconf = new MytOperconfigBean();
			operconf.setOperconfid(arrawork.getOperconfid());
			operconf.setState(1);
			operconf = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconf, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconf == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			String[] weeks = arrawork.getWeekid().split(",");
			String[] phones = arrawork.getConsphone().split(",");
			// 根据排班星期多次保存排班记录
			for (String weekid : weeks) {
				// 保存医生排班信息
				MytArraworkBean addMaBean = new MytArraworkBean();
//				addMaBean.setWeekid(weekid);
//				addMaBean.setOperconfid(arrawork.getOperconfid());
//				addMaBean.setDoctorid(operconf.getDoctorid());
//				addMaBean.setState(String.valueOf(MytConst.EFFECTIVE.getValue()));
//				addMaBean.setOperatorid(arrawork.getOperatorid());
//				addMaBean.setOperatorname(arrawork.getOperatorname());
//				addMaBean.setOpertime(DateOper.getNowDateTime());
				BeanUtils.copyProperties(addMaBean, arrawork);
				addMaBean.setArraworkid(null);
				addMaBean.setConsphone("");
				addMaBean.setWeekid(weekid);
				addMaBean.setYihustatus("1");
				addMaBean.setDoctorid(operconf.getDoctorid());
				int arraworkid = DB.me().insert(connBoss, 
						DB.me().createInsertSql(addMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
				this.saveArraworkPhone(phones, operconf.getDoctorid().toString(), arraworkid);
				// 保存排班MytArraworkWBean
				MytArraworkWBean addMawBean = new MytArraworkWBean();
				BeanUtils.copyProperties(addMawBean, addMaBean);
				addMawBean.setArraworkid(arraworkid);
				int r = DB.me().insert(MyDatabaseEnum.bossdata, DB.me().createInsertSql(addMawBean, MyTableNameEnum.MYT_ARRAWORK_W, "dbo"));
				if (r <= 0) {
					connBoss.rollbackAndclose();
					return new ReturnValue(1, "添加排班错误。");
				}
			}
			
			// 提交事务并返回消息
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "添加排班成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "添加排班错误。");
		} finally {
			try {
				if (connBoss != null)
					connBoss.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
public String addMytArraworkForApp(MytArraworkBean arrawork) {
		
		JdbcConnection connBoss = null;
		try {
			// 获取Boss数据库事务并开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			// 根据Operconfid查询医生配置信息
			MytOperconfigBean operconf = new MytOperconfigBean();
			operconf.setOperconfid(arrawork.getOperconfid());
			operconf.setState(1);
			operconf = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(operconf, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"),
					MytOperconfigBean.class);
			if (operconf == null) {
				return ApiUtil.getJsonRt(-14444, "医生配置信息不存在。");
			}
			String[] weeks = arrawork.getWeekid().split(",");
			String[] phones = arrawork.getConsphone().split(",");
			String ids = "";
			// 根据排班星期多次保存排班记录
			for (String weekid : weeks) {
				// 保存医生排班信息
				MytArraworkBean addMaBean = new MytArraworkBean();
//				addMaBean.setWeekid(weekid);
//				addMaBean.setOperconfid(arrawork.getOperconfid());
//				addMaBean.setDoctorid(operconf.getDoctorid());
//				addMaBean.setState(String.valueOf(MytConst.EFFECTIVE.getValue()));
//				addMaBean.setOperatorid(arrawork.getOperatorid());
//				addMaBean.setOperatorname(arrawork.getOperatorname());
//				addMaBean.setOpertime(DateOper.getNowDateTime());
				BeanUtils.copyProperties(addMaBean, arrawork);
				addMaBean.setArraworkid(null);
				addMaBean.setConsphone("");
				addMaBean.setWeekid(weekid);
				addMaBean.setYihustatus("1");
				addMaBean.setDoctorid(operconf.getDoctorid());
				int arraworkid = DB.me().insert(connBoss, 
						DB.me().createInsertSql(addMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
				this.saveArraworkPhone(phones, operconf.getDoctorid().toString(), arraworkid);
				// 保存排班MytArraworkWBean
				MytArraworkWBean addMawBean = new MytArraworkWBean();
				BeanUtils.copyProperties(addMawBean, addMaBean);
				addMawBean.setArraworkid(arraworkid);
				int r = DB.me().insert(MyDatabaseEnum.bossdata, DB.me().createInsertSql(addMawBean, MyTableNameEnum.MYT_ARRAWORK_W, "dbo"));
				if (r <= 0) {
					connBoss.rollbackAndclose();
					return ApiUtil.getJsonRt(-14444, "添加排班错误。");
					
				}
				ids = ids + arraworkid+ ",";
			}
			// 提交事务并返回消息
			connBoss.commitTransaction(true);
			net.sf.json.JSONObject rt = net.sf.json.JSONObject.fromObject(ApiUtil.getJsonRt(10000, "添加排班成功。"));
			JSONObject arraWorkIDs = new JSONObject();
			arraWorkIDs.put("arraWorkIDs", ids);
			rt.put("Result", arraWorkIDs.toString());
			return rt.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiUtil.getJsonRt(-14444, "添加排班错误。");
		} finally {
			try {
				if (connBoss != null)
					connBoss.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	/**
	 * 保存排班关联电话
	 * @param phones
	 * @param doctorId
	 * @param arraworkid
	 * @param connBoss
	 * @throws Exception
	 */
	private void saveArraworkPhone(String[] phones, String doctorId, int arraworkid) throws Exception {
		for (String phone : phones) {
			// 获取咨询电话
			MytConsphoneBean phoneBean = new MytConsphoneBean();
			phoneBean.setConsphone(phone);
			phoneBean.setState(MytConst.EFFECTIVE.getValue());
			phoneBean.setDoctorid(Integer.valueOf(doctorId));
			phoneBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(phoneBean, MyTableNameEnum.MYT_CONSPHONE, "dbo"),
					MytConsphoneBean.class);
			if (phoneBean != null) {
				// 保存排班电话
				MytArraworkPhoneBean proMapBean = new MytArraworkPhoneBean();
				proMapBean.setDoctorid(Integer.valueOf(doctorId));
				proMapBean.setState(MytConst.EFFECTIVE.getValue());
				proMapBean.setArraworkid(arraworkid);
				proMapBean.setConsphoneid(phoneBean.getConsphoneid());
				DB.me().insert(
						MyDatabaseEnum.boss,
						DB.me().createInsertSql(proMapBean, MyTableNameEnum.MYT_ARRAWORKPHONE,
								"dbo"));
			}
		}
	}

	public ReturnValue updateMytArrawork(MytArraworkBean arrawork) {

		try {
			int arraworkId = arrawork.getArraworkid();
			// 重新设置排班字段属性值并执行更新
			arrawork.setArraworkid(null);
			System.out.println(
					DB.me().createUpdateSql(arrawork, "BOSS", MyTableNameEnum.MYT_ARRAWORK,
							" arraworkid = " + arraworkId).toString());
			int r = DB.me().update(
					MyDatabaseEnum.boss,
					DB.me().createUpdateSql(arrawork, "BOSS", MyTableNameEnum.MYT_ARRAWORK,
							" arraworkid = " + arraworkId));
			if (r == 0) {
				return new ReturnValue(-1, "排班修改失败。");
			}
			if (arrawork.getState()==1) {
				Sql sql = DB.me().createSql(MySqlNameEnum.invalidArraworkPhone);
				sql.addParamValue(arraworkId);
				DB.me().update(MyDatabaseEnum.boss, sql);
				this.saveArraworkPhone(arrawork.getConsphone().split(","), arrawork.getDoctorid().toString(),
						arraworkId);
			}

			return new ReturnValue(1, "修改排班成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "修改排班出现异常。");
		}
	}

	public ReturnValue deleteMytArrawork(int arraworkid, OperatorInfo operator) {
		
		JdbcConnection connBoss = null;
		try {
			// 根据arraworkid查询医生排班记录
			MytArraworkBean qryMaBean = new MytArraworkBean();
			qryMaBean.setArraworkid(arraworkid);
			MytArraworkBean rltMaBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(qryMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"),
					MytArraworkBean.class);
			if (rltMaBean == null) {
				return new ReturnValue(-1, "医生排班信息不存在。");
			}
			
			// 设置排班属性值更新记录
			rltMaBean.setArraworkid(null);
			rltMaBean.setOperatorid(operator.getOperatorID() + "");
			rltMaBean.setOperatorname(operator.getOperatorName());
			rltMaBean.setOpertime(DateOper.getNowDateTime());
			rltMaBean.setState(MytConst.PAUSE.getValue());
			
			// 获取boss库连接及开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			
			// 更新医生排班
			int arraworkId = DB.me().update(connBoss,
					DB.me().createUpdateSql(rltMaBean, "BOSS", MyTableNameEnum.MYT_ARRAWORK,
							" arraworkid = " + arraworkid));
			if (arraworkId <= 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "删除医生排班错误。");
			}

			// 复制医生排班信息
			MytArraworkWBean addMawBean = new MytArraworkWBean();
			BeanUtils.copyProperties(addMawBean, rltMaBean);
			addMawBean.setArraworkid(arraworkId);
			int r = DB.me().insert(MyDatabaseEnum.bossdata, DB.me().createInsertSql(addMawBean, MyTableNameEnum.MYT_ARRAWORK_W, "dbo"));
			if (r <= 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "删除医生排班错误。");
			}
			
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "删除医生排班成功。");
		} catch (Exception e) {
			e.printStackTrace();
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

	public ServiceResult<List<MytArraworkBean>> getMytArraworkList(MytArraworkBean parMaBean) {
		
		ServiceResult<List<MytArraworkBean>> sr = new ServiceResult<List<MytArraworkBean>>(-1, null);
		try {
			Sql sql = DB.me().createSelect(parMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS");
			List<MytArraworkBean> lstMaBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytArraworkBean.class);
			if (lstMaBean == null || lstMaBean.isEmpty()) {
				sr.setMessage("无排班信息显示。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(lstMaBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	
	public ServiceResult<List<MytArraworkBean>> getMytArraworkListBySqlVar(MytArraworkBean parMaBean, Page<MytArraworkBean> pg) {
		
		ServiceResult<List<MytArraworkBean>> sr = new ServiceResult<List<MytArraworkBean>>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getArraworkResult);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			if (parMaBean.getState()!=null) {
				sbSql.append(" AND a.STATE = " + parMaBean.getState());
			}
			if (StringUtils.isNotEmpty(parMaBean.getDoctorname())) {
				sbSql.append(" AND a.DOCTORNAME LIKE '%" + parMaBean.getDoctorname() + "%'");
			}
			if (StringUtils.isNotEmpty(parMaBean.getHospofficeid())) {
				sbSql.append(" AND a.HOSPOFFICEID = " + parMaBean.getHospofficeid());
			}
			if (parMaBean.getOrgid()!=null) {
				sbSql.append(" AND a.ORGID = " + parMaBean.getOrgid());
			}
			if (parMaBean.getOperconfid()!=null) {
				sbSql.append(" AND a.OPERCONFID IN (" + parMaBean.getOperconfid() + ")");
			} else {
				sbSql.append(" AND a.OPERCONFID = 9999999");
			}
			if (pg != null && pg.getOrderProp() != null) {
				sbSql.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sbSql.toString());
			
			List<MytArraworkBean> lstMaBean = DB.me().queryForBeanList(
					MyDatabaseEnum.boss, sql, MytArraworkBean.class,
					pg.getPageNo(), pg.getPageSize());
			if (lstMaBean == null || lstMaBean.isEmpty()) {
				sr.setMessage("无排班信息显示。");
				sr.setResult(null);
				return sr;
			}
			
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(lstMaBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	public ServiceResult<List<MytArraworkView>> getArraworkViewList(MytArraworkView arrView,
			Page<MytArraworkView> pg){
		ServiceResult<List<MytArraworkView>> sr = new ServiceResult<List<MytArraworkView>>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getArraworkViewResult);
			StringBuffer sb = new StringBuffer("");
			if (StringUtils.isNotEmpty(arrView.getDoctorname())) {
				sb.append(" AND DOCTORNAME LIKE '%");
				sb.append(arrView.getDoctorname());
				sb.append("%'");
			}
			if (StringUtils.isNotEmpty(arrView.getHospofficeid())) {
				sb.append(" AND HOSPOFFICEID = '");
				sb.append(arrView.getHospofficeid());
				sb.append("'");
			}
			if (arrView.getOrgid()!=null && arrView.getOrgid()>0) {
				sb.append(" AND ORGID = ");
				sb.append(arrView.getOrgid());
			}
			if (arrView.getDoctorlevel()!=null && arrView.getDoctorlevel()>-1) {
				sb.append(" AND doctorlevel = ");
				sb.append(arrView.getDoctorlevel());
			}
			if (pg != null && pg.getOrderProp() != null) {
				sb.append(" ORDER BY " + pg.getOrderProp());
			}
			sql.addVar("@p", sb.toString());
			DebugLogHelper.info(sql.toString());
			
			List<MytArraworkView> bean = DB.me().queryForBeanList(
					MyDatabaseEnum.boss, sql, MytArraworkView.class,
					pg.getPageNo()*pg.getPageSize(), pg.getPageSize());
			if (bean == null || bean.isEmpty()) {
				sr.setMessage("无排班信息显示。");
				sr.setResult(null);
				return sr;
			}
			
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(bean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	
	public ServiceResult<Integer> getArraworkViewCount(MytArraworkView arrView) {
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getArraworkViewCount);
			StringBuffer sb = new StringBuffer("");
			if (StringUtils.isNotEmpty(arrView.getDoctorname())) {
				sb.append(" AND DOCTORNAME LIKE '%");
				sb.append(arrView.getDoctorname());
				sb.append("%'");
			}
			if (StringUtils.isNotEmpty(arrView.getHospofficeid())) {
				sb.append(" AND HOSPOFFICEID = '");
				sb.append(arrView.getHospofficeid());
				sb.append("'");
			}
			if (arrView.getOrgid()!=null && arrView.getOrgid()>0) {
				sb.append(" AND ORGID = ");
				sb.append(arrView.getOrgid());
			}
			if (arrView.getDoctorlevel()!=null && arrView.getDoctorlevel()>-1) {
				sb.append(" AND doctorlevel = ");
				sb.append(arrView.getDoctorlevel());
			}
			sql.addVar("@p", sb.toString());
			DebugLogHelper.info(sql.toString());

			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			if (count == null || count == 0) {
				sr.setMessage("无排班信息显示。");
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
			return sr;
		}
	}
	
	public ServiceResult<Integer> getMytArraworkCount(MytArraworkBean parMaBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss,
					DB.me().createSelect(parMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
			if (count == null || count == 0) {
				sr.setMessage("无排班信息显示。");
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("成功。");
			sr.setResult(count);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setResult(0);
			return sr;
		}
	}
	
	public ServiceResult<JSONObject> getMytArrawork(int arraworkid, int operconfid) {
		
		ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			MytArraworkBean qryMaBean = new MytArraworkBean();
			qryMaBean.setArraworkid(arraworkid);
			qryMaBean.setState(MytConst.EFFECTIVE.getValue());
			JSONObject jsonMaBean = DB.me().queryForJson(MyDatabaseEnum.boss,
					DB.me().createSelect(qryMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
			if (jsonMaBean == null) {
				sr.setMessage("医生排班信息不存在。");
				return sr;
			}
			ServiceResult<List<MytArraphoneViewBean>> res = this.getMytArraphoneView(arraworkid + "");
			String consphone = "";
			if (res.getCode() > 0) {
				for (MytArraphoneViewBean mav : res.getResult()) {
					if ("".equals(consphone)) {
						consphone = mav.getConsphone();
					} else {
						consphone += "," + mav.getConsphone();
					}
				}
			}
			JSONObject consphoneObj = new JSONObject();
			consphoneObj.put("consphone", consphone);
			//当前排班医生电话
			jsonMaBean.getJSONArray("result").put(jsonMaBean.getJSONArray("result").length(), consphoneObj);
			IOperconfidService operconfService = Ioc.get(IOperconfidService.class);
			JSONObject obj = operconfService.getConsphoneByDoctor(operconfid);
			//所有医生电话
			jsonMaBean.getJSONArray("result").put(jsonMaBean.getJSONArray("result").length(), obj);
			System.out.println(jsonMaBean);
			sr.setCode(1);
			sr.setResult(jsonMaBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
public  ServiceResult<JSONObject> getArraworkListAndOnline( int operconfid) {
		
	ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			Sql sqlMaBean = DB.me().createSql(MySqlNameEnum.getArraworkListAndOnline);
			Calendar mfNow = Calendar.getInstance();
			mfNow.setTime(new Date());
			int dw = mfNow.get(Calendar.DAY_OF_WEEK);
			if(dw-1<0){
				dw = 0;
			}else{
				dw=dw-1;
			}
			sqlMaBean.addParamValue(dw);
			sqlMaBean.addParamValue(operconfid);
			JSONObject json = DB.me().queryForJson(MyDatabaseEnum.boss, 
					sqlMaBean);
			if(json==null){
				sr.setMessage("医生排班信息不存在。");
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("医生排班信息.");
			sr.setResult(json);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	public ServiceResult<MytArraworkBean> getMytArraworkByOperconfId(String operconfid) {
		
		ServiceResult<MytArraworkBean> sr = new ServiceResult<MytArraworkBean>(-1, null);
		try {
			MytArraworkBean qryMaBean = new MytArraworkBean();
			qryMaBean.setOperconfid(Integer.valueOf(operconfid));
			qryMaBean.setState(MytConst.EFFECTIVE.getValue());
			Sql sql = DB.me().createSelect(qryMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS");
			List<MytArraworkBean> lstMaBean = DB.me().queryForBeanList(MyDatabaseEnum.boss,
					sql, MytArraworkBean.class);
			if (lstMaBean == null || lstMaBean.isEmpty()) {
				sr.setMessage("医生排班信息不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lstMaBean.get(0));
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	
	public ServiceResult<MytArraworkBean> getMytArrawork(int operconfid, String time) {
		
		ServiceResult<MytArraworkBean> sr = new ServiceResult<MytArraworkBean>(-1, null);
		try {
			Sql sqlMaBean = DB.me().createSql(MySqlNameEnum.getArrawork);
			sqlMaBean.addParamValue(operconfid);
			sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMaBean.addParamValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1);
			sqlMaBean.addParamValue(time);
			sqlMaBean.addParamValue(time);
			MytArraworkBean o = DB.me().queryForBean(MyDatabaseEnum.boss, 
					sqlMaBean, MytArraworkBean.class);
			DebugLogHelper.info(sqlMaBean.toString());
			if (o == null) {
				sr.setMessage("医生排班信息不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生排班信息成功。");
			sr.setResult(o);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
public ServiceResult<JSONObject> getMytArraworks(String time,String nowDate, String nowTime,Integer start,Integer pageSize) {
	ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			Sql sqlMaBean = DB.me().createSql(MySqlNameEnum.getArraworks);
			sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMaBean.addParamValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1);
			sqlMaBean.addParamValue(time);
			sqlMaBean.addParamValue(time);
			
			sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMaBean.addParamValue(nowDate);
			sqlMaBean.addParamValue(nowDate);
			sqlMaBean.addParamValue(nowTime);
			sqlMaBean.addParamValue(nowTime);
			JSONObject  lstMdvBean = DB.me().queryForJson(MyDatabaseEnum.boss, 
					sqlMaBean,  start, pageSize);
			DebugLogHelper.info(sqlMaBean.toString());
			if (lstMdvBean == null ) {
				sr.setMessage("获取医生排班信息失败。");
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生排班信息成功。");
			sr.setResult(lstMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}

public ServiceResult<JSONObject> getMytArraworksV2(String time) {
	ServiceResult<JSONObject> sr = new ServiceResult<JSONObject>(-1, null);
		try {
			Sql sqlMaBean = DB.me().createSql(MySqlNameEnum.getArraworks);
			sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMaBean.addParamValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1);
			sqlMaBean.addParamValue(time);
			sqlMaBean.addParamValue(time);
			JSONObject  lstMdvBean = DB.me().queryForJson(MyDatabaseEnum.boss, 
					sqlMaBean);
			DebugLogHelper.info(sqlMaBean.toString());
			if (lstMdvBean == null ) {
				sr.setMessage("获取医生排班信息失败。");
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生排班信息成功。");
			sr.setResult(lstMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
public ServiceResult<Integer> getMytArraworksCount(String time,String nowDate, String nowTime) {
			ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
			try {
				Sql sqlMaBean = DB.me().createSql(MySqlNameEnum.getArraworks);
				sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
				sqlMaBean.addParamValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1);
				sqlMaBean.addParamValue(time);
				sqlMaBean.addParamValue(time);
				
				sqlMaBean.addParamValue(MytConst.EFFECTIVE.getValue());
				sqlMaBean.addParamValue(nowDate);
				sqlMaBean.addParamValue(nowDate);
				sqlMaBean.addParamValue(nowTime);
				sqlMaBean.addParamValue(nowTime);
				
				int rt  = DB.me().queryForInteger(MyDatabaseEnum.boss, 
						sqlMaBean);
				DebugLogHelper.info(sqlMaBean.toString());
				sr.setCode(1);
				sr.setResult(rt);
				return sr;
			} catch (Exception e) {
				e.printStackTrace();
				sr.setResult(0);
				return sr;
			}
	}

	public ServiceResult<List<Object[]>> getMytArraworkForDate(String operconfid) {
		
		ServiceResult<List<Object[]>> sr = new ServiceResult<List<Object[]>>(-1, null);
		if (StringUtils.isEmpty(operconfid)) {
			sr.setMessage("医生配置编号不存在。");
			sr.setResult(null);
		}
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytArraworkForDate);
			sql.addParamValue(operconfid);
			List<Object[]> lstObj = DB.me().queryForList(MyDatabaseEnum.boss, sql);
			if (lstObj == null || lstObj.isEmpty()) {
				sr.setMessage("无法获取排班日期。");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("成功。");
				sr.setResult(lstObj);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<MytDoctorViewBean> getMytDoctorView(int operconfid) {

		ServiceResult<MytDoctorViewBean> sr = new ServiceResult<MytDoctorViewBean>(-1, null);
		try {
			MytDoctorViewBean qryMdvBean = new MytDoctorViewBean();
			qryMdvBean.setOperconfid(operconfid);
			qryMdvBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					DB.me().createSelect(qryMdvBean, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"),
					MytDoctorViewBean.class);
			if (qryMdvBean == null) {
				sr.setMessage("医生信息不存在。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(qryMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	public ServiceResult <MytDoctorViewBean> getMytDoctor(int operconfid) {
		ServiceResult<MytDoctorViewBean>  sr = new ServiceResult<MytDoctorViewBean>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.STATE = 1");
			sbSql.append(" AND a.OPERCONFID = " + operconfid);
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			MytDoctorViewBean qryMdvBean = DB.me().queryForBean(MyDatabaseEnum.boss,
					sql,
					MytDoctorViewBean.class);
			if (qryMdvBean == null) {
				sr.setMessage("医生信息不存在。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(qryMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生视图信息错误。");
			sr.setResult(null);
			return sr;
		}
	}
	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorViewList(MytDoctorViewBean parMdvBean, Page<MytDoctorViewBean> pg) {
		
		ServiceResult<List<MytDoctorViewBean>> sr = new ServiceResult<List<MytDoctorViewBean>>(-1, null);
		try {
			// 查询条件
			List<MytDoctorViewBean> lstMdvBean = null;
			if (pg == null) {
				lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
						DB.me().createSelect(parMdvBean, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
						MytDoctorViewBean.class);
			} else {
				lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
						DB.me().createSelect(parMdvBean, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
						MytDoctorViewBean.class, pg.getOffset(), pg.getPageSize());
			}
			if (lstMdvBean == null || lstMdvBean.isEmpty()) {
				sr.setMessage("无医生视图显示。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lstMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorList(String name) {
		ServiceResult<List<MytDoctorViewBean>> sr = new ServiceResult<List<MytDoctorViewBean>>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.STATE = 1");
			sbSql.append(" AND a.doctorname = '" + name+"'");
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			List<MytDoctorViewBean> lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss,
					sql,
					MytDoctorViewBean.class);
			if (lstMdvBean == null || lstMdvBean.isEmpty()) {
				sr.setMessage("无医生视图显示。");
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lstMdvBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			return sr;
		}
	}
	public ServiceResult<Integer> getMytDoctorViewCount(MytDoctorViewBean parMdvBean) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		try {
			List<MytDoctorViewBean> lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(parMdvBean, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
					MytDoctorViewBean.class);
			if (lstMdvBean == null || lstMdvBean.isEmpty()) {
				sr.setResult(0);
				return sr;
			}
			sr.setCode(1);
			sr.setResult(lstMdvBean.size());
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setResult(0);
			return sr;
		}
	}
	
	public ServiceResult<List<MytArraphoneViewBean>> getMytArraphoneView(String arraworkid) {
		
		ServiceResult<List<MytArraphoneViewBean>> sr = new ServiceResult<List<MytArraphoneViewBean>>(-1, null);
		try {
			MytArraphoneViewBean qryMavBean = new MytArraphoneViewBean();
			qryMavBean.setArraworkid((StringUtils.isEmpty(arraworkid) || "-".equals(arraworkid)) ? 0 : Integer.parseInt(arraworkid));
			qryMavBean.setState(MytConst.EFFECTIVE.getValue());
			List<MytArraphoneViewBean> rltMavBean = DB.me().queryForBeanList(
					MyDatabaseEnum.boss, DB.me().createSelect(qryMavBean,
					MyTableNameEnum.MYT_ARRAPHONE_VIEW, "dbo"),
					MytArraphoneViewBean.class);
			if (rltMavBean == null || rltMavBean.isEmpty()) {
				sr.setMessage("医生咨询电话信息不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setResult(rltMavBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(null);
			return sr;
		}
	}
	
	public boolean isBusy(int operconfid) {
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytBusyState);
			sql.addParamValue(operconfid);
			Integer state = (Integer) DB.me().queryForObject(MyDatabaseEnum.boss, sql);
			if (state != null && state == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
