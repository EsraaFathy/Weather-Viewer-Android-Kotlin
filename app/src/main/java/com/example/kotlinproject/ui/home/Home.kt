package com.example.kotlinproject.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.databinding.FragmentHomeBinding


class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == LocationHanding.LOCATION_PERMISSION_REQUEST_CODE) {
            Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE $requestCode")
            homeViewModel.gettingLocation()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        observ()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observ() {
        homeViewModel = HomeViewModel(activity!!)


//https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=minutely&lang=ar&units=standard&appid=517a14f849e519bb4fa84cdbd4755f56

        homeViewModel.gettingLocation().observe(this, androidx.lifecycle.Observer {
            val location = it
            Log.d("TAG", "it.lang" + location.latitude)
            homeViewModel.getSetting().observe(this, {
                Log.d("TAG", "it.lang" + it.lang)
                homeViewModel.loadOnlineData(
                    location.latitude.toString(),
                    location.longitude.toString(),
                    it.lang,
                    "517a14f849e519bb4fa84cdbd4755f56",
                    "minutely",
                    it.units
                )
            })
        })




//        homeViewModel.getRoomData().observe(this,{
//            initUI(it[0])
//        })

        homeViewModel.getRoomData().observe(this,{
            Log.d("TAG",it.current.humidity.toString())
            initUI(it)
        })

    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getRoomData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: AllData) {
        loadImage(binding.currentModeImg, it.current.weather[0].icon)
        Log.d("TAG","icon ${it.current.weather[0].icon}")
        binding.currentCity.text = it.timezone
        binding.description.text = it.current.weather[0].description
        binding.currentTemp.text = it.current.temp.toString()
        binding.humidityPercentage.text = it.current.humidity.toString()
        binding.windSpeedPercentage.text = it.current.wind_speed.toString()
        binding.pressurePercentage.text = it.current.pressure.toString()
        binding.cloudsPercentage.text = it.current.clouds.toString()
        binding.currentTime.text= homeViewModel.formateTime(it.current.dt)
        binding.currentDate.text= homeViewModel.formateDate(it.current.dt)
        binding.sunrisetime.text=homeViewModel.formateTime(it.current.sunrise)
        binding.sunsetdate.text=homeViewModel.formateTime(it.current.sunset)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }

}