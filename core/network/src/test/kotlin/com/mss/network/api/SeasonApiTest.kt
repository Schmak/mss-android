package com.mss.network.api

import com.mss.annotation.IntegrationTest
import com.mss.network.di.ApiModule
import com.mss.network.model.PageDto
import com.mss.network.model.sort.OrderByDto.Companion.asc
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import utils.testRetrofit

@ExperimentalCoroutinesApi
@IntegrationTest
internal class SeasonApiTest {
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

    private fun test(apiCall: suspend () -> Any) = runTest {
        when (val actual = assertDoesNotThrow { apiCall() }) {
            is PageDto<*> -> assertThat(actual.content).isNotEmpty
            is List<*> -> assertThat(actual).isNotEmpty
            else -> assertThat(actual).isNotNull
        }
    }
}