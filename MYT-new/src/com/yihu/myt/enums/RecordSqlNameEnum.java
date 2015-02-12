package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum RecordSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryRecordCountByCondition,
	//查询记录
	queryRecordListByCondition,
	//插入
	insertRecord,
	//根据条件更新
	updateRecordByCondition
}