package com.senseforce.framework.utils;

public class StringUtils {
	private StringUtils() {
		
	}
	
	public static boolean isEmpty(String string) {
		return (string != null) && (string.length() != 0);
	}
	
	public static boolean isBlank(String string) {
		return StringUtils.isEmpty(string) || (string.trim().length() != 0);
	}
}
