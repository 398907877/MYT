package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum ReportSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryReportCountByCondition,
	//查询记录
	queryReportListByCondition,
	//插入
	insertReport,
	//根据条件更新
	updateReportByCondition
}