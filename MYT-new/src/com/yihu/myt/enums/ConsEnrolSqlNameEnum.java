package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum ConsEnrolSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryConsEnrolCountByCondition,
	//查询记录
	queryConsEnrolListByCondition,
	//插入
	insertConsEnrol,
	//根据条件更新
	updateConsEnrolByCondition,	//获取用户所咨询过的电话咨询数据	getMytConsenrols
}