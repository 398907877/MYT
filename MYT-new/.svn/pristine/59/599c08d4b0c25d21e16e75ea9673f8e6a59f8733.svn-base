package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.FavoritesVo;import com.yihu.myt.service.service.impl.FavoritesService;
@By(FavoritesService.class)
public interface IFavoritesService{
	/**	*获取列表记录数	*/	public Integer queryFavoritesCountByCondition(FavoritesVo vo) throws Exception;
	/**	*获取列表	*/	public List<FavoritesVo> queryFavoritesListByCondition(FavoritesVo vo) throws Exception;
	/**	*添加	*/	public void insertFavorites(FavoritesVo vo) throws Exception;
	/**	*修改	*/	public void updateFavoritesByCondition(FavoritesVo vo,JdbcConnection conn) throws Exception;
}