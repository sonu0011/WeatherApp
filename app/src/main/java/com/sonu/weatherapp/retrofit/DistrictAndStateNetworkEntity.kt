package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DistrictAndStateNetworkEntity(
    @SerializedName("Message")
    @Expose
    var message: String,

    @SerializedName("Status")
    @Expose
    var status: String,

    @SerializedName("PostOffice")
    @Expose
    val postOffices: List<PostOfficeNetworkEntity>
)