package com.mss.features.driver.presentation.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.domain.usecases.GetGoldenSeries
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.components.landing.viewmodel.AbstractLandingViewModel
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.driver.R
import com.mss.features.driver.domain.usecases.GetChampions
import com.mss.features.driver.domain.usecases.GetDriverCollection
import com.mss.features.driver.domain.usecases.GetSeasonDrivers
import com.mss.features.driver.domain.usecases.GetWinners
import com.mss.features.driver.presentation.mapper.DriverCollectionMapper
import com.mss.features.driver.presentation.mapper.DriverItemMapper
import com.mss.features.driver.presentation.ui.landing.BlockId.*
import com.mss.features.driver.presentation.ui.landing.state.DriverLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class DriverLandingViewModel @Inject constructor(
    private val getGoldenSeries: GetGoldenSeries,
    private val getSeasonDrivers: GetSeasonDrivers,
    private val getChampions: GetChampions,
    private val getWinners: GetWinners,
    private val getDriverCollection: GetDriverCollection,
) : AbstractLandingViewModel() {
    override val categories = BlockId.values().toList()
    private val viewModelState = MutableStateFlow(DriverLandingModelState(isLoading = true))

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
                collections = Collections.values().toList(),
                currentSeasonDrivers = actions.filterByCategory(CurrentSeason)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .mapNotNull { it.currentSeason?.slug }
                    .flatMapLatest { getSeasonDrivers(it) }
                    .mapPage(DriverItemMapper)
                    .cachedIn(viewModelScope),
                champions = actions.filterByCategory(Champions)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .flatMapLatest { getChampions(it.series.slug) }
                    .mapPage(DriverItemMapper)
                    .cachedIn(viewModelScope),
                winners = actions.filterByCategory(Winners)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .flatMapLatest { getWinners(it.series.slug) }
                    .mapPage(DriverItemMapper)
                    .cachedIn(viewModelScope),
                collectionDrivers = actions.filterByCategory(Collections)
                    .mapNotNull { viewModelState.value.collections[it.idx] }
                    .map { it.let(DriverCollectionMapper) }
                    .flatMapLatest { getDriverCollection(it) }
                    .mapPage(DriverItemMapper)
                    .cachedIn(viewModelScope),
            )
        }
        refresh()
    }

    override fun selectCategory(action: UiAction.SelectCategory) = when (action.blockId) {
        CurrentSeason -> viewModelState.update { it.copy(selectedSeasonIdx = action.idx) }
        Champions -> viewModelState.update { it.copy(selectedChampionSeriesIdx = action.idx) }
        Winners -> viewModelState.update { it.copy(selectedWinnerSeriesIdx = action.idx) }
        Collections -> viewModelState.update { it.copy(selectedCollectionIdx = action.idx) }
        else -> Timber.e("'${action.blockId}' block is not supported")
    }

    override fun refresh() {
        viewModelState.update {
            it.copy(
                isLoading = true,
                errorMessageId = null,
            )
        }

        viewModelScope.launch {
            val goldenSeries = getGoldenSeries()

            if (goldenSeries !is Success) {
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
                    goldenSeries = goldenSeries.data,
                    isLoading = false,
                    errorMessageId = null,
                )
            }
            resetCategories()
        }
    }
}