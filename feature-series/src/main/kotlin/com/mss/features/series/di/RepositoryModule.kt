package com.mss.features.series.di

import com.mss.features.series.data.repository.SeriesRepositoryImpl
import com.mss.features.series.domain.repository.SeriesRepository
import com.mss.network.api.SeriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesSeriesRepository(seriesApi: SeriesApi): SeriesRepository =
        SeriesRepositoryImpl(seriesApi)
}