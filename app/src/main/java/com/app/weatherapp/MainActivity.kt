package com.app.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.R.menu
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Context
import android.support.v7.widget.SearchView
import android.view.MenuInflater



class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            weatherClient.getWeather(it, API_KEY).enqueue(object : Callback<Weather>{
            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                print(t?.message)
            }

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                degree.text = "${(response?.body()?.main?.temp?.minus(KELVIN)?.toInt())} Degree"
                stat.text = response?.body()?.weather?.get(0)?.main
                name.text = response?.body()?.name
            }

        })
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    companion object {
        val URL = "http://api.openweathermap.org/"
        val API_KEY = "73026360cf33abd5d66b18edc8f880b5"
        val KELVIN = 273.15
        fun create() : WeatherClient{
            val retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val client = retrofit.create(WeatherClient::class.java)
            return client

        }
        val weatherClient by lazy { create() }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(this)
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
        }
        return super.onCreateOptionsMenu(menu)
    }
}
