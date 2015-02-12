package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.BulletinTypeVo;import com.yihu.myt.service.service.impl.BulletinTypeService;
@By(BulletinTypeService.class)
public interface IBulletinTypeService{
	/**	*获取列表记录数	*/	public Integer queryBulletinTypeCountByCondition(BulletinTypeVo vo) throws Exception;
	/**	*获取列表	*/	public List<BulletinTypeVo> queryBulletinTypeListByCondition(BulletinTypeVo vo) throws Exception;
	/**	*添加	*/	public void insertBulletinType(BulletinTypeVo vo) throws Exception;
	/**	*修改	*/	public void updateBulletinTypeByCondition(BulletinTypeVo vo,JdbcConnection conn) throws Exception;
}