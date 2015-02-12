package com.yihu.myt.vo;public class DiseaseVo{	private Integer DISEASE_ID;  

	private String DISEASE_CreateTime;  

	private Integer ASK_QuesID;  

	private Integer ASK_DiseaseID;  

	private String ASK_DiseaseName;  

	private Integer DISEASE_OperatorType;  
	private Integer  DISEASE_Status;		
	public Integer getDISEASE_Status() {		return DISEASE_Status;	}	public void setDISEASE_Status(Integer dISEASE_Status) {		DISEASE_Status = dISEASE_Status;	}	public Integer getDISEASE_ID(){
		return this.DISEASE_ID;
	}
	public void setDISEASE_ID(Integer DISEASE_ID){
		this.DISEASE_ID=DISEASE_ID;
	}

	public String getDISEASE_CreateTime(){
		return this.DISEASE_CreateTime;
	}
	public void setDISEASE_CreateTime(String DISEASE_CreateTime){
		this.DISEASE_CreateTime=DISEASE_CreateTime;
	}

	public Integer getASK_QuesID(){
		return this.ASK_QuesID;
	}
	public void setASK_QuesID(Integer ASK_QuesID){
		this.ASK_QuesID=ASK_QuesID;
	}

	public Integer getASK_DiseaseID(){
		return this.ASK_DiseaseID;
	}
	public void setASK_DiseaseID(Integer ASK_DiseaseID){
		this.ASK_DiseaseID=ASK_DiseaseID;
	}

	public String getASK_DiseaseName(){
		return this.ASK_DiseaseName;
	}
	public void setASK_DiseaseName(String ASK_DiseaseName){
		this.ASK_DiseaseName=ASK_DiseaseName;
	}

	public Integer getDISEASE_OperatorType(){
		return this.DISEASE_OperatorType;
	}
	public void setDISEASE_OperatorType(Integer DISEASE_OperatorType){
		this.DISEASE_OperatorType=DISEASE_OperatorType;
	}

}