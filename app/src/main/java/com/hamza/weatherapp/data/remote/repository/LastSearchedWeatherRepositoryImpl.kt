package com.hamza.weatherapp.data.remote.repository

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.network.NetworkConnectivity
import com.hamza.weatherapp.domain.repository.LastSearchedWeatherRepository
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.repository.RemoteDataSource
import com.hamza.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LastSearchedWeatherRepositoryImpl @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : LastSearchedWeatherRepository {

    override suspend fun getLastSearchedWeather(): Flow<WeatherData?> = flow {
        val weather = localDataSource.fetchLastSearchedWeather()
        if (weather == null) {
            emit(null)
        } else if (networkConnectivity.isConnected().not()) {
            emit(weather)
        } else {
            emit(weather)
            when (val response = remoteDataSource.getCurrentWeather(weather.cityName ?: "")) {
                is Resource.Error -> emit(weather)
                is Resource.Success -> {
                    val cityWeather = response.data.toDomain()
                    localDataSource.saveCityWeather(cityWeather)
                    emit(cityWeather)
                }
            }
        }
    }
}