package com.example.weatherapp.ui.screens.homeScreen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.weatherapp.openAppSettings

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hourlyForecastUiState: HourlyForecastUiState,
    permissionsToRequest: Array<String>,
    activity: Activity
) {
    var isPermissionGranted: Boolean by remember {
        mutableStateOf(true)
    }
    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            isPermissionGranted = false
            perms.forEach { 
                if (it.value) isPermissionGranted = true
            }
        }
    )
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            locationPermissionResultLauncher.launch(permissionsToRequest)
            if (isPermissionGranted){
                when (hourlyForecastUiState) {
                    is HourlyForecastUiState.Loading -> HomeScreenLoading()
                    is HourlyForecastUiState.Error -> HomeScreenError()
                    is HourlyForecastUiState.Success -> HomeScreenSuccess()
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
fun HomeScreenLoading() {

}

@Composable
fun HomeScreenError() {

}

@Composable
fun HomeScreenSuccess() {

}

