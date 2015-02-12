package com.yihu.myt.vo;public class ReplyModelVo{	private Integer modelID;  

	private String title;  

	private String template;  

	private String fileName;  

	private Integer modeType;  

	private Integer status;  

	private String insertTime;  	
	private Integer modelUserID;		private Integer ifSystem;		
	public Integer getIfSystem() {		return ifSystem;	}	public void setIfSystem(Integer ifSystem) {		this.ifSystem = ifSystem;	}	public Integer getModelUserID() {		return modelUserID;	}	public void setModelUserID(Integer modelUserID) {		this.modelUserID = modelUserID;	}	public Integer getModelID(){
		return this.modelID;
	}
	public void setModelID(Integer modelID){
		this.modelID=modelID;
	}

	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title=title;
	}

	public String getTemplate(){
		return this.template;
	}
	public void setTemplate(String template){
		this.template=template;
	}

	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String fileName){
		this.fileName=fileName;
	}

	public Integer getModeType(){
		return this.modeType;
	}
	public void setModeType(Integer modeType){
		this.modeType=modeType;
	}

	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}

	public String getInsertTime(){
		return this.insertTime;
	}
	public void setInsertTime(String insertTime){
		this.insertTime=insertTime;
	}

}