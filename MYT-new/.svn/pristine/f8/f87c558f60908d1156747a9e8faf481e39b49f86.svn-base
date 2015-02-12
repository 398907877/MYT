package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DictionaryVo;import com.yihu.myt.service.service.impl.DictionaryService;
@By(DictionaryService.class)
public interface IDictionaryService{
	/**	*获取列表记录数	*/	public Integer queryDictionaryCountByCondition(DictionaryVo vo) throws Exception;
	/**	*获取列表	*/	public List<DictionaryVo> queryDictionaryListByCondition(DictionaryVo vo) throws Exception;
	/**	*添加	*/	public void insertDictionary(DictionaryVo vo) throws Exception;
	/**	*修改	*/	public void updateDictionaryByCondition(DictionaryVo vo,JdbcConnection conn) throws Exception;
}