package com.mss.features.results.presentation.ui.landing.state

import androidx.paging.PagingData
import com.mss.core.ui.model.landing.LandingBlockState
import com.mss.core.ui.model.landing.LandingUiState
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.model.landing.UiItemConfiguration.WithLongSecondSubtitle
import com.mss.features.results.R
import com.mss.features.results.presentation.ui.landing.ActionBlockId
import com.mss.features.results.presentation.ui.landing.BlockId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ResultsLandingModelState(
    val categories: List<String> = emptyList(),
    val selectedCategoryIdx: Int = 0,
    val mostRecent: Block = Block(),
    val forthcoming: Block = Block(),
    val categorySessions: Block = Block(),
    val mostPopular: Block = Block(),
    val isLoading: Boolean = false,
    val errorMessageId: Int? = null
) {
    data class Block(
        val flow: Flow<PagingData<UiItem>> = emptyFlow(),
        val timeType: TimeType = TimeType.Venue,
    )

    fun toUiState(): LandingUiState {
        return LandingUiState(
            titleId = R.string.results,
            blocks = listOf(
                LandingBlockState(
                    titleId = R.string.most_recent,
                    itemsFlow = mostRecent.flow,
                    action = action(ActionBlockId.MostRecent),
                    itemsConfig = WithLongSecondSubtitle
                ),
                LandingBlockState(
                    titleId = R.string.forthcoming,
                    itemsFlow = forthcoming.flow,
                    action = action(ActionBlockId.Forthcoming),
                    itemsConfig = WithLongSecondSubtitle,
                ),
                LandingBlockState(
                    titleId = R.string.categories,
                    selector = LandingBlockState.Selector(
                        id = BlockId.Categories,
                        titleId = R.string.select_category,
                        values = categories,
                        selectedIdx = selectedCategoryIdx,
                    ),
                    itemsFlow = categorySessions.flow,
                    action = action(ActionBlockId.Categories),
                    itemsConfig = WithLongSecondSubtitle,
                ),
                LandingBlockState(
                    titleId = R.string.most_popular,
                    itemsFlow = mostPopular.flow,
                    action = action(ActionBlockId.MostPopular),
                    itemsConfig = WithLongSecondSubtitle,
                ),
            ),
            isLoading = isLoading,
            errorMessageId = errorMessageId,
            hasData = categories.isNotEmpty(),
        )
    }

    companion object {
        private fun action(blockId: ActionBlockId) = LandingBlockState.Action(
            id = blockId,
            drawableId = R.drawable.ic_clock,
            descriptionId = R.string.switch_time_format
        )
    }
}