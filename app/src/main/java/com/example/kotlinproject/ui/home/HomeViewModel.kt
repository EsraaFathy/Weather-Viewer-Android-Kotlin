package com.example.kotlinproject.ui.home

import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Weather
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class HomeViewModel(context: Context) : ViewModel() {
    private var dateLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private var progress :MutableLiveData<Int> =MutableLiveData<Int>()
    val locationHanding: LocationHanding = LocationHanding(context)
    private var timeLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)


    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String){
        progress.value=View.INVISIBLE
        Log.d("TAG", "loadOnlineData: ")
        dataSourceViewModel.loadOneCall(lat,lon,lang,appid,exclude, units)
    }
    fun loadOnline():LiveData<AllData>{
        return dataSourceViewModel.getOneCalltData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun loadDate(){
        val date= LocalDate.now()
        dateLiveData.value= date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun loadTime(){
        val currentTime = LocalTime.now()
        timeLiveData.value= currentTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }

    fun getTime():LiveData<String>{
        return timeLiveData
    }
    fun getDate():LiveData<String>{
        return dateLiveData
    }
    fun getProgress():LiveData<Int>{
        return progress
    }

    fun gettingLocation() :LiveData<Location>{
        locationHanding.loadLocation()
        return locationHanding.getLocatin()
    }

    fun getCurrentLocalStatue() :LiveData<Boolean>{
        return locationHanding.getFromLoacl()
    }
    fun getCurrentLocal() :LiveData<Current>{
        progress.value=View.INVISIBLE
        return dataSourceViewModel.getCurrentFromLocal()
    }
//    fun saveCurentToLocal(current: Current,timezone:String){
//        dataSourceViewModel.saveCurentToLocal(current,timezone)
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeAndDate(dt : String):Date{
        ///// todo
//        val s = java.time.format.DateTimeFormatter.ISO_INSTANT.format(
//            java.time.Instant.ofEpochSecond()
//        )
        lateinit var date :Date
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            date  = format.parse(dt)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }


    fun handelData(lat: String,lon: String){
    }
}