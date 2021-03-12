package com.example.kotlinproject.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.media.AudioAttributes
import android.media.AudioManager
import android.net.Uri
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

                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                val soundUri = Uri.parse(
                    "android.resource://" +
                            applicationContext.packageName +
                            "/" +
                            R.raw.rang
                )

                val notificationChannel = NotificationChannel(
                    channelID,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationChannel.enableLights(true)
                notificationChannel.enableVibration(true)
                notificationChannel.setSound(soundUri, audioAttributes)
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
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSound(Uri.parse(
                    "android.resource://" +
                            applicationContext.packageName +
                            "/" +
                            R.raw.rang
                ), AudioManager.STREAM_NOTIFICATION)
                .setContentText(message).setSmallIcon(R.mipmap.icon_notification)
        }

}