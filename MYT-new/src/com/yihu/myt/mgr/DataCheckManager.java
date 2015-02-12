package com.yihu.myt.mgr;

import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.vo.DataCheckBean;
import com.yihu.myt.vo.DataCheckResultBean;

public class DataCheckManager {

	public static ReturnValue record(String remark, int checkID,
			int operatorID, String operatorName, int checkState) {
		
		JdbcConnection connBossData = null;
		try {
			// 获取bossdata数据库事务及开始事务
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// 保存审核结果
			DataCheckResultBean proDcrBean = new DataCheckResultBean();
			proDcrBean.setCheckId(checkID);
			proDcrBean.setOperatorId(operatorID);
			proDcrBean.setOperatorName(operatorName);
			proDcrBean.setOpertime(DateOper.getNowDateTime());
			proDcrBean.setState(1);
			proDcrBean.setRemark(remark);
			DB.me().insert(connBossData, DB.me().createInsertSql(
					proDcrBean, MyTableNameEnum.DATA_CHECKRESULT, "dbo"));
			
			// 根据checkID获取审核表信息并更新
			DataCheckBean qryDcBean = new DataCheckBean();
			qryDcBean.setCheckId(checkID);
			DataCheckBean rltDcBean = DB.me().queryForBean(MyDatabaseEnum.bossdata, 
					DB.me().createSelect(qryDcBean, MyTableNameEnum.DATA_CHECK, "dbo"), 
					DataCheckBean.class);
			
			rltDcBean.setCheckState(checkState);
			rltDcBean.setRemark(rltDcBean.getRemark() + "[" + operatorName + ":" + remark + "]");
			rltDcBean.setCheckId(null);
			DB.me().update(connBossData, DB.me().createUpdateSql(
					rltDcBean, "dbo", MyTableNameEnum.DATA_CHECK, " checkId = " + checkID));
			
			// 提交事务并返回结果
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
}
