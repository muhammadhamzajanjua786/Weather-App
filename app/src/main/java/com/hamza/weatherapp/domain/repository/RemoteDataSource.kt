package com.hamza.weatherapp.domain.repository

import com.hamza.weatherapp.data.remote.response.CurrentWeatherResponse
import com.hamza.weatherapp.data.remote.response.WeatherForecastResponse
import com.hamza.weatherapp.domain.util.Resource

interface RemoteDataSource {
    suspend fun getCurrentWeather(cityName: String): Resource<CurrentWeatherResponse>
    suspend fun getWeatherForecast(cityName: String): Resource<WeatherForecastResponse>
}