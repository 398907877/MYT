package com.yihu.myt.service;

import org.apache.commons.beanutils.BeanUtils;

import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IDataCheckService;
import com.yihu.myt.IMytCheckService;
import com.yihu.myt.enums.BaseDictionary;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.vo.DataCheckBean;
import com.yihu.myt.vo.DataCheckResultBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytArraworkWBean;

public class MytCheckService implements IMytCheckService {

	public ReturnValue insertMytArrawork(int checkid, int operator,
			String operatorname, String remark, DataCheckBean parDcBean, MytArraworkBean parMaBean) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取BossData数据库事务及开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// 保存审核结果表
			DataCheckResultBean proDcrBean = new DataCheckResultBean();
			proDcrBean.setCheckId(checkid);
			proDcrBean.setOperatorId(operator);
			proDcrBean.setOperatorName(operatorname);
			proDcrBean.setOpertime(DateOper.getNowDateTime());
			proDcrBean.setState(1);
			proDcrBean.setRemark(remark);
			DB.me().insert(connBossData, DB.me().createInsertSql(
					proDcrBean, MyTableNameEnum.DATA_CHECKRESULT, "dbo"));
			
			// 保存审核表
			DataCheckBean proDcBean = new DataCheckBean();
			proDcBean.setCheckName(parDcBean.getCheckName());
			proDcBean.setType(parDcBean.getType());
			proDcBean.setOperItem(BaseDictionary.OperatorItem.mytArr);
			proDcBean.setHandlerId(parDcBean.getHandlerId());
			proDcBean.setHandlerName(parDcBean.getHandlerName());
			proDcBean.setHandlerDeptId(parDcBean.getHandlerDeptId());
			proDcBean.setHandlerDeptName(parDcBean.getHandlerDeptName());
			proDcBean.setCheckDeptId(parDcBean.getCheckDeptId());
			proDcBean.setCheckDeptName(parDcBean.getCheckDeptName());
			proDcBean.setCheckHandlerId(parDcBean.getCheckHandlerId());
			proDcBean.setCheckHandlerName(parDcBean.getCheckHandlerName());
			proDcBean.setUrl(parDcBean.getUrl());
			proDcBean.setOpertime(parDcBean.getOpertime());
			proDcBean.setCheckState(2);
			proDcBean.setCheckId(null);
			DB.me().update(connBossData, DB.me().createUpdateSql(
					proDcBean, "dbo", MyTableNameEnum.DATA_CHECK, " checkId = " + checkid));
			
			int r = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(parMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
			if (r <= 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(1, "添加排班错误。");
			}
			
			// 提交事务
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "添加排班成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue updateMytArrawork(int checkid, String remark,
			int operator, String operatorname, DataCheckBean parDcBean,
			MytArraworkWBean parMawBean) {
		
		JdbcConnection connBossData = null;
		JdbcConnection connBoss = null;
		try {
			// 获取BossData数据库事务及开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// 保存审核结果表
			DataCheckResultBean proDcrBean = new DataCheckResultBean();
			proDcrBean.setCheckId(checkid);
			proDcrBean.setOperatorId(operator);
			proDcrBean.setOperatorName(operatorname);
			proDcrBean.setOpertime(DateOper.getNowDateTime());
			proDcrBean.setState(1);
			proDcrBean.setRemark(remark);
			DB.me().insert(connBossData, DB.me().createInsertSql(
					proDcrBean, MyTableNameEnum.DATA_CHECKRESULT, "dbo"));
			
			// 保存审核表
			DataCheckBean proDcBean = new DataCheckBean();
			proDcBean.setCheckName(parDcBean.getCheckName());
			proDcBean.setType(parDcBean.getType());
			proDcBean.setOperItem(BaseDictionary.OperatorItem.mytArr);
			proDcBean.setHandlerId(parDcBean.getHandlerId());
			proDcBean.setHandlerName(parDcBean.getHandlerName());
			proDcBean.setHandlerDeptId(parDcBean.getHandlerDeptId());
			proDcBean.setHandlerDeptName(parDcBean.getHandlerDeptName());
			proDcBean.setCheckDeptId(parDcBean.getCheckDeptId());
			proDcBean.setCheckDeptName(parDcBean.getCheckDeptName());
			proDcBean.setCheckHandlerId(parDcBean.getCheckHandlerId());
			proDcBean.setCheckHandlerName(parDcBean.getCheckHandlerName());
			proDcBean.setUrl(parDcBean.getUrl());
			proDcBean.setOpertime(parDcBean.getOpertime());
			proDcBean.setCheckState(2);
			DB.me().insert(connBossData, DB.me().createInsertSql(
					proDcBean, MyTableNameEnum.DATA_CHECK, "dbo"));
			
			MytArraworkBean proMaBean = new MytArraworkBean();
			BeanUtils.copyProperties(proMaBean, parMawBean);
			int r = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(proMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
			if (r <= 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(1, "修改排班错误。");
			}
			
			// 提交事务
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "修改排班成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ServiceResult<Integer> noAgree(String remark, int checkid, int operator,
			String operatorname) {
		
		ServiceResult<Integer> sr = new ServiceResult<Integer>(-1, null);
		JdbcConnection connBossData = null;
		try {
			// 获取数据库事务及开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// 保存审核结果表
			DataCheckResultBean proDcrBean = new DataCheckResultBean();
			proDcrBean.setCheckId(checkid);
			proDcrBean.setOperatorId(operator);
			proDcrBean.setOperatorName(operatorname);
			proDcrBean.setOpertime(DateOper.getNowDateTime());
			proDcrBean.setState(1);
			proDcrBean.setRemark(remark);
			DB.me().insert(connBossData, 
					DB.me().createInsertSql(proDcrBean, MyTableNameEnum.DATA_CHECKRESULT, "dbo"));
			
			// 根据checkid获取审核表并更新
			DataCheckBean qryDcBean = new DataCheckBean();
			qryDcBean.setCheckId(checkid);
			DataCheckBean rltDcBean = DB.me().queryForBean(MyDatabaseEnum.bossdata, 
					DB.me().createSelect(qryDcBean, MyTableNameEnum.DATA_CHECK, "dbo"), 
					DataCheckBean.class);
			
			rltDcBean.setCheckState(1);
			rltDcBean.setCheckId(null);
			DB.me().update(connBossData, 
					DB.me().createUpdateSql(rltDcBean, "dbo", MyTableNameEnum.DATA_CHECK, " checkId = " + checkid));
			
			// 提交事务及返回结果
			connBossData.commitTransaction(true);
			sr.setCode(1);
			sr.setResult(MytConst.YES.getValue());
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setResult(MytConst.NO.getValue());
			return sr;
		}
	}

	public ReturnValue effect(int checkID, String remark, int operatorID,
			String operatorName) {
		
		try {
			// 根据checkid获取MytArraworkW表信息
			MytArraworkWBean qryMawBean = new MytArraworkWBean();
			qryMawBean.setCheckId(checkID);
			MytArraworkWBean rltMawBean = DB.me().queryForBean(MyDatabaseEnum.bossdata, 
					DB.me().createSelect(qryMawBean, MyTableNameEnum.MYT_ARRAWORK_W, "dbo"), 
					MytArraworkWBean.class);
			if (rltMawBean == null) {
				return new ReturnValue(-1, "医生排班信息不存在。");
			}
			
			// 根据checkid获取审核表并更新
			DataCheckBean qryDcBean = new DataCheckBean();
			qryDcBean.setCheckId(checkID);
			DataCheckBean rltDcBean = DB.me().queryForBean(MyDatabaseEnum.bossdata, 
					DB.me().createSelect(qryDcBean, MyTableNameEnum.DATA_CHECK, "dbo"), 
					DataCheckBean.class);
			if (rltDcBean == null) {
				return new ReturnValue(-1, "审核信息不存在。");
			}
			if (rltDcBean.getCheckState() != BaseDictionary.CheckState.unDeal) {
				return new ReturnValue(-1, "审核信息的状态不是未审核。");
			}
			
			// 调用IDataCheckService接口
			ReturnValue rm = ((IDataCheckService) Ioc.get(IDataCheckService.class)).pass(remark, checkID, operatorID, operatorName);
			if (rm.getCode() != 1) {
				return new ReturnValue(-1, rm.getMessage());
			}
			
			switch (rltDcBean.getType()) {
				case BaseDictionary.OperatorType.add: {
					MytArraworkBean proMaBean = new MytArraworkBean();
					BeanUtils.copyProperties(proMaBean, rltMawBean);
					int rMaBean = DB.me().insert(MyDatabaseEnum.boss, 
							DB.me().createInsertSql(proMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"));
					if (rMaBean == 0) {
						return new ReturnValue(-1, "审核失败，无法新增排班。");
					} else {
						return new ReturnValue(1, "审核成功，已新增排班" + rltMawBean.getDoctorname() + "。");
					}
				}
				case BaseDictionary.OperatorType.update: {
					MytArraworkBean qryMaBean = new MytArraworkBean();
					qryMaBean.setArraworkid(rltMawBean.getArraworkid());
					MytArraworkBean rltMaBean = DB.me().queryForBean(MyDatabaseEnum.bossdata, 
							DB.me().createSelect(qryMaBean, MyTableNameEnum.MYT_ARRAWORK, "BOSS"), 
							MytArraworkBean.class);
					rltMaBean.setArraworkid(null);
					int rMaBean = DB.me().update(MyDatabaseEnum.boss, 
							DB.me().createUpdateSql(rltMaBean, "BOSS", MyTableNameEnum.MYT_ARRAWORK, " checkId = " + rltMawBean.getArraworkid()));
					if (rMaBean == 0) {
						return new ReturnValue(-1, "审核失败，无法修改排班。");
					} else {
						return new ReturnValue(1, "审核成功，已修改排班" + rltMawBean.getDoctorname() + "。");
					}
				}
				case BaseDictionary.OperatorType.delete: {
					return new ReturnValue(-1, "不需要审核");
				}
				default: {
					return new ReturnValue(-1, "未知的操作类型。");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

}
