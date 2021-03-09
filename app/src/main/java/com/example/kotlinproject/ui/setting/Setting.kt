package com.example.kotlinproject.ui.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.databinding.FragmentSettingBinding
import com.example.kotlinproject.ui.favouriteDetails.FavouriteDetails
import com.example.kotlinproject.ui.map.MapActivity
import com.example.kotlinproject.ui.map.MapActivityViewMode


class Setting : Fragment() {
    lateinit var binding: FragmentSettingBinding
    lateinit var settingViewModel: SettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        settingViewModel =  ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            requireActivity().application
        ))[SettingViewModel::class.java]


        binding.saveButton.setOnClickListener{
            savedata()
        }

        binding.addLocationRadioButton.setOnClickListener{
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra("type","setting")
            startActivity(intent)
        }
        getSetting()

        return binding.root
    }

    fun getSetting() {
        settingViewModel.getSetting().observe(this, {
            val units: String = it.units
            val lang: String = it.lang
            val location: String = it.location
            Log.d("TAG",it.location)
            if (units == "standard") {
                binding.unitsRadioGroup.check(R.id.standardRadioButton)
            } else if (units == "imperial") {
                binding.unitsRadioGroup.check(R.id.imperialRadioButton)
            } else {
                binding.unitsRadioGroup.check(R.id.metricRadioButton)
            }

            if (lang == "en") {
                binding.langRadioGroup.check(R.id.EnglishRadioButton)
            } else {
                binding.langRadioGroup.check(R.id.ArabicRadioButton)
            }

            if (location == "add") {
                binding.locationRadioGroup.check(R.id.addLocationRadioButton)
            } else {
                binding.locationRadioGroup.check(R.id.gpsRadioButton)
            }
        })
    }

    private fun savedata() {
        val units: String = when (binding.unitsRadioGroup.checkedRadioButtonId) {
            R.id.standardRadioButton -> {
                "standard"
            }
            R.id.imperialRadioButton -> {
                "imperial"
            }
            else -> {
                "metric"
            }
        }


        val lang: String = if (binding.langRadioGroup.checkedRadioButtonId == R.id.EnglishRadioButton) {
            "en"
        } else {
            "ar"
        }


        val location: String = if (binding.locationRadioGroup.checkedRadioButtonId == R.id.gpsRadioButton) {
            "gps"
        } else {
            "add"
        }

        settingViewModel.setLocale(requireActivity(),lang)
        settingViewModel.setSetting(SettingModel(units, lang, location))
    }


}