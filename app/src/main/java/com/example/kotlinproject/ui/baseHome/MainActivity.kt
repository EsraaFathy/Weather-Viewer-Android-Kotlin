package com.example.kotlinproject.ui.baseHome

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.ui.alert.Alert
import com.example.kotlinproject.ui.favourit.Favourit
import com.example.kotlinproject.ui.home.Home
import com.example.kotlinproject.ui.setting.Setting
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
companion object{
    var units:String="standard"
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != RESULT_CANCELED&&data!=null){
            Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE111111 $requestCode")
            val fragment = supportFragmentManager.findFragmentByTag("HOME")
            fragment!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fragment= Home()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment, "HOME").commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem_menu -> {
                    fragment = Home()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment)
                        .commit()
                }
                R.id.favorite_menu -> {
                    fragment = Favourit()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment)
                        .commit()
                }
                R.id.alert_menu -> {
                    fragment = Alert()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment)
                        .commit()
                }
                else -> {
                    fragment = Setting()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true

        }

        }
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        baseContext.resources.updateConfiguration(newConfig, baseContext.resources.displayMetrics)
//        setContentView(R.layout.activity_main)
////        setTitle(R.string.app_name)
//        // Checks the active language
//        if (newConfig.locale === Locale.ENGLISH) {
//            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "French", Toast.LENGTH_SHORT).show()
//        }
//    }


    }
