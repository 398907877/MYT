/**
 * 
 */
package com.yihu.myt.service;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Administrator
 * 
 */
public class DoctorOnLine {

	private static HashMap map = new HashMap();

	public synchronized static void put(String operconfId) {
		if (map.get(operconfId) == null) {
			map.put(operconfId, new Date());
			//System.out.println(operconfId+"Ê¾Ã¦****************");
		}
	}

	public synchronized static void remove(String operconfId) {
		map.remove(operconfId);
		//System.out.println(operconfId+"Ê¾Ã¦½áÊø****************");
	}
	
	public static Object get(int operconfId) {
		return map.get(operconfId);
	}
}
