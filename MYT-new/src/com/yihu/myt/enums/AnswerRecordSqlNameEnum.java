package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum AnswerRecordSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryAnswerRecordCountByCondition,
	//查询记录
	queryAnswerRecordListByCondition,
	//插入
	insertAnswerRecord,
	//根据条件更新
	updateAnswerRecordByCondition
}