package com.mss.features.team.presentation.ui.info

sealed interface UiAction {
    object Refresh : UiAction
    data class SeriesSelected(val idx: Int) : UiAction
    data class DriverSelected(val seriesIdx: Int, val driverIdx: Int) : UiAction
}