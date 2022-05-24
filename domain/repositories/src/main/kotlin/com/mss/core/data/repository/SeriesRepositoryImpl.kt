package com.mss.core.data.repository

import com.mss.core.domain.LastSeriesChampions
import com.mss.core.domain.Series
import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.SeriesItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.network.v3.api.SeriesApiV3
import com.mss.core.network.v3.mapper.SeriesInfoMapper
import com.mss.core.network.v3.mapper.SeriesItemMapper
import com.mss.core.network.v4.api.SeriesApiV4
import com.mss.core.network.v4.mapper.LastSeriesChampionsMapper
import com.mss.core.network.v4.mapper.SeriesMapper
import com.mss.core.utils.Result
import com.mss.core.utils.Result.Companion.map
import com.mss.core.utils.nullIfNotFound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SeriesRepositoryImpl @Inject constructor(
    private val apiV3: SeriesApiV3,
    private val apiV4: SeriesApiV4,
    private val dispatcher: CoroutineDispatcher,
) : SeriesRepository {
    override suspend fun getRegions(): Result<List<String>> = withContext(dispatcher) {
        Result.of { apiV3.getRegions() }
    }

    override suspend fun getCategories(): Result<List<String>> = withContext(dispatcher) {
        Result.of { apiV3.getCategories() }
    }

    override suspend fun getLeadingSeries(): Result<List<SeriesInfo>> = withContext(dispatcher) {
        Result.of { apiV3.getGoldenSeries().map(SeriesInfoMapper) }
    }

    override suspend fun getMostRecent(
        pageable: Pageable
    ): Result<Page<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            apiV3.getSeries(
                filterIds = arrayOf("Active"),
                orderBy = SeriesApiV3.SeriesOrder.LastEventDate,
                orderDescending = true,
                page = pageable.page,
                size = pageable.size,
            ).let(SeriesItemMapper.pageMapper)
        }
    }

    override suspend fun getCollection(
        region: String?,
        category: String?,
        pageable: Pageable
    ): Result<Page<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            apiV3.getCollection(
                region = region,
                category = category,
                page = pageable.page,
                size = pageable.size,
            ).let(SeriesItemMapper.pageMapper)
        }
    }

    override suspend fun getSeriesInfo(series: String): Result<Series> = withContext(dispatcher) {
        Result.of { apiV4.getInfo(series) }
            .map(SeriesMapper)
    }

    override suspend fun getLastChampions(series: String): Result<LastSeriesChampions?> =
        withContext(dispatcher) {
            Result.of { apiV4.getLastChampions(series) }
                .map(LastSeriesChampionsMapper)
                .nullIfNotFound()
        }
}