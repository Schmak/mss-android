package com.mss.features.series.presentation.ui.landing

import androidx.paging.PagingData
import com.mss.core.ui.utils.mapPage
import com.mss.domain.SeriesItem
import com.mss.domain.ref.SeriesReference
import com.mss.features.series.presentation.mapper.LeadingSeriesItemMapper
import com.mss.features.series.presentation.mapper.MostRecentSeriesItemMapper
import com.mss.features.series.presentation.mapper.SeriesItemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SeriesLandingModelState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val leadingSeries: Flow<PagingData<SeriesReference>> = emptyFlow(),
    val categorySeries: Flow<PagingData<SeriesItem>> = emptyFlow(),
    val regionSeries: Flow<PagingData<SeriesItem>> = emptyFlow(),
    val mostRecent: Flow<PagingData<SeriesItem>> = emptyFlow(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun toUiState(): SeriesLandingUiState = SeriesLandingUiState(
        regions = regions,
        categories = categories,
        leadingSeries = leadingSeries.mapPage(LeadingSeriesItemMapper),
        categorySeries = categorySeries.mapPage(SeriesItemMapper),
        regionSeries = regionSeries.mapPage(SeriesItemMapper),
        mostRecent = mostRecent.mapPage(MostRecentSeriesItemMapper),
        selectedRegionIdx = selectedRegionIdx,
        selectedCategoryIdx = selectedCategoryIdx,
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasData = regions.isNotEmpty() && categories.isNotEmpty(),
    )
}