package com.example.kotlinproject.dataLayer.entity

import androidx.room.TypeConverter
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromWetherToGesonx(list :List<WeatherX>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToWeatherx(gson: String):List<WeatherX>{
        val type = object : TypeToken<List<WeatherX>>() {}.type
        return Gson().fromJson(gson,type)
    }
    @TypeConverter
    fun fromWetherToGesonxx(list :List<WeatherXX>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToWeatherxx(gson: String):List<WeatherXX>{
        val type = object : TypeToken<List<WeatherXX>>() {}.type
        return Gson().fromJson(gson,type)
    }

    @TypeConverter
    fun fromWetherToGeson(list :List<Weather>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToWeather(gson: String):List<Weather>{
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(gson,type)
    }

    @TypeConverter
    fun fromCurrentToGeson(list :Current) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToCurrrent(gson: String):Current{
//        val type = object : TypeToken<List<Current>>() {}.type
        return Gson().fromJson(gson,Current::class.java)
    }

    @TypeConverter
    fun fromAlertToGeson(list :List<Alert>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToAlert(gson: String):List<Alert>{
        val type = object : TypeToken<List<Alert>>() {}.type
        return Gson().fromJson(gson,type)
    }


    @TypeConverter
    fun fromFeelsLikeToGeson(feelsLike: FeelsLike) : String{
        return Gson().toJson(feelsLike)
    }

    @TypeConverter
    fun fromGesonToFeelsLike(feelsLike: String) : FeelsLike{
        return Gson().fromJson(feelsLike,FeelsLike::class.java)
    }

    @TypeConverter
    fun fromRainToGeson(feelsLike: Rain) : String{
        return Gson().toJson(feelsLike)
    }

    @TypeConverter
    fun fromGesonToRain(feelsLike: String) : Rain{
        return Gson().fromJson(feelsLike,Rain::class.java)
    }



    @TypeConverter
    fun fromTemmpToGeson(temp: Temp) : String{
        return Gson().toJson(temp)
    }

    @TypeConverter
    fun fromGesonToTemp(temp: String) : Temp{
        return Gson().fromJson(temp,Temp::class.java)
    }


    @TypeConverter
    fun fromHourlyToGeson(list :List<Hourly>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToHourly(gson: String):List<Hourly>{
        val type = object : TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(gson,type)
    }
    @TypeConverter
    fun fromDailyToGeson(list :List<Daily>) : String{
        return Gson().toJson(list)
    }
    @TypeConverter
    fun fromJsonToDaily(gson: String):List<Daily>{
        val type = object : TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(gson,type)
    }
}