package com.mss.features.series.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.repository.SeriesRepository
import com.mss.core.ui.utils.PageSource
import com.mss.domain.ref.SeriesReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLeadingSeries @Inject constructor(
    private val repository: SeriesRepository,
) {
    operator fun invoke(pageSize: Int = 10): Flow<PagingData<SeriesReference>> =
        Pager(PagingConfig(pageSize)) {
            PageSource { repository.getLeadingSeries(it) }
        }.flow
}