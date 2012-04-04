package com.senseforce.everdoing;


public class Constants {
	
	private Constants() {
		
	}
	
	public static final String LOG_TAG_EXCEPTION = "exception";
	
	public static final float density = EDApplication.context.getResources().getDisplayMetrics().density;
	
	public static final String INTENT_KEY_IS_JOB_NEW = "is_job_new";
	public static final String KEY_JOB_TIMESTAMP = "job_timestamp";
	public static final String KEY_JOB_START_TIME = "job_start_time";
	public static final String KEY_JOB_SUMMARY = "job_summary";
	public static final String KEY_JOB_DETAIL = "job_detail";
	
	public static final String KEY_REVIEW_TYPE = "review_type";
	public static final String KEY_REVIEW_TITLE = "review_title";
	public static final int REVIEW_TYPE_THIS_WEEK = 1;
	public static final int REVIEW_TYPE_LAST_WEEK = REVIEW_TYPE_THIS_WEEK + 1;
	public static final int REVIEW_TYPE_THIS_MONTH = REVIEW_TYPE_LAST_WEEK + 1;
	public static final int REVIEW_TYPE_LAST_MONTH = REVIEW_TYPE_THIS_MONTH + 1;
	
}
