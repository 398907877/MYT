package com.yihu.myt.vo;

import java.util.Date;

public class DoctorAccPriceView {

	
	Integer da_id; //ҽ���˻�ID
	Integer doctorUid; //ҽ��ID
	String doctorName; //ҽ������
	String loginId; //ҽ����½ID
	String pwd; //ҽ������
	Integer balance; //ҽ���˻����
	Date doctorAccCreatetime; //ҽ���˻�����ʱ��
	Integer loginNum; //��½����
	
	Integer resId; //��Դ����	1ҽ����2����
	Integer resUid; //��ԴID	ResId=1ʱ�˴�ΪDoctorUid
	Integer serviceId;//������ID	1�绰��ѯ����,2ͼ����ѯ����
	
	Integer feeTemplateId; //�ʷ�ģ��ID
	Integer typeId;//�ʷ�����	1�� ǰX����YԪ����ÿ����ZԪ����ǰ5����20Ԫ����ÿ����2Ԫ��2�� ÿX����YԪ����ÿ10����30Ԫ��3�� ÿ��YԪ����ÿ��10Ԫ��.֧��ʱ������
	Integer minuteBefore;//ǰX����	TypeId=1ʱ��д
	Integer minuteBeforePrice;//ǰX���Ӽ۸�	TypeId=1ʱ��д
	Integer perMinutePrice;//ǰX���Ӽ۸�	TypeId=1ʱ��д
	Integer minuteNum;//������	TypeId=2ʱ��д
	Integer price;//�۸�	TypeId=2 OR TypeID=3ʱ��д
	Integer minuteLimit;//���η�������	TypeId=3ʱ��д,Ĭ��Ϊ0��ʾ������ 
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
	public Date getDoctorAccCreatetime() {
		return doctorAccCreatetime;
	}
	public void setDoctorAccCreatetime(Date doctorAccCreatetime) {
		this.doctorAccCreatetime = doctorAccCreatetime;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public Integer getResUid() {
		return resUid;
	}
	public void setResUid(Integer resUid) {
		this.resUid = resUid;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getFeeTemplateId() {
		return feeTemplateId;
	}
	public void setFeeTemplateId(Integer feeTemplateId) {
		this.feeTemplateId = feeTemplateId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getMinuteBefore() {
		return minuteBefore;
	}
	public void setMinuteBefore(Integer minuteBefore) {
		this.minuteBefore = minuteBefore;
	}
	public Integer getMinuteBeforePrice() {
		return minuteBeforePrice;
	}
	public void setMinuteBeforePrice(Integer minuteBeforePrice) {
		this.minuteBeforePrice = minuteBeforePrice;
	}
	public Integer getPerMinutePrice() {
		return perMinutePrice;
	}
	public void setPerMinutePrice(Integer perMinutePrice) {
		this.perMinutePrice = perMinutePrice;
	}
	public Integer getMinuteNum() {
		return minuteNum;
	}
	public void setMinuteNum(Integer minuteNum) {
		this.minuteNum = minuteNum;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getMinuteLimit() {
		return minuteLimit;
	}
	public void setMinuteLimit(Integer minuteLimit) {
		this.minuteLimit = minuteLimit;
	}
	


}
