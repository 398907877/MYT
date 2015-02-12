package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorPriceRecordVo;import com.yihu.myt.service.service.impl.DoctorPriceRecordService;
@By(DoctorPriceRecordService.class)
public interface IDoctorPriceRecordService{
	/**	*��ȡ�б��¼��	*/	public Integer queryDoctorPriceRecordCountByCondition(DoctorPriceRecordVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<DoctorPriceRecordVo> queryDoctorPriceRecordListByCondition(DoctorPriceRecordVo vo) throws Exception;
	/**	*���	*/	public void insertDoctorPriceRecord(DoctorPriceRecordVo vo) throws Exception;
	/**	*�޸�	*/	public void updateDoctorPriceRecordByCondition(DoctorPriceRecordVo vo,JdbcConnection conn) throws Exception;
}