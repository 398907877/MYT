package com.yihu.myt.vo;public class CreditsRecordVo{	private Integer CR_ID;  

	private Integer ASK_QuesID;  

	private Integer ASK_DoctorAccountID;  

	private Integer CR_Credits;  

	private Integer CR_CreditsType;  

	private String CR_CreateTime;  

	private Integer ASK_DoctorID;  		private Integer CR_Type;
	
	public Integer getCR_Type() {		return CR_Type;	}	public void setCR_Type(Integer cR_Type) {		CR_Type = cR_Type;	}	public Integer getCR_ID(){
		return this.CR_ID;
	}
	public void setCR_ID(Integer CR_ID){
		this.CR_ID=CR_ID;
	}

	public Integer getASK_QuesID(){
		return this.ASK_QuesID;
	}
	public void setASK_QuesID(Integer ASK_QuesID){
		this.ASK_QuesID=ASK_QuesID;
	}

	public Integer getASK_DoctorAccountID(){
		return this.ASK_DoctorAccountID;
	}
	public void setASK_DoctorAccountID(Integer ASK_DoctorAccountID){
		this.ASK_DoctorAccountID=ASK_DoctorAccountID;
	}

	public Integer getCR_Credits(){
		return this.CR_Credits;
	}
	public void setCR_Credits(Integer CR_Credits){
		this.CR_Credits=CR_Credits;
	}

	public Integer getCR_CreditsType(){
		return this.CR_CreditsType;
	}
	public void setCR_CreditsType(Integer CR_CreditsType){
		this.CR_CreditsType=CR_CreditsType;
	}

	public String getCR_CreateTime(){
		return this.CR_CreateTime;
	}
	public void setCR_CreateTime(String CR_CreateTime){
		this.CR_CreateTime=CR_CreateTime;
	}

	public Integer getASK_DoctorID(){
		return this.ASK_DoctorID;
	}
	public void setASK_DoctorID(Integer ASK_DoctorID){
		this.ASK_DoctorID=ASK_DoctorID;
	}

}