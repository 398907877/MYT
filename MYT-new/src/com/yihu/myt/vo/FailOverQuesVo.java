package com.yihu.myt.vo;

	private Integer failOverQuesID;  

	private String failOverUserCardID;  

	private Integer failOverUserID;  

	private Integer failOverPrice;  

	private Integer failOverDoctorID;  

	private Integer failOverType;  

	public String getFailMemo() {
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

	public String getFailOverUserCardID() {
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