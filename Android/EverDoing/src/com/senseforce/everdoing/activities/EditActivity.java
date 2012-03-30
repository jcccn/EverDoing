package com.senseforce.everdoing.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.EDApplication;
import com.senseforce.everdoing.R;
import com.senseforce.everdoing.data.DoingListDBHelper;
import com.senseforce.framework.utils.CalendarUtils;
import com.senseforce.framework.utils.SFHelper;
import com.senseforce.framework.utils.StringUtils;

public class EditActivity extends Activity {
	
	private EditText mEditTextTime = null;
	private EditText mEditTextSummary = null;
	private EditText mEditTextDetail = null;
	private Button mButtonSave = null;
	private Button mButtonGiveup = null;
	private long mTimestamp = 0;
	private boolean isNewJob = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);
		
		mEditTextTime = (EditText)findViewById(R.id.edit_time_edit_text);
		mEditTextSummary = (EditText)findViewById(R.id.edit_summary_edit_text);
		mEditTextDetail = (EditText)findViewById(R.id.edit_detail_edit);
		mButtonSave = (Button)findViewById(R.id.edit_button_save);
		mButtonGiveup = (Button)findViewById(R.id.edit_button_giveup);
		
		mEditTextTime.setText(CalendarUtils.getCurrentDateString(CalendarUtils.HM));
		mButtonSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hideSoftKeyboard();
				if (isNewJob) {
					saveRecord();
					finish();
				}
				else {
					alertEditing();
				}
			}
		});
		mButtonGiveup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hideSoftKeyboard();
				if (needSave()) {
					alertGivingUp();
				}
				else {
					finish();
				}
			}
		});
		
		Intent receivedIntent = getIntent();
		isNewJob = receivedIntent.getBooleanExtra(Constants.INTENT_KEY_IS_JOB_NEW, true);
		if ( ! isNewJob)  {
			mTimestamp = receivedIntent.getLongExtra(Constants.KEY_JOB_TIMESTAMP, 0);
			mEditTextTime.setText(receivedIntent.getStringExtra(Constants.KEY_JOB_START_TIME));
			mEditTextSummary.setText(receivedIntent.getStringExtra(Constants.KEY_JOB_SUMMARY));
			mEditTextDetail.setText(receivedIntent.getStringExtra(Constants.KEY_JOB_DETAIL));
		}
		else {
			mTimestamp = Calendar.getInstance().getTimeInMillis();
		}

		
		mEditTextSummary.requestFocus();
	}
	
	private boolean needSave() {
		return ! (StringUtils.isBlank(mEditTextSummary.getText().toString())
				&& StringUtils.isBlank(mEditTextDetail.getText().toString()));
	}
	
	private void saveRecord() {
		if (needSave()) {
			if (StringUtils.isBlank(mEditTextTime.getText().toString())) {
				mEditTextTime.setText(CalendarUtils.getCurrentDateString(CalendarUtils.HM));
			}
			saveRecord(mEditTextTime.getText().toString(),
					mEditTextSummary.getText().toString(),
					mEditTextDetail.getText().toString());
		}
		else {
			SFHelper.showToast(R.string.toast_nothing_saved);
		}
	}
	
	private void saveRecord(String time, String summary, String detail) {
		SFHelper.showToast(R.string.toast_saved);
     
        DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
        SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(Constants.KEY_JOB_TIMESTAMP, mTimestamp);
        cv.put(Constants.KEY_JOB_START_TIME, time);
        cv.put(Constants.KEY_JOB_SUMMARY, summary);
        cv.put(Constants.KEY_JOB_DETAIL, detail);
        if (isNewJob) {
        	job_db.insert(DoingListDBHelper.TABLE_NAME, null, cv);
        }
        else {
        	job_db.update(DoingListDBHelper.TABLE_NAME, cv, Constants.KEY_JOB_TIMESTAMP+"="+mTimestamp, null);
        }
        job_db.close();
        dbhelper.close();
	}
	
	private void alertGivingUp() {
		AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(this);
		alertDialogBuiler.setMessage(R.string.dialog_title_giveup);
		alertDialogBuiler.setPositiveButton(R.string.button_title_yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
			
		});
		alertDialogBuiler.setNegativeButton(R.string.button_title_no, null);
		alertDialogBuiler.show();
		alertDialogBuiler = null;
	}
	
	private void alertEditing() {
		AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(this);
		alertDialogBuiler.setMessage(R.string.dialog_title_edit_commit);
		alertDialogBuiler.setPositiveButton(R.string.button_title_yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				saveRecord();
				finish();
			}
			
		});
		alertDialogBuiler.setNegativeButton(R.string.button_title_no, null);
		alertDialogBuiler.show();
		alertDialogBuiler = null;
	}
	
	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		View view = this.getCurrentFocus();
		if (view != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			if (needSave()) {
				if (isNewJob) {
					saveRecord();
				}
				else {
					alertEditing();
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDestroy() {
		
		mEditTextTime = null;
		mEditTextSummary = null;
		mEditTextDetail = null;
		mButtonSave = null;
		mButtonGiveup = null;
		
		super.onDestroy();
	}
}
