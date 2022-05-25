package com.mss.core.data.repository

import com.mss.core.domain.*
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.*
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
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.model.LastSeriesChampionsDto
import com.mss.core.network.v4.model.SeriesDto
import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.TemplateDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.create
import com.mss.core.network.v4.model.ref.DriverReferenceDto
import com.mss.core.network.v4.model.ref.OrganisationReferenceDto
import com.mss.core.network.v4.model.ref.TeamReferenceDto
import com.mss.core.network.v4.model.ref.create
import com.mss.core.test.utils.DEFAULT_LOCAL_DATE
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk
import kotlin.time.Duration.Companion.days

internal class SeriesRepositoryImplTest : AbstractRepositoryTest() {
    private val seriesApiV3: SeriesApiV3 = mockk()
    private val seriesApiV4: SeriesApiV4 = mockk()
    private val repository: SeriesRepository =
        SeriesRepositoryImpl(
            apiV3 = seriesApiV3,
            apiV4 = seriesApiV4,
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
        TestCase(
            name = "getInfo",
            apiQuery = { seriesApiV4.getInfo(SERIES) },
            apiResponse = SERIES_DTO,
            repositoryQuery = { repository.getSeriesInfo(SERIES) },
            expectedRepositoryResponse = EXPECTED_SERIES,
        ),
        TestCase(
            name = "getLastChampions",
            apiQuery = { seriesApiV4.getLastChampions(SERIES) },
            apiResponse = CHAMPIONS_DTO,
            repositoryQuery = { repository.getLastChampions(SERIES) },
            expectedRepositoryResponse = EXPECTED_CHAMPIONS,
        )
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
            uuid = "$SERIES-slug",
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = EventReferenceDto.create(
                name = "$SERIES event",
            ),
            lastEventDate = DEFAULT_LOCAL_DATE.toEpochDay().days.inWholeSeconds
        )
        private val EXPECTED_SERIES_ITEM = SeriesItem(
            slug = "$SERIES-slug",
            name = SERIES,
            picture = "$SERIES pic",
            region = "$SERIES region",
            lastEvent = SeriesItem.LastEvent(
                name = "$SERIES event",
                date = DEFAULT_LOCAL_DATE,
            )
        )
        private val SERIES_DTO = SeriesDto.create(
            name = "series.name",
            shortName = "series.shortName",
            picture = "series.picture",
            status = "series.status",
            firstSeason = "series.firstSeason",
            organisation = OrganisationReferenceDto.create(name = "series.organisation"),
            template = TemplateDto.create(seriesCategory = "series.category"),
            resourceLinks = listOf(ResourceLinkDto.create(href = "youtube.com", type = "youtube")),
        )
        private val EXPECTED_SERIES = Series(
            name = "series.name",
            shortName = "series.shortName",
            picture = "series.picture",
            firstSeason = "series.firstSeason",
            organisation = "series.organisation",
            category = "series.category",
            links = listOf(ResourceLink.YouTube("youtube.com")),
        )
        private val CHAMPIONS_DTO = LastSeriesChampionsDto.create(
            drivers = listOf(
                LastSeriesChampionsDto.Driver.create(
                    driver = DriverReferenceDto.create(
                        name = "driver.name",
                        slug = "driver.slug",
                        picture = "driver.picture",
                        countryFlag = "country.flag",
                    )
                )
            ),
            team = TeamReferenceDto.create(
                name = "team.name",
                slug = "team.slug",
                picture = "team.picture",
            )
        )
        private val EXPECTED_CHAMPIONS = LastSeriesChampions(
            drivers = listOf(
                DriverReference(
                    name = "driver.name",
                    slug = "driver.slug",
                    picture = "driver.picture",
                    countryFlag = "country.flag",
                )
            ),
            team = TeamReference(
                name = "team.name",
                slug = "team.slug",
                picture = "team.picture",
            )
        )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}