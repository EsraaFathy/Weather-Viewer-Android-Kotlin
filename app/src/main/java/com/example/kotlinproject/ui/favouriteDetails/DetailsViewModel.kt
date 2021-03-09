package com.example.kotlinproject.ui.favouriteDetails

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.ui.GeneralFunctions

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val generalFunctions :GeneralFunctions= GeneralFunctions()
    fun getOneFav(lat: String,lon: String)= dataSourceViewModel.getOneFav(lat,lon)

    fun saveFave(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadImage(imageView: ImageView, string: String) {
        generalFunctions.loadImage(imageView,string)
    }
    @SuppressLint("SimpleDateFormat")
    fun fermatTime(format: Int): String {
        return generalFunctions.formateTime(format)

    }
    @SuppressLint("SimpleDateFormat")
    fun formatDate(format: Int): String {
        return generalFunctions.formateDate(format)
    }
    fun getUnites(units: String): String {
        return generalFunctions.getUnites(units)
    }
}