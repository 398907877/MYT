package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.QuesReplyMutualVo;import com.yihu.myt.service.service.impl.QuesReplyMutualService;
@By(QuesReplyMutualService.class)
public interface IQuesReplyMutualService{
	/**	*��ȡ�б��¼��	*/	public Integer queryQuesReplyMutualCountByCondition(QuesReplyMutualVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<QuesReplyMutualVo> queryQuesReplyMutualListByCondition(QuesReplyMutualVo vo) throws Exception;	public QuesReplyMutualVo queryQuesReplyMutual(QuesReplyMutualVo vo) throws Exception;
	/**	*���	*/	public int insertQuesReplyMutual(QuesReplyMutualVo vo) throws Exception;	public int updateQuesReplyMutual(QuesReplyMutualVo vo,JdbcConnection conn) throws Exception;	public int updateQuesReplyMutual(QuesReplyMutualVo vo) throws Exception;
	/**	*�޸�	*/	public void updateQuesReplyMutualByCondition(QuesReplyMutualVo vo,JdbcConnection conn) throws Exception;
}