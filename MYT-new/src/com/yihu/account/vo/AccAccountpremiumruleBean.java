package com.yihu.account.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class AccAccountpremiumruleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer accountpremiumrulesn;
	private Integer accountsn;
	private Timestamp validdate;
	private Timestamp invaliddate;
	private String productno;
	private String feeno;
	private Integer feecash;
	private Integer premiumvalue;
	private Integer priority;
	private Integer orgid;
	private Integer cityid;
	private Integer operatorid;
	private Timestamp opertime;
	private Integer state;
	private String remark;
	private String flag;
	private String cardnumber;
	private Integer monthcount;
	private Timestamp startdate;
	private Timestamp enddate;
	private Integer type;
	private Integer premiumcount;

	public Integer getAccountpremiumrulesn() {
		return accountpremiumrulesn;
	}

	public void setAccountpremiumrulesn(Integer accountpremiumrulesn) {
		this.accountpremiumrulesn = accountpremiumrulesn;
	}

	public Integer getAccountsn() {
		return accountsn;
	}

	public void setAccountsn(Integer accountsn) {
		this.accountsn = accountsn;
	}

	public Timestamp getValiddate() {
		return validdate;
	}

	public void setValiddate(Timestamp validdate) {
		this.validdate = validdate;
	}

	public Timestamp getInvaliddate() {
		return invaliddate;
	}

	public void setInvaliddate(Timestamp invaliddate) {
		this.invaliddate = invaliddate;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public String getFeeno() {
		return feeno;
	}

	public void setFeeno(String feeno) {
		this.feeno = feeno;
	}

	public Integer getFeecash() {
		return feecash;
	}

	public void setFeecash(Integer feecash) {
		this.feecash = feecash;
	}

	public Integer getPremiumvalue() {
		return premiumvalue;
	}

	public void setPremiumvalue(Integer premiumvalue) {
		this.premiumvalue = premiumvalue;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public Integer getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public Integer getMonthcount() {
		return monthcount;
	}

	public void setMonthcount(Integer monthcount) {
		this.monthcount = monthcount;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPremiumcount() {
		return premiumcount;
	}

	public void setPremiumcount(Integer premiumcount) {
		this.premiumcount = premiumcount;
	}
}
