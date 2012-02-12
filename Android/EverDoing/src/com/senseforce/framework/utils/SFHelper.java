package com.senseforce.framework.utils;

import com.senseforce.everdoing.EDApplication;

import android.widget.Toast;

public class SFHelper {
	
	private static final Toast mToast = Toast.makeText(EDApplication.context, "", Toast.LENGTH_SHORT);
	
	private SFHelper() {
		
	}
	
	public static void showToast(int resId) {
		mToast.cancel();
		mToast.setText(resId);
		mToast.show();
	}
	
	public static void showToast(CharSequence s) {
		mToast.cancel();
		mToast.setText(s);
		mToast.show();
	}
	
}
