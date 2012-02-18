package com.senseforce.everdoing.adapters;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senseforce.everdoing.R;
import com.senseforce.framework.utils.StringUtils;

public class DoingListAdapter extends BaseAdapter {

	public static String KEY_START_TIME = "job_start_time";
	public static String KEY_SUMMARY = "job_summary";
	public static String KEY_DETAIL = "job_detail";
	
	private List<? extends Map<String, ?>> mData = null;
	private LayoutInflater mInflater = null;
	
	public DoingListAdapter(Context context, List<? extends Map<String, ?>> data) {
		super();
		mData = data;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setListData(List<? extends Map<String, ?>> data) {
		mData = data;
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
            v = mInflater.inflate(R.layout.doing_list_item, parent, false);
        } else {
            v = convertView;
        }
		
		int inversePosition = mData.size()-1 - position;
		TextView timeTextView = (TextView)v.findViewById(R.id.job_start_time);
		if (timeTextView != null) {
			timeTextView.setText((String)(mData.get(inversePosition).get(KEY_START_TIME)));
		}
		TextView summaryTextView = (TextView)v.findViewById(R.id.job_summary);
		if (summaryTextView != null) {
			String text = (String)(mData.get(inversePosition).get(KEY_SUMMARY));
			summaryTextView.setText(text);
			if (StringUtils.isBlank(text)) {
				summaryTextView.setVisibility(View.GONE);
			}
			else {
				summaryTextView.setVisibility(View.VISIBLE);
			}
		}
		TextView detailTextView = (TextView)v.findViewById(R.id.job_detail);
		if (detailTextView != null) {
			String text = (String)(mData.get(inversePosition).get(KEY_DETAIL));
			detailTextView.setText(text);
			if (StringUtils.isBlank(text)) {
				detailTextView.setVisibility(View.GONE);
			}
			else {
				detailTextView.setVisibility(View.VISIBLE);
			}
		}
		
		return v;
	}

}
