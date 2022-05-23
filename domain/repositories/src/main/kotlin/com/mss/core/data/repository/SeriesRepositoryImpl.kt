package com.mss.core.data.repository

import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.SeriesItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.network.v3.api.SeriesApi
import com.mss.core.network.v3.mapper.SeriesInfoMapper
import com.mss.core.network.v3.mapper.SeriesItemMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SeriesRepositoryImpl @Inject constructor(
    private val api: SeriesApi,
    private val dispatcher: CoroutineDispatcher,
) : SeriesRepository {
    override suspend fun getRegions(): Result<List<String>> = withContext(dispatcher) {
        Result.of { api.getRegions() }
    }

    override suspend fun getCategories(): Result<List<String>> = withContext(dispatcher) {
        Result.of { api.getCategories() }
    }

    override suspend fun getLeadingSeries(): Result<List<SeriesInfo>> = withContext(dispatcher) {
        Result.of { api.getGoldenSeries().map(SeriesInfoMapper) }
    }

    override suspend fun getMostRecent(
        pageable: Pageable
    ): Result<Page<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            api.getSeries(
                filterIds = arrayOf("Active"),
                orderBy = SeriesApi.SeriesOrder.LastEventDate,
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
            api.getCollection(
                region = region,
                category = category,
                page = pageable.page,
                size = pageable.size,
            ).let(SeriesItemMapper.pageMapper)
        }
    }
}