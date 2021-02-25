package com.example.kotlinproject.dataLayer.online

import com.example.kotlinproject.dataLayer.entity.currentModel.ModelCurent
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=30.590426&lon=31.502410&lang=en&appid=517a14f849e519bb4fa84cdbd4755f56"
interface ApiInterface {
    @GET("data/2.5/weather?")
     fun getCurrent(@Query("lat") lat: String,
                           @Query("lon") lon: String,
                           @Query("lang") lang: String,
                           @Query("appid") appid: String): Call<ModelCurent>


//https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=minutely&lang=ar&units=standard&appid=517a14f849e519bb4fa84cdbd4755f56
//https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&lang=ar&appid=517a14f849e519bb4fa84cdbd4755f56
//     @GET("/data/2.5/onecall?")
//    fun getOneCall(@Query("lat") lat: String,
//                   @Query("lon") lon: String,
//                   @Query("lang") lang: String,
//                   @Query("appid") appid: String) :Call<AllData>


     @GET("/data/2.5/onecall?")
    fun getOneCall(@Query("lat") lat: String,
                   @Query("lon") lon: String,
                   @Query("lang") lang: String,
                   @Query("appid") appid: String,
                    @Query("exclude") exclude : String,
                    @Query("units") units: String) :Call<AllData>
}

//@Query("units") units :String