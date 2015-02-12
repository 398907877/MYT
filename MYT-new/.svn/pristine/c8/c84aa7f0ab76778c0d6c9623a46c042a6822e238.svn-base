package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.RecordVo;import com.yihu.myt.service.service.impl.RecordService;
@By(RecordService.class)
public interface IRecordService{
	/**	*获取列表记录数	*/	public Integer queryRecordCountByCondition(RecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<RecordVo> queryRecordListByCondition(RecordVo vo) throws Exception;
	/**	*添加	*/	public void insertRecord(RecordVo vo) throws Exception;
	/**	*修改	*/	public void updateRecordByCondition(RecordVo vo,JdbcConnection conn) throws Exception;
}