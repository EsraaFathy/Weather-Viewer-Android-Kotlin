package com.example.kotlinproject.baseHome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.ui.favourit.Favourit
import com.example.kotlinproject.ui.home.Home
import com.example.kotlinproject.ui.setting.Setting


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)


        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId==R.id.homeItem_menu){
                fragment= Home()
                supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit()
            }else if (it.itemId==R.id.favorite_menu){
                fragment= Favourit()
                supportFragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit()
            }else {
                fragment = Setting()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }
            return@setOnNavigationItemSelectedListener true

        }

        }


    }
