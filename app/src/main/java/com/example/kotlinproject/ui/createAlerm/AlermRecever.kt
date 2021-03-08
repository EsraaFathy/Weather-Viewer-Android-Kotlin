package com.example.kotlinproject.ui.createAlerm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.d

class AlermRecever: BroadcastReceiver() {
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationHelper= NotificationHelper(context!!)

        d("TAG","Receved")
        val notificationBuilder=notificationHelper.getChanelNotification("Alert","There is some Weather changes")
        notificationHelper.getManger()!!.notify(1,notificationBuilder.build())
    }
}