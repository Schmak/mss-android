package com.mss.core.data.repository

import com.mss.core.domain.DriverItem
import com.mss.core.domain.create
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.model.DriverItemDto
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.create
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class DriverRepositoryImplTest : AbstractRepositoryTest() {
    private val seriesApiV3: SeriesApiV3 = mockk()
    private val driverApiV3: DriverApiV3 = mockk()
    private val seasonApiV3: SeasonApiV3 = mockk()
    private val repository: DriverRepository =
        DriverRepositoryImpl(
            seriesApiV3 = seriesApiV3,
            seasonApiV3 = seasonApiV3,
            driverApiV3 = driverApiV3,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeriesDrivers",
            apiQuery = {
                seriesApiV3.getDrivers(
                    series = SERIES,
                    hideZeros = true,
                    orderBy = SeriesApiV3.DriverOrder.Wins,
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
                seasonApiV3.getDrivers(
                    season = SEASON,
                    hideZeros = false,
                    orderBy = SeasonApiV3.DriverOrder.ChampionshipPosition,
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
                driverApiV3.getCollection(
                    DriverApiV3.DriverCollection.RecentWinners,
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