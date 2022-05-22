package com.mss.features.venue.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.VenueItem
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVenueCollection @Inject constructor(
    private val repository: VenueRepository,
) {
    operator fun invoke(
        collection: VenueRepository.Collection,
        pageSize: Int = 10
    ): Flow<PagingData<VenueItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getCollection(
                    collection = collection,
                    pageable = it
                )
            }
        }.flow
}