package com.yihu.api.impl;

import com.coreframework.remoting.Url;
import com.yihu.event.api.EventMessage;
import com.yihu.event.api.EventUtil;
import com.yihu.myt.ConfigUtil;

public class EventUtilForMYT {
	
	
	
	/**
	 * 正式线  事件地址

	
	public  static  final String   EVENTURL="10.0.100.132";
	public  static  final  Integer   EVENTPORT=7002;
		 */

	/**
	 * 测试线  事件地址
	 */
	
	public  static  final String   EVENTURL="172.18.20.121";
	public  static  final  Integer   EVENTPORT=7000;

	public static String  sendEvenForClose(String docID,String queID) throws Exception{


		
		
		EventMessage message = EventMessage.create("closeQuebackReward");
		message.add("docID", docID);
		message.add("queID", queID);
		Url url;
		url = ConfigUtil.getInstance().getUrl("url.eventGateway");
		EventUtil.sendToGateway(message, url);
		return "++++++++++发送成功  ： sendEvenForClose  ++++++++++";
		
	} 
	
	
	public static String  sendEvenForAddlove(String docID,String queID) throws Exception{
		
		
		EventMessage message = EventMessage.create("rebackaddlove");
		message.add("docID", docID);
		message.add("queID", queID);
		Url url =  ConfigUtil.getInstance().getUrl("url.eventGateway");
		EventUtil.sendToGateway(message, url);
		
		
		return "++++++++++发送成功  ： sendEvenForClose  ++++++++++";
		
	} 
	
	

}
