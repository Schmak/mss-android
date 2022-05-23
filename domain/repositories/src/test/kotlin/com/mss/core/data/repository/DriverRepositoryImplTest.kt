package com.mss.core.data.repository

import com.mss.annotation.UnitTest
import com.mss.core.domain.DriverItem
import com.mss.core.domain.create
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.core.network.v3.api.DriverApi
import com.mss.core.network.v3.api.SeasonApi
import com.mss.core.network.v3.api.SeriesApi
import com.mss.core.network.v3.model.DriverItemDto
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.create
import com.mss.test.BaseRepositoryTest
import com.mss.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class DriverRepositoryImplTest : BaseRepositoryTest() {
    private val seriesApi: SeriesApi = mockk()
    private val driverApi: DriverApi = mockk()
    private val seasonApi: SeasonApi = mockk()
    private val repository: DriverRepository =
        DriverRepositoryImpl(
            seriesApi = seriesApi,
            seasonApi = seasonApi,
            driverApi = driverApi,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeriesDrivers",
            apiQuery = {
                seriesApi.getDrivers(
                    series = SERIES,
                    hideZeros = true,
                    orderBy = SeriesApi.DriverOrder.Wins,
                    orderDescending = true,
                    page = 8,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = apiResponse(9),
            repositoryQuery = {
                repository.getSeriesDrivers(
                    series = SERIES,
                    orderBy = DriverRepository.SeriesDriverOrder.Wins.desc,
                    pageable = page(8)
                )
            },
            expectedRepositoryResponse = repoResponse(9),
        ),
        TestCase(
            name = "getSeasonDrivers",
            apiQuery = {
                seasonApi.getDrivers(
                    season = SEASON,
                    hideZeros = false,
                    orderBy = SeasonApi.DriverOrder.ChampionshipPosition,
                    orderDescending = false,
                    page = 12,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = apiResponse(11),
            repositoryQuery = {
                repository.getSeasonDrivers(
                    season = SEASON,
                    orderBy = DriverRepository.SeasonDriverOrder.ChampionshipPosition.asc,
                    pageable = page(12)
                )
            },
            expectedRepositoryResponse = repoResponse(11),
        ),
        TestCase(
            name = "getCollection",
            apiQuery = {
                driverApi.getCollection(
                    DriverApi.DriverCollection.RecentWinners,
                    page = 16,
                    size = PAGE_SIZE
                )
            },
            apiResponse = apiResponse(15),
            repositoryQuery = {
                repository.getCollection(
                    collection = DriverRepository.Collection.RecentWinners,
                    pageable = page(16)
                )
            },
            expectedRepositoryResponse = repoResponse(15),
        ),
    )

    companion object {
        private const val SEASON = "F1 2022"
        private const val SERIES = "Formula One"
        private const val DRIVER_NAME = "Ferrari"
        private const val DRIVER_SLUG = "ferrari"
        private const val PAGE_SIZE = 27
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val DRIVER_ITEM = DriverItem.create(
            slug = DRIVER_SLUG,
            name = DRIVER_NAME,
            picture = null,
            lastTeam = null,
            nationality = null,
        )

        private val DRIVER_ITEM_DTO = DriverItemDto.create(
            uuid = DRIVER_SLUG,
            name = DRIVER_NAME,
            picture = null,
            lastTeam = null,
            nationalities = emptyList()
        )

        private fun repoResponse(page: Int) =
            Page(
                content = listOf(DRIVER_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = page,
            )

        private fun apiResponse(page: Int) =
            PageDto.create(
                content = listOf(DRIVER_ITEM_DTO),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                number = page,
            )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}