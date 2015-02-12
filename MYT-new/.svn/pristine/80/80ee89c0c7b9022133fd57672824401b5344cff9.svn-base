package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorAccountVo;import com.yihu.myt.service.service.impl.DoctorAccountService;
@By(DoctorAccountService.class)
public interface IDoctorAccountService{
	/**	*获取列表记录数	*/	public Integer queryDoctorAccountCountByCondition(DoctorAccountVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorAccountVo> queryDoctorAccountListByCondition(DoctorAccountVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorAccount(DoctorAccountVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorAccountByCondition(DoctorAccountVo vo,JdbcConnection conn) throws Exception;
}