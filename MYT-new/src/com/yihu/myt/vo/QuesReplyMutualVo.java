package com.yihu.myt.vo;

	private Integer replyType;  

	private Integer replyCount;  

	private String beginTime;  

	private String endTime;  

	private Integer queID;  

	public String getLastUpdateTime() {
		return this.quesReplyMutualID;
	}
	public void setQuesReplyMutualID(Integer quesReplyMutualID){
		this.quesReplyMutualID=quesReplyMutualID;
	}

	public Integer getReplyType(){
		return this.replyType;
	}
	public void setReplyType(Integer replyType){
		this.replyType=replyType;
	}

	public Integer getReplyCount(){
		return this.replyCount;
	}
	public void setReplyCount(Integer replyCount){
		this.replyCount=replyCount;
	}

	public String getBeginTime(){
		return this.beginTime;
	}
	public void setBeginTime(String beginTime){
		this.beginTime=beginTime;
	}

	public String getEndTime(){
		return this.endTime;
	}
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}

	public Integer getQueID(){
		return this.queID;
	}
	public void setQueID(Integer queID){
		this.queID=queID;
	}

}