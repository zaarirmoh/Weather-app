package com.example.weatherapp.ui.navigation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.ui.AppViewModelProvider
import com.example.weatherapp.ui.screens.homeScreen.HomeScreen
import com.example.weatherapp.ui.screens.homeScreen.HomeViewModel
import com.google.android.gms.location.LocationServices

@Composable
fun NavigationSystem(
    navController: NavHostController = rememberNavController(),
    activity: Activity
){
    NavHost(
        navController = navController,
        startDestination = HomeScreenN
    ) {
        composable<HomeScreenN>{
            val weatherbitApiKey = stringResource(id = R.string.weatherbitApiKey)
            val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            val permissionsToRequest = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            val context = LocalContext.current
            val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
            val locationResult = remember { mutableStateOf<Location?>(null) }
            val coroutineScope = rememberCoroutineScope()
            val isPermissionGranted = remember {
                mutableStateOf(
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                )
            }
            LaunchedEffect(Unit) {
                if (isPermissionGranted.value) {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            locationResult.value = location
                            Log.d("location",location?.latitude.toString())
                            Log.d("location",location?.longitude.toString())
                        }
                }
            }
            LaunchedEffect(locationResult.value == null) {
                if (locationResult.value != null) {
                    viewModel.getHourlyForecast(
                        lat = locationResult.value!!.latitude,
                        long = locationResult.value!!.longitude,
                        key = weatherbitApiKey,
                        hours = 24
                    )
                    viewModel.getDailyForecast(
                        lat = locationResult.value!!.latitude,
                        long = locationResult.value!!.longitude,
                        key = weatherbitApiKey,
                        days = 7
                    )
                }
            }
            HomeScreen(
                hourlyForecastUiState = viewModel.hourlyForecastUiState,
                dailyForecastUiState = viewModel.dailyForecastUiState,
                permissionsToRequest = permissionsToRequest,
                activity = activity,
                location = locationResult.value,
                isPermissionsGranted = isPermissionGranted,
                retryAction = {
                    viewModel.getHourlyForecast(
                        lat = locationResult.value!!.latitude,
                        long = locationResult.value!!.longitude,
                        key = weatherbitApiKey,
                        hours = 24
                    )
                    viewModel.getDailyForecast(
                        lat = locationResult.value!!.latitude,
                        long = locationResult.value!!.longitude,
                        key = weatherbitApiKey,
                        days = 7
                    )
                }
            )
        }
    }
}