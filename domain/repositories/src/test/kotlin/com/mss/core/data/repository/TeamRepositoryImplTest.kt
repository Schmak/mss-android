package com.mss.core.data.repository

import com.mss.annotation.UnitTest
import com.mss.core.domain.TeamItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.network.api.SeasonApi
import com.mss.network.api.SeriesApi
import com.mss.network.api.TeamApi
import com.mss.network.model.PageDto
import com.mss.network.model.TeamItemDto
import com.mss.network.model.create
import com.mss.network.model.sort.OrderByDto.Companion.asc
import com.mss.network.model.sort.OrderByDto.Companion.desc
import com.mss.test.BaseRepositoryTest
import com.mss.utils.coroutines.TestDispatchers
import io.mockk.mockk

@UnitTest
internal class TeamRepositoryImplTest : BaseRepositoryTest() {
    private val seriesApi: SeriesApi = mockk()
    private val teamApi: TeamApi = mockk()
    private val seasonApi: SeasonApi = mockk()
    private val repository: TeamRepository =
        TeamRepositoryImpl(
            seriesApi = seriesApi,
            seasonApi = seasonApi,
            teamApi = teamApi,
            dispatcher = TestDispatchers.IO,
        )

    @Suppress("LongMethod")
    override fun cases() = listOf(
        TestCase(
            name = "getSeriesTeams",
            apiQuery = {
                seriesApi.getTeams(
                    series = SERIES,
                    hideZeros = true,
                    orderBy = SeriesApi.TeamOrder.TeamWins.desc,
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
                seasonApi.getTeams(
                    season = SEASON,
                    hideZeros = false,
                    orderBy = SeasonApi.TeamOrder.ChampionshipPosition.asc,
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
                teamApi.getCollection(
                    TeamApi.TeamCollection.RecentWinners,
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