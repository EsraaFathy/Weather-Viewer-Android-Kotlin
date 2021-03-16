package com.example.kotlinproject.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.R
import com.example.kotlinproject.ui.baseHome.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationHanding(val context: Context) {
    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1584
    }

    private var locationLiveData: MutableLiveData<Location> = MutableLiveData<Location>()
    private var loadLocal: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(
            context
        )

    @SuppressLint("MissingPermission")
    fun loadLocation(context: Context, activity: Activity) {
        if (verifyLocationEnabled()) {
            if (chickPermition()) {

                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null)
                            locationLiveData.value = location
                    }
            } else {
                requestPremition(activity)
                loadLocation(context, activity)
            }
        } else {
            enableLocationSitting(context, activity)
        }
    }

    @SuppressLint("MissingPermission")
    fun checkLocation(context: Context, activity: Activity) {
        if (verifyLocationEnabled()) {
            if (chickPermition()) {
            } else {
                requestPremition(activity)
            }
        } else {
            enableLocationSitting(context, activity)
        }
    }

    private fun enableLocationSitting(context: Context, activity: Activity) {
        Log.d("TAG","MainActivity.readFromDatabase  " + MainActivity.readFromDatabase.toString())
        if (!MainActivity.readFromDatabase) {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle(context.getString(R.string.location_not_enabel))
            alertDialogBuilder.setMessage(context.getString(R.string.to_load_condetions))
            alertDialogBuilder.setPositiveButton(context.getString(R.string.enabel)) { dialog, _ ->
                Log.d("TAG", "Enabel log")
                MainActivity.readFromDatabase = false
                dialog.dismiss()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(activity, intent, LOCATION_PERMISSION_REQUEST_CODE, Bundle())

            }
            alertDialogBuilder.setNegativeButton(context.getString(R.string.load_from_last_known_data)) { _, _ ->
                loadLocal.value = true
                MainActivity.readFromDatabase = true
            }
            Log.d("TAG", "Enabel show")
            alertDialogBuilder.show()
        }
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

    fun verifyLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun requestPremition(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    fun getLocatin(): LiveData<Location> {
        return locationLiveData
    }


}