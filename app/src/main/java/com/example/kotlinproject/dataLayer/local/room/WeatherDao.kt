package com.example.kotlinproject.dataLayer.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM AllData")
    fun getAllData(): LiveData<List<AllData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllData(allData: AllData)

    @Query("DELETE FROM AllData")
    fun deleteAll()

    //////////////////////fav\\\\\\\\\\\\\\\\\\\\\

    @Query("SELECT * FROM FavData ")
    fun getFavData(): LiveData<List<FavData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFaveData(favData: FavData)


    @Query("SELECT * FROM FavData WHERE lat LIKE:lat " + "OR lon LIKE:lon LIMIT 1")
    fun getOneFav(lat: String,lon: String): LiveData<FavData>

}