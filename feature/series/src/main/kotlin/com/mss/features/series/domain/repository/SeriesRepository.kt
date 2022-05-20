package com.mss.features.series.domain.repository

import com.mss.core.utils.Result
import com.mss.domain.SeriesItem
import com.mss.domain.page.Page
import com.mss.domain.page.Pageable
import com.mss.domain.ref.SeriesReference

interface SeriesRepository {
    suspend fun getRegions(): Result<List<String>>

    suspend fun getCategories(): Result<List<String>>

    suspend fun getMostRecent(pageable: Pageable): Result<Page<SeriesItem>>

    suspend fun getCollection(
        region: String? = null,
        category: String? = null,
        pageable: Pageable
    ): Result<Page<SeriesItem>>

    suspend fun getLeadingSeries(pageable: Pageable): Result<Page<SeriesReference>>
}