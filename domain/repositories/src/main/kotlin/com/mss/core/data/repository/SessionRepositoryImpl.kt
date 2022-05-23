package com.mss.core.data.repository

import com.mss.core.domain.SessionItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.domain.repository.SessionRepository
import com.mss.core.network.v4.api.SessionApiV4
import com.mss.core.network.v4.api.SessionApiV4.SeriesStatus.Active
import com.mss.core.network.v4.mapper.SessionCollectionMapper
import com.mss.core.network.v4.mapper.SessionItemMapper
import com.mss.core.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SessionRepositoryImpl(
    private val sessionApiV4: SessionApiV4,
    private val dispatcher: CoroutineDispatcher,
) : SessionRepository {
    override suspend fun getCollection(
        collection: SessionRepository.Collection,
        pageable: Pageable
    ): Result<Page<SessionItem>> = withContext(dispatcher) {
        Result.of {
            sessionApiV4.getCollection(
                collection = collection.let(SessionCollectionMapper),
                status = Active,
                page = pageable.page,
                size = pageable.size,
            ).let(SessionItemMapper.pageMapper)
        }
    }

    override suspend fun getSeriesCategorySessions(
        category: String,
        pageable: Pageable
    ): Result<Page<SessionItem>> = withContext(dispatcher) {
        Result.of {
            sessionApiV4.getSeriesCategorySessions(
                category = category,
                status = Active,
                page = pageable.page,
                size = pageable.size,
            ).let(SessionItemMapper.pageMapper)
        }
    }
}