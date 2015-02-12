package com.yihu.myt;

import java.util.List;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.MIDoctorService;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.Page;

/**
 * 推荐医生信息服务接口
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:26
 */
@By(MIDoctorService.class)
public interface IMIDoctorService {

	/**
	 * 查询记录集
	 * @param parMmBean
	 * @return
	 */
	public ServiceResult<JSONObject> queryResult(MytMainintrodoctorBean parMmBean, Page<MytMainintrodoctorBean> pg);
	
	/**
	 * 查询记录数
	 * @param parMmBean
	 * @return
	 */
	public ServiceResult<Integer> queryCount(MytMainintrodoctorBean parMmBean);
	
	/**
	 * 查询单项记录
	 * @param mainintrodoctorid
	 * @return
	 */
	public ServiceResult<MytMainintrodoctorBean> queryEntity(String mainintrodoctorid);
	
	/**
	 * 新增推荐医生信息
	 * @param parMmBean
	 * @return
	 */
	public ReturnValue insert(MytMainintrodoctorBean parMmBean);
	
	/**
	 * 修改推荐医生信息
	 * @param parMmBean
	 * @return
	 */
	public ReturnValue update(MytMainintrodoctorBean parMmBean);
	
	/**
	 * 删除推荐医生信息
	 * @param mainintrodoctorid
	 * @return
	 */
	public ReturnValue delete(int mainintrodoctorid);
}
