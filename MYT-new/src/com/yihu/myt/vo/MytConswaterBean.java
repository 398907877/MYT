package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MytConswaterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pkconswaterid;
	private String pkwaterIDs;
	private String hotline;
	private String conswaterid;
	private Integer doctorid;
	private String sevendoctorid;
	private String tendoctorid;
	private String doctorname;
	private Integer operconfid;
	private String hospofficeid;
	private String hospofficename;
	private String doctorphone;
	private String cardid;
	private String meetid;
	private String intervals;
	private String ableshort;
	private String lack;
	private Integer orders;
	private Integer flag;
	private String custphone;
	private Timestamp startdatetime;
	private Timestamp enddatetime;
	private String feeid;
	private String doctorlevel;
	private String mytfeename;
	private Integer charge;
	private String chargeid;
	private Integer score;
	private Timestamp opertime;
	private Integer cityid;
	private Integer orgid;
	private String isfaultdeal;
	private Integer state;
	private String remark;
	private Integer hospitalid;
	private String hospitalname;
	private Integer hosdeptid;
	private String hosdeptname;
	private Integer consmin;
	private Integer operatorid;
	private String operatorname;
	private String filepath;
	private Integer isqk;
	private Integer cardtypesn;
	private Integer feeTemplateId;
	private Integer provinceId;
	
	
	public Integer getConsmin() {
		return consmin;
	}



	public Integer getFeeTemplateId() {
		return feeTemplateId;
	}



	public Integer getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}



	public void setFeeTemplateId(Integer feeTemplateId) {
		this.feeTemplateId = feeTemplateId;
	}



	public String getOperconfids() {
		return operconfids;
	}



	public void setOperconfids(String operconfids) {
		this.operconfids = operconfids;
	}



	private String cardtypename;
	private String accountsn;
	private Integer doctororg;
	private String operconfids;
	
	private Timestamp mindatetime;
	private Timestamp maxdatetime;

	

	public Integer getPkconswaterid() {
		return pkconswaterid;
	}



	public void setPkconswaterid(Integer pkconswaterid) {
		this.pkconswaterid = pkconswaterid;
	}



	public String getHotline() {
		return hotline;
	}



	public void setHotline(String hotline) {
		this.hotline = hotline;
	}



	public String getConswaterid() {
		return conswaterid;
	}



	public void setConswaterid(String conswaterid) {
		this.conswaterid = conswaterid;
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



	public String getDoctorphone() {
		return doctorphone;
	}



	public void setDoctorphone(String doctorphone) {
		this.doctorphone = doctorphone;
	}



	public String getCardid() {
		return cardid;
	}



	public void setCardid(String cardid) {
		this.cardid = cardid;
	}



	public String getMeetid() {
		return meetid;
	}



	public void setMeetid(String meetid) {
		this.meetid = meetid;
	}



	public String getIntervals() {
		return intervals;
	}



	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}



	public String getAbleshort() {
		return ableshort;
	}



	public void setAbleshort(String ableshort) {
		this.ableshort = ableshort;
	}



	public String getLack() {
		return lack;
	}



	public void setLack(String lack) {
		this.lack = lack;
	}



	public Integer getOrders() {
		return orders;
	}



	public void setOrders(Integer orders) {
		this.orders = orders;
	}



	public Integer getFlag() {
		return flag;
	}



	public void setFlag(Integer flag) {
		this.flag = flag;
	}



	public String getCustphone() {
		return custphone;
	}



	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}



	public Timestamp getStartdatetime() {
		return startdatetime;
	}



	public void setStartdatetime(Timestamp startdatetime) {
		this.startdatetime = startdatetime;
	}



	public Timestamp getEnddatetime() {
		return enddatetime;
	}



	public void setEnddatetime(Timestamp enddatetime) {
		this.enddatetime = enddatetime;
	}



	public String getFeeid() {
		return feeid;
	}



	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}



	public String getDoctorlevel() {
		return doctorlevel;
	}



	public void setDoctorlevel(String doctorlevel) {
		this.doctorlevel = doctorlevel;
	}



	public String getMytfeename() {
		return mytfeename;
	}



	public void setMytfeename(String mytfeename) {
		this.mytfeename = mytfeename;
	}



	public Integer getCharge() {
		return charge;
	}



	public void setCharge(Integer charge) {
		this.charge = charge;
	}



	public String getChargeid() {
		return chargeid;
	}



	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}



	public Integer getScore() {
		return score;
	}



	public void setScore(Integer score) {
		this.score = score;
	}



	public Timestamp getOpertime() {
		return opertime;
	}



	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}



	public Integer getCityid() {
		return cityid;
	}



	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}



	public Integer getOrgid() {
		return orgid;
	}



	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}



	public String getIsfaultdeal() {
		return isfaultdeal;
	}



	public void setIsfaultdeal(String isfaultdeal) {
		this.isfaultdeal = isfaultdeal;
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



	public Integer getHospitalid() {
		return hospitalid;
	}



	public void setHospitalid(Integer hospitalid) {
		this.hospitalid = hospitalid;
	}



	public String getHospitalname() {
		return hospitalname;
	}



	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}



	public Integer getHosdeptid() {
		return hosdeptid;
	}



	public void setHosdeptid(Integer hosdeptid) {
		this.hosdeptid = hosdeptid;
	}



	public String getHosdeptname() {
		return hosdeptname;
	}



	public void setHosdeptname(String hosdeptname) {
		this.hosdeptname = hosdeptname;
	}







	public void setConsmin(Integer consmin) {
		this.consmin = consmin;
	}



	public Integer getOperatorid() {
		return operatorid;
	}



	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}



	public String getOperatorname() {
		return operatorname;
	}



	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}



	public String getFilepath() {
		return filepath;
	}



	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}



	public Integer getIsqk() {
		return isqk;
	}



	public void setIsqk(Integer isqk) {
		this.isqk = isqk;
	}



	public Integer getCardtypesn() {
		return cardtypesn;
	}



	public void setCardtypesn(Integer cardtypesn) {
		this.cardtypesn = cardtypesn;
	}



	public String getCardtypename() {
		return cardtypename;
	}



	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}



	public String getAccountsn() {
		return accountsn;
	}



	public void setAccountsn(String accountsn) {
		this.accountsn = accountsn;
	}



	public Integer getDoctororg() {
		return doctororg;
	}



	public void setDoctororg(Integer doctororg) {
		this.doctororg = doctororg;
	}



	public Timestamp getMindatetime() {
		return mindatetime;
	}



	public void setMindatetime(Timestamp mindatetime) {
		this.mindatetime = mindatetime;
	}



	public Timestamp getMaxdatetime() {
		return maxdatetime;
	}



	public void setMaxdatetime(Timestamp maxdatetime) {
		this.maxdatetime = maxdatetime;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPkwaterIDs() {
		return pkwaterIDs;
	}



	public void setPkwaterIDs(String pkwaterIDs) {
		this.pkwaterIDs = pkwaterIDs;
	}





	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
