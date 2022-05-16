package com.mss.features.series.presentation.ui.landing

import androidx.paging.PagingData
import com.mss.features.series.presentation.model.UiSeriesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SeriesLandingUiState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val leadingSeries: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val categorySeries: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val regionSeries: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val mostRecent: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasData: Boolean = false,
)