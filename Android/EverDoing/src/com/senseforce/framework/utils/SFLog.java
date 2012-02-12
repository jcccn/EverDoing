package com.senseforce.framework.utils;

import android.util.Log;

import com.senseforce.everdoing.EDApplication;
import com.senseforce.everdoing.R;

public class SFLog {
	
	private static final boolean isLogOn = !("0".equals(EDApplication.context.getString(R.string.isLogOn)));
	
	private SFLog() {
		
	}
	
	public static void logv(String tag, String msg) {
		if (isLogOn) {
			Log.v(tag, msg);
		}
	}
	
	public static void logd(String tag, String msg) {
		if (isLogOn) {
			Log.d(tag, msg);
		}
	}
	
	public static void logi(String tag, String msg) {
		if (isLogOn) {
			Log.i(tag, msg);
		}
	}
	
	public static void logw(String tag, String msg) {
		if (isLogOn) {
			Log.w(tag, msg);
		}
	}
	
	public static void loge(String tag, String msg) {
		if (isLogOn) {
			Log.e(tag, msg);
		}
	}
	
}
