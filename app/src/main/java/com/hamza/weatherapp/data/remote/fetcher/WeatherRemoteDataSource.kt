package com.hamza.weatherapp.data.remote.fetcher

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.fetcher.WeatherFetcher
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.repository.RemoteDataSource
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : WeatherFetcher {
    override suspend fun fetch(cityName: String): Resource<WeatherData> {
        return when (val response = remoteDataSource.getCurrentWeather(cityName)) {
            is Resource.Error -> Resource.Error(response.error)
            is Resource.Success -> {
                val cityWeather = response.data.toDomain()
                localDataSource.saveCityWeather(cityWeather)
                localDataSource.saveLastSearchedCity(cityName)
                Resource.Success(cityWeather)
            }
        }
    }
}