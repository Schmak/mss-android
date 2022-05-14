package com.mss.features.series.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.ui.utils.PageSource
import com.mss.domain.SeriesItem
import com.mss.features.series.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRegionSeries @Inject constructor(
    private val repository: SeriesRepository,
) {
    operator fun invoke(region: String, pageSize: Int = 10): Flow<PagingData<SeriesItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource { repository.getCollection(region = region, page = it) }
        }.flow
}