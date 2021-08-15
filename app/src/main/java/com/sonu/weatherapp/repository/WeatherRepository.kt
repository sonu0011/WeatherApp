package com.sonu.weatherapp.repository

import com.sonu.weatherapp.retrofit.WeatherRetrofit
import com.sonu.weatherapp.room.WeatherDao
import com.sonu.weatherapp.utils.CacheMapper
import com.sonu.weatherapp.utils.DataState
import com.sonu.weatherapp.utils.NetworkMapper
import kotlinx.coroutines.flow.flow

class WeatherRepository(
    private val weatherDao: WeatherDao,
    private val weatherRetrofit: WeatherRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getDistrictAndStates(pinCode: String) = flow {
        emit(DataState.Loading)
        try {
            val districtsAndStates = weatherRetrofit.getDistrictAndState(pinCode);
            val lists =
                networkMapper.mapFromNetworkEntityList(districtsAndStates.get(0).postOffices)

            for (d in lists) {
                weatherDao.insert(cacheMapper.mapFromDistrictAndStateToEntity(d))
            }
            val requiredData = weatherDao.getDistrictsAndStates()

            emit(DataState.Success(cacheMapper.mapFromEntityToDistrictAndState(requiredData)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}