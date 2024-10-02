package com.hamza.weatherapp.data.remote.repository

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.data.local.fetcher.WeatherLocalDataSource
import com.hamza.weatherapp.data.remote.fetcher.WeatherRemoteDataSource
import com.hamza.weatherapp.domain.network.NetworkConnectivity
import com.hamza.weatherapp.domain.repository.WeatherRepository
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
) : WeatherRepository {

    override suspend fun getCurrentWeather(cityName: String): Resource<WeatherData> {
        return if (networkConnectivity.isConnected().not()) {
            weatherLocalDataSource.fetch(cityName)
        } else {
            weatherRemoteDataSource.fetch(cityName)
        }
    }
}