package com.senseforce.everdoing.activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.EDApplication;
import com.senseforce.everdoing.R;
import com.senseforce.everdoing.adapters.DoingListAdapter;
import com.senseforce.everdoing.data.DoingListDBHelper;
import com.senseforce.framework.ui.SFPage;
import com.senseforce.framework.ui.TitleBar;

public class MainActivity extends SFPage {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        mDoingListView = (ListView)findViewById(R.id.doing_list);
        mDoingListAdapter = new DoingListAdapter(this, null);
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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(68 *Constants.density),
        												(int)(40 * Constants.density));
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        titleBar.setRightView(buttonNew, params);
    }
    
    
    
    @Override
	protected void onResume() {
    	mDoingListAdapter.setListData(getDataList());
    	mDoingListAdapter.notifyDataSetChanged();
		super.onResume();
	}



	private ArrayList<HashMap<String, Object>> getDataList() {
    	ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
		SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		Cursor cursor = job_db.rawQuery(DoingListDBHelper.SELECT_ALL, null);
		if (cursor != null) {
			int numColumn_time = cursor.getColumnIndex(DoingListDBHelper.JOB_TIME);
			int numColumn_summary = cursor.getColumnIndex(DoingListDBHelper.JOB_SUMMARY);
			int numColumn_detail = cursor.getColumnIndex(DoingListDBHelper.JOB_DETAIL);

			if (cursor.moveToFirst()) {
				do {
					String time = cursor.getString(numColumn_time);
					String summary = cursor.getString(numColumn_summary);
					String detail = cursor.getString(numColumn_detail);

					HashMap<String, Object> aHashMap = new HashMap<String, Object>();
					aHashMap.put(DoingListAdapter.KEY_START_TIME, time);
					aHashMap.put(DoingListAdapter.KEY_SUMMARY, summary);
					aHashMap.put(DoingListAdapter.KEY_DETAIL, detail);
					dataList.add(aHashMap);

				} while (cursor.moveToNext());
			}
			
			cursor.close();
		}
		job_db.close();
		dbhelper.close();
    	
    	return dataList;
    }
    
    @Override
    public void onDestroy() {
    	mDoingListAdapter = null;
    	mDoingListView = null;
    	super.onDestroy();
    }
}