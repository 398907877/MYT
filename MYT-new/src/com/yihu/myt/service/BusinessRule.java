package com.yihu.myt.service;

import java.util.HashMap;


/**
 * @author admin
 *
 */
public class BusinessRule {
	/**
	 * ҵ��������
	 * @author admin
	 *
	 */
	public class Rule 
	{
		
	}
	public class  MYTRule  extends Rule
	{
		/**
		 * ��ѯ�ȼ�Լ��
		 */
		private String LevelRestrain;
		/**
		 * ҵ�񿪹�
		 */
		private boolean  isOk;
	
		/**
		 * �Ƿ�����ȫ����ѯ
		 */
		private boolean  isQkOk;
		public boolean isQkOk() {
			return isQkOk;
		}
		public void setQkOk(boolean isQkOk) {
			this.isQkOk = isQkOk;
		}
		/**
		 * ���캯��
		 *
		 */
		public MYTRule()
		{
			
		}
		public boolean isOk() {
			return isOk;
		}
		public void setOk(boolean isOk) {
			this.isOk = isOk;
		}
		public String getLevelRestrain() {
			return LevelRestrain;
		}
		public void setLevelRestrain(String levelRestrain) {
			LevelRestrain = levelRestrain;
		}
		
	}
	public class  GHTRule extends Rule
	{
		private boolean permit;
		public boolean isPermit() {
			return permit;
		}
		public void setPermit(boolean permit) {
			this.permit = permit;
		}
		/**
		 * ����ǰ����
		 */
		private HashMap forwardDay;
		/**
		 * ����Һŷ�
		 */
		private boolean hasGhFee;
		/**
		 * ��Դռ�÷Ѵ���ʽ
		 */
		private int occupyWay;
		/**
		 * ԤԼҽԺԼ�� 
		 */
		private String[] hospitalRestrain;
		/**
		 * ��Ա�޺�
		 */
		private HashMap numLimit;
		/**
		 * ҽ���ȼ�Լ��
		 */
		private String  doctorLevel;
		public GHTRule()
		{
			
		}
		public GHTRule(HashMap forwardDay,boolean hasGhFee,int occupyWay,String[] hospitalRestrain,HashMap numLimit)
		{
			this.forwardDay=forwardDay;
			this.hasGhFee=hasGhFee;
			this.occupyWay=occupyWay;
			this.hospitalRestrain=hospitalRestrain;
			this.numLimit=numLimit;
		}
		public HashMap getForwardDay() {
			return forwardDay;
		}
		public int getForwardDay(int cityID) 
		{
			String value=(String)forwardDay.get(Integer.toString(cityID));
			if(value==null)
			{
				value=(String)forwardDay.get(Integer.toString(0));
			}
			return Integer.parseInt(value);
		}
		public void setForwardDay(HashMap forwardDay) {
			this.forwardDay = forwardDay;
		}
		public boolean isHasGhFee() {
			return hasGhFee;
		}
		public void setHasGhFee(boolean hasGhFee) {
			this.hasGhFee = hasGhFee;
		}
		public String[] getHospitalRestrain() {
			return hospitalRestrain;
		}
		public boolean  isHosPermit(int ghtHosID)
		{
			if(hospitalRestrain==null)
			{
				return true;
			}
			if(hospitalRestrain[0].equals("only"))
			{
				if(hospitalRestrain[1].indexOf(ghtHosID+",")==-1)
				{
					return false;
				}
			}
			else
			{
				if(hospitalRestrain[1].indexOf(ghtHosID+",")>=0)
				{
					return false;
				}
			}
			return true;
		}
		public void setHospitalRestrain(String[] hospitalRestrain) {
			this.hospitalRestrain = hospitalRestrain;
		}
		public HashMap getNumLimit() {
			return numLimit;
		}
		public int getNumLimit(int doctorsn)
		{
			String value=(String)numLimit.get(Integer.toString(doctorsn));
			if(value==null)
			{
				value=(String)numLimit.get(Integer.toString(0));
			}
			return Integer.parseInt(value);
		}
		public void setNumLimit(HashMap numLimit) {
			this.numLimit = numLimit;
		}
		public int getOccupyWay() {
			return occupyWay;
		}
		public void setOccupyWay(int occupyWay) {
			this.occupyWay = occupyWay;
		}
		public String getDoctorLevel() {
			return doctorLevel;
		}
		public boolean isDoctorLevelPermit(int levelID) 
		{
			if(doctorLevel.indexOf(Integer.toString(levelID))>=0)
			{
				return true;
			}
			
			return false;
			
			
		}
		public void setDoctorLevel(String doctorLevel) {
			this.doctorLevel = doctorLevel;
		}
	}
	
	public class ACCRule extends Rule
	{
		/**
		 * �Ƿ������ֵ<br>
		 * 	Ĭ�Ͽɳ�ֵ
		 */
		private boolean chargeOk = true;
		/**
		 * �Ƿ���������<br>
		 * Ĭ����������
		 */
		private boolean gradeOk = true;
		/**
		 * �Ƿ������˿�<br>
		 * Ĭ�������˿�
		 */
		private boolean backCardOk = true;
		/**
		 * �Ƿ�������<br>
		 * Ĭ��������
		 */
		private boolean replacementCardOk =true; 


		public void setChargeOk(boolean chargeOk) {
			this.chargeOk = chargeOk;
		}

		public boolean isChargeOk() {
			return chargeOk;
		}
		
		public void setGradeOk(boolean gradeOk) {
			this.gradeOk = gradeOk;
		}

		public boolean isGradeOk() {
			return gradeOk;
		}
		
		public boolean isBackCardOk() {
			return backCardOk;
		}

		public void setBackCardOk(boolean backCardOk) {
			this.backCardOk = backCardOk;
		}

		public boolean isReplacementCardOk() {
			return replacementCardOk;
		}

		public void setReplacementCardOk(boolean replacementCardOk) {
			this.replacementCardOk = replacementCardOk;
		}
		
	}
		
}
