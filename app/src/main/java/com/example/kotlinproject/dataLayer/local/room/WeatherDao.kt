package com.example.kotlinproject.dataLayer.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM AllData")
    suspend fun getAllData(): List<AllData>

    @Insert
    fun saveAllData(allData :AllData)

    @Query("DELETE FROM AllData")
    fun deleteAll()


    @Insert
    fun saveFaveData(favData :FavData)


}