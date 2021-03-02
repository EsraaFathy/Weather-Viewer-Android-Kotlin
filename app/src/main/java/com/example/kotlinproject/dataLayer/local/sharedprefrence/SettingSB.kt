package com.example.kotlinproject.dataLayer.local.sharedprefrence

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


    fun saveSetting(settingModel: SettingModel){
        CoroutineScope(Dispatchers.IO).launch {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("units",settingModel.units)
            editor.putString("lang",settingModel.lang)
            editor.putString("location",settingModel.location)
            editor.apply()
            editor.commit()
        }
    }

    fun loadSetting(){
        CoroutineScope(Dispatchers.IO).launch {
            val units = sharedPreferences.getString("units","standard")
            val lang = sharedPreferences.getString("lang","ar")
            val location = sharedPreferences.getString("location","gps")
            SettingData.postValue(SettingModel(units!!, lang!!, location!!))
        }
    }
    fun getSetting(): LiveData<SettingModel>{
        return SettingData
    }
}