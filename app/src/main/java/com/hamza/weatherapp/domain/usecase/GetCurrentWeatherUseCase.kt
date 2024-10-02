package com.hamza.weatherapp.domain.usecase

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.repository.WeatherRepository
import com.hamza.weatherapp.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Resource<WeatherData> = withContext(Dispatchers.IO) {
        weatherRepository.getCurrentWeather(cityName)
    }
}