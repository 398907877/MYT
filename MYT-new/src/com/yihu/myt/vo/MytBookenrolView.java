package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MytBookenrolView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bookenrolid;
	
	private Integer doctorid;
	private String doctorname;
	private Integer operconfid;
	private String orgname;
	private String hospitalname;
	private String mazskill;
	private Integer doctorlevel;
	private Integer hospitalid;
	private String hospofficeid;
	private String hospofficename;
	private String cardid;
	private String custname;
	private String custphone;
	private String constype;
	private String revertphone;
	private String rsperiod;
	private Timestamp revertdate;
	private String revertresult;
	private Integer orgid;
	private Integer cityid;
	private String operatorid;
	private String operatorname;
	private Timestamp opertime;
	private Timestamp begOpertime;
	private Timestamp endOpertime;
	private Integer state;
	private String remark;
	private String dateweek;
	private String starttime;
	private String endtime;
	private Integer cardtypesn;
	private Integer cardorgid;
	private String operconfids;
	private String comefrom;;

	





	public String getComefrom() {
		return comefrom;
	}



	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}



	public String getOperconfids() {
		return operconfids;
	}



	public void setOperconfids(String operconfids) {
		this.operconfids = operconfids;
	}



	public Integer getBookenrolid() {
		return bookenrolid;
	}



	public void setBookenrolid(Integer bookenrolid) {
		this.bookenrolid = bookenrolid;
	}



	public Integer getDoctorid() {
		return doctorid;
	}



	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
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



	public String getOrgname() {
		return orgname;
	}



	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}



	public String getHospitalname() {
		return hospitalname;
	}



	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}



	public String getMazskill() {
		return mazskill;
	}



	public void setMazskill(String mazskill) {
		this.mazskill = mazskill;
	}



	public Integer getDoctorlevel() {
		return doctorlevel;
	}



	public void setDoctorlevel(Integer doctorlevel) {
		this.doctorlevel = doctorlevel;
	}



	public Integer getHospitalid() {
		return hospitalid;
	}



	public void setHospitalid(Integer hospitalid) {
		this.hospitalid = hospitalid;
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



	public String getRevertphone() {
		return revertphone;
	}



	public void setRevertphone(String revertphone) {
		this.revertphone = revertphone;
	}



	public String getRsperiod() {
		return rsperiod;
	}



	public void setRsperiod(String rsperiod) {
		this.rsperiod = rsperiod;
	}



	public Timestamp getRevertdate() {
		return revertdate;
	}



	public void setRevertdate(Timestamp revertdate) {
		this.revertdate = revertdate;
	}



	public String getRevertresult() {
		return revertresult;
	}



	public void setRevertresult(String revertresult) {
		this.revertresult = revertresult;
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



	public Timestamp getBegOpertime() {
		return begOpertime;
	}



	public void setBegOpertime(Timestamp begOpertime) {
		this.begOpertime = begOpertime;
	}



	public Timestamp getEndOpertime() {
		return endOpertime;
	}



	public void setEndOpertime(Timestamp endOpertime) {
		this.endOpertime = endOpertime;
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



	public String getDateweek() {
		return dateweek;
	}



	public void setDateweek(String dateweek) {
		this.dateweek = dateweek;
	}



	public String getStarttime() {
		return starttime;
	}



	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}



	public String getEndtime() {
		return endtime;
	}



	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}



	public Integer getCardtypesn() {
		return cardtypesn;
	}



	public void setCardtypesn(Integer cardtypesn) {
		this.cardtypesn = cardtypesn;
	}



	public Integer getCardorgid() {
		return cardorgid;
	}



	public void setCardorgid(Integer cardorgid) {
		this.cardorgid = cardorgid;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
