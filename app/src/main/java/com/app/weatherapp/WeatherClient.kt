package com.app.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherClient {
    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") q:String,@Query("APPID") apiKey:String): Call<Weather>
}