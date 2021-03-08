package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinproject.dataLayer.entity.AlertTable
import com.example.kotlinproject.dataLayer.entity.Converter
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@TypeConverters(Converter::class)
@Database(entities = [AllData::class, FavData::class,AlertTable::class], version = 1,exportSchema = false)
abstract class DataBaseWeather : RoomDatabase() {
    companion object{
        @Volatile
        private var db :DataBaseWeather? =null

        fun getInstance(application: Application): DataBaseWeather? {
            synchronized(this) {
            if (db == null)
                db = Room.databaseBuilder(
                    application, DataBaseWeather::class.java, "Weather"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }

    }


    abstract fun weatherDao(): WeatherDao

}