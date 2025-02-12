package com.example.myapplication2.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R
import com.example.myapplication2.ViewModel.WeatherViewModel
import com.example.myapplication2.databinding.ActivityMainBinding
import com.example.myapplication2.model.CurrentResponseApi
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val weatherViewModel:WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        binding.apply {
            var lat = 51.50
            var lon = -0.12
            var name = "London"
            cityText.text = name
            progressBar.visibility = View.VISIBLE
            weatherViewModel.loadCurrentWeather(lat, lon, "metric").enqueue(object :
                retrofit2.Callback<CurrentResponseApi> {
                override fun onResponse(
                    call: Call<CurrentResponseApi>,
                    response: Response<CurrentResponseApi>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()
                        progressBar.visibility=View.GONE
                        detailLayout.visibility=View.VISIBLE
                        data?.let {
                            statusText.text = it.weather?.get(0)?.main ?: "-"
                            windText.text = it.wind.speed.let { Math.round(it).toString() } + "Km"
                            currentTempText.text = it.main.temp.let { Math.round(it).toString() } + "°"
                            maxTempText.text = it.main.tempMax.let { Math.round(it).toString() } + "°"
                            minTempText.text = it.main.tempMin.let { Math.round(it).toString() } + "°"

                            val drawable = if(isNightNow()) R.drawable.night_bg
                            else {

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CurrentResponseApi>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun isNightNow():Boolean {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 18
    }

    private fun setDynamicallyWallpaper(icon:String):Int {
        return when(icon.dropLast(1)) {
            "01" -> {
            }
        }
    }

    private fun initWeatherView(type:PrecipType) {
        binding.
    }
}