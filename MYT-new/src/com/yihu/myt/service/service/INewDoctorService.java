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
 *ҽ����ѯ�� service
 */
@By(NewDoctorService.class)
public interface INewDoctorService{
	
	
	
	
	 public  List<SuchDownDocMSGVo>  suchDownDocMSGlist(String starttime,String endtime,String province,String city,String hos,String doctorname,int startrow,int endrow)throws Exception;
	
	 public  int  suchDownDocMSGcount(String starttime,String endtime,String province,String city,String hos,String doctorname,int startrow,int endrow)throws Exception;
	
	
	/**
	 * ��ѯ �������ҵ� ��Ҫ���͵�ҽ��
	 */
	 public   List<QuesMainVo>  queryPushDoctor(String start,String end)throws Exception;
	
	
	/**
	 * ��ѯ �������ҵ��±���Ϣ
	 */
	 public  String  queryPushMsg(String start,String end)throws Exception;
	
	
	/**
	 *���ݿ���id  ��ѯ�� ��ѯ��������� �������յ��������  ���� ��Ҫ���ţ�
	 */
	public List<HotQuesMainVo> queryQuesMainByDepartmentID(String  dept) throws Exception;
	
	/**
	 *����ҽԺid  ��ѯ�� ��ѯ��������� �������յ��������  ���� ��Ҫ���ţ�
	 */
	public List<HotQuesMainVo> queryQuesMainByHospitalID(String  hos) throws Exception;
	
	public List<NewDocProblem> queryListEveryDay(String  nowdate,String today) throws Exception;
	
	
	public List<NewDocProblem> queryOneDateChannel(String  nowdate,int today) throws Exception;
	
	/**
	 * ����ʱ���ѯ ����ظ�������=150 �� ��Ա
	*/
	public List<NewDocProblem> queryReward(String startdate, String enddate) throws Exception;
	
	
	/**
	*��ȡҽ����Ϣ�ķ�����new
	*/
	public List<NewDocProblem> queryNewDoctorList(NewDocProblemParaVO vo) throws Exception;
	
	
	/**
	*��ȡҽ����Ϣ�ķ�����new
	*/
	public List<NewDocProblem> queryNewALLDoctorList(NewDocProblemParaVO vo,List<String >twodeptids) throws Exception;
	
	
	
	
	

	/**
	*��ȡҽ����Ϣ�ķ�����
	*/
	public List<NewDocProblem> queryDoctorList(NewDocProblemParaVO vo,Page page) throws Exception;
	
	/**
	*����id ��  ��Ϣ
	*/
	public NewDocProblem queryDoctorById( String id) throws Exception;
	
	
	
	/**
	*��ȡҽ����Ϣ�ķ�����
	*/
	public Integer queryDoctorListCount(NewDocProblemParaVO vo) throws Exception;

	
	/**
	*��ȡ һ������  �������� ������
	*/
	public List<NewDocProblem> queryAllDoctorList(NewDocProblemParaVO vo,Page page) throws Exception;

	

}
