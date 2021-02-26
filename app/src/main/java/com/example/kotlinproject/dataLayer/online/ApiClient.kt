package com.example.kotlinproject.dataLayer.online

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//object ApiClient {
//    private const val BASE_URL = "http://api.openweathermap.org/"
//    var gson = GsonBuilder()
//    .setLenient()
//    .create()
//    private fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
//
//    val apiService: ApiInterface = getRetrofit().create(ApiInterface::class.java)
//}

object ApiClient {
    private const val BASE_URL = "http://api.openweathermap.org/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}

//
//var retrofit: Retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .client(client)
//    .addConverterFactory(GsonConverterFactory.create(gson))
//    .build()