package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AssayTypeVo;import com.yihu.myt.service.service.impl.AssayTypeService;
@By(AssayTypeService.class)
public interface IAssayTypeService{
	/**	*��ȡ�б��¼��	*/	public Integer queryAssayTypeCountByCondition(AssayTypeVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<AssayTypeVo> queryAssayTypeListByCondition(AssayTypeVo vo) throws Exception;
	/**	*���	*/	public void insertAssayType(AssayTypeVo vo) throws Exception;
	/**	*�޸�	*/	public void updateAssayTypeByCondition(AssayTypeVo vo,JdbcConnection conn) throws Exception;
}