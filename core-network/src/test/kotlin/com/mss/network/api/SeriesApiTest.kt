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
internal class SeriesApiTest {
    private val api = ApiModule.provideSeriesApi(testRetrofit)

    @Test
    fun getCategories() = test { api.getCategories() }

    @Test
    fun getRegions() = test { api.getRegions() }

    @Test
    fun getGoldenSeries() = test { api.getGoldenSeries() }

    @Test
    fun getLeadingSeries() = test {
        api.getSeries(
            filterIds = arrayOf("Active"),
            orderBy = SeriesApi.OrderBy.LastEventDate,
            orderDescending = true,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getRegionSeries() = test {
        api.getCollection(
            region = "Worldwide",
            category = null,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getCategorySeries() = test {
        api.getCollection(
            region = null,
            category = "Single Seater",
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