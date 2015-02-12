package com.yihu.myt.service.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.yihu.baseinfo.vo.AreaVo;
import com.yihu.baseinfo.vo.BigDepartmentVo;
import com.yihu.baseinfo.vo.CityVo;
import com.yihu.baseinfo.vo.CityVo_V2;
import com.yihu.baseinfo.vo.ProvinceVo;
import com.yihu.baseinfo.vo.ProvinceVo_V2;
import com.yihu.baseinfo.vo.SmallDepartmentVo;
import com.yihu.baseinfo.vo.SmallDepartmentVo_V2;
import com.yihu.myt.service.service.impl.CommonService;

@By(CommonService.class)
public interface ICommonService {

	/**
	 * 根据条件查询大科室
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BigDepartmentVo> getBigDepartmentByCon(BigDepartmentVo vo) throws Exception;

	/**
	 * 查询 科室的一级国家标准科室SN
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<BigDepartmentVo> getBigDepartmentSnByCondition(Map<String, Object> map) throws SQLException;

	/**
	 * 获取标准科室小类
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public ReturnValue getSmallDepartment(int parentId) throws Exception;

	/**
	 * 根据条件获取标准科室
	 * 
	 * @param departmentVo
	 * @return
	 * @throws SQLException
	 */
	public List<SmallDepartmentVo> getSmallDepartmentListByCond(SmallDepartmentVo vo) throws SQLException;

	/**
	 * 根据条件获取标准科室
	 * 
	 * @param departmentVo
	 * @return
	 * @throws SQLException
	 */
	public List<SmallDepartmentVo_V2> getSmallDepartmentListByCond(SmallDepartmentVo_V2 vo) throws SQLException;

/*	*//**
	 * 获取有合作医院的省份
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public List<ProvinceVo_V2> getProvinceByHos(int start, int pageSize) throws Exception;
*/
	/**
	 * 返回有开通电话资询的省份
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<ProvinceVo_V2> getProvinceByPhone() throws Exception;

	/**
	 * 根据省份ID获取所有省份城市
	 * 
	 * @throws Exception
	 */
	public List<CityVo_V2> getCityByProvince(int provoinceId, int start, int pageSize) throws Exception;

	/**
	 * 根据城市获取市区
	 * 
	 * @param cityId
	 * @return
	 */
	public List<AreaVo> getAreaByCity(int cityId) throws Exception;

	/**
	 * 取字典的值
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public String getBusinName(String businTypeId, String businId) throws Exception;

	/**
	 * 取字典的值
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getBusinName(String businTypeId) throws Exception;

	/**
	 * 根据科室是否开放咨询查询省份ID及名称
	 * 
	 * @throws Exception
	 */
	public List<ProvinceVo> getProvinceByHosDeptZXType() throws Exception;

	/**
	 * 根据科室是否开放和省份ID查询城市ID及名称
	 * 
	 * @throws Exception
	 */
	public List<CityVo> getCityByHosDeptProvinceZXType(int provinceId) throws Exception;

	/**
	 * 查询医疗资源统计
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryResourcesCount() throws Exception;

	/**
	 * 根据省份GBCode查询省份ID
	 * 
	 * @param GBCode
	 * @return
	 */
	public Integer queryProvinceIdByGBCode(int GBCode) throws Exception;

	/**
	 * 根据城市GBCode查询城市ID
	 * 
	 * @param GBCode
	 * @return
	 */
	public Integer queryCityIdByGBCode(int GBCode) throws Exception;

	/**
	 * 根据城市 ID获取所有城市GBcode
	 * 
	 * @throws Exception
	 */
	public String getGBCodeByCityId(int cityId) throws Exception;
}
