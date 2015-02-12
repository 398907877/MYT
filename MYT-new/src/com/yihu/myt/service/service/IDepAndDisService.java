package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DepAndDisVo;import com.yihu.myt.service.service.impl.DepAndDisService;
@By(DepAndDisService.class)
public interface IDepAndDisService{
	/**	*获取列表记录数	*/	public Integer queryDepAndDisCountByCondition(DepAndDisVo vo) throws Exception;
	/**	*获取列表	*/	public List<DepAndDisVo> queryDepAndDisListByCondition(DepAndDisVo vo) throws Exception;
	/**	*添加	*/	public void insertDepAndDis(DepAndDisVo vo) throws Exception;
}