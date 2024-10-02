package com.hamza.weatherapp.presentation.current_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.hamza.weatherapp.domain.usecase.GetLastSearchedWeatherUseCase
import com.hamza.weatherapp.domain.util.Resource
import com.hamza.weatherapp.presentation.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLastSearchedWeatherUseCase: GetLastSearchedWeatherUseCase
) : ViewModel() {

    private val _weather =
        MutableStateFlow<WeatherUiState<WeatherData>>(WeatherUiState())
    val weather = _weather.asStateFlow()

    init {
        getLastSearchCity()
    }

    private fun getLastSearchCity() {
        viewModelScope.launch {
            getLastSearchedWeatherUseCase().collectLatest { weather ->
                _weather.value = _weather.value.copy(data = weather, error = "")
            }
        }
    }

    fun getCurrentWeather(cityName: String) {
        viewModelScope.launch {
            when (val response = getCurrentWeatherUseCase(cityName)) {
                is Resource.Error -> _weather.value = _weather.value.copy(
                    data = null,
                    error = response.error.toString()
                )

                is Resource.Success -> _weather.value = _weather.value.copy(
                    data = response.data,
                    error = ""
                )
            }
        }
    }

    fun getCityName(): String {
        return _weather.value.data?.cityName ?: ""
    }
}