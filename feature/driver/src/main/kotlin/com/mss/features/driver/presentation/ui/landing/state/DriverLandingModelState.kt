package com.mss.features.driver.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.domain.SeriesInfo
import com.mss.core.ui.model.landing.LandingBlockState
import com.mss.core.ui.model.landing.LandingUiState
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.model.landing.UiItemConfiguration.WithTwoSubtitles
import com.mss.features.driver.R
import com.mss.features.driver.presentation.ui.landing.BlockId
import com.mss.features.driver.presentation.ui.landing.Collections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DriverLandingModelState(
    val goldenSeries: List<SeriesInfo> = emptyList(),
    val collections: List<Collections> = emptyList(),
    val selectedSeasonIdx: Int = 0,
    val selectedChampionSeriesIdx: Int = 0,
    val selectedWinnerSeriesIdx: Int = 0,
    val selectedCollectionIdx: Int = 0,
    val currentSeasonDrivers: Flow<PagingData<UiItem>> = emptyFlow(),
    val champions: Flow<PagingData<UiItem>> = emptyFlow(),
    val winners: Flow<PagingData<UiItem>> = emptyFlow(),
    val collectionDrivers: Flow<PagingData<UiItem>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
) {
    fun toUiState(): LandingUiState {
        val goldenSeriesNames = goldenSeries.map { it.series.name }
        return LandingUiState(
            titleId = R.string.drivers,
            blocks = listOf(
                LandingBlockState(
                    titleId = R.string.current_season_drivers,
                    selector = LandingBlockState.Selector(
                        id = BlockId.CurrentSeason,
                        titleId = R.string.select_series,
                        values = goldenSeriesNames,
                        selectedIdx = selectedSeasonIdx,
                    ),
                    itemsFlow = currentSeasonDrivers,
                    itemsConfig = WithTwoSubtitles,
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
                    itemsConfig = WithTwoSubtitles,
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
                    itemsConfig = WithTwoSubtitles,
                ),
                LandingBlockState(
                    titleId = R.string.collections,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Collections,
                        titleId = R.string.select_collection,
                        values = collections.map { it.title },
                        selectedIdx = selectedCollectionIdx,
                    ),
                    itemsFlow = collectionDrivers,
                    itemsConfig = WithTwoSubtitles,
                ),
            ),
            isLoading = isLoading,
            errorMessageId = errorMessageId,
            hasData = goldenSeries.isNotEmpty(),
        )
    }
}