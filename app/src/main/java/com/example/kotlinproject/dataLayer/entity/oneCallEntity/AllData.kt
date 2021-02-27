package com.example.kotlinproject.dataLayer.entity.oneCallEntity

import androidx.room.Entity


@Entity(primaryKeys = arrayOf("timezone"))
data class AllData(
    val alerts: List<Alert>,
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
    )