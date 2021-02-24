package com.example.kotlinproject.setting

import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData


class SettingViewModel(val context :Context) {
    fun gettingLocation() :LiveData<Location>{
        val locationHanding:LocationHanding=LocationHanding(context)
        locationHanding.loadLocation()
        return locationHanding.getLocatin()
    }

}