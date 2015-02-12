package com.yihu.myt.vo;public class DoctorPriceRecordVo{	private Integer DPR_ID;  

	private Integer ASK_DoctorID;  

	private String DPR_CreateTime;  

	private Integer DPR_Status;  

	private Integer DPR_Price;  

	public Integer getDPR_ID(){
		return this.DPR_ID;
	}
	public void setDPR_ID(Integer DPR_ID){
		this.DPR_ID=DPR_ID;
	}

	public Integer getASK_DoctorID(){
		return this.ASK_DoctorID;
	}
	public void setASK_DoctorID(Integer ASK_DoctorID){
		this.ASK_DoctorID=ASK_DoctorID;
	}

	public String getDPR_CreateTime(){
		return this.DPR_CreateTime;
	}
	public void setDPR_CreateTime(String DPR_CreateTime){
		this.DPR_CreateTime=DPR_CreateTime;
	}

	public Integer getDPR_Status(){
		return this.DPR_Status;
	}
	public void setDPR_Status(Integer DPR_Status){
		this.DPR_Status=DPR_Status;
	}

	public Integer getDPR_Price(){
		return this.DPR_Price;
	}
	public void setDPR_Price(Integer DPR_Price){
		this.DPR_Price=DPR_Price;
	}

}