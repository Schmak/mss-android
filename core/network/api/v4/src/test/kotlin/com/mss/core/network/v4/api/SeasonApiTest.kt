package com.mss.core.network.v4.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v4.di.ApiV4Module
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.asc
import org.junit.jupiter.api.Test

internal class SeasonApiTest : AbstractApiTest() {
    private val api = ApiV4Module.provideSeasonApi(testRetrofit)

    @Test
    fun getTeams() = test {
        api.getTeams(
            season = "formula-one_2022",
            hideZeros = false,
            page = 0,
            size = 10,
            orderBy = SeasonApiV4.TeamOrder.ChampionshipPosition.asc,
        )
    }
}