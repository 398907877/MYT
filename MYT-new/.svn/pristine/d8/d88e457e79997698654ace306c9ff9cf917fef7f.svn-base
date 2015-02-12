package com.yihu.myt.service;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.vo.ReturnValue;
import com.yihu.myt.IChargeService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;

public class ChargeService implements IChargeService {

	public ReturnValue settled(String doctorIds) {

		try {
			// 更新不结算设置
			Sql sql = DB.me().createSql(MySqlNameEnum.unsettled);
			sql.addParamValue(doctorIds);
			Integer r = DB.me().update(MyDatabaseEnum.boss, sql);
			if (r == null || r == 0)
				return new ReturnValue(-1, "本次不结算设置失败。");

			return new ReturnValue(1, "本次不结算设置成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue unsettled(String doctorIds) {

		try {
			// 更新已结算设置
			Sql sql = DB.me().createSql(MySqlNameEnum.settled);
			sql.addParamValue(doctorIds);
			Integer r = DB.me().update(MyDatabaseEnum.boss, sql);
			if (r == null || r == 0)
				return new ReturnValue(-1, "已结算设置失败。");

			return new ReturnValue(1, "已结算设置成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

}
