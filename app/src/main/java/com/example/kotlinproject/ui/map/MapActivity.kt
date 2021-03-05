package com.example.kotlinproject.ui.map


import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapActivityViewMode: MapActivityViewMode
    private lateinit var latLng: LatLng
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        type=intent.getStringExtra("type").toString()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        mapActivityViewMode= MapActivityViewMode(this)
        mapActivityViewMode= ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(
                application
            )
        )[MapActivityViewMode::class.java]


        mapActivityViewMode.saveLatLng.observe(this, {
            if (it) {
                mapActivityViewMode.saveLocationSetting(latLng)
                mapActivityViewMode.saveLatLng.value = false

            }
        })

        mapActivityViewMode.saveFav.observe(this, { it ->
            if (it) {
                mapActivityViewMode.getSettnig().observe(this, {
                    mapActivityViewMode.saveFav(
                        latLng.latitude.toString(),
                        latLng.longitude.toString(),
                        it.lang,
                        it.units
                    )
                })
                mapActivityViewMode.saveFav.value = false

            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.setOnMapClickListener {
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(it))
            Log.d("TAG", "${it.latitude}.....${it.longitude}")
            latLng=it
            if (type=="setting"){
                mapActivityViewMode.showLocationSavingAlarm(this)
            }else {
                mapActivityViewMode.showAlarm(this)
            }
            Log.d("TAG", "${latLng.latitude}.....${type}")
            Log.d("TAG type", "")
        }

    }

}