package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentDayWeatherInfoNetworkEntity(
    @SerializedName("location")
    @Expose
    var location: CurrentLocationNetworkEntity,


    @SerializedName("current")
    @Expose
    var current: CurrentDayInfoNetworkEntity,

    )