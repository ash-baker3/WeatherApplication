package com.example.myapplication.data.remote

import com.example.myapplication.data.model.CityInfo
import com.example.myapplication.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("geo/1.0/direct")
    suspend fun getCityCoordinates(
        @Query("q") city: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") apiKey: String
    ): List<CityInfo>

    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

}