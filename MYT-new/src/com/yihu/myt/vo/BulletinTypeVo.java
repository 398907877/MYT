package com.yihu.myt.vo;public class BulletinTypeVo{	private Integer BULLTYPE_ID;  

	private String BULLTYPE_Name;  

	private Integer BULLTYPE_Status;  

	private Integer BULLTYPE_OperatorID;  

	private String BULLTYPE_OperatorName;  

	private String BULLTYPE_CreateTime;  

	public Integer getBULLTYPE_ID(){
		return this.BULLTYPE_ID;
	}
	public void setBULLTYPE_ID(Integer BULLTYPE_ID){
		this.BULLTYPE_ID=BULLTYPE_ID;
	}

	public String getBULLTYPE_Name(){
		return this.BULLTYPE_Name;
	}
	public void setBULLTYPE_Name(String BULLTYPE_Name){
		this.BULLTYPE_Name=BULLTYPE_Name;
	}

	public Integer getBULLTYPE_Status(){
		return this.BULLTYPE_Status;
	}
	public void setBULLTYPE_Status(Integer BULLTYPE_Status){
		this.BULLTYPE_Status=BULLTYPE_Status;
	}

	public Integer getBULLTYPE_OperatorID(){
		return this.BULLTYPE_OperatorID;
	}
	public void setBULLTYPE_OperatorID(Integer BULLTYPE_OperatorID){
		this.BULLTYPE_OperatorID=BULLTYPE_OperatorID;
	}

	public String getBULLTYPE_OperatorName(){
		return this.BULLTYPE_OperatorName;
	}
	public void setBULLTYPE_OperatorName(String BULLTYPE_OperatorName){
		this.BULLTYPE_OperatorName=BULLTYPE_OperatorName;
	}

	public String getBULLTYPE_CreateTime(){
		return this.BULLTYPE_CreateTime;
	}
	public void setBULLTYPE_CreateTime(String BULLTYPE_CreateTime){
		this.BULLTYPE_CreateTime=BULLTYPE_CreateTime;
	}

}