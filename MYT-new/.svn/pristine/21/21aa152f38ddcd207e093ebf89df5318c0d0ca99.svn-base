package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorInviteVo;import com.yihu.myt.service.service.impl.DoctorInviteService;
@By(DoctorInviteService.class)
public interface IDoctorInviteService{
	/**	*获取列表记录数	*/	public Integer queryDoctorInviteCountByCondition(DoctorInviteVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorInviteVo> queryDoctorInviteListByCondition(DoctorInviteVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorInvite(DoctorInviteVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorInviteByCondition(DoctorInviteVo vo,JdbcConnection conn) throws Exception;
}