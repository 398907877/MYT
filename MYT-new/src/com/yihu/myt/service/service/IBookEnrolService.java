package com.yihu.myt.service.service;import java.util.List;import com.common.json.JSONObject;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.BookEnrolVo;import com.yihu.myt.service.service.impl.BookEnrolService;
@By(BookEnrolService.class)
public interface IBookEnrolService{
	/**	*��ȡ�б��¼��	*/	public Integer queryBookEnrolCountByCondition(BookEnrolVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public BookEnrolVo queryBookEnrol(BookEnrolVo vo) throws Exception;	public List<BookEnrolVo> queryBookEnrolListByCondition(BookEnrolVo vo) throws Exception;	public String queryBookEnrolListByCondition(BookEnrolVo vo,int pageSize,int pageIndex) throws Exception;	public  JSONObject getBookEnrolListForSendMsg() throws Exception;		/**	*���	*/	public void insertBookEnrol(BookEnrolVo vo) throws Exception;	public int insertBookEnrolRt(BookEnrolVo vo) throws Exception;	
	/**	*�޸�	*/	public void updateBookEnrol(BookEnrolVo vo) throws Exception;	public int updateBE(BookEnrolVo vo) throws Exception;
}