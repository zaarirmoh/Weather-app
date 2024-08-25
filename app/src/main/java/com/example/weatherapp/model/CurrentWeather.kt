package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val count: Int,
    val data: CurrentWeatherDate
)

@Serializable
data class CurrentWeatherDate(
    @SerialName("temp")
    val temperature: Double,
    @SerialName("app_time")
    val apparentTemperature: Double,
    @SerialName("city_name")
    val cityName: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("datetime")
    val dateTime: String,
    val lat: Double,
    val lon: Double,
    val sunrise: String,
    val sunset: String,
    val timezone: String,
    val weather: Weather,
)