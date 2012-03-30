package com.senseforce.everdoing.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.senseforce.everdoing.data.DataProvider;
import com.senseforce.everdoing.data.DoingListDBHelper;
import com.senseforce.framework.ui.SFPage;
import com.senseforce.framework.ui.TitleBar;
import com.senseforce.framework.utils.SFHelper;

public class MainActivity extends SFPage implements OnItemLongClickListener, OnItemClickListener {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;	
	
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
        titleBar.setTitle(R.string.bar_title_main);
        
        Button buttonHome = new Button(this);
        buttonHome.setText(R.string.button_title_home);
        buttonHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(68 *Constants.density),
        												(int)(40 * Constants.density));
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        titleBar.setLeftView(buttonHome, params);
        
        Button buttonNew = new Button(this);
        buttonNew.setText(R.string.button_title_new);
        buttonNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, EditActivity.class);
				startActivity(intent);
			}
		});
        params = new RelativeLayout.LayoutParams((int)(68 *Constants.density), (int)(40 * Constants.density));
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        titleBar.setRightView(buttonNew, params);
    }
    
    
    
    @Override
	protected void onResume() {
    	mDoingListAdapter.notifyDataSetChanged();
		super.onResume();
	}

	private void deleteRecord(int position, long id) {
		SFHelper.showToast(R.string.toast_delete);
        DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
        SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		job_db.delete(DoingListDBHelper.TABLE_NAME, Constants.KEY_JOB_TIMESTAMP+"="+id, null);
        job_db.close();
        dbhelper.close();
        
        DataProvider.instance.notifyDataChanged();
        
        mDoingListAdapter.notifyDataSetChanged();
	}
    
    @Override
    public void onDestroy() {
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
		intent.putExtra(Constants.KEY_JOB_START_TIME, (String)DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_START_TIME));
		intent.putExtra(Constants.KEY_JOB_SUMMARY, (String)DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_SUMMARY));
		intent.putExtra(Constants.KEY_JOB_DETAIL, (String)DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_DETAIL));
		startActivity(intent);
		
	}
}