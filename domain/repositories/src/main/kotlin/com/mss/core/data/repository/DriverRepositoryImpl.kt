package com.mss.core.data.repository

import com.mss.core.domain.DriverItem
import com.mss.core.domain.mapper.DriverCollectionMapper
import com.mss.core.domain.mapper.DriverItemMapper
import com.mss.core.domain.mapper.sort.SeasonDriverOrderMapper
import com.mss.core.domain.mapper.sort.SeriesDriverOrderMapper
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.sort.Direction
import com.mss.core.domain.sort.OrderBy
import com.mss.core.utils.Result
import com.mss.network.api.DriverApi
import com.mss.network.api.SeasonApi
import com.mss.network.api.SeriesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DriverRepositoryImpl(
    private val seriesApi: SeriesApi,
    private val seasonApi: SeasonApi,
    private val driverApi: DriverApi,
    private val dispatcher: CoroutineDispatcher,
) : DriverRepository {
    override suspend fun getSeriesDrivers(
        series: String,
        orderBy: OrderBy<DriverRepository.SeriesDriverOrder>,
        pageable: Pageable
    ): Result<Page<DriverItem>> = withContext(dispatcher) {
        Result.of {
            seriesApi.getDrivers(
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
            seasonApi.getDrivers(
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
            driverApi.getCollection(
                collection = collection.let(DriverCollectionMapper),
                page = pageable.page,
                size = pageable.size,
            ).let(DriverItemMapper.pageMapper)
        }
    }
}