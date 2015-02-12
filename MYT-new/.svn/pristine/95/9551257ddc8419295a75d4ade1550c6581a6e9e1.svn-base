package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorInviteSMSVo;import com.yihu.myt.service.service.impl.DoctorInviteSMSService;
@By(DoctorInviteSMSService.class)
public interface IDoctorInviteSMSService{
	/**	*获取列表记录数	*/	public Integer queryDoctorInviteSMSCountByCondition(DoctorInviteSMSVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorInviteSMSVo> queryDoctorInviteSMSListByCondition(DoctorInviteSMSVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorInviteSMS(DoctorInviteSMSVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorInviteSMSByCondition(DoctorInviteSMSVo vo,JdbcConnection conn) throws Exception;
}