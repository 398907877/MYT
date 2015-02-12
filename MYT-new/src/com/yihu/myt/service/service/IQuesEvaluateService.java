package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.QuesEvaluateVo;import com.yihu.myt.service.service.impl.QuesEvaluateService;
@By(QuesEvaluateService.class)
public interface IQuesEvaluateService{
	/**	*��ȡ�б��¼��	*/	public Integer queryQuesEvaluateCountByCondition(QuesEvaluateVo vo) throws Exception;
	/**	*��ȡ�б�	*/	public List<QuesEvaluateVo> queryQuesEvaluateListByCondition(QuesEvaluateVo vo) throws Exception;
	/**	*���	*/	public int insertQuesEvaluate(QuesEvaluateVo vo) throws Exception;	public int insertQEvaluate(QuesEvaluateVo vo,JdbcConnection conn) throws Exception;
	/**	*�޸�	*/	public void updateQuesEvaluateByCondition(QuesEvaluateVo vo,JdbcConnection conn) throws Exception;
}