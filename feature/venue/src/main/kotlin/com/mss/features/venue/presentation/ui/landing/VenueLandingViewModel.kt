package com.mss.features.venue.presentation.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mss.core.domain.repository.VenueRepository
import com.mss.core.domain.usecases.GetGoldenSeries
import com.mss.core.ui.components.landing.UiAction
import com.mss.core.ui.components.landing.UiAction.Companion.filterByCategory
import com.mss.core.ui.components.landing.viewmodel.AbstractLandingViewModel
import com.mss.core.ui.utils.mapPage
import com.mss.core.utils.Result.Success
import com.mss.features.venue.R
import com.mss.features.venue.domain.usecases.GetSeasonVenues
import com.mss.features.venue.domain.usecases.GetVenueCollection
import com.mss.features.venue.presentation.mapper.VenueItemMapper
import com.mss.features.venue.presentation.ui.landing.BlockId.CurrentSeason
import com.mss.features.venue.presentation.ui.landing.state.VenueLandingModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class VenueLandingViewModel @Inject constructor(
    private val getGoldenSeries: GetGoldenSeries,
    private val getSeasonVenues: GetSeasonVenues,
    private val getVenueCollection: GetVenueCollection,
) : AbstractLandingViewModel() {
    override val categories = BlockId.values().toList()
    private val viewModelState = MutableStateFlow(VenueLandingModelState(isLoading = true))

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
                currentSeasonVenues = actions.filterByCategory(CurrentSeason)
                    .mapNotNull { viewModelState.value.goldenSeries.getOrNull(it.idx) }
                    .mapNotNull { it.currentSeason?.slug }
                    .flatMapLatest { getSeasonVenues(it) }
                    .mapPage(VenueItemMapper)
                    .cachedIn(viewModelScope),
                raceCircuitVenues = refreshActions
                    .flatMapLatest { getVenueCollection(VenueRepository.Collection.RaceCircuit) }
                    .mapPage(VenueItemMapper)
                    .cachedIn(viewModelScope),
                rallycrossVenues = refreshActions
                    .flatMapLatest { getVenueCollection(VenueRepository.Collection.Rallycross) }
                    .mapPage(VenueItemMapper)
                    .cachedIn(viewModelScope),
                streetCircuitVenues = refreshActions
                    .flatMapLatest { getVenueCollection(VenueRepository.Collection.StreetCircuit) }
                    .mapPage(VenueItemMapper)
                    .cachedIn(viewModelScope),
                roadCircuitVenues = refreshActions
                    .flatMapLatest { getVenueCollection(VenueRepository.Collection.RoadCircuit) }
                    .mapPage(VenueItemMapper)
                    .cachedIn(viewModelScope),
            )
        }
        refresh()
    }

    override fun selectCategory(action: UiAction.SelectCategory) =
        if (action.blockId == CurrentSeason)
            viewModelState.update { it.copy(selectedSeasonIdx = action.idx) }
        else
            Timber.e("'${action.blockId}' block is not supported")

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