package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModuleV3
import org.junit.jupiter.api.Test

internal class SeriesApiTest : AbstractApiTest() {
    private val api = ApiModuleV3.provideSeriesApi(testRetrofit)

    @Test
    fun getCategories() = assertIsNotEmpty { api.getCategories() }

    @Test
    fun getRegions() = assertIsNotEmpty { api.getRegions() }

    @Test
    fun getGoldenSeries() = assertIsNotEmpty { api.getGoldenSeries() }

    @Test
    fun getLeadingSeries() = assertIsNotEmpty {
        api.getSeries(
            filterIds = arrayOf("Active"),
            orderBy = SeriesApiV3.SeriesOrder.LastEventDate,
            orderDescending = true,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getRegionSeries() = assertIsNotEmpty {
        api.getCollection(
            region = "Worldwide",
            category = null,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getCategorySeries() = assertIsNotEmpty {
        api.getCollection(
            region = null,
            category = "Single Seater",
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getDriversOrderedByDriverWins() = assertIsNotEmpty {
        api.getDrivers(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApiV3.DriverOrder.Wins,
            orderDescending = false,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getDriversOrderedByChampWins() = assertIsNotEmpty {
        api.getDrivers(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApiV3.DriverOrder.ChampionshipWins,
            orderDescending = true,
            page = 0,
            size = 10,
        )
    }

    companion object {
        private const val SERIES = "formula-one"
    }
}