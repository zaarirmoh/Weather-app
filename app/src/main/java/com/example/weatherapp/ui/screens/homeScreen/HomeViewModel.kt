package com.example.weatherapp.ui.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.CurrentWeather
import com.example.weatherapp.model.DailyWeather
import com.example.weatherapp.model.HourlyWeather
import kotlinx.coroutines.launch

sealed interface HourlyForecastUiState {
    data class Success(val hourlyWeather: HourlyWeather): HourlyForecastUiState
    object Error: HourlyForecastUiState
    object Loading: HourlyForecastUiState
}

sealed interface DailyForecastUiState {
    data class Success(val dailyWeather: DailyWeather): DailyForecastUiState
    object Error: DailyForecastUiState
    object Loading: DailyForecastUiState
}

sealed interface CurrentWeatherUiState {
    data class Success(val currentWeather: CurrentWeather): CurrentWeatherUiState
    object Error: CurrentWeatherUiState
    object Loading: CurrentWeatherUiState
}

class HomeViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    var hourlyForecastUiState: HourlyForecastUiState by mutableStateOf(HourlyForecastUiState.Loading)
        private set

    var dailyForecastUiState: DailyForecastUiState by mutableStateOf(DailyForecastUiState.Loading)
        private set

    var currentWeatherUiState: CurrentWeatherUiState by mutableStateOf(CurrentWeatherUiState.Loading)
        private set

    fun getHourlyForecast(
        lat: Double,
        long: Double,
        key: String,
        hours: Int
    ){
      viewModelScope.launch {
          hourlyForecastUiState = HourlyForecastUiState.Loading
          hourlyForecastUiState = try {
              HourlyForecastUiState.Success(weatherRepository.getHourlyForecast(lat, long, key, hours))
          } catch (e: Exception) {
              HourlyForecastUiState.Error
          }
      }
    }

    fun getDailyForecast(
        lat: Double,
        long: Double,
        key: String,
        days: Int
    ){
        viewModelScope.launch {
            dailyForecastUiState = DailyForecastUiState.Loading
            dailyForecastUiState = try {
                DailyForecastUiState.Success(weatherRepository.getDailyForecast(lat, long, key, days))
            } catch (e: Exception) {
                DailyForecastUiState.Error
            }
        }
    }

    fun getCurrentWeather(
        lat: Double,
        long: Double,
        key: String
    ){
        viewModelScope.launch {
            currentWeatherUiState = CurrentWeatherUiState.Loading
            currentWeatherUiState = try {
                CurrentWeatherUiState.Success(weatherRepository.getCurrentWeather(lat, long, key))
            } catch (e: Exception) {
                CurrentWeatherUiState.Error
            }
        }
    }
}