package com.example.kotlinproject.ui.favourit

import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val mApplication: Application=application
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication)
    private val intentLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    private val alertDialogLiveData: MutableLiveData<FavData> = MutableLiveData<FavData>()
    fun deleteOneFav(lat: String, lon: String)= dataSourceViewModel.deleteOneFav(lat,lon)

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


    fun intentLiveData(position : Int){
        intentLiveData.value=position
    }

    fun getIntent():LiveData<Int>{
        return intentLiveData
    }
    fun setAlertDialogLiveData(position : FavData){
        alertDialogLiveData.value=position
    }

    fun getAlertDialogLiveData():LiveData<FavData>{
        return alertDialogLiveData
    }
}
