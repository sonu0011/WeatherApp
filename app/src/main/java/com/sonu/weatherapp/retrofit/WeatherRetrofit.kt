package com.sonu.weatherapp.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRetrofit {

    @GET("pincode/{pincode}")
    suspend fun getDistrictAndState(
        @Path("pincode") pinCode: String
    ): List<DistrictAndStateNetworkEntity>

    @GET("v1/current.json")
    suspend fun getCurrentDayWeatherInfo(
        @Query("key") key: String,
        @Query("q") cityName: String
    ): CurrentDayWeatherInfoNetworkEntity

}