package com.example.kotlinproject.dataLayer.entity.oneCallEntity

import androidx.room.Entity

@Entity(primaryKeys = arrayOf("lat","lon","timezone"))
data class AllData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: Current,
    val hourly: List<Hourly>,
    val daily: List<Daily>,
    val alerts: List<Alert>)