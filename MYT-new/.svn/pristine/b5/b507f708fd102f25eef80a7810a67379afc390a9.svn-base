package com.yihu.myt.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coreframework.util.DateOper;

public class PostDAO {
	
	public static String dopost(String posturl,String content){
		String results="";
		try {
	//		String content = "GUID=15997459762&AreaId=420000&Keyword=&CurrentPage=0&PageSize=5";
	//		URL url = new URL("http://116.228.55.5:8180/bstHealth/portal.do?functionid=30009");
			URL url = new URL(posturl);
			HttpURLConnection con =  (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(8000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(content);
			osw.flush();
			osw.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));//璁剧疆缂栫爜,鍚﹀垯涓枃涔辩爜
	//		System.out.println(reader.readLine()+"****");
			String line = "";
			while ((line = reader.readLine()) != null){
				//System.out.println(line);
				if(line!=null&&!line.equals("")){
					results = line;
				}
			}
			reader.close();
			con.disconnect();
		} catch (Exception e) {
			 //把错误写入日志
			writeLog(e.toString());
	//		e.printStackTrace();
		}
		return results;
	}

	//把错误信息写入日志
	public static void writeLog(String errorCon){
		try {
			String folderPath = "D:\\logs\\"+DateOper.getNowDate()+"ctghtlogic";
			File f = new File(folderPath);
			if(!f.exists()){
				f.mkdir();
			}
			File documentPath = new File(folderPath+"\\"+DateOper.getNowDate()+".txt");
			if(!documentPath.exists()){
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new  FileOutputStream(documentPath)));
				bw.write(DateOper.getNowDateTime()+"错误信息:"+errorCon);
				bw.close();
			}else{
				String str= System.getProperty("line.separator");
				FileWriter fw = new FileWriter(documentPath,true);
				fw.write(str+DateOper.getNowDateTime()+"信息:"+errorCon);
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		try {
			PostDAO dao = new PostDAO();
			String result = dao.dopost("http://116.228.55.5:8180/bstHealth/portal.do?functionid=30009", "GUID=15997459762&HospitalId=1214&Keyword=&CurrentPage=0&PageSize=5");
			System.out.println(result);
//			JSONObject obj = new JSONObject(result);
//			JSONArray obj1 = obj.getJSONArray("Items");
//			System.out.println(obj1.getJSONObject(0).getString("HospitalAddr"));
		//	JSONObject obj1 = new JSONObject(obj.getString("Items"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
