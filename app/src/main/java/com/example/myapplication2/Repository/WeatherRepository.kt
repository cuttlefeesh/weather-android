package com.example.myapplication2.Repository

import com.example.myapplication2.Server.ApiServices

class WeatherRepository(val api:ApiServices) {
    fun getCurrentWeather(lat: Double, lng:Double, unit:String)=api.getCurrentWeather(lat, lng, unit, "ac05f986740b9312c902427a454fbaa7")
}