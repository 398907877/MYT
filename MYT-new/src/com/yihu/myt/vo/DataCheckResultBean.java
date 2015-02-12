package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class DataCheckResultBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer checkResultId;
	private Integer checkId;
	private Integer operatorId;
	private String operatorName;
	private Integer operatorDeptId;
	private String operatorDeptName;
	private Timestamp opertime;
	private Integer state;
	private String remark;

	public Integer getCheckResultId() {
		return checkResultId;
	}

	public void setCheckResultId(Integer checkResultId) {
		this.checkResultId = checkResultId;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperatorDeptId() {
		return operatorDeptId;
	}

	public void setOperatorDeptId(Integer operatorDeptId) {
		this.operatorDeptId = operatorDeptId;
	}

	public String getOperatorDeptName() {
		return operatorDeptName;
	}

	public void setOperatorDeptName(String operatorDeptName) {
		this.operatorDeptName = operatorDeptName;
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

	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
