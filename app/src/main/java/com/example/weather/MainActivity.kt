package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.repository.WeatherRepository
import com.example.weather.util.Resource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository= WeatherRepository()

        viewModel=ViewModelProvider(this,WeatherViewModelProviderFactory(repository)).get(WeatherViewModel::class.java)
        setContentView(R.layout.activity_main)

        val cityName=searchView.text.toString()


        imageButton.setOnClickListener{
            setWeather(cityName)
            Log.i("VALUE OF",searchView.text.toString())
        }

        viewModel.weatherData.observe(this, Observer {weatherResponse->
            when(weatherResponse){
                is Resource.Success->{
                    weatherResponse.data?.let {
                        temperature.text=it.current.temperature.toString()+"\u2103"
                        location.text=it.request.query
                        humidity.text=humidity.text.toString()+" :"+it.current.humidity+"%"
                        val url=it.current.weatherIcons[0]
                        Picasso.get().load(it.current.weatherIcons[0]).into(imageView)

                        Log.i("Temp",url)
                    }
                }
            }

        })

    }

    private fun setWeather(cityName: String) {
        viewModel.getWeather(cityName)
    }
}