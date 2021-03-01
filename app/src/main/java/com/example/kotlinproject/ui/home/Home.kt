package com.example.kotlinproject.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly
import com.example.kotlinproject.databinding.FragmentHomeBinding


class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var adapter:HourlyAdabter
    lateinit var dailyadapter:DailyAdapter
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
        homeViewModel = HomeViewModel(activity!!)
         adapter=HourlyAdabter(activity!!)
        dailyadapter= DailyAdapter(activity!!)
        relad()

        binding.cureentCard.setOnClickListener {
            binding.cureentCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.broun)
            binding.daialyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.hoourlyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.currentList.visibility=View.VISIBLE
            binding.hourlyList.visibility=View.GONE
            binding.dialyList.visibility=View.GONE
            binding.currentext.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.white))
            binding.dailyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
            binding.hourlyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
        }

        binding.hoourlyCard.setOnClickListener {
            binding.hoourlyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.broun)
            binding.cureentCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.daialyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.hourlyList.visibility=View.VISIBLE
            binding.dialyList.visibility=View.GONE
            binding.currentList.visibility=View.GONE
            binding.hourlyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.white))
            binding.currentext.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
            binding.dailyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
        }

        binding.daialyCard.setOnClickListener {
            binding.daialyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.broun)
            binding.hoourlyCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.cureentCard.backgroundTintList= ContextCompat.getColorStateList(activity!!, R.color.white)
            binding.dialyList.visibility=View.VISIBLE
            binding.hourlyList.visibility=View.GONE
            binding.currentList.visibility=View.GONE
            binding.dailyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.white))
            binding.hourlyText.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
            binding.currentext.setTextColor(ContextCompat.getColorStateList(activity!!, R.color.broun))
        }
        binding.reload.setOnClickListener {
            relad()
        }
        homeViewModel.getRoomData().observe(this, {
            if (it.size==1) {
                initUI(it[0])
                loadHourly(it[0].hourly)
                loadDaily(it[0].daily)
            }
        })
        return binding.root
    }



    override fun onStart() {
        super.onStart()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: AllData) {
        loadImage(binding.currentModeImg, it.current.weather[0].icon)
        Log.d("TAG", "icon ${it.current.weather[0].icon}")
        binding.currentCity.text = it.timezone
        binding.description.text = it.current.weather[0].description
        binding.currentTemp.text = it.current.temp.toString()
        binding.humidityPercentage.text = it.current.humidity.toString()
        binding.windSpeedPercentage.text = it.current.wind_speed.toString()
        binding.pressurePercentage.text = it.current.pressure.toString()
        binding.cloudsPercentage.text = it.current.clouds.toString()
        binding.currentTime.text = homeViewModel.formateTime(it.current.dt)
        binding.currentDate.text = homeViewModel.formateDate(it.current.dt)
        binding.sunrisetime.text = homeViewModel.formateTime(it.current.sunrise)
        binding.sunsetdate.text = homeViewModel.formateTime(it.current.sunset)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadImage(imageView: ImageView, string: String) {
        Glide.with(imageView)  //2
            .load("https://openweathermap.org/img/wn/$string@2x.png") //3
            .centerCrop() //4
            .into(imageView)
    }

    private fun relad() {
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
//                homeViewModel.loadRoomData()
            })
        })
    }

    fun loadHourly(hourlyList :List<Hourly>){
        val lay : RecyclerView.LayoutManager= LinearLayoutManager(activity)
        binding.hourlyList.layoutManager=lay
        adapter.models=hourlyList
        binding.hourlyList.adapter=adapter
    }
    fun loadDaily(dailyList :List<Daily>){
        val lay : RecyclerView.LayoutManager= LinearLayoutManager(activity)
        binding.dialyList.layoutManager=lay
        dailyadapter.models=dailyList
        binding.dialyList.adapter=dailyadapter
    }

}