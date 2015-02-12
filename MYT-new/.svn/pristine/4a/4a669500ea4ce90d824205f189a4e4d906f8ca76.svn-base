package com.yihu.myt;

import java.util.List;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONArray;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.BookenrolService;
import com.yihu.myt.vo.Consstatistic;
import com.yihu.myt.vo.MytBookenrolBean;
import com.yihu.myt.vo.MytBookenrolView;
import com.yihu.myt.vo.MytConsenrolBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytRevertBean;
import com.yihu.myt.vo.Page;

/**
 * 预约回复服务接口
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:56
 */
@By(BookenrolService.class)
public interface IBookenrolService {
	
	
	
	/**
	 *  咨询运营支撑数据
	 * @param Consstatistic  Page
	 * @return ServiceResult
	 */
	public ServiceResult<List<Consstatistic>> getAllConsstatistic(Consstatistic bean, Page<Consstatistic> pg);
	
	/**
	 *  咨询运营支撑数据count
	 * @param Consstatistic  
	 * @return int
	 */
	public  ServiceResult<Integer> getAllConsstatisticCount(Consstatistic bean);
	
	
	

	/**
	 * 查询预约回复记录集
	 * @param parMbBean
	 * @param regway 登记渠道 1：人工  2：网站
	 * @return
	 */
	public ServiceResult<List<MytBookenrolView>> getMytBookenrolResult(MytBookenrolView parMbBean, int regway, Page<MytBookenrolView> pg, int jsType);
	public ServiceResult<List<MytBookenrolBean>> getMytBookenrolResult(MytBookenrolBean parMbBean);
	public ServiceResult<List<MytBookenrolView>> getMytBookenrolResultS(MytBookenrolView parMbBean, int regway, Page<MytBookenrolView> pg, int jsType);

	/**
	 * 查询预约回复记录数
	 * @param parMbBean
	 * @param regway 登记渠道 1：人工  2：网站
	 * @return
	 */
	public ServiceResult<Integer> getMytBookenrolCount(MytBookenrolView parMbBean, int regway);
	public ServiceResult<Integer> getMytBookenrolCountS(MytBookenrolView parMbBean, int regway);
	/**
	 * 获取单条预约回复
	 * @param parMbBean
	 * @return
	 */
	public ServiceResult<MytBookenrolBean> getMytBookenrolEntity(MytBookenrolBean parMbBean);
	
	/**
	 * 查询咨询登记记录集
	 * @param parMcBean
	 * @param pg
	 * @return
	 */
	public ServiceResult<JSONArray> getMytConsenrol(MytConsenrolBean parMcBean, Page<MytConsenrolBean> pg);
	
	/**
	 * 查询咨询登记记录数
	 * @param parMcBean
	 * @return
	 */
	public ServiceResult<Integer> getMytConsenrol(MytConsenrolBean parMcBean);
	
	/**
	 * 新增咨询预约登记
	 * @param proMcBean
	 * @return
	 */
	public ReturnValue addMytConsenrol(MytConsenrolBean parMcBean);
	
	/**
	 * 更新咨询预约登记
	 * @param bookenrolid
	 * @param operconfid
	 * @param revertresult
	 * @param remark
	 * @param operatorid
	 * @param operatorname
	 * @return
	 */
	public ReturnValue updateMytConsenrol(int bookenrolid,
			int operconfid, String revertresult, String remark,
			OperatorInfo operator);
	
	/**
	 * 获取医生视图信息
	 * @param operconfId
	 * @return
	 */
	public ServiceResult<MytDoctorViewBean> getMytDoctorView(int operconfId);
	
	/**
	 * 获取咨询回访信息
	 * @param bookenrolId
	 * @return
	 */
	public ServiceResult<List<MytRevertBean>> getMytRevert(String bookenrolId);
	/**
	 * 获取用户业绩报表
	 * @param  
	 * @return
	 */
	public String getGradReport(String operName,int prvid, int rows, int page,
			String sBTime, String sETime);
	
	public Integer getUserReturnListCount(String cardid,String start,String end,String type);
	public String getUserReturnList(String cardid, String start,String end,String type, Integer pageSize, Integer pageIndex) ;
	
}
