package com.example.kotlinproject.ui.createAlerm

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.AlertTable
import java.util.*

class CreateAlermViewModel(application: Application) : AndroidViewModel(application) {
    val dataSourceViewModel=DataSourceViewModel(application)
    val dataSavedOrNot=MutableLiveData<Boolean>()
     private fun saveAlert(alertTable: AlertTable){
            dataSourceViewModel.saveAlert(alertTable)
    }
    fun getAllAlerts(): LiveData<List<AlertTable>> {
        return dataSourceViewModel.getAllAlerts()
    }

    fun saveData( title: String?,
                  startTime: Long?,
                  startDate: Long?,
                  endTime: Long?,
                  endDate: Long?,
                  reputation: Boolean){
        if (title != null || startTime!= null
            || startDate != null || endTime != null
            || endDate!= null){
//            saveAlert(AlertTable(title = title!!,startTime = startTime!!,startDate = startDate!!,
//                endTime = endTime!!,endDate = endDate!!,reputation = reputation))
            dataSavedOrNot.value=true
        }else{
            dataSavedOrNot.value=false
        }
    }

    fun getDataSavedOrNot():LiveData<Boolean>{
        return dataSavedOrNot
    }


//     fun setAlaram(activity: Activity,calende : Calendar) {
//        val intentA = Intent(activity, AlermRecever::class.java)
//        val random = Random()
//        val int1 = random.nextInt(99)
//        val pendingIntentA = PendingIntent.getBroadcast(activity, int1, intentA, 0)
////        val calendar = Calendar.getInstance()
////        calendar.set(Calendar.HOUR_OF_DAY, hour)
////        calendar.set(Calendar.MINUTE, min)
////        calendar[Calendar.MONTH] = month - 1
////        calendar[Calendar.DATE] = day
////        calendar[Calendar.YEAR] = year
////        calendar[Calendar.SECOND] = 0
//        val alarmtime: Long = calende.timeInMillis
//         Log.d("Tag",alarmtime.toString())
//        val alarmManager: AlarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmtime, pendingIntentA)
//        activity.registerReceiver(AlermRecever(), IntentFilter())
//    }
     fun setAlaram(activity: Activity,hour:Int,min:Int,month:Int,day:Int,year:Int) {
        val intentA = Intent(activity, AlermRecever::class.java)
        val random = Random()
        val int1 = random.nextInt(99)
        val pendingIntentA = PendingIntent.getBroadcast(activity, int1, intentA, 0)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, min)
        calendar[Calendar.MONTH] = month - 1
        calendar[Calendar.DATE] = day
        calendar[Calendar.YEAR] = year
        calendar[Calendar.SECOND] = 0
        val alarmtime: Long = calendar.timeInMillis
         Log.d("Tag",alarmtime.toString())
        val alarmManager: AlarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmtime, pendingIntentA)
        activity.registerReceiver(AlermRecever(), IntentFilter())
    }

}