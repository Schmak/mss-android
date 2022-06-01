package com.mss.core.data.repository

import com.mss.core.domain.SeriesWithDrivers
import com.mss.core.domain.Team
import com.mss.core.domain.TeamItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.TeamRepository
import com.mss.core.domain.sort.OrderBy
import com.mss.core.network.v4.api.SeasonApiV4
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.api.TeamApiV4
import com.mss.core.network.v4.mapper.SeriesWithDriversMapper
import com.mss.core.network.v4.mapper.TeamCollectionMapper
import com.mss.core.network.v4.mapper.TeamItemMapper
import com.mss.core.network.v4.mapper.TeamMapper
import com.mss.core.network.v4.mapper.sort.SeasonTeamOrderMapper
import com.mss.core.network.v4.mapper.sort.SeriesTeamOrderMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class TeamRepositoryImpl(
    private val seriesApiV4: SeriesApiV4,
    private val seasonApiV4: SeasonApiV4,
    private val teamApiV4: TeamApiV4,
    private val dispatcher: CoroutineDispatcher,
) : TeamRepository {
    override suspend fun getInfo(team: String): Result<Team> = withContext(dispatcher) {
        Result.of { teamApiV4.getTeamInfo(team).let(TeamMapper) }
    }

    override suspend fun getLastDrivers(team: String): Result<List<SeriesWithDrivers>> =
        withContext(dispatcher) {
            Result.of { teamApiV4.getLastDrivers(team).map(SeriesWithDriversMapper) }
        }

    override suspend fun getSeriesTeams(
        series: String,
        orderBy: OrderBy<TeamRepository.SeriesTeamOrder>,
        pageable: Pageable
    ): Result<Page<TeamItem>> = withContext(dispatcher) {
        Result.of {
            seriesApiV4.getTeams(
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
            seasonApiV4.getTeams(
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
            teamApiV4.getCollection(
                collection = collection.let(TeamCollectionMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(TeamItemMapper.pageMapper)
        }
    }
}