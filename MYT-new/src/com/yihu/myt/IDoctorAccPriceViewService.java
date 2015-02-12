package com.yihu.myt;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.yihu.myt.service.DoctorAccPriceViewService;


@By(DoctorAccPriceViewService.class)
public interface IDoctorAccPriceViewService {
	/**
	 * ��ҽͨ��ȡ�շ���Ŀ����
	 * @author cy
	 * @company yihu.com
	 * 2013-9-22����11:06:14
	 */
	public JSONObject getBill(Integer resUid, Integer serviceId,Integer resId);
	/**
	 * ��ҽͨ��ȡ�ʷѹ���
	 * @author cy
	 * @company yihu.com
	 * 2013-9-22����11:06:14
	 */
	public JSONObject getDoctorBill(Integer resUid, Integer serviceId,Integer resId,Integer time);
	/**
	 * ��ҽͨ��ȡ�շ���Ŀ����
	 * @author cy
	 * @company yihu.com
	 * 2013-9-22����11:06:14
	 */
	public JSONObject changeBilling(Integer resUid, Integer serviceId);
}
