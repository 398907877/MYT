package com.yihu.myt.service;

import java.util.HashMap;


/**
 * @author admin
 *
 */
public class BusinessRule {
	/**
	 * 业务规则基类
	 * @author admin
	 *
	 */
	public class Rule 
	{
		
	}
	public class  MYTRule  extends Rule
	{
		/**
		 * 咨询等级约束
		 */
		private String LevelRestrain;
		/**
		 * 业务开关
		 */
		private boolean  isOk;
	
		/**
		 * 是否允许全科咨询
		 */
		private boolean  isQkOk;
		public boolean isQkOk() {
			return isQkOk;
		}
		public void setQkOk(boolean isQkOk) {
			this.isQkOk = isQkOk;
		}
		/**
		 * 构造函数
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
		 * 可提前天数
		 */
		private HashMap forwardDay;
		/**
		 * 处理挂号费
		 */
		private boolean hasGhFee;
		/**
		 * 号源占用费处理方式
		 */
		private int occupyWay;
		/**
		 * 预约医院约束 
		 */
		private String[] hospitalRestrain;
		/**
		 * 会员限号
		 */
		private HashMap numLimit;
		/**
		 * 医生等级约束
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
		 * 是否允许充值<br>
		 * 	默认可充值
		 */
		private boolean chargeOk = true;
		/**
		 * 是否允许升级<br>
		 * 默认允许升级
		 */
		private boolean gradeOk = true;
		/**
		 * 是否允许退卡<br>
		 * 默认允许退卡
		 */
		private boolean backCardOk = true;
		/**
		 * 是否允许补卡<br>
		 * 默认允许补卡
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
