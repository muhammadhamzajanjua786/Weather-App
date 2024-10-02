package com.hamza.weatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.hamza.weatherapp.domain.network.NetworkConnectivity
import javax.inject.Inject

class NetworkConnectivityImpl @Inject constructor(
    private val context: Context
) : NetworkConnectivity {
    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}