package com.yihu.myt.vo;

import java.util.Date;

public class DoctorAccountBean {
	Integer da_id;
	Integer doctorUid;
	String doctorName;
	String loginId;
	String pwd;
	Integer balance;
	Date createtime;
	Integer state;
	Date updatetime;
	Integer loginNum;
	
	public Integer getDa_id() {
		return da_id;
	}
	public void setDa_id(Integer da_id) {
		this.da_id = da_id;
	}
	public Integer getDoctorUid() {
		return doctorUid;
	}
	public void setDoctorUid(Integer doctorUid) {
		this.doctorUid = doctorUid;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	
	
}
