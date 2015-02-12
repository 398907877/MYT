package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorClickVo;import com.yihu.myt.service.service.impl.DoctorClickService;
@By(DoctorClickService.class)
public interface IDoctorClickService{
	/**	*获取列表记录数	*/	public Integer queryDoctorClickCountByCondition(DoctorClickVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorClickVo> queryDoctorClickListByCondition(DoctorClickVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorClick(DoctorClickVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorClickByCondition(DoctorClickVo vo,JdbcConnection conn) throws Exception;
}