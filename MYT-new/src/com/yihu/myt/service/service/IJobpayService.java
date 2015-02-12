package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.JobpayVo;import com.yihu.myt.service.service.impl.JobpayService;
@By(JobpayService.class)
public interface IJobpayService{
	/**	*获取列表记录数	*/	public Integer queryJobpayCountByCondition(JobpayVo vo) throws Exception;
	/**	*获取列表	*/	public List<JobpayVo> queryJobpayListByCondition(JobpayVo vo) throws Exception;
	/**	*添加	*/	public void insertJobpay(JobpayVo vo) throws Exception;
	/**	*修改	*/	public void updateJobpayByCondition(JobpayVo vo,JdbcConnection conn) throws Exception;
}