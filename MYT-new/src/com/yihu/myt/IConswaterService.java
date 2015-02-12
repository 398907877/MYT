package com.yihu.myt;

import java.util.List;

import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.ConswaterService;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytConswaterSatisfactionBean;
import com.yihu.myt.vo.Page;

/**
 * 咨询计费流水服务接口
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:46
 */
@By(ConswaterService.class)
public interface IConswaterService {

	/**
	 * 获取咨询计费流水记录集
	 * @param proMcBean
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public ServiceResult<List<MytConswaterBean>> getMytConswaterResult(MytConswaterBean parMcBean, Page<MytConswaterBean> pg, int cardorgid);
	public ServiceResult<List<MytConswaterBean>> getMytConswaterWrongResult(MytConswaterBean parMcBean, Page<MytConswaterBean> pg);
	public ServiceResult<List<MytConswaterBean>> getMytConswaterResultList(String pkconswaterids) ;
	/**
	 * 获取咨询计费流水记录数
	 * @param parMcBean
	 * @return
	 */
	public ServiceResult<Integer> getMytConswaterCount(MytConswaterBean parMcBean, int cardorgid);
	public ServiceResult<Integer> getMytConswaterWrongCount(MytConswaterBean parMcBean);
	
	/**
	 * 获取单条咨询计费流水
	 * @param parMcBean
	 * @return
	 */
	public ServiceResult<MytConswaterBean> getMytConswaterEntity(MytConswaterBean parMcBean);
	
	/**
	 * 添加咨询计费流水记录
	 * @param proMcBean
	 * @return
	 */
	public ReturnValue addMytConswater(MytConswaterBean parMcBean);
	public ReturnValue payMytConswater(MytConswaterBean parMcBean);

	/**
	 * 获取咨询流水满意度回访记录集
	 * @param parMcsBean
	 * @param pg
	 * @return
	 */
	public ServiceResult<List<MytConswaterSatisfactionBean>> getMytConswaterSatisfactionResult(MytConswaterSatisfactionBean parMcsBean, Page<MytConswaterSatisfactionBean> pg);
	
	/**
	 * 获取咨询流水满意度回访记录数
	 * @param parMcsBean
	 * @return
	 */
	public ServiceResult<Integer> getMytConswaterSatisfactionCount(MytConswaterSatisfactionBean parMcsBean);
	
	/**
	 * 保存满意度回访记录
	 * @param mcs
	 * @return
	 */
	public ReturnValue addMytConsWaterSatisfaction(MytConswaterSatisfactionBean parMcsBean);
	
	/**
	 * 获取账单记录数
	 * @param bean
	 * @return
	 */
	public ServiceResult<Integer> getBillCount(MytConswaterBean bean);
	
	/**
	 * 获取账单 
	 * @param bean
	 * @param page
	 * @return
	 */
	public ServiceResult<JSONObject> getBillResult(MytConswaterBean bean, Page page);
	public ServiceResult<List<MytConswaterBean>> queryMytConsList(MytConswaterBean vo,int start,int pageSize);
	public ServiceResult<List<MytConswaterBean>> queryMytConsListForReport(MytConswaterBean vo,int start,int pageSize);
	public int  queryMytConsListCount(MytConswaterBean vo );
	public int  queryMytConsListCountForReport(MytConswaterBean vo );
	public JSONObject getMytWaters(MytConswaterBean bean) throws Exception ;
	public int getMytWaterCountByCheck(MytConswaterBean bean,Integer type ,Integer chagneType,Integer quesNo) throws Exception ;
	public JSONObject getMytWaterByCheck(MytConswaterBean bean,Integer type,Integer chagneType,Integer quesNo ,Integer pageSize, Integer pageIndex) throws Exception;

}

