package com.yihu.myt.service;

import java.sql.SQLException;
import java.util.List;

import com.common.unique.AppUnique;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IDoctorBillService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.vo.DoctorBillBean;

public class DoctorBillService implements IDoctorBillService{
	
	public ReturnValue add(DoctorBillBean dcBill){
		try {
			
			int r =  DB.me().insert(MyDatabaseEnum.BaseInfo,
					DB.me().createInsertSql(dcBill, MyTableNameEnum.DC_Bill, "BaseInfo"));
			if (r == 0) {
				return new ReturnValue(-1, "添加流水失败。");
			}
			return new ReturnValue(1, "添加流水订单成功。");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnValue(-1, "添加流水失败。");
		}
	}
	
	public ReturnValue update(DoctorBillBean dcBill){
		try {
			int r =  DB.me().update(MyDatabaseEnum.boss,
					DB.me().createInsertSql(dcBill, MyTableNameEnum.DC_Bill, "BaseInfo"));
			if (r == 0) {
				return new ReturnValue(-1, "添加流水失败。");
			}
			return new ReturnValue(1, "添加流水订单成功。");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ReturnValue(-1, "添加流水失败。");
		}
	}
	

	public ServiceResult<List<DoctorBillBean>> getResult(DoctorBillBean dcBill){
		ServiceResult<List<DoctorBillBean>> sr = new ServiceResult<List<DoctorBillBean>>(-1, null);
		
		Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorBill);
		StringBuffer sbSql = new StringBuffer(" WHERE state=1 ");
		if(dcBill.getBillWaterId()!=null){
			sbSql.append(" AND BillWaterID  = '" + dcBill.getBillWaterId() + "' ");
		}
		if(dcBill.getDa_id()!=null){
			sbSql.append(" AND DA_ID  = '" + dcBill.getDa_id() + "' ");
		}
		if(dcBill.getDoctorUid()!=null){
			sbSql.append(" AND DoctorUid  = '" + dcBill.getDoctorUid()+ "' ");
		}
		if(dcBill.getServiceRecordId()!=null){
			sbSql.append(" AND ServiceRecordId  = '" + dcBill.getServiceRecordId() + "' ");
		}
		if(dcBill.getState()!=null){
			sbSql.append(" AND State  = '" + dcBill.getState() + "' ");
		}
		sql.addVar("@p", sbSql.toString());
		try {
			List<DoctorBillBean> lstO = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, 
					sql, DoctorBillBean.class);
			if (lstO == null || lstO.isEmpty()) {
				sr.setMessage("流水订单不存在或已删除。");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("获取流水订单成功。");
			sr.setResult(lstO);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("获取流水订单异常。");
			return sr;
		}
	}
}
