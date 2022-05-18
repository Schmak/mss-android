package com.mss.features.series.presentation.ui.landing

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.series.R
import com.mss.features.series.domain.usecases.*
import com.mss.features.series.presentation.mapper.LeadingSeriesItemMapper
import com.mss.features.series.presentation.mapper.MostRecentSeriesItemMapper
import com.mss.features.series.presentation.mapper.SeriesItemMapper
import com.mss.features.series.presentation.model.UiSeriesItem
import com.mss.features.series.presentation.ui.landing.state.SeriesFlows
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
    private val application: Application,
) : ViewModel(), SeriesFlows {
    private val viewModelState = MutableStateFlow(SeriesLandingModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    private val actions = MutableSharedFlow<UiAction>()

    override val mostRecent: Flow<PagingData<UiSeriesItem>> =
        actions
            .onSubscription { emit(UiAction.Refresh) }
            .filterIsInstance<UiAction.Refresh>()
            .flatMapLatest { getMostRecentSeries() }
            .mapPage(MostRecentSeriesItemMapper)
            .cachedIn(viewModelScope)

    override val leadingSeries: Flow<PagingData<UiSeriesItem>> =
        actions
            .onSubscription { emit(UiAction.Refresh) }
            .filterIsInstance<UiAction.Refresh>()
            .flatMapLatest { getLeadingSeries() }
            .mapPage(LeadingSeriesItemMapper)
            .cachedIn(viewModelScope)

    override val categorySeries: Flow<PagingData<UiSeriesItem>> =
        actions
            .onSubscription { emit(UiAction.SelectCategory(viewModelState.value.selectedCategoryIdx)) }
            .filterIsInstance<UiAction.SelectCategory>()
            .distinctUntilChanged()
            .flatMapLatest { getCategorySeries(category = viewModelState.value.categories[it.idx]) }
            .mapPage(SeriesItemMapper)
            .cachedIn(viewModelScope)

    override val regionSeries: Flow<PagingData<UiSeriesItem>> =
        actions
            .onSubscription { emit(UiAction.SelectRegion(viewModelState.value.selectedRegionIdx)) }
            .filterIsInstance<UiAction.SelectRegion>()
            .distinctUntilChanged()
            .flatMapLatest { getRegionSeries(region = viewModelState.value.regions[it.idx]) }
            .mapPage(SeriesItemMapper)
            .cachedIn(viewModelScope)

    init {
        handleAction(UiAction.Refresh)
    }

    fun handleAction(action: UiAction) = run {
        viewModelScope.launch { actions.emit(action) }
        when (action) {
            is UiAction.Refresh -> refresh()
            is UiAction.SelectCategory ->
                viewModelState.update { it.copy(selectedCategoryIdx = action.idx) }
            is UiAction.SelectRegion ->
                viewModelState.update { it.copy(selectedRegionIdx = action.idx) }
        }
    }

    private fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
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
                        errorMessage = application.getString(R.string.try_again),
                        isLoading = false,
                    )
                }
                return@launch
            }

            viewModelState.update {
                it.copy(
                    categories = categories.data,
                    selectedCategoryIdx = 0,
                    regions = regions.data,
                    selectedRegionIdx = 0,
                    isLoading = false,
                    errorMessage = null,
                )
            }
        }
    }
}