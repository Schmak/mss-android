package com.mss.features.results.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.LandingUiState
import com.mss.core.ui.model.UiItem
import com.mss.features.results.R
import com.mss.features.results.presentation.ui.landing.BlockId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ResultsLandingModelState(
    val categories: List<String> = emptyList(),
    val selectedCategoryIdx: Int = 0,
    val mostRecent: Flow<PagingData<UiItem>> = emptyFlow(),
    val forthcoming: Flow<PagingData<UiItem>> = emptyFlow(),
    val categorySessions: Flow<PagingData<UiItem>> = emptyFlow(),
    val mostPopular: Flow<PagingData<UiItem>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
) {
    fun toUiState(): LandingUiState {
        return LandingUiState(
            titleId = R.string.results,
            blocks = listOf(
                LandingBlockState(
                    titleId = R.string.most_recent,
                    itemsFlow = mostRecent,
                    itemsConfig = UiItem.Configuration.WithTwoSubtitles,
                ),
                LandingBlockState(
                    titleId = R.string.forthcoming,
                    itemsFlow = forthcoming,
                    itemsConfig = UiItem.Configuration.WithTwoSubtitles,
                ),
                LandingBlockState(
                    titleId = R.string.categories,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Categories,
                        titleId = R.string.select_category,
                        values = categories,
                        selectedIdx = selectedCategoryIdx,
                    ),
                    itemsFlow = categorySessions,
                    itemsConfig = UiItem.Configuration.WithTwoSubtitles,
                ),
                LandingBlockState(
                    titleId = R.string.most_popular,
                    itemsFlow = mostPopular,
                    itemsConfig = UiItem.Configuration.WithTwoSubtitles,
                ),
            ),
            isLoading = isLoading,
            errorMessageId = errorMessageId,
            hasData = categories.isNotEmpty(),
        )
    }
}