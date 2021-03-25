package com.example.kotlinproject.ui.widget

//import com.example.kotlinproject.dataLayer.local.room.RoomRepositry.getData
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData.timezone
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData.current
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current.temp
//import com.example.kotlinproject.ui.GeneralFunctions.formateTime
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current.dt
//import com.example.kotlinproject.ui.GeneralFunctions.formateDate
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current.weather
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Weather.icon
//import com.example.kotlinproject.ui.widget.WeatherWidget
//import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.widget.RemoteViews
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.local.room.RoomRepositry
import android.app.Application
import com.example.kotlinproject.ui.GeneralFunctions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.Glide
import android.app.PendingIntent
import android.content.Context

/**
 * Implementation of App Widget functionality.
 */
class WeatherWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (MY_BUTTTON_START == intent.action) {
//            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            views = RemoteViews(context.packageName, R.layout.weather_widget)
            val roomRepositry = RoomRepositry((context.applicationContext as Application))
            val generalFunctions = GeneralFunctions()
            val current = roomRepositry.getData()[0]
            views!!.setTextViewText(R.id.currentCity, current.timezone)
            views!!.setTextViewText(R.id.currentTemp, "" + current.current.temp)
            views!!.setTextViewText(
                R.id.currentTime,
                generalFunctions.formateTime(current.current.dt)
            )
            views!!.setTextViewText(
                R.id.currentDate,
                generalFunctions.formateDate(current.current.dt)
            )
            val appWidgetTarget = AppWidgetTarget(context, R.id.currentModeImg, views, appWidgtId)
            Glide.with(context.applicationContext)
                .asBitmap()
                .load("https://openweathermap.org/img/w/" + current.current.weather[0].icon + ".png")
                .into(appWidgetTarget)
            appWidgetManage!!.updateAppWidget(appWidgtId, views)
        }
    }

    companion object {
        private const val MY_BUTTTON_START = "myButtonStart"
        private var views: RemoteViews? = null
        private var appWidgetManage: AppWidgetManager? = null
        private var appWidgtId = 0
        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            appWidgetManage = appWidgetManager
            appWidgtId = appWidgetId
            val widgetText: CharSequence = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            views = RemoteViews(context.packageName, R.layout.weather_widget)
            //        views.setTextViewText(R.id.appwidget_text, widgetText);
            val roomRepositry = RoomRepositry((context.applicationContext as Application))
            val generalFunctions = GeneralFunctions()
            val current = roomRepositry.getData()[0]
            views!!.setTextViewText(R.id.currentCity, current.timezone)
            views!!.setTextViewText(R.id.currentTemp, "" + current.current.temp)
            views!!.setTextViewText(
                R.id.currentTime,
                generalFunctions.formateTime(current.current.dt)
            )
            views!!.setTextViewText(
                R.id.currentDate,
                generalFunctions.formateDate(current.current.dt)
            )
            val appWidgetTarget = AppWidgetTarget(context, R.id.currentModeImg, views, appWidgetId)
            Glide.with(context.applicationContext)
                .asBitmap()
                .load("https://openweathermap.org/img/w/" + current.current.weather[0].icon + ".png")
                .into(appWidgetTarget)
            // Instruct the widget manager to update the widget
            val intent = Intent(context, WeatherWidget::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )
            views!!.setOnClickPendingIntent(
                R.id.reload,
                getPendingSelfIntent(context, MY_BUTTTON_START)
            )
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        protected fun getPendingSelfIntent(context: Context?, action: String?): PendingIntent {
            val intent = Intent(context, WeatherWidget::class.java)
            intent.action = action
            return PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }
}