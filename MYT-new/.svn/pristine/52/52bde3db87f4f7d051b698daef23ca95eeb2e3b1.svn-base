package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.UserFreeCountVo;import com.yihu.myt.service.service.impl.UserFreeCountService;
@By(UserFreeCountService.class)
public interface IUserFreeCountService{
	/**	*获取列表记录数	*/	public Integer queryUserFreeCountCountByCondition(UserFreeCountVo vo) throws Exception;
	/**	*获取列表	*/	public List<UserFreeCountVo> queryUserFreeCountListByCondition(UserFreeCountVo vo) throws Exception;	public List<UserFreeCountVo> queryUserFreeOverList(UserFreeCountVo vo) throws Exception;
	/**	*添加	*/	public int insertUserFreeCount(UserFreeCountVo vo) throws Exception;
	/**	*修改	*/	public void updateUserFreeCountByCondition(UserFreeCountVo vo,JdbcConnection conn) throws Exception;	public Integer userGetFreeCount(Integer doctorUid,Integer userID,Integer ifOver) throws Exception;	public UserFreeCountVo userGetFree(Integer doctorUid,Integer userID)throws Exception;	public int updateUserFree(UserFreeCountVo vo) throws Exception;	public UserFreeCountVo queryUserFreeCount(UserFreeCountVo vo)throws Exception;		public UserFreeCountVo queryUserFree(UserFreeCountVo vo)throws Exception;	
}