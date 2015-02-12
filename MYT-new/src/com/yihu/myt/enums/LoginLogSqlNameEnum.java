package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum LoginLogSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryLoginLogCountByCondition,
	//查询记录
	queryLoginLogListByCondition,
	//插入
	insertLoginLog,
	//根据条件更新
	updateLoginLogByCondition
}