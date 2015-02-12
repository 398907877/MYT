package com.yihu.myt.vo;
public class SelfHelpVo{
	private Integer ShId;  

	private String ShMobile;  

	private Integer OperConfId;  

	private String OperTime;  

	private Integer State;
	
	private Integer BussId;
	
	private Integer ShType;
	
	private String UserPhone;
  
	private String CardId;
	
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public Integer getBussId() {
		return BussId;
	}
	public void setBussId(Integer bussId) {
		BussId = bussId;
	}
	public Integer getShType() {
		return ShType;
	}
	public void setShType(Integer shType) {
		ShType = shType;
	}
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public Integer getShId(){
		return this.ShId;
	}
	public void setShId(Integer ShId){
		this.ShId=ShId;
	}

	public String getShMobile(){
		return this.ShMobile;
	}
	public void setShMobile(String ShMobile){
		this.ShMobile=ShMobile;
	}

	public Integer getOperConfId(){
		return this.OperConfId;
	}
	public void setOperConfId(Integer OperConfId){
		this.OperConfId=OperConfId;
	}

	public String getOperTime(){
		return this.OperTime;
	}
	public void setOperTime(String OperTime){
		this.OperTime=OperTime;
	}

	public Integer getState(){
		return this.State;
	}
	public void setState(Integer State){
		this.State=State;
	}

}