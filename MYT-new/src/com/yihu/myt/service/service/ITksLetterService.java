package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.TksLetterVo;import com.yihu.myt.service.service.impl.TksLetterService;
@By(TksLetterService.class)
public interface ITksLetterService{
	/**	*获取列表记录数	*/	public Integer queryTksLetterCountByCondition(TksLetterVo vo) throws Exception;
	/**	*获取列表	*/	public List<TksLetterVo> queryTksLetterListByCondition(TksLetterVo vo) throws Exception;
	/**	*添加	*/	public void insertTksLetter(TksLetterVo vo) throws Exception;
	/**	*修改	*/	public void updateTksLetterByCondition(TksLetterVo vo,JdbcConnection conn) throws Exception;
}