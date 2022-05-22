package com.mss.features.venue.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.VenueItem
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeasonVenues @Inject constructor(
    private val repository: VenueRepository,
) {
    operator fun invoke(season: String, pageSize: Int = 10): Flow<PagingData<VenueItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getSeasonVenues(
                    season = season,
                    pageable = it
                )
            }
        }.flow
}