package com.example.kotlinproject.dataLayer.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinproject.dataLayer.entity.AlertTable
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.favtable.Alert
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM AllData")
    fun getAllData(): LiveData<List<AllData>>

    @Query("SELECT * FROM AllData")
    fun getData(): List<AllData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllData(allData: AllData)

    @Query("DELETE FROM AllData")
    fun deleteAll()


    //////////////////////fav\\\\\\\\\\\\\\\\\\\\\

    @Query("SELECT * FROM FavData ")
    fun getFavData(): LiveData<List<FavData>>


    @Query("SELECT * FROM FavData ")
    fun getFavDataNotLiveData(): List<FavData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFaveData(favData: FavData)


    @Query("SELECT * FROM FavData WHERE lat LIKE:lat AND lon LIKE:lon LIMIT 1")
    fun getOneFav(lat: String, lon: String): LiveData<FavData>

    @Query("DELETE FROM FavData WHERE lat LIKE:lat AND lon LIKE:lon")
    fun deleteOneFav(lat: String, lon: String)


    @Query("DELETE FROM FavData")
    fun deleteAllFav()

    @Query("SELECT timezone FROM FavData")
    fun getTimezones(): LiveData<List<String>>

    @Query("SELECT * FROM FavData WHERE timezone LIKE:timezone LIMIT 1")
    fun getOAlerts(timezone : String): LiveData<FavData>?


    //////////////////////alert\\\\\\\\\\\\\\\\\\\\\
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveAlert(alertTable: AlertTable):Long

    @Query("SELECT * FROM AlertTable")
    fun getAllAlerts():LiveData<List<AlertTable>>

    @Query("DELETE FROM AlertTable WHERE id Like:id")
    fun deleteAlert(id: Long)


}