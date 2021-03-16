package com.example.kotlinproject.ui.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

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
    private static final String MY_BUTTTON_START = "myButtonStart";
    private static RemoteViews views;
    private static AppWidgetManager appWidgetManage;
    private static int appWidgtId;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
       appWidgetManage=appWidgetManager;
        appWidgtId=appWidgetId;
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        RoomRepositry roomRepositry=new RoomRepositry((Application) context.getApplicationContext());
        GeneralFunctions generalFunctions=new GeneralFunctions();
        AllData current=roomRepositry.getData().get(0);
        views.setTextViewText(R.id.currentCity,current.getTimezone());
        views.setTextViewText(R.id.currentTemp,""+current.getCurrent().getTemp());
        views.setTextViewText(R.id.currentTime,generalFunctions.formateTime(current.getCurrent().getDt()));
        views.setTextViewText(R.id.currentDate,generalFunctions.formateDate(current.getCurrent().getDt()));
        AppWidgetTarget appWidgetTarget = new AppWidgetTarget( context , R.id.currentModeImg, views,appWidgetId );

        Glide.with(context.getApplicationContext())
                .asBitmap()
                .load("https://openweathermap.org/img/w/"+current.getCurrent().getWeather().get(0).getIcon()+".png")
                .into(appWidgetTarget);
        // Instruct the widget manager to update the widget
        Intent intent = new Intent(context, WeatherWidget.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.reload, getPendingSelfIntent(context, MY_BUTTTON_START));
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

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (MY_BUTTTON_START.equals(intent.getAction())){
//            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
            RoomRepositry roomRepositry=new RoomRepositry((Application) context.getApplicationContext());
            GeneralFunctions generalFunctions=new GeneralFunctions();
            AllData current=roomRepositry.getData().get(0);
            views.setTextViewText(R.id.currentCity,current.getTimezone());
            views.setTextViewText(R.id.currentTemp,""+current.getCurrent().getTemp());
            views.setTextViewText(R.id.currentTime,generalFunctions.formateTime(current.getCurrent().getDt()));
            views.setTextViewText(R.id.currentDate,generalFunctions.formateDate(current.getCurrent().getDt()));
            AppWidgetTarget appWidgetTarget = new AppWidgetTarget( context , R.id.currentModeImg, views,appWidgtId );

            Glide.with(context.getApplicationContext())
                    .asBitmap()
                    .load("https://openweathermap.org/img/w/"+current.getCurrent().getWeather().get(0).getIcon()+".png")
                    .into(appWidgetTarget);

            appWidgetManage.updateAppWidget(appWidgtId, views);

        }
    }

    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, WeatherWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}