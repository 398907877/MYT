package com.yihu.myt.vo;public class DoctorClickWaterVo{	private Integer WaterID;  

	private Integer DoctorID;  

	private Integer UserID;  

	private String IP;  

	private String CreatDateTime;  

	private Integer State;  

	public Integer getWaterID(){
		return this.WaterID;
	}
	public void setWaterID(Integer WaterID){
		this.WaterID=WaterID;
	}

	public Integer getDoctorID(){
		return this.DoctorID;
	}
	public void setDoctorID(Integer DoctorID){
		this.DoctorID=DoctorID;
	}

	public Integer getUserID(){
		return this.UserID;
	}
	public void setUserID(Integer UserID){
		this.UserID=UserID;
	}

	public String getIP(){
		return this.IP;
	}
	public void setIP(String IP){
		this.IP=IP;
	}

	public String getCreatDateTime(){
		return this.CreatDateTime;
	}
	public void setCreatDateTime(String CreatDateTime){
		this.CreatDateTime=CreatDateTime;
	}

	public Integer getState(){
		return this.State;
	}
	public void setState(Integer State){
		this.State=State;
	}

}