package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.api.Test

internal class SeasonApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideSeasonApi(testRetrofit)

    @Test
    fun getDrivers() = test {
        api.getDrivers(
            season = SEASON,
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApi.DriverOrder.ChampionshipPosition,
            orderDescending = false,
        )
    }

    @Test
    fun getVenues() = test {
        api.getVenues(
            season = SEASON,
            page = 0,
            size = 10,
        )
    }

    companion object {
        private const val SEASON = "formula-one_2022"
    }
}