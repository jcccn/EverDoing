package com.senseforce.framework.ui;

import android.app.Activity;

import com.senseforce.framework.foundation.IReleasable;

public class SFPage extends Activity implements IReleasable {
	
	public void init() {
		
	}

	protected void pageDidDisappear() {
		
	}

	@Override
	public void onDestroy() {
		release();
		super.onDestroy();
	}
	
	@Override
	public void release() {
		
	}
}
