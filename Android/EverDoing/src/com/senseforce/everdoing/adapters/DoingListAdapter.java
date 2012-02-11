package com.senseforce.everdoing.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senseforce.everdoing.EDApplication;
import com.senseforce.everdoing.R;

public class DoingListAdapter extends BaseAdapter {

	private String KEY_TIME = "doing_time";
	private String KEY_WHAT = "doing_what";

	private ArrayList<HashMap<String, String>> mDoingArrayList = null;

	public DoingListAdapter() {
		mDoingArrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> aHashMap = new HashMap<String, String>();
		aHashMap.put(KEY_TIME, "2011-2-11-23:00");
		aHashMap.put(KEY_WHAT, "eating some bread");
		mDoingArrayList.add(aHashMap);
		aHashMap = null;
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
		} else {
			listItemTextView = (TextView) convertView;
		}
		listItemTextView.setText(mDoingArrayList.get(position).get(KEY_WHAT));

		return listItemTextView;
	}

}
