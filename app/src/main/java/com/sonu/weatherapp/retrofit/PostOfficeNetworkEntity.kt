package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostOfficeNetworkEntity(
    @SerializedName("Name")
    @Expose
    var name: String,

    @SerializedName("Description")
    @Expose
    var description: String,

    @SerializedName("PINCode")
    @Expose
    var pinCode: Int,

    @SerializedName("BranchType")
    @Expose
    var branchType: String,

    @SerializedName("DeliveryStatus")
    @Expose
    var deliveryStatus: String,

    @SerializedName("Circle")
    @Expose
    var circle: String,

    @SerializedName("District")
    @Expose
    var district: String,

    @SerializedName("Division")
    @Expose
    var division: String,

    @SerializedName("Region")
    @Expose
    var region: String,

    @SerializedName("State")
    @Expose
    var state: String,

    @SerializedName("Country")
    @Expose
    var country: String
)