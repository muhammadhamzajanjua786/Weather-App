package com.hamza.weatherapp.domain.usecase

import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.repository.LastSearchedWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLastSearchedWeatherUseCase @Inject constructor(
    private val repo: LastSearchedWeatherRepository
) {
    suspend operator fun invoke(): Flow<WeatherData?> = withContext(Dispatchers.IO) {
        repo.getLastSearchedWeather()
    }
}