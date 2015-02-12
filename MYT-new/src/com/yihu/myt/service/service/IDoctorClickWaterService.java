package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorClickWaterVo;import com.yihu.myt.service.service.impl.DoctorClickWaterService;
@By(DoctorClickWaterService.class)
public interface IDoctorClickWaterService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDoctorClickWaterCountByCondition(DoctorClickWaterVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DoctorClickWaterVo> queryDoctorClickWaterListByCondition(DoctorClickWaterVo vo) throws Exception;
	/**	*���	*/	public void insertDoctorClickWater(DoctorClickWaterVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDoctorClickWaterByCondition(DoctorClickWaterVo vo,JdbcConnection conn) throws Exception;
}