package com.hamza.weatherapp.domain.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.util.Resource

interface WeatherForecastFetcher {
    suspend fun fetchForecast(cityName: String): Resource<List<WeatherData>>
}