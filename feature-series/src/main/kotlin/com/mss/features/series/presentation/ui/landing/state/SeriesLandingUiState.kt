package com.mss.features.series.presentation.ui.landing.state

data class SeriesLandingUiState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasData: Boolean = false,
)