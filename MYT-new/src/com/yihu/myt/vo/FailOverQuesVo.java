package com.yihu.myt.vo;public class FailOverQuesVo{	private Integer failOver;  

	private Integer failOverQuesID;  

	private String failOverUserCardID;  

	private Integer failOverUserID;  

	private Integer failOverPrice;  

	private Integer failOverDoctorID;  

	private Integer failOverType;  
	private Integer failOverOrderID;		private Integer failOverStatus;		private Integer feeTemplateId;		private String failMemo;		
	public String getFailMemo() {		return failMemo;	}	public void setFailMemo(String failMemo) {		this.failMemo = failMemo;	}	public Integer getFeeTemplateId() {		return feeTemplateId;	}	public void setFeeTemplateId(Integer feeTemplateId) {		this.feeTemplateId = feeTemplateId;	}	public Integer getFailOverStatus() {		return failOverStatus;	}	public void setFailOverStatus(Integer failOverStatus) {		this.failOverStatus = failOverStatus;	}	public Integer getFailOverOrderID() {		return failOverOrderID;	}	public void setFailOverOrderID(Integer failOverOrderID) {		this.failOverOrderID = failOverOrderID;	}	public Integer getFailOver(){
		return this.failOver;
	}
	public void setFailOver(Integer failOver){
		this.failOver=failOver;
	}

	public Integer getFailOverQuesID(){
		return this.failOverQuesID;
	}
	public void setFailOverQuesID(Integer failOverQuesID){
		this.failOverQuesID=failOverQuesID;
	}

	public String getFailOverUserCardID() {		return failOverUserCardID;	}	public void setFailOverUserCardID(String failOverUserCardID) {		this.failOverUserCardID = failOverUserCardID;	}	public Integer getFailOverUserID(){
		return this.failOverUserID;
	}
	public void setFailOverUserID(Integer failOverUserID){
		this.failOverUserID=failOverUserID;
	}

	public Integer getFailOverPrice(){
		return this.failOverPrice;
	}
	public void setFailOverPrice(Integer failOverPrice){
		this.failOverPrice=failOverPrice;
	}

	public Integer getFailOverDoctorID(){
		return this.failOverDoctorID;
	}
	public void setFailOverDoctorID(Integer failOverDoctorID){
		this.failOverDoctorID=failOverDoctorID;
	}

	public Integer getFailOverType(){
		return this.failOverType;
	}
	public void setFailOverType(Integer failOverType){
		this.failOverType=failOverType;
	}

}