/**
 * 0. Project  : Finobot Monstro
 *
 * 1. FileName : DateUtil.java
 * 2. Package : kr.co.finotek.finobot.common.util
 * 3. Comment : 
 * 4. 작성자  : sky
 * 5. 작성일  : 2017. 9. 7. 오후 3:26:20
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    sky : 2017. 9. 7. :            : 신규 개발.
 */

package com.wooricard.chat.gw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	* <PRE>
	* 간략 : .
	* 상세 : .
	* <PRE>
	* @return 
	*/
	public static Long getCurrentDateToLong(){
		Date dte=new Date();
		long milliSeconds = dte.getTime();
		return milliSeconds;
	}

	/**
	* <PRE>
	* 간략 : .
	* 상세 : .
	* <PRE>
	* @param currentDate
	* @return 
	*/
	public static Long getDateToLong(String currentDate){
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		long milliseconds = 0;
		try {
			Date parseDate = f.parse(currentDate);
			milliseconds = parseDate.getTime();
		} catch (ParseException e) {
			logger.error("getDateToLong", e);
		}
		return milliseconds;
	}
	
	/**
	 * <PRE>
	 * 간략 : .
	 * 상세 : .
	 * <PRE>
	 * @return 
	 */
	public static String getCurrentRealTime(){
		SimpleDateFormat sdf	= new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal			= Calendar.getInstance();

		cal.setTime(new Date());
		return sdf.format(cal.getTime());
	}


	public static String getCurrentTimeMinute(){
		SimpleDateFormat sdf	= new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal			= Calendar.getInstance();
		
		cal.setTime(new Date());
		return sdf.format(cal.getTime());
	}

	public static String getFullCurrentTime(){
		SimpleDateFormat sdf	= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal			= Calendar.getInstance();
		
		cal.setTime(new Date());
		return sdf.format(cal.getTime());
	}

	public static String getCurrentTime(String dateFormat){
		SimpleDateFormat sdf	= new SimpleDateFormat(dateFormat);
		Calendar cal			= Calendar.getInstance();
		
		cal.setTime(new Date());
		return sdf.format(cal.getTime());
	}

	public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
		String dateFormat = "yyyyMMddHHmmssSSS";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(milliSeconds));
		return simpleDateFormat.format(calendar.getTime());
	}

	public static Date getISODate(){
		SimpleDateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");

		Date now = new Date();
		Date retDate = null;
		try {
			retDate = dateFormat.parse(dateFormat.format(now));
		} catch (ParseException e) {
			logger.error("getISODate", e);
		}
		return retDate;
	}
	
	public static String[] getCurrentDay(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 

		String strDay = sdf.format(cal.getTime());
		String day[] = {strDay.substring(0,4), strDay.substring(4,6), strDay.substring(6)};
		return day;
	}
}
