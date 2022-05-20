package com.mss.network.di

import com.mss.network.api.SeriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideSeriesApi(retrofit: Retrofit): SeriesApi = retrofit.create()
}