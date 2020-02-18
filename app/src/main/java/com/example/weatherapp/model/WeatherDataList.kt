package com.example.weatherapp.model

data class WeatherDataList(
    var list : List<WeatherData>
)

data class WeatherData(
    var dt : Long,
    var dt_txt : String,
    var main : Main,
    var weather : List<Weather>
)

data class Main(
    var temp : Float
)

data class Weather(
    var icon : String
)

data class City(
    var name : String,
    var country : String
)



