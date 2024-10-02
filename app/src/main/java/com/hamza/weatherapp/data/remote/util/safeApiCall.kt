package com.hamza.weatherapp.data.remote.util

import com.google.gson.Gson
import com.hamza.weatherapp.data.remote.NetworkConstants.ERROR_NETWORK
import com.hamza.weatherapp.data.remote.NetworkConstants.ERROR_SERVER
import com.hamza.weatherapp.data.remote.response.ErrorResponse
import com.hamza.weatherapp.domain.util.Resource
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> = try {
    val response = apiCall()
    when {
        response.isSuccessful && response.body() != null -> Resource.Success(response.body()!!)
        else -> {
            val errorBody = response.errorBody()?.string() ?: "Unknown error"
            val error = Gson().fromJson(errorBody, ErrorResponse::class.java)
            Resource.Error(error = error.message)
        }
    }
} catch (io: IOException) {
    Resource.Error(error = ERROR_NETWORK)
} catch (e: Exception) {
    Resource.Error(error = ERROR_SERVER)
}