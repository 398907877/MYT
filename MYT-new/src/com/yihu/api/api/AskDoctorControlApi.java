package com.yihu.api.api;

import com.coreframework.ioc.By;
import com.yihu.api.impl.AskDoctorControlApiImpl;
import com.yihu.wsgw.api.InterfaceMessage;

@By(AskDoctorControlApiImpl.class)
public interface AskDoctorControlApi {
	public String overUserFreeCount(InterfaceMessage msg);
	public String getDoctorFreeCount(InterfaceMessage msg);
	public String getDoctorFreeCountForApp(InterfaceMessage msg);
}
