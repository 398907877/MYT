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
 * �Ƽ�ҽ����Ϣ����ӿ�
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2����11:06:26
 */
@By(MIDoctorService.class)
public interface IMIDoctorService {

	/**
	 * ��ѯ��¼��
	 * @param parMmBean
	 * @return
	 */
	public ServiceResult<JSONObject> queryResult(MytMainintrodoctorBean parMmBean, Page<MytMainintrodoctorBean> pg);
	
	/**
	 * ��ѯ��¼��
	 * @param parMmBean
	 * @return
	 */
	public ServiceResult<Integer> queryCount(MytMainintrodoctorBean parMmBean);
	
	/**
	 * ��ѯ�����¼
	 * @param mainintrodoctorid
	 * @return
	 */
	public ServiceResult<MytMainintrodoctorBean> queryEntity(String mainintrodoctorid);
	
	/**
	 * �����Ƽ�ҽ����Ϣ
	 * @param parMmBean
	 * @return
	 */
	public ReturnValue insert(MytMainintrodoctorBean parMmBean);
	
	/**
	 * �޸��Ƽ�ҽ����Ϣ
	 * @param parMmBean
	 * @return
	 */
	public ReturnValue update(MytMainintrodoctorBean parMmBean);
	
	/**
	 * ɾ���Ƽ�ҽ����Ϣ
	 * @param mainintrodoctorid
	 * @return
	 */
	public ReturnValue delete(int mainintrodoctorid);
}
