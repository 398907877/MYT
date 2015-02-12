package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum SelfHelpSqlNameEnum implements SqlNameEnum {

	//查询记录数
	querySelfHelpCountByCondition,
	//查询记录
	querySelfHelpListByCondition,
	//插入
	insertSelfHelp,
	//根据条件更新
	updateSelfHelpByCondition
}