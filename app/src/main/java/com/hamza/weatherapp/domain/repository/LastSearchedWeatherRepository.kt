package com.hamza.weatherapp.domain.repository

import com.hamza.weatherapp.data.local.entity.WeatherData
import kotlinx.coroutines.flow.Flow

interface LastSearchedWeatherRepository {
    suspend fun getLastSearchedWeather(): Flow<WeatherData?>
}