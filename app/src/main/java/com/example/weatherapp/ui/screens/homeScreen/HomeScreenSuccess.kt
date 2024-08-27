package com.example.weatherapp.ui.screens.homeScreen

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.model.DailyData
import com.example.weatherapp.model.HourlyData
import com.example.weatherapp.model.HourlyWeather
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreenSuccess(
    modifier: Modifier = Modifier,
    dailyForecastUiState: DailyForecastUiState,
    paddingValues: PaddingValues,
    hourlyWeather: HourlyWeather,
) {
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentHourData = hourlyWeather.data[0]
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = modifier.verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {

                    }
                }
            }
        },
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 5.dp, end = 5.dp)
            ) {
                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = null
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(10.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = hourlyWeather.cityName,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = WeatherData.weatherTimeConverter(currentHourData.datetime),
                    style = MaterialTheme.typography.titleMedium
                )

            }
            Spacer(modifier = modifier.height(15.dp))
            Image(
                painter = painterResource(id = WeatherData.weatherCodeConverter[currentHourData.weather.code]!!.icon),
                contentDescription = null,
                modifier = modifier.size(230.dp)
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = currentHourData.weather.description,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = currentHourData.apparentTemperature.toString() + "\u2103",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = modifier.height(20.dp))
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(hourlyWeather.data){
                    HourlyWeatherItem(
                        currentHour = it
                    )
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            when (dailyForecastUiState){
                is DailyForecastUiState.Error -> {}
                is DailyForecastUiState.Loading -> {}
                is DailyForecastUiState.Success -> {
                    val currentDate = LocalDate.now()
                    val formatter = DateTimeFormatter.ofPattern("EEEE")
                    val formattedDate = currentDate.format(formatter)
                    val dailyWeather = dailyForecastUiState.dailyWeather
                    var currentDayInt = WeatherData.weekDaysConverter.entries.find { it.value == formattedDate }!!.key
                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = modifier.padding(10.dp)
                        ) {
                            dailyWeather.data.forEach {
                                DailyWeatherItem(
                                    currentDay = it,
                                    currentDayInt = currentDayInt
                                )
                                if (currentDayInt == 6) currentDayInt = 0 else currentDayInt++
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    currentHour: HourlyData,
) {
    OutlinedCard(
        shape = RoundedCornerShape(50)
    ) {
        Column(
            modifier = modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = WeatherData.weatherTimeConverter(currentHour.datetime)
            )
            Spacer(modifier = modifier.height(10.dp))
            Image(
                painter = painterResource(id = WeatherData.weatherCodeConverter[currentHour.weather.code]!!.icon),
                contentDescription = null,
                modifier = modifier.size(50.dp)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = currentHour.apparentTemperature.toString() + "\u2103",
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun DailyWeatherItem(
    modifier: Modifier = Modifier,
    currentDay: DailyData,
    currentDayInt: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = WeatherData.weekDaysConverter[currentDayInt]!!,
            modifier = modifier.width(100.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Image(
            painter = painterResource(id = WeatherData.weatherCodeConverter[currentDay.weather.code]!!.icon),
            contentDescription = null,
            modifier = modifier.size(24.dp)
        )
        Text(
            text = "${currentDay.appMaxTemp}/${currentDay.appMinTemp}",
            style = MaterialTheme.typography.titleSmall
        )
    }
}