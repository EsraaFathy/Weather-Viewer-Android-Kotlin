package com.example.kotlinproject.dataLayer.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)