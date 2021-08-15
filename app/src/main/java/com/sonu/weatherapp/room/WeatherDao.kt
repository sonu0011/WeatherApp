package com.sonu.weatherapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stateEntity: DistrictAndStateEntity): Long

    @Query("SELECT * FROM districtAndStates")
    suspend fun getDistrictsAndStates(): List<DistrictAndStateEntity>
}