package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ZiXun_DoctorApplyInterimVo;import com.yihu.myt.service.service.impl.ZiXun_DoctorApplyInterimService;
@By(ZiXun_DoctorApplyInterimService.class)
public interface IZiXun_DoctorApplyInterimService{
	/**	*获取列表记录数	*/	public Integer queryZiXun_DoctorApplyInterimCountByCondition(ZiXun_DoctorApplyInterimVo vo) throws Exception;
	/**	*获取列表	*/	public List<ZiXun_DoctorApplyInterimVo> queryZiXun_DoctorApplyInterimListByCondition(ZiXun_DoctorApplyInterimVo vo) throws Exception;
	/**	*添加	*/	public void insertZiXun_DoctorApplyInterim(ZiXun_DoctorApplyInterimVo vo) throws Exception;
	/**	*修改	*/	public void updateZiXun_DoctorApplyInterimByCondition(ZiXun_DoctorApplyInterimVo vo,JdbcConnection conn) throws Exception;
}