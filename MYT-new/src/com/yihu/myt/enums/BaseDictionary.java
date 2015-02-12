package com.yihu.myt.enums;

/**
 * @author Administrator
 *
 */
public class BaseDictionary {

	public class OperatorType {
		
		public static final int add=1;
		public static final int update=2;
		public static final int delete=3;
	}
	public class OperatorItem {

		public static final int baseHospital=1;
		public static final int baseDept=2;
		public static final int baseDoctor=3;
		public static final int ghtArr=4;
		public static final int mytArr=5;
	}
	public class CheckState {

		public static final int unDeal=0;
		public static final int noPass=1;
		public static final int pass=2;
	}
	public static String formatSex(int sex)
	{
		String s_sex="";
		switch(sex)
		{
			case Sex.boy:s_sex="男";break;
			case Sex.girl:s_sex="女";break;
			case Sex.unknown :s_sex="未知";break;
			default:break;
		}
		return s_sex;
	}
	public static String formatTimeID(int timeid)
	{
		String s="";
		switch(timeid)
		{
			case 1:s="上午";break;
			case 2:s="下午";break;
			case 3 :s="晚上";break;
			default:break;
		}
		return s;
	}
	public static String formatDoctorLevel(int doctorLevel)
	{
		String s_doctorLevel="";
		switch(doctorLevel)
		{
			case DoctorLevel.pt:s_doctorLevel="普通";break;
			case DoctorLevel.gt:s_doctorLevel="享受国家津贴的专家";break;
			case DoctorLevel.zr:s_doctorLevel="主任医生";break;
			case DoctorLevel.fzr:s_doctorLevel="副主任医生";break;
			case DoctorLevel.ts:s_doctorLevel="特殊";break;
			default:break;
		}
		return s_doctorLevel;
	}
	public static String formatCliniclevel(int cliniclevel)
	{
		String s_cliniclevel="";
		switch(cliniclevel)
		{
			case Cliniclevel.zr:s_cliniclevel="主任医生";break;
			case Cliniclevel.fzr:s_cliniclevel="副主任医生";break;
			case Cliniclevel.zz:s_cliniclevel="主治医生";break;
			case Cliniclevel.qt:s_cliniclevel="其他";break;
			default:break;
		}
		return s_cliniclevel;
	}
	public static String formatDayOfWeek(int dayid)
	{
		String week="";
		switch(dayid)
		{
		    case 1:week="星期一";break;
		    case 2:week="星期二";break;
		    case 3:week="星期三";break;
		    case 4:week="星期四";break;
		    case 5:week="星期五";break;
		    case 6:week="星期六";break;
		    case 0:week="星期日";break;
		    default:break;
		}
		return week;
	}
	public static String formatArrangeType(int type){
		String typeName = "";
		
		switch(type){
		case 1:typeName = "普通门诊";break;
		case 2:typeName = "特殊门诊";break;
		case 3:typeName = "专家门诊";break;
		case 4:typeName = "专属门诊";break;
		case 5:typeName = "副主任门诊";break;
		case 6:typeName = "正主任门诊";break;
		case 7:typeName = "退休老专家";break;
		case 8:typeName = "影像专家会诊";break;
		}
			
		return typeName; 
	}
	public  class Business
	{
		public static final String ght="GHT";
		
		public static final String myt="MYT";
		
		public static final String tjt="TJT";
		
		public static final String acc="ACC";
		
		public static final String babyplan="BABYPLAN";
		
	}
	public  class Sex
	{
		/**
		 * 男
		 */
		public static final int boy=1;
		/**
		 * 女
		 */
		public static final int girl=2;
		/**
		 * 未知
		 */
		public static final int unknown=3;
		
		
	}
	public class  Cliniclevel
	{
		/**
		 * 主任
		 */
		public static final int zr=0;
		/**
		 * 副主任
		 */
		public static final int fzr=1;
		/**
		 * 主治
		 */
		public static final int zz=2;
		/**
		 * 其他
		 */
		public static final int qt=3;
	}
	public  class DoctorLevel
	{
		/**
		 * 普通医生
		 */
		public static final int pt=0;
		/**
		 * 国贴专家
		 */
		public static final int gt=1;
		/**
		 * 主任医生
		 */
		public static final int zr=2;
		/**
		 * 副主任医生
		 */
		public static final int fzr=3;
		/**
		 * 检查类(特殊)
		 */
		public static final int ts=4;
		
	}
	
	
}
