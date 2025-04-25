package com.example.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeoRetrofitClient {
    private const val GEO_BASE_URL = "https://api.openweathermap.org/"

    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(GEO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
