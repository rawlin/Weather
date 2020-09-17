package com.example.weather.models


import com.google.gson.annotations.SerializedName

data class Current(
    val humidity: Int,
    val precip: Float,
    val temperature: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
)