package com.senseforce.everdoing.services;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.senseforce.everdoing.R;
import com.senseforce.everdoing.activities.EditActivity;
import com.senseforce.everdoing.widgets.EDWidgetProvider;
import com.senseforce.framework.utils.CalendarUtils;

public class WidgetUpdateService extends Service {
	boolean is_run = false;
	boolean flag = true;

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
						updateViews.setTextViewText(R.id.widget_title, CalendarUtils.getCurrentDateString());
						updateViews.setTextViewText(R.id.widget_item_0, "get up");
						updateViews.setTextViewText(R.id.widget_item_1, "have breakfast");
						updateViews.setTextViewText(R.id.widget_item_2, "take a nap");
						updateViews.setTextViewText(R.id.widget_item_3, "have lunch");
						updateViews.setTextViewText(R.id.widget_item_4, "take a nap");
						updateViews.setTextViewText(R.id.widget_item_5, "have dinner");
						updateViews.setTextViewText(R.id.widget_item_6, "go to bed");
						
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

	@Override
	public void onDestroy() {
		flag = false;
		super.onDestroy();
	}

}