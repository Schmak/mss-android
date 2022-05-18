package com.mss.features.series.presentation.ui.landing

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.series.R
import com.mss.features.series.domain.usecases.*
import com.mss.features.series.presentation.mapper.LeadingSeriesItemMapper
import com.mss.features.series.presentation.mapper.MostRecentSeriesItemMapper
import com.mss.features.series.presentation.mapper.SeriesItemMapper
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

    fun handleAction(action: UiAction) = when (action) {
        is UiAction.Refresh -> refresh()
        is UiAction.SelectCategory ->
            viewModelState.update {
                it.copy(
                    selectedCategoryIdx = action.idx,
                    categorySeries = getCategorySeries(it.categories[action.idx])
                        .mapPage(SeriesItemMapper)
                        .cachedIn(viewModelScope)
                )
            }
        is UiAction.SelectRegion ->
            viewModelState.update {
                it.copy(
                    selectedRegionIdx = action.idx,
                    regionSeries = getRegionSeries(it.regions[action.idx])
                        .mapPage(SeriesItemMapper)
                        .cachedIn(viewModelScope)
                )
            }
    }

    private fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                leadingSeries = emptyFlow(),
                regionSeries = emptyFlow(),
                categorySeries = emptyFlow(),
                mostRecent = emptyFlow(),
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
                        leadingSeries = getLeadingSeries()
                            .mapPage(LeadingSeriesItemMapper)
                            .cachedIn(viewModelScope),
                        categories = categories.data,
                        selectedCategoryIdx = 0,
                        categorySeries = getCategorySeries(categories.data[0])
                            .mapPage(SeriesItemMapper)
                            .cachedIn(viewModelScope),
                        regions = regions.data,
                        selectedRegionIdx = 0,
                        regionSeries = getRegionSeries(regions.data[0])
                            .mapPage(SeriesItemMapper)
                            .cachedIn(viewModelScope),
                        mostRecent = getMostRecentSeries()
                            .mapPage(MostRecentSeriesItemMapper)
                            .cachedIn(viewModelScope),
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