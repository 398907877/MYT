package com.yihu.myt.vo;public class CloseMainVo{	private Integer ID;  

	private String DOCID;  

	private String DOCNAME;  

	private String DOCDIC;  

	private String STARDATE;  

	private String ENDDATE;  

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

	public String getDOCNAME(){
		return this.DOCNAME;
	}
	public void setDOCNAME(String DOCNAME){
		this.DOCNAME=DOCNAME;
	}

	public String getDOCDIC(){
		return this.DOCDIC;
	}
	public void setDOCDIC(String DOCDIC){
		this.DOCDIC=DOCDIC;
	}

	public String getSTARDATE(){
		return this.STARDATE;
	}
	public void setSTARDATE(String STARDATE){
		this.STARDATE=STARDATE;
	}

	public String getENDDATE(){
		return this.ENDDATE;
	}
	public void setENDDATE(String ENDDATE){
		this.ENDDATE=ENDDATE;
	}

}