package com.mss.features.series.presentation.ui.info

sealed interface UiAction {
    object Refresh : UiAction
    data class DriverSelected(val idx: Int) : UiAction
}