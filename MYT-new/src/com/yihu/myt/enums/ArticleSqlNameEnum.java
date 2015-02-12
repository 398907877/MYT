package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum ArticleSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryArticleCountByCondition,
	//查询记录
	queryArticleListByCondition,
	//插入
	insertArticle,
	//根据条件更新
	updateArticleByCondition
}