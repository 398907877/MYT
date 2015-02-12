package com.yihu.myt;

import com.coreframework.ioc.By;
import com.coreframework.vo.ReturnValue;
import com.yihu.myt.service.DataCheckService;

@By(DataCheckService.class)
public interface IDataCheckService {

	public ReturnValue pass(String remark, int checkid, int operator, String operatorname);
	
	public ReturnValue noPass(String remark, int checkid, int operator, String operatorname);
	
}
