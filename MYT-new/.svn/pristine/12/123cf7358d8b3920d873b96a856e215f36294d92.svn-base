package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.WebLettersDetailVo;import com.yihu.myt.service.service.impl.WebLettersDetailService;
@By(WebLettersDetailService.class)
public interface IWebLettersDetailService{
	/**	*获取列表记录数	*/	public Integer queryWebLettersDetailCountByCondition(WebLettersDetailVo vo) throws Exception;
	/**	*获取列表	*/	public List<WebLettersDetailVo> queryWebLettersDetailListByCondition(WebLettersDetailVo vo) throws Exception;
	/**	*添加	*/	public void insertWebLettersDetail(WebLettersDetailVo vo) throws Exception;
	/**	*修改	*/	public void updateWebLettersDetailByCondition(WebLettersDetailVo vo,JdbcConnection conn) throws Exception;
}