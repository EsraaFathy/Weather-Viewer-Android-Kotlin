package com.example.kotlinproject.dataLayer

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.curent.SettingModel
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

class DataSourceViewModel(context: Context) {

    private val sharedPreferencesReopsitory: SharedPrefrencesReopsitory =
        SharedPrefrencesReopsitory(context)
    private val repositoryonLine = Repository(ApiClient.apiService)
    private val roomRepositry: RoomRepositry = RoomRepositry(context)


    private var roomDataLiveData: MutableLiveData<AllData> = MutableLiveData<AllData>()

    fun loadOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val  data =async { repositoryonLine.getOneCall(lat,lon,lang,appid,exclude,units) }
//            val  data =async { repositoryonLine.getOneCall(lat,lon,lang,appid,minutely,units) }
            data.await().enqueue(object : Callback<AllData?> {
                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                    CoroutineScope(Dispatchers.Default).launch {
                        Log.d("TAG", response.body()?.timezone.toString())
                        val delete =async { roomRepositry.deleteAll() }
                        delete.await()
                        roomRepositry.saveAllData(response.body()!!)
                    }

                }

                override fun onFailure(call: Call<AllData?>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("TAG","onFailure")

                }
            })
        }
    }


    fun saveFave(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val  data =async { repositoryonLine.getFavCall(lat,lon,lang,appid,exclude,units) }
//            val  data =async { repositoryonLine.getOneCall(lat,lon,lang,appid,minutely,units) }
            data.await().enqueue(object : Callback<FavData?> {
                override fun onResponse(call: Call<FavData?>, response: Response<FavData?>) {
                    CoroutineScope(Dispatchers.Default).launch {
                        Log.d("TAG", response.body()?.timezone.toString())
                        roomRepositry.saveFavData(response.body()!!)
                    }

                }

                override fun onFailure(call: Call<FavData?>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("TAG","onFailure")

                }
            })
        }
    }


     fun updateSetting(settingModel: SettingModel) {
        sharedPreferencesReopsitory.updateSetting(settingModel)
    }

    fun getSetting(): LiveData<SettingModel> {
        return sharedPreferencesReopsitory.getSetting()
    }


    fun loadRoomData(){
        CoroutineScope(Dispatchers.Default).launch {
            Log.d("TAG", "not null")

            roomDataLiveData.postValue(roomRepositry.getAllData().value)
        }
    }


    fun getRoomDataBase() : LiveData<AllData>{
        return roomDataLiveData
    }


//
//    fun getDaily(): LiveData<List<Daily>>{
//        return roomRepositry.getDaily()

}