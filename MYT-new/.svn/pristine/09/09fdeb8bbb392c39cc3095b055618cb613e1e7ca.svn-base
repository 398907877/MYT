package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConfigVo;import com.yihu.myt.service.service.impl.ConfigService;
@By(ConfigService.class)
public interface IConfigService{
	/**	*获取列表记录数	*/	public Integer queryConfigCountByCondition(ConfigVo vo) throws Exception;
	/**	*获取列表	*/	public List<ConfigVo> queryConfigListByCondition(ConfigVo vo) throws Exception;
	/**	*添加	*/	public void insertConfig(ConfigVo vo) throws Exception;
	/**	*修改	*/	public void updateConfigByCondition(ConfigVo vo,JdbcConnection conn) throws Exception;
}