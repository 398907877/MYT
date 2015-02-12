package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.WebLettersVo;import com.yihu.myt.service.service.impl.WebLettersService;
@By(WebLettersService.class)
public interface IWebLettersService{
	/**	*获取列表记录数	*/	public Integer queryWebLettersCountByCondition(WebLettersVo vo) throws Exception;
	/**	*获取列表	*/	public List<WebLettersVo> queryWebLettersListByCondition(WebLettersVo vo) throws Exception;
	/**	*添加	*/	public void insertWebLetters(WebLettersVo vo) throws Exception;
	/**	*修改	*/	public void updateWebLettersByCondition(WebLettersVo vo,JdbcConnection conn) throws Exception;
}