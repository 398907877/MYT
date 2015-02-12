package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum CheckRecordSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryCheckRecordCountByCondition,
	//查询记录
	queryCheckRecordListByCondition,
	//插入
	insertCheckRecord,
	//根据条件更新
	updateCheckRecordByCondition
}