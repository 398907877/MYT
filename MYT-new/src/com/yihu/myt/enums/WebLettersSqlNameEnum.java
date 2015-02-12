package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum WebLettersSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryWebLettersCountByCondition,
	//查询记录
	queryWebLettersListByCondition,
	//插入
	insertWebLetters,
	//根据条件更新
	updateWebLettersByCondition
}