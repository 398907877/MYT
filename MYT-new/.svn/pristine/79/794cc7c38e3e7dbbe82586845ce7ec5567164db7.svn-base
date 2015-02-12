package com.yihu.myt.service;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IDoctorFeeTemplateService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.DoctorServiceFeeConfigBean;;

public class DoctorFeeTemplateService implements IDoctorFeeTemplateService{
	public ServiceResult<DoctorServiceFeeConfigBean> getServiceFeeConfig(
			DoctorServiceFeeConfigBean doctor) {
		ServiceResult<DoctorServiceFeeConfigBean> sr = new ServiceResult<DoctorServiceFeeConfigBean>(
				-1, null);
		try {
			DoctorServiceFeeConfigBean DoctorSFC = new DoctorServiceFeeConfigBean();
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			
			sbSql.append(" AND FeeTemplateId = '" + DoctorSFC.getFeeTemplateId() + "'");
			Sql sql = DB.me().createSql(MySqlNameEnum.getFeeTemplate);
			sql.addVar("@p", sbSql.toString());
			DoctorSFC = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, sql,
					DoctorServiceFeeConfigBean.class);
			if (DoctorSFC == null) {
				sr.setMessage("资费模板不存在。");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("获取资费模板成功。");
				sr.setResult(DoctorSFC);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取资费模板错误。");
			sr.setResult(null);
			return sr;
		}
	}
}
