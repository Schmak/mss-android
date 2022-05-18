package com.mss.features.series.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.features.series.presentation.model.UiSeriesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SeriesLandingModelState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val categorySeries: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val regionSeries: Flow<PagingData<UiSeriesItem>> = emptyFlow(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun toUiState(): SeriesLandingUiState = SeriesLandingUiState(
        regions = regions,
        categories = categories,
        categorySeries = categorySeries,
        regionSeries = regionSeries,
        selectedRegionIdx = selectedRegionIdx,
        selectedCategoryIdx = selectedCategoryIdx,
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasData = regions.isNotEmpty() && categories.isNotEmpty(),
    )
}