package com.yihu.api.api;

import com.yihu.api.impl.DoctorBillDataApiImpl;
import com.coreframework.ioc.By;
import com.coreframework.vo.ServiceResult;
import com.yihu.wsgw.api.InterfaceMessage;

@By(DoctorBillDataApiImpl.class)
public interface DoctorBillDataApi {
	public  ServiceResult<String> getDoctorBillMyt(String jsonStr);
	public ServiceResult<String> getDoctorBillWys(String jsonStr) ;
	public ServiceResult<String> getDoctorCreditsRecord(String jsonStr);
	public String getOrderContents(InterfaceMessage msg);
	public String overQuesRefund(InterfaceMessage msg) ;
	public String overQuesRefundforAPI(InterfaceMessage msg);
}
