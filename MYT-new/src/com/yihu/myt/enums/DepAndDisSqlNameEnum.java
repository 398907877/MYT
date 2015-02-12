package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum DepAndDisSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryDepAndDisCountByCondition,
	//查询记录
	queryDepAndDisListByCondition,
	//插入
	insertDepAndDis,
	//根据条件更新
	updateDepAndDisByCondition
}