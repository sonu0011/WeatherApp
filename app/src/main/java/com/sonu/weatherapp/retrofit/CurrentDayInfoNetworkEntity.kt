package com.sonu.weatherapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentDayInfoNetworkEntity(
    @SerializedName("last_updated_epoch")
    @Expose
    var last_updated_epoch: Int,

    @SerializedName("last_updated")
    @Expose
    var last_updated: String,

    @SerializedName("temp_c")
    @Expose
    var temp_c: Double,

    @SerializedName("temp_f")
    @Expose
    var temp_f: Double,

    @SerializedName("is_day")
    @Expose
    var is_day: Int,

    @SerializedName("condition")
    @Expose
    var condition: CurrentDayConditionNetworkEntity,

    @SerializedName("wind_mph")
    @Expose
    var wind_mph: Double,

    @SerializedName("wind_kph")
    @Expose
    var wind_kph: Double,

    @SerializedName("wind_degree")
    @Expose
    var wind_degree: Int,

    @SerializedName("wind_dir")
    @Expose
    var wind_dir: String,

    @SerializedName("pressure_mb")
    @Expose
    var pressure_mb: Int,

    @SerializedName("pressure_in")
    @Expose
    var pressure_in: Double,

    @SerializedName("precip_mm")
    @Expose
    var precip_mm: Int,

    @SerializedName("precip_in")
    @Expose
    var precip_in: Int,

    @SerializedName("humidity")
    @Expose
    var humidity: Int,

    @SerializedName("cloud")
    @Expose
    var cloud: Int,

    @SerializedName("feelslike_c")
    @Expose
    var feelslike_c: Double,

    @SerializedName("feelslike_f")
    @Expose
    var feelslike_f: Double,

    @SerializedName("vis_km")
    @Expose
    var vis_km: Int,

    @SerializedName("vis_miles")
    @Expose
    var vis_miles: Int,

    @SerializedName("uv")
    @Expose
    var uv: Int,

    @SerializedName("gust_mph")
    @Expose
    var gust_mph: Double,

    @SerializedName("gust_kph")
    @Expose
    var gust_kph: Double
)