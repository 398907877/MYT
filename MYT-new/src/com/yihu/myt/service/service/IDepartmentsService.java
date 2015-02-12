package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DepartmentsVo;import com.yihu.myt.service.service.impl.DepartmentsService;
@By(DepartmentsService.class)
public interface IDepartmentsService{
	/**	*获取列表记录数	*/	public Integer queryDepartmentsCountByCondition(DepartmentsVo vo) throws Exception;
	/**	*获取列表	*/	public List<DepartmentsVo> queryDepartmentsListByCondition(DepartmentsVo vo) throws Exception;
	/**	*添加	*/	public int insertDepartments(DepartmentsVo vo) throws Exception;
	/**	*修改	*/	public void updateDepartmentsByCondition(DepartmentsVo vo,JdbcConnection conn) throws Exception;	public int updateDepartmentsForQuesID(DepartmentsVo vo, String ids) throws Exception;	public int updateDepartmentsForQuesID(DepartmentsVo vo) throws Exception;	
}