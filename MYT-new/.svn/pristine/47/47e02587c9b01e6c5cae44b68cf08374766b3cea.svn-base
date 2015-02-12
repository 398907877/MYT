package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsumerOrdersVo;import com.yihu.myt.service.service.impl.ConsumerOrdersService;
@By(ConsumerOrdersService.class)
public interface IConsumerOrdersService{
	/**	*获取列表记录数	*/	public Integer queryConsumerOrdersCountByCondition(ConsumerOrdersVo vo) throws Exception;
	/**	*获取列表	*/	public List<ConsumerOrdersVo> queryConsumerOrdersListByCondition(ConsumerOrdersVo vo) throws Exception;	/**	*根据问题ID获取订单数据	*/	public ConsumerOrdersVo queryConsumerOrdersByQuesID(			ConsumerOrdersVo vo) throws Exception ;
	/**	*添加	*/	public int insertConsumerOrders(ConsumerOrdersVo vo) throws Exception;
	/**	*修改	*/	public void updateConsumerOrdersByCondition(ConsumerOrdersVo vo,JdbcConnection conn) throws Exception;	public int updateConsumerOrdersByCondition(ConsumerOrdersVo vo) throws Exception;	public int updateCOrdersByCondition(ConsumerOrdersVo vo,JdbcConnection conn) throws Exception;	public int updateCOrdersByCondition(ConsumerOrdersVo vo) throws Exception;}