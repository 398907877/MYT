package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.CloseMainVo;import com.yihu.myt.vo.DoctorVo;import com.yihu.myt.service.service.impl.CloseMainService;
@By(CloseMainService.class)
public interface ICloseMainService{
	/**	*获取列表记录数	*/	public Integer queryCloseMainCountByCondition(CloseMainVo vo) throws Exception;
	/**	*获取列表	*/	public List<CloseMainVo> queryCloseMainListByCondition(CloseMainVo vo) throws Exception;
	/**	*添加	*/	public void insertCloseMain(CloseMainVo vo) throws Exception;
	/**	*修改	*/	public void updateCloseMainByCondition(CloseMainVo vo,JdbcConnection conn) throws Exception;			/**	 * 根据医生id 获取医生信息	 * */	public DoctorVo getDocById(String  docid) throws Exception;	
}