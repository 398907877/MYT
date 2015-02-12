package com.yihu.api.api;

import com.yihu.api.impl.MytDoctorOperconfidApiImpl;
import com.coreframework.ioc.By;
import com.coreframework.vo.ServiceResult;
import com.yihu.wsgw.api.InterfaceMessage;


@By(MytDoctorOperconfidApiImpl.class)
public interface MytDoctorOperconfidApi {
	public ServiceResult<String> getDoctorMytPhones(String jsonStr) ;
	public ServiceResult<String> saveDoctorMytPhones(String jsonStr) ;
	public String getMytDocList(InterfaceMessage msg);
	public String getDocUserListMytForUser(InterfaceMessage msg);
	public String upBookEnrol (InterfaceMessage msg);
	public String getHBPhoneCount(InterfaceMessage msg);
	public String upHBPhoneCount(InterfaceMessage msg);
}
