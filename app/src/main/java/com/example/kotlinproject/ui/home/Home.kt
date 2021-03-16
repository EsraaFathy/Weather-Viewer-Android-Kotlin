package com.example.kotlinproject.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Alert
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.ui.baseHome.MainActivity
import com.example.kotlinproject.ui.NotificationHelper


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HourlyAdabter
    private lateinit var dailyadapter: DailyAdapter
    private lateinit var notificationHelper: NotificationHelper

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE lll$requestCode")
//
//        if (requestCode == LocationHanding.LOCATION_PERMISSION_REQUEST_CODE) {
//            Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE $requestCode")
//            //homeViewModel.gettingLocation(activity!!,requireActivity())
//          //  relad()
//        }
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        Log.d("TAG", "onRequestPermissionsResult $requestCode")
//
//        if (requestCode == LocationHanding.LOCATION_PERMISSION_REQUEST_CODE) {
//            Log.d("TAG", "onRequestPermissionsResult $requestCode")
//           // homeViewModel.gettingLocation(activity!!,requireActivity())
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[HomeViewModel::class.java]
        relad()
        adapter = HourlyAdabter(homeViewModel)
        dailyadapter = DailyAdapter(homeViewModel)


        binding.cureentCard.setOnClickListener {
            binding.cureentCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.broun
            )
            binding.daialyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.hoourlyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.currentList.visibility = View.VISIBLE
            binding.hourlyList.visibility = View.GONE
            binding.dialyList.visibility = View.GONE
            binding.currentext.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.white
                )
            )
            binding.dailyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
            binding.hourlyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
        }

        binding.hoourlyCard.setOnClickListener {
            binding.hoourlyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.broun
            )
            binding.cureentCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.daialyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.hourlyList.visibility = View.VISIBLE
            binding.dialyList.visibility = View.GONE
            binding.currentList.visibility = View.GONE
            binding.hourlyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.white
                )
            )
            binding.currentext.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
            binding.dailyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
        }

        binding.daialyCard.setOnClickListener {
            binding.daialyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.broun
            )
            binding.hoourlyCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.cureentCard.backgroundTintList = ContextCompat.getColorStateList(
                activity!!,
                R.color.white
            )
            binding.dialyList.visibility = View.VISIBLE
            binding.hourlyList.visibility = View.GONE
            binding.currentList.visibility = View.GONE
            binding.dailyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.white
                )
            )
            binding.hourlyText.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
            binding.currentext.setTextColor(
                ContextCompat.getColorStateList(
                    activity!!,
                    R.color.broun
                )
            )
        }

        binding.reload.setOnClickListener {
            Log.d("TAG","clicked")
            relad()
        }
        homeViewModel.getRoomData().observe(this, {
            if (it.size == 1) {
                initUI(it[0])
                loadHourly(it[0].hourly)
                loadDaily(it[0].daily)
                homeViewModel.loadImage(binding.currentModeImg, it[0].current.weather[0].icon)
                loadAlert(it[0].alerts)
            }
        })
        return binding.root
    }

    private fun loadAlert(alerts: List<Alert>?) {
        if (alerts!=null){
            homeViewModel.getAlertFromSetting().observe(viewLifecycleOwner,{
                if (it == "ON"){
                    Log.d("TAG","alert not null")
                    notificationHelper = NotificationHelper(context!!)
                    val notificationBuilder = notificationHelper.getChanelNotification(
                        getString(R.string.weather_alert),
                        "Take care he Weather is ${alerts[0].event}"
                    )
                    notificationHelper.getManger()!!.notify(1000, notificationBuilder.build())
                }
            })

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUI(it: AllData) {
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

    private fun loadHourly(hourlyList: List<Hourly>) {
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.hourlyList.layoutManager = lay
        adapter.models = hourlyList
        binding.hourlyList.adapter = adapter
    }

    private fun loadDaily(dailyList: List<Daily>) {
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.dialyList.layoutManager = lay
        dailyadapter.models = dailyList
        binding.dialyList.adapter = dailyadapter
    }


    private fun relad() {
        Log.d("TAG", "reload")

        lateinit var settingModel: SettingModel
        homeViewModel.getSetting().observe(this, { it ->
            Log.d("TAG", "it.        lang" + it.lang)
            settingModel = it
            MainActivity.units=settingModel.units
            binding.currentTempUnic.text=homeViewModel.getUnites(settingModel.units)


            if (settingModel.location == "gps") {
                homeViewModel.gettingLocation(this.requireContext(), activity!!).observe(this, {
                    val location = it
                    Log.d("TAG", "it.location" + location.latitude)
                    homeViewModel.loadOnlineData(
                        location.latitude.toString(),
                        location.longitude.toString(),
                        settingModel.lang,
                        settingModel.units,
                        activity!!
                    )
                })
            } else {
                homeViewModel.getLocationSettnig().observe(this, {
                    Log.d("TAG", "it.latitude" + it.latitude)
                    Log.d("TAG", "it.latitude" + it.longitude)

                    homeViewModel.loadOnlineData(
                        it.latitude.toString(),
                        it.longitude.toString(),
                        settingModel.lang,
                        settingModel.units,
                        activity!!
                    )
                })

            }
        })
    }


}