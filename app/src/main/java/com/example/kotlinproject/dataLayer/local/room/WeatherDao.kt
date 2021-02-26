package com.example.kotlinproject.dataLayer.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM AllData")
    fun getAllData(): LiveData<AllData>

    @Insert
    fun saveAllData(allData :AllData)


//    @Query("SELECT * FROM Hourly")
//    fun getHourly(): LiveData<List<Hourly>>
//
//    @Insert
//    fun saveHourly(daily :List<Hourly>)
}