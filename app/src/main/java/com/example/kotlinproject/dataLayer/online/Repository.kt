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

    //https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=minutely&lang=ar&units=standard&appid=517a14f849e519bb4fa84cdbd4755f56

    fun getOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
         apiInterface.getOneCall(lat,lon,lang,appid,exclude,units)


//    private var oneCallData: MutableLiveData<AllData> = MutableLiveData<AllData>()
//
//     fun loadOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val data = getOneCall(lat,lon,lang,appid,exclude,units)
//            data.enqueue(object : Callback<AllData?> {
//                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
//                    oneCallData.value = response.body()
//
//                }
//
//                override fun onFailure(call: Call<AllData?>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//
//        }
//    }
//
//
//     fun getOneCalltData() : LiveData<AllData>{
//        return oneCallData;
//    }



}