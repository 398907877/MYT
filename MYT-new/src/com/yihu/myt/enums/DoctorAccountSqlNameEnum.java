package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum DoctorAccountSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryDoctorAccountCountByCondition,
	//查询记录
	queryDoctorAccountListByCondition,
	//插入
	insertDoctorAccount,
	//根据条件更新
	updateDoctorAccountByCondition
}