package com.example.myapplication2.ViewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication2.Repository.WeatherRepository
import com.example.myapplication2.Server.ApiClient
import com.example.myapplication2.Server.ApiServices

class WeatherViewModel(val repository: WeatherRepository):ViewModel() {
    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather(lat: Double, lng: Double, unit: String)=repository.getCurrentWeather(lat, lng, unit)
}