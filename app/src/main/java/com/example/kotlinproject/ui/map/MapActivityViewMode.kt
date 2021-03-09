package com.example.kotlinproject.ui.map

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.google.android.gms.maps.model.LatLng

class MapActivityViewMode(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    val saveFav :MutableLiveData<Boolean> =MutableLiveData<Boolean>()
    val saveLatLng :MutableLiveData<Boolean> =MutableLiveData<Boolean>()
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(application)

     fun showAlarm(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(context.getString(R.string.are_you_sure))
        alertDialogBuilder.setMessage(context.getString(R.string.you_want_to_add))
        alertDialogBuilder.setPositiveButton(context.getString(R.string.yes)) { _, _ ->
            saveFav.value=true
        }
        alertDialogBuilder.setNegativeButton(context.getString(R.string.no)) { _, _ ->
            saveFav.value=false
        }
        alertDialogBuilder.show()
    }
    fun showLocationSavingAlarm(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(context.getString(R.string.are_you_sure))
        alertDialogBuilder.setMessage(context.getString(R.string.are_you_sure_location))
        alertDialogBuilder.setPositiveButton(context.getString(R.string.yes)) { _, _ ->
            saveLatLng.value=true
        }
        alertDialogBuilder.setNegativeButton(context.getString(R.string.no)) { _, _ ->
            saveLatLng.value=false
        }
        alertDialogBuilder.show()
    }

    fun saveFav(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }

    fun getSettnig():LiveData<SettingModel>{
        return dataSourceViewModel.getSetting()
    }

    fun saveLocationSetting(latLng: LatLng)=dataSourceViewModel.saveLocationSetting(latLng)




}