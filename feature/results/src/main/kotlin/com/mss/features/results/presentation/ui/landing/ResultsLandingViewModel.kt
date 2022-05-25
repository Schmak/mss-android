package com.mss.features.results.presentation.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mss.core.domain.SessionItem
import com.mss.core.domain.repository.SessionRepository.Collection.*
import com.mss.core.domain.usecases.GetSeriesCategories
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.components.landing.viewmodel.AbstractLandingViewModel
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.utils.Result.Success
import com.mss.features.results.R
import com.mss.features.results.domain.usecases.GetSessionCollection
import com.mss.features.results.domain.usecases.GetSessionsBySeriesCategory
import com.mss.features.results.presentation.mapper.SessionItemMapper
import com.mss.features.results.presentation.ui.landing.BlockId.Categories
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState.Block
import com.mss.features.results.presentation.ui.landing.state.TimeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class ResultsLandingViewModel @Inject constructor(
    private val getSeriesCategories: GetSeriesCategories,
    private val getSessionCollection: GetSessionCollection,
    private val getSessionsBySeriesCategory: GetSessionsBySeriesCategory,
) : AbstractLandingViewModel() {
    override val categories = BlockId.values().toList()
    private val viewModelState = MutableStateFlow(ResultsLandingModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        viewModelState.update { state ->
            state.copy(
                mostRecent = Block(
                    flow = refreshActions
                        .flatMapLatest { getSessionCollection(MostRecent) }
                        .cachedIn(viewModelScope)
                        .mappedWithMapperOf { it.mostRecent }
                ),
                mostPopular = Block(
                    flow = refreshActions
                        .flatMapLatest { getSessionCollection(MostPopular) }
                        .cachedIn(viewModelScope)
                        .mappedWithMapperOf { it.mostPopular }
                ),
                forthcoming = Block(
                    flow = refreshActions
                        .flatMapLatest { getSessionCollection(Forthcoming) }
                        .cachedIn(viewModelScope)
                        .mappedWithMapperOf { it.forthcoming }
                ),
                categorySessions = Block(
                    flow = actions.filterByCategory(Categories)
                        .mapNotNull { viewModelState.value.categories.getOrNull(it.idx) }
                        .flatMapLatest { getSessionsBySeriesCategory(it) }
                        .cachedIn(viewModelScope)
                        .mappedWithMapperOf { it.categorySessions }
                ),
            )
        }
        refresh()
    }

    private fun Flow<PagingData<SessionItem>>.mappedWithMapperOf(
        selector: (ResultsLandingModelState) -> Block
    ): Flow<PagingData<UiItem>> = combine(
        viewModelState
            .map(selector)
            .map { it.timeType }
            .distinctUntilChanged()
    ) { data, timeType ->
        val mapper = when (timeType) {
            TimeType.Client -> SessionItemMapper.ClientTime
            TimeType.Venue -> SessionItemMapper.VenueTime
        }
        data.map(mapper)
    }

    override fun handleActionButtonClick(blockId: Any?) = when (blockId) {
        ActionBlockId.MostPopular -> viewModelState.update {
            it.copy(mostPopular = toggleTimeType(it.mostPopular))
        }
        ActionBlockId.MostRecent -> viewModelState.update {
            it.copy(mostRecent = toggleTimeType(it.mostRecent))
        }
        ActionBlockId.Categories -> viewModelState.update {
            it.copy(categorySessions = toggleTimeType(it.categorySessions))
        }
        ActionBlockId.Forthcoming -> viewModelState.update {
            it.copy(forthcoming = toggleTimeType(it.forthcoming))
        }
        else -> Timber.e("handleActionButtonClick: '$blockId' block is not supported")
    }

    private fun toggleTimeType(block: Block): Block =
        block.copy(timeType = block.timeType.next())

    override fun selectCategory(action: UiAction.SelectCategory) =
        if (action.blockId == Categories)
            viewModelState.update { it.copy(selectedCategoryIdx = action.idx) }
        else
            Timber.e("selectCategory: '${action.blockId}' block is not supported")

    override fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val categories = getSeriesCategories()

            if (categories !is Success) {
                viewModelState.update {
                    it.copy(
                        errorMessageId = R.string.try_again,
                        isLoading = false,
                    )
                }
                return@launch
            }

            viewModelState.update {
                it.copy(
                    categories = categories.data,
                    mostRecent = it.mostRecent.copy(timeType = TimeType.Venue),
                    mostPopular = it.mostPopular.copy(timeType = TimeType.Venue),
                    categorySessions = it.categorySessions.copy(timeType = TimeType.Venue),
                    forthcoming = it.forthcoming.copy(timeType = TimeType.Venue),
                    isLoading = false,
                    errorMessageId = null,
                )
            }
            resetCategories()
        }
    }
}