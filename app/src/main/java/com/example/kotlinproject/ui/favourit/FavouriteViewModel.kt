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
    private val dataSourceViewModel: DataSourceViewModel = DataSourceViewModel(mApplication.applicationContext)
    fun deleteOneFav(lat: String,lon: String)= dataSourceViewModel.deleteOneFav(lat,lon)

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

    fun showAlarm(lat : String,lon: String) {
        val alertDialogBuilder = AlertDialog.Builder(mApplication.applicationContext)
        alertDialogBuilder.setTitle("Are you Sure")
        alertDialogBuilder.setMessage("you want to delete this city")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                deleteOneFav(lat,lon)
            }
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->

        }
        alertDialogBuilder.show()
    }


}
