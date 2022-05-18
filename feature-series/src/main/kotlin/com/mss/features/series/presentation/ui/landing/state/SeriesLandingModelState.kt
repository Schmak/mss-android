package com.mss.features.series.presentation.ui.landing.state

data class SeriesLandingModelState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun toUiState(): SeriesLandingUiState = SeriesLandingUiState(
        regions = regions,
        categories = categories,
        selectedRegionIdx = selectedRegionIdx,
        selectedCategoryIdx = selectedCategoryIdx,
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasData = regions.isNotEmpty() && categories.isNotEmpty(),
    )
}