package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.databinding.FragmentHomeBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == LocationHanding.LOCATION_PERMISSION_REQUEST_CODE) {
            Log.d("TAG","LOCATION_PERMISSION_REQUEST_CODE $requestCode")
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
//        getFromLocal()
        homeViewModel.loadDate()
        homeViewModel.loadTime()
//
        homeViewModel.getCurrentLocalStatue().observe(this, androidx.lifecycle.Observer {
            if (it == true) {
                getFromLocal()

            }
        })
        homeViewModel.gettingLocation().observe(this, androidx.lifecycle.Observer {
            loadOnline(it.latitude,it.longitude)
        })
        homeViewModel.getDate().observe(this, {
            binding.currentDate.text = it
        })
        homeViewModel.getTime().observe(this, {
            binding.currentTime.text = it

        })
        homeViewModel.getProgress().observe(this, {
            binding.progressHome.visibility = it
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadOnline(latitude: Double, longitude: Double) {
        homeViewModel.loadOnlineData(latitude.toString(), longitude.toString(), "en", "517a14f849e519bb4fa84cdbd4755f56", "minutely", "standard").observe(
            this, {
                initUI(it)

                homeViewModel.saveCurentToLocal(it.current,it.timezone)
            })
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFromLocal() {
        homeViewModel.getCurrentLocal().observe(this, androidx.lifecycle.Observer {
            initUI(it)
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: AllData) {
        loadImage(binding.currentModeImg, it.current.weather[0].icon)
        binding.currentCity.text = it.timezone
        binding.description.text = it.current.weather[0].description
        // TODO definde c or f from shared preferences
        binding.currentTemp.text = it.current.temp.toString()
        binding.humidityPercentage.text = it.current.humidity.toString()
        binding.windSpeedPercentage.text = it.current.wind_speed.toString()
        binding.pressurePercentage.text = it.current.pressure.toString()
        binding.cloudsPercentage.text = it.current.clouds.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: Current) {
//        loadImage(binding.currentModeImg, it.current.weather[0].icon)
        binding.currentCity.text = it.weather[0].main
        binding.description.text = it.weather[0].description
        // TODO definde c or f from shared preferences
        binding.currentTemp.text = String.format("%.2f", it.temp)

        binding.humidityPercentage.text = it.humidity.toString()
        binding.windSpeedPercentage.text = String.format("%.2f", it.wind_speed)
        binding.pressurePercentage.text = it.pressure.toString()
        binding.cloudsPercentage.text = it.clouds.toString()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("http://openweathermap.org/img/wn/" + string + "@2x.png") //3
            .centerCrop() //4
            .into(imageView)

        val s = java.time.format.DateTimeFormatter.ISO_INSTANT.format(
            java.time.Instant.ofEpochSecond(
                1532358895
            )
        )

        val dtStart = s
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            val date: Date = format.parse(dtStart)
            System.out.println(date)
            Toast.makeText(activity, date.toString(), Toast.LENGTH_SHORT).show()

        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

}