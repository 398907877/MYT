package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AssayResultListVo;import com.yihu.myt.service.service.impl.AssayResultListService;
@By(AssayResultListService.class)
public interface IAssayResultListService{
	/**	*获取列表记录数	*/	public Integer queryAssayResultListCountByCondition(AssayResultListVo vo) throws Exception;
	/**	*获取列表	*/	public List<AssayResultListVo> queryAssayResultListListByCondition(AssayResultListVo vo) throws Exception;
	/**	*添加	*/	public void insertAssayResultList(AssayResultListVo vo) throws Exception;
	/**	*修改	*/	public void updateAssayResultListByCondition(AssayResultListVo vo,JdbcConnection conn) throws Exception;
}