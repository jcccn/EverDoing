package com.senseforce.everdoing.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.senseforce.everdoing.R;
import com.senseforce.everdoing.activities.MainActivity;
import com.senseforce.everdoing.widgets.EDWidgetProvider;

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
						Calendar calendar = Calendar.getInstance();
						Date date = calendar.getTime();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
						updateViews.setTextViewText(R.id.widget_title, formatter.format(date));
						date = null;
						formatter = null;
						calendar = null;
						
						updateViews.setTextViewText(R.id.widget_item_0, "get up");
						updateViews.setTextViewText(R.id.widget_item_1, "have breakfast");
						updateViews.setTextViewText(R.id.widget_item_2, "take a nap");
						updateViews.setTextViewText(R.id.widget_item_3, "have lunch");
						updateViews.setTextViewText(R.id.widget_item_4, "take a nap");
						updateViews.setTextViewText(R.id.widget_item_5, "have dinner");
						updateViews.setTextViewText(R.id.widget_item_6, "go to bed");
						
						Intent intent = new Intent(WidgetUpdateService.this, MainActivity.class);
						PendingIntent pendingintent = PendingIntent.getActivity(WidgetUpdateService.this, 0, intent, 0);
						updateViews.setOnClickPendingIntent(R.id.widget_title, pendingintent);
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