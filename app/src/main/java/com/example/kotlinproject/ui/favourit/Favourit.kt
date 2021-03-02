package com.example.kotlinproject.ui.favourit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentFavouritBinding
import com.example.kotlinproject.databinding.FragmentSettingBinding
import com.example.kotlinproject.ui.map.MapActivity


class Favourit : Fragment() {

    lateinit var binding :FragmentFavouritBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener{
            val intent = Intent(activity,MapActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}