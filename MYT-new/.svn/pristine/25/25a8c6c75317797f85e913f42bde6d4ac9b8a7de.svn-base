package com.yihu.myt;

import java.util.List;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.ArraworkService;
import com.yihu.myt.vo.MytArraphoneViewBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytArraworkView;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.Page;

/**
 * 医生排班服务接口
 * 
 * @author wangfeng
 * @company yihu.com 2012-8-2上午11:07:03
 */
@By(ArraworkService.class)
public interface IArraworkService {

	/**
	 * 添加医生排班
	 * 
	 * @param bean
	 * @param week
	 * @param doctorid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue addMytArrawork(MytArraworkBean arrawork);
	public String addMytArraworkForApp(MytArraworkBean arrawork) ;
	/**
	 * 更新医生排班
	 * 
	 * @param proMaBean
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue updateMytArrawork(MytArraworkBean arrawork);

	/**
	 * 删除医生排班
	 * 
	 * @param arraworkid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue deleteMytArrawork(int arraworkid, OperatorInfo operator);

	/**
	 * 获取医生排班记录集
	 * 
	 * @param parMaBean
	 * @return
	 */
	public ServiceResult<List<MytArraworkBean>> getMytArraworkList(
			MytArraworkBean parMaBean);

	public ServiceResult<List<MytArraworkBean>> getMytArraworkListBySqlVar(
			MytArraworkBean parMaBean, Page<MytArraworkBean> pg);

	public ServiceResult<List<MytArraworkView>> getArraworkViewList(
			MytArraworkView arrView, Page<MytArraworkView> pg);

	/**
	 * 查询医生排班记录数
	 * 
	 * @param parMaBean
	 * @return
	 */
	public ServiceResult<Integer> getMytArraworkCount(MytArraworkBean parMaBean);

	/**
	 * 查询医生排班记录数
	 * 
	 * @param parMaBean
	 * @return
	 */
	public ServiceResult<Integer> getArraworkViewCount(MytArraworkView arrView);

	/**
	 * 获取单条医生排班信息
	 * 
	 * @param arraworkid
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<JSONObject> getMytArrawork(int arraworkid,
			int operconfid);

	/**
	 * 根据操作者ID获取排班信息
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<MytArraworkBean> getMytArraworkByOperconfId(
			String operconfid);

	/**
	 * 通过sql占位符拼接查询排班信息
	 * 
	 * @param operconfid
	 * @param time
	 *            时间(HH:mm)
	 * @return
	 */
	public ServiceResult<MytArraworkBean> getMytArrawork(int operconfid,
			String time);

	/**
	 * 获取排班日期
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<List<Object[]>> getMytArraworkForDate(String operconfid);

	/**
	 * 获取排班医生信息
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<MytDoctorViewBean> getMytDoctorView(int operconfid);

	public ServiceResult<MytDoctorViewBean> getMytDoctor(int operconfid);

	/**
	 * 查询医生视图记录集
	 * 
	 * @param parMdvBean
	 * @return
	 */
	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorViewList(
			MytDoctorViewBean parMdvBean, Page<MytDoctorViewBean> pg);

	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorList(String name);

	/**
	 * 查询医生视图记录数
	 * 
	 * @param parMdvBean
	 * @return
	 */
	public ServiceResult<Integer> getMytDoctorViewCount(
			MytDoctorViewBean parMdvBean);

	/**
	 * 获取排班医生联系方式
	 * 
	 * @param arraworkid
	 * @return
	 */
	public ServiceResult<List<MytArraphoneViewBean>> getMytArraphoneView(
			String arraworkid);

	/**
	 * 医生是否在忙
	 * 
	 * @param operconfid
	 * @return
	 */
	public boolean isBusy(int operconfid);

	/**
	 * 获取所有在线的医生ID
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<JSONObject> getMytArraworks(String time,
			String nowDate, String nowTime, Integer start, Integer pageSize);

	public ServiceResult<Integer> getMytArraworksCount(String time,
			String nowDate, String nowTime);

	public ServiceResult<JSONObject> getMytArraworksV2(String time);
	
	public  ServiceResult<JSONObject> getArraworkListAndOnline( int operconfid) ;
}
