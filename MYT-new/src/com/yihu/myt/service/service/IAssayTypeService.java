package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AssayTypeVo;import com.yihu.myt.service.service.impl.AssayTypeService;
@By(AssayTypeService.class)
public interface IAssayTypeService{
	/**	*获取列表记录数	*/	public Integer queryAssayTypeCountByCondition(AssayTypeVo vo) throws Exception;
	/**	*获取列表	*/	public List<AssayTypeVo> queryAssayTypeListByCondition(AssayTypeVo vo) throws Exception;
	/**	*添加	*/	public void insertAssayType(AssayTypeVo vo) throws Exception;
	/**	*修改	*/	public void updateAssayTypeByCondition(AssayTypeVo vo,JdbcConnection conn) throws Exception;
}