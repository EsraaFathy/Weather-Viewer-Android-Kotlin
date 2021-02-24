package com.example.kotlinproject.dataLayer

import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.model.currentModel.ModelCurent
import com.example.kotlinproject.dataLayer.model.onCallModel.OneCallModel
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository

class DataSourceViewModel{

    var repositoryonLine = Repository(ApiClient.apiService)
    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String):LiveData<ModelCurent>{
        repositoryonLine.loadCurrent(lat,lon,lang,appid)
       return repositoryonLine.getCurentData()
    }
    fun loadOCallData(lat: String,lon: String,lang: String, appid: String):LiveData<OneCallModel>{
        repositoryonLine.loadCurrent(lat,lon,lang,appid)
       return repositoryonLine.getOneCalltData()
    }
}