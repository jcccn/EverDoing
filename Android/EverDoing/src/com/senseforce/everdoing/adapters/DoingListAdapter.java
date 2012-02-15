package com.senseforce.everdoing.adapters;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DoingListAdapter extends SimpleAdapter {

	public static String KEY_START_TIME = "job_start_time";
	public static String KEY_SUMMARY = "job_summary";
	public static String KEY_DETAIL = "job_detail";

	public DoingListAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		
	}

	public static class DoingViewBinder implements ViewBinder {

		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {
			if ((view instanceof TextView) && (data instanceof String)) {
				((TextView)view).setText((String)data);
			}
			return false;
		}
		
	}

}
