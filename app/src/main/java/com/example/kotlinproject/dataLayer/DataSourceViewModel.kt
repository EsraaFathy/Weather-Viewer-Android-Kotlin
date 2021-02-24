package com.example.kotlinproject.dataLayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.dataLayer.model.ModelCurent
import com.example.kotlinproject.dataLayer.online.ApiClient
import com.example.kotlinproject.dataLayer.online.Repository

class DataSourceViewModel{

    var repositoryonLine = Repository(ApiClient.apiService)
    fun loadOnlineData(lat: String,lon: String,lang: String, appid: String):LiveData<ModelCurent>{
        repositoryonLine.loadCurrent(lat,lon,lang,appid)
       return repositoryonLine.getCurentData()
    }
}