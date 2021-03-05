package com.example.kotlinproject.ui.alert

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.kotlinproject.databinding.FragmentAlertBinding
import com.example.kotlinproject.ui.baseHome.MainActivity
import java.util.*


class Alert : Fragment() {

    lateinit var notificationHelper: NotificationHelper
    lateinit var fragmentAlertBinding :FragmentAlertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentAlertBinding= FragmentAlertBinding.inflate(inflater, container, false)
        notificationHelper= NotificationHelper(requireActivity())


        startAlert()




        return fragmentAlertBinding.root
    }

    fun startAlert(){

        val myIntent = Intent(activity, AlermRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 400)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, 1000, pendingIntent)

    }

    fun cancelAlarm(){
        val myIntent = Intent(activity, AlermRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

    }

}