package com.mss.features.driver.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.DriverItem
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeriesDriverOrder.Wins
import com.mss.core.domain.sort.OrderBy.Companion.desc
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWinners @Inject constructor(
    private val repository: DriverRepository,
) {
    operator fun invoke(series: String, pageSize: Int = 10): Flow<PagingData<DriverItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getSeriesDrivers(
                    series = series,
                    orderBy = Wins.desc,
                    pageable = it
                )
            }
        }.flow
}