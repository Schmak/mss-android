package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.api.Test

internal class SeasonApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideSeasonApi(testRetrofit)

    @Test
    fun getDrivers() = assertIsNotEmpty {
        api.getDrivers(
            season = SEASON,
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApiV3.DriverOrder.ChampionshipPosition,
            orderDescending = false,
        )
    }

    @Test
    fun getMissingDrivers() = assertIsEmpty {
        api.getDrivers(
            season = MISSING_SEASON,
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApiV3.DriverOrder.ChampionshipPosition,
            orderDescending = false,
        )
    }

    @Test
    fun getVenues() = assertIsNotEmpty {
        api.getVenues(
            season = SEASON,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getMissingVenues() = assertIsEmpty {
        api.getVenues(
            season = MISSING_SEASON,
            page = 0,
            size = 10,
        )
    }

    companion object {
        private const val SEASON = "formula-one_2022"
        private const val MISSING_SEASON = "formula-one_202"
    }
}