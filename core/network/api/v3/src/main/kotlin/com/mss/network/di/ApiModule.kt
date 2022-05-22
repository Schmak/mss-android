package com.mss.network.di

import com.mss.network.api.*
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

    @Provides
    fun provideSeasonApi(retrofit: Retrofit): SeasonApi = retrofit.create()

    @Provides
    fun provideDriverApi(retrofit: Retrofit): DriverApi = retrofit.create()

    @Provides
    fun provideTeamApi(retrofit: Retrofit): TeamApi = retrofit.create()

    @Provides
    fun provideVenueApi(retrofit: Retrofit): VenueApi = retrofit.create()
}