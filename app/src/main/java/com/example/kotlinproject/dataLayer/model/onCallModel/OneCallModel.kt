package com.example.kotlinproject.dataLayer.model.onCallModel

data class OneCallModel(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)