package com.mss.features.series.presentation.ui.landing

sealed interface UiAction {
    object Refresh : UiAction
    data class SelectRegion(val idx: Int) : UiAction
    data class SelectCategory(val idx: Int) : UiAction
}