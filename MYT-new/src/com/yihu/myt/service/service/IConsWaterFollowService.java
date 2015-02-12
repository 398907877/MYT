package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsWaterFollowVo;import com.yihu.myt.service.service.impl.ConsWaterFollowService;
@By(ConsWaterFollowService.class)
public interface IConsWaterFollowService{
	/**	*��ȡ�б��¼��	*/	public Integer queryConsWaterFollowCountByCondition(ConsWaterFollowVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConsWaterFollowVo> queryConsWaterFollowListByCondition(ConsWaterFollowVo vo) throws Exception;	public ConsWaterFollowVo queryConsWaterFollow(ConsWaterFollowVo vo ) throws Exception ;
	/**	*���	*/	public void insertConsWaterFollow(ConsWaterFollowVo vo) throws Exception;
	/**	*�޸�	*/	public void updateConsWaterFollow(ConsWaterFollowVo vo) throws Exception;
}