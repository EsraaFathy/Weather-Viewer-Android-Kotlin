package com.example.kotlinproject.ui.baseHome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.ui.alert.Alert
import com.example.kotlinproject.ui.favourit.Favourit
import com.example.kotlinproject.ui.home.Home
import com.example.kotlinproject.ui.setting.Setting


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fragment: Fragment



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE111111 $requestCode")
        val fragment = supportFragmentManager.findFragmentByTag("HOME")
        fragment!!.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fragment= Home()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment,"HOME").commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId==R.id.homeItem_menu){
                fragment= Home()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }else if (it.itemId==R.id.favorite_menu){
                fragment= Favourit()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }else if (it.itemId==R.id.alert_menu){
                fragment= Alert()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }else {
                fragment = Setting()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
            }
            return@setOnNavigationItemSelectedListener true

        }

        }


    }
