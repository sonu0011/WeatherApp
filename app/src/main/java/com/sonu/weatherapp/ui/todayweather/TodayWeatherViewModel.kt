package com.sonu.weatherapp.ui.todayweather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.repository.WeatherRepository
import com.sonu.weatherapp.retrofit.CurrentDayWeatherInfoNetworkEntity
import com.sonu.weatherapp.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TodayWeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<CurrentDayWeatherInfoNetworkEntity>> =
        MutableLiveData()

    val dataState: LiveData<DataState<CurrentDayWeatherInfoNetworkEntity>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStateEvent(mainStateEvent: MainStateEvent, cityName: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetWeatherInfo -> {
                    weatherRepository.getWeatherInfo(cityName)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainStateEvent {

        object GetWeatherInfo : MainStateEvent()
    }


}