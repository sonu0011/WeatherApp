package com.sonu.weatherapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DistrictAndStateEntity::class], version = 1,exportSchema = false)
abstract class WeatherDatabase  : RoomDatabase(){
    abstract fun getWeatherDao(): WeatherDao

    companion object {
        val DATABASE_NAME = "weather_db"
    }
}