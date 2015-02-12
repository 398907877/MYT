package com.yihu.myt.service.service;

import com.coreframework.ioc.By;
import com.yihu.myt.service.service.impl.AskDoctorNewInterfaceV1ServiceImp;
import com.yihu.myt.vo.QuesMainVo;


@By(AskDoctorNewInterfaceV1ServiceImp.class)
public interface IAskDoctorNewInterfaceV1Service {
	
	public  String  queryDoctorQueFree(int doctorID) throws Exception;
	public  String  queryHotQueByDept(String deptid,int pageIndex ,int pageSize,String startdate,String enddate) throws Exception;
	public  String  queryWaitAndNewReplyCount (int doctorUid)throws Exception;
	public  Integer  deleteQuesVoByUser (QuesMainVo vo)throws Exception;
}
