package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Clob;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MytDoctorViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer operconfid;
	private String orgname;
	private Integer doctorid;
	private String sevendoctorid;
	private String tendoctorid;
	private String doctorname;
	private String hospitalname;
	private String hospofficeid;
	private Integer orgid;
	private Integer cityid;
	private String hospofficename;
	private Integer state;
	private String sex;
	private Clob mazskill;
	private String doctoridiom;
	private String remark;
	private Integer doctorlevel;
	private String balancetype;
	private String paytype;
	private String sendType;

	
	public Integer getOperconfid() {
		return operconfid;
	}


	public void setOperconfid(Integer operconfid) {
		this.operconfid = operconfid;
	}


	public String getOrgname() {
		return orgname;
	}


	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}


	public Integer getDoctorid() {
		return doctorid;
	}


	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}


	public String getSevendoctorid() {
		return sevendoctorid;
	}


	public void setSevendoctorid(String sevendoctorid) {
		this.sevendoctorid = sevendoctorid;
	}


	public String getTendoctorid() {
		return tendoctorid;
	}


	public void setTendoctorid(String tendoctorid) {
		this.tendoctorid = tendoctorid;
	}


	public String getDoctorname() {
		return doctorname;
	}


	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}


	public String getHospitalname() {
		return hospitalname;
	}


	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}


	public String getHospofficeid() {
		return hospofficeid;
	}


	public void setHospofficeid(String hospofficeid) {
		this.hospofficeid = hospofficeid;
	}


	public Integer getOrgid() {
		return orgid;
	}


	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}


	public Integer getCityid() {
		return cityid;
	}


	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}


	public String getHospofficename() {
		return hospofficename;
	}


	public void setHospofficename(String hospofficename) {
		this.hospofficename = hospofficename;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Clob getMazskill() {
		return mazskill;
	}


	public void setMazskill(Clob mazskill) {
		this.mazskill = mazskill;
	}


	public String getDoctoridiom() {
		return doctoridiom;
	}


	public void setDoctoridiom(String doctoridiom) {
		this.doctoridiom = doctoridiom;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getDoctorlevel() {
		return doctorlevel;
	}


	public void setDoctorlevel(Integer doctorlevel) {
		this.doctorlevel = doctorlevel;
	}


	public String getBalancetype() {
		return balancetype;
	}


	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}


	public String getPaytype() {
		return paytype;
	}


	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}


	public String getSendType() {
		return sendType;
	}


	public void setSendType(String sendType) {
		this.sendType = sendType;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
