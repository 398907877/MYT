package com.yihu.myt.service.service;import java.util.List;import net.sf.json.JSONObject;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ReplyVo;import com.yihu.myt.service.service.impl.ReplyService;
@By(ReplyService.class)
public interface IReplyService{
	/**	*��ȡ�б��¼��	*/	public Integer queryReplyCountByCondition(ReplyVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ReplyVo> queryReplyListByCondition(ReplyVo vo) throws Exception;	public List<ReplyVo> queryReplyListByCondition(ReplyVo vo,Integer pageIndex,Integer pageSize) throws Exception;	public int queryReplyCount(ReplyVo vo) throws Exception;		public com.common.json.JSONObject queryReplyListAll(ReplyVo vo) throws Exception;	public ReplyVo queryReplyLastOne(ReplyVo vo) throws Exception;	public com.common.json.JSONObject getYestodayReplyCount() throws Exception;	/**	*���	*/	public int insertReply(ReplyVo vo) throws Exception;	public int insertReplyByCondition(ReplyVo vo,JdbcConnection conn) throws Exception;
	/**	*�޸�	*/	public int updateReplyByCondition(ReplyVo vo) throws Exception;		
}