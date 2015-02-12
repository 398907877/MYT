package com.yihu.myt.service;

import java.sql.SQLException;

import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.vo.DoctorAccountBean;
import com.yihu.myt.vo.DoctorBillBean;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;

public class DoctorAccountService {
	public ReturnValue Add(DoctorAccountBean doctorAcc){
		try {
			int r =  DB.me().insert(MyDatabaseEnum.BaseInfo,
					DB.me().createInsertSql(doctorAcc, MyTableNameEnum.DC_DoctorAccount, "BaseInfo"));
			if (r == 0) {
				return new ReturnValue(-1, "添加医生信息失败。");
			}
			return new ReturnValue(1, "添加医生信息订单成功。");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnValue(-1, "添加医生信息失败。");
		}
	}
	public ReturnValue Update(DoctorAccountBean doctorAcc){
		try {
			int r =  DB.me().update(MyDatabaseEnum.BaseInfo,
					DB.me().createInsertSql(doctorAcc, MyTableNameEnum.DC_DoctorAccount, "BaseInfo"));
			if (r == 0) {
				return new ReturnValue(-1, "添加医生信息失败。");
			}
			return new ReturnValue(1, "添加医生信息订单成功。");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnValue(-1, "添加医生信息失败。");
		}
	}
	public ServiceResult<DoctorAccountBean> getDoctorAcc(
			DoctorAccountBean doctor) {
		ServiceResult<DoctorAccountBean> sr = new ServiceResult<DoctorAccountBean>(
				-1, null);
		try {
			DoctorAccountBean docAcc = new DoctorAccountBean();
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND DoctorUid = '" + doctor.getDoctorUid() + "'");
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorAccount);
			sql.addVar("@p", sbSql.toString());
			docAcc = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, sql,
					DoctorAccountBean.class);
			if (docAcc == null) {
				sr.setMessage("医生信息不存在。");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("获取医生信息成功。");
				sr.setResult(docAcc);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取医生信息错误。");
			sr.setResult(null);
			return sr;
		}
	}
}
