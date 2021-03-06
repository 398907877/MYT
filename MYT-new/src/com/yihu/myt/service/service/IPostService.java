package com.yihu.myt.service.service;

import com.coreframework.ioc.Impl;
@Impl("com.yihu.myt.service.service.impl.PostService")
public interface IPostService {
	public String queryComplexDoctorList(String param);
	//用户鉴权
	public String userJq(String Cellphone,String CityCode);
	
	//用户信息添加
	public String addUserInfo(String UserId,String UserName,String UserSex,String UserCardId,String UserPhone);
	public String queryFeeTemplateId(String param);
	//用户信息查询
	public String findUserInfo(String UserId);
	
	//用户添加家庭成员
	public String userAddFamliy(String UserId,String MemberName,String MemberSex,String MemberCardId,String MemberPhone,String MemberRelation,String MemberAddress,String MemberEmail);
	
	//用户查询家庭成员
	public String userFindFamliy(String UserId);
	
	//修改用户信息
	public String updateUserInfo(String UserId,String UserName,String UserSex,String UserCardId,String UserPhone);
	
	//修改用户家庭成员
	public String updateUserFamliy(String MemberId,String MemberName,String MemberSex,String MemberCardId,String MemberPhone,String MemberRelation,String MemberAddress,String MemberEmail);
	
	
	//得到医院信息
	public String getHospital(String GUID,String AreaId,String Keyword,String CurrentPage,String PageSize);
	
	//根据关键字查询科室信息
	public String getdeptbygjz(String GUID,String AreaId,String Keyword,String CurrentPage,String PageSize);
	
	//根据医院编码查询科室信息
	public String getDeptByHospitalNo(String GUID,String HospitalId,String CPId,String CurrentPage,String PageSize);
	
	//根据关键字查询医生信息
	public String getDoctorBygjz(String GUID,String AreaId,String Keyword,String CurrentPage,String PageSize);
	
	//根据科室编码查询医生信息
	public String getDoctorByDeptNo(String GUID,String HospitalId,String DeptId,String CPId,String CurrentPage,String PageSize);
	
	//查询医生排班信息
	public String getDcotorInfo(String GUID,String HospitalId,String DeptId,String DocId,String CPId);
	
	//查询医生可用号源信息
	public String getdoctorSyHy(String GUID,String SchemeId,String CPId);
	
/*	//预约挂号登记
	public String Yygh(userInfo info);*/
	
	//取消预约挂号
	public String qxgh(String OrderId,String CPId);
	
	//用户预约详单查询
	public String regWater(String UserId,String SortBy,String CurrentPage,String PageSize);
	
	//删除家庭成员
	public String delFamliy(String UserId,String MemberId);
	
	//用户医生咨询判断
	public String getMytUserCount(String userPhone);
	//用户医生咨询计数
	String changeMytUserCount(String userPhone);

	String getDoctorDiseaseListTest(String doctorUid);

	String getDoctorDiseaseList(String doctorUid);


	String telecounSelingUpdate(String type, String code);
	//用户医生咨询计数

	public String queryComplexDoctorList_v2(String param);
	//二级科室 查询一级
	public String getBigAndSmallDepartmentBySmallDepartmentId(String param);

	String sendMsg(String param);

	String sendMsgJ(String param);


	String getResourceCount(String Serviceproviderid, String Cardid,
			String Productno, String Feeno);


	String charge(String Cardid, String Feesn, String Relateid,
			String Operatorid, String Operatorname, String Relatetype,
			String Ispermitowe, String Remark, String Cash, String Resource,
			String Serviceproviderid,String OutType, String ParamType, String AuthInfo);

	String frozenWswyFee(String Accountsn, String Serviceid, String Frozenfee,
			String Productno, String Feeno, String Feename, String Relatesign,
			String Operatorid, String Operatorname, String Serviceproviderid,
			String OutType, String ParamType, String AuthInfo);

	String insertBill(String param);
	String upfile(String param,String filename);
	String addDoctorLove(String param,String AuthInfo);
 
}
