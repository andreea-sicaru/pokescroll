package com.example.pokescroll.di

import com.example.pokescroll.data.remote.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Centralizes all networking configuration.
 *
 * eg. If we need to add a timeout or a logging interceptor later, we only change it here.
 */

@Module // Tells Hilt, "This is a place where I'll tell you how to create things."
@InstallIn(SingletonComponent::class) // Tells Hilt, "Keep these objects alive for the entire life of the app."
object NetworkModule {

    @Provides // The actual recipe for creating the object.
    @Singleton // Ensure only one instance of Retrofit exists to save memory.
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder()
            .baseUrl(PokeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(
                PokeApi::class.java
            )
    }
}