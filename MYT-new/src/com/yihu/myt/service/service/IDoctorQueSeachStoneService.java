package com.yihu.myt.service.service;import java.util.List;import com.coreframework.ioc.By;import com.coreframework.db.JdbcConnection;import com.yihu.myt.vo.DoctorQueSeachStoneVo;import com.yihu.myt.service.service.impl.DoctorQueSeachStoneService;
@By(DoctorQueSeachStoneService.class)
public interface IDoctorQueSeachStoneService{
	/**	*获取列表记录数	*/	public Integer queryDoctorQueSeachStoneCountByCondition(DoctorQueSeachStoneVo vo) throws Exception;
	/**	*获取列表	*/	public List<DoctorQueSeachStoneVo> queryDoctorQueSeachStoneListByCondition(DoctorQueSeachStoneVo vo) throws Exception;
	/**	*添加	*/	public void insertDoctorQueSeachStone(DoctorQueSeachStoneVo vo) throws Exception;	public int insertDoctorQueSeachStoneRt(DoctorQueSeachStoneVo vo) throws Exception;
	/**	*修改	*/	public void updateDoctorQueSeachStone(DoctorQueSeachStoneVo vo) throws Exception;	public void updateDoctorQueSeachStoneForDoctorUid(DoctorQueSeachStoneVo vo) throws Exception;
}