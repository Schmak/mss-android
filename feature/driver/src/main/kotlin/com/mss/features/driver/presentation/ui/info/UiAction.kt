package com.mss.features.driver.presentation.ui.info

sealed interface UiAction {
    object Refresh : UiAction
    data class SelectSeries(val idx: Int) : UiAction
}