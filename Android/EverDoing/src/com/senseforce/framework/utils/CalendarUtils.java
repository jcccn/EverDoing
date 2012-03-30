package com.senseforce.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.senseforce.everdoing.Constants;

public class CalendarUtils {
	
	public static final String YMDHMA = "yyyy-MM-dd hh:mm a";
	public static final String YMDHM = "yyyy-MM-dd HH:mm";
	public static final String MDHM = "MM-dd HH:mm";
	public static final String HM = "HH:mm";
	
	private CalendarUtils() {
		
	}
	
	public static String getCurrentDateString() {
		return getCurrentDateString(YMDHMA);
	}
	
	public static String getCurrentDateString(String pattern) {
		try {
			return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
		} catch (Exception e) {
			SFLog.loge(Constants.LOG_TAG_EXCEPTION, "format date:" + pattern);
		}
		return "";
	}

	public static long getSmallHourTimestamp() {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
	}
}
