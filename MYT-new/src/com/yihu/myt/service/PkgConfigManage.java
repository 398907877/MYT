/**
 * 
 */
package com.yihu.myt.service;

import java.util.HashMap;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.AccPackage;

/**
 * @author Administrator
 * 
 */
public class PkgConfigManage {

	private static HashMap configMap;

	public static void refresh() {
		if (configMap != null) {
			configMap.clear();
		}
	}

	public static void refresh(int cardtypesn) {
		if (configMap != null) {
			configMap.remove(Integer.toString(cardtypesn));
		}
	}

	public static String[] get(int pkgid) {
		put(pkgid);
		if (configMap == null) {
			return null;
		}
		return (String[]) configMap.get(pkgid);
	}

	private synchronized static void put(int pkgid) {
		if (configMap == null) {
			configMap = new HashMap();
		}
		if (!configMap.containsKey(pkgid)) {

			try {
				Sql sql = DB.me().createSql(MySqlNameEnum.getAccPackageSql);
				sql.addParamValue(pkgid);
				AccPackage obj = DB.me().queryForBean(MyDatabaseEnum.boss, sql, AccPackage.class);
				if (obj != null) {
					if (obj.getChargeXml() == null) {
						obj.setChargeXml("");
					}
					if (obj.getRuleXml() == null) {
						obj.setRuleXml("");
					}
					String[] values = new String[] { obj.getChargeXml().replaceAll("\r\n\t", ""),
							obj.getRuleXml().replaceAll("\r\n\t", ""),
							Integer.toString(obj.getOrgId()) };
					configMap.put(pkgid, values);
				}
			} catch (Exception e) {

				System.out.println("PkgConfigManage.put error" + e.getMessage());
			}

		}

	}
}
