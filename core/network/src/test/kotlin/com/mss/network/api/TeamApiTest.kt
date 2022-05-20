package com.mss.network.api

import com.mss.annotation.IntegrationTest
import com.mss.network.di.ApiModule
import com.mss.network.model.PageDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import utils.testRetrofit

@ExperimentalCoroutinesApi
@IntegrationTest
internal class TeamApiTest {
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

    private fun test(apiCall: suspend () -> Any) = runTest {
        when (val actual = assertDoesNotThrow { apiCall() }) {
            is PageDto<*> -> assertThat(actual.content).isNotEmpty
            is List<*> -> assertThat(actual).isNotEmpty
            else -> assertThat(actual).isNotNull
        }
    }
}