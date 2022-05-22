package com.mss.core.di

import com.mss.core.coroutines.di.qualifiers.IoDispatcher
import com.mss.core.data.repository.DriverRepositoryImpl
import com.mss.core.data.repository.SeriesRepositoryImpl
import com.mss.core.data.repository.TeamRepositoryImpl
import com.mss.core.data.repository.VenueRepositoryImpl
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.repository.VenueRepository
import com.mss.network.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesSeriesRepository(
        seriesApi: SeriesApi,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): SeriesRepository =
        SeriesRepositoryImpl(seriesApi, dispatcher)

    @Provides
    @Singleton
    fun providesDriverRepository(
        seriesApi: SeriesApi,
        seasonApi: SeasonApi,
        driverApi: DriverApi,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): DriverRepository =
        DriverRepositoryImpl(
            seriesApi = seriesApi,
            seasonApi = seasonApi,
            driverApi = driverApi,
            dispatcher = dispatcher
        )

    @Provides
    @Singleton
    fun providesTeamRepository(
        seriesApi: SeriesApi,
        seasonApi: SeasonApi,
        teamApi: TeamApi,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): TeamRepository =
        TeamRepositoryImpl(
            seriesApi = seriesApi,
            seasonApi = seasonApi,
            teamApi = teamApi,
            dispatcher = dispatcher
        )

    @Provides
    @Singleton
    fun providesVenueRepository(
        venueApi: VenueApi,
        seasonApi: SeasonApi,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): VenueRepository =
        VenueRepositoryImpl(
            seasonApi = seasonApi,
            venueApi = venueApi,
            dispatcher = dispatcher
        )
}