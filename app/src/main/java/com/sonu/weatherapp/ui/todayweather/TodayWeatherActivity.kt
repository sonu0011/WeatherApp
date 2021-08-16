package com.sonu.weatherapp.ui.todayweather

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.sonu.weatherapp.R
import com.sonu.weatherapp.retrofit.CurrentDayWeatherInfoNetworkEntity
import com.sonu.weatherapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_today_weather.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class TodayWeatherActivity : AppCompatActivity() {
    private val todayWeatherViewModel: TodayWeatherViewModel by viewModels()

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_weather)
        btn_show_result.setOnClickListener {
            if (TextUtils.isEmpty(city_name.editText!!.text.toString())) {
                Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            todayWeatherViewModel.setStateEvent(TodayWeatherViewModel.MainStateEvent.GetWeatherInfo , city_name.editText!!.text.toString())


        }
        subscribeObservers()

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun subscribeObservers() {
        todayWeatherViewModel.dataState.observe(this, Observer {
            when (it) {
                is DataState.Success<CurrentDayWeatherInfoNetworkEntity> -> {
                    displayProgressBar(false)
                    Log.d("Today", "subscribeObservers: "+it.data)
                    getCurrentWeatherInfo(it.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {

        if (message != null) error_text.text = message else error_text.text = "Unknown error."
    }

    private fun getCurrentWeatherInfo(data: CurrentDayWeatherInfoNetworkEntity) {
        temp_celsius.text = "${temp_celsius.text}{${data.current.temp_c}}"
        temp_Fahrenheit.text = "${temp_Fahrenheit.text}{${data.current.temp_f}}"
        latitude.text = "${latitude.text}{${data.location.lat}}"
        longitude.text = "${longitude.text}{${data.location.lon}}"
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar_weather.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


}