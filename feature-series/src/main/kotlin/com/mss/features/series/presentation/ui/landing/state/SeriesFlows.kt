package com.mss.features.series.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.ui.model.UiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SeriesFlows {
    val leadingSeries: Flow<PagingData<UiItem>>
    val categorySeries: Flow<PagingData<UiItem>>
    val regionSeries: Flow<PagingData<UiItem>>
    val mostRecent: Flow<PagingData<UiItem>>

    companion object {
        val Empty = object : SeriesFlows {
            override val leadingSeries = emptyFlow<PagingData<UiItem>>()
            override val categorySeries = emptyFlow<PagingData<UiItem>>()
            override val regionSeries = emptyFlow<PagingData<UiItem>>()
            override val mostRecent = emptyFlow<PagingData<UiItem>>()
        }
    }
}