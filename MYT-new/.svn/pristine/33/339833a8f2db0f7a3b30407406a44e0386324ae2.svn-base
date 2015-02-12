package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.QuesEvaluateVo;import com.yihu.myt.service.service.impl.QuesEvaluateService;
@By(QuesEvaluateService.class)
public interface IQuesEvaluateService{
	/**	*获取列表记录数	*/	public Integer queryQuesEvaluateCountByCondition(QuesEvaluateVo vo) throws Exception;
	/**	*获取列表	*/	public List<QuesEvaluateVo> queryQuesEvaluateListByCondition(QuesEvaluateVo vo) throws Exception;
	/**	*添加	*/	public int insertQuesEvaluate(QuesEvaluateVo vo) throws Exception;	public int insertQEvaluate(QuesEvaluateVo vo,JdbcConnection conn) throws Exception;
	/**	*修改	*/	public void updateQuesEvaluateByCondition(QuesEvaluateVo vo,JdbcConnection conn) throws Exception;
}