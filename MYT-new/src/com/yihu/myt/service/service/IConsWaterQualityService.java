package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsWaterQualityVo;import com.yihu.myt.service.service.impl.ConsWaterQualityService;
@By(ConsWaterQualityService.class)
public interface IConsWaterQualityService{
	/**	*获取列表记录数	*/	public Integer queryConsWaterQualityCountByCondition(ConsWaterQualityVo vo) throws Exception;
	/**	*获取列表	*/	public List<ConsWaterQualityVo> queryConsWaterQualityListByCondition(ConsWaterQualityVo vo) throws Exception;	public ConsWaterQualityVo  queryConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
	/**	*添加	*/	public void insertConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
	/**	*修改	*/	public void updateConsWaterQuality(ConsWaterQualityVo vo) throws Exception;
}