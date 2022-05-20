package com.mss.core.data.repository

import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.SeriesItem
import com.mss.core.domain.create
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.SeasonReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create
import com.mss.core.domain.repository.SeriesRepository
import com.mss.network.api.SeriesApi
import com.mss.network.model.PageDto
import com.mss.network.model.SeriesInfoDto
import com.mss.network.model.SeriesItemDto
import com.mss.network.model.create
import com.mss.network.model.ref.EventReferenceDto
import com.mss.network.model.ref.SeasonReferenceDto
import com.mss.network.model.ref.SeriesReferenceDto
import com.mss.network.model.ref.create
import com.mss.test.BaseRepositoryTest
import com.mss.utils.DEFAULT_LOCAL_DATE
import com.mss.utils.coroutines.TestDispatchers
import io.mockk.mockk
import kotlin.time.Duration.Companion.days

internal class SeriesRepositoryImplTest : BaseRepositoryTest() {
    private val seriesApi: SeriesApi = mockk()
    private val repository: SeriesRepository =
        SeriesRepositoryImpl(
            api = seriesApi,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getCategories",
            apiQuery = { seriesApi.getCategories() },
            apiResponse = CATEGORIES,
            repositoryQuery = { repository.getCategories() },
            expectedRepositoryResponse = CATEGORIES,
        ),
        TestCase(
            name = "getRegions",
            apiQuery = { seriesApi.getRegions() },
            apiResponse = REGIONS,
            repositoryQuery = { repository.getRegions() },
            expectedRepositoryResponse = REGIONS,
        ),
        TestCase(
            name = "getLeadingSeries",
            apiQuery = { seriesApi.getGoldenSeries() },
            apiResponse = listOf(
                SeriesInfoDto.create(
                    series = SeriesReferenceDto.create(
                        name = SERIES,
                        picture = "$SERIES pic",
                        uuid = "$SERIES slug",
                    ),
                    currentSeason = SeasonReferenceDto.create(
                        name = SEASON,
                        uuid = "$SEASON slug"
                    )
                )
            ),
            repositoryQuery = { repository.getLeadingSeries() },
            expectedRepositoryResponse = listOf(
                SeriesInfo.create(
                    series = SeriesReference.create(
                        name = SERIES,
                        picture = "$SERIES pic",
                        slug = "$SERIES slug",
                    ),
                    currentSeason = SeasonReference.create(
                        name = SEASON,
                        slug = "$SEASON slug"
                    )
                )
            )
        ),
        TestCase(
            name = "getCollection", apiQuery = {
                seriesApi.getCollection(
                    region = REGIONS[0],
                    category = CATEGORIES[0],
                    page = 12,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = PageDto.create(
                listOf(SERIES_ITEM_DTO),
                totalPages = TOTAL_PAGES,
                totalElements = TOTAL_ELEMENTS,
                number = 12,
            ),
            repositoryQuery = {
                repository.getCollection(
                    region = REGIONS[0], category = CATEGORIES[0], pageable = page(12)
                )
            },
            expectedRepositoryResponse = Page(
                content = listOf(EXPECTED_SERIES_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = 12
            )
        ),
        TestCase(
            name = "getMostRecent",
            apiQuery = {
                seriesApi.getSeries(
                    page = 23,
                    size = PAGE_SIZE,
                    filterIds = any(),
                    orderBy = any(),
                    orderDescending = any()
                )
            },
            apiResponse = PageDto.create(
                content = listOf(SERIES_ITEM_DTO),
                totalPages = TOTAL_PAGES,
                totalElements = TOTAL_ELEMENTS,
                number = 23,
            ),
            repositoryQuery = { repository.getMostRecent(pageable = page(23)) },
            expectedRepositoryResponse = Page(
                content = listOf(EXPECTED_SERIES_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = 23,
            ),
        ),
    )

    private companion object {
        private const val SERIES = "Formula One"
        private const val SEASON = "F1 2022"
        private const val PAGE_SIZE = 17
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val CATEGORIES = listOf("Single Seater", "Motorcycle")
        private val REGIONS = listOf("Worldwide", "Europe", "United States")

        private val SERIES_ITEM_DTO = SeriesItemDto.create(
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = EventReferenceDto.create(
                name = "$SERIES event",
            ),
            lastEventDate = DEFAULT_LOCAL_DATE.toEpochDay().days.inWholeSeconds
        )
        private val EXPECTED_SERIES_ITEM = SeriesItem.create(
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = SeriesItem.LastEvent(
                name = "$SERIES event",
                date = DEFAULT_LOCAL_DATE,
            )
        )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}