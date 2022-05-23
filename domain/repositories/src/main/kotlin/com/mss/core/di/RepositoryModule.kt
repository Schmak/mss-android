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
import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.api.TeamApiV4
import com.mss.network.api.DriverApi
import com.mss.network.api.SeasonApi
import com.mss.network.api.SeriesApi
import com.mss.network.api.VenueApi
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
        seriesApiV4: SeriesApiV4,
        seasonApiV4: SeasonApiV4,
        teamApiV4: TeamApiV4,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): TeamRepository =
        TeamRepositoryImpl(
            seriesApiV4 = seriesApiV4,
            seasonApiV4 = seasonApiV4,
            teamApiV4 = teamApiV4,
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