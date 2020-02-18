package com.example.weatherapp.view
import WeatherViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherDataList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.daily_forecast_item_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherViewModel() as T
            }
        }).get(WeatherViewModel::class.java)

        val dailyRecyclerAdapter  = DailyForecastAdapter()
        daily_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        daily_recycler.adapter  = dailyRecyclerAdapter

        weatherViewModel.getWeatherForecast("11221", "Metric")
        weatherViewModel.getWeatherDataForecast()
            .observe(this, Observer<WeatherDataList> { t ->
                dailyRecyclerAdapter.setDailyWeatherData(t)
            }
            )
    }
}

/*
                hourly_recycler.layoutManager = GridLayoutManager(this@MainActivity, 4)
                hourly_recycler.adapter = HourlyForecastAdapter(t)
 */