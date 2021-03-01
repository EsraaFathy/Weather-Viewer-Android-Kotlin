package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(context: Context) : ViewModel() {
    val locationHanding: LocationHanding = LocationHanding(context)
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)
    private val data:MutableLiveData<AllData> = MutableLiveData<AllData>()


    fun loadOnlineData(
        lat: String,
        lon: String,
        lang: String,
        appid: String,
        exclude: String,
        units: String
    ){
        Log.d("TAG", "loadOnlineData: ")
        dataSourceViewModel.loadOneCall(lat, lon, lang, appid, exclude, units)
    }



    fun gettingLocation() :LiveData<Location>{
        locationHanding.loadLocation()
        return locationHanding.getLocatin()
    }


    fun getSetting():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }

//     fun getRoomData():LiveData<List<AllData>>{
//        return dataSourceViewModel.getRoomDataBase()
//    }

//    fun getRoomData():LiveData<AllData>{
//        return dataSourceViewModel.getRoomDataBase()
//    }
//    fun loadRoomData(){
//        return dataSourceViewModel.loadRoomDataBase()
//    }

     fun getRoomData():LiveData<List<AllData>>{
        return dataSourceViewModel.getRoomDataBase()
    }


    @SuppressLint("SimpleDateFormat")
    fun formateDate(format: Int): String {
            val dateFormat = SimpleDateFormat("EEE,dd MM yyyy")
            val date = Date()
            return dateFormat.format(date)
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