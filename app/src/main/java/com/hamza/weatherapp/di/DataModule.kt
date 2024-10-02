package com.hamza.weatherapp.di

import android.content.Context
import com.hamza.weatherapp.data.local.WeatherDatabase
import com.hamza.weatherapp.data.local.fetcher.WeatherForecastLocalDataSource
import com.hamza.weatherapp.data.local.fetcher.WeatherLocalDataSource
import com.hamza.weatherapp.data.local.repository.LocalDataSourceImpl
import com.hamza.weatherapp.data.network.NetworkConnectivityImpl
import com.hamza.weatherapp.data.remote.WeatherApi
import com.hamza.weatherapp.data.remote.fetcher.WeatherForecastRemoteDataSource
import com.hamza.weatherapp.data.remote.fetcher.WeatherRemoteDataSource
import com.hamza.weatherapp.data.remote.repository.LastSearchedWeatherRepositoryImpl
import com.hamza.weatherapp.data.remote.repository.RemoteDataSourceImpl
import com.hamza.weatherapp.data.remote.repository.WeatherForecastRepositoryImpl
import com.hamza.weatherapp.data.remote.repository.WeatherRepositoryImpl
import com.hamza.weatherapp.domain.network.NetworkConnectivity
import com.hamza.weatherapp.domain.repository.LastSearchedWeatherRepository
import com.hamza.weatherapp.domain.repository.LocalDataSource
import com.hamza.weatherapp.domain.repository.RemoteDataSource
import com.hamza.weatherapp.domain.repository.WeatherForecastRepository
import com.hamza.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(db: WeatherDatabase): LocalDataSource =
        LocalDataSourceImpl(dao = db.weatherDao())

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: WeatherApi): RemoteDataSource =
        RemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideWeatherRepository(
        networkConnectivity: NetworkConnectivity,
        weatherLocalDataSource: WeatherLocalDataSource,
        weatherRemoteDataSource: WeatherRemoteDataSource,
    ): WeatherRepository =
        WeatherRepositoryImpl(
            networkConnectivity = networkConnectivity,
            weatherLocalDataSource = weatherLocalDataSource,
            weatherRemoteDataSource = weatherRemoteDataSource
        )

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(
        networkConnectivity: NetworkConnectivity,
        weatherForecastLocalDataSource: WeatherForecastLocalDataSource,
        weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
    ): WeatherForecastRepository =
        WeatherForecastRepositoryImpl(
            networkConnectivity = networkConnectivity,
            weatherForecastLocalDataSource = weatherForecastLocalDataSource,
            weatherForecastRemoteDataSource = weatherForecastRemoteDataSource
        )

    @Provides
    @Singleton
    fun provideLastSearchedWeatherRepository(
        networkConnectivity: NetworkConnectivity,
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): LastSearchedWeatherRepository =
        LastSearchedWeatherRepositoryImpl(
            networkConnectivity = networkConnectivity,
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext appContext: Context): NetworkConnectivity =
        NetworkConnectivityImpl(appContext)
}