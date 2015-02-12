package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsumerOrders2Vo;import com.yihu.myt.service.service.impl.ConsumerOrders2Service;
@By(ConsumerOrders2Service.class)
public interface IConsumerOrders2Service{
	/**	*获取列表记录数	*/	public Integer queryConsumerOrders2CountByCondition(ConsumerOrders2Vo vo) throws Exception;
	/**	*获取列表	*/	public List<ConsumerOrders2Vo> queryConsumerOrders2ListByCondition(ConsumerOrders2Vo vo) throws Exception;
	/**	*添加	*/	public void insertConsumerOrders2(ConsumerOrders2Vo vo) throws Exception;
	/**	*修改	*/	public void updateConsumerOrders2ByCondition(ConsumerOrders2Vo vo,JdbcConnection conn) throws Exception;
}