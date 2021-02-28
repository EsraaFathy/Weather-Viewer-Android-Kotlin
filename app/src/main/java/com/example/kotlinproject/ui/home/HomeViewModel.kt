package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
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
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(context: Context) : ViewModel() {
    private var progress :MutableLiveData<Int> =MutableLiveData<Int>()
    val locationHanding: LocationHanding = LocationHanding(context)
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)


    fun loadOnlineData(
        lat: String,
        lon: String,
        lang: String,
        appid: String,
        exclude: String,
        units: String
    ){
        progress.value=View.INVISIBLE
        Log.d("TAG", "loadOnlineData: ")
        dataSourceViewModel.loadOneCall(lat, lon, lang, appid, exclude, units)
    }


    fun getProgress():LiveData<Int>{
        return progress
    }

    fun gettingLocation() :LiveData<Location>{
        locationHanding.loadLocation()
        return locationHanding.getLocatin()
    }


    fun getSetting():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }

     fun getRoomData():LiveData<AllData>{
        return dataSourceViewModel.getRoomDataBase()
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime(dt: Int):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dd= dt.toLong()
        val date = Date(dd * 1000)
        sdf.format(date)
       val time= "${date.hours}:${date.minutes}"
        return time
    }
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(dt: Int):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dd= dt.toLong()
        val date = Date(dd * 1000)
        Log.d("TAG",date.toString())
        sdf.format(date)
        val calendar = Calendar.getInstance()
        calendar.time = date
       val time= "${date.year}-${date.month}-${date.day}"
        return time
    }

}