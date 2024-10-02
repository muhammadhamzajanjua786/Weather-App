package com.hamza.weatherapp.data.remote.repository

import com.hamza.weatherapp.data.remote.WeatherApi
import com.hamza.weatherapp.data.remote.response.Clouds
import com.hamza.weatherapp.data.remote.response.Coord
import com.hamza.weatherapp.data.remote.response.CurrentWeatherResponse
import com.hamza.weatherapp.data.remote.response.MainDetails
import com.hamza.weatherapp.data.remote.response.Sys
import com.hamza.weatherapp.data.remote.response.WeatherCondition
import com.hamza.weatherapp.data.remote.response.Wind
import com.hamza.weatherapp.domain.util.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class RemoteDataSourceImplTest {

    @Mock
    private lateinit var weatherApi: WeatherApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldReturnSuccessWhenGettingCurrentWeather() = runTest {
        Mockito.`when`(weatherApi.getCurrentWeatherData("Chakwal")).thenReturn(Response.success(
            dummyCityWeather()
        ))
        val sut = RemoteDataSourceImpl(weatherApi)
        val result = sut.getCurrentWeather("Chakwal")
        assertEquals(true, result is Resource.Success)
    }

    @Test
    fun shouldReturnErrorWhenResponseIsNull() = runTest {
        Mockito.`when`(weatherApi.getCurrentWeatherData("Chakwal")).thenReturn(
            Response.success<CurrentWeatherResponse>(null)
        )
        val sut = RemoteDataSourceImpl(weatherApi)
        val result = sut.getCurrentWeather("Chakwal")
        assertEquals(true, result is Resource.Error)
    }

    @Test
    fun shouldReturnErrorWhenApiThrowsException() = runTest {
        Mockito.`when`(weatherApi.getCurrentWeatherData("Chakwal")).thenThrow(RuntimeException("Network error"))
        val sut = RemoteDataSourceImpl(weatherApi)
        val result = sut.getCurrentWeather("Chakwal")
        assertEquals(true, result is Resource.Error)
    }

    private fun dummyCityWeather(): CurrentWeatherResponse {
        return CurrentWeatherResponse(
            coord = Coord(
                lon = 1.22,
                lat = 2.6
            ),
            weather = listOf(WeatherCondition(
                id = 1,
                main = "Clear",
                description = "Clear Sky",
                icon = "10n"
            )),
            base = "",
            main = MainDetails(
                temp = 23.8,
                temp_max = 28.6,
                temp_min = 22.4,
                pressure = 5000,
                humidity = 800,
                sea_level = 891
            ),
            wind = Wind(
                speed = 56.8,
                deg = 8,
                gust = 55.4
            ),
            clouds = Clouds(
                all = 59
            ),
            sys = Sys(
                country = "pk",
                sunset = 5554,
                sunrise = 555,
                pod = ""
            ),
            timezone = 36000,
            id = 2147714,
            name = "Sydney",
            cod = 200,
            visibility = 10000.0
        )
    }

    @Test
    fun getWeatherForecast() {
    }
}