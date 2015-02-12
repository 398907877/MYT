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
 * ҽ���Ű����ӿ�
 * 
 * @author wangfeng
 * @company yihu.com 2012-8-2����11:07:03
 */
@By(ArraworkService.class)
public interface IArraworkService {

	/**
	 * ���ҽ���Ű�
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
	 * ����ҽ���Ű�
	 * 
	 * @param proMaBean
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue updateMytArrawork(MytArraworkBean arrawork);

	/**
	 * ɾ��ҽ���Ű�
	 * 
	 * @param arraworkid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ReturnValue deleteMytArrawork(int arraworkid, OperatorInfo operator);

	/**
	 * ��ȡҽ���Ű��¼��
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
	 * ��ѯҽ���Ű��¼��
	 * 
	 * @param parMaBean
	 * @return
	 */
	public ServiceResult<Integer> getMytArraworkCount(MytArraworkBean parMaBean);

	/**
	 * ��ѯҽ���Ű��¼��
	 * 
	 * @param parMaBean
	 * @return
	 */
	public ServiceResult<Integer> getArraworkViewCount(MytArraworkView arrView);

	/**
	 * ��ȡ����ҽ���Ű���Ϣ
	 * 
	 * @param arraworkid
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<JSONObject> getMytArrawork(int arraworkid,
			int operconfid);

	/**
	 * ���ݲ�����ID��ȡ�Ű���Ϣ
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<MytArraworkBean> getMytArraworkByOperconfId(
			String operconfid);

	/**
	 * ͨ��sqlռλ��ƴ�Ӳ�ѯ�Ű���Ϣ
	 * 
	 * @param operconfid
	 * @param time
	 *            ʱ��(HH:mm)
	 * @return
	 */
	public ServiceResult<MytArraworkBean> getMytArrawork(int operconfid,
			String time);

	/**
	 * ��ȡ�Ű�����
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<List<Object[]>> getMytArraworkForDate(String operconfid);

	/**
	 * ��ȡ�Ű�ҽ����Ϣ
	 * 
	 * @param operconfid
	 * @return
	 */
	public ServiceResult<MytDoctorViewBean> getMytDoctorView(int operconfid);

	public ServiceResult<MytDoctorViewBean> getMytDoctor(int operconfid);

	/**
	 * ��ѯҽ����ͼ��¼��
	 * 
	 * @param parMdvBean
	 * @return
	 */
	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorViewList(
			MytDoctorViewBean parMdvBean, Page<MytDoctorViewBean> pg);

	public ServiceResult<List<MytDoctorViewBean>> getMytDoctorList(String name);

	/**
	 * ��ѯҽ����ͼ��¼��
	 * 
	 * @param parMdvBean
	 * @return
	 */
	public ServiceResult<Integer> getMytDoctorViewCount(
			MytDoctorViewBean parMdvBean);

	/**
	 * ��ȡ�Ű�ҽ����ϵ��ʽ
	 * 
	 * @param arraworkid
	 * @return
	 */
	public ServiceResult<List<MytArraphoneViewBean>> getMytArraphoneView(
			String arraworkid);

	/**
	 * ҽ���Ƿ���æ
	 * 
	 * @param operconfid
	 * @return
	 */
	public boolean isBusy(int operconfid);

	/**
	 * ��ȡ�������ߵ�ҽ��ID
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
