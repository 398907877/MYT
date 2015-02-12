package com.yihu.myt;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.yihu.myt.service.BaseInfoService;

@By(BaseInfoService.class)
public interface IBaseInfoService {

	/**
	 * 获取国家标准科室小科室
	 * 
	 * @param bigDeptId
	 *            标准科室大科室ID
	 * @return
	 */
	public JSONObject getSmallStdDept(int bigDeptId);

	/**
	 * 根据机构ID获取有医生的科室
	 * 
	 * @param orgId
	 * @return
	 */
	public JSONObject getDeptByOrg(int orgId);
	
	/**
	 * 获取机构下指定科室的医生
	 * @param orgId
	 * @param deptId
	 * @return
	 */
	public JSONObject getDoctorByOrgAndDept(int orgId, String deptId);
}
