package com.example.weatherapp.view

import android.icu.lang.UCharacter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.model.WeatherDataList
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.notify
import java.lang.StringBuilder
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class DailyForecastAdapter :
    RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    var dataSet: List<WeatherData> = emptyList()
    var dailyDataSet: ArrayList<WeatherData> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : DailyForecastViewHolder =
        DailyForecastViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.daily_forecast_item_layout,
                    parent,
                    false
                )
        )

    fun setDailyWeatherData(t: List<WeatherData>) {
        dataSet = t
        var date = dataSet[0]
        dailyDataSet.add(dataSet[0])

        for (weather in dataSet) {
            /*
                IF-STATEMENT
                checks the substring of dt_text "yyyy-MM-dd"
                if the dataSet[0] != dataSet[position]
                dailyDataSet.add(dataSet[position]) -->> date = dataSet[position]
             */
            if (date.dt_txt.substring(0, 10) != weather.dt_txt.substring(0, 10)) {
                dailyDataSet.add(weather)   // ArrayList of the different dates
                date = weather
            }
        }

        notifyDataSetChanged()
    }

    override fun getItemCount() = dailyDataSet.size


    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {

        holder.onBind(dailyDataSet, position)

        val hourlyRecyclerAdapter = HourlyForecastAdapter()
        holder.hourlyRecyclerView.layoutManager = GridLayoutManager(
            holder.hourlyRecyclerView.context,
            4
        )  // creates a gridLayout with 4 columns
        holder.hourlyRecyclerView.adapter = hourlyRecyclerAdapter
        hourlyRecyclerAdapter.setHourlyWeatherData(
            dailyDataSet[position].dt_txt.substring(0, 10),
            dataSet
        )
    }

    class DailyForecastViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvDate: TextView =
            itemView.findViewById(R.id.tv_date)

        var hourlyRecyclerView: RecyclerView = itemView.findViewById(R.id.hourly_recycler)

        fun onBind(data: List<WeatherData>, position: Int) {

            val currentDate: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
            val date = StringBuilder()


            if (currentDate == data[0].dt_txt.substring(0, 10)) {

                tvDate.text = when (position) {
                    0 -> "TODAY"
                    1 -> "TOMORROW"
                    else -> date.append(
                        data[position].dt_txt.substring(5, 7) + "/" +
                                data[position].dt_txt.substring(8, 10) + "/" +
                                data[position].dt_txt.substring(0, 4)
                    )
                }
            } else {
                date.append(
                    data[position].dt_txt.substring(5, 7) + "/" +
                            data[position].dt_txt.substring(8, 10) + "/" +
                            data[position].dt_txt.substring(0, 4)
                )
                tvDate.text = date
            }
        }
    }
}


