package com.goldcard.usmart.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFilterUtil {
	

	
	/**
     * 日期格式化
	 * @return	返回yyyy-MM样式的日期字符串
	*/
	public static String dateFormatYYYYMM(java.util.Date date)
	{
		if (date.getTime() <= 0)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM");
		return formatter.format(date);
	}
	public static String dateFormatString(java.util.Date date,String format)
	{
		if (date.getTime() <= 0)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		return formatter.format(date);
	}	
	public static String dateFormatYYYY(java.util.Date date)
	{
		if (date.getTime() <= 0)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
		return formatter.format(date);
	}
	public static String dateFormatYYYYMMDD(java.util.Date date)
	{
		if (date.getTime() <= 0)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	//yyyyMMddHHmmss星期   星期1=01
	public static String dateFormatYYYYMMDDHHmmssW(java.util.Date date){
		StringBuffer sb = new StringBuffer();
		sb.append(DateFilterUtil.dateFormatString(date,"yyyyMMddHHmmss"));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(i==0){
			i=7;
		}
		return sb.append("0").append(i).toString();
	}
   public static java.util.Date parserYMD(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}
   
   public static java.util.Date datepParserYMD(java.util.Date date) {
	   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
	   String strDate=formatter.format(date);
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   try {
			return sdf.parse(strDate);
	    } catch (Exception e) {
			return null;
		}
	}
   
   public static String datepParserYMDHMS(java.util.Date date) {
	   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String strDate=formatter.format(date);
	   return strDate;
   }
   public static java.util.Date parserFormate(String strDate,String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}
   
   public static java.util.Date parserFormateYMDHMS(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

  /**
     * 取得指定月份的第一天
     *
     * @param strdate String
     * @return String
     */
    public static Date getMonthBegin(Date date)
    {
        
        return parserYMD(dateFormatYYYYMM(date) + "-01");
    }
  
    /**
     * 取得年初第一天
     *
     * @param strdate String
     * @return String
     */
    public static Date getYearBegin(Date date)
    {
        
        return parserYMD(dateFormatYYYY(date) + "-01-01");
    }
    
    public static Date getSomeMonth(Date date,String month)
    {
        return parserYMD(dateFormatYYYY(date)+"-" +month+ "-01");
    }
 

    /**
     * 取得指定月份的最后一天
     *
     * @param strdate String
     * @return String
     */
    @SuppressWarnings("static-access")
	public static  Date getMonthEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMonthBegin(date));
        calendar.add(calendar.MONTH,1);
        calendar.add(calendar.DATE, -1);
        return calendar.getTime();
    }
    
    /**
	 * 增加月份
	 * @param date
	 * @param num
	 * @return
	 */
	public static java.util.Date addMonths(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}
	
	 /**
	 * 增加年
	 * @param date
	 * @param num
	 * @return
	 */
	public static java.util.Date addyears(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, num);
		return cal.getTime();
	}
	
	/**
	 * 增加天数
	 * @param date
	 * @param num
	 * @return
	 */
	public static java.util.Date addDays(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, num);
		return cal.getTime();
	}
	
	/**
	 * 增加小时
	 * @param date
	 * @param num
	 * @return
	 */
	public static java.util.Date addhours(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, num);
		return cal.getTime();
	}
	/**
	 * 增加小时
	 * @param date
	 * @param num
	 * @return
	 */
	public static java.util.Date addMinutes(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, num);
		return cal.getTime();
	}
	public static int compareTwoDay(Date date1, Date date2) {
		String yyyy_mm1 = dateFormatYYYYMMDD(date1);
		String yyyy_mm2 = dateFormatYYYYMMDD(date2);
		if (yyyy_mm1 == null && yyyy_mm2 == null) {
			return 0;
		}
		if (yyyy_mm1 == null) {
			return -1;
		}
		if (yyyy_mm2 == null) {
			return 1;
		}

		return yyyy_mm1.compareTo(yyyy_mm2);
	}
	
	/**
	 * 判断两个日期是否 在同一个月份
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean compareSameMonth(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		return calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR) &&  calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH);
	}
	
	public static Boolean compareSameYear(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		return calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR);
	}
	public static Boolean compareSameDay(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		return calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR) &&  calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH)
				&&  calendar1.get(Calendar.DAY_OF_YEAR)==calendar2.get(Calendar.DAY_OF_YEAR);
	}
	public static  int getDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
	
	public static  int getMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }
	
	public static  int getYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
	
	/**
	 * 计算两个日期之间相差天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDayNumber(Date date1,Date date2){
		long daterange = parserYMD(dateFormatYYYYMMDD(date2)).getTime() - parserYMD(dateFormatYYYYMMDD(date1)).getTime(); 
		long time = 1000*3600*24; //A day in milliseconds 
		long dayNumber = daterange/time;	
		Long z=new Long(dayNumber);
		int c=z.intValue();
		return c;
	}
	/**
	 * 比较两个时间之差是否在1表小时之内
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1,Date date2){
		int result=1;
		long count=date1.getTime()-date2.getTime();
	    if(count>-1000*3600&&count<1000*3600){
	    	result=0;
	    }
	    return result;
	}
    public static String datepParserYMDH(java.util.Date date) {
	   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH");
	   String strDate=formatter.format(date);
	   strDate=strDate+":00:00";
	   return strDate;
    }
    
    public static int computeHour(Date date1,Date date2){
      long count=parserFormate(datepParserYMDH(date1),"yyyy-MM-dd HH:mm:ss").getTime()-parserFormate(datepParserYMDH(date2),"yyyy-MM-dd HH:mm:ss").getTime();
      long time = 1000*3600; 
      long dayNumber=count/time;
      return (int)dayNumber;
      
    }
	/**
	 * 相差多个小时
	 * @param date1
	 * @param date2
	 * @param hour
	 * @return
	 */
	public static int compareSomeDate(Date date1,Date date2,int hour){
		int result=1;
		long count=date1.getTime()-date2.getTime();
	    if(count>-1000*3600*hour&&count<1000*3600*hour){
	    	result=0;
	    }
	    return result;
	}
	/**
	 * 计算两个时间相差多少秒
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long computeDate(Date date1,Date date2){
		long count=date1.getTime()-date2.getTime();
	    return count/1000;
	}
	/**
	 * 两个时间相差的月份
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferCount(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		return  (calendar1.get(Calendar.YEAR)*12)+calendar1.get(Calendar.MONTH)-((calendar2.get(Calendar.YEAR)*12)+calendar2.get(Calendar.MONTH));
	}
	
}
