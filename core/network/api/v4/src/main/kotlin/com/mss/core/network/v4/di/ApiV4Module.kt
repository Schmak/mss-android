package com.mss.core.network.v4.di

import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.api.SessionApiV4
import com.mss.core.network.v4.api.TeamApiV4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiV4Module {
    @Provides
    fun provideSessionApi(retrofit: Retrofit): SessionApiV4 = retrofit.create()

    @Provides
    fun provideSeriesApi(retrofit: Retrofit): SeriesApiV4 = retrofit.create()

    @Provides
    fun provideSeasonApi(retrofit: Retrofit): SeasonApiV4 = retrofit.create()

    @Provides
    fun provideTeamApi(retrofit: Retrofit): TeamApiV4 = retrofit.create()
}