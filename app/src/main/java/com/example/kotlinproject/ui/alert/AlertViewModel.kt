package com.example.kotlinproject.ui.alert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData

class AlertViewModel(application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)


    fun getAllData():LiveData<List<AllData>>{
      return  dataSourceViewModel.getRoomDataBase()
    }

    fun getTimezones():LiveData<List<String>>{
      return  dataSourceViewModel.getTimezones()
    }

    fun getAlertFav(timeZone :String): LiveData<FavData>?{
        return dataSourceViewModel.getAlertFav(timeZone)
    }


    fun saveAlertSetting(alert: String)=dataSourceViewModel.saveAlertSetting(alert)

    fun getAlertSetting(): LiveData<String>{
        return dataSourceViewModel.getAlertSetting()
    }

}