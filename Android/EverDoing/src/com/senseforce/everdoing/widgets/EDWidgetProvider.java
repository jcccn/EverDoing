package com.senseforce.everdoing.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.senseforce.everdoing.services.WidgetUpdateService;

public class EDWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Intent intent = new Intent(context, WidgetUpdateService.class);
		context.startService(intent);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Intent intent = new Intent(context, WidgetUpdateService.class);
		context.stopService(intent);
		super.onDeleted(context, appWidgetIds);
	}

}
