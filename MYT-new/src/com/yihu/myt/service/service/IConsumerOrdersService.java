package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsumerOrdersVo;import com.yihu.myt.service.service.impl.ConsumerOrdersService;
@By(ConsumerOrdersService.class)
public interface IConsumerOrdersService{
	/**	*��ȡ�б��¼��	*/	public Integer queryConsumerOrdersCountByCondition(ConsumerOrdersVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConsumerOrdersVo> queryConsumerOrdersListByCondition(ConsumerOrdersVo vo) throws Exception;	/**	*��������ID��ȡ��������	*/	public ConsumerOrdersVo queryConsumerOrdersByQuesID(			ConsumerOrdersVo vo) throws Exception ;
	/**	*���	*/	public int insertConsumerOrders(ConsumerOrdersVo vo) throws Exception;
	/**	*�޸�	*/	public void updateConsumerOrdersByCondition(ConsumerOrdersVo vo,JdbcConnection conn) throws Exception;	public int updateConsumerOrdersByCondition(ConsumerOrdersVo vo) throws Exception;	public int updateCOrdersByCondition(ConsumerOrdersVo vo,JdbcConnection conn) throws Exception;	public int updateCOrdersByCondition(ConsumerOrdersVo vo) throws Exception;}