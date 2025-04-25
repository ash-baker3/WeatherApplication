package com.example.myapplication.data.model

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Float,
    val humidity: Int
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)