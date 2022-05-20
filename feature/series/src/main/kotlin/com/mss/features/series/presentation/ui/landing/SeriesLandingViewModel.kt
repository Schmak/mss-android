package com.mss.features.series.presentation.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.series.R
import com.mss.features.series.domain.usecases.*
import com.mss.features.series.presentation.mapper.LeadingSeriesItemMapper
import com.mss.features.series.presentation.mapper.MostRecentSeriesItemMapper
import com.mss.features.series.presentation.mapper.SeriesItemMapper
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class SeriesLandingViewModel @Inject constructor(
    private val getSeriesCategories: GetSeriesCategories,
    private val getSeriesRegions: GetSeriesRegions,
    private val getLeadingSeries: GetLeadingSeries,
    private val getMostRecentSeries: GetMostRecentSeries,
    private val getCategorySeries: GetCategorySeries,
    private val getRegionSeries: GetRegionSeries,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(SeriesLandingModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    private val _actions = MutableSharedFlow<UiAction>()
    private val actions = _actions.asSharedFlow().onSubscription { emit(UiAction.Refresh) }
    private val refreshActions = actions.filterIsInstance<UiAction.Refresh>()

    init {
        viewModelState.update { state ->
            state.copy(
                mostRecent = refreshActions
                    .flatMapLatest { getMostRecentSeries() }
                    .mapPage(MostRecentSeriesItemMapper)
                    .cachedIn(viewModelScope),
                leadingSeries = refreshActions
                    .flatMapLatest { getLeadingSeries() }
                    .mapPage(LeadingSeriesItemMapper)
                    .cachedIn(viewModelScope),
                categorySeries = actions.filterByCategory(BlockId.Category)
                    .mapNotNull { viewModelState.value.categories.getOrNull(it.idx) }
                    .flatMapLatest { getCategorySeries(it) }
                    .mapPage(SeriesItemMapper)
                    .cachedIn(viewModelScope),
                regionSeries = actions.filterByCategory(BlockId.Region)
                    .mapNotNull { viewModelState.value.regions.getOrNull(it.idx) }
                    .flatMapLatest { getRegionSeries(it) }
                    .mapPage(SeriesItemMapper)
                    .cachedIn(viewModelScope)
            )
        }
        refresh()
    }

    fun handleAction(action: UiAction) {
        viewModelScope.launch { _actions.emit(action) }
        when (action) {
            is UiAction.Refresh -> refresh()
            is UiAction.SelectCategory -> selectCategory(action)
        }
    }

    private fun selectCategory(action: UiAction.SelectCategory) = when (action.blockId) {
        BlockId.Category -> viewModelState.update { it.copy(selectedCategoryIdx = action.idx) }
        BlockId.Region -> viewModelState.update { it.copy(selectedRegionIdx = action.idx) }
        else -> Timber.e("'${action.blockId}' block is not supported")
    }

    private fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val categoriesDeferred = async { getSeriesCategories() }
            val regionsDeferred = async { getSeriesRegions() }

            val categories = categoriesDeferred.await()
            val regions = regionsDeferred.await()

            if (categories !is Success || regions !is Success) {
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
                    regions = regions.data,
                    isLoading = false,
                    errorMessageId = null,
                )
            }
            handleAction(UiAction.SelectCategory(BlockId.Category, 0))
            handleAction(UiAction.SelectCategory(BlockId.Region, 0))
        }
    }
}