package com.example.weatherapp.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.weatherapp.model.WeatherDataList
import com.squareup.picasso.Picasso


class HourlyForecastAdapter :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    var dataSet: WeatherDataList = WeatherDataList(emptyList())
/*

*/
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

    fun setHourlyWeatherData(t : WeatherDataList){
        dataSet = t
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.list.size

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        holder.onBind(dataSet, position)
    }

    class HourlyForecastViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvTime: TextView =
            itemView.findViewById(R.id.tv_time)

        //var ivIcon: TextView =
           // itemView.findViewById(R.id.iv_icon)

        var tvTemp: TextView =
            itemView.findViewById(R.id.tv_date)

        fun onBind(data: WeatherDataList, position: Int) {
            tvTime.text = data.list[position].dt.toString()
            tvTemp.text = data.list[position].main.temp.toString()

            // tvTemp.text = data.list[position].toString()

        }
    }
}

