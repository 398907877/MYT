package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorPriceRecordVo;import com.yihu.myt.service.service.impl.DoctorPriceRecordService;
@By(DoctorPriceRecordService.class)
public interface IDoctorPriceRecordService{
	/**	*获取列表记录数	*/	public Integer queryDoctorPriceRecordCountByCondition(DoctorPriceRecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorPriceRecordVo> queryDoctorPriceRecordListByCondition(DoctorPriceRecordVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorPriceRecord(DoctorPriceRecordVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorPriceRecordByCondition(DoctorPriceRecordVo vo,JdbcConnection conn) throws Exception;
}