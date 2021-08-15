package com.sonu.weatherapp.di

import com.sonu.weatherapp.repository.WeatherRepository
import com.sonu.weatherapp.retrofit.WeatherRetrofit
import com.sonu.weatherapp.room.WeatherDao
import com.sonu.weatherapp.utils.CacheMapper
import com.sonu.weatherapp.utils.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        weatherDao: WeatherDao,
        weatherRetrofit: WeatherRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): WeatherRepository {
        return WeatherRepository(weatherDao, weatherRetrofit, cacheMapper, networkMapper)
    }
}