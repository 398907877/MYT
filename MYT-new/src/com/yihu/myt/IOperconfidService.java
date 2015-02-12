package com.yihu.myt;

import java.util.List;
import java.util.Map;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.OperconfidService;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.MytOperconfigBean;

/**
 * 名医通医生配置服务接口
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:14
 */
@By(OperconfidService.class)
public interface IOperconfidService {
	
	/**
	 * 查询科室	
	 * @param orgId
	 * @return
	 */
	public ServiceResult<List<Map<String, String>>> getStdOffice(int orgId);
	
	/**
	 * 获取医生视图信息
	 * @param orgID
	 * @param deptID
	 * @return
	 */
	public ServiceResult<List<MytDoctorViewBean>> getDoctor(int orgID, String deptID);
	/**
	 * 获取医生列表
	 * @param name
	 * @return
	 */
	public String getDoctorIndexList(String name,int st,int row) ;
	/**
	 * 获取医生简介信息
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<List<MytMainintrodoctorBean>> getMainintrodoctor(int operconfid);
	
	/**
	 * 添加医生信息
	 * @param operator
	 * @param operatorname
	 * @param parMoBean
	 * @param consphone
	 * @return
	 */
	public ReturnValue addDoctor(int operator, String operatorname, MytOperconfigBean parMoBean, String consphone);
	public ReturnValue addNODoctor(MytOperconfigBean parMoBean);

	/**
	 * 更新医生信息
	 * @param operconfid
	 * @param parMoBean
	 * @return
	 */
	public ReturnValue updateDoctor(int operconfid, MytOperconfigBean parMoBean);
	
	/**
	 * 删除医生信息
	 * @param operconfid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue deleteDoctor(int operconfid,int operator,String operatorname);
	
	/**
	 * 恢复医生
	 * @param operconfid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue NOdeleteDoctor(int operconfid, int operator, String operatorname);
	
	/**
	 * 添加科室
	 * @param cmb_sdept
	 * @param sdept
	 * @param remark
	 * @param operator
	 * @param operatorname
	 * @param parMoBean
	 * @return
	 */
	public ReturnValue addDept(String cmb_sdept, String sdept, String remark, int operator, String operatorname, MytOperconfigBean parMoBean);
	
	/**
	 * 获取科室信息
	 * @param orgID
	 * @return
	 */
	// public ServiceResult<> getHospOffice(int orgID);
	
	/**
	 * 判断名医通医生配置是否存在
	 * @return
	 */
	public ServiceResult<Boolean> isExsistMytOperconfig(String doctorId, String state);
	
	/**
	 * 获取指定医生所有咨询电话
	 * @param operconfId
	 * @return
	 */
	public JSONObject getConsphoneByDoctor(int operconfId);
	

}
