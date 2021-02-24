package com.example.kotlinproject.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.kotlinproject.R
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

        settingViewModel.gettingLocation().observe(this, Observer {
            Toast.makeText(activity,it.latitude.toString(),Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}