package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.AppViewModelProvider
import com.example.weatherapp.ui.screens.homeScreen.HomeScreen
import com.example.weatherapp.ui.screens.homeScreen.HomeViewModel

@Composable
fun NavigationSystem(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = HomeScreenN
    ) {
        composable<HomeScreenN>{
            val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            HomeScreen(
                hourlyForecastUiState = viewModel.hourlyForecastUiState
            )
        }
    }
}