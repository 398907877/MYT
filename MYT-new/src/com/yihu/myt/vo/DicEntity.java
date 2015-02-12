package com.yihu.myt.vo;

import java.io.Serializable;

public class DicEntity  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String  businName;
	private  String businID;
	private  String status;
	private  String privilege;
	private  String businTypeID;
	
	
	
	
	
	public DicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBusinName() {
		return businName;
	}
	public void setBusinName(String businName) {
		this.businName = businName;
	}
	public String getBusinID() {
		return businID;
	}
	public void setBusinID(String businID) {
		this.businID = businID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getBusinTypeID() {
		return businTypeID;
	}
	public void setBusinTypeID(String businTypeID) {
		this.businTypeID = businTypeID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
