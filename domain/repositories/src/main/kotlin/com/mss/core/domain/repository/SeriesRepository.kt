package com.mss.core.domain.repository

import com.mss.core.domain.SeriesInfo
import com.mss.core.domain.SeriesItem
import com.mss.core.domain.page.Page
import com.mss.core.domain.page.Pageable
import com.mss.core.utils.Result

interface SeriesRepository {
    suspend fun getRegions(): Result<List<String>>

    suspend fun getCategories(): Result<List<String>>

    suspend fun getMostRecent(pageable: Pageable): Result<Page<SeriesItem>>

    suspend fun getCollection(
        region: String? = null,
        category: String? = null,
        pageable: Pageable
    ): Result<Page<SeriesItem>>

    suspend fun getLeadingSeries(): Result<List<SeriesInfo>>
}