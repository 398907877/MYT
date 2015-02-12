package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsphoneVo;import com.yihu.myt.service.service.impl.ConsphoneService;
@By(ConsphoneService.class)
public interface IConsphoneService{
	/**	*��ȡ�б��¼��	*/	public Integer queryConsphoneCountByCondition(ConsphoneVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<ConsphoneVo> queryConsphoneListByCondition(ConsphoneVo vo) throws Exception;	public ConsphoneVo queryConsphoneByCondition(ConsphoneVo vo) throws Exception;
	/**	*���	*/	public int insertConsphone(ConsphoneVo vo) throws Exception;	public int updateConsphone(ConsphoneVo vo) throws Exception ;
	/**	*�޸�	*/	public void updateConsphoneByCondition(ConsphoneVo vo,JdbcConnection conn) throws Exception;
}