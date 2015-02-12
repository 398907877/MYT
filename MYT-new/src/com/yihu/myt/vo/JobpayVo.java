package com.yihu.myt.vo;public class JobpayVo{	private Integer ID;  

	private String DOCID;  

	private String NUM;  

	private String SEQNUM;  

	private String ROUTEMESSAGE;  

	private String FLAG;  

	private String INSERTTIME;  

	public Integer getID(){
		return this.ID;
	}
	public void setID(Integer ID){
		this.ID=ID;
	}

	public String getDOCID(){
		return this.DOCID;
	}
	public void setDOCID(String DOCID){
		this.DOCID=DOCID;
	}

	public String getNUM(){
		return this.NUM;
	}
	public void setNUM(String NUM){
		this.NUM=NUM;
	}

	public String getSEQNUM(){
		return this.SEQNUM;
	}
	public void setSEQNUM(String SEQNUM){
		this.SEQNUM=SEQNUM;
	}

	public String getROUTEMESSAGE(){
		return this.ROUTEMESSAGE;
	}
	public void setROUTEMESSAGE(String ROUTEMESSAGE){
		this.ROUTEMESSAGE=ROUTEMESSAGE;
	}

	public String getFLAG(){
		return this.FLAG;
	}
	public void setFLAG(String FLAG){
		this.FLAG=FLAG;
	}

	public String getINSERTTIME(){
		return this.INSERTTIME;
	}
	public void setINSERTTIME(String INSERTTIME){
		this.INSERTTIME=INSERTTIME;
	}

}