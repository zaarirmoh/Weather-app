package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeather(
    @SerialName("city_name")
    val cityName: String,
    @SerialName("country_code")
    val countryCode: String,
    val data: List<HourlyData>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
)

@Serializable
data class HourlyData(
    @SerialName("temp")
    val temperature: Double,
    @SerialName("app_temp")
    val apparentTemperature: Double,
    val datetime: String,
    val weather: Weather,
)
