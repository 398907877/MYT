package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.CheckRecordVo;import com.yihu.myt.service.service.impl.CheckRecordService;
@By(CheckRecordService.class)
public interface ICheckRecordService{
	/**	*��ȡ�б��¼��	*/	public Integer queryCheckRecordCountByCondition(CheckRecordVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<CheckRecordVo> queryCheckRecordListByCondition(CheckRecordVo vo) throws Exception;
	/**	*���	*/	public void insertCheckRecord(CheckRecordVo vo) throws Exception;
	/**	*�޸�	*/	public void updateCheckRecordByCondition(CheckRecordVo vo,JdbcConnection conn) throws Exception;
}