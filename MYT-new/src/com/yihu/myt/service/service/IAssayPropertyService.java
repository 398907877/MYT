package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AssayPropertyVo;import com.yihu.myt.service.service.impl.AssayPropertyService;
@By(AssayPropertyService.class)
public interface IAssayPropertyService{
	/**	*获取列表记录数	*/	public Integer queryAssayPropertyCountByCondition(AssayPropertyVo vo) throws Exception;
	/**	*获取列表	*/	public List<AssayPropertyVo> queryAssayPropertyListByCondition(AssayPropertyVo vo) throws Exception;
	/**	*添加	*/	public void insertAssayProperty(AssayPropertyVo vo) throws Exception;
	/**	*修改	*/	public void updateAssayPropertyByCondition(AssayPropertyVo vo,JdbcConnection conn) throws Exception;
}