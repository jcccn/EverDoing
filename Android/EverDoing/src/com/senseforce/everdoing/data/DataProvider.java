package com.senseforce.everdoing.data;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.EDApplication;

public enum DataProvider {
	instance;
	
	private static ArrayList<HashMap<String, Object>> today_doing_list = new ArrayList<HashMap<String, Object>>();
	
	public ArrayList<HashMap<String, Object>> getTodayDataList() {
		return today_doing_list;
	}
	
	private void reloadTodayDataList() {
		DoingListDBHelper dbhelper = new DoingListDBHelper(EDApplication.context);
		SQLiteDatabase job_db = dbhelper.getWritableDatabase();
		Cursor cursor = job_db.rawQuery(DoingListDBHelper.getSQL_SELECT_TODAY(), null);
		today_doing_list.clear();
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
					today_doing_list.add(aHashMap);

				} while (cursor.moveToPrevious());
			}
			
			cursor.close();
		}
		job_db.close();
		dbhelper.close();
	}
	
	public void notifyDataChanged() {
		reloadTodayDataList();
	}
}
