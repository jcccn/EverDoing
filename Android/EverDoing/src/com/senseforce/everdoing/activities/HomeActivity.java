package com.senseforce.everdoing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.R;
import com.senseforce.framework.ui.SFPage;
import com.senseforce.framework.ui.TitleBar;

public class HomeActivity extends SFPage implements OnClickListener {

	private Button buttonNew = null;
	private TextView textViewToday = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		TitleBar titleBar = (TitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle(R.string.bar_title_home);
        buttonNew = new Button(this);
        buttonNew.setText(R.string.button_title_new);
        buttonNew.setOnClickListener(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(68 *Constants.density), (int)(40 * Constants.density));
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        titleBar.setRightView(buttonNew, params);
        
        textViewToday = (TextView)findViewById(R.id.home_item_today);
        
        textViewToday.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.home_item_today) {
			Intent intent = new Intent();
			intent.setClass(HomeActivity.this, MainActivity.class);
			startActivity(intent);
		}
		else if(v == buttonNew) {
			Intent intent = new Intent();
			intent.setClass(HomeActivity.this, EditActivity.class);
			startActivity(intent);
		}
		
	}
}
