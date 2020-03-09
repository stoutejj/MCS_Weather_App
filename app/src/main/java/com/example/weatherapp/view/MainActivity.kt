package com.example.weatherapp.view

import WeatherViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val dailyRecyclerAdapter = DailyForecastAdapter()
        daily_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        daily_recycler.adapter = dailyRecyclerAdapter
        //Toast.makeText(this,"message", Toast.LENGTH_LONG).show()


        weatherViewModel.getWeatherForecast("11221")

        weatherViewModel.weatherForecast.observe(this, Observer {

            dailyRecyclerAdapter.setDailyWeatherData(it)
        })
    }
}