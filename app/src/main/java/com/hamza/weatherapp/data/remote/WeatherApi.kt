package com.hamza.weatherapp.data.remote

import com.hamza.weatherapp.BuildConfig
import com.hamza.weatherapp.data.remote.response.CurrentWeatherResponse
import com.hamza.weatherapp.data.remote.response.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("units") units: String = NetworkConstants.WEATHER_UNIT,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY
    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") q: String,
        @Query("units") units: String = NetworkConstants.WEATHER_UNIT,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY
    ): Response<WeatherForecastResponse>
}