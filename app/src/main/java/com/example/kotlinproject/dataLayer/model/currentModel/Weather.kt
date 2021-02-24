package com.example.kotlinproject.dataLayer.model.currentModel

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)