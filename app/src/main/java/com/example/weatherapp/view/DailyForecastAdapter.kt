package com.example.weatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherDataList


class DailyForecastAdapter :
    RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    var dataSet: WeatherDataList = WeatherDataList(emptyList())

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

    fun setDailyWeatherData(t : WeatherDataList){
        dataSet = t
        notifyDataSetChanged()
        //Log.d("TESTING TO SEE OUTPUT: ", dataSet.toString())

    }

    override fun getItemCount() = dataSet.list.size

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {


        //val hourlyRecyclerAdapter = HourlyForecastAdapter()
        //holder.hourlyRecyclerView.layoutManager = GridLayoutManager(holder.hourlyRecyclerView.context,4)
       // Log.d("SETTING THE OUTPUT: ", dataSet.toString())

        var currentData : String = dataSet.list[position].dt_txt.substring(0,10)
        var nextData : String = dataSet.list[position+1].dt_txt.substring(0,10)


        if(position != 0){
            Log.d("POSITION VALUE", position.toString())


            if(currentData.equals(nextData)){
                onBindViewHolder(holder,position+1)
            }
            else{
                val hourlyRecyclerAdapter = HourlyForecastAdapter()
                holder.onBind(dataSet,position)
                Log.d("DAILY RECYCLER ", dataSet.list[position].dt_txt + "   " + dataSet.list[position+1].dt_txt)
                //Log.d("DATE ", dataSet.list[position].dt_txt + "   " + position)
                holder.hourlyRecyclerView.layoutManager =
                    GridLayoutManager(holder.hourlyRecyclerView.context, 4)
                holder.hourlyRecyclerView.adapter = hourlyRecyclerAdapter
                hourlyRecyclerAdapter.setHourlyWeatherData(dataSet)
            }

        }
        else{
            val hourlyRecyclerAdapter = HourlyForecastAdapter()

            holder.onBind(dataSet,position)
            Log.d("DAILY RECYCLER ", dataSet.list[position].dt_txt + "   " + dataSet.list[position+1].dt_txt)
            //Log.d("DATE ", dataSet.list[position].dt_txt + "   " + position)
            holder.hourlyRecyclerView.layoutManager =
                GridLayoutManager(holder.hourlyRecyclerView.context, 4)
            holder.hourlyRecyclerView.adapter = hourlyRecyclerAdapter
            hourlyRecyclerAdapter.setHourlyWeatherData(dataSet)
        }
    }

    class DailyForecastViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvDate: TextView =
            itemView.findViewById(R.id.tv_date)

        var hourlyRecyclerView : RecyclerView = itemView.findViewById(R.id.hourly_recycler)


        fun onBind(data: WeatherDataList, position: Int) {
            tvDate.text = data.list[position].dt_txt.substring(0,10) + "   " + position





        }
    }
}

/*
val dailyRecyclerAdapter  = DailyForecastAdapter()
        daily_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        daily_recycler.adapter  = dailyRecyclerAdapter

               val hourlyRecyclerAdapter = HourlyForecastAdapter()
        hourlyRecyclerAdapter.setHourlyWeatherData(t)

        val dailyRecyclerAdapter  = DailyForecastAdapter()
        daily_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        daily_recycler.adapter  = dailyRecyclerAdapter


                val hourlyRecyclerAdapter = HourlyForecastAdapter()
                hourlyRecyclerView.layoutManager = GridLayoutManager(hourlyRecyclerView.context,4)
                 hourly_recycler.adapter  = hourlyRecyclerAdapter
 */
