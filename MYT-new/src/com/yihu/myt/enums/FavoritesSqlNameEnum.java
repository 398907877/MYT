package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum FavoritesSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryFavoritesCountByCondition,
	//查询记录
	queryFavoritesListByCondition,
	//插入
	insertFavorites,
	//根据条件更新
	updateFavoritesByCondition
}