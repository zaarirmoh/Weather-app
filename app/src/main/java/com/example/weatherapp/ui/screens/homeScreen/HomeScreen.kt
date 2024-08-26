package com.example.weatherapp.ui.screens.homeScreen

import android.app.Activity
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.openAppSettings

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hourlyForecastUiState: HourlyForecastUiState,
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
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            locationPermissionResultLauncher.launch(permissionsToRequest)
            if (isPermissionGranted){
                when (hourlyForecastUiState) {
                    is HourlyForecastUiState.Loading -> HomeScreenLoading(

                    )
                    is HourlyForecastUiState.Error -> HomeScreenError(
                        retryAction = retryAction
                    )
                    is HourlyForecastUiState.Success -> HomeScreenSuccess(
                        hourlyWeather = hourlyForecastUiState.hourlyWeather
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
}

@Composable
fun HomeScreenLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun HomeScreenError(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "There was an error connecting to the internet")
        Spacer(modifier = modifier.height(10.dp))
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun HomeScreenSuccess(
    modifier: Modifier = Modifier,
    hourlyWeather: HourlyWeather,
) {
    Column(
        modifier.fillMaxSize()
    ) {

    }
}

