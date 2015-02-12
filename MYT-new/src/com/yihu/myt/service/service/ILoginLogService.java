package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.LoginLogVo;import com.yihu.myt.service.service.impl.LoginLogService;
@By(LoginLogService.class)
public interface ILoginLogService{
	/**	*��ȡ�б��¼��	*/	public Integer queryLoginLogCountByCondition(LoginLogVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<LoginLogVo> queryLoginLogListByCondition(LoginLogVo vo) throws Exception;
	/**	*���	*/	public void insertLoginLog(LoginLogVo vo) throws Exception;
	/**	*�޸�	*/	public void updateLoginLogByCondition(LoginLogVo vo,JdbcConnection conn) throws Exception;
}