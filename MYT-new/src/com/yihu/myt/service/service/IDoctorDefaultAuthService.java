package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorDefaultAuthVo;import com.yihu.myt.vo.QuesMainVo;import com.yihu.myt.service.service.impl.DoctorDefaultAuthService;
@By(DoctorDefaultAuthService.class)
public interface IDoctorDefaultAuthService{					public List<QuesMainVo> queryQuesByDiseaseID(Integer id) throws Exception;	public List<DoctorDefaultAuthVo> queryDoctorDefaultAuthListByDoctorId(DoctorDefaultAuthVo vo) throws Exception;
	/**	*获取列表记录数	*/	public Integer queryDoctorDefaultAuthCountByCondition(DoctorDefaultAuthVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorDefaultAuthVo> queryDoctorDefaultAuthListByCondition(DoctorDefaultAuthVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorDefaultAuth(DoctorDefaultAuthVo vo) throws Exception;
	/**	*修改	*/	public int updateDoctorDefaultAuthByCondition(DoctorDefaultAuthVo vo) throws Exception;
}