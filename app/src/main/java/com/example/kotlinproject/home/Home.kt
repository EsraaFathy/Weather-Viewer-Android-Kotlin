package com.example.kotlinproject.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.model.ModelCurent
import com.example.kotlinproject.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class Home : Fragment() {
    lateinit var binding : FragmentHomeBinding
//    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentHomeBinding.inflate(inflater, container, false)

        val dataSourceViewModel :HomeViewModel= HomeViewModel()
        dataSourceViewModel.loadDate()
        dataSourceViewModel.loadTime()
        dataSourceViewModel.loadOnlineData("30.590426", "31.502410", "en", "517a14f849e519bb4fa84cdbd4755f56")
            .observe(this,{
                initUI(it)
            })
        dataSourceViewModel.getDate().observe(this,{
            binding.currentDate.text=it
        })
        dataSourceViewModel.getTime().observe(this,{
            binding.currentTime.text=it

        })
        dataSourceViewModel.getProgress().observe(this,{
            binding.progressHome.visibility=it
        })
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: ModelCurent) {
        loadImage(binding.currentModeImg, it.weather[0].icon)
        binding.currentCity.text=it.name
        binding.description.text=it.weather[0].description
        // TODO definde c or f from shared preferences
        binding.currentTemp.text=it.main.temp.toString()


    }


    private fun loadImage(imageView: ImageView, string: String){
        Glide.with(imageView)  //2
            .load("http://openweathermap.org/img/wn/"+string+"@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }


}