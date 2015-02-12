package com.yihu.myt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coreframework.remoting.HandlerThreadTimeOut;
import com.coreframework.remoting.Server;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Request;
import com.coreframework.remoting.reflect.Rpc;

/**
 * 
 * @author wangfeng
 * @company yihu.com
 * 2012-8-2上午10:57:02
 */
public class StartServlet extends HttpServlet {

	
	//private static final Log log = LogFactory.getLog(ActionServlet2.class);
	//private Runtime   rt   =   Runtime.getRuntime();   
	//private static final Log actionLog = LogFactory.
	public StartServlet() {
		super();
	}
	
	public static void main(String args[]){
		StartServlet ss  = new StartServlet();
		try {
			ss.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws ServletException {

		
	
		try 
		{
			Url  url=ConfigUtil.getInstance().getCenterServerUrl();
			if(url!=null)
			{
				Rpc.serverStart(url);
			}
			else
			{
				int port=ConfigUtil.getInstance().getPort();
				if(port!=0)
				{
					Server  server=Server.getInstance(port);
					server.setHandlerTimeOut(new HandlerThreadTimeOut(){

						public int getTimeOut(Request req) {
							// TODO Auto-generated method stub
							if(req.getRemoteInterfaceClassName().indexOf("http")>=0)
							{
								return 8;
							}
							return 0;
							
						}});
					server.start();
				}
			}
			Url  logUrl=ConfigUtil.getInstance().getLogUrl();
			if(logUrl!=null)
			{
				com.coreframework.log.LogHandler.execute(10, logUrl);
			}
		} catch (Exception e) {
			
			System.exit(0);
		}
		
		//SessionWatch  runnable=new SessionWatch();
		//Thread  thread=new Thread(runnable);
		//thread.setName("db-SessionWatch-Thread");
		//thread.start();
		
		//连上管理中心获取本地服务端口
		/*
		try {
			Rpc.serverStart(ConfigUtil.getInstance().getCenterServerUrl());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LogHandler.execute(new LogQueueDealRunnable(10, new LogDeal(){

			@Override
			public void exec(LogInfo info) {
				// TODO Auto-generated method stub
				FileOper.write("c://"+info.getModuleName()+".txt", info.getContent().toString()+"\r\n", true);
			}}){});
			*/
		
	}

	public void destroy() {
		super.destroy();
		//rt=null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	
	}

	
	

}
