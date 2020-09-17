package com.example.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.Weather
import com.example.weather.repository.WeatherRepository
import com.example.weather.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(val weatherRepository: WeatherRepository):ViewModel(){

    private val _weatherData:MutableLiveData<Resource<Weather>> = MutableLiveData()
    val weatherData:LiveData<Resource<Weather>>
        get() = _weatherData


    fun getWeather(cityName:String)=viewModelScope.launch {
        _weatherData.postValue(Resource.Loading())
        val response=weatherRepository.getWeather()
        _weatherData.postValue(handleWeatherResponse(response))
    }





    private fun handleWeatherResponse(response: Response<Weather>): Resource<Weather>? {
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}