package com.yihu.myt.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.baseinfo.vo.AreaVo;
import com.yihu.baseinfo.vo.BigDepartmentVo;
import com.yihu.baseinfo.vo.CityVo_V2;
import com.yihu.baseinfo.vo.ProvinceVo_V2;
import com.yihu.baseinfo.vo.SmallDepartmentVo_V2;
import com.yihu.myt.enums.CommonSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.service.service.impl.CommonService;

/**
 * 初始化一些数据库数据。
 * 
 * @author wengsb
 * @company yihu.com 2013年11月26日上午9:53:00
 */
public class DBCache {

	public static void main(String[] args) throws Exception {
		DBCache.initCacheByDB();
		System.out.println(DBCache.smallDepartmentListBySn.size());
	}
	
	// 省份简称
	public static Map<Integer, String> provinceShort = new HashMap<Integer, String>(); // 省份简称
	public static Map<String, String> bigDepartmentBySn = new HashMap<String, String>(); // 国家一级科室名称
	public static Map<String, String> bigDepartmentByDeptId = new HashMap<String, String>(); // 国家一级科室名称
	public static Map<String, String> bigDepartmentDeptIdBySn = new HashMap<String, String>(); // 国家一级科室SN,DeptId
	public static Map<String, String> bigDepartmentPicBySn = new HashMap<String, String>(); // 国家一级科室图片
	public static Map<String, String> smallDepartment = new HashMap<String, String>(); // 国家小科室名称
	public static Map<String, String> smallDepartmentBigDepartmentTranslation = new HashMap<String, String>(); // 小科室与大科室关联
	public static Map<String, Map<String, String>> smallDepartmentListBySn = new HashMap<String, Map<String, String>>(); // 国家小科室名称
	public static Map<String, String> hospitalLevel = new HashMap<String, String>(); // 医院等级
	public static Map<String, String> doctor_lczc = new HashMap<String, String>(); // 医生临床职称
	public static Map<String, List<Map<String, String>>> doctor_lczcByUserType = new HashMap<String, List<Map<String, String>>>(); // 人员类型对应临床职称
	public static Map<String, String> provinceMap = new HashMap<String, String>(); // 省份
	public static Map<String, String> cityMap = new HashMap<String, String>(); // 城市
	public static Map<String, String> areaMap = new HashMap<String, String>(); // 区县
	public static List<ProvinceVo_V2> provinceList = new ArrayList<ProvinceVo_V2>(); // 省份
	public static List<ProvinceVo_V2> provinceByHosList = new ArrayList<ProvinceVo_V2>(); // 有合作的省份
	public static List<ProvinceVo_V2> provinceByPhoneList = new ArrayList<ProvinceVo_V2>(); // 有开通名医咨询的省份
	public static Map<String, List<CityVo_V2>> cityListByProvince = new HashMap<String, List<CityVo_V2>>(); // 城市集合
	//
	/**
	 * 加载数据库记录进入map缓存
	 * 
	 * @throws SQLException
	 */
	public static void initCacheByDB() throws Exception {
		System.out.println("加载数据库数据进入应用缓存开始....");
		DBCache.initProvinceShort();
		DBCache.initBigDepartment();
		DBCache.initSmallDepartment();
		//DBCache.initDoctor_lczc();
		DBCache.initProvinceList();
		//DBCache.initProvinceByHosList();
		DBCache.initProvinceByPhoneList();
		DBCache.initSmallDepartmentBigDepartmentTranslation();
		//DBCache.initHospitalLevel();
		System.out.println("加载数据库数据进入应用缓存结束...");
	}

	/**
	 * 初始化省份简称
	 * 
	 * @throws SQLException
	 */
	private static void initProvinceShort() throws SQLException {
		provinceShort.clear();
		Sql sql = new Sql("select * from BASE_PROVINCE");
		List<Map<String, Object>> list = DB.me().queryForMapList(MyDatabaseEnum.BaseInfo, sql);
		for (Map<String, Object> map : list) {
			provinceShort.put(Integer.valueOf(map.get("provinceid").toString()), map.get("provinceshort").toString());
		}
	}

	/**
	 * 初始化国家大科室名称
	 * 
	 * @throws Exception
	 */
	private static void initBigDepartment() throws Exception {
		bigDepartmentBySn.clear();
		bigDepartmentByDeptId.clear();
		bigDepartmentDeptIdBySn.clear();
		bigDepartmentPicBySn.clear();
		smallDepartmentListBySn.clear();
		CommonService commonService = new CommonService();
		List<BigDepartmentVo> list = commonService.getBigDepartmentByCon(new BigDepartmentVo());
		for (BigDepartmentVo vo : list) {
			bigDepartmentBySn.put(vo.getDeptSn().toString(), vo.getName());
			bigDepartmentByDeptId.put(vo.getDeptId().toString(), vo.getName());
			bigDepartmentDeptIdBySn.put(vo.getDeptSn().toString(), vo.getDeptId());
			bigDepartmentPicBySn.put(vo.getDeptSn().toString(), vo.getDeptPic());
			
			SmallDepartmentVo_V2 voParam =  new SmallDepartmentVo_V2();
			voParam.setParentId(vo.getDeptSn());
			List<SmallDepartmentVo_V2> smallList = commonService.getSmallDepartmentListByCond(voParam);
			Map<String,String> _map = new HashMap<String, String>();
			for (SmallDepartmentVo_V2 vo2 : smallList) {
				_map.put(vo2.getDeptId().trim(), vo2.getName());
			}
			smallDepartmentListBySn.put(vo.getDeptSn().toString(), _map);
		}
	}

	/**
	 * 初始化国家小科室名称
	 * 
	 * @throws SQLException
	 */
	private static void initSmallDepartment() throws SQLException {
		smallDepartment.clear();
		CommonService commonService = new CommonService();
		List<SmallDepartmentVo_V2> list = commonService.getSmallDepartmentListByCond(new SmallDepartmentVo_V2());
		for (SmallDepartmentVo_V2 vo : list) {
			smallDepartment.put(vo.getDeptId().trim(), vo.getName());
		}
	}
	/**
	 * 初始化国家小科室与大科室的关联
	 * 
	 * @throws SQLException
	 */
	
	private static void initSmallDepartmentBigDepartmentTranslation() throws SQLException {
		smallDepartmentBigDepartmentTranslation.clear();
		CommonService commonService = new CommonService();
		List<SmallDepartmentVo_V2> list = commonService.getSmallDepartmentListByCond(new SmallDepartmentVo_V2());
		for (SmallDepartmentVo_V2 vo : list) {
			smallDepartmentBigDepartmentTranslation.put(vo.getDeptId().trim(), vo.getDeptSn().toString());
		}
	}
	
	/**
	 * 初始化医生临床职称
	 * 
	 * @throws Exception
	 *//*
	private static void initDoctor_lczc() throws Exception {
		doctor_lczc.clear();
		doctor_lczcByUserType.clear();
		CommonService commonService = new CommonService();
		List<Map<String, Object>> mapList = commonService.getBusinName(Constant.DOCTOR_USERTYPE);
		for (Map<String, Object> map : mapList) {
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			doctor_lczcByUserType.put(map.get("businid").toString(), list);
		}
		
		mapList = commonService.getBusinName(Constant.DOCTOR_CLINICLEVEL);
		for (Map<String, Object> map : mapList) {
			doctor_lczc.put(map.get("businid").toString(), map.get("businname").toString());
			Integer businid = Integer.valueOf(map.get("businid").toString());
			String tag = "9";
			if (businid < 60) {
				tag = "1";
			} else if (businid >= 60 && businid < 70) {
				tag = "2";
			} else if (businid >= 70 && businid < 80) {
				tag = "3";
			} else if (businid >= 80 && businid < 90) {
				tag = "4";
			} else {
				tag = "9";
			}
			Map<String, String> _map = new HashMap<String, String>(); 
			_map.put("businID",  map.get("businid").toString());
			_map.put("businName",  map.get("businname").toString());
			doctor_lczcByUserType.get(tag).add(_map);
		}
	}
*/
	/**
	 * 初始化省份
	 * 
	 * @throws Exception
	 */
	private static void initProvinceList() throws Exception {
		provinceList.clear();
		cityListByProvince.clear();
		provinceMap.clear();
		cityMap.clear();
		areaMap.clear();
		Sql sql = DB.me().createSql(CommonSqlNameEnum.getProvinceList);
		sql.addVar("@condition", "");
		List<ProvinceVo_V2> lst = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql, ProvinceVo_V2.class);
		provinceList = lst;
		CommonService commonService = new CommonService();
		for (ProvinceVo_V2 provinceVo : lst) {
			provinceMap.put(provinceVo.getProvinceId().toString(), provinceVo.getProvinceName());
			List<CityVo_V2> list = commonService.getCityByProvince(provinceVo.getProvinceId(), 0, 0);
			cityListByProvince.put(provinceVo.getProvinceId().toString(), list);
			for (CityVo_V2 cityVo : list) {
				cityMap.put(cityVo.getCityId().toString(), cityVo.getCityName());
				List<AreaVo> areaList = commonService.getAreaByCity(cityVo.getCityId());
				for (AreaVo areaVo : areaList) {
					areaMap.put(areaVo.getAreaId().toString(), areaVo.getAreaName());
				}
			}
		}
	}

/*	*//**
	 * 初始化有合作的省份
	 * 
	 * @throws Exception
	 *//*
	private static void initProvinceByHosList() throws Exception {
		provinceByHosList.clear();
		CommonService commonService = new CommonService();
		provinceByHosList = commonService.getProvinceByHos(0, 0);
	}
*/
	/**
	 * 初始化有开通名咨询的省份
	 * 
	 * @throws Exception
	 */
	private static void initProvinceByPhoneList() throws Exception {
		provinceByPhoneList.clear();
		CommonService commonService = new CommonService();
		provinceByPhoneList = commonService.getProvinceByPhone();
	}

	/**
	 * 初始化医院等级
	 * 
	 * @throws Exception
	 *//*
	private static void initHospitalLevel() throws Exception {
		hospitalLevel.clear();
		CommonService commonService = new CommonService();
		List<Map<String, Object>> mapList = commonService.getBusinName(Constant.HOSPITAL_LEVELID_NAME);
		for (Map<String, Object> map : mapList) {
			hospitalLevel.put(map.get("businid").toString(), map.get("businname").toString());
		}
	}
*/
}
