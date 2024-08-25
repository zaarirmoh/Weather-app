package com.example.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val description: String,
    val code: Int,
    val icon: String
)
