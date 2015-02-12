package com.yihu.myt.vo;public class ConsWaterFollowVo{	private Integer waterFollowID;  

	private Integer consWaterID;  

	private Integer follwType;  

	private String follwResult;  
		private String inputUserID;		private String inputUserName;	private String inputTime;			public String getInputTime() {		return inputTime;	}	public void setInputTime(String inputTime) {		this.inputTime = inputTime;	}	public String getInputUserID() {		return inputUserID;	}	public void setInputUserID(String inputUserID) {		this.inputUserID = inputUserID;	}	public String getInputUserName() {		return inputUserName;	}	public void setInputUserName(String inputUserName) {		this.inputUserName = inputUserName;	}	
	public Integer getWaterFollowID(){
		return this.waterFollowID;
	}
	public void setWaterFollowID(Integer waterFollowID){
		this.waterFollowID=waterFollowID;
	}

	public Integer getConsWaterID(){
		return this.consWaterID;
	}
	public void setConsWaterID(Integer consWaterID){
		this.consWaterID=consWaterID;
	}

	public Integer getFollwType(){
		return this.follwType;
	}
	public void setFollwType(Integer follwType){
		this.follwType=follwType;
	}

	public String getFollwResult(){
		return this.follwResult;
	}
	public void setFollwResult(String follwResult){
		this.follwResult=follwResult;
	}

}