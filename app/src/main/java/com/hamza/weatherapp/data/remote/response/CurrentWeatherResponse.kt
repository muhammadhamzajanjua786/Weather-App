package com.hamza.weatherapp.data.remote.response

import com.hamza.weatherapp.data.local.entity.WeatherData

data class CurrentWeatherResponse(
    val coord: Coord,
    val weather: List<WeatherCondition>,
    val base: String,
    val main: MainDetails,
    val wind: Wind,
    val clouds: Clouds,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
    val visibility: Double
) {
    fun toDomain(): WeatherData {
        return WeatherData(
            temp = main.temp,
            tempMin = main.temp_min,
            tempMax = main.temp_max,
            cityName = name,
            condition = weather.firstOrNull()?.main ?: "",
            humidity = main.humidity.toString(),
            windSpeed = wind.speed.toString(),
            visibility = visibility,
            icon = weather.firstOrNull()?.icon ?: "",
            pressure = main.pressure
        )
    }
}

data class MainDetails(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int
)
// Data class for wind information
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Clouds(
    val all: Int
)
// Data class for system information (country, sunrise, sunset, etc.)
data class Sys(
    val country: String? = null,
    val sunrise: Long,
    val sunset: Long,
    val pod: String? = null  // For forecast "pod" (day/night)
)



// Base data class for coordinates
data class Coord(
    val lon: Double,
    val lat: Double
)

// Data class for weather condition
data class WeatherCondition(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)