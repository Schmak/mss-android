package com.mss.features.series.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.features.series.presentation.model.UiSeriesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SeriesFlows {
    val leadingSeries: Flow<PagingData<UiSeriesItem>>
    val categorySeries: Flow<PagingData<UiSeriesItem>>
    val mostRecent: Flow<PagingData<UiSeriesItem>>

    companion object {
        val Empty = object : SeriesFlows {
            override val leadingSeries = emptyFlow<PagingData<UiSeriesItem>>()
            override val categorySeries = emptyFlow<PagingData<UiSeriesItem>>()
            override val mostRecent = emptyFlow<PagingData<UiSeriesItem>>()
        }
    }
}