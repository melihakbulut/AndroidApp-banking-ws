package com.banking.soap;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class DateType {

	
	public static String formatDate(DateTime date){
		
		DateTime dt2 = date;
		return dt2.getYear()+"-"+dt2.getMonthOfYear()+"-"+dt2.getDayOfMonth()+
				" "+dt2.getHourOfDay()+":"+dt2.getMinuteOfHour()+":"+dt2.getSecondOfMinute();
	}
	
	public static DateTime toDateTime(String date){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
		DateTime dt = null;
				try {
					dt = formatter.parseDateTime(date);
				} catch (java.lang.IllegalArgumentException e) {
					formatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.SSS");
					dt = formatter.parseDateTime(date);
				}
			
	    
	    return dt;
	}
	
	public static String getProperDateBetween(DateTime dt1,DateTime dt2){
		Period period = new Period(dt1, dt2, PeriodType.dayTime());
		
		PeriodFormatter formatter = new PeriodFormatterBuilder()
		        .appendDays().appendSuffix(" day ", " gün ")
		        .appendHours().appendSuffix(" hour ", " saat ")
		        .appendMinutes().appendSuffix(" minute ", " dakika ")
		        .appendSeconds().appendSuffix(" second ", " saniye ")
		        .toFormatter();
		
		return formatter.print(period);
		
	}
	
}
