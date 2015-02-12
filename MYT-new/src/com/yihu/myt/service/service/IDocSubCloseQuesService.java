package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DocSubCloseQuesVo;import com.yihu.myt.service.service.impl.DocSubCloseQuesService;
@By(DocSubCloseQuesService.class)
public interface IDocSubCloseQuesService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDocSubCloseQuesCountByCondition(DocSubCloseQuesVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DocSubCloseQuesVo> queryDocSubCloseQuesListByCondition(DocSubCloseQuesVo vo) throws Exception;	public DocSubCloseQuesVo queryDocSubCloseQues(DocSubCloseQuesVo vo) throws Exception;
	/**	*���	*/	public int insertDocSubCloseQues(DocSubCloseQuesVo vo) throws Exception;		public int updateDocSubCloseQuesByQueID(DocSubCloseQuesVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDocSubCloseQuesByCondition(DocSubCloseQuesVo vo,JdbcConnection conn) throws Exception;
}