package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsWaterQualityVo;import com.yihu.myt.service.service.impl.ConsWaterQualityService;
@By(ConsWaterQualityService.class)
public interface IConsWaterQualityService{
	/**	*��ȡ�б��¼��	*/	public Integer queryConsWaterQualityCountByCondition(ConsWaterQualityVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConsWaterQualityVo> queryConsWaterQualityListByCondition(ConsWaterQualityVo vo) throws Exception;	public ConsWaterQualityVo  queryConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
	/**	*���	*/	public void insertConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
	/**	*�޸�	*/	public void updateConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
}