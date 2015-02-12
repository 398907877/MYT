package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DiseaseVo;import com.yihu.myt.service.service.impl.DiseaseService;
@By(DiseaseService.class)
public interface IDiseaseService{
	/**	*获取列表记录数	*/	public Integer queryDiseaseCountByCondition(DiseaseVo vo) throws Exception;
	/**	*获取列表	*/	public List<DiseaseVo> queryDiseaseListByCondition(DiseaseVo vo) throws Exception;
	/**	*添加	*/	public int insertDisease(DiseaseVo vo) throws Exception;	public int deleteDiseases(DiseaseVo vo) throws Exception;
	/**	*修改	*/	public void updateDiseaseByCondition(DiseaseVo vo,JdbcConnection conn) throws Exception;	public int updateDiseaseForAskQueID(DiseaseVo vo) throws Exception;	
}