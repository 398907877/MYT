package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.RecordVo;import com.yihu.myt.service.service.impl.RecordService;
@By(RecordService.class)
public interface IRecordService{
	/**	*��ȡ�б��¼��	*/	public Integer queryRecordCountByCondition(RecordVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<RecordVo> queryRecordListByCondition(RecordVo vo) throws Exception;
	/**	*���	*/	public void insertRecord(RecordVo vo) throws Exception;
	/**	*�޸�	*/	public void updateRecordByCondition(RecordVo vo,JdbcConnection conn) throws Exception;
}