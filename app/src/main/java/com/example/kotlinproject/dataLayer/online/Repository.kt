package com.example.kotlinproject.dataLayer.online

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiInterface : ApiInterface) {
    fun getOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
        apiInterface.getOneCall(lat,lon,lang,appid,exclude,units)
    fun getFavCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
        apiInterface.getFavCall(lat,lon,lang,appid,exclude,units)

}