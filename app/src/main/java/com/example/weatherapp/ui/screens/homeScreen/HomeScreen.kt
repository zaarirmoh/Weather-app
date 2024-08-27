package com.example.weatherapp.ui.screens.homeScreen

import android.app.Activity
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.model.DailyData
import com.example.weatherapp.model.HourlyData
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.openAppSettings
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hourlyForecastUiState: HourlyForecastUiState,
    dailyForecastUiState: DailyForecastUiState,
    permissionsToRequest: Array<String>,
    isPermissionsGranted: MutableState<Boolean>,
    activity: Activity,
    location: Location?,
    retryAction: () -> Unit,
) {
    var isPermissionGranted: Boolean by remember {
        mutableStateOf(true)
    }
    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            isPermissionGranted = false
            perms.forEach { 
                if (it.value) {
                    isPermissionGranted = true
                    isPermissionsGranted.value = true
                    return@forEach
                }
            }
        }
    )
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        locationPermissionResultLauncher.launch(permissionsToRequest)
        if (isPermissionGranted){
            when (hourlyForecastUiState) {
                is HourlyForecastUiState.Loading -> HomeScreenLoading(
                    paddingValues = paddingValues
                )
                is HourlyForecastUiState.Error -> HomeScreenError(
                    paddingValues = paddingValues,
                    retryAction = retryAction
                )
                is HourlyForecastUiState.Success -> HomeScreenSuccess(
                    paddingValues = paddingValues,
                    hourlyWeather = hourlyForecastUiState.hourlyWeather,
                    dailyForecastUiState = dailyForecastUiState
                )
            }
        }else{
            AlertDialog(
                onDismissRequest = {  },
                confirmButton = {
                    TextButton(onClick = { activity.openAppSettings() }) {
                        Text(text = "Open settings")
                    }
                },
                title = {
                    Text(text = "App needs location permission to work, you need to allow the permission from the settings")
                }
            )
        }
    }
}