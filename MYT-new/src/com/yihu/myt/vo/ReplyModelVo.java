package com.yihu.myt.vo;

	private String title;  

	private String template;  

	private String fileName;  

	private Integer modeType;  

	private Integer status;  

	private String insertTime;  
	private Integer modelUserID;
	public Integer getIfSystem() {
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