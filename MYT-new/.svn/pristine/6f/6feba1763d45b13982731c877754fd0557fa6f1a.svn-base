package com.yihu.myt.service.service;


import java.util.List;

import com.coreframework.ioc.By;
import com.yihu.myt.service.service.impl.NewDoctorService;
import com.yihu.myt.vo.HotQuesMainVo;
import com.yihu.myt.vo.NewDocProblem;
import com.yihu.myt.vo.NewDocProblemParaVO;
import com.yihu.myt.vo.Page;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.myt.vo.SuchDownDocMSGVo;


/**
 * 
 * @author WUJIAJUN
 *医生咨询的 service
 */
@By(NewDoctorService.class)
public interface INewDoctorService{
	
	
	
	
	 public  List<SuchDownDocMSGVo>  suchDownDocMSGlist(String starttime,String endtime,String province,String city,String hos,String doctorname,int startrow,int endrow)throws Exception;
	
	 public  int  suchDownDocMSGcount(String starttime,String endtime,String province,String city,String hos,String doctorname,int startrow,int endrow)throws Exception;
	
	
	/**
	 * 查询 网络诊室的 需要推送的医生
	 */
	 public   List<QuesMainVo>  queryPushDoctor(String start,String end)throws Exception;
	
	
	/**
	 * 查询 网络诊室的月报信息
	 */
	 public  String  queryPushMsg(String start,String end)throws Exception;
	
	
	/**
	 *根据科室id  查询到 咨询问题的数据 ！（按照点击量排序  并且 需要开放）
	 */
	public List<HotQuesMainVo> queryQuesMainByDepartmentID(String  dept) throws Exception;
	
	/**
	 *根据医院id  查询到 咨询问题的数据 ！（按照点击量排序  并且 需要开放）
	 */
	public List<HotQuesMainVo> queryQuesMainByHospitalID(String  hos) throws Exception;
	
	public List<NewDocProblem> queryListEveryDay(String  nowdate,String today) throws Exception;
	
	
	public List<NewDocProblem> queryOneDateChannel(String  nowdate,int today) throws Exception;
	
	/**
	 * 更具时间查询 满足回复数量》=150 的 人员
	*/
	public List<NewDocProblem> queryReward(String startdate, String enddate) throws Exception;
	
	
	/**
	*获取医生信息的分钟数new
	*/
	public List<NewDocProblem> queryNewDoctorList(NewDocProblemParaVO vo) throws Exception;
	
	
	/**
	*获取医生信息的分钟数new
	*/
	public List<NewDocProblem> queryNewALLDoctorList(NewDocProblemParaVO vo,List<String >twodeptids) throws Exception;
	
	
	
	
	

	/**
	*获取医生信息的分钟数
	*/
	public List<NewDocProblem> queryDoctorList(NewDocProblemParaVO vo,Page page) throws Exception;
	
	/**
	*更具id 查  信息
	*/
	public NewDocProblem queryDoctorById( String id) throws Exception;
	
	
	
	/**
	*获取医生信息的分钟数
	*/
	public Integer queryDoctorListCount(NewDocProblemParaVO vo) throws Exception;

	
	/**
	*获取 一级科室  二级科室 分钟数
	*/
	public List<NewDocProblem> queryAllDoctorList(NewDocProblemParaVO vo,Page page) throws Exception;

	

}
