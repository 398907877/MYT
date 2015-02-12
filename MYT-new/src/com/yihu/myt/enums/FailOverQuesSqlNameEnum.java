package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum FailOverQuesSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryFailOverQuesCountByCondition,
	//查询记录
	queryFailOverQuesListByCondition,
	//插入
	insertFailOverQues,
	//根据条件更新
	updateFailOverQuesByCondition
}