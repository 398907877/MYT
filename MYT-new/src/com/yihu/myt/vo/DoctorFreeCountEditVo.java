package com.yihu.myt.vo;public class DoctorFreeCountEditVo{	private Integer dfcEdit;  

	private Integer dfcId;  

	private Integer freeCount;  

	private Integer ifChange;  

	private String beginTime;  

	private String endTime;  

	public Integer getDfcEdit(){
		return this.dfcEdit;
	}
	public void setDfcEdit(Integer dfcEdit){
		this.dfcEdit=dfcEdit;
	}

	public Integer getDfcId(){
		return this.dfcId;
	}
	public void setDfcId(Integer dfcId){
		this.dfcId=dfcId;
	}

	public Integer getFreeCount(){
		return this.freeCount;
	}
	public void setFreeCount(Integer freeCount){
		this.freeCount=freeCount;
	}

	public Integer getIfChange(){
		return this.ifChange;
	}
	public void setIfChange(Integer ifChange){
		this.ifChange=ifChange;
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