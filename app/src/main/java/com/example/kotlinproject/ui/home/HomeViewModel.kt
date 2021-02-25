package com.example.kotlinproject.ui.home

import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Weather
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HomeViewModel(context: Context) : ViewModel() {
    private var dateLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private var progress :MutableLiveData<Int> =MutableLiveData<Int>()
    val locationHanding: LocationHanding = LocationHanding(context)
    private var timeLiveData :MutableLiveData<String> =MutableLiveData<String>()
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)


    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String): LiveData<AllData> {
        progress.value=View.INVISIBLE
        Log.d("TAG", "loadOnlineData: ")
       return dataSourceViewModel.loadOnCallData(lat,lon,lang,appid,exclude,units)
    }
//    fun load(lat: String,lon: String,lang: String, appid: String): LiveData<ModelCurent> {
//        progress.value=View.INVISIBLE
//        Log.d("TAG", "loadOnlineData: ")
//       return dataSourceViewModel.loadOnlineData(lat,lon,lang,appid)
//    }


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
    fun saveCurentToLocal(current: Current,timezone:String){
        dataSourceViewModel.saveCurentToLocal(current,timezone)
    }
}