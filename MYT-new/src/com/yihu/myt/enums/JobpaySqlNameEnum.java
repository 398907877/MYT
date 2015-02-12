package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum JobpaySqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryJobpayCountByCondition,
	//查询记录
	queryJobpayListByCondition,
	//插入
	insertJobpay,
	//根据条件更新
	updateJobpayByCondition
}