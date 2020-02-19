package com.example.weatherapp.view

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import com.example.weatherapp.model.WeatherDataList
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.LocalDateTime


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

        var currentData : String = dataSet.list[position].dt_txt.substring(0,10)
        var nextData : String = dataSet.list[position+1].dt_txt.substring(0,10)


        if(currentData.equals(nextData)) {
            holder.onBind(dataSet, position)
            Log.d("HOURLY RECYCLER ", currentData + "   " + nextData + " POSITION" + position.toString())
        }
        else {
            //onBindViewHolder(holder, position+1)
        }

    }

    class HourlyForecastViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvTime: TextView =
            itemView.findViewById(R.id.tv_time)

        var ivIcon: ImageView =
            itemView.findViewById(R.id.iv_icon)

        var tvTemp: TextView =
            itemView.findViewById(R.id.tv_temp)

        fun onBind(data: WeatherDataList, position: Int) {
            tvTime.text = data.list[position].dt_txt
            tvTemp.text = data.list[position].main.temp.toString()

            var iconCode : String
                    = data.list[position].weather[0].icon
           // Log.d("TESTING ICON CODE: ", iconCode)


            // tvTemp.text = data.list[position].toString()
 Picasso.get().load("http://openweathermap.org/img/wn/$iconCode@2x.png").into(ivIcon)
            // tvTemp.text = data.list[position].toString()

        }
    }
}

