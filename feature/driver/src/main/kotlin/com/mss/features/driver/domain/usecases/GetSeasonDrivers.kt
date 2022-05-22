package com.mss.features.driver.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.DriverItem
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.domain.repository.DriverRepository.SeasonDriverOrder.ChampionshipPosition
import com.mss.core.domain.sort.OrderBy.Companion.asc
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeasonDrivers @Inject constructor(
    private val repository: DriverRepository,
) {
    operator fun invoke(season: String, pageSize: Int = 10): Flow<PagingData<DriverItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getSeasonDrivers(
                    season = season,
                    orderBy = ChampionshipPosition.asc,
                    pageable = it
                )
            }
        }.flow
}