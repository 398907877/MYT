package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.FavoritesTypeVo;import com.yihu.myt.service.service.impl.FavoritesTypeService;
@By(FavoritesTypeService.class)
public interface IFavoritesTypeService{
	/**	*获取列表记录数	*/	public Integer queryFavoritesTypeCountByCondition(FavoritesTypeVo vo) throws Exception;
	/**	*获取列表	*/	public List<FavoritesTypeVo> queryFavoritesTypeListByCondition(FavoritesTypeVo vo) throws Exception;
	/**	*添加	*/	public void insertFavoritesType(FavoritesTypeVo vo) throws Exception;
	/**	*修改	*/	public void updateFavoritesTypeByCondition(FavoritesTypeVo vo,JdbcConnection conn) throws Exception;
}