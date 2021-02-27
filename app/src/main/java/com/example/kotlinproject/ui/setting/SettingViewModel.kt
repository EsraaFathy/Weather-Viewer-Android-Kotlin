package com.example.kotlinproject.ui.setting

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.ui.home.LocationHanding


class SettingViewModel(val context :Context) {
    val dataSourceViewModel :DataSourceViewModel= DataSourceViewModel(context)

    fun getSetting():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }
    fun setSetting(setttingModel:SettingModel){
        dataSourceViewModel.setSetting(setttingModel)
    }

}