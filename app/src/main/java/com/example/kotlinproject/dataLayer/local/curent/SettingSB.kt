package com.example.kotlinproject.dataLayer.local.curent

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingSB(val context: Context) {

    companion object {
        const val fileName = "Current"
    }
    private val SettingData: MutableLiveData<SettingModel> = MutableLiveData<SettingModel>()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
//    standard, Temperature in Kelvin and wind speed in meter/sec
//    metric     Celsius and wind speed in meter/sec,
//    imperial  For temperature in Fahrenheit and wind speed in miles/hour, use

    fun saveSetting(settingModel: SettingModel){
        CoroutineScope(Dispatchers.IO).launch {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("units",settingModel.units)
            editor.putString("lang",settingModel.lang)
            editor.apply()
            editor.commit()
        }
    }

    fun loadSetting(){
        CoroutineScope(Dispatchers.IO).launch {
            val units = sharedPreferences.getString("units","standard")
            val lang = sharedPreferences.getString("lang","ar")
            SettingData.postValue(SettingModel(units!!, lang!!))
        }
    }
    fun getSetting(): LiveData<SettingModel>{
        return SettingData
    }
}