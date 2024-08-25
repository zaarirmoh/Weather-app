package com.example.weatherapp.data

import com.example.weatherapp.model.CurrentWeather
import com.example.weatherapp.model.DailyWeather
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.network.WeatherApiService

interface WeatherRepository{

    suspend fun getCurrentWeather(
        lat:Double,
        lon:Double,
        key:String
    ): CurrentWeather

    suspend fun getDailyForecast(
        lat:Double,
        lon:Double,
        key:String,
        days:Int
    ): DailyWeather

    suspend fun getHourlyForecast(
        lat:Double,
        lon:Double,
        key:String,
        hours:Int
    ): HourlyWeather

}

class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService
): WeatherRepository{

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        key: String
    ): CurrentWeather = weatherApiService.getCurrentWeather(
        latitude = lat,
        longitude = lon,
        apiKey = key
    )

    override suspend fun getDailyForecast(
        lat: Double,
        lon: Double,
        key: String,
        days: Int
    ): DailyWeather = weatherApiService.getDailyForecast(
        latitude = lat,
        longitude = lon,
        apiKey = key,
        days = days
    )

    override suspend fun getHourlyForecast(
        lat: Double,
        lon: Double,
        key: String,
        hours: Int
    ): HourlyWeather = weatherApiService.getHourlyForecast(
        latitude = lat,
        longitude = lon,
        apiKey = key,
        hours = hours
    )

}