package com.mss.core.domain.repository

import com.mss.core.domain.TeamItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.sort.OrderBy
import com.mss.core.domain.sort.SortField
import com.mss.core.utils.Result

interface TeamRepository {
    suspend fun getSeriesTeams(
        series: String,
        orderBy: OrderBy<SeriesTeamOrder>,
        pageable: Pageable
    ): Result<Page<TeamItem>>

    suspend fun getSeasonTeams(
        season: String,
        orderBy: OrderBy<SeasonTeamOrder>,
        pageable: Pageable
    ): Result<Page<TeamItem>>

    suspend fun getCollection(
        collection: Collection,
        pageable: Pageable
    ): Result<Page<TeamItem>>

    enum class SeriesTeamOrder : SortField {
        ChampionshipWins,
        TeamWins,
    }

    enum class SeasonTeamOrder : SortField {
        ChampionshipPosition,
    }

    enum class Collection {
        RecentWinners,
        ChampionshipLeaders,
    }
}