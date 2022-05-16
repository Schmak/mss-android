package com.mss.features.series.presentation.ui.landing

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.utils.Result.Success
import com.mss.features.series.R
import com.mss.features.series.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesLandingViewModel @Inject constructor(
    private val getSeriesCategories: GetSeriesCategories,
    private val getSeriesRegions: GetSeriesRegions,
    private val getLeadingSeries: GetLeadingSeries,
    private val getMostRecentSeries: GetMostRecentSeries,
    private val getCategorySeries: GetCategorySeries,
    private val getRegionSeries: GetRegionSeries,
    private val application: Application,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(SeriesLandingModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        refresh()
    }

    fun handleAction(action: SeriesLandingAction) = when (action) {
        is SeriesLandingAction.Refresh -> refresh()
        is SeriesLandingAction.SelectCategory ->
            viewModelState.update {
                it.copy(
                    selectedCategoryIdx = action.idx,
                    categorySeries = getCategorySeries(it.categories[action.idx]).cachedIn(viewModelScope)
                )
            }
        is SeriesLandingAction.SelectRegion ->
            viewModelState.update {
                it.copy(
                    selectedRegionIdx = action.idx,
                    regionSeries = getRegionSeries(it.regions[action.idx]).cachedIn(viewModelScope)
                )
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

            viewModelState.update {
                if (categories is Success && regions is Success) {
                    it.copy(
                        leadingSeries = getLeadingSeries().cachedIn(viewModelScope),
                        categories = categories.data,
                        selectedCategoryIdx = 0,
                        categorySeries = getCategorySeries(categories.data[0]).cachedIn(viewModelScope),
                        regions = regions.data,
                        selectedRegionIdx = 0,
                        regionSeries = getRegionSeries(regions.data[0]).cachedIn(viewModelScope),
                        mostRecent = getMostRecentSeries().cachedIn(viewModelScope),
                        isLoading = false,
                        errorMessage = null,
                    )
                } else
                    it.copy(
                        errorMessage = application.getString(R.string.try_again),
                        isLoading = false,
                    )
            }
        }
    }
}