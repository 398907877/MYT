package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.LoginLogVo;import com.yihu.myt.service.service.impl.LoginLogService;
@By(LoginLogService.class)
public interface ILoginLogService{
	/**	*获取列表记录数	*/	public Integer queryLoginLogCountByCondition(LoginLogVo vo) throws Exception;
	/**	*获取列表	*/	public List<LoginLogVo> queryLoginLogListByCondition(LoginLogVo vo) throws Exception;
	/**	*添加	*/	public void insertLoginLog(LoginLogVo vo) throws Exception;
	/**	*修改	*/	public void updateLoginLogByCondition(LoginLogVo vo,JdbcConnection conn) throws Exception;
}