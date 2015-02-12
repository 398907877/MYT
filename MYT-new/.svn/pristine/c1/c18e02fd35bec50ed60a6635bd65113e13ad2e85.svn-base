package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.ConsEnrolVo;import com.yihu.myt.service.service.impl.ConsEnrolService;
@By(ConsEnrolService.class)
public interface IConsEnrolService{
	/**	*获取列表记录数	*/	public Integer queryConsEnrolCountByCondition(ConsEnrolVo vo) throws Exception;
	/**	*获取列表	*/	public List<ConsEnrolVo> queryConsEnrolListByCondition(ConsEnrolVo vo) throws Exception;
	/**	*添加	*/	public void insertConsEnrol(ConsEnrolVo vo) throws Exception;
	/**	*修改	*/	public void updateConsEnrol(ConsEnrolVo vo) throws Exception;		/**	*获取用户所咨询过的电话咨询数据	*/	public String getMytConsenrols(ConsEnrolVo vo, Integer pageSize, Integer pageIndex) throws Exception;
}