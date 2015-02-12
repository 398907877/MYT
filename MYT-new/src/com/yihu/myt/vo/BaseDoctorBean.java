package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class BaseDoctorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer basedoctorid;
	private Integer hospitalid;
	private Integer oldhospitalid;
	private String deptid;
	private Integer hosdeptid;
	private Integer oldhosdeptid;
	private Integer doctorid;
	private String doctoruser;
	private String doctorpsw;
	private String innerid;
	private String hosdoctorid;
	private String cname;
	private String sex;
	private String identityno;
	private String mobile;
	private String telephone;
	private String fax;
	private String address;
	private String postcode;
	private String email;
	private Timestamp birthdate;
	private Timestamp workdate;
	private String specialskill;
	private String diseasestr;
	private String introduction;
	private String teachlevel;
	private Integer cliniclevel;
	private Integer levelid;
	private Integer researchlevel;
	private Integer banktype;
	private String bankaccount;
	private Integer honorlevel;
	private String status;
	private Integer cityid;
	private Timestamp opertime;
	private String remark;
	private Integer orgid;
	private String certificate;
	private Blob picture;
	private Integer operatorid;
	private String officephone;
	private String workyearscount;
	private String homepage;
	private Integer yihuwebpic;
	private Integer yihusound;
	private Integer yihustatus;
	private String mazskill2;
	private String mazskill;
	private String mazpost;
	private String mazworktime;
	private String mazsign;
	private Integer teachjob;
	private String sevenid;

	public Integer getBasedoctorid() {
		return basedoctorid;
	}

	public void setBasedoctorid(Integer basedoctorid) {
		this.basedoctorid = basedoctorid;
	}

	public Integer getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(Integer hospitalid) {
		this.hospitalid = hospitalid;
	}

	public Integer getOldhospitalid() {
		return oldhospitalid;
	}

	public void setOldhospitalid(Integer oldhospitalid) {
		this.oldhospitalid = oldhospitalid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Integer getHosdeptid() {
		return hosdeptid;
	}

	public void setHosdeptid(Integer hosdeptid) {
		this.hosdeptid = hosdeptid;
	}

	public Integer getOldhosdeptid() {
		return oldhosdeptid;
	}

	public void setOldhosdeptid(Integer oldhosdeptid) {
		this.oldhosdeptid = oldhosdeptid;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public String getDoctoruser() {
		return doctoruser;
	}

	public void setDoctoruser(String doctoruser) {
		this.doctoruser = doctoruser;
	}

	public String getDoctorpsw() {
		return doctorpsw;
	}

	public void setDoctorpsw(String doctorpsw) {
		this.doctorpsw = doctorpsw;
	}

	public String getInnerid() {
		return innerid;
	}

	public void setInnerid(String innerid) {
		this.innerid = innerid;
	}

	public String getHosdoctorid() {
		return hosdoctorid;
	}

	public void setHosdoctorid(String hosdoctorid) {
		this.hosdoctorid = hosdoctorid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityno() {
		return identityno;
	}

	public void setIdentityno(String identityno) {
		this.identityno = identityno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	public Timestamp getWorkdate() {
		return workdate;
	}

	public void setWorkdate(Timestamp workdate) {
		this.workdate = workdate;
	}

	public String getSpecialskill() {
		return specialskill;
	}

	public void setSpecialskill(String specialskill) {
		this.specialskill = specialskill;
	}

	public String getDiseasestr() {
		return diseasestr;
	}

	public void setDiseasestr(String diseasestr) {
		this.diseasestr = diseasestr;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTeachlevel() {
		return teachlevel;
	}

	public void setTeachlevel(String teachlevel) {
		this.teachlevel = teachlevel;
	}

	public Integer getCliniclevel() {
		return cliniclevel;
	}

	public void setCliniclevel(Integer cliniclevel) {
		this.cliniclevel = cliniclevel;
	}

	public Integer getLevelid() {
		return levelid;
	}

	public void setLevelid(Integer levelid) {
		this.levelid = levelid;
	}

	public Integer getResearchlevel() {
		return researchlevel;
	}

	public void setResearchlevel(Integer researchlevel) {
		this.researchlevel = researchlevel;
	}

	public Integer getBanktype() {
		return banktype;
	}

	public void setBanktype(Integer banktype) {
		this.banktype = banktype;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public Integer getHonorlevel() {
		return honorlevel;
	}

	public void setHonorlevel(Integer honorlevel) {
		this.honorlevel = honorlevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Timestamp getOpertime() {
		return opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Integer getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getWorkyearscount() {
		return workyearscount;
	}

	public void setWorkyearscount(String workyearscount) {
		this.workyearscount = workyearscount;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Integer getYihuwebpic() {
		return yihuwebpic;
	}

	public void setYihuwebpic(Integer yihuwebpic) {
		this.yihuwebpic = yihuwebpic;
	}

	public Integer getYihusound() {
		return yihusound;
	}

	public void setYihusound(Integer yihusound) {
		this.yihusound = yihusound;
	}

	public Integer getYihustatus() {
		return yihustatus;
	}

	public void setYihustatus(Integer yihustatus) {
		this.yihustatus = yihustatus;
	}

	public String getMazskill2() {
		return mazskill2;
	}

	public void setMazskill2(String mazskill2) {
		this.mazskill2 = mazskill2;
	}

	public String getMazskill() {
		return mazskill;
	}

	public void setMazskill(String mazskill) {
		this.mazskill = mazskill;
	}

	public String getMazpost() {
		return mazpost;
	}

	public void setMazpost(String mazpost) {
		this.mazpost = mazpost;
	}

	public String getMazworktime() {
		return mazworktime;
	}

	public void setMazworktime(String mazworktime) {
		this.mazworktime = mazworktime;
	}

	public String getMazsign() {
		return mazsign;
	}

	public void setMazsign(String mazsign) {
		this.mazsign = mazsign;
	}

	public Integer getTeachjob() {
		return teachjob;
	}

	public void setTeachjob(Integer teachjob) {
		this.teachjob = teachjob;
	}

	public String getSevenid() {
		return sevenid;
	}

	public void setSevenid(String sevenid) {
		this.sevenid = sevenid;
	}

	public String getHosname() {
		return hosname;
	}

	public void setHosname(String hosname) {
		this.hosname = hosname;
	}

	public String getHasmyt() {
		return hasmyt;
	}

	public void setHasmyt(String hasmyt) {
		this.hasmyt = hasmyt;
	}

	private String hosname;
	private String hasmyt;

	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
