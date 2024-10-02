package com.hamza.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.hamza.weatherapp.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app, WeatherDatabase::class.java, WeatherDatabase.DATABASE_NAME
        ).build()
    }
}