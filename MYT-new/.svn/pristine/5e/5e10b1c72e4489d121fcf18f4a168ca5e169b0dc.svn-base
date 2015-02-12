package com.yihu.myt.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.boss.sdk.OperatorInfo;
import com.common.json.JSONObject;

public class SysOperatorBean {

	private String orgSEQ;
	private String[] orgSEQArray;
	private boolean hasSIP;
	private String deptids;

	public String getDeptids() {
		return deptids;
	}

	public void setDeptids(String deptids) {
		this.deptids = deptids;
	}

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}

	private String orgids;

	public boolean isHasSIP() {
		return hasSIP;
	}

	public void setHasSIP(boolean hasSIP) {
		this.hasSIP = hasSIP;
	}

	public String[] getOrgSEQArray() {
		return orgSEQArray;
	}

	public void setOrgSEQArray(String[] orgSEQArray) {
		this.orgSEQArray = orgSEQArray;
	}

	public String getOrgSEQ() {
		return orgSEQ;
	}

	public void setOrgSEQ(String orgSEQ) {
		this.orgSEQ = orgSEQ;
	}

	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	private int orgID;
	private int operatorID;

	public int getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	private String operatorName;
	private int deptID;
	private String deptName;

	public SysOperatorBean() {

	}

	public SysOperatorBean(OperatorInfo info) {
		this.deptID = info.getDeptID();
		this.deptids = info.getDeptids();
		this.deptName = info.getDeptName();
		this.hasSIP = info.isHasSIP();
		this.operatorID = info.getOperatorID();
		this.operatorName = info.getOperatorName();
		this.orgID = info.getOrgID();
		this.orgids = info.getOrgids();
		this.orgSEQ = info.getOrgSEQ();
		this.orgSEQArray = info.getOrgSEQArray();
	}

	public SysOperatorBean(JSONObject json) throws Exception {
		this.deptID = json.isNull("deptid") ? 0 : json.getInt("deptid");
		this.deptids = json.isNull("deptids") ? "" : json.getString("deptids");
		this.deptName = json.getString("deptname");
		this.hasSIP = json.getBoolean("hassip");
		this.operatorID = json.getInt("operatorid");
		this.operatorName = json.getString("operatorname");
		this.orgID = json.getInt("orgid");
		this.orgids = json.isNull("orgids") ? "" : json.getString("orgids");
		this.orgSEQ = json.isNull("orgseq") ? "" : json.getString("orgseq");
		this.orgSEQArray = json.getString("orgseqarray").split("$");
	}

	public JSONObject toJSON() throws Exception {
		String orgseqarray = "";
		if (this.orgSEQArray != null) {
			for (String string : this.orgSEQArray) {
				orgseqarray += string + "$";
			}
		}
		JSONObject json = new JSONObject();
		json.put("deptids", this.deptids);
		json.put("deptname", this.deptName);
		json.put("operatorid", this.operatorID);
		json.put("operatorname", this.operatorName);
		json.put("orgids", this.orgids);
		json.put("orgseq", this.orgSEQ);
		json.put("deptid", this.deptID);
		json.put("orgid", this.orgID);
		json.put("hassip", this.hasSIP);
		json.put("orgseqarray", orgseqarray);
		return json;
	}

	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
