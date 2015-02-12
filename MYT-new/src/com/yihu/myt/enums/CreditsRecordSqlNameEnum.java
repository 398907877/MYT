package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum CreditsRecordSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryCreditsRecordCountByCondition,
	//查询记录
	queryCreditsRecordListByCondition,
	//插入
	insertCreditsRecord,
	//根据条件更新
	updateCreditsRecordByCondition
}