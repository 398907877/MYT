package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DocSubCloseQuesVo;import com.yihu.myt.service.service.impl.DocSubCloseQuesService;
@By(DocSubCloseQuesService.class)
public interface IDocSubCloseQuesService{
	/**	*获取列表记录数	*/	public Integer queryDocSubCloseQuesCountByCondition(DocSubCloseQuesVo vo) throws Exception;
	/**	*获取列表	*/	public List<DocSubCloseQuesVo> queryDocSubCloseQuesListByCondition(DocSubCloseQuesVo vo) throws Exception;	public DocSubCloseQuesVo queryDocSubCloseQues(DocSubCloseQuesVo vo) throws Exception;
	/**	*添加	*/	public int insertDocSubCloseQues(DocSubCloseQuesVo vo) throws Exception;		public int updateDocSubCloseQuesByQueID(DocSubCloseQuesVo vo) throws Exception;
	/**	*修改	*/	public void updateDocSubCloseQuesByCondition(DocSubCloseQuesVo vo,JdbcConnection conn) throws Exception;
}