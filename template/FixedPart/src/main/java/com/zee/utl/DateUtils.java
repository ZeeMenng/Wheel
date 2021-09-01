package com.zee.utl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2018年1月6日 下午2:29:02
 * @updateDate 2018年1月6日 下午2:29:02
 * @description 日期转换等处理工具，来源于生猪项目。
 */
public class DateUtils {

	/**
	 * 取当前日期，无时分秒
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getCurrentTime() {
		return new Date();

	}

	/**
	 * 获取当前日期
	 * @Title: getCurrentDateStr 
	 * @Description: 获取当前日期 yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDateStr() {
		Date date = new Date();
		return date2String(date, CustomSymbolic.DATE_FORMAT);
	}
	
	public static String getCurrentTimeStr() {
		Date date = new Date();
		return date2String(date, CustomSymbolic.DATETIME_FORMAT);
	}

	/**
	 * 给Date增加second秒
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 给Date增加hourz小时
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 将UTC时间转成中国本地时间
	 * @param date
	 * @return
	 */
	public static Date UTC2LocalTime(Date date) {
		return addHour(date, 8);
	}

	/**
	 * 判断日期是否在有效期内
	 * 返回true时已过期，false未过期
	 * @param expire
	 * @return
	 */
	public static boolean isUserSessionExpire(Date expire) {
		boolean isExpire = true;
		Calendar now = Calendar.getInstance();
		Calendar expireCalendar = Calendar.getInstance();
		expireCalendar.setTime(expire);

		isExpire = now.after(expireCalendar);
		return isExpire;
	}

	/**
	 * 格式化时间
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static String date2String(Date date, String formatPattern) {
		String dateStr = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 字符串转日期
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static Date string2Date(String date, String formatPattern) {
		Date result = null;
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatPattern);
			result = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式化日期
	 * yyyy-MM-dd HH:mm:ss
	 * @param datetime
	 * @return
	 */
	public static Date string2Datetime(String datetime) {
		if (StringUtils.isBlank(datetime)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(CustomSymbolic.DATETIME_FORMAT);

		Date date = null;
		try {
			date = format.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将时间戳转化成String时间
	 * @param time
	 * @return
	 */
	public static String convertDateTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat(CustomSymbolic.DATETIME_FORMAT);
		String outDate = format.format(time);
		return outDate;
	}

	/**
	 * 日期比较
	 * @param date1
	 * @param date2
	 * @return date1 < date2 return -1
	 *         date1 > date2 return  1
	 *         date1 = date2 return  0
	 */
	public static int comparator(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		if (calendar1.before(calendar2)) {
			return -1;
		} else if (calendar1.after(calendar2)) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取按时间往后推 的日期
	 * @Title: getDayTime 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param days 时间间隔
	 * @return 2017-07-12
	 */
	public static String getDayTime(int days) {
		Calendar c = Calendar.getInstance();
		int day = c.get(c.DAY_OF_YEAR) - days;
		c.set(c.DAY_OF_YEAR, day);
		String time = date2String(c.getTime(), CustomSymbolic.DATE_FORMAT);
		return time;
	}

	/**
	 * 按照formatPattern格式，获取lastQueryDate和currentDate之间的时间列表
	 * @param lastQueryDate
	 * @param currentDate
	 * @param formatPattern
	 * @return
	 */
	public static List getQueryDateList(String lastQueryDate, String currentDate,String formatPattern) {
		List<String> queryDateList = new ArrayList<String>();
		Date startDate = string2Date(lastQueryDate,formatPattern);
		Date endDate = string2Date(currentDate,formatPattern);
		int num = differentDays(startDate,endDate);
		for(int i = 0;i<num+1;i++) {
			Date date = new Date();
			Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(startDate); 
		    calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动 
		    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		    String queryDate = date2String(date,formatPattern);
		    queryDateList.add(queryDate);
		}
		return queryDateList;
	}
	
	/**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
//            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }	
    
    /**
     * 获取两个月份之间的时间列表
     * @param minDate	起始月份
     * @param maxDate	结束月份
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
	    ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();

	    min.setTime(sdf.parse(minDate));
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	    Calendar curr = min;
	    while (curr.before(max)) {
	     result.add(sdf.format(curr.getTime()));
	     curr.add(Calendar.MONTH, 1);
	    }

	    return result;
	  }

    /**
     * 获取两个年份之间的时间列表
     * @param string	起始年份
     * @param endTime	结束年份
     * @return
     */
	public static List<String> getYearBetween(String startTime, String endTime) {
		ArrayList<String> result = new ArrayList<String>();
		int start = Integer.parseInt(startTime);
		int end = Integer.parseInt(endTime);
		while(start<=end) {
			result.add(start+"");
			start+=1;
		}
		return result;
	}
	
    /**
	 * 获取按时间往后推 的日期
	 * @Title: getMonthTime 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param month 时间间隔
	 * @return 2017-07-12
	 */
	public static Date getMonthTime(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, month);   
		return calendar.getTime();
	}
	
	/**
	 * 获取两个时间内的全部日期
	 * @param dBegin	开始时间
	 * @param dEnd		结束时间
	 * @return
	 */
	public static List<String> findDates(Date dBegin, Date dEnd){
		  List<String> lDate = new ArrayList<String>();
		  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  lDate.add(sd.format(dBegin));
		  Calendar calBegin = Calendar.getInstance();
		  calBegin.setTime(dBegin);
		  Calendar calEnd = Calendar.getInstance();
		  calEnd.setTime(dEnd);
		  while (dEnd.after(calBegin.getTime()))
		  {
			   calBegin.add(Calendar.DAY_OF_MONTH, 1);
			   lDate.add(sd.format(calBegin.getTime()));
		  }
		  return lDate;
	}
}
