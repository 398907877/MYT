package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum ConfigSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryConfigCountByCondition,
	//查询记录
	queryConfigListByCondition,
	//插入
	insertConfig,
	//根据条件更新
	updateConfigByCondition
}