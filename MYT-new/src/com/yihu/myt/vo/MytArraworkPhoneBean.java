package com.yihu.myt.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MytArraworkPhoneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer arraworkPhoneId;
	private Integer arraworkid;
	private Integer state;
	private Integer doctorid;
	private Integer consphoneid;

	public Integer getArraworkPhoneId() {
		return arraworkPhoneId;
	}

	public void setArraworkPhoneId(Integer arraworkPhoneId) {
		this.arraworkPhoneId = arraworkPhoneId;
	}

	public Integer getArraworkid() {
		return arraworkid;
	}

	public void setArraworkid(Integer arraworkid) {
		this.arraworkid = arraworkid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public Integer getConsphoneid() {
		return consphoneid;
	}

	public void setConsphoneid(Integer consphoneid) {
		this.consphoneid = consphoneid;
	}

	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
