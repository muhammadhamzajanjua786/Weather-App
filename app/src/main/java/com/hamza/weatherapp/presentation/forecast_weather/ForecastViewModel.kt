package com.hamza.weatherapp.presentation.forecast_weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.weatherapp.data.local.entity.WeatherData
import com.hamza.weatherapp.domain.usecase.GetWeatherForecastUseCase
import com.hamza.weatherapp.domain.util.Resource
import com.hamza.weatherapp.presentation.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _forecast =
        MutableStateFlow<WeatherUiState<List<WeatherData>>>(WeatherUiState())
    val forecast = _forecast.asStateFlow()

    private val _cityName = MutableStateFlow("")
    val cityName = _cityName.asStateFlow()

    init {
        savedStateHandle.get<String>("city_name")?.let { cityName ->
            _cityName.value = cityName
            getWeatherForecast(cityName)
        }
    }

    private fun getWeatherForecast(cityName: String) {
        viewModelScope.launch {
            when (val response = getWeatherForecastUseCase(cityName)) {
                is Resource.Error -> {
                    _forecast.value = _forecast.value.copy(error = response.error.toString())
                }

                is Resource.Success -> {
                    _forecast.value = _forecast.value.copy(data = response.data)
                }
            }
        }
    }
}