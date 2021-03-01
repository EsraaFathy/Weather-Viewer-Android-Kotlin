package com.example.kotlinproject.dataLayer

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SharedPrefrencesReopsitory
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

class DataSourceViewModel(context: Context) {

    private val sharedPreferencesReopsitory: SharedPrefrencesReopsitory =
        SharedPrefrencesReopsitory(context)
    private val repositoryonLine = Repository(ApiClient.apiService)
    private val roomRepositry: RoomRepositry = RoomRepositry(context)
    private val roomData :MutableLiveData<AllData> = MutableLiveData<AllData>()


    fun loadOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
           val data =  repositoryonLine.getOneCall(lat,lon,lang,appid,exclude,units)
            data.enqueue(object : Callback<AllData?> {
                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                    Log.d("tag", response.body().toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        roomRepositry.deleteAll()
                        roomRepositry.saveAllData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<AllData?>, t: Throwable) {
                    Log.d("tag", t.message.toString())
                    t.printStackTrace()

                }
            })


    }


    fun saveFave(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data =repositoryonLine.getFavCall(lat,lon,lang,appid,exclude,units)
            roomRepositry.saveFavData(data)
        }
    }



    fun getSetting(): LiveData<SettingModel> {
        return sharedPreferencesReopsitory.getSetting()
    }
    fun setSetting(setttingModel:SettingModel) {
        sharedPreferencesReopsitory.updateSetting(setttingModel)
    }


     fun getRoomDataBase() : LiveData<List<AllData>>{
        return roomRepositry.getAllData()
    }


}