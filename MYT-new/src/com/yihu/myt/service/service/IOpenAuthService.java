package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.OpenAuthVo;import com.yihu.myt.service.service.impl.OpenAuthService;
@By(OpenAuthService.class)
public interface IOpenAuthService{
	/**	*��ȡ�б��¼��	*/	public Integer queryOpenAuthCountByCondition(OpenAuthVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<OpenAuthVo> queryOpenAuthListByCondition(OpenAuthVo vo) throws Exception;
	/**	*���	*/	public void insertOpenAuth(OpenAuthVo vo) throws Exception;
	/**	*�޸�	*/		public int updateClicksByID(int click,int id) throws Exception;		public void updateOpenAuthByCondition(OpenAuthVo vo,JdbcConnection conn) throws Exception;	public int updateOpenAuthFlagByID(OpenAuthVo vo) throws Exception;				public int updateDocidandopenflagByqueID(OpenAuthVo vo) throws Exception;		public int updateOpenAuthFlagByDocid(OpenAuthVo vo) throws Exception;		
}