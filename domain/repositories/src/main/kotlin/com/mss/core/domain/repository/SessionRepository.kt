package com.mss.core.domain.repository

import com.mss.core.domain.SessionItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.utils.Result

interface SessionRepository {
    suspend fun getCollection(
        collection: Collection,
        pageable: Pageable
    ): Result<Page<SessionItem>>

    suspend fun getSeriesCategorySessions(
        category: String,
        pageable: Pageable
    ): Result<Page<SessionItem>>

    enum class Collection {
        MostRecent,
        MostPopular,
        Forthcoming,
    }
}