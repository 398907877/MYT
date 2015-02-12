package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum DoctorClickSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryDoctorClickCountByCondition,
	//查询记录
	queryDoctorClickListByCondition,
	//插入
	insertDoctorClick,
	//根据条件更新
	updateDoctorClickByCondition
}