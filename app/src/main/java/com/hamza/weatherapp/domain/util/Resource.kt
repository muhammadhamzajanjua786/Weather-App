package com.hamza.weatherapp.domain.util

sealed class Resource<T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val error: String?): Resource<T>()
}