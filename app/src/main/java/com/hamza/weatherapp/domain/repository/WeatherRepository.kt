package com.hamza.weatherapp.domain.repository

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.util.Resource

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String): Resource<WeatherData>
}