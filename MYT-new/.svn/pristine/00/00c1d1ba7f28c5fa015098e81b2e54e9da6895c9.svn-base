package com.yihu.myt;


import java.util.List;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.service.DoctorBillService;
import com.yihu.myt.vo.DoctorBillBean;

@By(DoctorBillService.class)
public interface IDoctorBillService {
	public ReturnValue add(DoctorBillBean dcBill);
	public ReturnValue update(DoctorBillBean dcBill);
	public ServiceResult<List<DoctorBillBean>> getResult(DoctorBillBean dcBill);
}
