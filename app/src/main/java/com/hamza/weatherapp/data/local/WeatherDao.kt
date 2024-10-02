package com.hamza.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hamza.weatherapp.data.local.entity.LastSearchedCity
import com.hamza.weatherapp.data.local.entity.WeatherData

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherData(vararg data: WeatherData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastSearchedCity(city: LastSearchedCity)

    @Query("SELECT * FROM weather WHERE cityName = :cityName AND isForecast = 0 ORDER BY date DESC LIMIT 1")
    suspend fun getWeatherByCityName(cityName: String): WeatherData?

    @Query("SELECT * FROM weather WHERE cityName = :cityName AND isForecast = 1")
    suspend fun getWeatherForecastByCityName(cityName: String): List<WeatherData>

    @Transaction
    @Query("""
        SELECT * FROM weather
        WHERE cityName = (SELECT city_name FROM last_search ORDER BY timestamp DESC LIMIT 1) 
        AND isForecast = 0 ORDER
        BY date DESC
        LIMIT 1
    """)
    suspend fun getLastSearchedWeatherData(): WeatherData?
}