package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AssayResultMainVo;import com.yihu.myt.service.service.impl.AssayResultMainService;
@By(AssayResultMainService.class)
public interface IAssayResultMainService{
	/**	*获取列表记录数	*/	public Integer queryAssayResultMainCountByCondition(AssayResultMainVo vo) throws Exception;
	/**	*获取列表	*/	public List<AssayResultMainVo> queryAssayResultMainListByCondition(AssayResultMainVo vo) throws Exception;	public AssayResultMainVo queryAssayResultMain(AssayResultMainVo vo) throws Exception;
	/**	*添加	*/	public void insertAssayResultMain(AssayResultMainVo vo) throws Exception;
	/**	*修改	*/	public void updateAssayResultMainByCondition(AssayResultMainVo vo,JdbcConnection conn) throws Exception;
}