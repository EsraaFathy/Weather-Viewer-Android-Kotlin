package com.example.kotlinproject.ui.createAlerm

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.d
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.local.room.RoomRepositry
import com.example.kotlinproject.ui.NotificationHelper

class AlermRecever() : BroadcastReceiver() {
    lateinit var notificationHelper: NotificationHelper


    override fun onReceive(context: Context?, intent: Intent?) {
        notificationHelper = NotificationHelper(context!!)
        val roomRepositry= RoomRepositry(context = context.applicationContext as Application)
        val current = roomRepositry.getData()[0].current
        val actualType = current.weather[0].description
        d("TAG", "Receved")
        if (intent != null) {

            val type = intent.getStringExtra("TYPE")
            if (actualType.contains(type!!) ) {
                val notificationBuilder = notificationHelper.getChanelNotification(
                    context.getString(R.string.weather_alert),
                    context.getString(R.string.take_care) + type
                )
                notificationHelper.getManger()!!.notify(1, notificationBuilder.build())
            }
            d("TAG", "Receved  $type")

        }
    }
}


