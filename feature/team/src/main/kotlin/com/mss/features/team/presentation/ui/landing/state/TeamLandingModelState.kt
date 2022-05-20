package com.mss.features.team.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.domain.SeriesInfo
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.LandingUiState
import com.mss.core.ui.model.UiItem
import com.mss.features.team.R
import com.mss.features.team.presentation.ui.landing.BlockId
import com.mss.features.team.presentation.ui.landing.Collections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TeamLandingModelState(
    val goldenSeries: List<SeriesInfo> = emptyList(),
    val collections: List<Collections> = emptyList(),
    val selectedSeasonIdx: Int = 0,
    val selectedChampionSeriesIdx: Int = 0,
    val selectedWinnerSeriesIdx: Int = 0,
    val selectedCollectionIdx: Int = 0,
    val currentSeasonTeams: Flow<PagingData<UiItem>> = emptyFlow(),
    val champions: Flow<PagingData<UiItem>> = emptyFlow(),
    val winners: Flow<PagingData<UiItem>> = emptyFlow(),
    val collectionTeams: Flow<PagingData<UiItem>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
) {
    fun toUiState(): LandingUiState {
        val goldenSeriesNames = goldenSeries.map { it.series.name }
        return LandingUiState(
            titleId = R.string.teams,
            blocks = listOf(
                LandingBlockState(
                    titleId = R.string.current_season_teams,
                    selector = LandingBlockState.Selector(
                        id = BlockId.CurrentSeason,
                        titleId = R.string.select_series,
                        values = goldenSeriesNames,
                        selectedIdx = selectedSeasonIdx,
                    ),
                    itemsFlow = currentSeasonTeams,
                ),
                LandingBlockState(
                    titleId = R.string.champions,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Champions,
                        titleId = R.string.select_series,
                        values = goldenSeriesNames,
                        selectedIdx = selectedChampionSeriesIdx,
                    ),
                    itemsFlow = champions,
                ),
                LandingBlockState(
                    titleId = R.string.winners,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Winners,
                        titleId = R.string.select_series,
                        values = goldenSeriesNames,
                        selectedIdx = selectedWinnerSeriesIdx,
                    ),
                    itemsFlow = winners,
                ),
                LandingBlockState(
                    titleId = R.string.collections,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Collections,
                        titleId = R.string.select_collection,
                        values = collections.map { it.title },
                        selectedIdx = selectedCollectionIdx,
                    ),
                    itemsFlow = collectionTeams,
                ),
            ),
            isLoading = isLoading,
            errorMessageId = errorMessageId,
            hasData = goldenSeries.isNotEmpty(),
        )
    }
}