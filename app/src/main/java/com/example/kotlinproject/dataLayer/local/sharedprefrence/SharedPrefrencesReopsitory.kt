package com.example.kotlinproject.dataLayer.local.sharedprefrence

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng

class SharedPrefrencesReopsitory(context: Context) {
    private val setting : SettingSB = SettingSB(context)
    fun updateSetting(settingModel: SettingModel)=setting.saveSetting(settingModel)
    fun getSetting(): LiveData<SettingModel>{
        setting.loadSetting()
        return setting.getSetting()
    }


    fun saveLocationSetting(latLng: LatLng)=setting.saveLocationSetting(latLng)
    fun getLocationSetting(): LiveData<LatLng>{
        setting.loadLocationSetting()
        return setting.getLocationSetting()
    }
}