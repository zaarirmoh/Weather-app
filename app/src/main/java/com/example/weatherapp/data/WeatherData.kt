package com.example.weatherapp.data

import com.example.weatherapp.R

data class WeatherResult(
    val description: String,
    val icon: Int,
)
object WeatherData {
    val weatherCodeConverter: Map<Int,WeatherResult> = mapOf(
        Pair(200,WeatherResult("Thunderstorm with light rain",R.drawable.storm)),
        Pair(201,WeatherResult("Thunderstorm with rain",R.drawable.storm)),
        Pair(202,WeatherResult("Thunderstorm with heavy rain",R.drawable.storm)),
        Pair(230,WeatherResult("Thunderstorm with light drizzle",R.drawable.thunder)),
        Pair(231,WeatherResult("Thunderstorm with drizzle",R.drawable.thunder)),
        Pair(232,WeatherResult("Thunderstorm with heavy drizzle",R.drawable.thunderstorm)),
        Pair(233,WeatherResult("Thunderstorm with Hail",R.drawable.thunderstorm)),
        Pair(300,WeatherResult("Light Drizzle",R.drawable.rain__3_)),
        Pair(301,WeatherResult("Drizzle",R.drawable.rain__3_)),
        Pair(302,WeatherResult("Heavy Drizzle",R.drawable.rain__3_)),
        Pair(500,WeatherResult("Light Rain",R.drawable.rain)),
        Pair(501,WeatherResult("Moderate Rain",R.drawable.rain)),
        Pair(502,WeatherResult("Heavy Rain",R.drawable.rain__2_)),
        Pair(511,WeatherResult("Freezing rain",R.drawable.rain__1_)),
        Pair(520,WeatherResult("Light shower rain",R.drawable.rain__3_)),
        Pair(521,WeatherResult("Shower rain",R.drawable.rain)),
        Pair(522,WeatherResult("Heavy shower rain",R.drawable.rain)),
        Pair(600,WeatherResult("Light snow",R.drawable.snow)),
        Pair(601,WeatherResult("Snow",R.drawable.snowy)),
        Pair(602,WeatherResult("Heavy Snow",R.drawable.snowy)),
        Pair(610,WeatherResult("Mix snow/rain",R.drawable.blizzard)),
        Pair(611,WeatherResult("Sleet",R.drawable.windy_2)),
        Pair(612,WeatherResult("Heavy sleet",R.drawable.windy_2)),
        Pair(621,WeatherResult("Snow shower",R.drawable.snow)),
        Pair(622,WeatherResult("Heavy snow shower",R.drawable.snowy)),
        Pair(623,WeatherResult("Flurries",R.drawable.snow__1_)),
        Pair(700,WeatherResult("Mist",R.drawable.cloud__2_)),
        Pair(711,WeatherResult("Smoke",R.drawable.cloud__2_)),
        Pair(721,WeatherResult("Haze",R.drawable.cloud__2_)),
        Pair(731,WeatherResult("Sand/dust",R.drawable.haze)),
        Pair(741,WeatherResult("Fog",R.drawable.haze)),
        Pair(751,WeatherResult("Freezing Fog",R.drawable.haze)),
        Pair(800,WeatherResult("Clear sky",R.drawable.sun)),
        Pair(801,WeatherResult("Few clouds",R.drawable.cloud)),
        Pair(802,WeatherResult("Scattered clouds",R.drawable.cloudy__2_)),
        Pair(803,WeatherResult("Broken clouds",R.drawable.cloudy__2_)),
        Pair(804,WeatherResult("Overcast clouds",R.drawable.clouds)),
        Pair(900,WeatherResult("Unknown Precipitation",R.drawable.rain))
    )

    val weekDaysConverter: Map<Int,String> = mapOf(
        Pair(0,"Sunday"),
        Pair(1,"Monday"),
        Pair(2,"Tuesday"),
        Pair(3,"Wednesday"),
        Pair(4,"Thursday"),
        Pair(5,"Friday"),
        Pair(6,"Saturday")
    )

    fun weatherTimeConverter(datetime: String): String{
        val splitResult = datetime.split("-",":")
        return when (splitResult[3]){
            "00" -> "12 AM"
            "01" -> "1 AM"
            "02" -> "2 AM"
            "03" -> "3 AM"
            "04" -> "4 AM"
            "05" -> "5 AM"
            "06" -> "6 AM"
            "07" -> "7 AM"
            "08" -> "8 AM"
            "09" -> "9 AM"
            "10" -> "10 AM"
            "11" -> "11 AM"
            "12" -> "12 PM"
            "13" -> "1 PM"
            "14" -> "2 PM"
            "15" -> "3 PM"
            "16" -> "4 PM"
            "17" -> "5 PM"
            "18" -> "6 PM"
            "19" -> "7 PM"
            "20" -> "8 PM"
            "21" -> "9 PM"
            "22" -> "10 PM"
            "23" -> "11 PM"
            else -> "12 AM"
        }
    }

}