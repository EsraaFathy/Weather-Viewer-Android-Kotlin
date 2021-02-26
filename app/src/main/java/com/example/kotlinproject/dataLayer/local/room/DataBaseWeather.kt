package com.example.kotlinproject.dataLayer.local.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinproject.dataLayer.entity.Converter
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly

@TypeConverters(Converter::class)
@Database(entities = arrayOf(AllData::class), version = 1)
abstract class DataBaseWeather : RoomDatabase() {
    companion object{
        private var db :DataBaseWeather? =null

        fun getInstance(application: Context): DataBaseWeather? {
            if (db == null)
                db = Room.databaseBuilder(
                    application, DataBaseWeather::class.java, "Weather"
                ).build()
            return db
        }

    }

    abstract fun weatherDao(): WeatherDao

}