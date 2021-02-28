package com.example.kotlinproject.dataLayer.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinproject.dataLayer.entity.Converter
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

@TypeConverters(Converter::class)
@Database(entities = [AllData::class, FavData::class], version = 1,exportSchema = false)
abstract class DataBaseWeather : RoomDatabase() {
    companion object{
        @Volatile
        private var db :DataBaseWeather? =null

        fun getInstance(application: Context): DataBaseWeather? {
            synchronized(this) {
            if (db == null)
                db = Room.databaseBuilder(
                    application, DataBaseWeather::class.java, "Weather"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }

    }



//    [7:52 PM] Alshimaa (Guest)
//    companion object {​​​​​
//
//        @Volatile
//        private var INSTANCE: WeatherDatabaseInstance? = null
//        fun getInstance(context: Context): WeatherDatabaseInstance {​​​​​
//            synchronized(this) {​​​​​
//                var instance = INSTANCE
//                if (instance == null) {​​​​​
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        WeatherDatabaseInstance::class.java,
//                        "weather_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }​​​​​
//                return instance
//            }​​​​​
//        }​​​​​
//    }​​​​​
//
//
//



    abstract fun weatherDao(): WeatherDao

}