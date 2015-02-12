package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsumerOrders2Vo;import com.yihu.myt.service.service.impl.ConsumerOrders2Service;
@By(ConsumerOrders2Service.class)
public interface IConsumerOrders2Service{
	/**	*��ȡ�б��¼��	*/	public Integer queryConsumerOrders2CountByCondition(ConsumerOrders2Vo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConsumerOrders2Vo> queryConsumerOrders2ListByCondition(ConsumerOrders2Vo vo) throws Exception;
	/**	*���	*/	public void insertConsumerOrders2(ConsumerOrders2Vo vo) throws Exception;
	/**	*�޸�	*/	public void updateConsumerOrders2ByCondition(ConsumerOrders2Vo vo,JdbcConnection conn) throws Exception;
}