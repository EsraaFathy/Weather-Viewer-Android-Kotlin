package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.AlertTable
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Alert
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData


class RoomRepositry(context: Application) : AndroidViewModel(context) {
    val database :DataBaseWeather?=DataBaseWeather.getInstance(context)
    val weatherDao :WeatherDao= database!!.weatherDao()

    suspend fun saveAllData(allData : AllData){
        weatherDao.saveAllData(allData)
    }
     fun getAllData(): LiveData<List<AllData>>{
        return weatherDao.getAllData()
    }
    fun getData(): List<AllData>{
        return weatherDao.getData()
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
    fun getFavDataNotLiveData(): List<FavData>{
        return weatherDao.getFavDataNotLiveData()
    }
    fun deleteAllFav(){
        return weatherDao.deleteAllFav()
    }

    fun getOneFav(lat: String,lon: String):LiveData<FavData>{
        return weatherDao.getOneFav(lat,lon)
    }
    fun deleteOneFav(lat: String,lon: String){
        weatherDao.deleteOneFav(lat,lon)
    }

     fun saveAlert(alertTable: AlertTable):Long{
       return weatherDao.saveAlert(alertTable)
    }
    fun getAllAlerts():LiveData<List<AlertTable>>{
        return weatherDao.getAllAlerts()
    }
    fun deleteAlert(id: Long)=weatherDao.deleteAlert(id)
}