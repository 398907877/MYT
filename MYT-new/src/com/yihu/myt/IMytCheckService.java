package com.yihu.myt;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.MytCheckService;
import com.yihu.myt.vo.DataCheckBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytArraworkWBean;

/**
 * 
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2����11:06:20
 */
@By(MytCheckService.class)
public interface IMytCheckService {

	/**
	 * ������ҽͨ�Ű�
	 * @param checkid
	 * @param operator
	 * @param operatorname
	 * @param remark
	 * @param dck
	 * @param mak
	 * @return
	 */
	public ReturnValue insertMytArrawork(int checkid, int operator,
			String operatorname, String remark, DataCheckBean parDcBean,
			MytArraworkBean parMaBean);
	
	/**
	 * �޸���ҽͨ�Ű�
	 * @param checkid
	 * @param remark
	 * @param operator
	 * @param operatorname
	 * @param parDcBean
	 * @param parMawBean
	 * @return
	 */
	public ReturnValue updateMytArrawork(int checkid, String remark,
			int operator, String operatorname, DataCheckBean parDcBean,
			MytArraworkWBean parMawBean);
	
	/**
	 * ��ͬ��
	 * @param remark
	 * @param checkid
	 * @param operator
	 * @param operatorname
	 * @return
	 */
	public ServiceResult<Integer> noAgree(String remark, int checkid, int operator,
			String operatorname);
	
	/**
	 * 
	 * @param checkID
	 * @param remark
	 * @param operatorID
	 * @param operatorName
	 * @return
	 */
	public ReturnValue effect(int checkID, String remark, int operatorID,
			String operatorName);
}
