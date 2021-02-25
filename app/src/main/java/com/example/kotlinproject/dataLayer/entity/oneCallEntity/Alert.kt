package com.example.kotlinproject.dataLayer.entity.oneCallEntity

data class Alert(
    val description: String,
    val end: Int,
    val event: String,
    val sender_name: String,
    val start: Int
)