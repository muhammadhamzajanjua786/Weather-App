package com.hamza.weatherapp.domain.repository

import com.hamza.weatherapp.data.local.entity.WeatherData


interface LocalDataSource {
    suspend fun saveCityWeather(vararg weatherDataList: WeatherData)
    suspend fun saveLastSearchedCity(cityName: String)
    suspend fun fetchCityWeather(cityName: String): WeatherData?
    suspend fun fetchLastSearchedWeather(): WeatherData?
    suspend fun fetchWeatherForecast(cityName: String): List<WeatherData>
}