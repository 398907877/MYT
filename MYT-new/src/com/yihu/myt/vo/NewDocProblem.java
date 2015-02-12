package com.yihu.myt.vo;

import java.io.Serializable;

public class NewDocProblem  implements  Serializable{

	/**
	 * @author WUJIAJUN  
	 * 咨询问题数据统计    的实体类
	 * 
	 * 医生 数据
	 * 
	 * 科室数据
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String ASK_DoctorID;
	
	public String getASK_DoctorID() {
		return ASK_DoctorID;
	}

	public void setASK_DoctorID(String aSK_DoctorID) {
		ASK_DoctorID = aSK_DoctorID;
	}

	private String docidone;
	private String docidtwo;
	private String idone;
	private String idtwo;
	
	
	private String  postonedeptname;
	
	
	public String getPostonedeptname() {
		return postonedeptname;
	}

	public void setPostonedeptname(String postonedeptname) {
		this.postonedeptname = postonedeptname;
	}

	private  String  hosname;//医院
	
	
	
	private  String  hosdeptid;//科室id
	
	private String standarddeptid;
	
	
	private  String  deptname;//科室
	
	
	private  String  docid;//医生id
	
	private  String  doctorname;//医生姓名
	
	
	//指定
	private  String  toallcount;//指定总数
	
	private  String  tobackcount;//指定回复数
	
	private  String  tomin;//指定回复分钟数
	
	//公共
	private  String  puballcount;//公共总数
	
	private  String  pubmin;//公共回复分钟数

	
	
	
	
	
	public String getDocidone() {
		return docidone;
	}

	public void setDocidone(String docidone) {
		this.docidone = docidone;
	}

	public String getDocidtwo() {
		return docidtwo;
	}

	public void setDocidtwo(String docidtwo) {
		this.docidtwo = docidtwo;
	}

	public String getIdone() {
		return idone;
	}

	public void setIdone(String idone) {
		this.idone = idone;
	}

	public String getIdtwo() {
		return idtwo;
	}

	public void setIdtwo(String idtwo) {
		this.idtwo = idtwo;
	}

	public String getHosname() {
		return hosname;
	}

	public void setHosname(String hosname) {
		this.hosname = hosname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getToallcount() {
		return toallcount;
	}

	public void setToallcount(String toallcount) {
		this.toallcount = toallcount;
	}

	public String getTobackcount() {
		return tobackcount;
	}

	public void setTobackcount(String tobackcount) {
		this.tobackcount = tobackcount;
	}

	public String getTomin() {
		return tomin;
	}

	public void setTomin(String tomin) {
		this.tomin = tomin;
	}

	public String getPuballcount() {
		return puballcount;
	}

	public void setPuballcount(String puballcount) {
		this.puballcount = puballcount;
	}

	public String getPubmin() {
		return pubmin;
	}

	public void setPubmin(String pubmin) {
		this.pubmin = pubmin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public NewDocProblem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHosdeptid() {
		return hosdeptid;
	}

	public void setHosdeptid(String hosdeptid) {
		this.hosdeptid = hosdeptid;
	}

	public String getStandarddeptid() {
		return standarddeptid;
	}

	public void setStandarddeptid(String standarddeptid) {
		this.standarddeptid = standarddeptid;
	}
	
	
	//统计渠道的
	private String  datetime;
	private String web;
	private String app;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
	
	private String usertopay;
	private String usertofree;
	private String userpub;

	private String doctopay;
	private String doctofree;
	public String getUsertopay() {
		return usertopay;
	}

	public void setUsertopay(String usertopay) {
		this.usertopay = usertopay;
	}

	public String getUsertofree() {
		return usertofree;
	}

	public void setUsertofree(String usertofree) {
		this.usertofree = usertofree;
	}

	public String getUserpub() {
		return userpub;
	}

	public void setUserpub(String userpub) {
		this.userpub = userpub;
	}

	public String getDoctopay() {
		return doctopay;
	}

	public void setDoctopay(String doctopay) {
		this.doctopay = doctopay;
	}

	public String getDoctofree() {
		return doctofree;
	}

	public void setDoctofree(String doctofree) {
		this.doctofree = doctofree;
	}

	public String getDocpub() {
		return docpub;
	}

	public void setDocpub(String docpub) {
		this.docpub = docpub;
	}

	private String docpub;
	
	
	private String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	
	
	

}
