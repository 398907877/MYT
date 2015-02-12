package com.yihu.myt;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.yihu.myt.service.ChargeService;

/**
 * 
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:51
 */
@By(ChargeService.class)
public interface IChargeService {

	/**
	 * 不结算医生当前待结算帐单
	 * @param doctorIds	医生编号集   格式：'$$','$$'
	 * @return
	 */
	public ReturnValue unsettled(String doctorIds);
	
	/**
	 * 已结算医生当前帐单
	 * @param doctorIds	医生编号集   格式：'$$','$$'
	 * @return
	 */
	public ReturnValue settled(String doctorIds);
}
