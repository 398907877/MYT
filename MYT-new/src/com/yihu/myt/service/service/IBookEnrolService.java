package com.yihu.myt.service.service;import java.util.List;import com.common.json.JSONObject;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.BookEnrolVo;import com.yihu.myt.service.service.impl.BookEnrolService;
@By(BookEnrolService.class)
public interface IBookEnrolService{
	/**	*获取列表记录数	*/	public Integer queryBookEnrolCountByCondition(BookEnrolVo vo) throws Exception;
	/**	*获取列表	*/	public BookEnrolVo queryBookEnrol(BookEnrolVo vo) throws Exception;	public List<BookEnrolVo> queryBookEnrolListByCondition(BookEnrolVo vo) throws Exception;	public String queryBookEnrolListByCondition(BookEnrolVo vo,int pageSize,int pageIndex) throws Exception;	public  JSONObject getBookEnrolListForSendMsg() throws Exception;		/**	*添加	*/	public void insertBookEnrol(BookEnrolVo vo) throws Exception;	public int insertBookEnrolRt(BookEnrolVo vo) throws Exception;	
	/**	*修改	*/	public void updateBookEnrol(BookEnrolVo vo) throws Exception;	public int updateBE(BookEnrolVo vo) throws Exception;
}