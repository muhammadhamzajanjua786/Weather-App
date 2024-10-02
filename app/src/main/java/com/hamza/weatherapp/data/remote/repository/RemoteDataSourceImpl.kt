package com.hamza.weatherapp.data.remote.repository

import com.hamza.weatherapp.data.remote.WeatherApi
import com.hamza.weatherapp.data.remote.response.CurrentWeatherResponse
import com.hamza.weatherapp.data.remote.response.WeatherForecastResponse
import com.hamza.weatherapp.data.remote.util.safeApiCall
import com.hamza.weatherapp.domain.repository.RemoteDataSource
import com.hamza.weatherapp.domain.util.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: WeatherApi
): RemoteDataSource {

    override suspend fun getCurrentWeather(cityName: String): Resource<CurrentWeatherResponse> = safeApiCall {
        api.getCurrentWeatherData(cityName)
    }

    override suspend fun getWeatherForecast(cityName: String): Resource<WeatherForecastResponse> = safeApiCall {
        api.getWeatherForecast(cityName)
    }
}