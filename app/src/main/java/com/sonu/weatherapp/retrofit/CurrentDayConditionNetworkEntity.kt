package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentDayConditionNetworkEntity(
    @SerializedName("text")
    @Expose
    var text: String,

    @SerializedName("icon")
    @Expose
    var icon: String,

    @SerializedName("code")
    @Expose
    var code: Int
)