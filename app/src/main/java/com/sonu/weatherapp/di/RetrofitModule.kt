package com.sonu.weatherapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sonu.weatherapp.retrofit.WeatherRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideRetrofitClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Singleton
    @Provides
    @Named("postalRetrofit")
    fun provideRetrofit(gson: Gson  , okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.postalpincode.in/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    @Named("weatherRetrofit")
    fun provideWeatherRetrofit(gson: Gson  , okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    @Named("postalRetrofitInstance")
    fun providePostalRetrofit(@Named("postalRetrofit") retrofit: Retrofit.Builder): WeatherRetrofit {
        return retrofit
            .build()
            .create(WeatherRetrofit::class.java)
    }

    @Singleton
    @Provides
    @Named("weatherRetrofitInstance")
    fun provideWeatherRetrofitInstance(@Named("weatherRetrofit") retrofit: Retrofit.Builder): WeatherRetrofit {
        return retrofit
            .build()
            .create(WeatherRetrofit::class.java)
    }
}