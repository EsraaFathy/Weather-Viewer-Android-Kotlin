package com.example.kotlinproject.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.databinding.FragmentSettingBinding


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
        settingViewModel= SettingViewModel(activity!!)

        binding.saveButton.setOnClickListener(View.OnClickListener {
        savedata()
        })

        return binding.root
    }

    private fun savedata() {
        lateinit var units:String
        lateinit var lang:String
        if (binding.unitsRadioGroup.checkedRadioButtonId.equals(R.id.standardRadioButton)){
            units="standard"
        }else if (binding.unitsRadioGroup.checkedRadioButtonId.equals(R.id.imperialRadioButton)){
            units="imperial"
        }else{
            units="metric"
        }



        if (binding.langRadioGroup.checkedRadioButtonId.equals(R.id.EnglishRadioButton)){
            lang="en"
        }else{
            lang="ar"
        }
        settingViewModel.setSetting(SettingModel(units,lang))
    }



}