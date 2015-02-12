package com.yihu;

import java.io.File;
import java.io.FileWriter;

import com.coreframework.remoting.standard.DateOper;

public class DebugLogHelper {

	public static void info(String info) {
		try {
			File fileDir = new File("c:/myt_test");
			if (!fileDir.exists())
				fileDir.mkdirs();
			File record = new File("c:/myt_test/sql.txt");
			if (!record.exists()) {
				record.createNewFile();
			}
			String iv = new Exception().getStackTrace()[1].getClassName()+"."+new Exception().getStackTrace()[1].getMethodName();
			FileWriter resultFile = new FileWriter(record, true);
			resultFile.append(DateOper.getNow("yyyy-MM-dd HH:mm:ss")+"_"+iv+"_"+info+"\n");
			resultFile.flush();
			resultFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
