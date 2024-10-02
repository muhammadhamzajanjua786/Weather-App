package com.hamza.weatherapp.presentation

//sealed class WeatherUiState<out T> {
//    data object Loading : WeatherUiState<Nothing>()
//    data class Success<T>(val weather: T?) : WeatherUiState<T>()
//    data class Error(val message: String) : WeatherUiState<Nothing>()
//}

data class WeatherUiState<T> (
    val loading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)
//    data object Loading : WeatherUiState<Nothing>()
//    data class Success<T>(val weather: T?) : WeatherUiState<T>()
//    data class Error(val message: String) : WeatherUiState<Nothing>()