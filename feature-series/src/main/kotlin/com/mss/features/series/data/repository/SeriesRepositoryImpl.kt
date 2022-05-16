package com.mss.features.series.data.repository

import com.mss.core.utils.Result
import com.mss.domain.SeriesItem
import com.mss.domain.ref.SeriesReference
import com.mss.features.series.domain.mapper.SeriesItemMapper
import com.mss.features.series.domain.mapper.SeriesReferenceMapper
import com.mss.features.series.domain.repository.SeriesRepository
import com.mss.network.api.SeriesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val api: SeriesApi,
    private val dispatcher: CoroutineDispatcher,
) : SeriesRepository {

    override suspend fun getRegions(): Result<List<String>> = withContext(dispatcher) {
        Result.of { api.getRegions() }
    }

    override suspend fun getCategories(): Result<List<String>> = withContext(dispatcher) {
        Result.of { api.getCategories() }
    }

    override suspend fun getLeadingSeries(page: Int): Result<List<SeriesReference>> = withContext(dispatcher) {
        Result.of {
            if (page == 0)
                api.getGoldenSeries().map { it.series }.map(SeriesReferenceMapper)
            else
                emptyList()
        }
    }

    override suspend fun getMostRecent(page: Int): Result<List<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            api.getSeries(
                filterIds = arrayOf("Active"),
                orderBy = SeriesApi.OrderBy.LastEventDate,
                orderDescending = true,
                page = page,
            ).content.map(SeriesItemMapper)
        }
    }

    override suspend fun getCollection(
        region: String?,
        category: String?,
        page: Int
    ): Result<List<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            api.getCollection(
                region = region,
                category = category,
                page = page,
            ).content.map(SeriesItemMapper)
        }
    }
}