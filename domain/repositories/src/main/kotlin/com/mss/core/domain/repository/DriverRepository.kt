package com.mss.core.domain.repository

import com.mss.core.domain.DriverItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.sort.OrderBy
import com.mss.core.domain.sort.SortField
import com.mss.core.utils.Result

interface DriverRepository {
    suspend fun getSeriesDrivers(
        series: String,
        orderBy: OrderBy<SeriesDriverOrder>,
        pageable: Pageable
    ): Result<Page<DriverItem>>

    suspend fun getSeasonDrivers(
        season: String,
        orderBy: OrderBy<SeasonDriverOrder>,
        pageable: Pageable
    ): Result<Page<DriverItem>>

    suspend fun getCollection(
        collection: Collection,
        pageable: Pageable
    ): Result<Page<DriverItem>>

    enum class SeriesDriverOrder : SortField {
        ChampionshipWins,
        Wins,
    }

    enum class SeasonDriverOrder : SortField {
        ChampionshipPosition,
    }

    enum class Collection {
        Anniversaries,
        ChampionshipLeaders,
        Debutants,
        RecentWinners,
    }
}