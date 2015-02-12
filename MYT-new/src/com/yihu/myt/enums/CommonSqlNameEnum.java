package com.yihu.myt.enums;

import com.coreframework.db.SqlNameEnum;

public enum CommonSqlNameEnum implements SqlNameEnum {

	// �����
	getBigDepartment, getBigDepartmentByCondition,

	getBigDepartmentSnByCondition,
	// С����
	getSmallDepartment,

	// ��׼����С����
	getSmallDepartmentByCondition,

	// ʡ��
	getProvinceList,

	// ���м�¼��
	getCityCountByProvince,
	// ����
	getCityByProvince,
	getGBCodeByCityId,
	
	
	// ����
	getAreaByCity,

	// �ֵ�
	getDictionary,
	// ��������
	getDepartmentType,

	// ȡ���ҵ�ʡ��ID������
	getProvinceByHosDeptZXType,

	// ȡ����ID������
	getCityByHosDeptProvinceZXType,

	// ��ѯҽ����Դͳ��
	queryResourcesCount,
	// ����ʡ��GBCode��ѯʡ��ID
	queryProvinceIdByGBCode,
	// ���ݳ���GBCode��ѯ����ID
	queryCityIdByGBCode
}
