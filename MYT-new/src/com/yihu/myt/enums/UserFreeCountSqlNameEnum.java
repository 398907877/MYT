package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum UserFreeCountSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryUserFreeCountCountByCondition,	queryUserFreeListByCondition,	queryUserGetFreeCount,	queryUserGetFree,
	//查询记录
	queryUserFreeCountListByCondition,	queryUserFreeOverList,
	//插入
	insertUserFreeCount,
	//根据条件更新
	updateUserFreeCountByCondition
}