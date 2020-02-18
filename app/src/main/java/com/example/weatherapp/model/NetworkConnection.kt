package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkConnection {

    @GET("weather")
    fun getCurrentWeather(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherDataList>

    @GET("forecast")
    fun getWeatherForecast(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherDataList>

    //https://samples.openweathermap.org/data/2.5/
// forecast?zip=232323&appid=b6907d289e10d714a6e88b30761fae22
}

data class Network (var url: String){
    fun initRetrofit(): NetworkConnection {
        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NetworkConnection::class.java)
    }
}