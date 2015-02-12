package com.yihu.myt.vo;public class ConfigVo{	private Integer CONFIG_ID;  

	private String CONFIG_Key;  

	private String CONFIG_Value;  

	private Integer CONFIG_Status;  

	private String CONFIG_CreateTime;  

	public Integer getCONFIG_ID(){
		return this.CONFIG_ID;
	}
	public void setCONFIG_ID(Integer CONFIG_ID){
		this.CONFIG_ID=CONFIG_ID;
	}

	public String getCONFIG_Key(){
		return this.CONFIG_Key;
	}
	public void setCONFIG_Key(String CONFIG_Key){
		this.CONFIG_Key=CONFIG_Key;
	}

	public String getCONFIG_Value(){
		return this.CONFIG_Value;
	}
	public void setCONFIG_Value(String CONFIG_Value){
		this.CONFIG_Value=CONFIG_Value;
	}

	public Integer getCONFIG_Status(){
		return this.CONFIG_Status;
	}
	public void setCONFIG_Status(Integer CONFIG_Status){
		this.CONFIG_Status=CONFIG_Status;
	}

	public String getCONFIG_CreateTime(){
		return this.CONFIG_CreateTime;
	}
	public void setCONFIG_CreateTime(String CONFIG_CreateTime){
		this.CONFIG_CreateTime=CONFIG_CreateTime;
	}

}