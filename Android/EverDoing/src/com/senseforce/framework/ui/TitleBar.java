package com.senseforce.framework.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.senseforce.everdoing.R;

public class TitleBar extends RelativeLayout {
	
	public TitleBar(Context context) {
		super(context);
		init();
	}
	
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		
	}
	
	public String getTitle() {
		TextView titleTextView = (TextView)findViewById(R.id.title_text_view);
		if (titleTextView == null) {
			return "";
		}
		return titleTextView.getText().toString();
	}
	
	public void setTitle(String title) {
		TextView titleTextView = (TextView)findViewById(R.id.title_text_view);
		if (titleTextView != null) {
			titleTextView.setText(title);
		}
	}
	
	public void setTitle(int resId) {
		TextView titleTextView = (TextView)findViewById(R.id.title_text_view);
		if (titleTextView != null) {
			titleTextView.setText(resId);
		}
	}
	
	public void setLeftView(View view, RelativeLayout.LayoutParams params) {
		RelativeLayout leftRootView = (RelativeLayout)findViewById(R.id.title_left_view);
		leftRootView.removeAllViews();
		if (view == null) {
			leftRootView.setVisibility(View.GONE);
			return;
		}
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(params);
		leftRootView.addView(view, layoutParams);
		leftRootView.setVisibility(View.VISIBLE);
	}
	
	public void setRightView(View view, RelativeLayout.LayoutParams params) {
		RelativeLayout rightRootView = (RelativeLayout)findViewById(R.id.title_right_view);
		rightRootView.removeAllViews();
		if (view == null) {
			rightRootView.setVisibility(View.GONE);
			return;
		}
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(params);
		rightRootView.addView(view, layoutParams);
		rightRootView.setVisibility(View.VISIBLE);
	}

}
