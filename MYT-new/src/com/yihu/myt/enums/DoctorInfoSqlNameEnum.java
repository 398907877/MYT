package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum DoctorInfoSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryDoctorInfoCountByCondition,
	//查询记录
	queryDoctorInfoListByCondition,
	//插入
	insertDoctorInfo,
	//根据条件更新
	updateDoctorInfoByCondition
}