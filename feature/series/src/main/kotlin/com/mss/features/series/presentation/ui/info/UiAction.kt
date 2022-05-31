package com.mss.features.series.presentation.ui.info

sealed interface UiAction {
    object Refresh : UiAction
    data class ChampionDriverSelected(val idx: Int) : UiAction
    object ChampionTeamSelected : UiAction
}