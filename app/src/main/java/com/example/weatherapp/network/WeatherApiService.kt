package com.example.weatherapp.network

import com.example.weatherapp.model.CurrentWeather
import com.example.weatherapp.model.DailyWeather
import com.example.weatherapp.model.HourlyWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String
    ): CurrentWeather

    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String,
        @Query("days") days: Int
    ): DailyWeather

    @GET("forecast/hourly")
    suspend fun getHourlyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String,
        @Query("hours") hours: Int
    ): HourlyWeather

}