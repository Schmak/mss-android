package com.mss.network.api

import com.mss.network.di.ApiModule
import com.mss.network.model.sort.OrderByDto.Companion.asc
import org.junit.jupiter.api.Test
import utils.testRetrofit

internal class SeasonApiTest : AbstractApiTest() {
    private val api = ApiModule.provideSeasonApi(testRetrofit)

    @Test
    fun getTeams() = test {
        api.getTeams(
            season = "formula-one_2022",
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApi.TeamOrder.ChampionshipPosition.asc,
        )
    }

    @Test
    fun getVenues() = test {
        api.getVenues(
            season = "formula-one_2022",
            page = 0,
            size = 10,
        )
    }
}