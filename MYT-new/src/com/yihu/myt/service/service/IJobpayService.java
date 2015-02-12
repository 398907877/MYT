package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.JobpayVo;import com.yihu.myt.service.service.impl.JobpayService;
@By(JobpayService.class)
public interface IJobpayService{
	/**	*��ȡ�б��¼��	*/	public Integer queryJobpayCountByCondition(JobpayVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<JobpayVo> queryJobpayListByCondition(JobpayVo vo) throws Exception;
	/**	*���	*/	public void insertJobpay(JobpayVo vo) throws Exception;
	/**	*�޸�	*/	public void updateJobpayByCondition(JobpayVo vo,JdbcConnection conn) throws Exception;
}