package com.hamza.weatherapp.di

import com.hamza.weatherapp.BuildConfig
import com.hamza.weatherapp.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)
}