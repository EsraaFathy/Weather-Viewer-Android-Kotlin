package com.example.kotlinproject.dataLayer.local.curent

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Weather
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CurrentFileSB(val context: Context) {

    companion object {
        const val fileName = "Current"
    }
    private val cueentLive: MutableLiveData<Current> = MutableLiveData<Current>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE)

    fun saveCurrent(current: Current,timezone:String){
        CoroutineScope(Dispatchers.IO).launch {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putInt("clouds",current.clouds)
            editor.putString("timezone",timezone)
            editor.putInt("dt",current.dt)
            editor.putInt("humidity",current.humidity)
            editor.putInt("pressure",current.pressure)
            editor.putInt("sunrise",current.sunrise)
            editor.putInt("sunset",current.sunset)
            editor.putFloat("temp", current.temp.toFloat())
            editor.putFloat("wind_speed",current.wind_speed.toFloat())
            editor.putString("description",current.weather[0].description)
            editor.putString("main",current.weather[0].main)
            editor.apply()
            editor.commit()
        }
    }

    fun loadLocalCurrent(){
        CoroutineScope(Dispatchers.IO).launch {
            val clouds = sharedPreferences.getInt("clouds",0)
            val dt = sharedPreferences.getInt("dt",0)
            val humidity = sharedPreferences.getInt("humidity",0)
            val pressure = sharedPreferences.getInt("pressure",0)
            val sunrise = sharedPreferences.getInt("sunrise",0)
            val sunset = sharedPreferences.getInt("sunset",0)
            val temp = sharedPreferences.getFloat("temp",0.0f)
            val wind_speed = sharedPreferences.getFloat("wind_speed",0.0f)
            val description = sharedPreferences.getString("description","no Data Stored yet")
            val main = sharedPreferences.getString("timezone","no Data Stored yet")
            val wether: List<Weather> = listOf(Weather(description!!,"",0, main!!))
            val current =Current(clouds,0.0,dt,0.0,humidity,pressure,sunrise,sunset
                ,temp.toDouble(),0.0,0,wether,0,wind_speed.toDouble())
            cueentLive.postValue(current)
        }
    }
    fun getLocalCurrent(): LiveData<Current>{
        return cueentLive
    }
}