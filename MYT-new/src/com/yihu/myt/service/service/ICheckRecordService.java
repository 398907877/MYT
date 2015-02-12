package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.CheckRecordVo;import com.yihu.myt.service.service.impl.CheckRecordService;
@By(CheckRecordService.class)
public interface ICheckRecordService{
	/**	*获取列表记录数	*/	public Integer queryCheckRecordCountByCondition(CheckRecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<CheckRecordVo> queryCheckRecordListByCondition(CheckRecordVo vo) throws Exception;
	/**	*添加	*/	public void insertCheckRecord(CheckRecordVo vo) throws Exception;
	/**	*修改	*/	public void updateCheckRecordByCondition(CheckRecordVo vo,JdbcConnection conn) throws Exception;
}