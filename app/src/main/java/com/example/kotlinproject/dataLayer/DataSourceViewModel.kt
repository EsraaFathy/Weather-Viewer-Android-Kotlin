package com.example.kotlinproject.dataLayer

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly
import com.example.kotlinproject.dataLayer.local.curent.SharedPrefrencesReopsitory
import com.example.kotlinproject.dataLayer.local.room.RoomRepositry
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceViewModel(context: Context){

    private val sharedPreferencesReopsitory : SharedPrefrencesReopsitory = SharedPrefrencesReopsitory(context)
    private val repositoryonLine = Repository(ApiClient.apiService)
    private val roomRepositry : RoomRepositry= RoomRepositry(context)


    private var oneCallData: MutableLiveData<AllData> = MutableLiveData<AllData>()

   // TODO("shared setting ")
    fun loadOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val  data =async { repositoryonLine.getOneCall(lat,lon,lang,appid,exclude,units) }
            data.await().enqueue(object : Callback<AllData?> {
                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                    oneCallData.value = response.body()
                        Log.d("TAG","not null")
//                        roomRepositry.saveAllData(response.body()!!)
                    saveCurentToLocal(response.body()!!.current, response.body()!!.timezone)
                }

                override fun onFailure(call: Call<AllData?>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("TAG","onFailure")

                }
            })
        }
    }


    fun getOneCalltData() : LiveData<AllData>{
        return oneCallData;
    }


    private fun saveCurentToLocal(current: Current,timezone:String){
        sharedPreferencesReopsitory.saveCurrent(current,timezone)
    }
    fun getCurrentFromLocal(): LiveData<Current>{
        return sharedPreferencesReopsitory.getLoca()
    }


    fun getHourly(): LiveData<AllData>{
        return roomRepositry.getAllData()
    }
//
//    fun getDaily(): LiveData<List<Daily>>{
//        return roomRepositry.getDaily()
//    }
}