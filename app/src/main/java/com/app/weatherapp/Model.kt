package com.app.weatherapp

data class Weather(val dt: Int = 0,
                   val coord: Coord,
                   val visibility: Int = 0,
                   val weather: List<WeatherItem>?,
                   val name: String = "",
                   val cod: Int = 0,
                   val main: Main,
                   val clouds: Clouds,
                   val id: Int = 0,
                   val sys: Sys,
                   val base: String = "",
                   val wind: Wind)


data class Coord(val lon: Double = 0.0,
                 val lat: Double = 0.0)


data class Wind(val deg: Double = 0.0,
                val speed: Double = 0.0)


data class WeatherItem(val icon: String = "",
                       val description: String = "",
                       val main: String = "",
                       val id: Int = 0)


data class Clouds(val all: Int = 0)


data class Sys(val country: String = "",
               val sunrise: Int = 0,
               val sunset: Int = 0,
               val id: Int = 0,
               val type: Int = 0,
               val message: Double = 0.0)


data class Main(val temp: Double = 0.0,
                val tempMin: Double = 0.0,
                val humidity: Int = 0,
                val pressure: Int = 0,
                val tempMax: Double = 0.0)


