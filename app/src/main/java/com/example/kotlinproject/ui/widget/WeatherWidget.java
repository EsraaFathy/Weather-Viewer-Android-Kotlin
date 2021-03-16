package com.example.kotlinproject.ui.widget;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.example.kotlinproject.R;
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData;
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current;
import com.example.kotlinproject.dataLayer.local.room.RoomRepositry;
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingSB;
import com.example.kotlinproject.ui.GeneralFunctions;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        RoomRepositry roomRepositry=new RoomRepositry((Application) context.getApplicationContext());
        GeneralFunctions generalFunctions=new GeneralFunctions();
        AllData current=roomRepositry.getData().get(0);
        views.setTextViewText(R.id.currentCity,current.getTimezone());
        views.setTextViewText(R.id.currentTemp,""+current.getCurrent().getTemp());
        views.setTextViewText(R.id.currentTime,generalFunctions.formateTime(current.getCurrent().getDt()));
        views.setTextViewText(R.id.currentDate,generalFunctions.formateDate(current.getCurrent().getDt()));
//        generalFunctions.loadImage();

        AppWidgetTarget appWidgetTarget = new AppWidgetTarget( context , R.id.currentModeImg, views,appWidgetId );

        Glide.with(context.getApplicationContext())
                .asBitmap()
                .load("https://openweathermap.org/img/w/"+current.getCurrent().getWeather().get(0).getIcon()+".png")
                .into(appWidgetTarget);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}