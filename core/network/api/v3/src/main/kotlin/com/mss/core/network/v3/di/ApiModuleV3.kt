package com.mss.core.network.v3.di

import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.api.VenueApiV3
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
    fun provideSeriesApi(retrofit: Retrofit): SeriesApiV3 = retrofit.create()

    @Provides
    fun provideSeasonApi(retrofit: Retrofit): SeasonApiV3 = retrofit.create()

    @Provides
    fun provideDriverApi(retrofit: Retrofit): DriverApiV3 = retrofit.create()

    @Provides
    fun provideVenueApi(retrofit: Retrofit): VenueApiV3 = retrofit.create()
}