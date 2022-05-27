package com.mss.features.venue.presentation.ui.info

sealed interface UiAction {
    object Refresh : UiAction
    data class SeriesSelected(val idx: Int) : UiAction
}