package com.example.kotlinproject.dataLayer

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.entity.currentModel.ModelCurent
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.local.curent.SharedPrefrencesReopsitory
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository

class DataSourceViewModel(context: Context){

    private val sharedPreferencesReopsitory : SharedPrefrencesReopsitory =
        SharedPrefrencesReopsitory(context)
    private val repositoryonLine = Repository(ApiClient.apiService)
    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String):LiveData<ModelCurent>{
        repositoryonLine.loadCurrent(lat,lon,lang,appid)
       return repositoryonLine.getCurentData()
    }
    fun loadOnCallData(lat: String, lon: String, lang: String, appid: String,exclude :String, units: String):LiveData<AllData>{
        repositoryonLine.loadOneCall(lat,lon,lang,appid,units,units)
       return repositoryonLine.getOneCalltData()
    }

    fun saveCurentToLocal(current: Current,timezone:String){
        sharedPreferencesReopsitory.saveCurrent(current,timezone)
    }
    fun getCurrentFromLocal(): LiveData<Current>{
        return sharedPreferencesReopsitory.getLoca()
    }
}