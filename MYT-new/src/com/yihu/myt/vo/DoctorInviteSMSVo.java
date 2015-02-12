package com.yihu.myt.vo;public class DoctorInviteSMSVo{	private Integer ID;  

	private String DISMS_CreateTime;  

	private String DISMS_Title;  

	private String DISMS_Content;  

	private Integer DISMS_Status;  

	private Integer DISMS_Type;  

	private String DISMS_SendTEL;  

	private Integer DISMS_SendID;  

	private String DISMS_ReceiveTEL;  

	private Integer DISMS_ReceiveID;  

	public Integer getID(){
		return this.ID;
	}
	public void setID(Integer ID){
		this.ID=ID;
	}

	public String getDISMS_CreateTime(){
		return this.DISMS_CreateTime;
	}
	public void setDISMS_CreateTime(String DISMS_CreateTime){
		this.DISMS_CreateTime=DISMS_CreateTime;
	}

	public String getDISMS_Title(){
		return this.DISMS_Title;
	}
	public void setDISMS_Title(String DISMS_Title){
		this.DISMS_Title=DISMS_Title;
	}

	public String getDISMS_Content(){
		return this.DISMS_Content;
	}
	public void setDISMS_Content(String DISMS_Content){
		this.DISMS_Content=DISMS_Content;
	}

	public Integer getDISMS_Status(){
		return this.DISMS_Status;
	}
	public void setDISMS_Status(Integer DISMS_Status){
		this.DISMS_Status=DISMS_Status;
	}

	public Integer getDISMS_Type(){
		return this.DISMS_Type;
	}
	public void setDISMS_Type(Integer DISMS_Type){
		this.DISMS_Type=DISMS_Type;
	}

	public String getDISMS_SendTEL(){
		return this.DISMS_SendTEL;
	}
	public void setDISMS_SendTEL(String DISMS_SendTEL){
		this.DISMS_SendTEL=DISMS_SendTEL;
	}

	public Integer getDISMS_SendID(){
		return this.DISMS_SendID;
	}
	public void setDISMS_SendID(Integer DISMS_SendID){
		this.DISMS_SendID=DISMS_SendID;
	}

	public String getDISMS_ReceiveTEL(){
		return this.DISMS_ReceiveTEL;
	}
	public void setDISMS_ReceiveTEL(String DISMS_ReceiveTEL){
		this.DISMS_ReceiveTEL=DISMS_ReceiveTEL;
	}

	public Integer getDISMS_ReceiveID(){
		return this.DISMS_ReceiveID;
	}
	public void setDISMS_ReceiveID(Integer DISMS_ReceiveID){
		this.DISMS_ReceiveID=DISMS_ReceiveID;
	}

}