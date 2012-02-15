package com.senseforce.everdoing.activities;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.senseforce.framework.utils.CalendarUtils;

public class MainActivity extends SFPage {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        mDoingListView = (ListView)findViewById(R.id.doing_list);
        mDoingListAdapter = new DoingListAdapter(this, getDataList(), R.layout.doing_list_item, 
        		new String[] {DoingListAdapter.KEY_START_TIME, DoingListAdapter.KEY_SUMMARY, DoingListAdapter.KEY_DETAIL},
        		new int[]{R.id.job_start_time, R.id.job_summary, R.id.job_detail});
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
    
    private ArrayList<HashMap<String, Object>> getDataList() {
    	ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
    	for (int loop = 15; loop >= 0; loop--) {
			HashMap<String, Object> aHashMap = new HashMap<String, Object>();
			aHashMap.put(DoingListAdapter.KEY_START_TIME, CalendarUtils.getCurrentDateString(CalendarUtils.HM));
			aHashMap.put(DoingListAdapter.KEY_SUMMARY, "eating some bread");
			aHashMap.put(DoingListAdapter.KEY_DETAIL, "again and again");
			dataList.add(aHashMap);
			aHashMap = null;
		}
    	return dataList;
    }
    
    @Override
    public void onDestroy() {
    	mDoingListAdapter = null;
    	mDoingListView = null;
    	super.onDestroy();
    }
}