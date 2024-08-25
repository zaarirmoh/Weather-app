package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeather(
    @SerialName("city_name")
    val cityName: String,
    @SerialName("country_code")
    val countryCode: String,
    val data: List<DailyData>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
)

@Serializable
data class DailyData(
    @SerialName("app_max_temp")
    val appMaxTemp: Double,
    @SerialName("app_min_temp")
    val appMinTemp: Double,
    @SerialName("max_temp")
    val maxTemp: Double,
    @SerialName("min_temp")
    val minTemp: Double,
    val temp: Double,
    val datetime: String,
    val weather: Weather,
)


