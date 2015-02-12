package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MytConsenrolBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// entity properties
	private Integer consenrolid;
	private Integer doctorid;
	private String sevendoctorid;
	private String tendoctorid;
	private String doctorname;
	private Integer operconfid;
	private String hospofficeid;
	private String hospofficename;
	private String cardid;
	private String custname;
	private String custphone;
	private String constype;
	private String isappoint;
	private String vouchresult;
	private String vouchdefeat;
	private String turnresult;
	private String turndefeat;
	private String isrevert;
	private String norevert;
	private Integer orgid;
	private Integer cityid;
	private String operatorid;
	private String operatorname;
	private Timestamp opertime;
	private Integer state;
	private String remark;
	private int cardtypesn;
	private int cardorgid;
	
	// query properties
	private String minDateTime;
	private String maxDateTime;
	private String operconfids;
	
	//回复电话
	private String revertphone;
	//回复时间
	private String dateweeks;

	private Integer provinceId;

	public Integer getConsenrolid() {
		return consenrolid;
	}



	public Integer getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}



	public void setConsenrolid(Integer consenrolid) {
		this.consenrolid = consenrolid;
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



	public Integer getOperconfid() {
		return operconfid;
	}



	public void setOperconfid(Integer operconfid) {
		this.operconfid = operconfid;
	}



	public String getHospofficeid() {
		return hospofficeid;
	}



	public void setHospofficeid(String hospofficeid) {
		this.hospofficeid = hospofficeid;
	}



	public String getHospofficename() {
		return hospofficename;
	}



	public void setHospofficename(String hospofficename) {
		this.hospofficename = hospofficename;
	}



	public String getCardid() {
		return cardid;
	}



	public void setCardid(String cardid) {
		this.cardid = cardid;
	}



	public String getCustname() {
		return custname;
	}



	public void setCustname(String custname) {
		this.custname = custname;
	}



	public String getCustphone() {
		return custphone;
	}



	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}



	public String getConstype() {
		return constype;
	}



	public void setConstype(String constype) {
		this.constype = constype;
	}



	public String getIsappoint() {
		return isappoint;
	}



	public void setIsappoint(String isappoint) {
		this.isappoint = isappoint;
	}



	public String getVouchresult() {
		return vouchresult;
	}



	public void setVouchresult(String vouchresult) {
		this.vouchresult = vouchresult;
	}



	public String getVouchdefeat() {
		return vouchdefeat;
	}



	public void setVouchdefeat(String vouchdefeat) {
		this.vouchdefeat = vouchdefeat;
	}



	public String getTurnresult() {
		return turnresult;
	}



	public void setTurnresult(String turnresult) {
		this.turnresult = turnresult;
	}



	public String getTurndefeat() {
		return turndefeat;
	}



	public void setTurndefeat(String turndefeat) {
		this.turndefeat = turndefeat;
	}



	public String getIsrevert() {
		return isrevert;
	}



	public void setIsrevert(String isrevert) {
		this.isrevert = isrevert;
	}



	public String getNorevert() {
		return norevert;
	}



	public void setNorevert(String norevert) {
		this.norevert = norevert;
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



	public String getOperatorid() {
		return operatorid;
	}



	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}



	public String getOperatorname() {
		return operatorname;
	}



	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}



	public Timestamp getOpertime() {
		return opertime;
	}



	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}



	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public int getCardtypesn() {
		return cardtypesn;
	}



	public void setCardtypesn(int cardtypesn) {
		this.cardtypesn = cardtypesn;
	}



	public int getCardorgid() {
		return cardorgid;
	}



	public void setCardorgid(int cardorgid) {
		this.cardorgid = cardorgid;
	}



	public String getMinDateTime() {
		return minDateTime;
	}



	public void setMinDateTime(String minDateTime) {
		this.minDateTime = minDateTime;
	}



	public String getMaxDateTime() {
		return maxDateTime;
	}



	public void setMaxDateTime(String maxDateTime) {
		this.maxDateTime = maxDateTime;
	}



	public String getOperconfids() {
		return operconfids;
	}



	public void setOperconfids(String operconfids) {
		this.operconfids = operconfids;
	}



	public String getRevertphone() {
		return revertphone;
	}



	public void setRevertphone(String revertphone) {
		this.revertphone = revertphone;
	}



	public String getDateweeks() {
		return dateweeks;
	}



	public void setDateweeks(String dateweeks) {
		this.dateweeks = dateweeks;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
