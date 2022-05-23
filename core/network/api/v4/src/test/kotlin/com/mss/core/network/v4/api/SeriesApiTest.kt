package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.di.ApiV4Module
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.asc
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.desc
import org.junit.jupiter.api.Test

internal class SeriesApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideSeriesApi(testRetrofit)

    @Test
    fun getTeamsOrderedByTeamWins() = test {
        api.getTeams(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApiV4.TeamOrder.TeamWins.desc,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getTeamsOrderedByChampWins() = test {
        api.getTeams(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApiV4.TeamOrder.ChampionshipWins.asc,
            page = 0,
            size = 10,
        )
    }

    companion object {
        private const val SERIES = "formula-one"
    }
}