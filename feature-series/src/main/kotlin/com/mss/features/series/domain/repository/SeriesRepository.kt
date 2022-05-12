package com.mss.features.series.domain.repository

import com.mss.core.utils.Result
import com.mss.domain.SeriesItem
import com.mss.domain.ref.SeriesReference

interface SeriesRepository {
    suspend fun getRegions(): Result<List<String>>

    suspend fun getCategories(): Result<List<String>>

    suspend fun getMostRecent(page: Int = 0): Result<List<SeriesItem>>

    suspend fun getCollection(
        region: String? = null,
        category: String? = null,
        page: Int = 0
    ): Result<List<SeriesItem>>

    suspend fun getLeadingSeries(page: Int = 0): Result<List<SeriesReference>>
}