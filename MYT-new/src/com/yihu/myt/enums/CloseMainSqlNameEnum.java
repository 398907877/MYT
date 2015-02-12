package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum CloseMainSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryCloseMainCountByCondition,
	//查询记录
	queryCloseMainListByCondition,
	//插入
	insertCloseMain,
	//根据条件更新
	updateCloseMainByCondition,		//根据医生id查找 医生	findDocById	
}