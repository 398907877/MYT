package com.yihu.myt.vo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 *  ��ѯ��Ӫ֧������ ʵ��
 * @author WUJIAJUN
 *
 */

public class Consstatistic  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *����
	 */
	private String name;
	/**
	 * ������ѯ�ظ���
	 */
	private Integer countone;
	/**
	 * ����ָ����ѯ�ظ���
	 */
	private Integer counttwo;
	/**
	 * ��ѯ�ر������
	 */
	private Integer countthree;
	/**
	 * ������ѯר�Ʒ�����
	 */
	
	private Integer countfour;
	
	/**
	 * ������ѯ����������
	 */
	
	private Integer countfive;
	
	
	
	private String  startdate;
	private String  enddate;
	
	
	
	
	
	
	public Consstatistic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	public String getStartdate() {
		return startdate;
	}












	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}










	public String getEnddate() {
		return enddate;
	}










	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}










	public Integer getCountfive() {
		return countfive;
	}







	public void setCountfive(Integer countfive) {
		this.countfive = countfive;
	}







	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountone() {
		return countone;
	}
	public void setCountone(Integer countone) {
		this.countone = countone;
	}
	public Integer getCounttwo() {
		return counttwo;
	}
	public void setCounttwo(Integer counttwo) {
		this.counttwo = counttwo;
	}
	public Integer getCountthree() {
		return countthree;
	}
	public void setCountthree(Integer countthree) {
		this.countthree = countthree;
	}
	public Integer getCountfour() {
		return countfour;
	}
	public void setCountfour(Integer countfour) {
		this.countfour = countfour;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
