package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.di.ApiV4Module
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.asc
import org.junit.jupiter.api.Test

internal class SeasonApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideSeasonApi(testRetrofit)

    @Test
    fun getTeams() = assertIsNotEmpty {
        api.getTeams(
            season = SEASON,
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApiV4.TeamOrder.ChampionshipPosition.asc,
        )
    }

    @Test
    fun getMissingTeams() = assertIsEmpty {
        api.getTeams(
            season = MISSING_SEASON,
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApiV4.TeamOrder.ChampionshipPosition.asc,
        )
    }

    companion object {
        private const val SEASON = "formula-one_2022"
        private const val MISSING_SEASON = "formula-one_202"
    }
}