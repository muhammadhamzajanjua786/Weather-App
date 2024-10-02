package com.hamza.weatherapp.data.local.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.fetcher.WeatherFetcher
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(
    private val localDataSource: LocalDataSource
) : WeatherFetcher {
    override suspend fun fetch(cityName: String): Resource<WeatherData> {
        val weatherData = localDataSource.fetchCityWeather(cityName)
        return if (weatherData != null) {
            localDataSource.saveLastSearchedCity(cityName)
            Resource.Success(weatherData)
        } else {
            Resource.Error("No city found")
        }
    }
}