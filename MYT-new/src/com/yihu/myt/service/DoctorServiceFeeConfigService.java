package com.yihu.myt.service;

import com.common.unique.AppUnique;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IDoctorServiceFeeConfigService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.DoctorServiceFeeConfigBean;

public class DoctorServiceFeeConfigService implements IDoctorServiceFeeConfigService{
	public ServiceResult<DoctorServiceFeeConfigBean> getDcSerFeeCfg(DoctorServiceFeeConfigBean dcSerFeeCfg){

		ServiceResult<DoctorServiceFeeConfigBean> sr = new ServiceResult<DoctorServiceFeeConfigBean>(-1, null);

		Sql sql = DB.me().createSql(MySqlNameEnum.getServiceFeeConfig);
		StringBuffer sbSql = new StringBuffer(" WHERE 1=1  ");
		if(dcSerFeeCfg.getResId()!=null){
			sbSql.append(" AND ResId  = '" + dcSerFeeCfg.getResId() + "' ");
		}
		if(dcSerFeeCfg.getResUid()!=null){
			sbSql.append(" AND ServiceId  = '" + dcSerFeeCfg.getServiceId() + "' ");

		}
		
		sql.addVar("@p", sbSql.toString());
		try {

			 DoctorServiceFeeConfigBean doctorB = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, 
					sql, DoctorServiceFeeConfigBean.class);
			if (doctorB == null ) {
				sr.setMessage("流水订单不存在或已删除。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取流水订单成功。");
			sr.setResult(doctorB);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取流水订单异常。");
			return sr;
		}
	
	}
}
