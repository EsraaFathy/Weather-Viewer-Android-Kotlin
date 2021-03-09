package com.example.kotlinproject.ui.splash

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.ui.GeneralFunctions
import java.util.*

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)
    private val generalFunctions=GeneralFunctions()

    private fun setLocale(activity: Activity, languageCode: String?) {
        generalFunctions.setLocale(activity,languageCode)
    }
    fun getSetting():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }
    fun enableLocalization(activity: Activity, languageCode: String?){
        setLocale(activity,languageCode)

    }



}