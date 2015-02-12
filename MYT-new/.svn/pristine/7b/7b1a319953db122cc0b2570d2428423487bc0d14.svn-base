package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.TksLetterTempVo;import com.yihu.myt.service.service.impl.TksLetterTempService;
@By(TksLetterTempService.class)
public interface ITksLetterTempService{
	/**	*获取列表记录数	*/	public Integer queryTksLetterTempCountByCondition(TksLetterTempVo vo) throws Exception;
	/**	*获取列表	*/	public List<TksLetterTempVo> queryTksLetterTempListByCondition(TksLetterTempVo vo) throws Exception;
	/**	*添加	*/	public void insertTksLetterTemp(TksLetterTempVo vo) throws Exception;
	/**	*修改	*/	public void updateTksLetterTempByCondition(TksLetterTempVo vo,JdbcConnection conn) throws Exception;
}