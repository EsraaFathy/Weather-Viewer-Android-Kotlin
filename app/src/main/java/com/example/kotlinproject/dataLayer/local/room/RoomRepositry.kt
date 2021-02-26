package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly

class RoomRepositry(application: Context) {
    val database :DataBaseWeather?=DataBaseWeather.getInstance(application)
    val weatherDao :WeatherDao= database!!.weatherDao()

    fun saveAllData(list : AllData){
        weatherDao.saveAllData(list)
    }
    fun getAllData(): LiveData<AllData>{
        return weatherDao.getAllData()
    }

}