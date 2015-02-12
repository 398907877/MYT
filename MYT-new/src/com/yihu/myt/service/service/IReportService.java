package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ReportVo;import com.yihu.myt.service.service.impl.ReportService;
@By(ReportService.class)
public interface IReportService{
	/**	*��ȡ�б��¼��	*/	public Integer queryReportCountByCondition(ReportVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ReportVo> queryReportListByCondition(ReportVo vo) throws Exception;
	/**	*���	*/	public void insertReport(ReportVo vo) throws Exception;
	/**	*�޸�	*/	public void updateReportByCondition(ReportVo vo,JdbcConnection conn) throws Exception;
}