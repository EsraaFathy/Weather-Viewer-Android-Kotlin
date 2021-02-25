package com.example.kotlinproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlinproject.dataLayer.model.currentModel.ModelCurent
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
        if (requestCode == LocationHanding.LOCATION_PERMISSION_REQUEST_CODE) {
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
        homeViewModel.loadDate()
        homeViewModel.loadTime()
        homeViewModel.loadOnlineData(
            "30.590426",
            "31.502410",
            "en",
            "517a14f849e519bb4fa84cdbd4755f56"
        ).observe(this, {
            initUI(it)
        })
        homeViewModel.getFromLocalDataBase().observe(this, androidx.lifecycle.Observer {
            if (it == true){
                //  todo get from local

            }
        })
        homeViewModel.gettingLocation().observe(this, androidx.lifecycle.Observer {
            Toast.makeText(activity, it.latitude.toString(), Toast.LENGTH_SHORT).show()
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
    private fun initUI(it: ModelCurent) {
        loadImage(binding.currentModeImg, it.weather[0].icon)
        binding.currentCity.text = it.name
        binding.description.text = it.weather[0].description
        // TODO definde c or f from shared preferences
        binding.currentTemp.text = it.main.temp.toString()
        binding.humidityPercentage.text = it.main.humidity.toString()
        binding.windSpeedPercentage.text = it.wind.speed.toString()
        binding.pressurePercentage.text = it.clouds.all.toString()


    }


    @SuppressLint("SimpleDateFormat")
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