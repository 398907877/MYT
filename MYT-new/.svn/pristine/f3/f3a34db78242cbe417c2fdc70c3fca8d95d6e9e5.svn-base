package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ReportVo;import com.yihu.myt.service.service.impl.ReportService;
@By(ReportService.class)
public interface IReportService{
	/**	*获取列表记录数	*/	public Integer queryReportCountByCondition(ReportVo vo) throws Exception;
	/**	*获取列表	*/	public List<ReportVo> queryReportListByCondition(ReportVo vo) throws Exception;
	/**	*添加	*/	public void insertReport(ReportVo vo) throws Exception;
	/**	*修改	*/	public void updateReportByCondition(ReportVo vo,JdbcConnection conn) throws Exception;
}