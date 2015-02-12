package com.yihu.myt.util;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author chana
 *
 * 2014-4-23下午2:49:48
 *
 */
public class HttpClientRequest {
	public static String accent_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
	public static String doget(String URL) throws Exception{
		// 创造httpclient实例
				HttpClient client = new HttpClient();
				//PostMethod post = new PostMethod();
				GetMethod get=new GetMethod();
				get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
			         new DefaultHttpMethodRetryHandler(3, false));  
				get.setURI(new URI(URL));
				 int  statusCode = client.executeMethod(get);   
			      // 读取 HTTP 响应内容，这里简单打印网页内容   
		          byte [] responseBody = get.getResponseBody(); // 读取为字节数组   
		        String response =  new  String(responseBody, "utf-8");   
		        System.out.println( "----------response:" +response);
//		         JSONObject json=JSONObject.fromObject(response);
//		         System.out.println(json.getString("access_token"));
				return response;
	}
	
	public static String dopost(String url,String params)throws Exception{
		 //创造httpclient实例
		DefaultHttpClient httpclient = new DefaultHttpClient();

		//创建httppost

		HttpPost httppost = new HttpPost(url);
//		    PostMethod post = new PostMethod();
		   //HttpPost post=new HttpPost(url);
		//   post.set
		httppost.setEntity(new StringEntity(params, "UTF-8")); 
		  HttpResponse response = httpclient.execute(httppost); 
//		   byte [] responseBody = httppost.get.getResponseBody(); // 读取为字节数组   
//	        String response =  new  String(responseBody, "utf-8");   
//		   String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8"); 
//		   System.out.println(jsonStr); 
//		   JSONObject object = JSON.parseObject(jsonStr); 
//		   return object.getString("errmsg");  
		  HttpEntity entity = response.getEntity();
		  System.out.println("Response content:" + EntityUtils.toString(entity,"UTF-8"));
		    return EntityUtils.toString(entity,"UTF-8");
	}
	public static void main(String[] args) {
		try {
			HttpClientRequest.doget("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
