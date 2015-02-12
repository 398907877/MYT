package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.BulletinVo;import com.yihu.myt.service.service.impl.BulletinService;
@By(BulletinService.class)
public interface IBulletinService{
	/**	*获取列表记录数	*/	public Integer queryBulletinCountByCondition(BulletinVo vo) throws Exception;
	/**	*获取列表	*/	public List<BulletinVo> queryBulletinListByCondition(BulletinVo vo) throws Exception;
	/**	*添加	*/	public void insertBulletin(BulletinVo vo) throws Exception;
	/**	*修改	*/	public void updateBulletinByCondition(BulletinVo vo,JdbcConnection conn) throws Exception;
}