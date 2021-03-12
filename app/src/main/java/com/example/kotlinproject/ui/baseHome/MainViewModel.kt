package com.example.kotlinproject.ui.baseHome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    fun getFavDataNotLiveData(): List<FavData>{
        return dataSourceViewModel.getFavDataNotLiveData()
    }

    fun saveFav(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }
    fun getSettnig():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }
    fun deleteAllFav(){
        return dataSourceViewModel.deleteAllFav()
    }
}