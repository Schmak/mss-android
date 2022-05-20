package com.mss.core.data.repository

import com.mss.core.domain.TeamItem
import com.mss.core.domain.mapper.TeamCollectionMapper
import com.mss.core.domain.mapper.TeamItemMapper
import com.mss.core.domain.mapper.sort.SeasonTeamOrderMapper
import com.mss.core.domain.mapper.sort.SeriesTeamOrderMapper
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.sort.OrderBy
import com.mss.core.utils.Result
import com.mss.network.api.SeasonApi
import com.mss.network.api.SeriesApi
import com.mss.network.api.TeamApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class TeamRepositoryImpl(
    private val seriesApi: SeriesApi,
    private val seasonApi: SeasonApi,
    private val teamApi: TeamApi,
    private val dispatcher: CoroutineDispatcher,
) : TeamRepository {
    override suspend fun getSeriesTeams(
        series: String,
        orderBy: OrderBy<TeamRepository.SeriesTeamOrder>,
        pageable: Pageable
    ): Result<Page<TeamItem>> = withContext(dispatcher) {
        Result.of {
            seriesApi.getTeams(
                series = series,
                hideZeros = true,
                orderBy = orderBy.let(SeriesTeamOrderMapper.orderByMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(TeamItemMapper.pageMapper)
        }
    }

    override suspend fun getSeasonTeams(
        season: String,
        orderBy: OrderBy<TeamRepository.SeasonTeamOrder>,
        pageable: Pageable
    ): Result<Page<TeamItem>> = withContext(dispatcher) {
        Result.of {
            seasonApi.getTeams(
                season = season,
                hideZeros = false,
                orderBy = orderBy.let(SeasonTeamOrderMapper.orderByMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(TeamItemMapper.pageMapper)
        }
    }

    override suspend fun getCollection(
        collection: TeamRepository.Collection,
        pageable: Pageable
    ): Result<Page<TeamItem>> = withContext(dispatcher) {
        Result.of {
            teamApi.getCollection(
                collection = collection.let(TeamCollectionMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(TeamItemMapper.pageMapper)
        }
    }
}