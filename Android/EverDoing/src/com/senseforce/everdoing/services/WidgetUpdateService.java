package com.senseforce.everdoing.services;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.R;
import com.senseforce.everdoing.activities.EditActivity;
import com.senseforce.everdoing.data.DataProvider;
import com.senseforce.everdoing.widgets.EDWidgetProvider;
import com.senseforce.framework.utils.CalendarUtils;
import com.senseforce.framework.utils.StringUtils;

public class WidgetUpdateService extends Service {
	private boolean is_run = false;
	private boolean flag = true;
	private int[] textviewResIds = {R.id.widget_item_0, R.id.widget_item_1, R.id.widget_item_2,
			R.id.widget_item_3, R.id.widget_item_4, R.id.widget_item_5, R.id.widget_item_6};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		final RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.widget);
		final ComponentName thisWidget = new ComponentName(this,
				EDWidgetProvider.class);
		final AppWidgetManager manager = AppWidgetManager.getInstance(this);

		if (!is_run) {
			new Thread() {
				public void run() {
					is_run = true;
					while (flag) {
						
						ArrayList<String> doingStrings = getDataList(7);
						int doingCount = doingStrings.size();
						int textviewCount = textviewResIds.length;
						if (doingCount >= textviewCount) {
							for (int i = 0; i < textviewCount; i++) {
								updateViews.setTextViewText(textviewResIds[i], doingStrings.get(i));
								updateViews.setViewVisibility(textviewResIds[i], View.VISIBLE);
							}
						}
						else {
							for (int i = 0; i < doingCount; i++) {
								updateViews.setTextViewText(textviewResIds[i], doingStrings.get(i));
								updateViews.setViewVisibility(textviewResIds[i], View.VISIBLE);
							}
							
							for (int i = doingCount; i < textviewCount; i++) {
								updateViews.setTextViewText(textviewResIds[i], "");
								updateViews.setViewVisibility(textviewResIds[i], View.INVISIBLE);
							}
						}
						
						updateViews.setTextViewText(R.id.widget_title, CalendarUtils.getCurrentDateString());
						
						Intent intent = new Intent(WidgetUpdateService.this, EditActivity.class);
						PendingIntent pendingintent = PendingIntent.getActivity(WidgetUpdateService.this, 0, intent, 0);
						updateViews.setOnClickPendingIntent(R.id.widget_layout, pendingintent);
						manager.updateAppWidget(thisWidget, updateViews);
						try {
							sleep(60000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}

	}
	
	private ArrayList<String> getDataList(int size) {
		ArrayList<String> dataList = new ArrayList<String>();
		ArrayList<HashMap<String, Object>> today_doing_list = DataProvider.instance.getTodayDataList();
		for(int position = 0, maxSize = today_doing_list.size(); position < size && position < maxSize; position ++) {
			String doingString = "";
			String summary = (String)today_doing_list.get(position).get(Constants.KEY_JOB_SUMMARY);
			if ( ! StringUtils.isBlank(summary)) {
				doingString = summary;
			}
			else {
				doingString = (String)today_doing_list.get(position).get(Constants.KEY_JOB_DETAIL);
			}

			dataList.add(doingString);
		}
    	
    	return dataList;
    }

	@Override
	public void onDestroy() {
		flag = false;
		super.onDestroy();
	}

}