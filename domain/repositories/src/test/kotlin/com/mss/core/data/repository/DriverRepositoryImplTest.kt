package com.mss.core.data.repository

import com.mss.core.domain.Driver
import com.mss.core.domain.DriverItem
import com.mss.core.domain.SeriesWithTeam
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.create
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.TeamReference
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.model.DriverDto
import com.mss.core.network.v3.model.DriverItemDto
import com.mss.core.network.v3.model.PageDto
import com.mss.core.network.v3.model.common.ResourceLinkDto
import com.mss.core.network.v3.model.common.create
import com.mss.core.network.v3.model.create
import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.DriverReferenceDto
import com.mss.core.network.v3.model.ref.create
import com.mss.core.network.v4.api.DriverApiV4
import com.mss.core.network.v4.model.SeriesWithTeamDto
import com.mss.core.network.v4.model.create
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.TeamReferenceDto
import com.mss.core.network.v4.model.ref.create
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class DriverRepositoryImplTest : AbstractRepositoryTest() {
    private val seriesApiV3: SeriesApiV3 = mockk()
    private val driverApiV3: DriverApiV3 = mockk()
    private val driverApiV4: DriverApiV4 = mockk()
    private val seasonApiV3: SeasonApiV3 = mockk()
    private val repository: DriverRepository =
        DriverRepositoryImpl(
            seriesApiV3 = seriesApiV3,
            seasonApiV3 = seasonApiV3,
            driverApiV3 = driverApiV3,
            driverApiV4 = driverApiV4,
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
        TestCase(
            name = "getDriverInfo",
            apiQuery = { driverApiV3.getDriverInfo(DRIVER_SLUG) },
            apiResponse = DRIVER_DTO,
            repositoryQuery = { repository.getInfo(DRIVER_SLUG) },
            expectedRepositoryResponse = DRIVER,
        ),
        TestCase(
            name = "getLastTeams",
            apiQuery = { driverApiV4.getLastTeams(DRIVER_SLUG) },
            apiResponse = listOf(LAST_TEAM_DTO),
            repositoryQuery = { repository.getLastTeams(DRIVER_SLUG) },
            expectedRepositoryResponse = listOf(LAST_TEAM),
        ),
    )

    companion object {
        private const val SEASON = "F1 2022"
        private const val SERIES = "Formula One"
        private const val DRIVER_NAME = "Lewis Hamilton"
        private const val DRIVER_SLUG = "lewis-hamilton"
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

        private val DRIVER_DTO = DriverDto.create(
            name = "driver.name",
            uuid = "driver.slug",
            picture = "driver.picture",
            nationality = CountryReferenceDto.create(
                name = "country.name",
                picture = null
            ),
            dateOfDeath = null,
            dateOfBirth = null,
            age = 15,
            placeOfBirth = "driver.placeOfBirth",
            placeOfDeath = "driver.placeOfDeath",
            resourceLinks = listOf(ResourceLinkDto.create(type = "facebook", href = "url")),
            relations = listOf(
                DriverDto.Relation.create(
                    driver = DriverReferenceDto.create(
                        name = "father.name",
                        uuid = "father.slug",
                        picture = "father.picture",
                    ),
                    relationship = "father"
                )
            ),
        )

        private val DRIVER = Driver(
            name = "driver.name",
            slug = "driver.slug",
            picture = "driver.picture",
            nationality = CountryReference(
                name = "country.name",
                picture = null
            ),
            dateOfDeath = null,
            dateOfBirth = null,
            age = 15,
            placeOfBirth = "driver.placeOfBirth",
            placeOfDeath = "driver.placeOfDeath",
            resourceLinks = listOf(ResourceLink.Facebook("url")),
            relations = listOf(
                Driver.Relation(
                    driver = DriverReference(
                        name = "father.name",
                        slug = "father.slug",
                        picture = "father.picture",
                        countryFlag = null,
                    ),
                    relationship = "father"
                )
            ),
        )

        private val LAST_TEAM_DTO = SeriesWithTeamDto.create(
            series = SeriesReferenceDto.create(
                slug = "series.slug",
                name = "series.name",
                picture = "series.picture",
            ),
            team = TeamReferenceDto.create(
                slug = "team.slug",
                name = "team.name",
                picture = "team.picture",
            ),
        )

        private val LAST_TEAM = SeriesWithTeam(
            series = SeriesReference(
                slug = "series.slug",
                name = "series.name",
                picture = "series.picture",
            ),
            team = TeamReference(
                slug = "team.slug",
                name = "team.name",
                picture = "team.picture",
            ),
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