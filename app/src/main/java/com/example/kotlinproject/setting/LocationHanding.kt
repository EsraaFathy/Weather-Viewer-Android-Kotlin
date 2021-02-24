package com.example.kotlinproject.setting

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationHanding(val context: Context) {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    private var locationLiveData : MutableLiveData<Location> = MutableLiveData<Location>()
    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
        context
    )

    @SuppressLint("MissingPermission")
    fun loadLocation(){
        if (verifyLocationEnabled()){
            if (chickPermition()){
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location!=null)
                        locationLiveData.value=location
                       // Toast.makeText(context,location.toString(),Toast.LENGTH_SHORT).show()
                    }
            }else{
                requestPremition()
            }
        }else {
            enableLocationSitting()
        }
    }

    private fun enableLocationSitting() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }

    private fun chickPermition(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun verifyLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun requestPremition() {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    fun getLocatin() : LiveData<Location>{
        return locationLiveData
    }


}