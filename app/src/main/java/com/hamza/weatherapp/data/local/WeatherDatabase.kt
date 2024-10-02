package com.hamza.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamza.weatherapp.data.local.entity.LastSearchedCity
import com.hamza.weatherapp.data.local.entity.WeatherData

@Database(
    entities = [WeatherData::class, LastSearchedCity::class],
    version = 1
)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "database_weather"
    }
}