package com.hamza.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_search")
data class LastSearchedCity(
    @PrimaryKey(autoGenerate = false)
    val city_name: String,
    val timestamp: Long = System.currentTimeMillis()
)