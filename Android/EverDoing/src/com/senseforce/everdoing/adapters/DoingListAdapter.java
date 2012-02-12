package com.senseforce.everdoing.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.EDApplication;
import com.senseforce.everdoing.R;
import com.senseforce.framework.utils.CalendarUtils;

public class DoingListAdapter extends BaseAdapter {

	private String KEY_TIME = "job_time";
	private String KEY_SUMMARY = "job_summary";
	private String KEY_DETAIL = "job_detail";

	private ArrayList<HashMap<String, String>> mDoingArrayList = null;

	public DoingListAdapter() {
		mDoingArrayList = new ArrayList<HashMap<String, String>>();
		for (int loop = 15; loop >= 0; loop --) {
		HashMap<String, String> aHashMap = new HashMap<String, String>();
		aHashMap.put(KEY_TIME, CalendarUtils.getCurrentDateString(CalendarUtils.HM));
		aHashMap.put(KEY_SUMMARY, "eating some bread");
		aHashMap.put(KEY_DETAIL, "again and again");
		mDoingArrayList.add(aHashMap);
		aHashMap = null;
		}
	}

	public int getCount() {
		return mDoingArrayList.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView listItemTextView = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(EDApplication.context);
			listItemTextView = (TextView) inflater.inflate(R.layout.doing_list_item, null);
			listItemTextView.setHeight((int)(32 * Constants.density));
		} else {
			listItemTextView = (TextView) convertView;
		}
		listItemTextView.setText(mDoingArrayList.get(position).get(KEY_TIME) + " ~ " + mDoingArrayList.get(position).get(KEY_SUMMARY));

		return listItemTextView;
	}

}
