package com.yihu.myt;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.yihu.myt.service.FaultDealService;
import com.yihu.myt.vo.MytConswaterBean;

/**
 * 名医通咨询流水操作
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:31
 */
@By(FaultDealService.class)
public interface IFaultDealService {

	/**
	 * 修正名医通咨询流水
	 * @param pkconswaterid
	 * @param realcard
	 * @param remark
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	public ReturnValue reviseMytConswater(int pkconswaterid, String realcard, String remark, int operatorId,
		String operatorName);
	
	/**
	 * 撤销名医通咨询流水
	 * @param water
	 * @return
	 */
	public ReturnValue repealMytConswater(MytConswaterBean water);
	
	/**
	 * 处理名医通异常流水
	 * @param pkconswaterid
	 * @param startTime
	 * @param endTime
	 * @param remark
	 * @param operatorId
	 * @param operatorName
	 * @return
	 */
	public ReturnValue dealMytConswater(int pkconswaterid, String startTime, String endTime,
		String remark, int operatorId, String operatorName);
}
