package com.sonu.weatherapp.ui.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.repository.WeatherRepository
import com.sonu.weatherapp.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegistrationViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<DistrictAndState>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<DistrictAndState>>>
        get() = _dataState


    @ExperimentalCoroutinesApi
    fun setStateEvent(mainStateEvent: MainStateEvent, pinCode: String) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetDistrictAndStateEvent -> {
                    weatherRepository.getDistrictAndStates(pinCode)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {

                }
            }
        }
    }

    sealed class MainStateEvent {

        object GetDistrictAndStateEvent : MainStateEvent()

        object None : MainStateEvent()
    }


}