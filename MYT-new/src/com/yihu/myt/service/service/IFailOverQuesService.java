package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.FailOverQuesVo;import com.yihu.myt.service.service.impl.FailOverQuesService;
@By(FailOverQuesService.class)
public interface IFailOverQuesService{
	/**	*获取列表记录数	*/	public Integer queryFailOverQuesCountByCondition(FailOverQuesVo vo) throws Exception;
	/**	*获取列表	*/	public List<FailOverQuesVo> queryFailOverQuesListByCondition(FailOverQuesVo vo) throws Exception;
	/**	*添加	*/	public int insertFailOverQues(FailOverQuesVo vo) throws Exception;	public int updateFailOverQuesVo(FailOverQuesVo vo) throws Exception;
	/**	*修改	*/	public void updateFailOverQuesByCondition(FailOverQuesVo vo,JdbcConnection conn) throws Exception;
}