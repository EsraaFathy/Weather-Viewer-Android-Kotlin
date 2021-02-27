package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly

class RoomRepositry(application: Context) {
    val database :DataBaseWeather?=DataBaseWeather.getInstance(application)
    val weatherDao :WeatherDao= database!!.weatherDao()

   suspend fun saveAllData(allData : AllData){
        weatherDao.saveAllData(allData)
    }
    suspend fun getAllData(): LiveData<AllData>{
        return weatherDao.getAllData()
    }
    suspend fun deleteAll(){
        return weatherDao.deleteAll()
    }
    suspend fun saveFavData(favData : FavData){
        weatherDao.saveFaveData(favData)
    }
}