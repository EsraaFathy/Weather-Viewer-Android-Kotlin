package com.example.kotlinproject.dataLayer.entity.oneCallEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity
data class AllData(
    val alerts: List<Alert>?,
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    @PrimaryKey
    val timezone: String,
    val timezone_offset: Int
    )