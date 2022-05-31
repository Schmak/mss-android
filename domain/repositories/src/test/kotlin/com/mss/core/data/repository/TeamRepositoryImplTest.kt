package com.mss.core.data.repository

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.domain.Team
import com.mss.core.domain.TeamItem
import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.ref.ConstructorReference
import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.api.TeamApiV4
import com.mss.core.network.v4.model.*
import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.ref.ConstructorReferenceDto
import com.mss.core.network.v4.model.ref.DriverReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.create
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.asc
import com.mss.core.network.v4.model.sort.OrderByDto.Companion.desc
import com.mss.core.test.annotation.UnitTest
import com.mss.core.test.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class TeamRepositoryImplTest : AbstractRepositoryTest() {
    private val seriesApiV4: SeriesApiV4 = mockk()
    private val teamApiV4: TeamApiV4 = mockk()
    private val seasonApiV4: SeasonApiV4 = mockk()
    private val repository: TeamRepository =
        TeamRepositoryImpl(
            seriesApiV4 = seriesApiV4,
            seasonApiV4 = seasonApiV4,
            teamApiV4 = teamApiV4,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeriesTeams",
            apiQuery = {
                seriesApiV4.getTeams(
                    series = SERIES,
                    hideZeros = true,
                    orderBy = SeriesApiV4.TeamOrder.TeamWins.desc,
                    page = 8,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = apiResponse(9),
            repositoryQuery = {
                repository.getSeriesTeams(
                    series = SERIES,
                    orderBy = TeamRepository.SeriesTeamOrder.TeamWins.desc,
                    pageable = page(8)
                )
            },
            expectedRepositoryResponse = repoResponse(9),
        ),
        TestCase(
            name = "getSeasonTeams",
            apiQuery = {
                seasonApiV4.getTeams(
                    season = SEASON,
                    hideZeros = false,
                    orderBy = SeasonApiV4.TeamOrder.ChampionshipPosition.asc,
                    page = 12,
                    size = PAGE_SIZE,
                )
            },
            apiResponse = apiResponse(11),
            repositoryQuery = {
                repository.getSeasonTeams(
                    season = SEASON,
                    orderBy = TeamRepository.SeasonTeamOrder.ChampionshipPosition.asc,
                    pageable = page(12)
                )
            },
            expectedRepositoryResponse = repoResponse(11),
        ),
        TestCase(
            name = "getCollection",
            apiQuery = {
                teamApiV4.getCollection(
                    TeamApiV4.TeamCollection.RecentWinners,
                    page = 16,
                    size = PAGE_SIZE
                )
            },
            apiResponse = apiResponse(15),
            repositoryQuery = {
                repository.getCollection(
                    collection = TeamRepository.Collection.RecentWinners,
                    pageable = page(16)
                )
            },
            expectedRepositoryResponse = repoResponse(15),
        ),
        TestCase(
            name = "getInfo",
            apiQuery = { teamApiV4.getTeamInfo(TEAM_SLUG) },
            apiResponse = TEAM_DTO,
            repositoryQuery = { repository.getInfo(TEAM_SLUG) },
            expectedRepositoryResponse = TEAM,
        ),
        TestCase(
            name = "getLastDrivers",
            apiQuery = { teamApiV4.getLastDrivers(TEAM_SLUG) },
            apiResponse = listOf(SERIES_WITH_DRIVERS_DTO),
            repositoryQuery = { repository.getLastDrivers(TEAM_SLUG) },
            expectedRepositoryResponse = listOf(SERIES_WITH_DRIVERS),
        ),
    )

    companion object {
        private const val SEASON = "F1 2022"
        private const val SERIES = "Formula One"
        private const val TEAM_NAME = "Ferrari"
        private const val TEAM_SLUG = "ferrari"
        private const val PAGE_SIZE = 27
        private const val TOTAL_PAGES = 18
        private const val TOTAL_ELEMENTS = 199L

        private val TEAM_ITEM = TeamItem(
            slug = TEAM_SLUG,
            name = TEAM_NAME,
            picture = null,
            country = null,
        )

        private val TEAM_ITEM_DTO = TeamItemDto(
            slug = TEAM_SLUG,
            name = TEAM_NAME,
            picture = null,
            country = null,
        )

        private val TEAM_DTO = TeamDto.create(
            name = TEAM_NAME,
            logo = "team.picture",
            constructors = listOf(
                ConstructorReferenceDto.create(name = "constructor.name"),
            ),
            resourceLinks = listOf(ResourceLinkDto.create(type = "facebook", href = "url"))
        )

        private val TEAM = Team(
            name = TEAM_NAME,
            picture = "team.picture",
            constructors = listOf(
                ConstructorReference(name = "constructor.name"),
            ),
            resourceLinks = listOf(ResourceLink.Facebook("url"))
        )

        private val SERIES_WITH_DRIVERS_DTO = SeriesWithDriversDto.create(
            series = SeriesReferenceDto.create(
                slug = "series.slug",
                name = "series.name",
                picture = "series.picture",
            ),
            drivers = listOf(
                DriverReferenceDto.create(
                    slug = "driver.slug",
                    name = "driver.name",
                    picture = "driver.picture",
                    countryFlag = "driver.flag"
                )
            )
        )

        private val SERIES_WITH_DRIVERS = SeriesWithDrivers(
            series = SeriesReference(
                slug = "series.slug",
                name = "series.name",
                picture = "series.picture",
            ),
            drivers = listOf(
                DriverReference(
                    slug = "driver.slug",
                    name = "driver.name",
                    picture = "driver.picture",
                    countryFlag = "driver.flag"
                )
            )
        )

        private fun repoResponse(page: Int) =
            Page(
                content = listOf(TEAM_ITEM),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                pageNumber = page,
            )

        private fun apiResponse(page: Int) =
            PageDto.create(
                content = listOf(TEAM_ITEM_DTO),
                totalElements = TOTAL_ELEMENTS,
                totalPages = TOTAL_PAGES,
                number = page,
            )

        private fun page(page: Int) = Pageable.page(page, PAGE_SIZE)
    }
}