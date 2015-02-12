package com.yihu.myt.service.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ReturnValue;
import com.yihu.baseinfo.vo.AreaVo;
import com.yihu.baseinfo.vo.BigDepartmentVo;
import com.yihu.baseinfo.vo.CityVo;
import com.yihu.baseinfo.vo.CityVo_V2;
import com.yihu.baseinfo.vo.ProvinceVo;
import com.yihu.baseinfo.vo.ProvinceVo_V2;
import com.yihu.baseinfo.vo.SmallDepartmentVo;
import com.yihu.baseinfo.vo.SmallDepartmentVo_V2;
import com.yihu.myt.enums.CommonSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.service.service.ICommonService;
import com.yihu.myt.util.StringUtil;

public class CommonService implements ICommonService {

	public static void main(String[] args) throws Exception {
		CommonService cs = new CommonService();
		List<ProvinceVo_V2> list = cs.getProvinceByPhone();
	}
	
	/**
	 * 查询 科室的一级国家标准科室SN
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<BigDepartmentVo> getBigDepartmentSnByCondition(Map<String, Object> map) throws SQLException {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getBigDepartmentSnByCondition);
		StringBuilder sb = new StringBuilder();
		if (map.get("hospitalId") != null) {
			sb.append(" and a.hospitalId = " + map.get("hospitalId"));
		}

		if (map.get("doctorTextZX") != null) {
			sb.append(" and SUBSTRING(c.doctorService,3,1) = '1'");
		}
		if (map.get("deptZX") != null) {
			sb.append(" and a.ZXType = 1");
		}
		if (map.get("cityId") != null) {
			sb.append(" and d.cityId = " + map.get("cityId"));
		}
		sql.addVar("@condition", sb.toString());
		List<BigDepartmentVo> list = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, sql, BigDepartmentVo.class);
		return list;
	}

	/**
	 * 根据条件查询大科室
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BigDepartmentVo> getBigDepartmentByCon(BigDepartmentVo vo) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getBigDepartmentByCondition);

		StringBuilder sb = new StringBuilder();
		sql.addVar("@condition", sb.toString());
		List<BigDepartmentVo> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, BigDepartmentVo.class);
		// 返回组装后json数据
		return lst;
	}

	/**
	 * 获取标准科室小类
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public ReturnValue getSmallDepartment(int parentId) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getSmallDepartment);
		sql.addParamValue(parentId);
		List<Object[]> lst = DB.me().queryForList(MyDatabaseEnum.boss, sql);
		JSONArray jsons = new JSONArray();
		// 添加默认值
		JSONObject jsonDefault = new JSONObject();
		jsonDefault.put("deptsn", "");
		jsonDefault.put("deptname", "--请选择--");
		jsonDefault.put("selected", true);
		jsons.put(jsonDefault);
		// 添加数据库查询记录
		for (Object[] objs : lst) {
			JSONObject json = new JSONObject();
			json.put("deptsn", objs[0]);
			json.put("deptname", objs[1]);
			jsons.put(json);
		}
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", jsons);
		// 返回组装后json数据
		return new ReturnValue(1, jsonResult.toString());
	}

	/**
	 * 根据条件获取标准科室
	 * 
	 * @param departmentVo
	 * @return
	 * @throws SQLException
	 */
	public List<SmallDepartmentVo> getSmallDepartmentListByCond(SmallDepartmentVo vo) throws SQLException {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getSmallDepartmentByCondition);
		StringBuilder condition = new StringBuilder();
		if (StringUtil.isNotEmpty(vo.getDeptId())) {
			condition.append(" and a.deptid = ?");
			sql.addParamValue(vo.getDeptId());
		}
		if (StringUtil.isNotEmpty(vo.getParentId())) {
			condition.append(" and ParentId = ?");
			sql.addParamValue(vo.getParentId());
		}
		sql.addVar("@condition", condition.toString());

		List<SmallDepartmentVo> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, SmallDepartmentVo.class);
		return lst;
	}

	/**
	 * 根据条件获取标准科室
	 * 
	 * @param departmentVo
	 * @return
	 * @throws SQLException
	 */
	public List<SmallDepartmentVo_V2> getSmallDepartmentListByCond(SmallDepartmentVo_V2 vo) throws SQLException {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getSmallDepartmentByCondition);
		StringBuilder condition = new StringBuilder();
		if (StringUtil.isNotEmpty(vo.getDeptIds())) {
			condition.append(" and a.deptid in (" + vo.getDeptIds() + ")");
		}
		if (StringUtil.isNotEmpty(vo.getDeptId())) {
			condition.append(" and a.deptid = ?");
			sql.addParamValue(vo.getDeptId());
		}
		if (StringUtil.isNotEmpty(vo.getParentId())) {
			condition.append(" and ParentId = ?");
			sql.addParamValue(vo.getParentId());
		}
		sql.addVar("@condition", condition.toString());

		List<SmallDepartmentVo_V2> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, SmallDepartmentVo_V2.class);
		return lst;
	}

	/**
	 * 获取有合作医院的省份
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public List<ProvinceVo_V2> getProvinceByHos(int start, int pageSize) throws Exception {
		Sql sql = DB.me().createSql(HosSqLNameEnum.getHosProvince);
		List<Object[]> lists = DB.me().queryForList(MyDatabaseEnum.BaseInfo, sql);

		StringBuilder provinceids = new StringBuilder();
		if (lists != null && lists.size() > 0) {
			for (Object[] objects : lists) {
				provinceids.append(objects[0]).append(",");
			}
		}

		sql = DB.me().createSql(CommonSqlNameEnum.getProvinceList);
		if (provinceids.length() > 0) {
			provinceids.deleteCharAt(provinceids.length() - 1);
			sql.addVar("@condition", " and provinceid in (" + provinceids.toString() + ")");
		} else {
			sql.addVar("@condition", "");
		}
		List<ProvinceVo_V2> lst = null;
		if (start != 0 || pageSize != 0) {
			lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, ProvinceVo_V2.class, start, pageSize);
		} else {
			lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, ProvinceVo_V2.class);
		}
		return lst;
	}*/

	/**
	 * 返回有开通电话资询的省份
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<ProvinceVo_V2> getProvinceByPhone() throws Exception {
		// 查询医生科室信息
		Sql sql = new Sql("SELECT DISTINCT ProvinceID FROM dbo.V_DoctorInfo_View WHERE  SUBSTRING(doctorService,2,1) = '1'");
		List<Object[]> list = DB.me().queryForList(MyDatabaseEnum.BaseInfo, sql);
		StringBuilder provinceids = new StringBuilder();
		for (Object[] objs : list) {
			provinceids.append(objs[0]).append(",");
		}
		

		sql = DB.me().createSql(CommonSqlNameEnum.getProvinceList);
		if (provinceids.length() > 0) {
			provinceids.deleteCharAt(provinceids.length() - 1);// 去除最后的一个逗号
			sql.addVar("@condition", " and provinceid in (" + provinceids.toString() + ")");
		} else {
			return null;
		}
		List<ProvinceVo_V2> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, ProvinceVo_V2.class);
		return lst;
	}

	/**
	 * 根据省份ID获取所有省份城市
	 * 
	 * @throws Exception
	 */
	public List<CityVo_V2> getCityByProvince(int provoinceId, int start, int pageSize) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getCityByProvince);
		sql.addParamValue(provoinceId);

		List<CityVo_V2> lst = null;
		if (start != 0 || pageSize != 0) {
			lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, CityVo_V2.class);
		} else {
			lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, CityVo_V2.class, start, pageSize);
		}
		return lst;
	}
	
	/**
	 * 根据城市 ID获取所有城市GBcode
	 * 
	 * @throws Exception
	 */
	public String getGBCodeByCityId(int cityId) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getGBCodeByCityId);
		sql.addParamValue(cityId);

		String lst = DB.me().queryForString(MyDatabaseEnum.boss, sql);
		return lst;
	}

	/**
	 * 根据城市获取市区
	 * 
	 * @param status
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public List<AreaVo> getAreaByCity(int city) throws Exception {

		Sql sql = DB.me().createSql(CommonSqlNameEnum.getAreaByCity);
		sql.addParamValue(new Integer(city));
		List<AreaVo> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, AreaVo.class);
		return lst;
	}

	/**
	 * 取字典的值
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public String getBusinName(String businTypeId, String businId) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getDictionary);
		sql.addVar("@condition", " and busintypeid = ? and businid = ?");
		sql.addParamValue(businTypeId);
		sql.addParamValue(businId);
		Map<String, Object> mapinfo = DB.me().queryForMap(MyDatabaseEnum.boss, sql);
		if (mapinfo != null) {
			return StringUtil.isEmpty(mapinfo.get("businname")) ? "" : mapinfo.get("businname").toString();
		} else {
			return "";
		}
	}

	/**
	 * 取字典的值
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getBusinName(String businTypeId) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getDictionary);
		sql.addVar("@condition", " and busintypeid = ?");
		sql.addParamValue(businTypeId);
		List<Map<String, Object>> list = DB.me().queryForMapList(MyDatabaseEnum.boss, sql);
		return list;
	}

	/**
	 * 根据科室是否开放咨询查询省份ID及名称
	 * 
	 * @throws Exception
	 */
	public List<ProvinceVo> getProvinceByHosDeptZXType() throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getProvinceByHosDeptZXType);
		List<ProvinceVo> lst = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, sql, ProvinceVo.class);
		return lst;
	}

	/**
	 * 根据科室是否开放和省份ID查询城市ID及名称
	 * 
	 * @param provinceId
	 * @throws Exception
	 */
	public List<CityVo> getCityByHosDeptProvinceZXType(int provinceId) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getCityByHosDeptProvinceZXType);
		sql.addParamValue(provinceId);
		List<CityVo> lst = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, sql, CityVo.class);
		return lst;
	}

	/**
	 * 查询医疗资源统计
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryResourcesCount() throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.queryResourcesCount);
		return DB.me().queryForMapList(MyDatabaseEnum.BaseInfo, sql);
	}

	/**
	 * 根据省份GBCode查询省份ID
	 * 
	 * @param GBCode
	 * @return
	 */
	public Integer queryProvinceIdByGBCode(int GBCode) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.queryProvinceIdByGBCode);
		sql.addParamValue(GBCode);
		int list = DB.me().queryForInteger(MyDatabaseEnum.BaseInfo, sql);
		return list;
	}

	/**
	 * 根据城市GBCode查询城市ID
	 * 
	 * @param GBCode
	 * @return
	 */
	public Integer queryCityIdByGBCode(int GBCode) throws Exception {
		Sql sql = DB.me().createSql(CommonSqlNameEnum.queryCityIdByGBCode);
		sql.addParamValue(GBCode);
		int list = DB.me().queryForInteger(MyDatabaseEnum.BaseInfo, sql);
		return list;
	}

	/*@Override
	public List<ProvinceVo_V2> getProvinceByHos(int start, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<SmallDepartmentVo> getSmallDepartmentListByCond(
			SmallDepartmentVo vo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmallDepartmentVo_V2> getSmallDepartmentListByCond(
			SmallDepartmentVo_V2 vo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}*/
}
