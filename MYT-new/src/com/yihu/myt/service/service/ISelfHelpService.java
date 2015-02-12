package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.SelfHelpVo;import com.yihu.myt.service.service.impl.SelfHelpService;
@By(SelfHelpService.class)
public interface ISelfHelpService{
	/**	*获取列表记录数	*/	public Integer querySelfHelpCountByCondition(SelfHelpVo vo) throws Exception;
	/**	*获取列表	*/	public List<SelfHelpVo> querySelfHelpListByCondition(SelfHelpVo vo) throws Exception;
	/**	*添加	*/	public int insertSelfHelp(SelfHelpVo vo) throws Exception;
	/**	*修改	*/	public void updateSelfHelpByCondition(SelfHelpVo vo,JdbcConnection conn) throws Exception;
}