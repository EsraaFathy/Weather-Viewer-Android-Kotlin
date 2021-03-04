package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.google.android.gms.maps.model.LatLng
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    val locationHanding: LocationHanding = LocationHanding(mApplication.applicationContext)
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val data:MutableLiveData<AllData> = MutableLiveData<AllData>()


    fun loadOnlineData(
        lat: String,
        lon: String,
        lang: String,
        units: String
    ){
        Log.d("TAG", "loadOnlineData: ")
        dataSourceViewModel.loadOneCall(lat, lon, lang, units)
    }



    fun gettingLocation() :LiveData<Location>{
        locationHanding.loadLocation()
        return locationHanding.getLocatin()
    }
    fun getLocationSettnig():LiveData<LatLng>{
        return dataSourceViewModel.getLocationSetting()
    }

    fun getSetting():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }


     fun getRoomData():LiveData<List<AllData>>{
        return dataSourceViewModel.getRoomDataBase()
    }


    @SuppressLint("SimpleDateFormat")
    fun formateDate(format: Int): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(format.toLong()* 1000)
        return sdf.format(netDate)
        }

    @SuppressLint("SimpleDateFormat")
    fun formateTime(format: Int): String {
        val dateFormat = SimpleDateFormat("HH:mm a")
        val date = Date()
        date.time = format.toLong() * 1000
        return dateFormat.format(date)

    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }

}