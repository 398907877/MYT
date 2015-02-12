package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.UserDetailVo;import com.yihu.myt.service.service.impl.UserDetailService;
@By(UserDetailService.class)
public interface IUserDetailService{
	/**	*获取列表记录数	*/	public Integer queryUserDetailCountByCondition(UserDetailVo vo) throws Exception;
	/**	*获取列表	*/	public List<UserDetailVo> queryUserDetailListByCondition(UserDetailVo vo) throws Exception;
	/**	*添加	*/	public void insertUserDetail(UserDetailVo vo) throws Exception;
	/**	*修改	*/	public void updateUserDetailByCondition(UserDetailVo vo,JdbcConnection conn) throws Exception;
}