package com.yihu.myt.vo;

import java.io.Serializable;

public class HotQuesMainVo  implements Serializable{
	
	
	
	/**
	 * 用于接受查询  返回的 热门问题
	 */
	private static final long serialVersionUID = 1L;
	private String rowid;
	private String  quesmain_id;
	private String ask_doctorid;
	private String quesmain_content;
	private String  quesmain_createtime;
	private String  openauthflag;
	private String clicks;
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getQuesmain_id() {
		return quesmain_id;
	}
	public void setQuesmain_id(String quesmain_id) {
		this.quesmain_id = quesmain_id;
	}
	public String getAsk_doctorid() {
		return ask_doctorid;
	}
	public void setAsk_doctorid(String ask_doctorid) {
		this.ask_doctorid = ask_doctorid;
	}
	public String getQuesmain_content() {
		return quesmain_content;
	}
	public void setQuesmain_content(String quesmain_content) {
		this.quesmain_content = quesmain_content;
	}
	public String getQuesmain_createtime() {
		return quesmain_createtime;
	}
	public void setQuesmain_createtime(String quesmain_createtime) {
		this.quesmain_createtime = quesmain_createtime;
	}
	public String getOpenauthflag() {
		return openauthflag;
	}
	public void setOpenauthflag(String openauthflag) {
		this.openauthflag = openauthflag;
	}
	public String getClicks() {
		return clicks;
	}
	public void setClicks(String clicks) {
		this.clicks = clicks;
	}
	

}
