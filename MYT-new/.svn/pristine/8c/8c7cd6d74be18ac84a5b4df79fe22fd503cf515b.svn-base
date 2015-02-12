package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorAccount_MoreVo;import com.yihu.myt.service.service.impl.DoctorAccount_MoreService;
@By(DoctorAccount_MoreService.class)
public interface IDoctorAccount_MoreService{
	/**	*获取列表记录数	*/	public Integer queryDoctorAccount_MoreCountByCondition(DoctorAccount_MoreVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorAccount_MoreVo> queryDoctorAccount_MoreListByCondition(DoctorAccount_MoreVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorAccount_More(DoctorAccount_MoreVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorAccount_MoreByCondition(DoctorAccount_MoreVo vo,JdbcConnection conn) throws Exception;
}