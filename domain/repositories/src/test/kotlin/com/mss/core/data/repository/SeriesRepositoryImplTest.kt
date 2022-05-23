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
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.SeriesInfoDto
import com.mss.core.network.v3.model.SeriesItemDto
import com.mss.core.network.v3.model.create
import com.mss.core.network.v3.model.ref.EventReferenceDto
import com.mss.core.network.v3.model.ref.SeasonReferenceDto
import com.mss.core.network.v3.model.ref.SeriesReferenceDto
import com.mss.core.network.v3.model.ref.create
import com.mss.core.test.utils.DEFAULT_LOCAL_DATE
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk
import kotlin.time.Duration.Companion.days

internal class SeriesRepositoryImplTest : AbstractRepositoryTest() {
    private val seriesApiV3: SeriesApiV3 = mockk()
    private val repository: SeriesRepository =
        SeriesRepositoryImpl(
            api = seriesApiV3,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getCategories",
            apiQuery = { seriesApiV3.getCategories() },
            apiResponse = CATEGORIES,
            repositoryQuery = { repository.getCategories() },
            expectedRepositoryResponse = CATEGORIES,
        ),
        TestCase(
            name = "getRegions",
            apiQuery = { seriesApiV3.getRegions() },
            apiResponse = REGIONS,
            repositoryQuery = { repository.getRegions() },
            expectedRepositoryResponse = REGIONS,
        ),
        TestCase(
            name = "getLeadingSeries",
            apiQuery = { seriesApiV3.getGoldenSeries() },
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
                seriesApiV3.getCollection(
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
                seriesApiV3.getSeries(
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