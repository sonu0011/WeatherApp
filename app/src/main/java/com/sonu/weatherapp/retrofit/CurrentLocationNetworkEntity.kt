package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentLocationNetworkEntity(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("region")
    @Expose
    var region: String,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("lat")
    @Expose
    var lat: Double,

    @SerializedName("lon")
    @Expose
    var lon: Double,

    @SerializedName("tz_id")
    @Expose
    var tzId: String,

    @SerializedName("localtime_epoch")
    @Expose
    var localtimeEpoch: Int,

    @SerializedName("localtime")
    @Expose
    var localtime: String
)