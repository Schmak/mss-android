package com.mss.core.network.v3.api

import com.mss.core.network.utils.testRetrofit
import com.mss.core.network.v3.di.ApiModule
import org.junit.jupiter.api.Test

internal class SeriesApiTest : AbstractApiTest() {
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
            orderBy = SeriesApi.SeriesOrder.LastEventDate,
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

    @Test
    fun getDriversOrderedByDriverWins() = test {
        api.getDrivers(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApi.DriverOrder.Wins,
            orderDescending = false,
            page = 0,
            size = 10,
        )
    }

    @Test
    fun getDriversOrderedByChampWins() = test {
        api.getDrivers(
            series = SERIES,
            hideZeros = true,
            orderBy = SeriesApi.DriverOrder.ChampionshipWins,
            orderDescending = true,
            page = 0,
            size = 10,
        )
    }

    companion object {
        private const val SERIES = "formula-one"
    }
}