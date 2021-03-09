package com.example.kotlinproject.ui.setting

import android.app.Activity
import android.app.Application
import android.content.Context
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.ui.GeneralFunctions
import com.example.kotlinproject.ui.home.LocationHanding


class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application = application
    val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)

    private val generalFunctions = GeneralFunctions()

     fun setLocale(activity: Activity, languageCode: String?) {
        generalFunctions.setLocale(activity, languageCode)
    }

    fun getSetting(): LiveData<SettingModel> {
        return dataSourceViewModel.getSetting()
    }

    fun setSetting(setttingModel: SettingModel) {
        dataSourceViewModel.setSetting(setttingModel)
    }

}