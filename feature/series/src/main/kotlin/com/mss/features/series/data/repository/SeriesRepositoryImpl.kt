package com.mss.features.series.data.repository

import com.mss.core.utils.Result
import com.mss.domain.SeriesItem
import com.mss.domain.page.Page
import com.mss.domain.page.Page.Companion.getPage
import com.mss.domain.page.Page.Companion.map
import com.mss.domain.page.Pageable
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

    override suspend fun getLeadingSeries(
        pageable: Pageable
    ): Result<Page<SeriesReference>> = withContext(dispatcher) {
        Result.of {
            api.getGoldenSeries()
                .getPage(pageable)
                .map { SeriesReferenceMapper(it.series) }
        }
    }

    override suspend fun getMostRecent(
        pageable: Pageable
    ): Result<Page<SeriesItem>> = withContext(dispatcher) {
        Result.of {
            api.getSeries(
                filterIds = arrayOf("Active"),
                orderBy = SeriesApi.OrderBy.LastEventDate,
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