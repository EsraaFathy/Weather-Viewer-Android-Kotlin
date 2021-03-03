package com.example.kotlinproject.ui.favouriteDetails

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailsViewModel(val context: Context) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)
    fun getOneFav(lat: String,lon: String)= dataSourceViewModel.getOneFav(lat,lon)
    fun saveFave(lat: String,lon: String,lang: String,units :String){
        dataSourceViewModel.saveFave(lat,lon,lang,units)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .fitCenter() //4
            .into(imageView)
    }
    @SuppressLint("SimpleDateFormat")
    fun formateTime(format: Int): String {
        val dateFormat = SimpleDateFormat("HH:mm a")
        val date = Date()
        date.time = format.toLong() * 1000
        return dateFormat.format(date)

    }
    @SuppressLint("SimpleDateFormat")
    fun formateDate(format: Int): String {
        val dateFormat = SimpleDateFormat("EEE,dd MM yyyy")
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(format.toLong()* 1000)
        return sdf.format(netDate)
    }
}