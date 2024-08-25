package com.example.weatherapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.ui.screens.homeScreen.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                weatherApplication().container.weatherRepository
            )
        }
    }
}

fun CreationExtras.weatherApplication(): WeatherApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WeatherApplication)