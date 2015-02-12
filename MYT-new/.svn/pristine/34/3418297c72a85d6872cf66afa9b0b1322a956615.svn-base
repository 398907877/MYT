package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorClickWaterVo;import com.yihu.myt.service.service.impl.DoctorClickWaterService;
@By(DoctorClickWaterService.class)
public interface IDoctorClickWaterService{
	/**	*获取列表记录数	*/	public Integer queryDoctorClickWaterCountByCondition(DoctorClickWaterVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorClickWaterVo> queryDoctorClickWaterListByCondition(DoctorClickWaterVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorClickWater(DoctorClickWaterVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorClickWaterByCondition(DoctorClickWaterVo vo,JdbcConnection conn) throws Exception;
}