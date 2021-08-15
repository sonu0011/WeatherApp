package com.sonu.weatherapp.utils

import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.retrofit.DistrictAndStateNetworkEntity
import com.sonu.weatherapp.room.DistrictAndStateEntity
import javax.inject.Inject

class CacheMapper @Inject constructor() {

    fun mapFromDistrictAndStateToEntity(districtAndState: DistrictAndState): DistrictAndStateEntity {
        return DistrictAndStateEntity(districtAndState.district, districtAndState.state)
    }

    fun mapFromEntityToDistrictAndState(districtAndStates: List<DistrictAndStateEntity>): List<DistrictAndState> {

        return districtAndStates.map { mapFromEntityToDistrictAndState1(it) }
    }

    fun mapFromEntityToDistrictAndState1(districtAndState: DistrictAndStateEntity): DistrictAndState {
        return DistrictAndState(districtAndState.district, districtAndState.state)
    }
}