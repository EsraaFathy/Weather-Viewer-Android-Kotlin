package com.example.kotlinproject.ui.favourit

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.favtable.FavData

class FavouriteViewModel(context: Context) {
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(context)
    val clicked: MutableLiveData<String> = MutableLiveData<String>()

    fun getFavDataBase(): LiveData<List<FavData>> {
        return dataSourceViewModel.getFavDataBase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }

    fun getClicked(): LiveData<String>{
        return clicked
    }
}
