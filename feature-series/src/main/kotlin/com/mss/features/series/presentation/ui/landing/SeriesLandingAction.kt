package com.mss.features.series.presentation.ui.landing

sealed interface SeriesLandingAction {
    object Refresh : SeriesLandingAction
    data class SelectRegion(val idx: Int) : SeriesLandingAction
    data class SelectCategory(val idx: Int) : SeriesLandingAction
}