package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorInfoVo;import com.yihu.myt.service.service.impl.DoctorInfoService;
@By(DoctorInfoService.class)
public interface IDoctorInfoService{
	/**	*获取列表记录数	*/	public Integer queryDoctorInfoCountByCondition(DoctorInfoVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorInfoVo> queryDoctorInfoListByCondition(DoctorInfoVo vo) throws Exception;	public DoctorInfoVo queryDoctorInfo(DoctorInfoVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorInfo(DoctorInfoVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorInfo(DoctorInfoVo vo) throws Exception;
}