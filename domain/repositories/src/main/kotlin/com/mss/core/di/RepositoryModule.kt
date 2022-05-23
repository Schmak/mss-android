package com.mss.core.di

import com.mss.core.coroutines.di.qualifiers.IoDispatcher
import com.mss.core.data.repository.*
import com.mss.core.domain.repository.*
import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.api.VenueApiV3
import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.api.SessionApiV4
import com.mss.core.network.v4.api.TeamApiV4
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
        seriesApiV3: SeriesApiV3,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): SeriesRepository =
        SeriesRepositoryImpl(seriesApiV3, dispatcher)

    @Provides
    @Singleton
    fun providesDriverRepository(
        seriesApiV3: SeriesApiV3,
        seasonApiV3: SeasonApiV3,
        driverApiV3: DriverApiV3,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): DriverRepository =
        DriverRepositoryImpl(
            seriesApiV3 = seriesApiV3,
            seasonApiV3 = seasonApiV3,
            driverApiV3 = driverApiV3,
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
        venueApiV3: VenueApiV3,
        seasonApiV3: SeasonApiV3,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): VenueRepository =
        VenueRepositoryImpl(
            seasonApiV3 = seasonApiV3,
            venueApiV3 = venueApiV3,
            dispatcher = dispatcher
        )

    @Provides
    @Singleton
    fun providesSessionRepository(
        sessionApiV4: SessionApiV4,
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
    ): SessionRepository =
        SessionRepositoryImpl(
            sessionApiV4 = sessionApiV4,
            dispatcher = dispatcher
        )
}