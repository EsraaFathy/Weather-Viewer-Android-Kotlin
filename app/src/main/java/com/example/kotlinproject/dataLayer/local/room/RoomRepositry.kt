package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData


class RoomRepositry(application: Context) {
    val database :DataBaseWeather?=DataBaseWeather.getInstance(application)
    val weatherDao :WeatherDao= database!!.weatherDao()

    fun saveAllData(allData : AllData){
        weatherDao.saveAllData(allData)
    }
     suspend fun getAllData(): List<AllData>?{
        return weatherDao.getAllData()
    }
     fun deleteAll(){
        return weatherDao.deleteAll()
    }
     fun saveFavData(favData : FavData){
        weatherDao.saveFaveData(favData)
    }
}