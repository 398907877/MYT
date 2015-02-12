package com.yihu.myt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.enums.QuesMainSqlNameEnum;
import com.yihu.myt.vo.BaseDoctorBean;
import com.yihu.myt.vo.BaseSmalldepartmentBean;
import com.yihu.myt.vo.MytConsphoneBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytOperconfigWBean;
import com.yihu.myt.vo.OperconfigVo;


/**
 * 名医通医生配置服务实现类
 * @author wangfeng
 * @company yihu.com
 * 2012-8-8下午05:13:23
 */
public class OperconfidService implements IOperconfidService {

	public ServiceResult<List<Map<String, String>>> getStdOffice(int orgId) {
		
		ServiceResult<List<Map<String, String>>> sr = new ServiceResult<List<Map<String, String>>>(-1, null);
		try {
			Sql sqlObj = DB.me().createSql(MySqlNameEnum.getDeptByOrg);
			sqlObj.addParamValue(orgId);
			List<Object[]> lstObj = DB.me().queryForList(MyDatabaseEnum.boss, sqlObj);
			if (lstObj == null || lstObj.isEmpty()) {
				sr.setMessage("科室信息不存在。");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("获取科室信息成功。");
				List<Map<String, String>> lstMap = new ArrayList<Map<String, String>>();
				for (Object[] objs : lstObj) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("hospofficeid", objs[0].toString());
					map.put("hospofficename", objs[1].toString());
					lstMap.add(map);
				}
				sr.setResult(lstMap);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取科室信息错误。");
			sr.setResult(null);
			return sr;
		}
	}
public String getDoctorIndexList(String name,int row,int page) {
		JSONObject result = new JSONObject();
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.doctorName = '" + name + "'");
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			result = DB.me().queryForJson(MyDatabaseEnum.boss, 
					sql,row,page);
		}catch (Exception e) {
					e.printStackTrace();
					try {
						result.put("code", "-1");
						result.put("result", e.getLocalizedMessage());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		return result.toString();
	}
	public ServiceResult<List<MytDoctorViewBean>> getDoctor(int orgID, String deptID) {
		
		ServiceResult<List<MytDoctorViewBean>> sr = new ServiceResult<List<MytDoctorViewBean>>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.STATE = 1");
			sbSql.append(" AND a.ORGID = " + orgID);
			if ("-1".equals(deptID)) {
				sbSql.append(" AND a.DOCTORLEVEL = 7");
			} else {
				sbSql.append(" AND a.HOSPOFFICEID = " + deptID);
				sbSql.append(" AND a.DOCTORLEVEL <> 7");
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			List<MytDoctorViewBean> lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytDoctorViewBean.class);
			
			if (lstMdvBean == null || lstMdvBean.isEmpty()) {
				sr.setMessage("医生视图信息不存在。");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("获取医生视图信息成功。");
				sr.setResult(lstMdvBean);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生视图信息错误。");
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<List<MytMainintrodoctorBean>> getMainintrodoctor(int operconfid) {
		
		ServiceResult<List<MytMainintrodoctorBean>> sr = new ServiceResult<List<MytMainintrodoctorBean>>(-1, null);
		try {
			Sql sqlMmBean = DB.me().createSql(MySqlNameEnum.getMainintrodoctor);
			sqlMmBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMmBean.addParamValue(operconfid);
			List<MytMainintrodoctorBean> lstMmBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sqlMmBean, MytMainintrodoctorBean.class);
			
			if (lstMmBean == null || lstMmBean.isEmpty()) {
				sr.setMessage("医生简介信息不存在。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取医生简介信息成功。");
			sr.setResult(lstMmBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生简介信息错误。");
			sr.setResult(null);
			return sr;
		}
	}
	
	public ReturnValue addDoctor(int operator, String operatorname, MytOperconfigBean parMoBean, String consphone) {

		JdbcConnection connBoss = null;
		try {
			// 设置医生配置信息
			parMoBean.setState(MytConst.EFFECTIVE.getValue());		
			parMoBean.setOperatorid(operator+"");
			parMoBean.setOperatorname(operatorname);
			parMoBean.setOpertime(DateOper.getNowDateTime());
			
			// 根据doctorid查询医生基本信息
			BaseDoctorBean qryBdBean = new BaseDoctorBean();
			qryBdBean.setDoctorid(parMoBean.getDoctorid());
			BaseDoctorBean rltBdBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryBdBean, MyTableNameEnum.BASE_DOCTOR, "BOSS"), 
					BaseDoctorBean.class);
			if (rltBdBean == null) {
				return new ReturnValue(-1, "医生基本信息不存在。");
			}
			
			// 查询科室信息
			BaseSmalldepartmentBean qryBsBean = new BaseSmalldepartmentBean();
			qryBsBean.setDeptid(rltBdBean.getDeptid());
			List<BaseSmalldepartmentBean> rltBsBeans = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryBdBean, MyTableNameEnum.BASE_DOCTOR, "BOSS"), 
					BaseSmalldepartmentBean.class);
			if (rltBsBeans == null || rltBsBeans.isEmpty()) {
				return new ReturnValue(-1, "添加医生出错：关联标准科室异常。");
			}
			
			// 保存医生配置信息
			parMoBean.setDoctorid(rltBdBean.getDoctorid());
			
			// 获取boss数据库事务并开始事务
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			int rMoBean = DB.me().insert(connBoss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "新增名医通医生操作失败。");
			}
			
			String[] phoneArr = consphone.split(",");
			for (int i = 0; i < phoneArr.length; i++) {
				MytConsphoneBean proMcBean = new MytConsphoneBean();
				proMcBean.setConsphone(phoneArr[i]);
				proMcBean.setState(1);
				proMcBean.setDoctorid(parMoBean.getDoctorid());
				int rMcBean = DB.me().insert(connBoss, 
						DB.me().createInsertSql(proMcBean, MyTableNameEnum.MYT_CONSPHONE, "dbo"));
				if (rMcBean == 0) {
					connBoss.rollbackAndclose();
					return new ReturnValue(-1, "添加医生联系方式操作失败。");
				}
			}
			
			// 获取MytOperconfig信息
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(parMoBean.getOperconfid());
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			
			// 保存医生配置信息
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			int rMowBean = DB.me().insert(MyDatabaseEnum.bossdata, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "新增医生配置信息操作失败。");
			}
			
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "新增医生配置信息操作成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue addNODoctor(MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取bossdata数据库事务开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
						
			// 将parMoBean对象拷贝到proMowBean对象
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, parMoBean);
			
			// 保存MytOperconfigWBean对象
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "添加非医院医生出错。");
			}
			
			// 保存MytOperconfigBean对象
			int rMoBean = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "添加非医院医生出错。");
			}
			
			// 提交事务并返回结果
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "添加非医院医生成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue updateDoctor(int operconfid, MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取MytOperconfig信息
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			parMoBean.setOrgid(rltMoBean.getOrgid());
			parMoBean.setCityid(rltMoBean.getCityid());
			parMoBean.setState(MytConst.EFFECTIVE.getValue());
			parMoBean.setDoctorid(rltMoBean.getDoctorid());
			parMoBean.setSevendoctorid(rltMoBean.getSevendoctorid());
			parMoBean.setTendoctorid(rltMoBean.getTendoctorid());
			parMoBean.setRealname(rltMoBean.getRealname());
			parMoBean.setYiHustatus(rltMoBean.getYiHustatus());
			
			// 将MytOperconfigBean拷贝到MytOperconfigWBean对象
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, parMoBean);
			
			// 获取bossdata数据库连接并开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				return new ReturnValue(-1, "添加医生配置信息错误。");
			}
			
			// 更新MytOperconfigBean（医生配置信息）
			parMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(parMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "修改医生配置信息错误。");
			}
			
			// 提交事务并返回结果
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "添加医生信息成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue deleteDoctor(int operconfid,int operator,String operatorname) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取MytOperconfig信息
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			rltMoBean.setOperatorid(operator + "");
			rltMoBean.setOperatorname(operatorname);
			rltMoBean.setOpertime(DateOper.getNowDateTime());
			rltMoBean.setState(MytConst.PAUSE.getValue() );
			
			// 将MytOperconfigBean拷贝到MytOperconfigWBean对象
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			
			// 获取bossdata数据库连接并开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				return new ReturnValue(-1, "添加医生配置信息错误。");
			}
			
			// 更新MytOperconfigBean（医生配置信息）
			rltMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(rltMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "修改医生配置信息错误。");
			}
			
			// 提交事务并返回结果
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "删除医生信息成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue NOdeleteDoctor(int operconfid, int operator, String operatorname) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取MytOperconfig信息
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "医生配置信息不存在。");
			}
			rltMoBean.setOperatorid(operator + "");
			rltMoBean.setOperatorname(operatorname);
			rltMoBean.setOpertime(DateOper.getNowDateTime());
			rltMoBean.setState(MytConst.EFFECTIVE.getValue() );
			
			// 将MytOperconfigBean拷贝到MytOperconfigWBean对象
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			
			// 获取bossdata数据库连接并开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.close();
				return new ReturnValue(-1, "添加医生配置信息错误。");
			}
			
			// 更新MytOperconfigBean（医生配置信息）
			rltMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(rltMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "修改医生配置信息错误。");
			}
			
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "恢复医生成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue addDept(String cmbSdept, String sdept, String remark, int operator, String operatorname, MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// 保存MytOperconfigBean对象
			MytOperconfigBean proMoBean = new MytOperconfigBean();
			proMoBean.setDoctorid(parMoBean.getDoctorid());
			proMoBean.setSevendoctorid(parMoBean.getSevendoctorid());
			proMoBean.setTendoctorid(parMoBean.getTendoctorid());
			proMoBean.setRealname(parMoBean.getRealname());
			proMoBean.setDoctorname(parMoBean.getDoctorname());
			proMoBean.setHospofficeid(cmbSdept);
			proMoBean.setHospofficename(sdept);				
			proMoBean.setOperatorid(operator+"");
			proMoBean.setOperatorname(operatorname);
			proMoBean.setOpertime(DateOper.getNowDateTime());
			proMoBean.setDoctoridiom(parMoBean.getDoctoridiom());
			proMoBean.setDoctorlevel(parMoBean.getDoctorlevel());
			proMoBean.setPaytype(parMoBean.getPaytype());
			proMoBean.setBalancetype(parMoBean.getBalancetype());
			proMoBean.setSendtype(parMoBean.getSendtype());
			proMoBean.setOrgid(parMoBean.getOrgid());
			proMoBean.setCityid(parMoBean.getCityid());
			proMoBean.setState(parMoBean.getState());
			proMoBean.setRemark(remark);
			proMoBean.setIspaydoctor(parMoBean.getIspaydoctor());
			
			// 将MytOperconfigBean对象拷贝到MytOperconfigWBean对象
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, proMoBean);
			
			// 获取bossdata数据库连接开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// 保存MytOperconfigWBean对象
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.close();
				return new ReturnValue(-1, "添加非医院医生出错。");
			}
			
			// 保存MytOperconfigBean对象
			int rMoBean = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "添加非医院医生出错。");
			}
			
			// 提交事务并返回结果
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "添加非医院医生成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ServiceResult<Boolean> isExsistMytOperconfig(String doctorId, String state) {
		
		ServiceResult<Boolean> sr = new ServiceResult<Boolean>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytOperconfigCount);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			if (StringUtils.isNotEmpty(doctorId)) {
				sbSql.append(" AND a.DOCTORID = " + doctorId);
			}
			if (StringUtils.isNotEmpty(state)) {
				sbSql.append(" AND a.STATE <> " + state);
			}
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			if (count == null || count == 0) {
				sr.setMessage("不存在。");
				sr.setResult(false);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("存在。");
			sr.setResult(true);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(true);
			return sr;
		}
	}
	
	public JSONObject getConsphoneByDoctor(int operconfId){
		JSONObject obj = new JSONObject();
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getConsphoneByDoctor);
			sql.addParamValue(operconfId);
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
