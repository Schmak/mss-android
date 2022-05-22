package com.mss.features.driver.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mss.core.domain.DriverItem
import com.mss.core.domain.repository.DriverRepository
import com.mss.core.ui.utils.PageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDriverCollection @Inject constructor(
    private val repository: DriverRepository,
) {
    operator fun invoke(
        collection: DriverRepository.Collection,
        pageSize: Int = 10
    ): Flow<PagingData<DriverItem>> =
        Pager(PagingConfig(pageSize)) {
            PageSource {
                repository.getCollection(
                    collection = collection,
                    pageable = it
                )
            }
        }.flow
}