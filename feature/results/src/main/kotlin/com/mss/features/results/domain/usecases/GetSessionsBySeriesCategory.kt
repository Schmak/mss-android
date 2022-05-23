package com.mss.features.results.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.SessionItem
import com.mss.core.domain.repository.SessionRepository
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionsBySeriesCategory @Inject constructor(
    private val repository: SessionRepository,
) {
    operator fun invoke(
        category: String,
        pageSize: Int = 10
    ): Flow<PagingData<SessionItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getSeriesCategorySessions(
                    category = category,
                    pageable = it
                )
            }
        }.flow
}