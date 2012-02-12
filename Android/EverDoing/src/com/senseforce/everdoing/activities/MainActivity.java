package com.senseforce.everdoing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.R;
import com.senseforce.everdoing.adapters.DoingListAdapter;
import com.senseforce.framework.ui.SFPage;
import com.senseforce.framework.ui.TitleBar;

public class MainActivity extends SFPage {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        mDoingListView = (ListView)findViewById(R.id.widget_doing_list);
        mDoingListAdapter = new DoingListAdapter();
        mDoingListView.setAdapter(mDoingListAdapter);
        
        TitleBar titleBar = (TitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle(R.string.app_name);
        Button buttonNew = new Button(this);
        buttonNew.setText("NEW");
        buttonNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, EditActivity.class);
				startActivity(intent);
			}
		});
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(80 *Constants.density),
        												(int)(50 * Constants.density));
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        titleBar.setRightView(buttonNew, params);
    }
    
    @Override
    public void onDestroy() {
    	mDoingListAdapter = null;
    	mDoingListView = null;
    	super.onDestroy();
    }
}