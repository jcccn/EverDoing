package com.senseforce.everdoing.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.senseforce.everdoing.Constants;

public class DoingListDBHelper extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "everdoing.db";
    private final static int DATABASE_VERSION = 1;
    public final static String TABLE_NAME = "doing_job";
    
    public final static String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;

	public DoingListDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + Constants.KEY_JOB_TIMESTAMP
                + " INTEGER primary key autoincrement, " + Constants.KEY_JOB_START_TIME
                + " text, " + Constants.KEY_JOB_SUMMARY + " text, " + Constants.KEY_JOB_DETAIL + " text);";
        db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
	}

}
