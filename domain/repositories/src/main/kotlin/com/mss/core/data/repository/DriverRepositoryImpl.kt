package com.mss.core.data.repository

import com.mss.core.domain.Driver
import com.mss.core.domain.DriverItem
import com.mss.core.domain.SeriesWithTeam
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.sort.Direction
import com.mss.core.domain.sort.OrderBy
import com.mss.core.network.v3.api.DriverApiV3
import com.mss.core.network.v3.api.SeasonApiV3
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.mapper.DriverCollectionMapper
import com.mss.core.network.v3.mapper.DriverItemMapper
import com.mss.core.network.v3.mapper.DriverMapper
import com.mss.core.network.v3.mapper.sort.SeasonDriverOrderMapper
import com.mss.core.network.v3.mapper.sort.SeriesDriverOrderMapper
import com.mss.core.network.v4.api.DriverApiV4
import com.mss.core.network.v4.mapper.SeriesWithTeamMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DriverRepositoryImpl(
    private val seriesApiV3: SeriesApiV3,
    private val seasonApiV3: SeasonApiV3,
    private val driverApiV3: DriverApiV3,
    private val driverApiV4: DriverApiV4,
    private val dispatcher: CoroutineDispatcher,
) : DriverRepository {
    override suspend fun getInfo(driver: String): Result<Driver> = withContext(dispatcher) {
        Result.of { driverApiV3.getDriverInfo(driver).let(DriverMapper) }
    }

    override suspend fun getLastTeams(driver: String): Result<List<SeriesWithTeam>> =
        withContext(dispatcher) {
            Result.of {
                driverApiV4.getLastTeams(driver).map(SeriesWithTeamMapper)
            }
        }

    override suspend fun getSeriesDrivers(
        series: String,
        orderBy: OrderBy<DriverRepository.SeriesDriverOrder>,
        pageable: Pageable
    ): Result<Page<DriverItem>> = withContext(dispatcher) {
        Result.of {
            seriesApiV3.getDrivers(
                series = series,
                hideZeros = true,
                orderBy = orderBy.field.let(SeriesDriverOrderMapper),
                orderDescending = orderBy.direction == Direction.DESC,
                page = pageable.page,
                size = pageable.size,
            ).let(DriverItemMapper.pageMapper)
        }
    }

    override suspend fun getSeasonDrivers(
        season: String,
        orderBy: OrderBy<DriverRepository.SeasonDriverOrder>,
        pageable: Pageable
    ): Result<Page<DriverItem>> = withContext(dispatcher) {
        Result.of {
            seasonApiV3.getDrivers(
                season = season,
                hideZeros = false,
                orderBy = orderBy.field.let(SeasonDriverOrderMapper),
                orderDescending = orderBy.direction == Direction.DESC,
                page = pageable.page,
                size = pageable.size,
            ).let(DriverItemMapper.pageMapper)
        }
    }

    override suspend fun getCollection(
        collection: DriverRepository.Collection,
        pageable: Pageable
    ): Result<Page<DriverItem>> = withContext(dispatcher) {
        Result.of {
            driverApiV3.getCollection(
                collection = collection.let(DriverCollectionMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(DriverItemMapper.pageMapper)
        }
    }
}