package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AskSmsRecordVo;import com.yihu.myt.service.service.impl.AskSmsRecordService;
@By(AskSmsRecordService.class)
public interface IAskSmsRecordService{
	/**	*获取列表记录数	*/	public Integer queryAskSmsRecordCountByCondition(AskSmsRecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<AskSmsRecordVo> queryAskSmsRecordListByCondition(AskSmsRecordVo vo) throws Exception;
	/**	*添加	*/	public void insertAskSmsRecord(AskSmsRecordVo vo) throws Exception;
	/**	*修改	*/	public void updateAskSmsRecordByCondition(AskSmsRecordVo vo,JdbcConnection conn) throws Exception;
}