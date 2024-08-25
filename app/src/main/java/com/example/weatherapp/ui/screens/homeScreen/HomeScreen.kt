package com.example.weatherapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hourlyForecastUiState: HourlyForecastUiState,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            when (hourlyForecastUiState) {
                is HourlyForecastUiState.Loading -> HomeScreenLoading()
                is HourlyForecastUiState.Error -> HomeScreenError()
                is HourlyForecastUiState.Success -> HomeScreenSuccess()
            }
        }
    }
}

@Composable
fun HomeScreenLoading() {

}

@Composable
fun HomeScreenError() {

}

@Composable
fun HomeScreenSuccess() {

}