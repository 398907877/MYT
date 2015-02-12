package com.yihu.myt.service.service;import java.util.List;import com.common.json.JSONObject;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.CreditsRecordVo;import com.yihu.myt.service.service.impl.CreditsRecordService;
@By(CreditsRecordService.class)
public interface ICreditsRecordService{
	/**	*获取列表记录数	*/	public Integer queryCreditsRecordCountByCondition(CreditsRecordVo vo) throws Exception;
	/**	*获取列表	*/	public List<CreditsRecordVo> queryCreditsRecordListByCondition(CreditsRecordVo vo) throws Exception;	public List<CreditsRecordVo> queryCreditsRecordList(CreditsRecordVo vo,int start,int pageSize) throws Exception;	public int queryCreditsRecordListCont(CreditsRecordVo vo) throws Exception;
	/**	*添加	*/	public int insertCreditsRecord(CreditsRecordVo vo) throws Exception;
	/**	*修改	*/	public void updateCreditsRecordByCondition(CreditsRecordVo vo,JdbcConnection conn) throws Exception;
}