package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DiseaseClickVo;import com.yihu.myt.service.service.impl.DiseaseClickService;
@By(DiseaseClickService.class)
public interface IDiseaseClickService{
	/**	*获取列表记录数	*/	public Integer queryDiseaseClickCountByCondition(DiseaseClickVo vo) throws Exception;
	/**	*获取列表	*/	public List<DiseaseClickVo> queryDiseaseClickListByCondition(DiseaseClickVo vo) throws Exception;
	/**	*添加	*/	public void insertDiseaseClick(DiseaseClickVo vo) throws Exception;
	/**	*修改	*/	public void updateDiseaseClickByCondition(DiseaseClickVo vo,JdbcConnection conn) throws Exception;
}