package com.hamza.weatherapp.data.remote.repository

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.data.local.fetcher.WeatherForecastLocalDataSource
import com.hamza.weatherapp.data.local.fetcher.WeatherLocalDataSource
import com.hamza.weatherapp.data.remote.fetcher.WeatherForecastRemoteDataSource
import com.hamza.weatherapp.data.remote.fetcher.WeatherRemoteDataSource
import com.hamza.weatherapp.domain.network.NetworkConnectivity
import com.hamza.weatherapp.domain.repository.WeatherForecastRepository
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource,
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
) : WeatherForecastRepository {

    override suspend fun getWeatherForecast(cityName: String): Resource<List<WeatherData>> {
        return if (networkConnectivity.isConnected().not()) {
            weatherForecastLocalDataSource.fetchForecast(cityName)
        } else {
            weatherForecastRemoteDataSource.fetchForecast(cityName)
        }
    }
}