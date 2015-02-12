package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.AnswerRecordVo;import com.yihu.myt.service.service.impl.AnswerRecordService;
@By(AnswerRecordService.class)
public interface IAnswerRecordService{
	/**	*获取列表记录数	*/	public Integer queryAnswerRecordCountByCondition(AnswerRecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<AnswerRecordVo> queryAnswerRecordListByCondition(AnswerRecordVo vo) throws Exception;
	/**	*添加	*/	public int insertAnswerRecord(AnswerRecordVo vo) throws Exception;
	/**	*修改	*/	public void updateAnswerRecordByCondition(AnswerRecordVo vo,JdbcConnection conn) throws Exception;
}