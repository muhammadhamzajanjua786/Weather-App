package com.hamza.weatherapp.data.remote.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.fetcher.WeatherForecastFetcher
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.repository.RemoteDataSource
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherForecastRemoteDataSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherForecastFetcher {
    override suspend fun fetchForecast(cityName: String): Resource<List<WeatherData>> {
        return when (val response = remoteDataSource.getWeatherForecast(cityName)) {
            is Resource.Error -> Resource.Error(response.error)
            is Resource.Success -> {
                val forecastList = response.data.list
                    .filter { it.dt_txt.contains("12:00:00") }
                    .map { it.toDomain(cityName) }
                localDataSource.saveCityWeather(*forecastList.toTypedArray())
                Resource.Success(forecastList)
            }
        }
    }
}