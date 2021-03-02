package com.example.kotlinproject.ui.favouriteDetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinproject.R

class FavouriteDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_details)

        val intent: Intent = intent
        Log.d("TAG","${intent.getStringExtra("ID")}")
    }
}