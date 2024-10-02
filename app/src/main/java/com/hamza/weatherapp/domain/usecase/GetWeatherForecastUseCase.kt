package com.hamza.weatherapp.domain.usecase

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.repository.WeatherForecastRepository
import com.hamza.weatherapp.domain.repository.WeatherRepository
import com.hamza.weatherapp.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repo: WeatherForecastRepository
) {
    suspend operator fun invoke(cityName: String): Resource<List<WeatherData>> =
        withContext(Dispatchers.IO) {
            repo.getWeatherForecast(cityName)
        }
}