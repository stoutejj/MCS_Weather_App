package com.example.weatherapp.view

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.weatherapp.model.WeatherData

import com.squareup.picasso.Picasso
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class HourlyForecastAdapter :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    var dataSet: List<WeatherData> = emptyList()
    var hourlyDataSet: ArrayList<WeatherData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : HourlyForecastViewHolder =
        HourlyForecastViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.hourly_forecast_item_layout,
                    parent,
                    false
                )
        )

/*    fun setTemp(units: String){

        for (weather in dataSet) {
            weather.main.temp = when (units) {
                "metric" -> weather.main.temp - 273.15
                else ->
            }

        }
    }*/

    fun setHourlyWeatherData(date: String, t: List<WeatherData>) {
        dataSet = t
        for (weather in dataSet) {
            //println(" Date: " + weather.dt_txt.substring(0, 10) + " time : " + weather.dt_txt.substring(11,19))
            if (date == weather.dt_txt.substring(0, 10)) {
                //println("WEATHER: " + weather.main.temp + " ---> " + (weather.main.temp - 273.15).toFloat().roundToInt())
                hourlyDataSet.add(weather)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = hourlyDataSet.size

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {

        holder.onBind(hourlyDataSet, position)

    }

    class HourlyForecastViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvTime: TextView =
            itemView.findViewById(R.id.tv_time)

        var ivIcon: ImageView =
            itemView.findViewById(R.id.iv_icon)

        var tvTemp: TextView =
            itemView.findViewById(R.id.tv_temp)

        fun onBind(data: List<WeatherData>, position: Int) {
            tvTime.text = data[position].dt_txt.substring(11,19)
            tvTemp.text = Math.floor(data[position].main.temp.toDouble()).toInt().toString() + "°"

            var iconCode: String = data[position].weather[0].icon

            Picasso.get().load("http://openweathermap.org/img/wn/$iconCode@2x.png").into(ivIcon)

            //ivIcon.setColorFilter(Color.parseColor("#FFFFFF"))
        }
    }
}

