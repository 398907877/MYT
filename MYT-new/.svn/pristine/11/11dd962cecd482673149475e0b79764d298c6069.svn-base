/**
 * 
 */
package com.yihu.main;

import com.coreframework.db.DB;
import com.coreframework.log.Logger;
import com.coreframework.remoting.HandlerThreadTimeOut;
import com.coreframework.remoting.Server;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Request;
import com.yihu.config.ConfigReader;
import com.yihu.event.api.DefaultEventListener;
import com.yihu.event.api.EventListener;
import com.yihu.monitor.sdk.MonitorClient;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.util.DBCache;

/**
 * @author chenw
 * @company yihu.com
 * 2012-8-22上午09:53:16
 */
public class ServerConsole {

	public static void main(String[] args) {
		
		System.out.println("ServerConsole");
		try 
		{
			Logger.get().setDebugEnable(true);
//			Url  url=ConfigUtil.getInstance().getCenterServerUrl();
//			
//			if(url!=null)
//			{
//				Rpc.serverStart(url);
//			}
//			else
//			{
				int port=ConfigUtil.getInstance().getPort();
				if(port!=0)
				{
					Server  server=Server.getInstance(port);
					server.setHandlerTimeOut(new HandlerThreadTimeOut(){

						@Override
						public int getTimeOut(Request req) {
							// TODO Auto-generated method stub
							if(req.getRemoteInterfaceClassName().indexOf("http")>=0)
							{
								return 8;
							}
							return 0;
							
						}});
					server.addInstance(EventListener.class, new DefaultEventListener());//添加EventListener的实例
					ConfigReader.me(ConfigUtil.getInstance().getCenterServerUrl().toString()).printNamePair();
					server.start();
				}
			//}
			Url  logUrl=ConfigUtil.getInstance().getLogUrl();
			if(logUrl!=null)
			{
				com.coreframework.log.LogHandler.execute(10, logUrl);
			}
			DB.me();
			DBCache.initCacheByDB();
			//MonitorClient.register("10.0.100.154","MYT", "问医生产品", 8908,"健康之路");
			//			System.out.println("-------------------"+DB.me().getConnection(MyDatabaseEnum.BaseInfo).getConn());
			//DB.me().addDataSource(MyDatabaseEnum.YiHuNet2008.toString(), "YiHuNet2008", "10.0.200.6", 1433, "sa", "123");

			//System.out.println("-------------------"+DB.me().getConnection(MyDatabaseEnum.YiHuNet2008).getConn());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
