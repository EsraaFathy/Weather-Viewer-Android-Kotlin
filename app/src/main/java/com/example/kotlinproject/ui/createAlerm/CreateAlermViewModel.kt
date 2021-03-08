package com.example.kotlinproject.ui.createAlerm

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
            saveAlert(AlertTable(title = title!!,startTime = startTime!!,startDate = startDate!!,
                endTime = endTime!!,endDate = endDate!!,reputation = reputation))
            dataSavedOrNot.value=true
        }else{
            dataSavedOrNot.value=false
        }
    }

    fun getDataSavedOrNot():LiveData<Boolean>{
        return dataSavedOrNot
    }

    fun startAlert(activity: Activity,amount :Long) {

        val myIntent = Intent(activity, AlermRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val calendar: Calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.add(Calendar.SECOND, 400)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, amount, pendingIntent)

    }
}