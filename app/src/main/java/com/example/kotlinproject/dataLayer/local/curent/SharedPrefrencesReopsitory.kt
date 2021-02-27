package com.example.kotlinproject.dataLayer.local.curent

import android.content.Context
import androidx.lifecycle.LiveData

class SharedPrefrencesReopsitory(context: Context) {
    private val setting : SettingSB = SettingSB(context)
    fun updateSetting(settingModel: SettingModel){
        setting.saveSetting(settingModel)
    }

    fun getSetting(): LiveData<SettingModel>{
        setting.loadSetting()
        return setting.getSetting()
    }
}