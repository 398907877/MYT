package com.yihu.myt;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.DoctorAccountService;
import com.yihu.myt.vo.DoctorAccountBean;

@By(DoctorAccountService.class)
public interface IDoctorAccountService {
	public ServiceResult<DoctorAccountBean> getDoctorAcc(
			DoctorAccountBean doctor);
	public ReturnValue Update(DoctorAccountBean doctorAcc);
	public ReturnValue Add(DoctorAccountBean doctorAcc);
}
