package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.TksLetterVo;import com.yihu.myt.service.service.impl.TksLetterService;
@By(TksLetterService.class)
public interface ITksLetterService{
	/**	*��ȡ�б��¼��	*/	public Integer queryTksLetterCountByCondition(TksLetterVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<TksLetterVo> queryTksLetterListByCondition(TksLetterVo vo) throws Exception;
	/**	*���	*/	public void insertTksLetter(TksLetterVo vo) throws Exception;
	/**	*�޸�	*/	public void updateTksLetterByCondition(TksLetterVo vo,JdbcConnection conn) throws Exception;
}