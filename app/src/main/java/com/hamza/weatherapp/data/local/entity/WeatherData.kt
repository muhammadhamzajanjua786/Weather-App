package com.hamza.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cityName: String? = null,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val condition: String,
    val humidity: String,
    val windSpeed: String,
    val visibility: Double,
    val date: String = System.currentTimeMillis().toString(),
    val icon: String,
    val pressure: Int,
    val pop: Double? = null,
    val isForecast: Boolean = false
) {
    fun chanceOfRain(): String {
        return ((pop ?: 0.0) * 100).toInt().toString()
    }

    fun visibilityInKilometers(): Double {
        return visibility / 1000
    }
}