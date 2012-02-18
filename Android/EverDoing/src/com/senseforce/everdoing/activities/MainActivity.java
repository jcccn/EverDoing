package com.senseforce.everdoing.activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
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
import com.senseforce.framework.utils.SFHelper;

public class MainActivity extends SFPage implements OnItemLongClickListener, OnItemClickListener {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;	
	private ArrayList<HashMap<String, Object>> mDoingArrayList = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        mDoingListView = (ListView)findViewById(R.id.doing_list);
        mDoingListAdapter = new DoingListAdapter(this, null);
        mDoingListView.setAdapter(mDoingListAdapter);
        mDoingListView.setOnItemLongClickListener(this);
        mDoingListView.setOnItemClickListener(this);
        
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
    	mDoingArrayList = getDataList();
    	mDoingListAdapter.setListData(mDoingArrayList);
    	mDoingListAdapter.notifyDataSetChanged();
		super.onResume();
	}



	private ArrayList<HashMap<String, Object>> getDataList() {
    	ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
		SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		Cursor cursor = job_db.rawQuery(DoingListDBHelper.SELECT_ALL, null);
		if (cursor != null) {
			int numColumn_timestamp = cursor.getColumnIndex(Constants.KEY_JOB_TIMESTAMP);
			int numColumn_time = cursor.getColumnIndex(Constants.KEY_JOB_START_TIME);
			int numColumn_summary = cursor.getColumnIndex(Constants.KEY_JOB_SUMMARY);
			int numColumn_detail = cursor.getColumnIndex(Constants.KEY_JOB_DETAIL);

			if (cursor.moveToLast()) {
				do {
					String timestamp = String.valueOf(cursor.getLong(numColumn_timestamp));
					String time = cursor.getString(numColumn_time);
					String summary = cursor.getString(numColumn_summary);
					String detail = cursor.getString(numColumn_detail);

					HashMap<String, Object> aHashMap = new HashMap<String, Object>();
					aHashMap.put(Constants.KEY_JOB_TIMESTAMP, timestamp);
					aHashMap.put(Constants.KEY_JOB_START_TIME, time);
					aHashMap.put(Constants.KEY_JOB_SUMMARY, summary);
					aHashMap.put(Constants.KEY_JOB_DETAIL, detail);
					dataList.add(aHashMap);

				} while (cursor.moveToPrevious());
			}
			
			cursor.close();
		}
		job_db.close();
		dbhelper.close();
    	
    	return dataList;
    }
	
	private void deleteRecord(int position, long id) {
		SFHelper.showToast(R.string.toast_delete);
        DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
        SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		job_db.delete(DoingListDBHelper.TABLE_NAME, Constants.KEY_JOB_TIMESTAMP+"="+id, null);
        job_db.close();
        dbhelper.close();
        
        mDoingArrayList.remove(position);
        mDoingListAdapter.notifyDataSetChanged();
	}
    
    @Override
    public void onDestroy() {
    	mDoingArrayList.clear();
    	mDoingArrayList = null;
    	mDoingListAdapter = null;
    	mDoingListView = null;
    	super.onDestroy();
    }



	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, final long id) {
		AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(this);
		alertDialogBuiler.setMessage(R.string.dialog_title_delete_item);
		alertDialogBuiler.setPositiveButton(R.string.button_title_yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteRecord(position, id);
			}
			
		});
		alertDialogBuiler.setNegativeButton(R.string.button_title_no, null);
		alertDialogBuiler.show();
		alertDialogBuiler = null;
		return false;
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, EditActivity.class);
		intent.putExtra(Constants.INTENT_KEY_IS_JOB_NEW, false);
		intent.putExtra(Constants.KEY_JOB_TIMESTAMP, id);
		intent.putExtra(Constants.KEY_JOB_START_TIME, (String)mDoingArrayList.get(position).get(Constants.KEY_JOB_START_TIME));
		intent.putExtra(Constants.KEY_JOB_SUMMARY, (String)mDoingArrayList.get(position).get(Constants.KEY_JOB_SUMMARY));
		intent.putExtra(Constants.KEY_JOB_DETAIL, (String)mDoingArrayList.get(position).get(Constants.KEY_JOB_DETAIL));
		startActivity(intent);
		
	}
}