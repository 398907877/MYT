package com.yihu.myt.vo;public class UserFreeCountVo{	private Integer ufcId;  

	private Integer userId;  

	private Integer dfcId;  	
	private String expirationTime;	
	private String beginTime;  

	private String endTime;  		private Integer ifOver;
	
	public Integer getIfOver() {		return ifOver;	}	public void setIfOver(Integer ifOver) {		this.ifOver = ifOver;	}	public String getExpirationTime() {		return expirationTime;	}	public void setExpirationTime(String expirationTime) {		this.expirationTime = expirationTime;	}	public Integer getUfcId(){
		return this.ufcId;
	}
	public void setUfcId(Integer ufcId){
		this.ufcId=ufcId;
	}

	public Integer getUserId(){
		return this.userId;
	}
	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getDfcId(){
		return this.dfcId;
	}
	public void setDfcId(Integer dfcId){
		this.dfcId=dfcId;
	}

	public String getBeginTime(){
		return this.beginTime;
	}
	public void setBeginTime(String beginTime){
		this.beginTime=beginTime;
	}

	public String getEndTime(){
		return this.endTime;
	}
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}

}