package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.FilesVo;import com.yihu.myt.service.service.impl.FilesService;
@By(FilesService.class)
public interface IFilesService{
	/**	*获取列表记录数	*/	public Integer queryFilesCountByCondition(FilesVo vo) throws Exception;
	/**	*获取列表	*/	public List<FilesVo> queryFilesListByCondition(FilesVo vo) throws Exception;	public FilesVo queryFiles(FilesVo vo) throws Exception;
	/**	*添加	*/	public int insertFiles(FilesVo vo) throws Exception;	/**	*修改	*/	public void updateFilesByCondition(FilesVo vo,JdbcConnection conn) throws Exception;	public int updateFilesByCondition(FilesVo vo) throws Exception;	public int updateFilesForQuesID(FilesVo vo,String ids) throws Exception ;	public int insertFilesbyConn(FilesVo vo,JdbcConnection conn) throws Exception ;
}