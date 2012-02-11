package com.senseforce.everdoing;

import android.app.Application;
import android.content.Context;

public class EDApplication extends Application {
	
	public static Context context = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		context = this;
	}

}
