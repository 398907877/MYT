package com.yihu.myt.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理类
 * @author wangfeng
 * @company yihu.com
 * 2012-8-6下午03:31:45
 */
public final class DateUtil {

	public static final int DAY_UNIT = 1;

	public static final int MONTH_UNIT = 2;

	public static final int YEAR_UNIT = 3;

	public static final int WEEK_UNIT = 4;

	public static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YMDHMS_FORMAT_ALL =  "yyyyMMddHHmmsssss";

	public static final String YMD_FORMAT = "yyyy-MM-dd";
	
	public static final String YMD_FORMAT_NODAY = "yyyyMM";	
	
	public static final String YMD_FORMAT_NOYM = "dd";	
	
	public static final String YMD_FORMAT_OTHER = "yyyy/MM/dd";

	public static final String HMS_FORMAT = "HH:mm:ss";

	public static final String HM_FORMAT = "HH:mm";

	public static final String TIMEZONE_0 = "GMT+0";
	
	public static final String YMDHM_FORMAT_OTHER =  "yyyy/MM/dd HH:mm";
	

	public static final int DAY_MILL = 86400000;// 一天的毫秒数

	/**
	 * 时间，格式处理
	 * @param format 格式 默认:yyyy-MM-dd HH:mm:ss,24小时制
	 * @param date日期 默认:当前时间
	 * @return String类型的时间
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isNotEmpty(format) ? format : YMDHMS_FORMAT);
		return sdf.format(null != date ? date : new Date());
	}

	/**
	 * 时间，格式处理
	 * @param format 格式 默认:yyyy-MM-dd HH:mm:ss
	 * @param date待转换字符串的日期表示
	 * @return Date类型的时间
	 */
	public static Date stringFormat(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isNotEmpty(format) ? format : YMDHMS_FORMAT);
		if (StringUtils.isNotEmpty(date))
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				System.out.println("\n----Exception:cannot convert '" + date + "' to '" + format + "' date!----");
			}
		return null;
	}

	/**
	 * 处理自定义格式当前时间
	 * @param format 格式
	 * @return String类型转换格式后的当前时间
	 */
	public static String dateFormat(String format) {
		return dateFormat(null, format);
	}

	/**
	 * 按默认格式处理时间
	 * @param date 日期
	 * @return String类型默认格式的时间 默认:yyyy-MM-dd HH:mm:ss
	 */
	public static String dateFormat(Date date) {
		return dateFormat(date, null);
	}

	/**
	 * 将当前时间按默认格式进行处理
	 * @return String类型的时间 默认:yyyy-MM-dd HH:mm:ss
	 */
	public static String dateFormat() {
		return dateFormat(null, null);
	}

	/**
	 * 将日期的字符串表示方式转换成Date
	 * 默认为"yyyy-MM-dd HH:mm:ss"格式
	 * @param date日期的字符串表示
	 * @return Date日期
	 */
	public static Date stringFormat(String date) {
		return stringFormat(date, null);
	}

	/**
	 * 按指定时区 转换毫秒数
	 * @param longTime
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static String praseLong(Long longTime, String format, String timeZone) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(new Date(longTime));
	}

	/**
	 * 获取当前时间的毫秒数
	 * @return
	 */
	public static long getCurrMills() {
		return (new Date()).getTime();
	}

	/**
	 * 取当前时间的日历对象
	 * @return
	 */
	public static Calendar getCurrCalendar() {
		return getCalendar(new Date());
	}

	/**
	 * 取 date 的Calendar 对象
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 获取一天最后一秒 23:59:59秒时的date
	 * @param date
	 * @return
	 */
	public static Date getLastTimeOfDay(Date date) {
		return getTimeOfDay(date, "23:59:59");
	}

	/**
	 * 获取一天的第一秒 00:00:00秒时的date
	 * @param date
	 * @return
	 */
	public static Date getFirstTimeOfDay(Date date) {
		return getTimeOfDay(date, "00:00:00");
	}

	public static Date getTimeOfDay(Date date, String time) {
		String orig = dateFormat(date, YMD_FORMAT);
		return StringUtils.isNotEmpty(orig) ? stringFormat(orig + " " + time) : null;
	}

	public static Date getTimeOfDay(Date date, String time,String format,String formatOut) {
		String orig = dateFormat(date, format);
		return StringUtils.isNotEmpty(orig) ? stringFormat(orig + " " + time,formatOut) : null;
	}
	/**
	 * 获取 日期 在field上的 起始时间 如：获取 date 所在月的第一天最开始时间：getBeginTimeForField(date,Calendar.DAY_OF_MONTH);
	 * @param cal
	 * @param rollField
	 * @return
	 */
	public static Date getFirstTimeOfField(Calendar cal, int rollField) {
		Date begin = getRollDate(cal, Calendar.DATE, (cal.get(rollField) - 1) * -1);
		return getFirstTimeOfDay(begin);
	}

	/**
	 * 获取 日期 在field上的 起始时间 如：获取 date 所在月的第一天最开始时间：getBeginTimeForField(date,Calendar.DAY_OF_MONTH);
	 * @param date
	 * @param rollField
	 * @return
	 */
	public static Date getFirstTimeOfField(Date date, int rollField) {
		return getFirstTimeOfField(getCalendar(date), rollField);
	}

	/**
	 * 获取 日历对象 在amount范围内的 date
	 * @param field  哪个属性上 摆动
	 * @param amount 增减幅度
	 * @return
	 */
	public static Date getRollDate(Calendar cal, int field, int amount) {
		cal.add(field, amount);
		return cal.getTime();
	}

	public static Date getRollDate(Date date, int field, int amount) {
		Calendar c = getCalendar(date);
		return getRollDate(c, field, amount);
	}

	/**
	 * 获取 当前日历对象 在amount范围内的 date
	 * @param field  哪个属性上 摆动
	 * @param amount 增减幅度
	 * @return
	 */
	public static Date getRollDate(int field, int amount) {
		return getRollDate(new Date(), field, amount);
	}

	/**
	 * 获取 date 日期 所属星期的起始时间 终止时间 （所属星期的星期日至次星期日）
	 * add by huangyimin ${date} Nov 25, 2010 
	 * @param date
	 * @return date[]{星期日 00:00:00，次星期日00:00:00} 精确到秒
	 * 时间范围 应该为：>= beginDate 00:00:00 and < endDate 00:00:00 
	 */
	public static Date[] getRollWeek(Date date) {
		return getRollTime(date, Calendar.DATE, Calendar.DAY_OF_WEEK, 7);
	}

	/**
	 * 获取 date 日期 所属星期的起始时间 终止时间 （所属星期的星期一至次星期一）
	 * @param date
	 * @return date[]{星期一 00:00:00，次星期一00:00:00} 精确到秒
	 * 时间范围 应该为：>= beginDate 00:00:00 and < endDate 00:00:00 
	 */
	public static Date[] getRollWeekZH(Date date) {
		Date[] dateRoll = new Date[2];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK);
		d = d == 1 ? 6 : d == 2 ? 0 : d - 2;
		dateRoll[0] = getFirstTimeOfDay(getRollDate(date, Calendar.DATE, d * -1));
		dateRoll[1] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 7));
		return dateRoll;
	}
	/**
	 * 获取 date 日期 所属星期的起始时间 终止时间 （所属星期的星期一至次星期天）
	 * @param date
	 * @return date[]{星期一 00:00:00，次星期一00:00:00} 精确到秒
	 * 时间范围 应该为：>= beginDate 00:00:00 and < endDate 00:00:00 
	 */
	public static Date[] getRollWeekMTS(Date date) {
		Date[] dateRoll = new Date[2];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK);
		d = d == 1 ? 6 : d == 2 ? 0 : d - 2;
		dateRoll[0] = getFirstTimeOfDay(getRollDate(date, Calendar.DATE, d * -1));
		dateRoll[1] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 6));
		return dateRoll;
	}
	/**
	 * 获取 date 日期 所属星期的起始时间 终止时间 （所属星期的星期一至星期日）
	 * @param date
	 * @return date[]{星期一 00:00:00，星期日00:00:00} 精确到秒
	 * 时间范围 应该为：>= beginDate 00:00:00 and < endDate 00:00:00 
	 */
	
	public static Date[] getRollAllWeekZH(Date date) {
		Date[] dateRoll = new Date[7];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK);
		d = d == 1 ? 6 : d == 2 ? 0 : d - 2;
		dateRoll[1] = getFirstTimeOfDay(getRollDate(date, Calendar.DATE, d * -1));
		dateRoll[2] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 1));
		dateRoll[3] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 2));
		dateRoll[4] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 3));
		dateRoll[5] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 4));
		dateRoll[6] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 5));
		dateRoll[0] = getFirstTimeOfDay(getRollDate(dateRoll[1], Calendar.DATE, 6));

		
		return dateRoll;
	}
	public static Date[] getRollAllMohthZH(Date date) {
		Date[] dateRoll = new Date[7];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_MONTH);
		d = d == 1 ? 6 : d == 2 ? 0 : d - 2;
		dateRoll[0] = getFirstTimeOfDay(getRollDate(date, Calendar.DATE, d * -1));
		dateRoll[1] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 1));
		dateRoll[2] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 2));
		dateRoll[3] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 3));
		dateRoll[4] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 4));
		dateRoll[5] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 5));
		dateRoll[6] = getFirstTimeOfDay(getRollDate(dateRoll[0], Calendar.DATE, 6));
		return dateRoll;
	}
	
	public static List getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List lDate = new ArrayList();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}
	
	/**
	 * 获取 date 日期 在 天 单位上的 起始时间 终止时间 
	 * 返回 当天的00:00:00  次日的00:00:00
	 * @param date
	 * @return
	 */
	public static Date[] getRollDay(Date date) {
		return new Date[] { getFirstTimeOfDay(date), getFirstTimeOfDay(getRollDate(date, Calendar.DATE, 1)) };
	}

	/**
	 * 获取 date 日期 在 月 单位上的 起始时间 终止时间 
	 * 返回 当月第一天的00:00:00  次月第一天的00:00:00
	 * @param date
	 * @return
	 */
	public static Date[] getRollMonth(Date date) {
		return getRollTime(date, Calendar.MONTH, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取 date 日期 在 年 单位上的 起始时间 终止时间 
	 * 返回 当年第一天的00:00:00  次年第一天的00:00:00
	 * @param date
	 * @return
	 */
	public static Date[] getRollYear(Date date) {
		return getRollTime(date, Calendar.YEAR, Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取 一个时间 所表示的在field上 浮动 时间值
	 * 如：
	 * 	取当前时间 在年范围内 的起始时间和终止时间，当前时间：2010-8-10  则返回 2010-01-01 00:00:00,2011-01-01 00:00:00
	 * 	取当前时间 在月范围内 的起始时间和终止时间，当前时间：2010-8-10  则返回 2010-08-01 00:00:00,2010-09-01 00:00:00
	 * 	取当前时间 在日范围内 的起始时间和终止时间，当前时间：2010-8-10  则返回 2010-08-10 00:00:00,2010-08-11 00:00:00
	 * @param date 时间
	 * @param field 在那个单位上 浮动
	 * @param rollField 在单位上 到起始时间的间隔。通常为：Calendar.DAY_OF_MONTH,Calendar.DAY_OF_YEAR等
	 * @param endValue 终止时间 向后推移的值。通常为：1，表示像后推移一个月，一天，一年。当用于星期时为7。
	 * @return 时间范围 new Date [] {begin ,end}
	 */
	public static Date[] getRollTime(Date date, int field, int rollField, int... endValue) {
		if (null != date) {
			Date begin = getFirstTimeOfField(date, rollField);
			int value = endValue.length > 0 ? endValue[0] : 1;
			Date end = getRollDate(begin, field, value);
			return new Date[] { begin, end };
		}
		return null;
	}

	/**
	 * 判断时间date 是否在date[]{起始时间，结束时间}之间 
	 * @param date 待判断的时间
	 * @param dateRoll 时间范围
	 * @return 
	 */
	public static boolean dateInRoll(Date date, Date[] dateRoll) {
		if (null != date && null != dateRoll) {
			if (dateRoll.length == 1) {
				return date.before(dateRoll[0]);
			} else if (dateRoll.length == 2) {
				if (null == dateRoll[0]) {
					return date.before(dateRoll[1]);
				} else if (null == dateRoll[1]) {
					return date.before(dateRoll[0]);
				}
				return date.after(dateRoll[0]) && date.before(dateRoll[1]);
			}
		}
		return false;
	}

	/**
	 * 判断时间date 是否在rollDate 依照field浮动后的日期范围 
	 * @param date 
	 * @param rollDate 待浮动日期
	 * @param field 单位
	 * @return
	 */
	public static boolean dateInRollByField(Date date, Date rollDate, int field) {
		switch (field) {
			case DAY_UNIT:
				return dateInRoll(date, getRollDay(rollDate));
			case WEEK_UNIT:
				return dateInRoll(date, getRollWeek(rollDate));
			case MONTH_UNIT:
				return dateInRoll(date, getRollMonth(rollDate));
			case YEAR_UNIT:
				return dateInRoll(date, getRollYear(rollDate));
		}
		return false;
	}

	/**
	 * 计算 两个日期的 天数差(精确时分秒之差 如：2010-11-02 10:00:00与2010-11-04 09:00:00 将返回1)
	 * @param date
	 * @param _date
	 * @return
	 */
	public static long dateDiff(Date date, Date _date) {
		return null != date && null != _date ? Math.abs((date.getTime() - _date.getTime()) / DAY_MILL) : 0l;
	}

	/**
	 * 计算 两个日期的 天数差(忽略时分秒之差 只计算日期差 如：2010-11-02 10:00:00与2010-11-04 09:00:00 将返回2)
	 * @param date
	 * @param _date
	 * @return
	 */
	public static long dateDiffIgnore(Date date, Date _date) {
		return dateDiff(DateUtil.getFirstTimeOfDay(date), DateUtil.getFirstTimeOfDay(_date));
	}
	
	/**
	 * 字符串转日期格式
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String format) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.parse(date);
	}
	
	/**
	 * 时间long型转换为Timestamp型
	 * @param parTime
	 * @return
	 */
	public static Timestamp parse(Long parTime) {
		return new Timestamp(parTime);
	}
	
	public static String getBeforeDate(Date date,int days,String format)  
	{  
	    SimpleDateFormat df = new SimpleDateFormat(StringUtils.isNotEmpty(format) ? format : YMDHMS_FORMAT);  
	    Calendar calendar = Calendar.getInstance();     
	    calendar.setTime(date);  
	    calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) - days);  
	    return df.format(calendar.getTime());  
	}  
	  
	public static String getAfterDate(Date date,int days,String format)  
	{  
	    SimpleDateFormat df = new SimpleDateFormat(StringUtils.isNotEmpty(format) ? format : YMDHMS_FORMAT);  
	    Calendar calendar = Calendar.getInstance();     
	    calendar.setTime(date);  
	    calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) + days);  
	    return df.format(calendar.getTime());  
	}
	public static int getDate(Date date){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date); 
        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
       /* if(week == 0)
            return week=7;
        else*/
        return week;
    }
	public static void main(String[] args) {
		Date[] r = getRollWeekZH(DateUtil.stringFormat("2011-4-24 00:00:00"));
		for (Date date : r) {
			System.out.println(dateFormat(date));
		}
	}
	/**
	 * 判断当前时间是否在这2个时间之内
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean getIfInTime(String star,String end){
	    SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try{
	        Date sdate = localTime.parse(star);
	        Date edate=localTime.parse(end);
	        long time = System.currentTimeMillis();
	        Date date=new Date();
	        System.out.println(time+"##"+sdate.getTime()+"##"+edate.getTime());
	        if(date.after(sdate)&& date.before(edate)){
	        	return true;
	        }else{
	        	return false;
	        }
	    }catch(Exception e){
			return false;
	    }
	}
}
