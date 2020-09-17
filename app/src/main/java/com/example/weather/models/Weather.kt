package com.example.weather.models


import android.location.Location
import com.google.gson.annotations.SerializedName

data class Weather(
    val current: Current,
    val request: Request
)