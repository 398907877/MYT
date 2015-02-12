package com.yihu.myt.enums;import com.coreframework.db.SqlNameEnum;
public enum BulletinTypeSqlNameEnum implements SqlNameEnum {

	//查询记录数
	queryBulletinTypeCountByCondition,
	//查询记录
	queryBulletinTypeListByCondition,
	//插入
	insertBulletinType,
	//根据条件更新
	updateBulletinTypeByCondition
}