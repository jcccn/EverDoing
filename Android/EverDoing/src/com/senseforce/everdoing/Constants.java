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
}
