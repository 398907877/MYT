package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorInviteVo;import com.yihu.myt.service.service.impl.DoctorInviteService;
@By(DoctorInviteService.class)
public interface IDoctorInviteService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDoctorInviteCountByCondition(DoctorInviteVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DoctorInviteVo> queryDoctorInviteListByCondition(DoctorInviteVo vo) throws Exception;
	/**	*���	*/	public void insertDoctorInvite(DoctorInviteVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDoctorInviteByCondition(DoctorInviteVo vo,JdbcConnection conn) throws Exception;
}