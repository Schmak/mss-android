package com.mss.features.series.presentation.ui.landing.state

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.LandingUiState
import com.mss.core.ui.model.UiItem
import com.mss.features.series.R
import com.mss.features.series.presentation.ui.landing.BlockId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SeriesLandingModelState(
    val regions: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val leadingSeries: Flow<PagingData<UiItem>> = emptyFlow(),
    val categorySeries: Flow<PagingData<UiItem>> = emptyFlow(),
    val regionSeries: Flow<PagingData<UiItem>> = emptyFlow(),
    val mostRecent: Flow<PagingData<UiItem>> = emptyFlow(),
    val selectedRegionIdx: Int = 0,
    val selectedCategoryIdx: Int = 0,
    val isLoading: Boolean = false,
    @StringRes val errorMessageId: Int? = null
) {
    fun toUiState(): LandingUiState = LandingUiState(
        titleId = R.string.series,
        blocks = listOf(
            LandingBlockState(
                titleId = R.string.leading_series,
                itemsFlow = leadingSeries,
                itemsConfig = UiItem.Configuration.NoSubtitle
            ),
            LandingBlockState(
                titleId = R.string.categories,
                selector = LandingBlockState.Selector(
                    id = BlockId.Category,
                    titleId = R.string.select_category,
                    values = categories,
                    selectedIdx = selectedCategoryIdx,
                ),
                itemsFlow = categorySeries,
            ),
            LandingBlockState(
                titleId = R.string.regions,
                selector = LandingBlockState.Selector(
                    id = BlockId.Region,
                    titleId = R.string.select_region,
                    values = regions,
                    selectedIdx = selectedRegionIdx,
                ),
                itemsFlow = regionSeries,
            ),
            LandingBlockState(
                titleId = R.string.most_recent,
                itemsFlow = mostRecent,
            ),
        ),
        isLoading = isLoading,
        errorMessageId = errorMessageId,
        hasData = regions.isNotEmpty() && categories.isNotEmpty(),
    )
}