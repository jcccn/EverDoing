package com.senseforce.framework.utils;

import android.util.Log;

public class SFLog {

	private static boolean isLoggable = false;

	public static final String TAG_SF = "SenseForce";
	public static final String TAG_ERROR = "ERROR";
	public static final String TAG_EXCEPTION = "EXCEPTION";
	public static final String TAG_NETWORK = "NETWORK";
	public static final String TAG_HARDWARE = "HARDWARE";

	public static final int VERBOSE = 2;
	public static final int DEBUG = 3;
	public static final int INFO = 4;
	public static final int WARN = 5;
	public static final int ERROR = 6;

	private SFLog() {

	}

	public static void setLoggable(boolean isLoggable) {
		SFLog.isLoggable = isLoggable;
	}
	
	public static boolean isLoggable() {
		return isLoggable;
	}

	public static void logv(String tag, String msg) {
		if (isLoggable() && tag != null && msg != null) {
			Log.v(tag, msg);
		}
	}

	public static void logd(String tag, String msg) {
		if (isLoggable() && tag != null && msg != null) {
			Log.d(tag, msg);
		}
	}

	public static void logi(String tag, String msg) {
		if (isLoggable() && tag != null && msg != null) {
			Log.i(tag, msg);
		}
	}

	public static void logw(String tag, String msg) {
		if (isLoggable() && tag != null && msg != null) {
			Log.w(tag, msg);
		}
	}

	public static void loge(String tag, String msg) {
		if (isLoggable() && tag != null && msg != null) {
			Log.e(tag, msg);
		}
	}

	public static void log(int priority, String tag, String format, Object... args) {
		if (isLoggable() && tag != null && format != null) {
			Log.println(priority, tag, String.format(format, args));
		}
	}
	
	public static void logThowable(Throwable e) {
		if (isLoggable() && e != null) {
			e.printStackTrace();
		}
	}

}
