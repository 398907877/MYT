package com.yihu.myt.vo;public class LoginLogVo{	private Integer LOGINLOG_ID;  

	private Integer LOGINLOG_UserID;  

	private Integer LOGINLOG_Status;  

	private Integer LOGINLOG_Type;  

	private String LOGINLOG_CreateTime;  

	private String LOGINLOG_IP;  

	public Integer getLOGINLOG_ID(){
		return this.LOGINLOG_ID;
	}
	public void setLOGINLOG_ID(Integer LOGINLOG_ID){
		this.LOGINLOG_ID=LOGINLOG_ID;
	}

	public Integer getLOGINLOG_UserID(){
		return this.LOGINLOG_UserID;
	}
	public void setLOGINLOG_UserID(Integer LOGINLOG_UserID){
		this.LOGINLOG_UserID=LOGINLOG_UserID;
	}

	public Integer getLOGINLOG_Status(){
		return this.LOGINLOG_Status;
	}
	public void setLOGINLOG_Status(Integer LOGINLOG_Status){
		this.LOGINLOG_Status=LOGINLOG_Status;
	}

	public Integer getLOGINLOG_Type(){
		return this.LOGINLOG_Type;
	}
	public void setLOGINLOG_Type(Integer LOGINLOG_Type){
		this.LOGINLOG_Type=LOGINLOG_Type;
	}

	public String getLOGINLOG_CreateTime(){
		return this.LOGINLOG_CreateTime;
	}
	public void setLOGINLOG_CreateTime(String LOGINLOG_CreateTime){
		this.LOGINLOG_CreateTime=LOGINLOG_CreateTime;
	}

	public String getLOGINLOG_IP(){
		return this.LOGINLOG_IP;
	}
	public void setLOGINLOG_IP(String LOGINLOG_IP){
		this.LOGINLOG_IP=LOGINLOG_IP;
	}

}