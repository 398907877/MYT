package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.QuesClickVo;import com.yihu.myt.service.service.impl.QuesClickService;
@By(QuesClickService.class)
public interface IQuesClickService{
	/**	*获取列表记录数	*/	public Integer queryQuesClickCountByCondition(QuesClickVo vo) throws Exception;
	/**	*获取列表	*/	public List<QuesClickVo> queryQuesClickListByCondition(QuesClickVo vo) throws Exception;
	/**	*添加	*/	public void insertQuesClick(QuesClickVo vo) throws Exception;
	/**	*修改	*/	public void updateQuesClickByCondition(QuesClickVo vo,JdbcConnection conn) throws Exception;
}