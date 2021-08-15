package com.sonu.weatherapp.utils

import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.retrofit.PostOfficeNetworkEntity
import javax.inject.Inject

class NetworkMapper @Inject constructor() {

    fun mapFromNetworkEntityList(postOffices: List<PostOfficeNetworkEntity>): List<DistrictAndState> {
        return postOffices.map { mapFromEntity(it) }
    }

    fun mapFromEntity(office: PostOfficeNetworkEntity): DistrictAndState {
        return DistrictAndState(office.district, office.state)
    }
}