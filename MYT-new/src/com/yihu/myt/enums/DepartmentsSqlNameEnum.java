package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum DepartmentsSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryDepartmentsCountByCondition,
	//查询记录
	queryDepartmentsListByCondition,
	//插入
	insertDepartments,
	//根据条件更新
	updateDepartmentsByCondition
}