package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum UserDetailSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryUserDetailCountByCondition,
	//查询记录
	queryUserDetailListByCondition,
	//插入
	insertUserDetail,
	//根据条件更新
	updateUserDetailByCondition
}