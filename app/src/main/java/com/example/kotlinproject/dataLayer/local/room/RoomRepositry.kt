package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData


class RoomRepositry(context: Context) {
    val database :DataBaseWeather?=DataBaseWeather.getInstance(context)
    val weatherDao :WeatherDao= database!!.weatherDao()

    suspend fun saveAllData(allData : AllData){
        weatherDao.saveAllData(allData)
    }
     fun getAllData(): LiveData<List<AllData>>{
        return weatherDao.getAllData()
    }
     fun deleteAll(){
        return weatherDao.deleteAll()
    }

    /////////////////fav\\\\\\\\\\\\\\\\\\\\\
    suspend fun saveFavData(favData : FavData){
        weatherDao.saveFaveData(favData)
    }
    fun getFavData(): LiveData<List<FavData>>{
        return weatherDao.getFavData()
    }

    fun getOneFav(lat: String,lon: String):LiveData<FavData>{
        return weatherDao.getOneFav(lat,lon)
    }
}