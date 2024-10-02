package com.hamza.weatherapp.data.local.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.fetcher.WeatherForecastFetcher
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherForecastLocalDataSource @Inject constructor(
    private val localDataSource: LocalDataSource
) : WeatherForecastFetcher {
    override suspend fun fetchForecast(cityName: String): Resource<List<WeatherData>> {
        val weatherData = localDataSource.fetchWeatherForecast(cityName)
        return if (weatherData.isEmpty()) {
            Resource.Error("No data found")
        } else {
            Resource.Success(weatherData)
        }
    }
}