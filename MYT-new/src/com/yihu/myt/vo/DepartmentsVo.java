package com.yihu.myt.vo;public class DepartmentsVo{	private Integer DEPART_ID;  

	private String DEPART_CreateTime;  

	private Integer ASK_QuesID;  

	private Integer ASK_DepartIDOne;  

	private String ASK_DepartIDTwo;  

	private Integer DEPART_OperatorType;  

	private String ASK_DepartNameOne;  

	private String ASK_DepartNameTwo;  
	private Integer DEPART_Status;		
	public Integer getDEPART_Status() {		return DEPART_Status;	}	public void setDEPART_Status(Integer dEPART_Status) {		DEPART_Status = dEPART_Status;	}	public Integer getDEPART_ID(){
		return this.DEPART_ID;
	}
	public void setDEPART_ID(Integer DEPART_ID){
		this.DEPART_ID=DEPART_ID;
	}

	public String getDEPART_CreateTime(){
		return this.DEPART_CreateTime;
	}
	public void setDEPART_CreateTime(String DEPART_CreateTime){
		this.DEPART_CreateTime=DEPART_CreateTime;
	}

	public Integer getASK_QuesID(){
		return this.ASK_QuesID;
	}
	public void setASK_QuesID(Integer ASK_QuesID){
		this.ASK_QuesID=ASK_QuesID;
	}

	public Integer getASK_DepartIDOne(){
		return this.ASK_DepartIDOne;
	}
	public void setASK_DepartIDOne(Integer ASK_DepartIDOne){
		this.ASK_DepartIDOne=ASK_DepartIDOne;
	}

	public String getASK_DepartIDTwo(){
		return this.ASK_DepartIDTwo;
	}
	public void setASK_DepartIDTwo(String ASK_DepartIDTwo){
		this.ASK_DepartIDTwo=ASK_DepartIDTwo;
	}

	public Integer getDEPART_OperatorType(){
		return this.DEPART_OperatorType;
	}
	public void setDEPART_OperatorType(Integer DEPART_OperatorType){
		this.DEPART_OperatorType=DEPART_OperatorType;
	}

	public String getASK_DepartNameOne(){
		return this.ASK_DepartNameOne;
	}
	public void setASK_DepartNameOne(String ASK_DepartNameOne){
		this.ASK_DepartNameOne=ASK_DepartNameOne;
	}

	public String getASK_DepartNameTwo(){
		return this.ASK_DepartNameTwo;
	}
	public void setASK_DepartNameTwo(String ASK_DepartNameTwo){
		this.ASK_DepartNameTwo=ASK_DepartNameTwo;
	}

}