package com.mss.network.di

import com.mss.network.api.SeasonApi
import com.mss.network.api.SeriesApi
import com.mss.network.api.TeamApi
import com.mss.network.api.VenueApi
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
    fun provideTeamApi(retrofit: Retrofit): TeamApi = retrofit.create()

    @Provides
    fun provideVenueApi(retrofit: Retrofit): VenueApi = retrofit.create()
}