package com.yihu.myt.service;

import com.coreframework.vo.ReturnValue;
import com.yihu.myt.IDataCheckService;
import com.yihu.myt.enums.BaseDictionary;
import com.yihu.myt.mgr.DataCheckManager;

public class DataCheckService implements IDataCheckService {

	public ReturnValue noPass(String remark, int checkid, int operator,
			String operatorname) {

		return DataCheckManager.record(remark, checkid, operator, operatorname,
				BaseDictionary.CheckState.noPass);
	}

	public ReturnValue pass(String remark, int checkid, int operator,
			String operatorname) {

		return DataCheckManager.record(remark, checkid, operator, operatorname,
				BaseDictionary.CheckState.pass);
	}

}
