package com.example.weather.network

import com.example.weather.models.Weather
import com.example.weather.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    @GET(value = "current")
    suspend fun getWeather(
        @Query(value = "access_key")
        apiKey:String= "f97100a8d39679deb6941e10ae30a3be",
        @Query("query")
        cityName:String="margao"
    ):Response<Weather>



}