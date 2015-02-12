package com.yihu.myt.service;

import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.das.dbutils.Order;
import com.yihu.myt.IBaseInfoService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.vo.BaseSmalldepartmentBean;

public class BaseInfoService implements IBaseInfoService {
	public JSONObject getSmallStdDept(int bigDeptId) {
		JSONObject obj = new JSONObject();
		try {
			String sqlPart = "";
			if (bigDeptId > 0) {
				sqlPart = " and parentid=" + bigDeptId;
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getSmallStdDept);
			sql.addVar("@p", sqlPart);
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql);

			JSONObject temp = new JSONObject();
			temp.put("deptid", "-1");
			temp.put("deptname", "全科咨询");
			obj.getJSONArray("result").put(0, temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public JSONObject getDeptByOrg(int orgId){
		JSONObject obj = new JSONObject();
		try {
			if (orgId < 1) {
				orgId=1;
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getDeptByOrg);
			sql.addParamValue(orgId);
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql);

			JSONObject temp = new JSONObject();
			temp.put("deptid", "-1");
			temp.put("deptname", "全科咨询");
			obj.getJSONArray("result").put(0, temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public JSONObject getDoctorByOrgAndDept(int orgId, String deptId) {
		JSONObject obj = new JSONObject();
		try {
			StringBuffer sb = new StringBuffer();
			if("-1".equals(deptId)) {
				//全科咨询
				sb.append(" and doctorlevel=7");
			} else {
				sb.append(" and doctorlevel<>7 and hospofficeid='");
				sb.append(deptId);
				sb.append("'");
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorByOrgAndDept);
			sql.addParamValue(orgId);
			sql.addVar("@p", sb.toString());
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
