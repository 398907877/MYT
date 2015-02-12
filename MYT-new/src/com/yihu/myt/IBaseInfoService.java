package com.yihu.myt;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.yihu.myt.service.BaseInfoService;

@By(BaseInfoService.class)
public interface IBaseInfoService {

	/**
	 * ��ȡ���ұ�׼����С����
	 * 
	 * @param bigDeptId
	 *            ��׼���Ҵ����ID
	 * @return
	 */
	public JSONObject getSmallStdDept(int bigDeptId);

	/**
	 * ���ݻ���ID��ȡ��ҽ���Ŀ���
	 * 
	 * @param orgId
	 * @return
	 */
	public JSONObject getDeptByOrg(int orgId);
	
	/**
	 * ��ȡ������ָ�����ҵ�ҽ��
	 * @param orgId
	 * @param deptId
	 * @return
	 */
	public JSONObject getDoctorByOrgAndDept(int orgId, String deptId);
}
