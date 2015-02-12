package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.PatientVo;import com.yihu.myt.service.service.impl.PatientService;
@By(PatientService.class)
public interface IPatientService{
	/**	*获取列表记录数	*/	public Integer queryPatientCountByCondition(PatientVo vo) throws Exception;
	/**	*获取列表	*/	public List<PatientVo> queryPatientListByCondition(PatientVo vo) throws Exception;	public PatientVo queryPatient(PatientVo vo) throws Exception;
	/**	*添加	*/	public int insertPatient(PatientVo vo) throws Exception;
	/**	*修改	*/	public void updatePatientByCondition(PatientVo vo,JdbcConnection conn) throws Exception;
}