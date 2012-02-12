package com.senseforce.everdoing.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.senseforce.everdoing.R;
import com.senseforce.framework.utils.CalendarUtils;
import com.senseforce.framework.utils.SFHelper;
import com.senseforce.framework.utils.StringUtils;

public class EditActivity extends Activity {
	
	private EditText mEditTextTime = null;
	private EditText mEditTextSummary = null;
	private EditText mEditTextDetail = null;
	private Button mButtonSave = null;
	private Button mButtonGiveup = null;

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
				saveRecord();
			}
		});
		mButtonGiveup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alertGivingUp();
			}
		});

	}
	
	private void saveRecord() {
		SFHelper.showToast(R.string.toast_saved);
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
	
	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			if (StringUtils.isBlank(mEditTextSummary.getText().toString())
					|| StringUtils.isBlank(mEditTextDetail.getText().toString())) {
				alertGivingUp();
				return true;
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
