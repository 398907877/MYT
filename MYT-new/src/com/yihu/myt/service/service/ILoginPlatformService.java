package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.LoginPlatformVo;import com.yihu.myt.service.service.impl.LoginPlatformService;
@By(LoginPlatformService.class)
public interface ILoginPlatformService{
	/**	*��ȡ�б��¼��	*/	public Integer queryLoginPlatformCountByCondition(LoginPlatformVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<LoginPlatformVo> queryLoginPlatformListByCondition(LoginPlatformVo vo) throws Exception;
	/**	*���	*/	public void insertLoginPlatform(LoginPlatformVo vo) throws Exception;
	/**	*�޸�	*/	public void updateLoginPlatformByCondition(LoginPlatformVo vo,JdbcConnection conn) throws Exception;
}