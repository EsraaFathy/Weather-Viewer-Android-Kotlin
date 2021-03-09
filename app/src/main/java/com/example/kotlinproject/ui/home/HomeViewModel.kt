package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.location.Location
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.ui.GeneralFunctions
import com.google.android.gms.maps.model.LatLng


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    private val locationHanding: LocationHanding = LocationHanding(mApplication.applicationContext)
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val generalFunctions : GeneralFunctions= GeneralFunctions()


    fun loadOnlineData(lat: String, lon: String, lang: String, units: String,context: Context)
    {
        if (generalFunctions.isOnline(context)) {
            Log.d("TAG", "loadOnlineData: ")
            dataSourceViewModel.loadOneCall(lat, lon, lang, units)
        }else{
            Toast.makeText(context,context.getString(R.string.you_areoffline),Toast.LENGTH_SHORT).show()
        }
    }

    fun getUnites(units: String): String {
        return generalFunctions.getUnites(units)
    }

    fun gettingLocation(context: Context,activity: Activity) :LiveData<Location>{
        locationHanding.loadLocation(context,activity)
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

    @RequiresApi(Build.VERSION_CODES.O)
     fun loadImage(imageView: ImageView, string: String) {
        generalFunctions.loadImage(imageView,string)
    }


    @SuppressLint("SimpleDateFormat")
    fun formateDate(format: Int): String {
        return generalFunctions.formateDate(format)
    }

    @SuppressLint("SimpleDateFormat")
    fun formateTime(format: Int): String {
        return generalFunctions.formateTime(format)
    }

    fun getAlertFromSetting():LiveData<String>{
        return dataSourceViewModel.getAlertSetting()
    }



}