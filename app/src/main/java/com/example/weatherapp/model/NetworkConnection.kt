package com.example.weatherapp.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String
    ): Response<WeatherDataList>

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String
    ): Response<WeatherDataList>

//https://api.openweathermap.org/data/2.5/forecast?zip=11221&appid=6f969fe2d1c61da1e7212cd11fcf7a7f

    companion object {

        fun create(): WeatherApiService {
/*
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()*/

            val retrofit =
                Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    //.client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}

