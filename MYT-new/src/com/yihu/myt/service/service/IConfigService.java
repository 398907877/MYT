package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConfigVo;import com.yihu.myt.service.service.impl.ConfigService;
@By(ConfigService.class)
public interface IConfigService{
	/**	*��ȡ�б��¼��	*/	public Integer queryConfigCountByCondition(ConfigVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConfigVo> queryConfigListByCondition(ConfigVo vo) throws Exception;
	/**	*���	*/	public void insertConfig(ConfigVo vo) throws Exception;
	/**	*�޸�	*/	public void updateConfigByCondition(ConfigVo vo,JdbcConnection conn) throws Exception;
}