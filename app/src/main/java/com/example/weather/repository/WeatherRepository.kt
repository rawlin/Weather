package com.example.weather.repository

import com.example.weather.network.RetrofitInstance

class WeatherRepository {
    suspend fun getWeather()=
        RetrofitInstance.api.getWeather()
}