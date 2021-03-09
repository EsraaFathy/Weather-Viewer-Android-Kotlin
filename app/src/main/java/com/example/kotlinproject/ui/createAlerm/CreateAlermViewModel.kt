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
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CreateAlermViewModel(application: Application) : AndroidViewModel(application) {
    val dataSourceViewModel = DataSourceViewModel(application)
    val dataSavedOrNot = MutableLiveData<Boolean>()
    val idLiveData = MutableLiveData<Int>()
    private fun saveAlert(alertTable: AlertTable):Long {
       return dataSourceViewModel.saveAlert(alertTable)
    }

    fun getdata(): LiveData<List<AllData>> {
        return dataSourceViewModel.getRoomDataBase()
    }

    fun saveData(
        title: String?,
        type: String?,
        time: String,
        reputation: Boolean
    ) {
        if (title != null || type != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val id =saveAlert(
                    AlertTable(
                        title = title!!,
                        type = type!!,
                        time = time,
                        reputation = reputation
                    )
                )
                Log.d("TAG", id.toString())
                idLiveData.postValue(id.toInt())
            }
            dataSavedOrNot.value = true
        } else {
            dataSavedOrNot.value = false
        }
    }

    fun getDataSavedOrNot(): LiveData<Boolean> {
        return dataSavedOrNot
    }

    fun setAlaram(
        activity: Activity,
        hour: Int,
        min: Int,
        month: Int,
        day: Int,
        year: Int,
        type: String?,
        reputation: Boolean,
        id: Int
    ) {
        val intentA = Intent(activity, AlermRecever::class.java)
        intentA.putExtra("TYPE", type)
        intentA.putExtra("REPUTATION", reputation)
        val pendingIntentA = PendingIntent.getBroadcast(activity, id, intentA, 0)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, min)
        calendar[Calendar.MONTH] = month - 1
        calendar[Calendar.DATE] = day
        calendar[Calendar.YEAR] = year
        calendar[Calendar.SECOND] = 0
        val alarmtime: Long = calendar.timeInMillis
        Log.d("Tag", alarmtime.toString())
        val alarmManager: AlarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmtime, pendingIntentA)
        activity.registerReceiver(AlermRecever(), IntentFilter())
    }


}