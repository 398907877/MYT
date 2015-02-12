package com.yihu.myt.vo;public class UserDetailVo{	private Integer UD_ID;  

	private Integer ASK_Type;  

	private Integer ASK_AccountID;  

	private Integer UD_Status;  

	private String UD_LastLoginTime;  

	private String UD_LastLoginIP;  

	private Integer UD_LoginCount;  

	public Integer getUD_ID(){
		return this.UD_ID;
	}
	public void setUD_ID(Integer UD_ID){
		this.UD_ID=UD_ID;
	}

	public Integer getASK_Type(){
		return this.ASK_Type;
	}
	public void setASK_Type(Integer ASK_Type){
		this.ASK_Type=ASK_Type;
	}

	public Integer getASK_AccountID(){
		return this.ASK_AccountID;
	}
	public void setASK_AccountID(Integer ASK_AccountID){
		this.ASK_AccountID=ASK_AccountID;
	}

	public Integer getUD_Status(){
		return this.UD_Status;
	}
	public void setUD_Status(Integer UD_Status){
		this.UD_Status=UD_Status;
	}

	public String getUD_LastLoginTime(){
		return this.UD_LastLoginTime;
	}
	public void setUD_LastLoginTime(String UD_LastLoginTime){
		this.UD_LastLoginTime=UD_LastLoginTime;
	}

	public String getUD_LastLoginIP(){
		return this.UD_LastLoginIP;
	}
	public void setUD_LastLoginIP(String UD_LastLoginIP){
		this.UD_LastLoginIP=UD_LastLoginIP;
	}

	public Integer getUD_LoginCount(){
		return this.UD_LoginCount;
	}
	public void setUD_LoginCount(Integer UD_LoginCount){
		this.UD_LoginCount=UD_LoginCount;
	}

}