package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorLoginTimeVo;import com.yihu.myt.service.service.impl.DoctorLoginTimeService;
@By(DoctorLoginTimeService.class)
public interface IDoctorLoginTimeService{
	/**	*获取列表记录数	*/	public Integer queryDoctorLoginTimeCountByCondition(DoctorLoginTimeVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorLoginTimeVo> queryDoctorLoginTimeListByCondition(DoctorLoginTimeVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorLoginTime(DoctorLoginTimeVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorLoginTimeByCondition(DoctorLoginTimeVo vo,JdbcConnection conn) throws Exception;
}