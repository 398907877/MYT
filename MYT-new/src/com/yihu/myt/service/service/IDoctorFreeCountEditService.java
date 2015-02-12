package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorFreeCountEditVo;import com.yihu.myt.service.service.impl.DoctorFreeCountEditService;
@By(DoctorFreeCountEditService.class)
public interface IDoctorFreeCountEditService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDoctorFreeCountEditCountByCondition(DoctorFreeCountEditVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DoctorFreeCountEditVo> queryDoctorFreeCountEditListByCondition(DoctorFreeCountEditVo vo) throws Exception;	public DoctorFreeCountEditVo queryDoctorFreeCountEdit(DoctorFreeCountEditVo vo) throws Exception;
	/**	*���	*/	public int insertDoctorFreeCountEdit(DoctorFreeCountEditVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDoctorFreeCountEditByCondition(DoctorFreeCountEditVo vo,JdbcConnection conn) throws Exception;		public List<DoctorFreeCountEditVo> queryDocFreeEditListForTop(DoctorFreeCountEditVo vo) throws Exception;	public List<DoctorFreeCountEditVo> queryDocFreeEditListForUpdate(DoctorFreeCountEditVo vo) throws Exception;		public int updateDoctorFreeCountEdit(DoctorFreeCountEditVo vo) throws Exception;
}