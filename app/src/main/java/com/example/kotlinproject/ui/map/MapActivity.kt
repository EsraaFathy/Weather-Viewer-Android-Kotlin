package com.example.kotlinproject.ui.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlinproject.R
import com.example.kotlinproject.ui.home.LocationHanding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions




class MapActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerDragListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mapActivityViewMode: MapActivityViewMode
    private lateinit var latLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mapActivityViewMode= MapActivityViewMode(this)

        mapActivityViewMode.save.observe(this,{
            if (it){
                mapActivityViewMode.getSettnig().observe(this,{
                    mapActivityViewMode.saveFav(latLng.latitude.toString(),latLng.longitude.toString(), it.lang,it.units)
                })
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener {
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(it))
            Log.d("TAG", "${it.latitude}.....${it.longitude}")
            latLng=it
            mapActivityViewMode.showAlarm()
        }

    }



    override fun onMarkerDragStart(p0: Marker?) {
        TODO("Not yet implemented")
    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMarkerDragEnd(p0: Marker?) {
        latLng = p0!!.getPosition()
            mapActivityViewMode.showAlarm()
        Log.d("TAG", "${latLng.latitude}.....${latLng.longitude}")

    }
}