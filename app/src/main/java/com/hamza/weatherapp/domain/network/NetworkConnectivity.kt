package com.hamza.weatherapp.domain.network

interface NetworkConnectivity {
    fun isConnected(): Boolean
}