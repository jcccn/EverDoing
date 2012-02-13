package com.senseforce.framework.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class SFView extends ViewGroup {

	public SFView(Context context) {
        super(context);
        init();
    }

    public SFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SFView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    public void init() {
    	//TODO initial method
    	
    }

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		
	}

}
