package com.senseforce.everdoing.adapters;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.R;
import com.senseforce.everdoing.data.DataProvider;
import com.senseforce.framework.utils.StringUtils;

public class DoingListAdapter extends BaseAdapter {

	private LayoutInflater mInflater = null;
	
	public DoingListAdapter(Context context, List<? extends Map<String, ?>> data) {
		super();
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return DataProvider.instance.getTodayDataList() == null ? 0 : DataProvider.instance.getTodayDataList().size();
	}

	@Override
	public Object getItem(int position) {
		return DataProvider.instance.getTodayDataList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong((String)(DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_TIMESTAMP)));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
            v = mInflater.inflate(R.layout.doing_list_item, parent, false);
        } else {
            v = convertView;
        }
		
		TextView timeTextView = (TextView)v.findViewById(R.id.job_start_time);
		if (timeTextView != null) {
			timeTextView.setText((String)(DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_START_TIME)));
		}
		TextView summaryTextView = (TextView)v.findViewById(R.id.job_summary);
		if (summaryTextView != null) {
			String text = (String)(DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_SUMMARY));
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
			String text = (String)(DataProvider.instance.getTodayDataList().get(position).get(Constants.KEY_JOB_DETAIL));
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
