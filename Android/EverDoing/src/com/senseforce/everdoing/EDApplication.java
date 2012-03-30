package com.senseforce.everdoing;

import com.senseforce.everdoing.data.DataProvider;
import com.senseforce.framework.utils.SFLog;

import android.app.Application;
import android.content.Context;

public class EDApplication extends Application {
	
	public static Context context = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		context = this;
		SFLog.setLoggable(!("0".equals(EDApplication.context.getString(R.string.isLogOn))));
		
		DataProvider.instance.notifyDataChanged();
	}

}
