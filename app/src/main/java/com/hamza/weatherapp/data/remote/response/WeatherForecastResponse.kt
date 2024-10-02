package com.hamza.weatherapp.data.remote.response

import com.hamza.weatherapp.data.local.entity.WeatherData

data class WeatherForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItem>,
    val city: City
)

data class ForecastItem(
    val dt: Long,
    val main: MainDetails,
    val weather: List<WeatherCondition>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Double,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
) {
    fun toDomain(cityName: String): WeatherData {
        return WeatherData(
            temp = main.temp,
            tempMin = main.temp_min,
            tempMax = main.temp_max,
            condition = weather.firstOrNull()?.main ?: "",
            humidity = main.humidity.toString(),
            windSpeed = wind.speed.toString(),
            isForecast = true,
            date = dt_txt,
            cityName = cityName,
            icon = weather.firstOrNull()?.icon ?: "",
            pressure = main.pressure,
            pop = pop,
            visibility = visibility
        )
    }
}

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)
