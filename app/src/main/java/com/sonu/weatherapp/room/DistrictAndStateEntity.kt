package com.sonu.weatherapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "districtAndStates")
class DistrictAndStateEntity(
    @ColumnInfo(name = "districtName")
    val district: String,

    @ColumnInfo(name = "stateName")
    val state: String
) {

    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int? = null
}