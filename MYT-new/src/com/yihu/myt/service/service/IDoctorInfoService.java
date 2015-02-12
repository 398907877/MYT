package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorInfoVo;import com.yihu.myt.service.service.impl.DoctorInfoService;
@By(DoctorInfoService.class)
public interface IDoctorInfoService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDoctorInfoCountByCondition(DoctorInfoVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DoctorInfoVo> queryDoctorInfoListByCondition(DoctorInfoVo vo) throws Exception;	public DoctorInfoVo queryDoctorInfo(DoctorInfoVo vo) throws Exception;
	/**	*���	*/	public void insertDoctorInfo(DoctorInfoVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDoctorInfo(DoctorInfoVo vo) throws Exception;
}