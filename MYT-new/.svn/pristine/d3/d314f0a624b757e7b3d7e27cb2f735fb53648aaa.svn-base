/**
 * 
 */
package com.yihu.myt.service;

import java.util.HashMap;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.AccCardtype;

/**
 * @author Administrator
 *
 */
public class ConfigManage {


	private  static  HashMap  configMap;
	public static void refresh()
	{
		if(configMap!=null)
		{
			configMap.clear();
		}
	}
	public static void refresh(int cardtypesn)
	{
		if(configMap!=null)
		{
			configMap.remove(Integer.toString(cardtypesn));
		}
	}
	public static String[] get(int cardtypesn)
	{
		put(cardtypesn);
		if(configMap==null)
		{
			return null;
		}
		return (String[])configMap.get(Integer.toString(cardtypesn));
	}
	private synchronized static void put(int cardtypesn)
	{
		if(configMap==null)
		{
			configMap=new HashMap();
		}
		if(!configMap.containsKey(Integer.toString(cardtypesn)))
		{
				
			try {
				Sql sql = DB.me().createSql(MySqlNameEnum.getAccCardtypeSql);
				sql.addParamValue(cardtypesn);
				AccCardtype obj = DB.me().queryForBean(MyDatabaseEnum.boss, sql, AccCardtype.class);
				
				if(obj!=null)
				{
					configMap.put(Integer.toString(cardtypesn), new String[]{obj.getFeexml().replaceAll("\r\n\t", ""),obj.getBusinessrule().replaceAll("\r\n\t", ""),Integer.toString(obj.getOrgid())});
				}
			} catch (Exception e) {
				
				System.out.println("ConfigManage.put error"+e.getMessage());
			}
			
		}
		
	}
}
