package com.hamza.weatherapp.data.local.repository

import com.hamza.weatherapp.data.local.WeatherDao
import com.hamza.weatherapp.data.local.entity.LastSearchedCity
import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: WeatherDao
) : LocalDataSource {

    override suspend fun saveCityWeather(vararg weatherDataList: WeatherData) =
        dao.addWeatherData(*weatherDataList)

    override suspend fun saveLastSearchedCity(cityName: String) =
        dao.insertLastSearchedCity(LastSearchedCity(cityName))

    override suspend fun fetchCityWeather(cityName: String): WeatherData? =
        dao.getWeatherByCityName(cityName)

    override suspend fun fetchLastSearchedWeather(): WeatherData? =
        dao.getLastSearchedWeatherData()

    override suspend fun fetchWeatherForecast(cityName: String): List<WeatherData> =
        dao.getWeatherForecastByCityName(cityName)
}