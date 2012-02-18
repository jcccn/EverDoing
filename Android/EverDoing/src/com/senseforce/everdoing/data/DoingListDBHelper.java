package com.senseforce.everdoing.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoingListDBHelper extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "everdoing.db";
    private final static int DATABASE_VERSION = 1;
    public final static String TABLE_NAME = "doing_job";
    public final static String JOB_ID = "job_id";
    public final static String JOB_TIME = "job_time";
    public final static String JOB_SUMMARY = "job_summary";
    public final static String JOB_DETAIL = "job_detail";
    
    public final static String SELECT_ALL = "SELECT * FROM doing_job";

	public DoingListDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + JOB_ID
                + " INTEGER primary key autoincrement, " + JOB_TIME
                + " text, " + JOB_SUMMARY + " text, " + JOB_DETAIL + " text);";
        db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
	}
	
	public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }
	
    public long insert(String job_time, String job_summary, String job_detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(JOB_TIME, job_time);
        cv.put(JOB_SUMMARY, job_summary);
        cv.put(JOB_DETAIL, job_detail);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

}
