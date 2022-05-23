package com.mss.core.network.v3.di

import com.mss.core.network.v3.api.DriverApi
import com.mss.core.network.v3.api.SeasonApi
import com.mss.core.network.v3.api.SeriesApi
import com.mss.core.network.v3.api.VenueApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiModuleV3 {
    @Provides
    fun provideSeriesApi(retrofit: Retrofit): SeriesApi = retrofit.create()

    @Provides
    fun provideSeasonApi(retrofit: Retrofit): SeasonApi = retrofit.create()

    @Provides
    fun provideDriverApi(retrofit: Retrofit): DriverApi = retrofit.create()

    @Provides
    fun provideVenueApi(retrofit: Retrofit): VenueApi = retrofit.create()
}