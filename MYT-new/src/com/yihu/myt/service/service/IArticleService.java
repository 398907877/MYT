package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ArticleVo;import com.yihu.myt.service.service.impl.ArticleService;
@By(ArticleService.class)
public interface IArticleService{
	/**	*获取列表记录数	*/	public Integer queryArticleCountByCondition(ArticleVo vo) throws Exception;
	/**	*获取列表	*/	public List<ArticleVo> queryArticleListByCondition(ArticleVo vo) throws Exception;
	/**	*添加	*/	public void insertArticle(ArticleVo vo) throws Exception;
	/**	*修改	*/	public void updateArticleByCondition(ArticleVo vo,JdbcConnection conn) throws Exception;
}