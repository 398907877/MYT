package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.LoginPlatformVo;import com.yihu.myt.service.service.impl.LoginPlatformService;
@By(LoginPlatformService.class)
public interface ILoginPlatformService{
	/**	*获取列表记录数	*/	public Integer queryLoginPlatformCountByCondition(LoginPlatformVo vo) throws Exception;
	/**	*获取列表	*/	public List<LoginPlatformVo> queryLoginPlatformListByCondition(LoginPlatformVo vo) throws Exception;
	/**	*添加	*/	public void insertLoginPlatform(LoginPlatformVo vo) throws Exception;
	/**	*修改	*/	public void updateLoginPlatformByCondition(LoginPlatformVo vo,JdbcConnection conn) throws Exception;
}