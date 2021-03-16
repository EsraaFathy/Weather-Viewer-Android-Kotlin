package com.example.kotlinproject.dataLayer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.AlertTable
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SharedPrefrencesReopsitory
import com.example.kotlinproject.dataLayer.local.room.RoomRepositry
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository
import com.example.kotlinproject.ui.baseHome.MainActivity
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferencesReopsitory: SharedPrefrencesReopsitory =
        SharedPrefrencesReopsitory(application)
    private val repositoryonLine = Repository(ApiClient.apiService)
    private val roomRepositry: RoomRepositry = RoomRepositry(application)
    private lateinit var job: Job
    private lateinit var job1: Job

    fun loadOneCall(lat: String,lon: String,lang: String,units :String) {
        if (!MainActivity.readFromDatabase) {
            val data = repositoryonLine.getOneCall(
                lat,
                lon,
                lang,
                "517a14f849e519bb4fa84cdbd4755f56",
                "minutely",
                units
            )
            //
            data.enqueue(object : Callback<AllData?> {
                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                    Log.d("tag", response.body().toString())

                    job = CoroutineScope(Dispatchers.IO).launch {
                        roomRepositry.deleteAll()
                        if (response.body() != null)
                            roomRepositry.saveAllData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<AllData?>, t: Throwable) {
                    Log.d("tag", t.message.toString())
                    t.printStackTrace()

                }
            })
        }


    }


    fun saveFave(lat: String,lon: String,lang: String,units :String) {
        val data =  repositoryonLine.getFavCall(lat,lon,lang,"517a14f849e519bb4fa84cdbd4755f56","minutely",units)
        data.enqueue(object : Callback<FavData?> {
            override fun onResponse(call: Call<FavData?>, response: Response<FavData?>) {
                Log.d("tag", response.body().toString())
               job1= CoroutineScope(Dispatchers.IO).launch {
                    roomRepositry.saveFavData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<FavData?>, t: Throwable) {
               t.printStackTrace()
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
        job1.cancel()
    }

    fun getSetting(): LiveData<SettingModel> {
        return sharedPreferencesReopsitory.getSetting()
    }

    fun saveLocationSetting(latLng: LatLng)=sharedPreferencesReopsitory.saveLocationSetting(latLng)

    fun getLocationSetting(): LiveData<LatLng> {
        return sharedPreferencesReopsitory.getLocationSetting()
    }
    fun setSetting(setttingModel:SettingModel) {
        sharedPreferencesReopsitory.updateSetting(setttingModel)
    }


     fun getRoomDataBase() : LiveData<List<AllData>>{
        return roomRepositry.getAllData()
    }

     fun getFavDataBase() : LiveData<List<FavData>>{
        return roomRepositry.getFavData()
    }
    fun getOneFav(lat: String,lon: String):LiveData<FavData>{
        return roomRepositry.getOneFav(lat,lon)
    }
    fun deleteOneFav(lat: String,lon: String){
        roomRepositry.deleteOneFav(lat,lon)
    }


    fun saveAlertSetting(alert: String)=sharedPreferencesReopsitory.saveAlertSetting(alert)

    fun getAlertSetting(): LiveData<String>{
        return sharedPreferencesReopsitory.getAlertSetting()
    }

     fun saveAlert(alertTable: AlertTable):Long{
           return roomRepositry.saveAlert(alertTable)
    }
    fun getAllAlerts():LiveData<List<AlertTable>>{
        return roomRepositry.getAllAlerts()
    }
    fun deleteAlert(id: Long)=roomRepositry.deleteAlert(id)

    fun deleteAllFav(){
        return roomRepositry.deleteAllFav()
    }

    fun getFavDataNotLiveData(): List<FavData>{
        return roomRepositry.getFavDataNotLiveData()
    }
}