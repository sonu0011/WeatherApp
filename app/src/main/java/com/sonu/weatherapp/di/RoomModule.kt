package com.sonu.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.sonu.weatherapp.room.WeatherDao
import com.sonu.weatherapp.room.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): WeatherDatabase {
        return Room
            .databaseBuilder(
                context,
                WeatherDatabase::class.java,
                WeatherDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.getWeatherDao()
    }
}