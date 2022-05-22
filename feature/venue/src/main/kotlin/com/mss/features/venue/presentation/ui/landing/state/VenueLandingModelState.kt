package com.mss.features.venue.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.domain.SeriesInfo
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.LandingUiState
import com.mss.core.ui.model.UiItem
import com.mss.features.venue.R
import com.mss.features.venue.presentation.ui.landing.BlockId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class VenueLandingModelState(
    val goldenSeries: List<SeriesInfo> = emptyList(),
    val selectedSeasonIdx: Int = 0,
    val currentSeasonVenues: Flow<PagingData<UiItem>> = emptyFlow(),
    val raceCircuitVenues: Flow<PagingData<UiItem>> = emptyFlow(),
    val rallycrossVenues: Flow<PagingData<UiItem>> = emptyFlow(),
    val roadCircuitVenues: Flow<PagingData<UiItem>> = emptyFlow(),
    val streetCircuitVenues: Flow<PagingData<UiItem>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
) {
    fun toUiState(): LandingUiState {
        val goldenSeriesNames = goldenSeries.map { it.series.name }
        return LandingUiState(
            titleId = R.string.venues,
            blocks = listOf(
                LandingBlockState(
                    titleId = R.string.current_season_circuits,
                    selector = LandingBlockState.Selector(
                        id = BlockId.CurrentSeason,
                        titleId = R.string.current_season_circuits,
                        values = goldenSeriesNames,
                        selectedIdx = selectedSeasonIdx,
                    ),
                    itemsFlow = currentSeasonVenues,
                ),
                LandingBlockState(
                    titleId = R.string.race_circuit,
                    itemsFlow = raceCircuitVenues,
                ),
                LandingBlockState(
                    titleId = R.string.rallycross,
                    itemsFlow = rallycrossVenues,
                ),
                LandingBlockState(
                    titleId = R.string.road_circuit,
                    itemsFlow = roadCircuitVenues,
                ),
                LandingBlockState(
                    titleId = R.string.street_circuit,
                    itemsFlow = streetCircuitVenues,
                ),
            ),
            isLoading = isLoading,
            errorMessageId = errorMessageId,
            hasData = goldenSeries.isNotEmpty(),
        )
    }
}