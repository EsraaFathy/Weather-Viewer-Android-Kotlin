package com.example.kotlinproject.ui.alert

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper

import androidx.core.app.NotificationCompat
import com.example.kotlinproject.R

class NotificationHelper(context: Context) : ContextWrapper(context){
        private val channelID: String = "channelID"
        private val channelName: String = "channelName"
        private var notificationManager: NotificationManager? = null

    init {
        createChanel()
    }

       private fun createChanel() {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    channelID,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationChannel.enableLights(true)
                notificationChannel.enableVibration(true)
                notificationChannel.lightColor = R.color.text_color
                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                getManger()!!.createNotificationChannel(notificationChannel)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }

        fun getManger(): NotificationManager? {
            if (notificationManager == null) {
                notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            }
            return notificationManager
        }

        fun getChanelNotification(title: String, message: String): NotificationCompat.Builder {
            return NotificationCompat.Builder(applicationContext, channelID).setContentTitle(title)
                .setContentText(message).setSmallIcon(R.mipmap.icon_notification)
        }

}