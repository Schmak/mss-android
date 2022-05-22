package com.mss.network.api

import com.mss.network.di.ApiModule
import org.junit.jupiter.api.Test
import utils.testRetrofit

internal class TeamApiTest : AbstractApiTest() {
    private val api = ApiModule.provideTeamApi(testRetrofit)

    @Test
    fun getRecentWinners() = test {
        api.getCollection(
            collection = TeamApi.TeamCollection.RecentWinners,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getChampionshipLeaders() = test {
        api.getCollection(
            collection = TeamApi.TeamCollection.ChampionshipLeaders,
            page = 0,
            size = 10,
        )
    }
}