package com.yihu.myt.vo;public class LoginPlatformVo{	private Integer LPID;  

	private String OpertTime;  

	private Integer LoginType;  

	private Integer LoginID;  

	private Integer LoginPlatform;  

	private Integer LoginUnitCount;  

	public Integer getLPID(){
		return this.LPID;
	}
	public void setLPID(Integer LPID){
		this.LPID=LPID;
	}

	public String getOpertTime(){
		return this.OpertTime;
	}
	public void setOpertTime(String OpertTime){
		this.OpertTime=OpertTime;
	}

	public Integer getLoginType(){
		return this.LoginType;
	}
	public void setLoginType(Integer LoginType){
		this.LoginType=LoginType;
	}

	public Integer getLoginID(){
		return this.LoginID;
	}
	public void setLoginID(Integer LoginID){
		this.LoginID=LoginID;
	}

	public Integer getLoginPlatform(){
		return this.LoginPlatform;
	}
	public void setLoginPlatform(Integer LoginPlatform){
		this.LoginPlatform=LoginPlatform;
	}

	public Integer getLoginUnitCount(){
		return this.LoginUnitCount;
	}
	public void setLoginUnitCount(Integer LoginUnitCount){
		this.LoginUnitCount=LoginUnitCount;
	}

}