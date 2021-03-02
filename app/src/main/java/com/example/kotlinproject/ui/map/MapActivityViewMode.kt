package com.example.kotlinproject.ui.map

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel

class MapActivityViewMode(val context: Context) {

    val save :MutableLiveData<Boolean> =MutableLiveData<Boolean>()
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)

     fun showAlarm() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Are you Sure")
        alertDialogBuilder.setMessage("you want to add this location to favourit places")
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            save.value=true
        }
        alertDialogBuilder.setNegativeButton("Now") {dialog, which ->
            save.value=false
        }
        alertDialogBuilder.show()
    }

    fun saveFav(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }

    fun getSettnig():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }
}