package com.hamza.weatherapp.domain.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.util.Resource

interface WeatherFetcher {
    suspend fun fetch(cityName: String): Resource<WeatherData>
}