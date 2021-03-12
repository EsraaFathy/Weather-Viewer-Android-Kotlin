package com.example.kotlinproject.ui.baseHome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.dataLayer.local.sharedprefrence.SettingModel
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.ui.alert.Alert
import com.example.kotlinproject.ui.favourit.Favourit
import com.example.kotlinproject.ui.home.Home
import com.example.kotlinproject.ui.setting.Setting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var mainViewModel: MainViewModel
    private lateinit var list: List<FavData>
    private lateinit var setting: SettingModel

    companion object {
        var units: String = "standard"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED && data != null) {
            Log.d("TAG", "LOCATION_PERMISSION_REQUEST_CODE111111 $requestCode")
            val fragment = supportFragmentManager.findFragmentByTag("HOME")
            fragment!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = MainViewModel(application)
        updateFavourite()
        fragment = Home()
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
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment)
                        .commit()
                }
            }
            return@setOnNavigationItemSelectedListener true

        }

    }

    private fun updateFavourite() {
        list = mainViewModel.getFavDataNotLiveData()
            mainViewModel.getSettnig().observe(this@MainActivity, {
                setting = it
                reDowenloadData(list)
            })
    }

    private fun reDowenloadData(list: List<FavData>) {
        CoroutineScope(Dispatchers.IO).launch {
//            mainViewModel.deleteAllFav()
            for (i in list) {
                mainViewModel.saveFav(
                    i.lat.toString(),
                    i.lon.toString(),
                    setting.lang,
                    setting.units
                )
            }
        }
    }


}
