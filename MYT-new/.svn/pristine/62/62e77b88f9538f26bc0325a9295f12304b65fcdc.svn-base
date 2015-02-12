package com.yihu.myt;

import java.util.List;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONObject;
import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.PauseService;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.MytPauseworkView;
import com.yihu.myt.vo.Page;

/**
 * 医生停诊操作服务接口
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午11:06:07
 */
@By(PauseService.class)
public interface IPauseService {

	/**
	 * 获取医生停诊记录集
	 * @param pauseworkView
	 * @param startOpertime 登记起始时间(yyyy-MM-dd)
	 * @param endOpertime 登记结束时间(yyyy-MM-dd)
	 * @return
	 */
	public JSONObject getResults(MytPauseworkView pauseworkView, String startOpertime, String endOpertime, Page<MytPauseworkBean> pg);
	/**
	 * 获取医生停诊记录集(获取医生有效停诊数据)
	 * @param MytPauseworkBean
	 * @return
	 */
	public ServiceResult<List<MytPauseworkBean>>  getTimeResults(MytPauseworkBean pauseworkBean) ;

	
	/**
	 * 获取医生停诊记录数
	 * @param pauseworkView
	 * @param startOpertime 登记起始时间(yyyy-MM-dd)
	 * @param endOpertime 登记结束时间(yyyy-MM-dd)
	 * @return
	 */
	public ServiceResult<Integer> getCount(MytPauseworkView pauseworkView, String startOpertime, String endOpertime);
	
	/**
	 * 根据单条医生停诊信息
	 * @param parMpBean
	 * @return
	 */
	public ServiceResult<JSONObject> getEntity(MytPauseworkBean parMpBean);
	
	/**
	 * 根据sql占位符拼接查询停诊信息
	 * @param operconfid
	 * @param nowTime 时间(HH:mm)
	 * @param nowDate 日期(yyyy-MM-dd)
	 * @return
	 */
	public ServiceResult<MytPauseworkBean> getEntity(int operconfid, String nowTime, String nowDate);
	public String getEntityValue(int operconfid, String nowTime, String nowDate) throws Exception;
	
	/**
	 * 根据操作ID获取医生停诊信息
	 * @param parMpBean
	 * @return
	 */
	public ServiceResult<List<MytPauseworkBean>> getResult(int operconfid);
	
	/**
	 * 添加医生停诊
	 * @param pausework
	 * @return
	 */
	public ReturnValue add(MytPauseworkBean pausework);
	public ReturnValue addPause(MytPauseworkBean pausework) ;
	public ReturnValue addPauseForWeb(MytPauseworkBean pausework) ;
	/**
	 * 删除医生停诊
	 * @param parPauseworkBean
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue delete(int pauseid, OperatorInfo operator);
	
	/**
	 * 修改医生停诊
	 * @param parPauseworkBean
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue update(MytPauseworkBean parMpBean);
	
	/**
	 * 获取医生停诊ID列表
	 * @param parPauseworkBean
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public  ServiceResult<List<MytPauseworkBean>> getEntitys(String nowTime, String nowDate) ;
	
}
